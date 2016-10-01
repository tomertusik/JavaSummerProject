package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/**
 * View for the 3D maze game
 * @author Tomer, Gilad
 *
 */
public class MyView extends Observable implements View, Observer {
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;

	/**
	 * CTOR
	 * @param in
	 * @param out
	 */
	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
				
		cli = new CLI(in, out);
		cli.addObserver(this);
	}
	
	@Override
	public void start(){
		try {
			cli.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void displayMaze(Maze3D maze) {
		out.println(maze);
		out.flush();
	}

	@Override
	public void displayCrossSection(Maze3D maze, String axis, int index) {
		if (axis.equals("Z"))
			maze.Print2DArray(maze.getCrossSectionByZ(index));
		else if (axis.equals("Y"))
			maze.Print2DArray(maze.getCrossSectionByY(index));
		else if (axis.equals("X"))
			maze.Print2DArray(maze.getCrossSectionByX(index));
		}

	@Override
	public void displaySolution(Solution<Position> _currentSolution) {
		out.println(_currentSolution);
	}

	@Override
	public void displayFiles(File[] files) {
		for(int i=0;i<files.length;i++) {
			if(files[i].isFile()) {
				System.out.println("File: " + files[i].getName());
			} else if(files[i].isDirectory()) {
				System.out.println("Directory: " + files[i].getName());
			}
		}
	}

	@Override
	public void displayMessage(String msg) {
		out.println("**"+msg);
		out.flush();		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == cli) {
			setChanged();
			notifyObservers(arg);
		}
	}

	@Override
	public void exit() {
		displayMessage("Thank you for using our program ! ");
	}

	@Override
	public void mazeReady(String name) {
		String msg = "maze " + name + " is ready";
		displayMessage(msg);
	}

	@Override
	public void solutionReady(String name) {
		String msg = "solution for maze " + name + " is ready";
		displayMessage(msg);
	}

	@Override
	public void solutionExist(String name) {
		String msg = "solution for maze " + name + " already exist";
		displayMessage(msg);
	    setChanged();
		notifyObservers("solution_ready " + name);
	}		
	}


