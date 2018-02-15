import java.util.List;
import java.util.Random;

public class RandomAgent implements Agent
{
	private Random random = new Random();

	private String role; // the name of this agent's role (white or black)
	private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
	private boolean myTurn; // whether it is this agent's turn or not
	private int width, height; // dimensions of the board
	
	
	//my own shit
	private State state;
	
	
	/*
		init(String role, int playclock) is called once before you have to select the first action. Use it to initialize the agent. role is either "white" or "black" and playclock is the number of seconds after which nextAction must return.
	*/
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
		
		state = new State();
		
		//List<Action> something = state.legalMoves(Tile.WHITE);
		
		//System.out.println(something);
		
    }

	// lastMove is null the first time nextAction gets called (in the initial state)
    // otherwise it contains the coordinates x1,y1,x2,y2 of the move that the last player did
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
   			
   			
   			try {
				state = state.ApplyAction(new Action(x1, y1, x2, y2));
			} catch (IllegalMoveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   			
   			state.board.print();
   			
    		System.out.println(state.getScore(Tile.BLACK));
    	}
		
    	// update turn (above that line it myTurn is still for the previous state)
		myTurn = !myTurn;
		if (myTurn) {
			
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

	// is called when the game is over or the match is aborted
	@Override
	public void cleanup() {
		// TODO: cleanup so that the agent is ready for the next match
	}

}
