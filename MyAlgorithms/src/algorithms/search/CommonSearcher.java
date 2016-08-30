package algorithms.search;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;

/**
 * Common searcher 
 * @author Tomer, Gilad
 *
 * @param <T>
 */
public abstract class CommonSearcher<T> implements Searcher<T> {
	
	protected int evaluatedNodes;
	
	protected CommonSearcher(){ //CTOR
		evaluatedNodes = 0;
	}
	
	@Override
	public int getNumOfNodesEvaluated(){ // get number of nodes
		return evaluatedNodes;
	}
	/**
	 * back trace the solution from end state to start state
	 * @param goalState {@link Position}
	 * @return {@link Solution}
	 */
	protected Solution<T> backTrace(State<T> goalState){ 
		Solution<T> sol = new Solution<T>();
		State<T> currState = goalState;
		ArrayList<State<T>> states = new ArrayList<State<T>>();
		while(currState != null){
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		sol.setStatesList(states);
		return sol;
	}

}
