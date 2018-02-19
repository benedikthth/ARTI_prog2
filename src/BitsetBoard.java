import java.util.BitSet;


public class BitsetBoard implements Board {
	
	private Environment env = Environment.GetInstance();
	
	private BitSet[][] board;
	
	static int whiteIndex = 0;
	static int blackIndex = 1;
	
	public BitsetBoard() {
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
		
		
		board = new BitSet[env.width][env.height];
		
		for(int x = 0; x < env.width; x++) {
			for(int y = 0; y < env.height; y++) {
				
				board[x][y] = (BitSet) b[x][y].clone();// new BitSet(2);
				/*
				boolean white = b[x][y].get(whiteIndex);
				boolean black = b[x][y].get(blackIndex);
				
				board[x][y].set(whiteIndex, white);
				board[x][y].set(blackIndex, black);
				*/
			}
		}
		
		//board = b.clone();
	}
	
	@Override
	public Tile get(int x, int y) {
		
		x -= 1;
		y -= 1;
		
		try {
			if( board[x][y].get(whiteIndex) && !board[x][y].get(blackIndex)) {
				return Tile.WHITE;
			}
			else if ( board[x][y].get(blackIndex) && !board[x][y].get(whiteIndex) ) {
				return Tile.BLACK;
			}
			else if(!board[x][y].get(whiteIndex) && !board[x][y].get(blackIndex)) {
				return Tile.EMPTY;
			} else {
				System.out.println("TERMINAL FAILURE. BOTH BITS SET IN BITSETBOARD:GET");
				return null;
			}
		} catch(ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();

			return null;
		}
	}
	
	@Override
	public Board applyAction(Action action) { // throws IllegalMoveException {
		
		// actions are 1 indexed, this board is 0 indexed.
		int ox = action.originX -1;
		int oy = action.originY -1;
		int dx = action.destinationX -1;
		int dy = action.destinationY -1;
		
		//todo: if get(action.originx, action.originy) empty throw;
		
		Tile t = get(action.originX, action.originY);
		
		if(t == Tile.EMPTY) {
			
			
			System.out.println("Illegal Move: " + action + " for player tried to move an empty dude");
			
			print();
			System.out.println("-------------------------------------------");
			//throw new IllegalMoveException();
		
		}
		
		BitsetBoard newBoard = new BitsetBoard(this.board.clone());
		
		//remove piece in original position
		newBoard._put(ox, oy, Tile.EMPTY);
		//add piece to new board in new position.
		newBoard._put(dx, dy, t);
		
		/*print();
		System.out.println("Becomes:");
		newBoard.print();
		*/
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

	/**
	 * DO NOT USE THIS OUTSIDE THE BitSetBoard CLASS!!!!
	 * @param x X-coordinate in board space
	 * @param y Y-coordinate in board space
	 * @param Which tile should go into the board at that specified location.
	 */
	public void _put(int x, int y, Tile tile) {

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
	public void put(int x, int y, Tile tile) {
		
		x-=1;
		y-=1;
		
		_put(x, y, tile);

	}

	@Override
	public void print() {
			
		for(int y = env.height; y >= 1; y--) {
			
			for(int x = 1; x <= env.width; x++) {
				
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
