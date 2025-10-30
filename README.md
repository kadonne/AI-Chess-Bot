# Akadic Chess Bot

A Java-based chess AI that uses advanced game tree search algorithms and position evaluation to play chess strategically.

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

```java
// Create an instance of the Akadic bot
Bot chessBot = new Akadic();

// The bot will automatically select moves when its turn arrives
// through the chooseMove() method which is called by the game framework
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
Bot Implementation: Custom chess AI using classical evaluation techniques