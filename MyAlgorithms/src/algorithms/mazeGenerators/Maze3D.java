package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * 3D maze 
 * @author Tomer , Gilad
 *
 */
public class Maze3D {
	private int [][][] maze; // 3d maze array
	private int floors; // number of floors
	private int rows; // number of rows
	private int cols; // number of columns

	public static final int FREE = 0; // represents a free cell
	public static final int WALL = 1; // represents a wall cell
	
	private Position startPosition; // start position of the maze
	private Position goalPosition; // end position of the maze
	
	/**
	 * CTOR
	 * @param floors
	 * @param rows
	 * @param cols
	 */
	public Maze3D(int floors, int rows,int cols){ 
		this.floors = floors;
		this.rows = rows;
		this.cols = cols;
		maze = new int[floors][rows][cols];
		
	}

	/**
	 * 
	 * @return Number of floors
	 */
	public int getFloors() { 
		return floors;
	}

	/**
	 * 
	 * @return Number of rows
	 */
	public int getRows() { 
		return rows;
	}

	/**
	 * 
	 * @return Number of columns
	 */
	public int getCols() { 
		return cols;
	}

	/**
	 * 
	 * @return start position
	 */
	public Position getStartPosition() { 
		return startPosition;
	}

	/**
	 * sets the start position
	 * @param startPosition {@link Position}
	 */
	public void setStartPosition(Position startPosition) { 
		this.startPosition = startPosition;
	}

	/**
	 * 
	 * @return end position
	 */
	public Position getGoalPosition() {
		return goalPosition;
	}

	/**
	 * sets the end position
	 * @param goalPosition {@link Position}
	 */
	public void setGoalPosition(Position goalPosition) { 
		this.goalPosition = goalPosition;
	}
	
	/**
	 * Method to set a wall in the position
	 * @param pos {@link Position}
	 */
	public void setWall(Position pos) { 
		maze[pos.z][pos.y][pos.x] = WALL;
	}
	
	/**
	 * Method to break a wall in the position
	 * @param pos {@link Position}
	 */
	public void setFree(Position pos) { 
		maze[pos.z][pos.y][pos.x] = FREE;
	}
	
	/**
	 * Method to break a wall in the specific(z,y,x) of the position
	 * @param z 
	 * @param y
	 * @param x
	 */
	public void setFree(int z, int y, int x) { 
		maze[z][y][x] = FREE;
	}

	/**
	 * 
	 * @return 3D maze
	 */
	public int[][][] getMaze() { 
		return maze;
	}
	
