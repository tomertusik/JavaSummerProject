package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/*
 * 1. BFS is an algorithm that will find the shortest path from the start position to the end position because it updates the list
 * if the algorithm finds a shorter path from the old one, however it developes alot of directions in he same time so it evaluate alot of nodes
 * DFS is an algorithm that try one route until it reaches a dead end,then it returns to the last possible cross-roads.  it would usually take
 * less nodes to reach the goal state.
 * 
 * 2. we have chosen Generics to create BFS because that way we do not need to perform casting to position every time, and if tomorrow 
 * we would like to add a new game we could easily make it search-able with an adapter
 */

/**
 * Best First Search algorithm
 * @author Tomer, Gilad
 *
 * @param <T>
 */
public class BFSsearch<T> extends CommonSearcher<T> {
	
	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>(); // open list for attending states
	private HashSet<State<T>> closeList = new HashSet<State<T>>(); // closed list for attended states
	

	
	@Override
	/**
	 * Method to find solution for a search-able type using best first search
	 */
	public Solution<T> Search(Searchable s) { // method to find solution for a search-able type using best first search
		State<T> startState = s.getStartState();
		openList.add(startState);       // define the start state
		
		while(!openList.isEmpty()){
			State<T> currState = openList.poll(); // move state from open list to closed list
			closeList.add(currState);
			if(currState.equals(s.getGoalState())){ // if the state equals the goal state back trace the path
				return backTrace(currState);
			}
			ArrayList<State<T>> neighbors = s.getAllPossibleStates(currState); // get the possible moves for each neighbor
			for(State<T> neighbor : neighbors){
				if(!openList.contains(neighbor)&&!closeList.contains(neighbor)){
					neighbor.setCameFrom(currState);     // if the neighbor is not in open and not in close add it to open
					neighbor.setCost(currState.getCost() + s.getMoveCost(currState, neighbor));
					openList.add(neighbor);
					evaluatedNodes++; // add a node when going to a new state
				}
				else {
					double newPathCost = currState.getCost() + s.getMoveCost(currState, neighbor); // calculate new path
					if(neighbor.getCost() > newPathCost){ // if the new path is better
						neighbor.setCost(newPathCost);
						neighbor.setCameFrom(currState);
						if(!openList.contains(neighbor))
							openList.add(neighbor);
							else{ // must notify the priority queue
								openList.remove(neighbor);
								openList.add(neighbor);
						}
					}
				}
			}
		}
		return null;
		}
		
	}


