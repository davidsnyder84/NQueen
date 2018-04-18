//David Snyder a247a342 cs771 prog2

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class BoardState implements Comparable<BoardState>{
	public static final int DEFAULT_NUM_QUEENS = 4;
	public static final char SYMBOL_BLANK = 'o';
	public static final char SYMBOL_QUEEN = 'X';
	private static final Random random = new Random();
	
	
	private int numQueens;
	private HashSet<Point> queenPositions;
	
	
	public BoardState(int numberOfQueens) {
		numQueens = numberOfQueens;		
		queenPositions = new HashSet<Point>();
	}
	public BoardState(){this(DEFAULT_NUM_QUEENS);}
	
	//copy constructor
	public BoardState(BoardState other){
		numQueens = other.numQueens;
		queenPositions = new HashSet<Point>(other.queenPositions);
	}
	
	
	public int getNumQueens(){return numQueens;}
	
	public void placeQueenAt(Point p){queenPositions.add(p);}
	public void placeQueenAt(int x, int y){placeQueenAt(new Point (x,y));}
	public void removeQueenAt(Point p){queenPositions.remove(p);}
	public void removeQueenAt(int x, int y){removeQueenAt(new Point (x,y));}
	
	
	
	
	
	//list of possibile successor states (child states / states that can be reached by making a single move)
	public ArrayList<BoardState> listOfPossibleSuccessorStatesForQueen(int desiredColumn){
		ArrayList<BoardState> successorStates = new ArrayList<BoardState>();
		Point queenSpace = queenInColumnNumber(desiredColumn);
		
		//set of blank spaces in the column (possible spaces where the queen can move within the column)
		HashSet<Point> blankSpaces = new HashSet<Point>();
		for (int row = 0; row < numQueens; row++)
			blankSpaces.add(new Point(desiredColumn, row));
		blankSpaces.remove(queenSpace);
		
		//generate a successor state for each blank space the queen can move to
		for (Point destination: blankSpaces){
			BoardState successor = new BoardState(this);
			successor.removeQueenAt(queenSpace);
			successor.placeQueenAt(destination);
			successorStates.add(successor);
		}
		
		return successorStates;
	}
	
	public BoardState successorStateWithLeastConflictsForQueen(int columnNum){
		ArrayList<BoardState> allSuccessors = listOfPossibleSuccessorStatesForQueen(columnNum);
		Collections.shuffle(allSuccessors);
		Collections.sort(allSuccessors);
		return allSuccessors.get(0);
	}
	
	
	//returns the column which has the most conflicts (ie, column #3)
	public int queenWithMostConflicts(){
		int[] columnConflicts = new int[numQueens];
		int highestSeen = 0;
		for (int col = 0; col < numQueens; col++){
			columnConflicts[col] = numConflictsForQueen(col);
			if (highestSeen < columnConflicts[col])
				highestSeen = columnConflicts[col];
		}
		
		int highestColumn = -1;
		for (int col = 0; col < numQueens; col++)
			if (columnConflicts[col] == highestSeen)
				highestColumn = col;
		
		return highestColumn;
	}
	
	public int randomQueenInConflict(){
		ArrayList<Integer> queensInConflict = new ArrayList<Integer>();
		for (int col=0; col<numQueens; col++)
			if (queenIsInConflict(col))
				queensInConflict.add(col);
		
		//return random queen in conflict
		return queensInConflict.get(random.nextInt(queensInConflict.size()));
	}
	
	
	
	
	
	
	
	//returns the total number of pairs of queens that are attacking each other
	public int totalNumberOfConflicts(){
		int numberOfConflicts = 0;
		for (int column = 0; column < numQueens; column++)
			numberOfConflicts += numConflictsForQueen(column);
		
		//cut in half, because we counted each pair attacking each other twice
		return numberOfConflicts / 2;
	}
	//returns the number of conflicts for a single given queen
	public int numConflictsForQueen(int desiredColumn){
		Point thisQueen = queenInColumnNumber(desiredColumn);
		int numberOfConflicts = 0;
		for (Point enemyQueen: queenPositions)
			//queens are in conflict if one them lies in the line of fire of the other
			if (lineOfFireOf(thisQueen).contains(enemyQueen))
				numberOfConflicts++;
		return numberOfConflicts;
	}
	public boolean queenIsInConflict(int column){return numConflictsForQueen(column) == 0;}
	
	//returns the set of all points under attack by a queen at the given position
	//(all points that lie in a straight/diagonal line from the queen)
	public HashSet<Point> lineOfFireOf(Point queenPosition){
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
	public HashSet<Point> lineOfFireOfQueenAt(int x, int y){return lineOfFireOf(new Point(x,y));}
	
	
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
	
	//returns true if the coordinate exists on the board (it is within the board's boundaries)
	private boolean validCoordinate(Point p){return (p.x >= 0 && p.y >= 0 && p.x < numQueens && p.y < numQueens);}
	private boolean validCoordinate(int x, int y){return validCoordinate(new Point(x,y));}
	
	
	
	public Point queenInColumnNumber(int desiredColumn){
		for (Point p: queenPositions)
			if (p.x == desiredColumn)
				return p;
		return null;
	}
	
	
	
	//two board statea are equal if all of their queens are placed in the same spots
	@Override
	public boolean equals(Object o){
		BoardState other = (BoardState) o;
		return queenPositions.equals(other.queenPositions);
	}
	//order states in order of how many conflicts
	@Override
	public int compareTo(BoardState other) {
		return this.totalNumberOfConflicts() - other.totalNumberOfConflicts();
	}
	
	public String toString(){
		String boardstring = "";
		for (int y=0; y<numQueens; y++){
			for (int x=0; x<numQueens; x++)
				if (queenPositions.contains(new Point(x,y)))
					boardstring += SYMBOL_QUEEN + " ";
				else
					boardstring += SYMBOL_BLANK + " ";
			boardstring += "\n";
		}
		return boardstring;
	}
}
