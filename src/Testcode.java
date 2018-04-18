//David Snyder a247a342 cs771 prog2

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


public class Testcode {

	
	public static void main(String[] args){
//		equaltest();
//		fire();
//		numConflicts();

//		BoardState boardState = new BoardState();
//		boardState.placeQueenAt(0,1);
//		boardState.placeQueenAt(0,2);
//		System.out.println(boardState);
		
		
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
		int[] want = null;
		
//		boardState = DemoBoards.exampleState1CONFLICTS();
//		System.out.print(boardState);
//		System.out.println("#Conflicts? (want1): " + boardState.totalNumberOfConflicts() + "\n");
//		
		boardState = DemoBoards.exampleState2CONFLICTS();
		System.out.print(boardState);
		System.out.println("#Conflicts? (want2): " + boardState.totalNumberOfConflicts() + "\n");
		for (int c=0;c<boardState.getNumQueens();c++)System.out.printf("conflicts for row %d: %d\n", c, boardState.numConflictsForQueen(c));
		System.out.print("column with most conflicts?: " + boardState.columnWithMostConflicts());
//		
//		boardState = DemoBoards.exampleState3CONFLICTS();
//		System.out.print(boardState);
//		System.out.println("#Conflicts? (want3): " + boardState.totalNumberOfConflicts() + "\n");
		
		
		
		
//		want = new int[]{2,2,1,2,3,1,2,1};
//		for (int y = 0; y<8; y++){
//			boardState = DemoBoards.e1base();
//			boardState.placeQueenAt(7, y);
//			System.out.println("#Conflicts? (want " + want[y] + "): " + boardState.totalNumberOfConflicts());
//		}
//		System.out.println("\n");
//		
//		want = new int[]{3,3,1,2,3,2,3,0};
//		for (int y = 0; y<8; y++){
//			boardState = DemoBoards.e2base();
//			boardState.placeQueenAt(5, y);
//			System.out.println("#Conflicts? (want " + want[y] + "): " + boardState.totalNumberOfConflicts());
//		}
		
	}
}
