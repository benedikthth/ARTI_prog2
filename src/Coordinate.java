import com.sun.javafx.geom.Vec2d;

public class Coordinate {
	
	public int x, y;
	
	//
	public Vector2d[] whiteMoves = {
			new Vector2d(-1, 1),
			new Vector2d(0, 1),
			new Vector2d(1, 1)
	};
	
	
	
	public Coordinate(int X, int Y) {
		this.x = X;
		this.y = Y;
	}
	
	public Coordinate clone() {
		return new Coordinate(this.x, this.y);
	}
	
	
	@Override
	public int hashCode() {
		return 11 * this.x + 191 * this.y;
	}
	
	
	
	public Coordinate translate(Vector2d vector){
		return new Coordinate(this.x - vector.x, this.y -vector.y);
	}
	
	
	
	@Override
	public boolean equals(Object other) {
		
		if(other != null && other instanceof Coordinate) {
			
			if( x != ((Coordinate) other).x) { return false; }
			if( y != ((Coordinate) other).y) { return false; }
			
			return true;
			
		} else {
			return false;
		}
	}
	
	
}

