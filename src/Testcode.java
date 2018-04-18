import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class Testcode {

	
	public static void main(String[] args){
//		equaltest();
		fire();
		numConflicts();
		
		
//		Point p = new Point(0,0);
//		System.out.println(p.x + "," + p.y);
//		p.x+=1;
//		System.out.println(p.x + "," + p.y);
//		p.x+=1;
//		System.out.println(p.x + "," + p.y);
	}
	
	private static void equaltest(){
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
	private static void fire(){
		BoardState boardState = new BoardState();
//		boardState.placeQueenAt(0,0);
//		boardState.placeQueenAt(0,1);
		
		Collection<Point> fireline = new ArrayList<Point>();
		fireline = boardState.lineOfFireOfQueenAt(0,1);
		for (Point p: fireline) boardState.placeQueenAt(p);
		System.out.println(boardState);
		
		boardState = new BoardState();
		fireline = boardState.lineOfFireOfQueenAt(0,0);
		for (Point p: fireline) boardState.placeQueenAt(p);
		System.out.println(boardState);
		
		boardState = new BoardState();
		fireline = boardState.lineOfFireOfQueenAt(2,2);
		for (Point p: fireline) boardState.placeQueenAt(p);
		System.out.println(boardState);
		
		boardState = new BoardState();
		fireline = boardState.lineOfFireOfQueenAt(3,3);
		for (Point p: fireline) boardState.placeQueenAt(p);
		System.out.println(boardState);
		
		boardState = new BoardState();
		fireline = boardState.lineOfFireOfQueenAt(0,3);
		for (Point p: fireline) boardState.placeQueenAt(p);
		System.out.println(boardState);
		
//		HashSet<Point> h = new HashSet<Point>(fireline);
//		for (Point p: h) System.out.println(p.x+","+p.y);
	}
	
	
	private static void numConflicts(){
		BoardState boardState = new BoardState();
		System.out.println(boardState);		
	}
}
