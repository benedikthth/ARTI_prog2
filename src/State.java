import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.Height;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

public class State {
	
	boolean whitePlayerMove;
	
	Environment environment;
	int WHITE = 0, BLACK=1;
	
	public BitSet[][] board;
	
	

	
	public State() {
		
		this.environment  = Environment.GetInstance();
		
		
		this.board = new BitSet[environment.width][environment.height];
		
		for(int x = 0; x < environment.width; x++) {
			
			for(int y = 0; y < environment.height; y ++) {
			
				this.board[x][y] = new BitSet(2);
				
				if(y == 0 || y == 1 ) {
					this.board[x][y].set(WHITE, true);
				} else if (y == environment.height-1 || y == environment.height-2){
					this.board[x][y].set(BLACK, true);
				}
				
			}
			
		}
		
		System.out.println(this.board);
		
	}
	
	

	public State clone() {
		
		// return a new State with the same properties as this one
		return null;
		
	}

	public List<String> legalActions(){
				
		List<String> rlist = new ArrayList<String>();
		
		return null;
	}
	
	public State ApplyAction( String action ) {
			
		State newState = this.clone();
	
		newState.whitePlayerMove = !this.whitePlayerMove;
		
		return newState;
	}
	
	
	
	
}
