# Multithreaded Typing Tutor Game - Project Overview

 <div align=”center”> <img width="466" alt="Screenshot 2024-01-08 214725" align=”center” src="https://github.com/Athi-sirmatt/Concurrency-Multithreading-Typing-Game/assets/93771863/9617aa6d-9e57-477f-9356-c66ad7f38d32"> </div> 


## Introduction

This project represents an extension of a Java-based multithreaded educational "Typing Tutor" game, focusing on enhancing thread synchronization and ensuring thread safety. The goal was to introduce additional features to the existing game, such as resolving duplicate word disappearance order and incorporating a new element called "Hungry Word."

## Project Description

### Typing Tutor Game

The Typing Tutor game challenges players to type words before they reach the bottom of the screen. Words fall at different speeds, and the player must type them correctly to score points. The game continues until the player either wins by typing a specified number of words correctly or loses by missing three words.

### Key Features

1. **Duplicate Word Disappearance Order:**
   - Ensured that when duplicate words appear on the screen, the lowest word disappears first, enhancing gameplay fairness.

2. **Hungry Word:**
   - Introduced a green word, the "Hungry Word," randomly selected from the dictionary.
   - The Hungry Word moves horizontally across the middle of the screen, interacting with falling words.
   - If the player types the Hungry Word, it disappears, and the caught counter increases.
   - If the Hungry Word exits without being typed, the missed counter increases.

### User Interaction

- **Start Button:**
  - Initiates the game with falling words.
- **Quit Game Button:**
  - Stops the current game and displays "Game Over."
- **Pause Button:**
  - Halts the game, preventing the player from typing words.
- **Exit Button:**
  - Exits the application completely.

### How to Play

1. Press the Start button to begin a new game.
2. Type words correctly to score points.
3. Avoid missing three words to prevent game over.
4. Handle the Hungry Word strategically to maximize points.

## Implementation

- Modified existing code without disrupting the core functionality.
- Introduced `HungryWordMover.java` class for the Hungry Word functionality.
- Ensured synchronization and thread safety for a seamless gaming experience.
- Verified that the game still adheres to the original specifications after additions and modifications.

## Acknowledgments

- Original code provided as a base for extension.
- Project developed as part of an educational assignment.
- Collaborated with peers to enhance and test the game features.

---

**Note:** This project was developed as part of an educational assignment, and its purpose is to showcase skills in Java programming, multithreading, and game development. It may be subject to further improvements and enhancements.
