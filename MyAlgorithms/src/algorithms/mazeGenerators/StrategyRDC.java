package algorithms.mazeGenerators;

/**
 * RDC choose
 * @author Tomer, Gilad
 *
 */
public class StrategyRDC implements ChooseStrategy {

	public void generate(GrowingTreeGenerator gt, Position currPos){ // method to choose RDC strategy
		gt.RDC();
	}

}
