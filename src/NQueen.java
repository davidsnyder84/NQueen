//David Snyder a247a342 cs771 prog2

import java.util.Scanner;

//main program
public class NQueen {
	
	public static void main(String[] args) {
		System.out.println("\nn-Queens Solver\n");
		
		//decide starting state through user choice
		BoardState startState = menuChoice();
		if (startState == null){System.out.print("exiting program........."); return;}		
		System.out.println("\nStarting state:\n" + startState + "------------------");
		
		//do search
		MinConflictsSearch searcher = new MinConflictsSearch(startState);
		BoardState solution = searcher.findMinConflictsSolution();
		
		//show result
		showResult(solution, searcher.numStepsTaken());
		System.out.println("\nprogram end");
	}
	
	private static void showResult(BoardState solution, int stepsTaken){
		if (solution == null){
			System.out.println("xxxxxFAILURE. Search exceeded " + MinConflictsSearch.MAX_STEPS + " steps without finding a solution.");
			return;
		}
		
		System.out.println("\n~~~~~~~~~~SOLTUION FOUND:\n" + solution);
		System.out.println("Steps taken: " + stepsTaken);
	}
	
	
	
	
	
	
	//-----------user input methods
	private static BoardState menuChoice(){
		final int CHOICE_INPUT = 1, CHOICE_DEMO_4 = 2, CHOICE_DEMO_8 = 3, CHOICE_RANDOM_8 = 4;
		System.out.println(
				"===Choose an option===\n"+
				"  1. Input queens manually\n"+
				"  2. View demo (n=4 queens)\n"+
				"  3. View demo (n=8 queens)\n"+
				"  4. View random set of columns for (n=8 queens)\n"+
				"choice: ");
		@SuppressWarnings("resource")
		int choice = (new Scanner(System.in)).nextInt();
//		int choice = 4;
		
		switch(choice){
		case CHOICE_INPUT:
			return userInputBoard();
		case CHOICE_DEMO_4:
			return DemoBoards.getDemoStartState4Queens();
		case CHOICE_DEMO_8:
			return DemoBoards.getDemoStartState8Queens();
		case CHOICE_RANDOM_8:
			return DemoBoards.generateRandomInRows(8);
		default:
			return null;
		}
	}
	
	private static BoardState userInputBoard(){
		int numQueens = askForNumQueens();
		BoardState boardInput = askForQueenPositions(numQueens);
		return boardInput;
	}
	
	//user input to place one queen in each column
	private static BoardState askForQueenPositions(int numQueens){
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		
		BoardState boardState = new BoardState(numQueens);
		for (int currentColumn = 0; currentColumn < numQueens; currentColumn++){			
			System.out.printf("Enter the row position of the queen in column #%d: ", currentColumn);
			int rowValue = keyboard.nextInt();
			boardState.placeQueenAt(currentColumn, rowValue);
		}
		return boardState;
	}
	
	public static int askForNumQueens(){
		System.out.print("\n\nEnter number of queens n: ");
		@SuppressWarnings("resource")
		int size = (new Scanner(System.in)).nextInt();		
		return size;
	}
}
