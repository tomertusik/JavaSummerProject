package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * Generator to launch a 3Dmaze using a growing tree algorithm
 * @author Tomer, Gilad
 *
 */
public class GrowingTreeGenerator extends Maze3DGeneratorBase { 
	
	private ChooseStrategy strategy;
	private ArrayList<Position> positionsMade = new ArrayList<Position>(); // array to save positions we go through
	private ArrayList<Position> allPositionsMade = new ArrayList<Position>(); // array to save all the positions we go through
	
	public GrowingTreeGenerator(ChooseStrategy s) {
		this.strategy = s;
	}

	/**
	 * Generate method to create the maze 
	 */
	public Maze3D generate(int floors, int rows, int cols) { 
	    maze = new Maze3D(floors+2,rows,cols)	;
		maze.putAllWalls();                             // create new maze full with walls
		Position startPos = chooseRandomStartPosition();
		maze.setStartPosition(startPos);
		allPositionsMade.add(startPos);
		positionsMade.add(startPos);  // add start position
		maze.setFree(startPos);
		strategy.generate(this, startPos);             // generate the maze with a specific strategy
		int rNum; 
		Position ChoosenPos;
		do{                                    
		rNum = rand.nextInt(allPositionsMade.size()-1);
		ChoosenPos = allPositionsMade.get(rNum); // choose a random position from the positions made
		}while(ChoosenPos.equals(startPos));
		rNum = rand.nextInt(6); // choose a direction to go to
		while(ChoosenPos.x !=0 && ChoosenPos.y != 0 && ChoosenPos.z != 0 && ChoosenPos.x !=maze.getCols()-1 && ChoosenPos.y != maze.getRows()-1 && ChoosenPos.z !=maze.getFloors()-1 ){
			switch (rNum) {
			case 0: // up
				ChoosenPos.z++;
				break;
			case 1: // down
				ChoosenPos.z--;
				break;
			case 2: // right
				ChoosenPos.x++;
				break;
			case 3: // left
				ChoosenPos.x--;
				break;
			case 4: // backward
				ChoosenPos.y++;
				break;
			case 5: // forward
				ChoosenPos.y--;
				break;

			default:
				break;
			}
			maze.setFree(ChoosenPos);
		}
		maze.setGoalPosition(ChoosenPos);
		return maze;
	}
	
	/**
	 * Method to move to the next position
	 * @param currPos {@link Position}
	 * @param dirs {@link Direction}
	 * @param randomDir {@link Integer}
	 * @return
	 */
	private Position MoveToNewPosition(Position currPos, Direction[] dirs, int randomDir){ 
		
		Position newPos = null;
		
		switch (dirs[randomDir]) {
		case Right:
			maze.setFree(currPos.z, currPos.y,currPos.x+1);
			maze.setFree(currPos.z, currPos.y,currPos.x+2);
			newPos = new Position(currPos.z, currPos.y,currPos.x+2);
			positionsMade.add(newPos);
			allPositionsMade.add(newPos);
			break;	
		
		case Left:
			maze.setFree(currPos.z, currPos.y,currPos.x-1);
			maze.setFree(currPos.z, currPos.y,currPos.x-2);
			newPos = new Position(currPos.z, currPos.y,currPos.x-2);
			positionsMade.add(newPos);
			allPositionsMade.add(newPos);
			break;
		
		case Forward:
			maze.setFree(currPos.z, currPos.y-1,currPos.x);
			maze.setFree(currPos.z, currPos.y-2,currPos.x);
		    newPos = new Position(currPos.z, currPos.y-2,currPos.x);
		    positionsMade.add(newPos);
		    allPositionsMade.add(newPos);
			break;
			
		case Backward:
			maze.setFree(currPos.z, currPos.y+1,currPos.x);
			maze.setFree(currPos.z, currPos.y+2,currPos.x);
		    newPos = new Position(currPos.z, currPos.y+2,currPos.x);
		    positionsMade.add(newPos);
		    allPositionsMade.add(newPos);
			break;
			
		case Up:
			maze.setFree(currPos.z+1, currPos.y,currPos.x);
		    newPos = new Position(currPos.z+1, currPos.y,currPos.x);
		    positionsMade.add(newPos);
		    allPositionsMade.add(newPos);
			break;
			
		case Down:
			maze.setFree(currPos.z-1, currPos.y,currPos.x);
		    newPos = new Position(currPos.z-1, currPos.y,currPos.x);
		    positionsMade.add(newPos);
		    allPositionsMade.add(newPos);
			break;
			
		default:
				break;
		}
		return newPos;
	}
	
