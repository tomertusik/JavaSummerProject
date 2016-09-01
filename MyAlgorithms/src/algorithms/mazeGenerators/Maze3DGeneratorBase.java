package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 *Common generator 
 * @author Tomer , Gilad
 *
 */
public abstract class Maze3DGeneratorBase implements Maze3DGenerator {

	@Override
	public String measureAlgorithmTime(int floors, int rows,int cols) { // method to measure the time it takes to do the algorithm
		long startTime =System.currentTimeMillis();
		this.generate(floors, rows, cols);
		long endTime = System.currentTimeMillis();
		return String.valueOf(endTime - startTime);
	}
	
	protected Random rand = new Random(); 
	protected Maze3D maze;
	
	/**
	 *  method to choose a random end position
	 * @return {@link Position}
	 */
	protected Position chooseRandomEndPosition() {
		//choose floor
		int z = rand.nextInt(maze.getFloors());     // choose a random floor 
		int x;
		int y;
		
		if (z==0 || z==maze.getFloors()-1) { // if the floor is first or last
			do{
			 x = rand.nextInt(maze.getCols()-2)+1;
			}while(x % 2 == 0);
			do{
			 y = rand.nextInt(maze.getRows()-2)+1;
			}while(y % 2 == 0);
		
		}else{
			do{ // if the floor is in the middle choose the exit
				do{
				x = rand.nextInt(maze.getCols());
				}while(x % 2 == 0);
				do{	
					y = rand.nextInt(maze.getRows());
				}while(y % 2 == 0);
			}while( (x!=0 &&x!=maze.getCols()-1&&y!=0&&y!=maze.getRows()-1)||
					(x==0&&y==0)||(x==0&&y==maze.getRows()-1)||
					(y==0&&x==maze.getCols()-1)||
					(x==maze.getCols()-1&&y==maze.getRows()-1));
		}
		
	
				
		return new Position(z,y,x);		
	}
	
	/**
	 *  method to choose a random start position
	 * @return {@link Position}
	 */
	protected Position chooseRandomStartPosition() { 
		//choose floor
		int z=rand.nextInt(maze.getFloors()-2)+1;
		int x;
		int y;
		// Choose an odd column
		do{
		x = rand.nextInt(maze.getCols()-2)+1;
		}while(x % 2 == 0);
		
		// Choose an odd row
		do{
		y = rand.nextInt(maze.getRows()-2)+1;	
		}while(y % 2 == 0);	
		
		return new Position(z,y,x);		
	}
	
	protected void createPathToGoal(Maze3D maze, ArrayList<Position> allPositionsMade){
		int rNum; 
		Position ChoosenPos;
		do{                                    
		rNum = rand.nextInt(allPositionsMade.size()-1);
		ChoosenPos = allPositionsMade.get(rNum); // choose a random position from the positions made
		}while(ChoosenPos.equals(maze.getStartPosition()));
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
	}
}
