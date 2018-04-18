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
		
//		//horizontal/vertical
//		for (int i = 0; i < numQueens; i++){
//			lineOfFire.add(new Point(i, queenPosition.y));	//horizontal line of fire
//			lineOfFire.add(new Point(queenPosition.x, i));	//vertical line of fire
//		}
//		lineOfFire.remove(queenPosition);
//		
//		//diagonals
//		Point upLeftDiagonalCrawler = new Point(queenPosition.x - 1, queenPosition.y - 1);
//		while (validCoordinate(upLeftDiagonalCrawler)){
//			lineOfFire.add(new Point(upLeftDiagonalCrawler));
//			upLeftDiagonalCrawler.y -= 1; upLeftDiagonalCrawler.x -= 1;
//		}
//		
//		Point upRightDiagonalCrawler = new Point(queenPosition.x + 1, queenPosition.y + 1);
//		while (validCoordinate(upRightDiagonalCrawler)){
//			lineOfFire.add(new Point(upRightDiagonalCrawler));
//			upRightDiagonalCrawler.y += 1; upRightDiagonalCrawler.x += 1;
//		}
//		for (Point p: lineOfFire) System.out.println(p.x + "," + p.y);
		
		return lineOfFire;
	}
	public HashSet<Point> lineOfFireOfQueenAt(int x, int y){return lineOfFireOfQueenAt(new Point(x,y));}
	
	
	
	public HashSet<Point> crawlFrom(Point p, Crawl crawl){
		HashSet<Point> lineOfFire = new HashSet<Point>();
		Point crawler = new Point(p.x + crawl.xmod, p.y + crawl.ymod);
		while (validCoordinate(crawler)){
			lineOfFire.add(new Point(crawler));
			crawler.x += crawl.xmod; crawler.y += crawl.ymod;
		}
		
		return lineOfFire;
	}
	
	private enum Crawl{
		UP(0,-1),
		DOWN(0,1),
		LEFT(-1,0),
		RIGHT(1,0),
		DIAG_UPLEFT(-1,-1),
		DIAG_UPRIGHT(1,-1),
		DIAG_DOWNLEFT(-1,1),
		DIAG_DOWNRIGHT(1,1);
		private int xmod, ymod;
		
		private Crawl(int xshift, int yshift){
			xmod=xshift; ymod=yshift;
		}
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
