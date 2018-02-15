//import java.util.List;
//
//public class MinMax implements Search {
//	
//	Tile player, opponent;
//	
//	@Override
//	public Action search(State state, int depth, boolean maximizing) {
//		
//		
//		if(state.terminalState()) {
//			System.out.println("TERMINAL STATE SEARCH!");
//			return null;
//		}
//		
//		//we only want to evaluate moves belonging to the current player.
//		List<Action> moves = getActions(maximizing, state);
//		
//		/*
//		System.out.println(moves);
//		System.out.println(s);
//		*/
//		
//		if(maximizing) {
//			int score = Integer.MIN_VALUE;
//			Action bestAction = null;
//			
//			for(Action a : moves) {
//				
//				int nextScore = -0;
//				
//				
//				try {
//					nextScore = intMax(state.ApplyAction(a).clone(), depth, !maximizing);
//				} catch (IllegalMoveException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				if( score < nextScore ) {
//					score = nextScore;
//					bestAction = a;
//				}
//			}
//			
//			return bestAction;
//			
//		} else {
//			int score = Integer.MAX_VALUE;
//			Action bestAction = null;
//			
//			for(Action a : moves) {
//				
//				int nextScore = -0;
//				
//				try {
//					nextScore = intMax(state.ApplyAction(a).clone(), depth, !maximizing);
//				} catch (IllegalMoveException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				if( score > nextScore ) {
//					score = nextScore;
//					bestAction = a;
//				}
//			}
//			return bestAction;
//			
//		}
//		
//		
//	}
//	
//	
//	
//	private List<Action> getActions(boolean maximizing, State state){
//		
//		return (!maximizing)? state.legalMoves(opponent): state.legalMoves(player);
//		
//	}
//	
//	
//	
//	private int intMax(State s, int depth, boolean maximizing ) {
//		
//		if( depth == 0 ) {
//			
//			return s.getScore(player);
//		} 
//		if( s.terminalState() ) {
//			return s.getScore(player);
//		}
//		
//		List<Action> moves = getActions(maximizing, s);
//		
//		
//		if(maximizing) {
//			
//			int bestValue = Integer.MIN_VALUE;
//			
//			for(Action a : moves) {
//				
//				try {
//					bestValue = Math.max(bestValue, intMax(s.ApplyAction(a).clone(), depth-1, false ));
//				} catch (IllegalMoveException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//			return bestValue;
//			
//		} else {
//
//			int bestValue = Integer.MAX_VALUE;
//			
//			for(Action a : moves) {
//				
//				try {
//					bestValue = Math.min(bestValue, intMax(s.ApplyAction(a).clone(), depth-1, true ));
//				} catch (IllegalMoveException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//			return bestValue;
//			
//		}
//		
//		
//	}
//
//	@Override
//	public void setUp(Tile player, Tile opponent) {
//		
//		this.player = player;
//		this.opponent = opponent;
//		
//	}
//	
//	
//}
