import java.util.Random;


public class MinConflictsSearch {
	public static final int MAX_STEPS = 99999;
	private static final Random random = new Random();
	
	private int stepsTaken = -1;
	private BoardState startState;
	
	public MinConflictsSearch(BoardState startingState){
		startState = startingState;
		stepsTaken = -1;
	}
	
	
	public BoardState findMinConflictsSolution(){
		BoardState currentState = startState;
		stepsTaken = 1;
		while (stepsTaken < MAX_STEPS){
			
			//return curent state if it is a solution
			if (currentState.containsNoConflicts())
				return currentState;
			
			//a randomly chosen conflicted column
			int columnVariable = currentState.getRandomQueenInConflict();
			
			//next state = the state with a new value that minimizes conflicts for the column columnVariable
			BoardState nextState = currentState.successorStateWithLeastConflictsForQueen(columnVariable);
			currentState = nextState;			
			
			stepsTaken++;
		}		
		
		return null;	//return failure if exceeded MAX_STEPS without finding a solution
	}
	
	public int numStepsTaken(){return stepsTaken;}
	
}
