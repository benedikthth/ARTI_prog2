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
			
		
		
		return bestAction;
		
	};
	
	private int Minimax(State state, int depth, boolean maximizing, boolean playerTurn) {
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
		
			for(Action a : legalMoves) {
				
				if(maximizing) {
					bestScore = Math.max(bestScore, Minimax( state.ApplyAction(a), depth-1, !maximizing, !playerTurn ));
				} 
				else {
					bestScore = Math.min(bestScore, Minimax( state.ApplyAction(a), depth-1, !maximizing, !playerTurn ));
				}
				
				
			}
		
		
		return bestScore;
		
	}
	
	
	/**
	 * Alpha-Beta pruning. Tries to maximize available moves for player.
	 * @param initialState Initial state to begin the ab search from.
	 * @param depth This is the depth to which to explore the tree.
	 * @return An action representing (hopefully) the best possible action
	 */
	public Action abSearch(State initialState, int depth, int playClock) {
		//this really shouldn't ever be called for the opponent. it just does not make sense.
		if(initialState.terminalState()) {
			System.out.println("Search called on a terminal state.");
			return null;
		}
		
		// millisecs = seconds * 1000
		double TimeLeft = (double) playClock * 1000;
		
		/**
		 * This is the alpha value passed into the recursive helper function
		 */
		int alpha = Integer.MIN_VALUE;
		/**
		 * This is the beta value.
		 */
		int beta = Integer.MAX_VALUE;
		
		
		/**
		 * The legal moves for the agent.
		 */
		List<Action> actions = initialState.legalMoves(player);
		
		/**
		 * this is the value we use to check if our
		 * recursive helper function has found a better move
		 */
		/**
		 * Store the best action here ... eventually
		 */
		Action bestAction = null;
		
		double margin = 50;
		double equalSearch = (TimeLeft-margin) / (double)actions.size();
		
		
		for(Action a : actions) {
			//call our recursive helper.

			int score = abSearch(initialState.ApplyAction(a), depth, alpha, beta, false, false, equalSearch);
			
			//System.out.println("equalSearch: "+equalSearch + " time: " + (System.currentTimeMillis()-time) );
			
			if(score > alpha) {
				alpha = score;
				bestAction = a;
			}
			
			
		}
		
		return bestAction;
		
		
	};
	
	private int abSearch(State state, int depth, int alpha, int beta, boolean maximizingplayer, boolean playerTurn, double timeLeft) {
		
		/**
		 * check if our depth is reached or if the current node is terminal;
		 */
		if(depth == 0 || state.terminalState()) {
			return state.getScore(player);
		}
		
		/**
		 * Get appropriate legal moves for the current player.
		 */
		List<Action> legalMoves = (playerTurn)? state.legalMoves(player): state.legalMoves(opponent);
		
		int value = (maximizingplayer)? Integer.MIN_VALUE: Integer.MAX_VALUE;
		
		double equalSearch = timeLeft / (double) legalMoves.size();
		
		if(maximizingplayer) {
			
			for(Action a : legalMoves) {
				
				if(timeLeft < equalSearch) {
					break;
				}
				
				double cTime = System.currentTimeMillis();
				
				value = Math.max(value,
						abSearch(state.ApplyAction(a),
								depth-1, alpha,
								beta, !maximizingplayer, !playerTurn,
								equalSearch));
				
				timeLeft -= (System.currentTimeMillis() - cTime);
				
				alpha = Math.max(alpha, value);
				
				if(beta <= alpha ) {
					break;
				}	
			}
			
		} else {
			
			for(Action a : legalMoves) {
				
				if(timeLeft < equalSearch) {
					break;
				}
				
				double cTime = System.currentTimeMillis();
				
				value = Math.min(value, 
						abSearch(state.ApplyAction(a), depth-1,
								alpha, beta, !maximizingplayer, !playerTurn,
								equalSearch));
				
				timeLeft -= (System.currentTimeMillis() - cTime);
				
				beta = Math.min(beta, value);
				
				if(beta <= alpha ) {
					break;
				}
				
			}
			
			
		}
		
		return value;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
