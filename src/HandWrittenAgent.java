import java.util.Random;

public class HandWrittenAgent implements Agent {

	Environment environment = Environment.GetInstance();
	
	private Random random = new Random();

	private String role; // the name of this agent's role (white or black)
	private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
	private boolean myTurn; // whether it is this agent's turn or not
	private int width, height; // dimensions of the board
	//int bestVal;
	Tile myRole;
	
	Search adversarySearch = new Search();
	
	//my own shit
	private State state;
	
	@Override
	public void init(String role, int width, int height, int playclock) {
		this.role = role;
		this.playclock = playclock;
		myTurn = !role.equals("white");
		myRole = myTurn? Tile.BLACK: Tile.WHITE;
	
		this.width = width;
		this.height = height;
		// TODO: add your own initialization code here
		
		Environment environment = Environment.GetInstance();
		environment.height = this.height;
		environment.width = this.width;

		Tile opponent = (myRole == Tile.WHITE)? Tile.BLACK: Tile.WHITE;
		adversarySearch.setUp(myRole, opponent);
		
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
   			
   			//update the opponents move to our state.
   			if(!myTurn) { 
   				
				state = state.ApplyAction(new Action(x1, y1, x2, y2));
				
   			}
   		
   			
    		//System.out.println(state.getScore(Tile.BLACK));
    	}
		
    	// update turn (above that line it myTurn is still for the previous state)
		myTurn = !myTurn;
		if (myTurn) {
			
			
			// TODO: 2. run alpha-beta search to determine the best move
			/*
			List<Action> lms;
			
			lms = (role.equals("white")) ?  state.legalMoves(Tile.WHITE): state.legalMoves(Tile.BLACK);
			
			int randex = (int)Math.floor( ( double) lms.size() * Math.random() );
			
			state = state.ApplyAction(lms.get(randex));
			return lms.get(randex).toString();
			
			//return "( move 4 3 4 4)";
			*/
			
			
			//Action a = adversarySearch.MinMaxSearch(state.clone(), 10, true, true);
			Action a = adversarySearch.abSearch(state, 15);
			
			State s = state.ApplyAction(a);
			
			this.state = s;
			
			this.state.print();
			System.out.println("SC:: " + state.getScore(myRole)) ;
			System.out.println("FW: "+this.state.furthestWhitePosition + ", FB: "+ this.state.furthestBlackPosition
								+ ", PlayerProtection: " + this.state.playerProtection 
								+ ", OpponentProtection: " + this.state.opponentProtection 
								+ ", PlayerWin: " + this.state.playerWin);
			
			
			return a.toString();
			
		} else {
			
			return "noop";
		
		}
	}
	
	


	// is called when the game is over or the match is aborted
	@Override
	public void cleanup() {
		// TODO: cleanup so that the agent is ready for the next match
	}

}
