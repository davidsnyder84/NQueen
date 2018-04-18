//David Snyder a247a342 cs771 prog1

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class BoardState {
	public static final int DEFAULT_NUM_QUEENS = 4;
	public static final char SYMBOL_BLANK = 'o';
	public static final char SYMBOL_QUEEN = 'X';
	
	
	private int numQueens;
	private ArrayList<Point> queenPositions;
	
	
	public BoardState(int numberOfQueens) {
		numQueens = numberOfQueens;		
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
		int numberOfAttacks = 0;		
		for (Point currentQueen: queenPositions)
			for (Point enemyQueen: queenPositions)
				if (areAttackingEachOther(enemyQueen, currentQueen))
					numberOfAttacks++;
		
		//cut in half, because we counted each pair attacking each other twice
		return numberOfAttacks / 2;
	}
	private boolean areAttackingEachOther(Point attackerPosition, Point defenderPosition){
		//queens are attacking each other if one them in the line of fire of the other
		return lineOfFireOfQueenAt(attackerPosition).contains(defenderPosition);
	}
	
	//returns the set of all points under attack by a queen at the given position
	//(all points that lie in a straight/diagonal line from the queen)
	public HashSet<Point> lineOfFireOfQueenAt(Point queenPosition){
		HashSet<Point> lineOfFire = new HashSet<Point>();
		
		lineOfFire.addAll(crawlFrom(queenPosition, Crawl.UP));
		lineOfFire.addAll(crawlFrom(queenPosition, Crawl.DOWN));
		lineOfFire.addAll(crawlFrom(queenPosition, Crawl.LEFT));
		lineOfFire.addAll(crawlFrom(queenPosition, Crawl.RIGHT));
		lineOfFire.addAll(crawlFrom(queenPosition, Crawl.DIAG_UPLEFT));
		lineOfFire.addAll(crawlFrom(queenPosition, Crawl.DIAG_DOWNRIGHT));
		lineOfFire.addAll(crawlFrom(queenPosition, Crawl.DIAG_UPRIGHT));
		lineOfFire.addAll(crawlFrom(queenPosition, Crawl.DIAG_DOWNLEFT));
		
		return lineOfFire;
	}
	public HashSet<Point> lineOfFireOfQueenAt(int x, int y){return lineOfFireOfQueenAt(new Point(x,y));}
	
	
	//returns the set of points the lie in a straight line from point P in the given crawl direction
	public HashSet<Point> crawlFrom(Point p, Crawl crawlDirection){
		HashSet<Point> lineOfFire = new HashSet<Point>();
		Point crawler = new Point(p.x + crawlDirection.xmod, p.y + crawlDirection.ymod);
		while (validCoordinate(crawler)){
			lineOfFire.add(new Point(crawler));
			crawler.x += crawlDirection.xmod; crawler.y += crawlDirection.ymod;
		}
		
		return lineOfFire;
	}
	private static enum Crawl{
		UP(0,-1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0),
		DIAG_UPLEFT(-1,-1), DIAG_UPRIGHT(1,-1), DIAG_DOWNLEFT(-1,1), DIAG_DOWNRIGHT(1,1);
		private int xmod, ymod;
		private Crawl(int x, int y){xmod=x; ymod=y;}
	}
	
	
	
	
	
	
	
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
