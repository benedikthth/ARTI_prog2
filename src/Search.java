import java.util.List;

public class Search {
	
	
	// returns a { score, action } my dude. 
	
	Tile player, opponent;
	
	public void setUp(Tile player, Tile opponent) {
		this.player = player;
		this.opponent = opponent;
	};
	
	
	public Action MinMaxSearch(State state, int depth, boolean maximizing, boolean playerTurn) {

		List<Action> legalMoves;
		
		if(playerTurn) {
			legalMoves = state.legalMoves(player);
		} else {
			legalMoves = state.legalMoves(opponent);
		}
		
		Action bestAction = null;
		
		try {
			
			if(maximizing) {
				
				// select the best score available.
				
				int bestScore = Integer.MIN_VALUE;
				
				for(Action a : legalMoves) {
					
					int potential = Minimax(state.ApplyAction(a), depth, !maximizing, !playerTurn);
					
					if(potential > bestScore) {
						potential = bestScore;
						bestAction = a;
					}
					
				}
				
			} else {
				
				//Select the minimum score possible
				
				int bestScore = Integer.MAX_VALUE;
				
				for(Action a : legalMoves) {
					
					int potential = Minimax(state.ApplyAction(a), depth, !maximizing, !playerTurn);
					
					if(potential < bestScore) {
						potential = bestScore;
						bestAction = a;
					}
					
				}
				
			}
			
		} catch (IllegalMoveException ex) {
			ex.printStackTrace();
		}
		
		return bestAction;
		
	};
	
	public int Minimax(State state, int depth, boolean maximizing, boolean playerTurn) {
		//do not try to search further into a terminal state. 
		if(state.terminalState() ) {
			return state.getScore(player);
		}
		//do not go deeper than neccesary.
		if(depth == 0) {
			return state.getScore(player);
		}
		//the list of eligible moves. 
		List<Action> legalMoves;
		//get the moves for the appropriate player.
		if(playerTurn) {
			legalMoves = state.legalMoves(player);
		} else {
			legalMoves = state.legalMoves(opponent);
		}
		// Best score is -inf if it is to be maxed. and inf it is to be mini-ed
		int bestScore = (maximizing)? Integer.MIN_VALUE: Integer.MAX_VALUE;
		
		//this will throw an illegalmove exception if the actions accesses an empty tile.
		try {
			for(Action a : legalMoves) {
				
				if(maximizing) {
					bestScore = Math.max(bestScore, Minimax( state.ApplyAction(a), depth-1, !maximizing, !playerTurn ));
				} 
				else {
					bestScore = Math.min(bestScore, Minimax( state.ApplyAction(a), depth-1, !maximizing, !playerTurn ));
				}
				
				
			}
		} catch (IllegalMoveException ex) {
			ex.printStackTrace();
		}
		
		return bestScore;
		
	}
	
}
