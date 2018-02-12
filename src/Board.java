
public interface Board {
	
	
	
	public Tile get(int x, int y);
	

	Board clone();
	
	void put(int x, int y, Tile tile);
	
	void print();

	Board applyAction(Action action);
	
}
