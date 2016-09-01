package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * Generator for creating a simple maze
 * @author Tomer, Gilad
 *
 */
public class SimpleMaze3DGenerator extends Maze3DGeneratorBase { 
	
	private ArrayList<Position> fakePositions = new ArrayList<Position>(); // fake positions made
	
	@Override
	public Maze3D generate(int floors, int rows, int cols) { // method to generate a simple maze and randomly put walls
		maze = new Maze3D(floors+2,rows,cols);
		
		maze.putAllWalls(); // put walls all over the maze
		
		// create a start position
		Position startPos = chooseRandomStartPosition(); 
		maze.setStartPosition(startPos);
		maze.setFree(startPos);

		createFakePathes(maze); // create fake path in the maze 
		
		// create a goal position
		createPathToGoal(maze, fakePositions);
	
		return maze;
	}
	
	/**
	 * method to crate a simple road from start position to end position
	 * @param startPos {@link Position}
	 * @param goalPos {@link Position}
	 */
	private void BordersRoad(Position startPos, Position goalPos){ 
		Position currPos = new Position(startPos.z,startPos.y,startPos.x); // create current position
		// move the x line (columns)
		while((currPos.x < goalPos.x)&&(currPos.x < maze.getCols()-2)){ // case 1 if the start position is before the end position
			currPos.x ++;
			maze.setFree(currPos);
	    }
		while((currPos.x > goalPos.x)&&currPos.x >1){ // case 2 if the start position is after the end position
			currPos.x --;
			maze.setFree(currPos);
	    }
		// move the y line (rows)
		while(currPos.y < goalPos.y && currPos.y < maze.getRows()-2){ // case 1 if the start position is before the end position
			currPos.y ++;
			maze.setFree(currPos);
	    }
		while(currPos.y > goalPos.y && currPos.y >1){ // case 2 if the start position is after the end position
			currPos.y --;
			maze.setFree(currPos);	
		}
		// move the z line (floors)
		while(currPos.z < goalPos.z){ // case 1 if the start position is before the end position
			currPos.z ++;
			maze.setFree(currPos);
	    }
		while(currPos.z > goalPos.z){ // case 2 if the start position is after the end position
			currPos.z --;
			maze.setFree(currPos);	
		}
	}

	/**
	 * 
	 * @return {@link Maze3D}
	 */
	public Maze3D getMaze() { 
		return maze;
	}
	
	/**
	 *  method to create a fake path in the maze 
	 */
	private void createFakePathes(Maze3D maze){ 
		
		for (int i = 0; i <maze.getFloors()*5;i++){ // make fake roads in the maze
		    Position fakePos = chooseRandomStartPosition(); // choose a fake path
		    fakePositions.add(fakePos);
		    maze.setFree(fakePos);
		    BordersRoad(fakePos, maze.getStartPosition()); // create a road 

		}
		for (int i = 0;i<maze.getFloors()*3;i++){  // connect fake path
			Position pos1 = fakePositions.get(rand.nextInt(fakePositions.size()));
			Position pos2 = fakePositions.get(rand.nextInt(fakePositions.size()));
			Position pos3 = fakePositions.get(rand.nextInt(fakePositions.size()));
			BordersRoad(pos1, pos2);
			BordersRoad(pos1, pos3);
			BordersRoad(pos2, pos3);
		}
	}
}
