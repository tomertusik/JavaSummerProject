package view;

import java.io.File;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * Interface for view types
 * @author Tomer, Gilad
 *
 */
public interface View {
	/**
	 * Starts the program
	 */
	public void start();

	/**
	 * Display on screen the maze requested
	 * @param maze
	 */
	public void displayMaze(Maze3D maze);
	/**
	 * Set the commands available for the program
	 * @param commands
	 */

	public void displayCrossSection(Maze3D maze,String axis,int index);
	
	/**
	 * Display on screen solution for the maze requested (only if solved first)
	 * @param _currentSolution
	 */
	public void displaySolution(Solution<Position> _currentSolution);
	
	/**
	 * Display all the files in the directory path
	 * @param _files
	 */
	public void displayFiles(File[] _files);
	
	/**
	 * Display message to the user on screen
	 * @param msg
	 */
	public void displayMessage(String msg);

	/**
	 * Exit the program and print thank you to the user
	 */
	public void exit();

	/**
	 * Notify maze is ready
	 * @param name
	 */
	public void mazeReady(String name);

	/**
	 * Notify solve is ready
	 * @param name
	 */
	public void solutionReady(String name);

	/**
	 * Notify solution exists
	 * @param name
	 */
	public void solutionExist(String name);

	/**
	 * Gives the user a clue
	 * @param sol 
	 */
	public void ClueMove();

	/**
	 * Load a maze from file and display it on screen
	 * @param name
	 * @param maze 
	 */
	public void MazeLoaded(String name);
}