	/**
	 * 
	 * @param z
	 * @param y
	 * @param x
	 * @return position value
	 */
	public int getValue(int z,int y, int x) { 
		return maze[z][y][x];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int z =0; z <floors;z++){
			for (int y =0;y<rows;y++){
				for (int x = 0; x < cols; x++){
					if (z == startPosition.z && y == startPosition.y && x == startPosition.x) // signal the start position
						sb.append("S");
					else if (z == goalPosition.z && y == goalPosition.y && x == goalPosition.x)
						sb.append("E"); // signal the out position
					else
						sb.append(maze[z][y][x] );
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Method to put walls all over the maze 
	 */
	public void putAllWalls(){ 
		for (int z =0; z <floors;z++){
			for (int y =0;y<rows;y++){
				for (int x = 0; x < cols; x++){
					maze[z][y][x] = 1;
				}
			}
		}
	}
	
	/**
	 * Method to get all the possible moves from a position in strings
	 * @param currPos {@link Position}
	 * @return {@link String}[]
	 */
	public String[] getPossibleMoves(Position currPos){ 
        ArrayList<Direction> directions = new ArrayList<Direction>();
		
		if (currPos.x + 1 < getCols() && 
				getValue(currPos.z,currPos.y,currPos.x +1) == FREE) {
			directions.add(Direction.Right);
		}
		
		if (currPos.x - 1 >= 0 && 
				getValue(currPos.z, currPos.y,currPos.x - 1) == FREE) {
			directions.add(Direction.Left);
		}
		
		if (currPos.y - 1 >=0 && 
				getValue(currPos.z, currPos.y - 1,currPos.x) == FREE) {
			directions.add(Direction.Forward);
		}
		
		if (currPos.y + 1 < getRows() && 
				getValue(currPos.z, currPos.y + 1,currPos.x) == FREE) {
			directions.add(Direction.Backward);
		}
		
		if (currPos.z + 1 < getFloors() && getValue(currPos.z+1, currPos.y, currPos.x) == FREE){
			directions.add(Direction.Up);
		}
		
		if (currPos.z - 1 >=0 && getValue(currPos.z-1, currPos.y, currPos.x) == FREE){
			directions.add(Direction.Down);
		}
		
		Direction[] dirs = directions.toArray(new Direction[directions.size()]);    // transfer directions to string array
		String[] moves = new String[dirs.length];
		for (int i = 0;i<moves.length;i++){
			moves[i] = dirs[i].name();
		}
    	return moves;
	}
	
	/**
	 * method to print 2d maze
	 * @param maze2d int[][]
	 */
	public void Print2DArray(int[][] maze2d){
		System.out.println("2D maze:");
		for (int y = 0; y< maze2d.length; y++ ){
			for (int x = 0; x<maze2d[0].length ; x++){
				System.out.print(maze2d[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * method to get the cross section by x value
	 * @param xPos {@link Integer}
	 * @return {@link Integer}[][]
	 */
	public int[][] getCrossSectionByX(int xPos){ 
		int Section[][] ;
		Section = new int[floors][rows];
		for (int z = 0;z<floors;z++){
			for (int y = 0;y<rows;y++){
				Section[z][y] = getValue(z, y, xPos);
			}
		}
		return Section;
	}
	
	/**
	 * method to get the cross section by y value
	 * @param yPos {@link Integer}
	 * @return {@link Integer}[][]
	 */
	public int[][] getCrossSectionByY(int yPos){ 
		int Section[][] ;
		Section = new int[floors][cols];
		for (int z = 0;z<floors;z++){
			for (int x = 0; x<cols; x++){
				Section[z][x] = getValue(z, yPos, x);
			}
		}
		return Section;
	}
	
	/**
	 * method to get the cross section by z value
	 * @param zPos {@link Integer}
	 * @return {@link Integer}[][]
	 */
	public int[][] getCrossSectionByZ(int zPos){
		int Section[][] ;
		Section = new int[rows][cols];
		for (int y = 0; y<rows; y++){
			for (int x = 0; x<cols; x++){
				Section[y][x] = getValue(zPos, y, x);
			}
		}
		return Section;
	}

	/**
	 * method to get all the possible moves from a position in position array
	 * @param currPos {@link Position}
	 * @return {@link Position}[]
	 */
	public Position[] getPossiblePositions(Position currPos){ 
		
		ArrayList<Position> PossiblePositions = new ArrayList<Position>();
		
		if (currPos.x + 1 < getCols() && 
				getValue(currPos.z,currPos.y,currPos.x +1) == FREE) {
			PossiblePositions.add(new Position(currPos.z,currPos.y,currPos.x+1));
		}
		
		if (currPos.x - 1 >= 0 && 
				getValue(currPos.z, currPos.y,currPos.x - 1) == FREE) {
			PossiblePositions.add(new Position(currPos.z,currPos.y,currPos.x-1));
		}
		
		if (currPos.y - 1 >=0 && 
				getValue(currPos.z, currPos.y - 1,currPos.x) == FREE) {
			PossiblePositions.add(new Position(currPos.z,currPos.y-1,currPos.x));
		}
		
		if (currPos.y + 1 < getRows() && 
				getValue(currPos.z, currPos.y + 1,currPos.x) == FREE) {
			PossiblePositions.add(new Position(currPos.z,currPos.y+1,currPos.x));
		}
		
		if (currPos.z + 1 < getFloors() && getValue(currPos.z+1, currPos.y, currPos.x) == FREE){
			PossiblePositions.add(new Position(currPos.z+1,currPos.y,currPos.x));
		}
		
		if (currPos.z - 1 >=0 && getValue(currPos.z-1, currPos.y, currPos.x) == FREE){
			PossiblePositions.add(new Position(currPos.z-1,currPos.y,currPos.x));
		}
		
		Position[] pos = PossiblePositions.toArray(new Position[PossiblePositions.size()]);    // transfer directions to positions array
		
		return pos;
		
	}

	
	
}
