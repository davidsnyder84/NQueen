//David Snyder a247a342 cs771 prog2
import java.util.Scanner;

public class NQueen {
	
	public static void main(String[] args) {
		System.out.println("\nn-Queens Solver\n");
		
		BoardState startState = menuChoice();		
		if (startState == null){
			System.out.print("exiting program.........");
			return;
		}
		
		
		///////do search
		
		System.out.println("\nprogram end");
	}
	
	
	
	
	
	
	//-----------user input methods
	private static BoardState menuChoice(){
		final int CHOICE_INPUT = 1, CHOICE_DEMO_4 = 2, CHOICE_DEMO_8 = 3;
		System.out.println(
				"===Choose an option===\n"+
				"  1. Input queens manually\n"+
				"  2. View demo (n=4 queens)\n"+
				"  3. View demo (n=8 queens)\n"+
				"choice: ");
//		@SuppressWarnings("resource")
//		int choice = (new Scanner(System.in)).nextInt();
		int choice = CHOICE_DEMO_8;
		
		switch(choice){
		case CHOICE_INPUT:
			return userInputBoard();
		case CHOICE_DEMO_4:
			return DemoBoards.getDemoStartState4Queens();
		case CHOICE_DEMO_8:
			return DemoBoards.getDemoStartState8Queens();
		default:
			return null;
		}
	}
	
	private static BoardState userInputBoard(){
		int numQueens = askForNumQueens();
		BoardState boardInput = askForQueenPositions(numQueens);
		System.out.println(boardInput);
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
