import java.util.ArrayList;
import java.util.List;

public class State {
	
	Environment environment  = Environment.GetInstance();;

	//STATES ARE 1 INDEXED

	public Board board;

	public State() {	
		this.board = new BitsetBoard();	
	}
	
	public State(Board cboard) {
		this.board = cboard.clone();
	}
	
	public int furthestWhitePosition, furthestBlackPosition;
	public int playerProtection;
	public int opponentProtection;
	
	public int getScore(Tile player) {
		
		int numberOfPawnsForPlayer = 0; 
		int numberOfPawnsForOpponent = 0;
		playerProtection = 0;
		opponentProtection = 0;		
		
		//the black wants to go down. white up.
		int furthestPlayerPawnPosition = (player == Tile.WHITE) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int furthestOpponentPawnPosition = (player == Tile.WHITE) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		
		// check for winning state
		if(terminalState()) {
			return checkWin(player);
		}
		
		/**
		 * Initial values.
		 */
		int fblack = Integer.MAX_VALUE, fwhite=Integer.MIN_VALUE;
		
		for(int y = 1; y <= environment.height; y++) {
			for(int x = 1; x <= environment.width; x++) {
				
				Tile t = board.get(x, y);
										
				if(t == Tile.EMPTY) {
					continue;
				}
				
				// check for number of protected pawns
				checkProtection(t, player, x, y);
				
				if(t==Tile.BLACK) {
					fblack = Math.min(fblack, y);
					//get attacks on black.				
				} else {
					fwhite = Math.max(fwhite, y);
				}
				
				if(t == player) {				
					numberOfPawnsForPlayer++;					
				} else {
					// tile belongs to opponent.				
					numberOfPawnsForOpponent++;				
				}				
			}
		}
		
		this.furthestBlackPosition = environment.height +1 - fblack;
		this.furthestWhitePosition = fwhite;
				
		//inverse the distance.
		if(player == Tile.BLACK) {
				
			furthestOpponentPawnPosition = fwhite;
			furthestPlayerPawnPosition = environment.height +1 - fblack;
			
		} else {
			
			furthestOpponentPawnPosition = environment.height +1 - fblack;
			furthestPlayerPawnPosition = fwhite;
		}
				
		return furthestPlayerPawnPosition - furthestOpponentPawnPosition 
				+ playerProtection - opponentProtection;
	
	};
	
	
	private void checkProtection(Tile t, Tile player, int x, int y) {
		//***************** Checking protected BLACK pawns ******************//
		if(t == Tile.BLACK) {
			if(x < environment.width && y < environment.height) {
				if(board.get(x+1, y+1) == Tile.BLACK) {
					if(player == Tile.BLACK) {
						playerProtection++;
					}
					else {
						opponentProtection++;
					}
				}
			}
			if(x > 1 && y < environment.height) {
				if(board.get(x-1, y+1) == Tile.BLACK) {
					if(player == Tile.BLACK) {
						playerProtection++;
					}
					else {
						opponentProtection++;
					}
				}
			}
		//***************** Checking protected WHITE pawns ******************//
		} else if(t == Tile.WHITE){
			if(x > 1 && y > 1) {
				if(board.get(x-1, y-1) == Tile.WHITE) {
					if(player == Tile.WHITE) {
						playerProtection++;
					}
					else {
						opponentProtection++;
					}				
				}
			}
			if(x < environment.width && y > 1) {
				if(board.get(x+1, y-1) == Tile.WHITE) {
					if(player == Tile.WHITE) {
						playerProtection++;
					}
					else {
						opponentProtection++;
					}	
				}
			}
		}
		else return;
		
	}

	private int checkWin(Tile player) {
		
		boolean winForBlack =false , winForWhite = false;
		
		for(int i = 1; i <= environment.width; i++) {
			if(board.get(i, 1) == Tile.BLACK) {
				winForBlack = true;
				break;
			}
		}
		
		for(int t = 1; t <= environment.width; t++) {
			if(board.get(t, environment.height) == Tile.WHITE) {
				winForWhite = true;
				break;
			}
		}
		
		if(winForBlack && winForWhite) {
			System.out.println("Both players cannot win at the same time. (state.checkWin)");
			return -0;
		}
		// disadvantage in ties. 
		if(player == Tile.WHITE) {
			return (winForWhite)? 100: -100;
		} else {
			return (winForBlack)? 100: -100;
		}
			
	}

	public List<Action> legalMoves(Tile player) {
		
		if(player == Tile.EMPTY) {
			System.out.println("Terminal failure. Received Empty player in legal moves");
			return null;
		}
		
		Tile opponent = (player == Tile.WHITE)? Tile.BLACK: Tile.WHITE;
		
		List<Action> legalActions = new ArrayList<Action>();
			
		for(int y = 1; y <= environment.height; y++) {
			for(int x = 1; x <= environment.width; x++) {
				
				if(y == 1) {
					// if the bottom row contains a black player. the game is over. return empty list.
					if(board.get(x, y) == Tile.BLACK) {
						return new ArrayList<Action>();
					}
				} else if(y == environment.height) {
					//if the top row contains a white player. the game is over. return empty list.
					if(board.get(x, y) == Tile.WHITE){
						return new ArrayList<Action>();
					}
				}
				
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
	
	public State ApplyAction( Action action ) { // throws IllegalMoveException {
			
		State newState = this.clone();
		
		newState.board = newState.board.applyAction(action);
				
		return newState;
	}
	
	
	public State clone() {
		
		// return a new State with the same properties as this one
		State newState = new State(this.board);
		return newState;	

	}
	
	//If either B or W has made it's way accross the board, that state is terminal. 
	public boolean terminalState() {
		
		if(legalMoves(Tile.BLACK).isEmpty() ) {
			
			return true;
			
		}
		
		if(legalMoves(Tile.WHITE).isEmpty()) {
			//System.out.println("```````````.");
			//print();
			//System.out.println("The above state is terminal.");
			return true;
		}
		
		return false;
		
	}
	
	
	public void print() {
		this.board.print();
	}
		
}
