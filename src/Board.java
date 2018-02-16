
public interface Board {
	
	/*
	 * BOARDS ARE BY RULE ZERO INDEXED. THEY RECEIVE 1 INDEXED INSTRUCTIONS AND RETURN 1 INDEXED INTSRUCTIONS
	 */
	
	public Tile get(int x, int y);
	

	Board clone();
	
	/**
	 * Receives a coordinate in GAME SPACE and assigns a tile to it. 
	 * @param x X-coordinate in GAME SPACE
	 * @param y Y-coordinate in GAME SPACE
	 * @param tile the tyle type that is to be placed at specified location
	 */
	public void put(int x, int y, Tile tile);
	/**
	 * Prints the board to the console.
	 */
	void print();

	/**
	 * Applies an action to the board.
	 * @param action the action to be applied. in Game space.
	 * @return the result of applying action to this board
	 */
	Board applyAction(Action action) ;
	
}
