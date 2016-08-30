package algorithms.mazeGenerators;

/**
 * Strategy pattern
 * @author Tomer , Gilad
 *
 */
public interface ChooseStrategy {
	/**
	 *Method to choose strategy
	 * @param gt {@link GrowingTreeGenerator}
	 * @param currPos {@link Position}
	 */
	public void generate(GrowingTreeGenerator gt, Position currPos); 
}
