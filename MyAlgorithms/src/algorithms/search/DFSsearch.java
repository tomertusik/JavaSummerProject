package algorithms.search;

import java.util.ArrayList;
import java.util.Random;

import algorithms.mazeGenerators.Position;

/**
 * Searcher with Depth First Search
 * @author Tomer, Gilad
 *
 * @param <T>
 */
public class DFSsearch<T> extends CommonSearcher<T> {
	
	private Random rand = new Random(); // create random number
	private ArrayList<State<T>> statesList = new ArrayList<State<T>>(); // list of solution stateT
	private ArrayList<State<T>> statesMade = new ArrayList<State<T>>(); // list of made states

	@Override
	public Solution<T> Search(Searchable s) { // method to find solution for a search-able type using depth first search
		State<T> startState = s.getStartState();
		statesList.add(startState); // add the start state to the list
		statesMade.add(startState);
		DFS(startState, s);   // recursively using depth first search
		Solution<T> sol = new Solution<T>();
		sol.setStatesList(statesList);   // set the solution
		return sol;
	}
	
	/**
	 * method to recursively use depth first search algorithm
	 * @param currState {@link Position}
	 * @param s {@link Searchable}
	 */
	private void DFS(State<T> currState, Searchable s){ 
		while(!statesList.isEmpty()){
		ArrayList<State<T>> moves = s.getAllPossibleStates(currState); // get the possible moves
		moves.remove(currState.getCameFrom()); // removes the states we have already been at
		moves.removeAll(statesMade);
	
		if (moves.size() > 0){ // if we got a direction to go to
			int randomNum = rand.nextInt(moves.size()); // random direction
			evaluatedNodes++; // add a node when going to a new state
			State<T> nextState = moves.get(randomNum); // the next state
			nextState.setCameFrom(currState); // set who it came from
			statesList.add(nextState); // add it to the list
			statesMade.add(nextState); 
			if (nextState.equals(s.getGoalState())) // if we made it to the goal state
				return;
			else
				currState = nextState;
		}
		else{ //if it is a dead end
		    statesList.remove(currState);
		    currState = statesList.get(statesList.size()-1);
		}
	}
	}
	}