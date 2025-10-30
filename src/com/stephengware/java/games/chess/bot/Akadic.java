package com.stephengware.java.games.chess.bot;

import java.util.Iterator;
import java.util.Random;
import java.util.Hashtable;
import java.util.LinkedList;

import com.stephengware.java.games.chess.bot.Bot;
import com.stephengware.java.games.chess.state.*;

/**
 * @author Ammar Kadic
 */
public class Akadic extends Bot {

	/** A random number generator */
	public  final Hashtable<Class<?>, Integer> material = new Hashtable<>();
	public Hashtable<Class<?>, int[][]> w_positional = new Hashtable<>();
	public Hashtable<Class<?>, int[][]> b_positional = new Hashtable<>();
	private Player akadic;
	private Player opp;
	public int turn;
	public int plies;
	public Random rand;
	public Result root;
	public int[][] wpawn_position = {
			{0, 0, 0, 0, 0, 0, 0, 0},
			{5, 10, 10, -20, -20, 10, 10, 5},
			{5, -5, -10, 0, 0, -10, -5, 5},
			{0, 0, 0, 20, 20, 0, 0, 0},
			{5, 5, 10, 25, 25, 10, 5, 5},
			{10, 10, 20, 30, 30, 20, 10, 10},
			{50, 50, 50, 50, 50, 50, 50, 50},
			{0, 0, 0, 0, 0, 0, 0, 0}
	};
	public int[][] bpawn_position = {
			{0, 0, 0, 0, 0, 0, 0, 0},
			{50, 50, 50, 50, 50, 50, 50, 50},
			{10, 10, 20, 30, 30, 20, 10, 10},
			{5, 5, 10, 25, 25, 10, 5, 5},
			{0, 0, 0, 20, 20, 0, 0, 0}, 
			{5, -5, -10, 0, 0, -10, -5, 5},
			{5, 10, 10, -20, -20, 10, 10, 5},
			{0, 0, 0, 0, 0, 0, 0, 0}
	};
	public int[][] bknight_position = {
			{-50, -40, -30, -30, -30, -30, -40, -50},
			{-40, -20, 0, 0, 0, 0, -20, -40},
			{-30, 0, 10, 15, 15, 10, 0, -30},
			{-30, 5, 15, 20, 20, 15, 5, -30},
			{-30, 0, 15, 20, 20, 15, 0, -30},
			{-30, 5, 10, 15, 15, 10, 5, -30},
			{-40, -20, 0, 5, 5, 0, -20, -40},
			{-50, -40, -30, -30, -30, -30, -40, -50}
	};
	public  int[][] wknight_position = {
			{-50, -40, -30, -30, -30, -30, -40, -50},
			{-40, -20, 0, 5, 5, 0, -20, -40},
			{-30, 5, 10, 15, 15, 10, 5, -30},
			{-30, 0, 15, 20, 20, 15, 0, -30},
			{-30, 5, 15, 20, 20, 15, 5, -30},
			{-30, 0, 10, 15, 15, 10, 0, -30},
			{-40, -20, 0, 0, 0, 0, -20, -40},
			{-50, -40, -30, -30, -30, -30, -40, -50}
	};
	public int[][] bbishop_position = {
			{-20, -10, -10, -10, -10, -10, -10, -20},
			{-10, 0, 0, 0, 0, 0, 0, -10},
			{-10, 0, 5, 10, 10, 5, 0, -10},
			{-10, 5, 5, 10, 10, 5, 5, -10},
			{-10, 0, 10, 10, 10, 10, 0, -10},
			{-10, 10, 10, 10, 10, 10, 10, -10},
			{-10, 5, 0, 0, 0, 0, 5, -10},
			{-20, -10, -10, -10, -10, -10, -10, -20}
	};
	public int[][] wbishop_position = {
			{-20, -10, -10, -10, -10, -10, -10, -20},
			{-10, 5, 0, 0, 0, 0, 5, -10},
			{-10, 10, 10, 10, 10, 10, 10, -10},
			{-10, 0, 10, 10, 10, 10, 0, -10},
			{-10, 5, 5, 10, 10, 5, 5, -10},
			{-10, 0, 5, 10, 10, 5, 0, -10},
			{-10, 0, 0, 0, 0, 0, 0, -10},
			{-20, -10, -10, -10, -10, -10, -10, -20}
			
	};
	public int[][] brook_position = {
			{0, 0, 0, 0, 0, 0, 0, 0},
			{5, 10, 10, 10, 10, 10, 10, 5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{0, 0, 0, 5, 5, 0, 0, 0}
	};
	public int [][] wrook_position = {
			{0, 0, 0, 5, 5, 0, 0, 0},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{-5, 0, 0, 0, 0, 0, 0, -5},
			{5, 10, 10, 10, 10, 10, 10, 5},
			{0, 0, 0, 0, 0, 0, 0, 0},
			
	};
	public int[][] bqueen_position = {
			{-20, -10, -10, -5, -5, -10, -10, -20},
			{-10, 0, 0, 0, 0, 0, 0, -10},
			{-10, 0, 5, 5, 5, 5, 0, -10},
			{-5, 0, 5, 5, 5, 5, 0, -5},
			{-5, 0, 5, 5, 5, 5, 0, 0},
			{-10, 5, 5, 5, 5, 5, 0, -10},
			{-10, 0, 0, 0, 0, 5, 0, -10}, 
			{-20, -10, -10, -5, -5, -10, -10, -20}
			
	};
	public int[][] wqueen_position = {
			{-20, -10, -10, -5, -5, -10, -10, -20},
			{-10, 0, 5, 0, 0, 0, 0, -10},
			{-10, 5, 5, 5, 5, 5, 0, -10},
			{0, 0, 5, 5, 5, 5, 0, -5},
			{-5, 0, 5, 5, 5, 5, 0, -5},
			{-10, 0, 5, 5, 5, 5, 0, -10},
			{-10, 0, 0, 0, 0, 0, 0, -10},
			{-20, -10, -10, -5, -5, -10, -10, -20}
	};
	public int[][] bking_mid = {
			{-50, -40, -30, -20, -20, -30, -40, -50},
			{-30, -20, -10, 0, 0, -10, -20, -30},
			{-30, -10, 20, 30, 30, 20, -10, -30},
			{-30, -10, 30, 40, 40, 30, -10, -30},
			{-30, -10, 30, 40, 40, 30, -10, -30},
			{-30, -10, 20, 30, 30, 20, -10, -30},
			{-30, -30, 0, 0, 0, 0, -30, -30},
			{-50, -30, -30, -30, -30, -30, -30, -50}
	};
	public int[][] wking_mid = {
			{-50, -30, -30, -30, -30, -30, -30, -50},
			{-30, -30, 0, 0, 0, 0, -30, -30},
			{-30, -10, 20, 30, 30, 20, -10, -30},
			{-30, -10, 30, 40, 40, 30, -10, -30},
			{-30, -10, 30, 40, 40, 30, -10, -30},
			{-30, -10, 20, 30, 30, 20, -10, -30},
			{-30, -20, -10, 0, 0, -10, -20, -30},
			{-50, -40, -30, -20, -20, -30, -40, -50},
	};
	
	
	public Akadic() {
		super("Akadic");
		this.material.put(Queen.class, 900);
		this.material.put(Rook.class, 500);
		this.material.put(Bishop.class, 330);
		this.material.put(Knight.class, 320);
		this.material.put(Pawn.class, 100);
		this.material.put(King.class, 10);
		
		this.w_positional.put(Pawn.class, this.wpawn_position);
		this.b_positional.put(Pawn.class, this.bpawn_position);

		this.w_positional.put(Knight.class, this.wknight_position);
		this.b_positional.put(Knight.class, this.bknight_position);

		this.w_positional.put(Bishop.class, this.wbishop_position);
		this.b_positional.put(Bishop.class, this.bbishop_position);

		this.w_positional.put(Rook.class, this.wrook_position);
		this.b_positional.put(Rook.class, this.brook_position);

		this.w_positional.put(Queen.class, this.wqueen_position);
		this.b_positional.put(Queen.class, this.bqueen_position);

		this.w_positional.put(King.class, this.wking_mid);
		this.b_positional.put(King.class, this.bking_mid);
	}
	
	@Override
	protected State chooseMove(State root) {
		this.akadic = root.player;
		this.opp = akadic.other();
		this.turn = root.turn;
		this.plies = 6;
		this.root = new Result(root, null, null, akadic, opp, this.material, this.w_positional, this.b_positional);
		Result best = IDDFS(root, root.turn+this.plies);
		
		return best.state;
	}
	
	public Result max(Result one, Result two) 
	{ 
		if(one.material_score > two.material_score)
			return one;
		return two;
	}
	public Result min(Result one, Result two) 
	{ 
		if(one.material_score < two.material_score)
			return one;
		return two;
	}
	public Result IDDFS(State root, int cutoff)
	{
		Result alpha = new Result(-10000);
		Result beta = new Result(10000);
		Result best = null;
		Result possible_best = null;
		for(int depth=root.turn; depth<=cutoff; depth++)
		{
			possible_best = alphabeta_DLS(this.root, alpha, beta, depth, true);
			if(!this.root.state.searchLimitReached())
				best = possible_best;
		}
		return best;
	}
	public Result alphabeta_DLS(Result current, Result alpha, Result beta, int cutoff, boolean max_player)
	{
		if(cutoff == this.turn || current.state.over || this.root.state.searchLimitReached())
			return current;
		Result best;
		Result next;
		State next_state;
		if(max_player)
			best = new Result(-10000);
		else
			best = new Result(10000);
		if(current.check)
		{
			Iterator<State> iterator = current.state.next().iterator();
			while(!this.root.state.searchLimitReached() && iterator.hasNext())
			{
				next_state = iterator.next();
				next = new Result(next_state, current, this.root, this.akadic, this.opp, this.material, this.w_positional, this.b_positional);
				if(max_player)
				{
					best = max(best, alphabeta_DLS(next, alpha, beta, cutoff-1, false));
					if(best.material_score > beta.material_score)
						break;
					alpha = max(alpha, best);
				}
				else
				{
					best = min(best, alphabeta_DLS(next, alpha, beta, cutoff-1, true));
					if(best.material_score < alpha.material_score)
						break;
					beta = min(beta, best);
				}
			}
			if(best.state == null)
				return current;
			return best.get_current_turn();
		}
		if(!current.capture_moves.isEmpty())
		{
			for(Piece[] capture : current.capture_moves)
			{
				if(this.root.state.searchLimitReached())
					break;
				try 
				{
					next_state = current.state.next(capture[0], capture[1]);
				} catch(IllegalArgumentException e) { continue; }
				next = new Result(next_state, current, this.root, this.akadic, this.opp, this.material, this.w_positional, this.b_positional);
				if(max_player)
				{
					best = max(best, alphabeta_DLS(next, alpha, beta, cutoff-1, false));
					if(best.material_score > beta.material_score)
						break;
					alpha = max(alpha, best);
				}
				else
				{
					best = min(best, alphabeta_DLS(next, alpha, beta, cutoff-1, true));
					if(best.material_score < alpha.material_score)
						break;
					beta = min(beta, best);
				}
			}
		}
		for(Piece[] other : current.other_moves)
		{
			if(this.root.state.searchLimitReached())
				break;
			try
			{
				next_state = current.state.next(other[0], other[1]);
			} catch(IllegalArgumentException e) { continue; }
			next = new Result(next_state, current, this.root, this.akadic, this.opp, this.material, this.w_positional, this.b_positional);
			if(max_player)
			{
				best = max(best, alphabeta_DLS(next, alpha, beta, cutoff-1, false));
				if(best.material_score > beta.material_score)
					break;
				alpha = max(alpha, best);
			}
			else
			{
				best = min(best, alphabeta_DLS(next, alpha, beta, cutoff-1, true));
				if(best.material_score < alpha.material_score)
					break;
				beta = min(beta, best);
			}
			
		}
		if(best.state == null)
			return current;
		return best.get_current_turn();
	}
}
class Result 
{
	public State state;
	public Result parent;
	public Result root;
	public double material_score;
	public boolean checkmate;
	public boolean stalemate;
	public boolean check;
	public LinkedList<Piece[]> capture_moves;
	public LinkedList<Piece[]> other_moves;
	public Result(int material_score)
	{
		this.material_score = material_score;
		this.state = null;
	}
	public Result(State state, Result parent, Result root, Player akadic, Player opp,
			Hashtable<Class<?>, Integer> material, Hashtable<Class<?>, int[][]> w_positional, Hashtable<Class<?>, int[][]> b_positional)
	{
		this.state = state;
		this.parent = parent;
		this.root = root;
		this.other_moves = new LinkedList<>();
		this.capture_moves = new LinkedList<Piece[]>();
		this.material_score = 0;
		this.check = state.check;
		this.stalemate = !state.check && state.over;
		this.checkmate = state.check && state.over;
		boolean max_player = state.player == akadic;
		if(this.stalemate)
			update_mat_score(-500);
		if(max_player)
		{
			if(this.check)
			{
				if(this.checkmate)
					update_mat_score(-10000);
				else
					update_mat_score(-material.get(King.class));
			}
		}
		else if(!max_player)
		{
			if(this.check)
			{
				if(this.checkmate)
					update_mat_score(10000);
				else
					update_mat_score(material.get(King.class));
			}
		}
		for(Piece piece: state.board)
		{
			if(piece.player==akadic)
			{
				this.material_score += material.get(piece.getClass());
					if(akadic == Player.WHITE)
						this.material_score += w_positional.get(piece.getClass())[piece.rank][piece.file];
					else
						this.material_score += b_positional.get(piece.getClass())[piece.rank][piece.file];
			}
			if(piece.player==opp)
			{
				this.material_score -= material.get(piece.getClass());
					if(opp == Player.WHITE)
						this.material_score -= w_positional.get(piece.getClass())[piece.rank][piece.file];
					else
						this.material_score -= b_positional.get(piece.getClass())[piece.rank][piece.file];
					
			}
			if(!check && !state.over && piece.player == state.player)
			{
				if(piece.player == Player.WHITE)
					evaluate_piece(piece, state.board, w_positional.get(piece.getClass()));
				else
					evaluate_piece(piece, state.board, b_positional.get(piece.getClass()));
					
			}
		}
	}
	public void evaluate_piece(Piece piece, Board board, int[][] positional)
	{
		int file = piece.file;
		int rank = piece.rank;
		if(piece.getClass() == King.class)
		{
			int[][] moves = { 
					{1,0}, {0,1}, {1,1}, 
					{-1, 0}, {0, -1}, {-1,-1},
					{1,-1}, {-1,1} };
			for(int[] j : moves)
			{
				int new_file = j[0]+file;
				int new_rank = j[1]+rank;
				if(Board.isValid(new_file, new_rank) && !board.pieceAt(new_file, new_rank, piece.player))
				{
					if(board.pieceAt(new_file, new_rank, piece.player.other()))
					{
						Piece enemy = board.getPieceAt(new_file, new_rank);
						Piece[] k = {piece, piece.move(j[0], j[1]) };
						if(enemy.getClass() == Queen.class)
							this.capture_moves.addFirst(k);
						else if(enemy.getClass() == Knight.class
								|| enemy.getClass() == Bishop.class
								|| enemy.getClass() == Rook.class)
							this.capture_moves.add(k);
						else if(enemy.getClass() == Pawn.class)
							this.capture_moves.addLast(k);
					}
					else if(!board.pieceAt(new_file, new_rank))
					{
						Piece[] k = {piece, piece.move(j[0], j[1]) };
						if(positional[new_rank][new_file] > positional[rank][file])
							this.other_moves.addFirst(k);
						else
							this.other_moves.addLast(k);
					}
				}
			}
			
		}
		if(piece.getClass() == Pawn.class)
		{
			int[][] moves = { {0,1} };
			int[][] captures = { {1,1}, {-1,1} };
			if(!board.hasMoved(piece))
			{
				int[][] new_move = { {0,1}, {0,2} };
				moves = new_move;
			}
			if(piece.player == Player.BLACK)
			{
				int[][] c = { {1, -1}, {-1, -1} };
				int[][] black = { {0,-1} };
				if(!board.hasMoved(piece))
				{
					int[][] new_black = { {0,-1}, {0,-2} };
					black = new_black;
				}
				moves = black;
				captures = c;
			}
			for (int[] j: captures)
			{
				int new_file = j[0] + file;
				int new_rank = j[1] + rank;
				if(Board.isValid(new_file, new_rank) && !board.pieceAt(new_file, new_rank, piece.player))
				{
					if(board.pieceAt(new_file, new_rank, piece.player.other()))
					{
						Piece enemy = board.getPieceAt(new_file, new_rank);
						Piece[] k = {piece, piece.move(j[0], j[1]) };
						if(k[1].rank == 7 || k[1].rank == 0)
							k[1] = new Queen(piece.player, new_file, new_rank);
						if(enemy.getClass() == Queen.class)
							this.capture_moves.addFirst(k);
						else if(enemy.getClass() == Knight.class
								|| enemy.getClass() == Bishop.class
								|| enemy.getClass() == Rook.class)
							this.capture_moves.add(k);
						else if(enemy.getClass() == Pawn.class)
							this.capture_moves.addLast(k);
					}
				}
			}
			for(int[] j : moves)
			{
				int new_file = j[0] + file;
				int new_rank = j[1] + rank;
				if(Board.isValid(new_file, new_rank) && !board.pieceAt(new_file, new_rank))
				{
						Piece[] k = {piece, piece.move(j[0], j[1]) };
						if(k[1].rank == 7 || k[1].rank == 0)
							k[1] = new Queen(piece.player, new_file, new_rank);
						if(positional[new_rank][new_file] > positional[rank][file])
							this.other_moves.addFirst(k);
						else if(k[1].rank == 7 || k[1].rank == 0)
							this.other_moves.addFirst(k);
						else
							this.other_moves.addLast(k);
				}
				else 
					break;
				
				
			}
			
		}
		if(piece.getClass() == Knight.class)
		{
			int[][] moves = { 
					{2, 1}, {2, -1}, {-2, 1}, {-2,-1}, 
					{1, 2}, {1, -2}, {-1, 2}, {-1, -2} };
			for(int[] j: moves)
			{
				int new_file = j[0] + file;
				int new_rank = j[1] + rank;
				if(!this.state.searchLimitReached() && Board.isValid(new_file, new_rank) && !board.pieceAt(new_file, new_rank, piece.player))
				{
					if(board.pieceAt(new_file, new_rank, piece.player.other()))
					{
						Piece enemy = board.getPieceAt(new_file, new_rank);
						Piece[] k = {piece, piece.move(j[0], j[1])};
						if(enemy.getClass() == Queen.class)
							this.capture_moves.addFirst(k);
						else if(enemy.getClass() == Knight.class
								|| enemy.getClass() == Bishop.class
								|| enemy.getClass() == Rook.class)
							this.capture_moves.add(k);
						else if(enemy.getClass() == Pawn.class)
							this.capture_moves.addLast(k);
					}
					else if(!board.pieceAt(new_file, new_rank))
					{
						Piece[] k = {piece, piece.move(j[0], j[1])};
						if(positional[new_rank][new_file] > positional[rank][file])
							this.other_moves.addFirst(k);
						else
							this.other_moves.addLast(k);
					}
				}
			}
		}
		if(piece.getClass() == Bishop.class || piece.getClass() == Queen.class)
		{
			int[][] moves = {
					{1, 1}, {1, -1}, {-1, 1}, {-1, -1} };
			for(int[] j : moves)
			{
				for(int i = 1; i<=7; i++)
				{
					int new_file = j[0]*i + file;
					int new_rank = j[1]*i + rank;
					if(Board.isValid(new_file, new_rank))
					{
						if(board.pieceAt(new_file, new_rank, piece.player.other()))
						{
							Piece enemy = board.getPieceAt(new_file, new_rank);
							if(enemy.getClass() == King.class)
								break;
							Piece[] k = {piece, piece.move(j[0]*i, j[1]*i)};
							if(enemy.getClass() == Queen.class)
								this.capture_moves.addFirst(k);
							else if(enemy.getClass() == Knight.class
									|| enemy.getClass() == Bishop.class
									|| enemy.getClass() == Rook.class)
								this.capture_moves.add(k);
							else if(enemy.getClass() == Pawn.class)
								this.capture_moves.addLast(k);
							break;
						}
						else
						{
							if(board.pieceAt(new_file, new_rank))
								break;
							Piece[] k = {piece, piece.move(j[0]*i, j[1]*i)};
							if(positional[new_rank][new_file] > positional[rank][file])
								this.other_moves.addFirst(k);
							else
								this.other_moves.addLast(k);
						}
					}
					else
						break;
				}
			}
		}
		if(piece.getClass() == Rook.class || piece.getClass() == Queen.class)
		{
			int[][] moves = {
					{1, 0}, {-1, 0}, {0, 1}, {0, -1} };
			for(int[] j : moves)
			{
				for(int i = 1; i<=7; i++)
				{
					int new_file = j[0]*i + file;
					int new_rank = j[1]*i + rank;
					if(Board.isValid(new_file, new_rank))
					{
						if(board.pieceAt(new_file, new_rank, piece.player.other()))
						{
							Piece enemy = board.getPieceAt(new_file, new_rank);
							if(enemy.getClass() == King.class)
								break;
							Piece[] k = {piece, piece.move(j[0]*i, j[1]*i)};
							if(enemy.getClass() == Queen.class)
								this.capture_moves.addFirst(k);
							else if(enemy.getClass() == Knight.class
									|| enemy.getClass() == Bishop.class
									|| enemy.getClass() == Rook.class)
								this.capture_moves.add(k);
							else if(enemy.getClass() == Pawn.class)
								this.capture_moves.addLast(k);
							break;
						}
						else
						{
							if(board.pieceAt(new_file, new_rank, piece.player))
								break;
							Piece[] k = {piece, piece.move(j[0]*i, j[1]*i)};
							if(positional[new_rank][new_file] > positional[rank][file])
								this.other_moves.addFirst(k);
							else
								this.other_moves.addLast(k);
						}
					}
					else
						break;
				}
			}
		}
	}
	public Result get_current_turn()
	{
		Result current = this;
		if(current.state!=null)
		{
			if(current.state.previous!=this.root.state)
				current.state = current.state.previous;
		}
		return current;
	}
	public void update_mat_score(double check)
	{
		this.material_score+=check;
	}
	@Override
	public String toString()
	{
		return this.material_score+"";
	}
	
}
