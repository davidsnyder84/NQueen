
public class DemoBoards {
	
	public static BoardState exampleState1CONFLICTS(){
		BoardState boardState = e1base();
		boardState.placeQueenAt(7, 2);
		return boardState;
	}
	public static BoardState exampleState2CONFLICTS(){
		BoardState boardState = e1base();
		boardState.placeQueenAt(7, 6);
		return boardState;
	}
	public static BoardState exampleState3CONFLICTS(){
		BoardState boardState = e1base();
		boardState.placeQueenAt(7, 4);
		return boardState;
	}
	
	public static BoardState e1base(){
		BoardState boardState = new BoardState(8);
		boardState.placeQueenAt(0, 1);
		boardState.placeQueenAt(1, 4);
		boardState.placeQueenAt(2, 6);
		boardState.placeQueenAt(3, 3);
		boardState.placeQueenAt(4, 0);
		boardState.placeQueenAt(5, 2);
		boardState.placeQueenAt(6, 5);
//		boardState.placeQueenAt(7, 7);
		return boardState;
	}
	
	public static BoardState e2base(){
		BoardState boardState = new BoardState(8);
		boardState.placeQueenAt(0, 1);
		boardState.placeQueenAt(1, 4);
		boardState.placeQueenAt(2, 6);
		boardState.placeQueenAt(3, 3);
		boardState.placeQueenAt(4, 0);
//		boardState.placeQueenAt(5, 2);
		boardState.placeQueenAt(6, 5);
		boardState.placeQueenAt(7, 2);
		return boardState;
	}
	
	
	
	
	
	
	
}
