import java.util.ArrayList;
import java.util.List;



public class State {
	
	//boolean whitePlayerMove;
	
	Environment environment  = Environment.GetInstance();;

	//STATES ARE 1 INDEXED

	public Board board;
	/*
	List<Action> legalActions;
	
	int len;
	*/
	public State() {	
		this.board = new BitsetBoard();	
	}
	
	public State(Board cboard) {
		this.board = cboard;//.clone();
	}

	public State clone() {
		
		// return a new State with the same properties as this one
		
		State newState = new State(this.board.clone());
		
		return newState;
		

	}

	
	public int getScore(Tile player) {
		
		int numberOfPawnsForPlayer = 0; 
		int numberOfPawnsForOpponent = 0;
		
		
		//the black wants to go down. white up.
		int furthestPlayerPawnPosition = (player == Tile.WHITE) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int furthestOpponentPawnPosition = (player == Tile.WHITE) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		
		
		for(int y = 1; y <= environment.height; y++) {
			for(int x = 1; x <= environment.width; x++) {
				
				Tile t = board.get(x, y);
				
				if(t == Tile.EMPTY) {
					continue;
				}
				
				if(t == player) {
					
					numberOfPawnsForPlayer ++;
					
					if(player == Tile.WHITE) {
						furthestPlayerPawnPosition = Math.max(furthestPlayerPawnPosition, y);
					} else {
						furthestPlayerPawnPosition = Math.min(furthestPlayerPawnPosition, y);
					}
					
				} else {
					// tile belongs to opponent.
					
					numberOfPawnsForOpponent ++;
					
					if(player == Tile.WHITE) {
						furthestOpponentPawnPosition = Math.min(furthestOpponentPawnPosition, y);
					} else {
						furthestOpponentPawnPosition = Math.max(furthestOpponentPawnPosition, y);
					}
					
				}
				
			}
		}
		
		//inverse the distance.
		if(player == Tile.BLACK) {
			furthestPlayerPawnPosition = environment.height +1 - furthestPlayerPawnPosition;
		} else {
			furthestOpponentPawnPosition = environment.height + 1 - furthestOpponentPawnPosition;
		}
		
		return /*numberOfPawnsForPlayer +*/ furthestPlayerPawnPosition;
	
	};
	
	
	public List<Action> legalMoves(Tile player) {
		
		if(player == Tile.EMPTY) {
			System.out.println("Terminal failure. Received Empty player in legal moves");
			return null;
		}
		
		Tile opponent = (player == Tile.WHITE)? Tile.BLACK: Tile.WHITE;
		
		List<Action> legalActions = new ArrayList<Action>();
		
		if(terminalState()) {
			System.out.println("Fetching moves for terminalState. BAD");
			return legalActions;
		}
		
		for(int y = 1; y <= environment.height; y++) {
			for(int x = 1; x <= environment.width; x++) {
				
				
				if(board.get(x, y) != player) {
					//no possible moves.
					continue;
				}
				
				
				// x, y = location of a pawn.
				
				
				//white goes up. black goes down.
				int direction = (player == Tile.WHITE)? 1: -1;
				
				
				
				//check for diagonal left.
				//There must be a tile of the type opponent 
				if(x != 1) {
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
				if(x != environment.width) {
					if(board.get(x+1, y+direction) == opponent) {
						legalActions.add(new Action(x, y, x+1, y+direction)) ;
					}
				}
				
			}
		}
		
		return legalActions;
	}
	
	public State ApplyAction( Action action ) throws IllegalMoveException {
			
		State newState = this.clone();
		
		
			
		newState.board.applyAction(action);
			
		
		return newState;
	}
	
	//If either B or W has made it's way accross the board, that state is terminal. 
	public boolean terminalState() {
		
		for(int i = 1; i <= environment.width; i++) {
			if(board.get(i, 1) == Tile.BLACK || board.get(i, environment.height) == Tile.WHITE) {
				System.out.println("State is terminal: ");
				this.print();
				System.out.println("0-0-0-0-0-0-0-0-0-0-0-0-0-0-0");
				return true;
			}
			
			
		}
		//board.print();
		
		return false;
		
	}
	
	
	public void print() {
		this.board.print();
	}
	
	/*
	 * this is already done in statescore(player)'
	// All values should be from MAX's point of view
	public int eval() {
		// num pieces of each type & num possible moves & num squares controlled
		return 0;
	}
	
	public boolean terminalState() {
		//return this.legalActions.isEmpty();
		
		
		
	}

	*/
	
}
