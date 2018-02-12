import java.util.BitSet;

public class BitsetBoard implements Board {
	
	private Environment env;
	
	private BitSet[][] board;
	
	int whiteIndex = 0,
		blackIndex = 1;
	
	public BitsetBoard() {
		env  = Environment.GetInstance();
		board = new BitSet[env.width][env.height];
		
		
		for(int x = 0; x < env.width; x++) {
			for(int y = 0; y < env.height; y++) {
				
				//initialize a bitset with two bits set by default to false.
				board[x][y] = new BitSet(2);
				
				if(y == 0 || y == 1) {
					//set a white
					board[x][y].set(whiteIndex, true);
				} else if(y == env.height-1 || y == env.height - 2) {
					//set a black
					board[x][y].set(blackIndex, true);
				} else {
					//set a blank
					//dont need to. 
				}
				
			}
		}
			
	}
	
	public BitsetBoard(BitSet[][] b) {
		board = b;
	}
	
	@Override
	public Tile get(int x, int y) {
		
		if(board[x][y].get(whiteIndex)) {
			return Tile.WHITE;
		} else if (board[x][y].get(blackIndex)) {
			return Tile.BLACK;
		} else {
			return Tile.EMPTY;
		}
		
	}

	@Override
	public Board applyAction(Action action) {
		
		
		
		//todo: if get(action.originx, action.originy) empty throw;
		
		Tile t = get(action.originX, action.originY);
		
		if(t == Tile.EMPTY) {
			System.out.println("Illegal Move: " + action + " tried to move an empty dude");
			return null;
		}
		
		BitsetBoard newBoard = new BitsetBoard(this.board.clone());
		
		//remove piece in original position
		newBoard.put(action.originX, action.originY, Tile.EMPTY);
		//add piece to new board in new position.
		newBoard.put(action.destinationX, action.destinationY, t);
		
		// TODO Auto-generated method stub
		return newBoard;
	}
	
	
	private void setWhite(int x, int y) {
		board[x][y].set(whiteIndex, true);
		board[x][y].set(blackIndex, false);
	}
	private void setBlack(int x, int y) {
		board[x][y].set(whiteIndex, false);
		board[x][y].set(blackIndex, true);
	}
	private void setEmpty(int x, int y) {
		board[x][y].clear();
	}
	
	
	
	@Override
	public Board clone() {
		// TODO Auto-generated method stub
		
		BitsetBoard newBoard = new BitsetBoard(this.board.clone());
		
		return newBoard;
	
	}

	@Override
	public void put(int x, int y, Tile tile) {
		
		switch(tile) {
		case BLACK:
			setBlack(x, y);
			break;
		case EMPTY:
			setEmpty(x, y);
			break;
		case WHITE:
			setWhite(x, y);
			break;
		default:
			break;
		
		}

	}

	@Override
	public void print() {
		
		
		
		
		for(int y = env.height-1; y >= 0; y--) {

			
			for(int x = 0; x < env.width; x++) {
				
				switch(get(x, y)) {
				
				case BLACK:
					System.out.print("[#]");
					break;
				case EMPTY:
					System.out.print("[ ]");
					break;
				case WHITE:
					System.out.print("[I]");
					break;
				default:

					System.out.println("TERMINAL FAULT");
					break;
				
				}
			}
			
			System.out.println("|");
		}
	}


}
