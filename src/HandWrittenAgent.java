import java.util.List;
import java.util.Random;

public class HandWrittenAgent implements Agent {

	Environment environment = Environment.GetInstance();
	
	private Random random = new Random();

	private String role; // the name of this agent's role (white or black)
	private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
	private boolean myTurn; // whether it is this agent's turn or not
	private int width, height; // dimensions of the board
	int bestVal;
	
	
	//my own shit
	private State state;
	
	@Override
	public void init(String role, int width, int height, int playclock) {
		this.role = role;
		this.playclock = playclock;
		myTurn = !role.equals("white");
		this.width = width;
		this.height = height;
		// TODO: add your own initialization code here
		
		Environment environment = Environment.GetInstance();
		environment.height = this.height;
		environment.width = this.width;
		
		
		//populate initial state.
		state = new State();
		
		
		
    }

	public String nextAction(int[] lastMove) {
    	   	
    	if (lastMove != null) {
    		int x1 = lastMove[0], y1 = lastMove[1], x2 = lastMove[2], y2 = lastMove[3];
    		String roleOfLastPlayer;
    		if (myTurn && role.equals("white") || !myTurn && role.equals("black")) {
    			roleOfLastPlayer = "white";
    		} else {
    			roleOfLastPlayer = "black";
    		}
   			System.out.println(roleOfLastPlayer + " moved from " + x1 + "," + y1 + " to " + x2 + "," + y2);
   			
   			state = state.ApplyAction(new Action(x1, y1, x2, y2));
   			
   			state.board.print();
   			
    		System.out.println(state.getScore(Tile.BLACK));
    	}
		
    	// update turn (above that line it myTurn is still for the previous state)
		myTurn = !myTurn;
		if (myTurn) {
			// have 2 different AB searches: one returns a move and the other returns a score
			/*
			Action bestMove;
			try {
				depth++;
				bestMove = alphaBeta(..., depth);
			} catch(TimeoutException) {
				return "Action";
			}
			*/
			
			// TODO: 2. run alpha-beta search to determine the best move
	
			List<Action> lms;
			
			lms = (role.equals("white")) ?  state.legalMoves(Tile.WHITE): state.legalMoves(Tile.BLACK);
			
			int randex = (int)Math.floor( ( double) lms.size() * Math.random() );
			
			return lms.get(randex).toString();
			
			//return "( move 4 3 4 4)";
			
		} else {
			
			return "noop";
		
		}
	}
	/* Returns best value
	// Call: minimaxValue = miniMax(initialState, MaxPlayer)
	int miniMax( State s, Tile p) {
		if(s.terminalState()) {
			return s.getScore(p);
		}
		
		List<Action> lms;
		lms = (role.equals("white")) ?  state.legalMoves(Tile.WHITE): state.legalMoves(Tile.BLACK);
		List<State> successors;
		for(int i = 0; i < lms.length; i++) {
			successors.add(ApplyAction(lms[i]);
		}
		
		
		if(p == MaxPlayer) {	// MAXplayer wants to maximize his score
			bestVal = -100000;
			for(State su : successors) {
				value = miniMax(su, MinPlayer);
			    bestVal = max(value, bestVal);
			}
		}
		else {					// MINplayer wants to minimize MAX's score
			bestVal = 100000;
			for(all successors su of s) {
				value = miniMax(su, MaxPlayer);
			    bestVal = min(value, bestVal);
			}
		}
		return bestVal;
	}
	*/
	/*// returns best action
	// Call: minimaxAction = miniMax(initialState, MaxPlayer)
		Action miniMax( State s, Tile p) {
			if(s.terminalState()) {
				return s.getScore(p);
			}
			
			if(p == MaxPlayer) {	// MAXplayer wants to maximize his score
				bestVal = -100000;
				for(all successors su of s) {
					value = miniMax(su, MinPlayer);
				    bestVal = max(value, bestVal);
				}
			}
			else {					// MINplayer wants to minimize MAX's score
				bestVal = 100000;
				for(all successors su of s) {
					value = miniMax(su, MaxPlayer);
				    bestVal = min(value, bestVal);
				}
			}
			return bestVal;
		}
	*/

	// is called when the game is over or the match is aborted
	@Override
	public void cleanup() {
		// TODO: cleanup so that the agent is ready for the next match
	}

}
