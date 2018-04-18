//David Snyder a247a342 cs771 prog2

//Uses MinConflicts algorithm to find a solution for n-queens problem
public class MinConflictsSearch {
	public static final int MAX_STEPS = 9999;
	
	private int stepsTaken;
	private BoardState startState;
	
	public MinConflictsSearch(BoardState startingState){
		startState = startingState;
		stepsTaken = -1;
	}
	
	
	public BoardState findMinConflictsSolution(){
		BoardState currentState = startState;
		stepsTaken = 0;
		while (stepsTaken < MAX_STEPS){
			
			//return curent state if it is a solution
			if (currentState.containsNoConflicts())
				return currentState;
			
			//a randomly chosen conflicted column
			int columnVariable = currentState.getRandomQueenInConflict();
			
			//next state = the state with a new value (by moving the queen within the column) that most minimizes conflicts
			BoardState nextState = currentState.successorStateWithLeastConflictsForQueen(columnVariable);
			currentState = nextState;			
			
			stepsTaken++;
		}		
		
		return null;	//return failure if exceeded MAX_STEPS without finding a solution
	}
	
	public int numStepsTaken(){return stepsTaken;}
	
}
