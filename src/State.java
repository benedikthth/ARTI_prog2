import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.Height;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

public class State {
	
	boolean whitePlayerMove;
	
	Environment environment;
	
	// to reference 1D as 2D: i = row * row + col
	
	public Board board;
	
	int len;
	
	public State() {
		
		this.environment  = Environment.GetInstance();
		
		this.board = new BitsetBoard();
		
		this.board.print();

	}
	
	

	public State clone() {
		
		// return a new State with the same properties as this one
		return null;
		
	}

	
	
	
	
	public List<Action> legalMoves(Tile player) {
		
		if(player == Tile.EMPTY) {
			System.out.println("Terminal failure. Received Empty player in legal moves");
			return null;
		}
		
		Tile opponent = (player == Tile.WHITE)? Tile.BLACK: Tile.WHITE;
		
		List<Action> legalActions = new ArrayList<Action>();
		
		for(int y = 0; y < environment.height; y++) {
			for(int x = 0; x < environment.width; x++) {
				
				
				
				if(board.get(x, y) != player) {
					//no possible moves.
					continue;
				}
				
				
				// x, y = location of a pawn.
				
				
				
				//white goes up. black goes down.
				int direction = (player == Tile.WHITE)? 1: -1;
				
				
				
				//check for diagonal left.
				//There must be a tile of the type opponent 
				if(x != 0) {
					if(board.get(x-1, y+direction) == opponent) {
						legalActions.add(new Action(x, y, x-1, y+direction)) ;
					}
				} 
				//straight!
				//there must be a vacant spot for this move to be legal.
				if(board.get(x, y+direction) == Tile.EMPTY) {
					legalActions.add(new Action(x, y, x, y+direction));
				}
				
				
				//diagonal right
				//there must be an opponent there for this move to be legal
				if(x != environment.width-1) {
					if(board.get(x+1, y+direction) == opponent) {
						legalActions.add(new Action(x, y, x+1, y+direction)) ;
					}
				}
				
			}
		}
		
		return legalActions;
	}
	
	
	
	
	
	
	public State ApplyAction( Action action ) {
			
		State newState = this.clone();
	
		newState.whitePlayerMove = !this.whitePlayerMove;
		
		return newState;
	}
	
	
	
	
}
