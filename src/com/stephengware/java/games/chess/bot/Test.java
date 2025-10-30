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
//				new RandomBot(),
//				new GreedyBot(),
//				new NoviceBot(),
//				new BeginnerBot(),
///				new IntermediateBot(),
		};
		Tournament tournament = new Tournament(2, bots);
		tournament.play();
		ChessDisplay.getInstance().console.append(tournament.toString());
	}
}
