import java.util.ArrayList;
import java.util.List;

public class State {
	
	boolean whitePlayerMove;
	
	Environment environment = Environment.GetInstance();
	
	List<Pawn> whitePawns, blackPawns;
	
	public State() {
		
		whitePlayerMove = true;
			
		whitePawns = new ArrayList<Pawn>();
		
		for(int i = 0; i < environment.width; i++) {
			whitePawns.add(new Pawn(i+1, 1));
			whitePawns.add(new Pawn(i+1, 2));
		}
		
		blackPawns = new ArrayList<Pawn>();
		
		for(int i = 0; i < environment.width; i++) {
			whitePawns.add(new Pawn(i+1, environment.height));
			whitePawns.add(new Pawn(i+1, environment.height-1));
		}
		
		
		
	}
	
	public State(List<Pawn> whitePawns2, List<Pawn> blackPawns2) {
		// TODO Auto-generated constructor stub
		whitePawns = new ArrayList<Pawn>();
		blackPawns = new ArrayList<Pawn>();
		
		for(Pawn wp : whitePawns2) {
			this.whitePawns.add(wp.clone());
		}
		for(Pawn bp: blackPawns2) {
			this.blackPawns.add(bp.clone());
		}
		
	}

	public State clone() {
		
		// return a new State with the same properties as this one
		
		State state = new State(whitePawns, blackPawns);
		
		return state;
		
	}

	public State ApplyAction() {
			
		State newState = this.clone();
	
		this.whitePlayerMove = !this.whitePlayerMove;
		
		return null;
	}
	
	
	
	
}
