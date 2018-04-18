
public class Testcode {

	
	public static void main(String[] args){
		BoardState boardState = new BoardState();
		boardState.placeQueenAt(0,1);
		boardState.placeQueenAt(2,3);
//		boardState.removeQueenAt(2, 3);
		System.out.println(boardState);
		

		BoardState g = new BoardState();
		g.placeQueenAt(0,1);
		g.placeQueenAt(2,3);
		System.out.println(g);
		
		System.out.println("Equal?: " + g.equals(boardState) + " " + boardState.equals(g));
	}
}
