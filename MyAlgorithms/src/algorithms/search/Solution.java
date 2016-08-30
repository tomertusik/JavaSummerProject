package algorithms.search;

import java.util.ArrayList;

/**
 * Solution for the game
 * @author Tomer, Gilad
 *
 * @param <T>
 */
public class Solution<T> {
	
	private ArrayList<State<T>> statesList = new ArrayList<State<T>>();  // list of states to represent solution

	/**
	 * @return the states list
	 */
	public ArrayList<State<T>> getStatesList() { 
		return statesList;
	}

	/**
	 * set the states list
	 * @param statesList
	 */
	public void setStatesList(ArrayList<State<T>> statesList) {
		this.statesList = statesList;
	}

	@Override
	public String toString() { // prints the solution
		StringBuilder sb = new StringBuilder();
		for(State<T> s :statesList){
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
	
	
	
	

}
