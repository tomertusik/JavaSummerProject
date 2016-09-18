package model;

import java.io.File;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * Interface for model types
 * @author Tomer, Gilad
 *
 */
public interface Model {
	/**
	 * Generate a new maze 
	 * @param name
	 * @param floors
	 * @param rows
	 * @param cols
	 */
	void generateMaze(String name,int floors, int rows, int cols);
	/**
	 * Get an existing maze by name
	 * @param name
	 * @return Maze3D
	 */
	Maze3D getMaze(String name);

	/**
	 * Save an existing maze to a file
	 * @param maze
	 * @param name
	 * @param fileName
	 * @throws Exception
	 */
	void SaveMaze(Maze3D maze,String name, String fileName) throws Exception;
	/**
	 * Load a maze from a file
	 * @param name
	 * @param fileName
	 * @throws Exception
	 */
	void LoadMaze(String name, String fileName) throws Exception;
	/**
	 * Solve an existing maze and save the solution
	 * @param name
	 * @param maze
	 * @param algorithm
	 */
	void SolveMaze(String name, Maze3D maze, String algorithm);
	/**
	 * Get the solution of a maze by the maze name
	 * @param name
	 * @return Solution<Position>
	 */
	Solution<Position> getSolutionsByName(String name);
	/**
	 * Exit the program safely and close all threads
	 */
	void exit();
	
	/**
	 * Get the files names
	 * @param path
	 * @return
	 */
	public File[] getFilesList(String path);
}
