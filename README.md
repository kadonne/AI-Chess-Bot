# Akadic Chess Bot

A Java-based chess AI that uses advanced game tree search algorithms and position evaluation to play chess strategically.
You can find my source code under `src/com/stephengware/java/games/chess/bot`.

## Overview

Akadic is a chess bot implementation that extends the `Bot` class from the `com.stephengware.java.games.chess` framework. It employs sophisticated search algorithms and evaluation heuristics to determine optimal moves during gameplay.

## Core Features

### Search Algorithm

* **Iterative Deepening Depth-First Search (IDDFS)**: Progressively searches deeper into the game tree until time limits are reached
* **Alpha-Beta Pruning**: Optimizes search by eliminating branches that cannot affect the final decision
* **Default Search Depth**: 6 plies (half-moves) from the current position

### Position Evaluation

The bot evaluates chess positions using two primary components:

#### 1. Material Values

Standard piece values used for evaluation:

* Queen: 900 points
* Rook: 500 points
* Bishop: 330 points
* Knight: 320 points
* Pawn: 100 points
* King: 10 points (base value)

#### 2. Positional Bonuses

Each piece type has position-specific bonuses based on:

* **Pawn Structure**: Encourages pawn advancement and center control
* **Knight Positioning**: Rewards centralization and punishes edge placement
* **Bishop Development**: Favors active diagonal control
* **Rook Placement**: Promotes open files and seventh rank control
* **Queen Activity**: Balances aggression with safety
* **King Safety**: Different tables for middlegame positioning

Separate positional tables are maintained for white and black pieces to account for board orientation.

## Move Ordering

The bot uses intelligent move ordering to improve alpha-beta pruning efficiency:

1. **Check Responses**: When in check, all legal moves are evaluated
2. **Capture Moves**: Prioritized by captured piece value
   * Queen captures (highest priority)
   * Rook/Bishop/Knight captures
   * Pawn captures (lowest priority)
3. **Quiet Moves**: Non-capture moves ordered by positional improvement

## Special Handling

* **Checkmate Detection**: Awards/penalizes 10,000 points
* **Check Evaluation**: Applies King piece value bonus/penalty
* **Stalemate**: Penalizes by 500 points to avoid draws
* **Pawn Promotion**: Automatically promotes pawns to Queens
* **Time Management**: Respects search time limits via `searchLimitReached()`

## Usage
Export Akadic.java as a JAR file and place it in the same directory as Chess.jar.
Open the terminal and run the following command:
```bash
java -Xms4g -Xmx4g -XX:NewSize=3g -jar chess.jar 2 akadic.jar bots/random.jar bots/greedy.jar
bots/novice.jar bots/beginner.jar bots/intermediate.jar
```
### Alternative Usage
You can also run the Java file below, which is located in `src/com/stephengware/java/games/chess/bot`.
This will let you play against my bot by yourself.

```java
package com.stephengware.java.games.chess.bot;

import java.io.IOException;

import com.stephengware.java.games.chess.Tournament;
import com.stephengware.java.games.chess.gui.ChessDisplay;
import com.stephengware.java.games.chess.gui.Piece;

/**
 * @author Stephen G. Ware
 */
public class Test {

	/**
	 * Runs a 2 game tournament of all bots.
	 * 
	 * @param args ignored
	 * @throws IOException if an input exception occurs while loading
	 */
	public static void main(String[] args) throws IOException {
		Piece.load();
		Bot[] bots = new Bot[]{
				new Human(),
				new Akadic(),
//			new RandomBot(),
//			new GreedyBot(),
//			new NoviceBot(),
//			new BeginnerBot(),
//			new IntermediateBot(),
		};
		Tournament tournament = new Tournament(2, bots);
		tournament.play();
		ChessDisplay.getInstance().console.append(tournament.toString());
	}
}
```

## Implementation Details

### Key Classes

* **Akadic**: Main bot class that implements move selection
* **Result**: Internal class representing board positions with evaluation scores and move lists

### Algorithm Flow

1. Initialize search from current board state
2. Iteratively deepen search until time limit or maximum depth (6 plies)
3. For each position:
   * Generate and order moves
   * Recursively search with alpha-beta pruning
   * Evaluate leaf positions using material + positional scoring
4. Return the move leading to the best evaluated position

## Technical Notes

* Uses `Hashtable` for piece value and positional table lookups
* Implements minimax algorithm with alpha-beta pruning for adversarial search
* Separates capture moves from quiet moves for efficient move ordering
* Handles en passant, castling, and pawn promotion through the underlying chess framework

## Limitations

* Fixed search depth of 6 plies (configurable via `plies` variable)
* No opening book or endgame tablebase
* Positional evaluation is based on static piece-square tables
* Does not implement transposition tables for position caching

## Author

Stephen G. Ware (Original Framework)  
Ammar Kadic (Akadic class)
Bot Implementation: Custom chess AI using classical evaluation techniques
