package algorithms.mazeGenerators;

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
			 x = rand.nextInt(maze.getCols()-2)+1;
			 y = rand.nextInt(maze.getRows()-2)+1;
		}else{
			do{ // if the floor is in the middle choose the exit
			 x = rand.nextInt(maze.getCols());
			 y = rand.nextInt(maze.getRows());
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
		
		// Choose an even column
		int x = rand.nextInt(maze.getCols()-2)+1;
		
		// Choose an even row
		int y = rand.nextInt(maze.getRows()-2)+1;	
				
		return new Position(z,y,x);		
	}
}
