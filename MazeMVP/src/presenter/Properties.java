package presenter;

import java.io.Serializable;
/**
 * Properties of the program 
 * @author Tomer, Gilad
 *
 */
public class Properties implements Serializable {

	private static final long serialVersionUID = 1L;
	private int numOfThreads;
	private String generateMazeAlgorithm;
	private String solveMazeAlgorithm;
	private String viewType;
	
	/**
	 * CTOR
	 */
    public Properties() {
	}
			
    /**
     * Get the number of threads in the thread pool
     * @return
     */
	public int getNumOfThreads() {
		return numOfThreads;
	}
	
	/**
	 * Set the number of threads in the thread pool
	 * @param numOfThreads
	 */
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	
	/**
	 * Get the generate maze algorithm which is used to create mazes
	 * @return
	 */
	public String getGenerateMazeAlgorithm() {
		return generateMazeAlgorithm;
	}
	
	/**
	 * Set the generate maze algorithm which is used to create mazes
	 * @param generateMazeAlgorithm
	 */
	public void setGenerateMazeAlgorithm(String generateMazeAlgorithm) {
		this.generateMazeAlgorithm = generateMazeAlgorithm;
	}
	
	/**
	 * Get the solve maze algorithm which is used to solve mazes
	 * @return
	 */
	public String getSolveMazeAlgorithm() {
		return solveMazeAlgorithm;
	}
	
	/**
	 * Set the solve maze algorithm which is used to solve mazes
	 * @return
	 */
	public void setSolveMazeAlgorithm(String solveMazeAlgorithm) {
		this.solveMazeAlgorithm = solveMazeAlgorithm;
	}

	/**
	 * Get the view type 
	 * @return
	 */
	public String getViewType() {
		return viewType;
	}

	/**
	 * Set the view type 
	 * @return
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	
}
