package algorithms.search;

import java.util.ArrayList;

/**
 * Interface for representing a search problem
 * @author Tomer, Gilad
 *
 */
public interface Searchable {
	
	/**
	 * 
	 * @return the start state
	 */
	public <T> State<T> getStartState(); 
	
	/**
	 * 
	 * @return the goal state
	 */
	public <T> State<T> getGoalState(); 
	/**
	 * @param s {@link State}
	 * @return all possible states to go to from this state
	 */
	public <T> ArrayList<State<T>> getAllPossibleStates(State<T> s);
	
	/**
	 * 
	 * @param currState {@link State}
	 * @param neighbor {@link State}
	 * @return move cost from state to state
	 */
	public <T> double getMoveCost(State<T> currState, State<T> neighbor); 
	

}
