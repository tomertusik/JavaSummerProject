package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Maze3DGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.StrategyDFS;
import algorithms.search.BFSsearch;
import algorithms.search.DFSsearch;
import algorithms.search.SearchAdapter;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * Test both search algorithms and writes and read from a file
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
		Maze3D maze=mg.generate(3,15,15);
		
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
	    
		// save it to a file
		try{
			OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
			((MyCompressorOutputStream) out).writeSize(maze.toByteArray().length);
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			
		// load from file
			InputStream in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
			int size = ((MyDecompressorInputStream) in).readSize();
			byte b[]=new byte[size];
			in.read(b);
			in.close();
			Maze3D loaded=new Maze3D(b);
			System.out.println(maze);
			System.out.println(loaded.equals(maze));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
	}
	}