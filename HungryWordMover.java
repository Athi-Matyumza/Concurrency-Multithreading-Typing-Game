package typingTutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover extends Thread{
    static FallingWord myWord;
    static FallingWord[] words;
    private AtomicBoolean done;
    private AtomicBoolean pause;
    private Score score;
    CountDownLatch startLatch; //so all can start at once

    HungryWordMover(FallingWord word) {
        myWord = word;
    }

    HungryWordMover(FallingWord word, FallingWord[] words, WordDictionary dict, Score score,
                    CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {
        this(word);
        this.words = words;
        this.startLatch = startLatch;
        this.score=score;
        this.done=d;
        this.pause=p;
    }

    public void CollisionCheck(){//NEW
        for (int i = 0; i < words.length; i++) {
            if (myWord.collide(words[i], myWord)){
                score.missedWord();
                words[i].resetWord();
            }
        }
    }

    public void run() {

        //System.out.println(myWord.getWord() + " falling speed = " + myWord.getSpeed());
        try {
//            System.out.println(myWord.getWord() + " waiting to start " );
            startLatch.await();
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } //wait for other threads to start
//        System.out.println(myWord.getWord() + " started" );
        while (!done.get()) {
            //animate the word
            while (!myWord.dropped() && !done.get()) {
                CollisionCheck();
                myWord.slideX(10); // SHIFTS THE WORDS HORIZONTALLY

                try {
                    sleep(myWord.getSpeed());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                };
                while(pause.get()&&!done.get()) {};
            }
            if (!done.get() && myWord.dropped()) {
                score.missedWord();
                myWord.resetHungry();//NEW
            }
            myWord.resetHungry();
        }
    }

}