	/**
	 * Method to create a path with a snake algorithm that choose a random cell
	 */
	public void RDC(){ 
		while(!positionsMade.isEmpty()){
		int cellChoose;
		if (positionsMade.size() == 1){
			cellChoose = 0;
		}
		else{
		    cellChoose = rand.nextInt(positionsMade.size()-1);
		}
		Position currPos = positionsMade.get(cellChoose); // choose a random cell from the list each time
		Direction[] dirs = getPossibleDirections(currPos);
		
		if (dirs.length > 0){ // if we got a direction to go to 
			
			int randomDir = rand.nextInt(dirs.length);
			MoveToNewPosition(currPos, dirs, randomDir);  // move to the next position and repeat
		}
		else{ // if we don't have a direction to go to
			if (positionsMade.size() > 1) { 
			   positionsMade.remove(currPos);
			}
			else{
				positionsMade.remove(currPos); // clear the list and done
			}
		}
		}
	}

	/**
	 * Method to create a path with a snake algorithm that choose the last cell each time
	 * @param currPos {@link Position}
	 */
	public void DFS(Position currPos){ 
		
		while(!positionsMade.isEmpty()){
		Direction[] dirs = getPossibleDirections(currPos);
		
		if (dirs.length > 0){ // if we got a direction to go to 
			
		int randomDir = rand.nextInt(dirs.length);
		Position newPos = MoveToNewPosition(currPos, dirs, randomDir);
		currPos = newPos;
	}
		else{ // if we don't have a direction to go to
			if (positionsMade.size() > 1) { 
			   positionsMade.remove(currPos);
			   currPos = positionsMade.get(positionsMade.size()-1);
			}
			else{
				positionsMade.remove(currPos);
			}
		}
	}
}
	
	/**
	 * Method to get all the possible directions for a position
	 * @param currPos {@link Position}
	 * @return {@link Direction}[]
	 */
	private Direction[] getPossibleDirections(Position currPos) { 
        ArrayList<Direction> directions = new ArrayList<Direction>();
		
		if (currPos.x + 2 < maze.getCols()-1 && 
				maze.getValue(currPos.z,currPos.y,currPos.x +2) == Maze3D.WALL) {
			directions.add(Direction.Right);
		}
		
		if (currPos.x - 2 > 0 && 
				maze.getValue(currPos.z, currPos.y,currPos.x - 2) == Maze3D.WALL) {
			directions.add(Direction.Left);
		}
		
		if (currPos.y - 2 >0 && 
				maze.getValue(currPos.z, currPos.y - 2,currPos.x) == Maze3D.WALL) {
			directions.add(Direction.Forward);
		}
		
		if (currPos.y + 2 < maze.getRows()-1 && 
				maze.getValue(currPos.z, currPos.y + 2,currPos.x) == Maze3D.WALL) {
			directions.add(Direction.Backward);
		}
		
		if (currPos.z + 1 < maze.getFloors()-1 && maze.getValue(currPos.z+1, currPos.y, currPos.x) == Maze3D.WALL){
			directions.add(Direction.Up);
		}
		
		if (currPos.z - 1 >0 && maze.getValue(currPos.z-1, currPos.y, currPos.x) == Maze3D.WALL){
			directions.add(Direction.Down);
		}
		
		return directions.toArray(new Direction[directions.size()]);
	}

	/**
	 * 
	 * @return {@link Maze3D}
	 */
	public Maze3D getMaze() {
		return maze;
	}
	
}
