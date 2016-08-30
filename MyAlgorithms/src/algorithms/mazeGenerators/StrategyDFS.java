package algorithms.mazeGenerators;

/**
 * DFS choose
 * @author Tomer,Gilad
 *
 */
public class StrategyDFS implements ChooseStrategy {

	public void generate(GrowingTreeGenerator gt, Position currPos){ // method to choose DFS strategy
		gt.DFS(currPos);
	}

}
