package typingTutor;

import java.util.ServiceLoader;

public class FallingWord {
	private String word; // the word
	private int x; //position - width
	private int y; // postion - height

	private String Orient;
	private int max; //maximum height/width
	private boolean dropped; //flag for if user does not manage to catch word in time
	
	private int fallingSpeed; //how fast this word is
	private static int maxWait=1000;
	private static int minWait=100;

	public static WordDictionary dict;

	FallingWord() { //constructor with defaults
		word="computer"; // a default - not used
		x=0;
		y=0;
		max=300;
		dropped=false;
		Orient = "Vert";
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait);
	}

	FallingWord(String Orient) { //constructor with defaults
		this();
		y=0;
		this.Orient = Orient;
	}
	
	FallingWord(String text, String Orient) {
		this(Orient);
		this.word=text;
	}

	FallingWord(String text,int x,int max, String Orient) { //most commonly used constructor - sets it all.
		this(text, Orient);
		this.x = x;
		this.max = max;
	}

	FallingWord(String text,int x, int max, int ylimit, String Orient) { //most commonly used constructor - sets it all.
		this(text, Orient);

		if (Orient.equals("Hori")) {
			this.x = 0;
			y = ylimit/2;
		}else{
			this.x = x;//only need to set x, word is at top of screen at start
		}

		this.max=max;
	}
	
	public static void increaseSpeed( ) {
		minWait+=50;
		maxWait+=50;
	}
	
	public static void resetSpeed( ) {
		maxWait=1000;
		minWait=100;
	}
	

// all getters and setters must be synchronized
	public synchronized  void setY(int y) {
		if (y>max) {
			y=max;
			dropped=true; //user did not manage to catch this word
		}
		this.y=y;
	}
	
	public synchronized  void setX(int x) {
		if ((x+word.length())>=max) {
			x=max;
			dropped=true; //user did not manage to catch this word
		}

		this.x=x;
	}
	
	public synchronized  void setWord(String text) {
		this.word=text;
	}

	public synchronized  String getWord() {
		return word;
	}
	
	public synchronized  int getX() {
		return x;
	}	
	
	public synchronized  int getY() {
		return y;
	}

	public synchronized String getOrient() {
		return Orient;
	}
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}

	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
	public synchronized void resetPos() {
		setY(0);
	}

	public synchronized void resetPosX() { //NEW
		setX(0);
	}

	public synchronized void resetWord() {
		resetPos();
		word=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait);
		//System.out.println(getWord() + " falling speed = " + getSpeed());
	}

	public synchronized void resetHungry() {//NEW
		resetPosX();
//		System.out.println("REACHES RESET-H");
		word=dict.getNewWord();
//		System.out.println(word);
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait);
		//System.out.println(getWord() + " falling speed = " + getSpeed());
	}
	
	public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.word)) {
			resetWord();
			return true;
		}
		else
			return false;
	}

	public synchronized  void drop(int inc) {
		setY(y+inc);
	}

	public synchronized void slideX(int inc){
		setX(x+inc);
	}
	
	public synchronized  boolean dropped() {
		return dropped;
	}

	public synchronized boolean collide(FallingWord falling, FallingWord sliding){//NEW
		//CHECK WHETHER THE WORD HAS BEEN TOUCHED BY THE HUNGRY WORD
		int xHun = sliding.getX();
		int xVert = falling.getX();
		int maxHun = xHun + sliding.getWord().length();
		int maxVert = xVert + falling.getWord().length();


		if ((Math.abs(falling.getX()-sliding.getX()) < 60) && (Math.abs(falling.getY()-sliding.getY())<20)){
//			System.out.println("collided "+ falling.getWord() + " ------- Falling X=" +xVert + ", Y="+ falling.getY() +
//					" and sliding X=" + xHun+ ", Y=" + sliding.getY());
			return true;
		}

		return false;
	}

}
