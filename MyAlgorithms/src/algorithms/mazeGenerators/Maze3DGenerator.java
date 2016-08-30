package algorithms.mazeGenerators;

/**
 * Common inteface for generators
 * @author Tomer, Gilad
 *
 */
public interface Maze3DGenerator{
	
	/**
	 * method to generate a maze
	 * @param floors
	 * @param rows
	 * @param cols
	 * @return {@link Maze3D}
	 */
	public Maze3D generate(int floors,int rows, int cols); 
	
	/**
	 *method to measure generate algorithm time
	 * @param floors
	 * @param rows
	 * @param cols
	 * @return {@link String}
	 */
	public String measureAlgorithmTime(int floors, int rows, int cols); 
	

}
