
public class Action {
	
	int originX, originY;
	int destinationX, destinationY;
	
	public Action(int ox, int oy, int dx, int dy) {
		originX = ox;
		originY = oy;
		destinationX = dx;
		destinationY = dy;
	}
	
	public String toString() {
		return "(move "+ originX + " " + originY + " " + destinationX + " " + destinationY +")";
		
	}
	
}
