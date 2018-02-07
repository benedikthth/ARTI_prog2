
public class Vector2d {
	public int x, y;
	
	public Vector2d(int x, int y) {
		this.x = x;
		this.y = y;
	
	}

	public static Vector2d[] whiteMoves = {
		new Vector2d(-1, 1),
		new Vector2d(0, 1),
		new Vector2d(1, 1)
	};
	
	public static Vector2d[] blackMoves = {
			new Vector2d(-1, -1),
			new Vector2d(0, -1),
			new Vector2d(1, -1)
	};
	
}
