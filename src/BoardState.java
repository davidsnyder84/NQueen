//David Snyder a247a342 cs771 prog1

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class BoardState {
	public static final int DEFAULT_NUM_QUEENS = 4;
	public static final int LEFTMOST_POSITION = 0;
	public static final int UPMOST_POSITION = 0;
	public static final char SYMBOL_BLANK = 'o';
	public static final char SYMBOL_QUEEN = 'X';
	
	
	private int numQueens;
	private ArrayList<Point> queenPositions;
	
	
	public BoardState(int gridsize) {
		numQueens = gridsize;		
		queenPositions = new ArrayList<Point>();
	}
	public BoardState(){this(DEFAULT_NUM_QUEENS);}
	
	//copy constructor
	public BoardState(BoardState other){
		numQueens = other.numQueens;
		queenPositions = new ArrayList<Point>(other.queenPositions);
	}
	
	
	
	public void placeQueenAt(Point p){queenPositions.add(p);}
	public void placeQueenAt(int x, int y){placeQueenAt(new Point (x,y));}
	public void removeQueenAt(Point p){queenPositions.remove(p);}
	public void removeQueenAt(int x, int y){removeQueenAt(new Point (x,y));}
	
	
	
	
	//list of possibile successor states (child states / states that can be reached by making a single move)
	public ArrayList<BoardState> listOfPossibleSuccessorStates(){
		ArrayList<BoardState> successorStates = new ArrayList<BoardState>();
		
		
		
		return successorStates;
	}
	
	
	//returns true if the coordinate exists on the board (it is within the board's boundaries)
	private boolean validCoordinate(Point p){return (p.x >= 0 && p.y >= 0 && p.x < numQueens && p.y < numQueens);}
	private boolean validCoordinate(int x, int y){return validCoordinate(new Point(x,y));}
	
	
	public int numConflicts(){
		
		return 999;
	}
	
	public ArrayList<Point> lineOfFireOfQueenAt(Point queenPosition){
		ArrayList<Point> lineOfFire = new ArrayList<Point>();
		
		//horizontal/vertical
		for (int i = 0; i < numQueens; i++){
			lineOfFire.add(new Point(i, queenPosition.y));	//horizontal line of fire
			lineOfFire.add(new Point(queenPosition.x, i));	//vertical line of fire
		}
//		lineOfFire.remove(queenPosition);
//		lineOfFire.remove(queenPosition);
		
		//diagonals
		Point upLeftDiagonalCrawler = new Point(queenPosition.x - 1, queenPosition.y - 1);
		while (validCoordinate(upLeftDiagonalCrawler)){
			lineOfFire.add(new Point(upLeftDiagonalCrawler));
			upLeftDiagonalCrawler.y -= 1; upLeftDiagonalCrawler.x -= 1;
		}
		
		Point upRightDiagonalCrawler = new Point(queenPosition.x + 1, queenPosition.y + 1);
		while (validCoordinate(upRightDiagonalCrawler)){
			lineOfFire.add(new Point(upRightDiagonalCrawler));
			upRightDiagonalCrawler.y += 1; upRightDiagonalCrawler.x += 1;
		}
		for (Point p: lineOfFire) System.out.println(p.x + "," + p.y);
		
		return lineOfFire;
	}
	public ArrayList<Point> lineOfFireOfQueenAt(int x, int y){return lineOfFireOfQueenAt(new Point(x,y));}
	
	
	
	
	//two board statea are equal if all of their queens are placed in the same spots
	@Override
	public boolean equals(Object o){
		BoardState other = (BoardState) o;
		return queenPositions.equals(other.queenPositions);
	}
	
	public String toString(){
		String boardstring = "";
		for (int i=0; i<numQueens; i++){
			for (int j=0; j<numQueens; j++)
				if (queenPositions.contains(new Point(i,j)))
					boardstring += SYMBOL_QUEEN + " ";
				else
					boardstring += SYMBOL_BLANK + " ";
			boardstring += "\n";
		}
		return boardstring;
	}
}
