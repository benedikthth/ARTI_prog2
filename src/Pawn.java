
public class Pawn {
	public int x, y;
	
	
	public Pawn(int X, int Y) {
		this.x = X;
		this.y = Y;
	}
	
	public Pawn clone() {
		return new Pawn(this.x, this.y);
	}
	
	
	@Override
	public int hashCode() {
		return 11 * this.x + 191 * this.y;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(other != null && other instanceof Pawn) {
			
			if( x != ((Pawn) other).x) { return false; }
			if( y != ((Pawn) other).y) { return false; }
			
			return true;
			
		} else {
			return false;
		}
	}
	
	
}

