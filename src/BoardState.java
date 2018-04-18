//David Snyder a247a342 cs771 prog1

import java.awt.Point;
import java.util.ArrayList;

import sun.net.www.content.text.plain;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class BoardState {
	public static final int DEFAULT_NUM_QUEENS = 4;
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
	
	
	//returns true if the coordinate exists on the grid (it is within the grid's boundaries)
	private boolean validCoordinate(Point p){return (p.x >= 0 && p.y >= 0 && p.x < numQueens && p.y < numQueens);}
	
	
	
	
	
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
