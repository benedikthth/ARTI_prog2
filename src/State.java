import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.Height;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

public class State {
	
	boolean whitePlayerMove;
	
	Environment environment;
	
	// to reference 1D as 2D: i = row * row + col
	
	public BitSet[] board;
	public BitSet whites;
	public BitSet blacks;
	int len;
	
	public State() {
		
		this.environment  = Environment.GetInstance();
		
		whites = new BitSet(len);
		blacks = new BitSet(len);
		this.board = new BitSet[2];
		board[0] = whites;
		board[1] = blacks;
		len = environment.width * environment.height;
			
		for(int i = 0; i < 2; i++) {	
			for(int j = 0; j < len; j++) {
				if(i == 0 && j < environment.width * 2 ) {
					this.board[i].set(j);
				} else if (i == 1 && j >= (len) - (2*environment.width)){
					this.board[i].set(j);
				}
			}				
		}
		
		StringBuilder w = new StringBuilder();
		StringBuilder b = new StringBuilder();

		for(int i = 0; i < board.length; i++) {	
	        for(int j = 0; j < len;  j++ ) {
	        	if(i == 0) {
	        		w.append( board[i].get(j) == true ? 1: 0 ); 
	        	}
	        	else {
	        		b.append( board[i].get(j) == true ? 1: 0 ); 
	        	}
	        }
		}
	       		   
        System.out.println( "White pawns: {" + w + "}" );
        System.out.println( "Black pawns: {" + b + "}" );
		//System.out.println(this.board);
		
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
