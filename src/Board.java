
public interface Board {
	
	/*
	 * BOARDS ARE BY RULE ZERO INDEXED. THEY RECEIVE 1 INDEXED INSTRUCTIONS AND RETURN 1 INDEXED INTSRUCTIONS
	 */
	
	public Tile get(int x, int y);
	

	Board clone();
	
	void put(int x, int y, Tile tile);
	
	void print();

	Board applyAction(Action action);
	
}
