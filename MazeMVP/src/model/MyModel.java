package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.StrategyDFS;
import algorithms.search.BFSsearch;
import algorithms.search.DFSsearch;
import algorithms.search.SearchAdapter;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
/**
 * Model fo the 3D maze game
 * @author Tomer, Gilad
 *
 */
public class MyModel extends Observable implements Model {
	
	private ExecutorService executor;	
	private Map<String, Maze3D> mazes = new ConcurrentHashMap<String, Maze3D>();
	private Map<Maze3D, Solution<Position>> solutions = new ConcurrentHashMap<Maze3D, Solution<Position>>();
	
	/**
	 * CTOR
	 */
	public MyModel() {
		executor = Executors.newFixedThreadPool(50);
		loadSolutions();
	}	

	@Override
	public void generateMaze(String name,int floors, int rows, int cols) {
		executor.submit(new Callable<Maze3D>() {

			@Override
			public Maze3D call() throws Exception {
				GrowingTreeGenerator generator = new GrowingTreeGenerator(new StrategyDFS());
				Maze3D maze = generator.generate(floors,rows, cols);
				mazes.put(name, maze);
				
				setChanged();
				notifyObservers("maze_ready " + name);		
				return maze;
			}
			
		});	
	}

	
	@Override
	public Maze3D getMaze(String name) {
		return mazes.get(name);
	}

	@Override
	public void SaveMaze(Maze3D maze,String name, String fileName) throws Exception {
		OutputStream out;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			((MyCompressorOutputStream) out).writeSize(name.getBytes().length);
			out.write(name.getBytes());
			((MyCompressorOutputStream) out).writeSize(maze.toByteArray().length);
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		} catch (Exception e) {
			extracted();
		}
		setChanged();
		notifyObservers("maze_saved " + name);		
	}


	@Override
	public void LoadMaze(String fileName, String name) throws Exception {
		InputStream in;
			in = new MyDecompressorInputStream(new FileInputStream(fileName));
			int nameSize=((MyDecompressorInputStream) in).readSize();
			byte nameArr[] = new byte[nameSize];
			in.read(nameArr);
			int size = ((MyDecompressorInputStream) in).readSize();
			byte b[]=new byte[size];
			in.read(b);
			in.close();
			Maze3D loaded=new Maze3D(b);
			mazes.put(name, loaded);
			setChanged();
			notifyObservers("maze_loaded " + name);
	}


	/**
	 * Throw new exception
	 * @throws Exception
	 */
	private void extracted() throws Exception {
		throw new Exception("Invalid name or file name");
	}


	@Override
	public void SolveMaze(String name, Maze3D maze, String algorithm) {
		if(getSolutionsByName(name) != null){
			setChanged();
		    notifyObservers("solution_exist " + name);
		}
		else{
		executor.submit(new Callable<Solution<Position>>() {

			@Override
			public Solution<Position> call() throws Exception {
				SearchAdapter sa = new SearchAdapter(maze);
				Solution<Position> sol=null;
				Searcher<Position> searchy = null;
				
				if(algorithm.equals("BFS")){
					 searchy = new BFSsearch<Position>(); // create BFS searcher
				}
				else if(algorithm.equals("DFS")){
					 searchy = new DFSsearch<Position>();// create DFS searcher
				}
				
				sol = searchy.Search(sa); // run the search method to get a solution
				solutions.put(maze, sol);
				
				setChanged();
				notifyObservers("solution_ready " + name);
				
				return sol;
			}
		});
		}
	}

	public  Solution<Position> getSolutionsByName(String soultionName) {
		Maze3D maze = mazes.get(soultionName);
		if(maze == null){
			return null;
		}
		for(Maze3D maze1 : solutions.keySet()){
			if(maze1.equals(maze))
				return solutions.get(maze1);
		}
		return null;
	}


	@Override
	public void exit() {
		saveSolutions();
		executor.shutdownNow();
	}

	@Override
	public File[] getFilesList(String path) {
		return (new File(path).listFiles());
	}
	
	/**
	 * Save the solutions list into gzip file
	 */
	private void saveSolutions() {
		ObjectOutputStream out=null;
		try {
			out = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("Solutions.dat")));
			out.writeObject(solutions);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	
	/**
	 * Load the solutions list from gzip file
	 */
	private void loadSolutions() {
		File file = new File("Solutions.dat");
		if (!file.exists())
			return;
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream("Solutions.dat")));
			solutions = (Map<Maze3D, Solution<Position>>) ois.readObject();		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	

}
