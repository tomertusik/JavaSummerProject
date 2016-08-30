package algorithms.demo;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Maze3DGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFSsearch;
import algorithms.search.DFSsearch;
import algorithms.search.SearchAdapter;
import algorithms.search.Solution;

/**
 * Test both search algorithms
 * @author Tomer, Gilad
 *
 */
public class Demo { 

	/**
	 * Run the test
	 * @param mg {@link Maze3DGenerator}
	 */
	public void run(Maze3DGenerator mg) {
		// generate 3d maze
		Maze3D maze=mg.generate(3,45,45);
		
		// prints the maze
		System.out.println(maze);
		
		// adapt maze to search-able
		SearchAdapter sa = new SearchAdapter(maze);
		
		// Try the best first search algorithm
		BFSsearch<Position> searchy = new BFSsearch<Position>(); // create BFS searcher
		Solution<Position> sol = searchy.Search(sa); // run the search method to get a solution
		System.out.println("BFS solution:\n" + sol); // print the solution
		System.out.println("The number of nodes evaluated: " + searchy.getNumOfNodesEvaluated()+"\n"); // print the number of nodes evaluated
		
		
		// Try the depth first search algorithm
		DFSsearch<Position> searchy2 = new DFSsearch<Position>();// create DFS searcher
		Solution<Position> sol2 = searchy2.Search(sa);// run the search method to get a solution
		System.out.println("DFS solution:\n" + sol2);// print the solution
		System.out.println("The number of nodes evaluated: " + searchy2.getNumOfNodesEvaluated()+"\n");// print the number of nodes evaluated
	}

}
