package algorithms.search;

/**
 *  Interface for searchers
 * @author Tomer, Gilad
 *
 * @param <T>
 */
public interface Searcher<T> {
	
	/**
	 * the search method for game solution
	 * @param s {@link Searchable}
	 * @return {@link Solution}
	 */
	public Solution<T> Search(Searchable s);
	
	/**
	 * 
	 * @return how many nodes were evaluated by the algorithm
	 */
	public int getNumOfNodesEvaluated();

}
