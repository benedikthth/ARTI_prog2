import java.util.List;

public class Environment {
	
	public static Environment instance;
	
	
	public int height, width;
	
	
	public List<String> AvailableActions(State state){
		return null;
	}
	
	public static Environment GetInstance() {
		if(instance == null) {
			instance = new Environment();
		}
		return instance;
	}

	public void setSize(int w, int h) {
		height = h;
		width = w;
	}
	
	
}
