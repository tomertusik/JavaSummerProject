package presenter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import view.View;

import model.Model;
/**
 * Manage all the commands in the program
 * @author Tomer, Gilad
 *
 */
public class CommandsManager {
	
	private Model model;
	private View view;
	private HashMap<String, Command> commands;
	
	/**
	 * CTOR
	 * @param model
	 * @param view
	 */
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;	
		this.commands = new HashMap<String, Command>();
	}
	
	/**
	 * Method to get all the commands for the program
	 * @return HashMap<String, Command>
	 */
	public HashMap<String, Command> getCommandsMap(){
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("maze_ready", new MazeReadyCommand());
		commands.put("maze_saved", new MazeSavedCommand());
		commands.put("maze_loaded", new MazeLoadedCommand());
		commands.put("solution_ready", new SolutionReadyCommand());
		commands.put("solution_exist", new SolutionExistCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("display_cross_section", new DisplayCrossSection());
		commands.put("save_maze", new SaveMaze());
		commands.put("load_maze", new LoadMaze());
		commands.put("solve", new SolveMaze());
		commands.put("display_solution", new DisplaySolution());
		commands.put("exit", new Exit());
		commands.put("dir", new Dir());
		commands.put("change_properties", new ChangePropertiesCommand());
		commands.put("change_properties_notify_command", new ChangePropertiesNotifyCommand());
		commands.put("display_properties", new DisplayPropertiesCommand());
		commands.put("clue", new ClueCommand());
		commands.put("switch_xml", new SwitchXMLCommand());		
		
		return commands;
	}
	
	/**
	 * Command to generate a new maze
	 * @author Tomer, Gilad
	 *
	 */
	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) throws Exception {
			int floors = 0,rows = 0,cols = 0;
			if(args.length!=2){
				throw new  Exception("Invalid number of parameters");
			}	
			
			String name = args[0];
			String[] nums = args[1].split(",");
			if(nums.length!=3){
				throw new  Exception("Invalid maze settings (floors, rows, columns)");
			}
			try {
				 floors = Integer.parseInt(nums[0]);
				 rows = Integer.parseInt(nums[1]);
				 cols = Integer.parseInt(nums[2]);
			} catch (Exception e) {
				throw new  Exception("Invalid maze settings (floors, rows, columns)");
			}
			
			model.generateMaze(name,floors, rows, cols);
		}	
	}
	
	/**
	 * Command to display an existing maze
	 * @author Tomer, Gilad
	 *
	 */
	public class DisplayMazeCommand implements Command  {

		@Override
		public void doCommand(String[] args) throws Exception {
			String name = args[0];
			Maze3D maze = model.getMaze(name);
			if(maze==null){
				throw new  Exception("Invalid maze name");
			}
			view.displayMaze(maze);
		}
		
	}
	
	/**
	 * Command to display cross section by axis of an existing maze
	 * @author Tomer, Gilad
	 *
	 */
	public class DisplayCrossSection implements Command{

		@Override
		public void doCommand(String[] args) throws Exception{
			int index=0;
			if(args.length!=3){
				throw new  Exception("Invalid cross section parameters (name, axis, index)");
			}
			String name = args[0];
			Maze3D maze = model.getMaze(name);
			if(maze==null){
				throw new  Exception("Invalid maze name");
			}
			String axis = args[1];
			if(!axis.equals("Z")&&!axis.equals("X")&&!axis.equals("Y")){
				throw new  Exception("Invalid axis name");
			}
			try {
				index = Integer.parseInt(args[2]);
				
			} catch (Exception e) {
				throw new  Exception("Invalid index settings");
			}
			
			try {
				view.displayCrossSection(maze,axis,index);
			} catch (Exception e) {
				throw new  Exception("Invalid index settings");
			}
			
		}
		
	}
	
	/**
	 * Command to save an existing maze to a file
	 * @author Tomer, Gilad
	 *
	 */
	public class SaveMaze implements Command{

		@Override
		public void doCommand(String[] args) throws Exception {
			if(args.length!=2){
				throw new  Exception("Invalid file name or name");
			}
			String name = args[0];
			Maze3D maze = model.getMaze(name);
			if(maze==null){
				throw new  Exception("Invalid maze name");
			}
			String fileName = args[1];
			
			try {
				model.SaveMaze( maze,name,  fileName);
			} catch (Exception e) {
				throw new  Exception(e.getMessage());
			}
		}
		
	}
	
	/**
	 * Command to load an existing maze from a file
	 * @author Tomer, Gilad
	 *
	 */
	public class LoadMaze implements Command{

		@Override
		public void doCommand(String[] args) throws Exception {
			if(args.length!=2){
				throw new  Exception("Invalid file name or name");
			}
			String fileName = args[0];
			
			String name = args[1];
//			Maze3D maze = model.getMaze(name);
//			if(maze!=null){
//				throw new  Exception("Invalid maze already exsist");
//			}
			
			try {
				model.LoadMaze(fileName,name);
			} catch (Exception e) {
				throw new  Exception(e.getMessage());
			}
		
		}
		
	}
	
	/**
	 * Command to solve and existing maze and save the solution
	 * @author Tomer, Gilad
	 *
	 */
	public class SolveMaze implements Command{

		@Override
		public void doCommand(String[] args) throws Exception {
			if(args.length!=1){
				throw new  Exception("Invalid name or algorithm");
			}
			String name = args[0];
			Maze3D maze = model.getMaze(name);
			if(maze==null){
				throw new  Exception("Invalid maze name");
			}
			try {
				model.SolveMaze(name,maze);
			} catch (Exception e) {
				throw new  Exception("Error accured,try again");
			}
			
		}
		
	}
	
	/**
	 * Command to display solution for an existing maze that have been solved by Solve command
	 * @author Tomer, Gilad
	 *
	 */
	public class DisplaySolution implements Command{

		@Override
		public void doCommand(String[] args) throws Exception {
			String name = args[0];
			Solution<Position> _currentSolution= model.getSolutionsByName(name);
			if(_currentSolution == null){
				throw new  Exception("Invalid solution name or maze not solved yet");
			}
			view.displaySolution(_currentSolution);
		}
		}
	
	/**
	 * Command to exit the program safely and close all threads
	 * @author Tomer, Gilad
	 *
	 */
	public class Exit implements Command{

		@Override
		public void doCommand(String[] args) throws Exception {
			view.exit();
			model.exit();
		}
		
	}
	
	/**
	 * Command to present all the files in the path directory
	 * @author Tomer, Gilad
	 *
	 */
	public class Dir implements Command{

		@Override
		public void doCommand(String[] args) throws Exception {
			File[] _files=model.getFilesList(args[0]);
			view.displayFiles(_files);
		}
		
	}
	
	/**
	 * Notify the user on screen that the maze requested is ready
	 * @author Tomer, Gilad
	 *
	 */ 
	class MazeReadyCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
		    view.mazeReady(name);
		}
		
	}
	
	/**
	 * Notify the user on screen that the maze requested is saved to file
	 * @author Tomer, Gilad
	 *
	 */
	class MazeSavedCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String msg = "maze " + name + " is saved to file";
		view.displayMessage(msg);
		}
		
	}
	
	/**
	 * Notify the user on screen that the maze requested is loaded from file
	 * @author Tomer, Gilad
	 *
	 */
	class MazeLoadedCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			view.MazeLoaded(name);
			}
		
	}
	
	/**
	 * Notify the user on screen that the maze requested is solved
	 * @author Tomer, Gilad
	 *
	 */
	class SolutionReadyCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
		    view.solutionReady(name);
		}
		
	}
	
	/**
	 * Notify the user on screen that the maze requested is solved already
	 * @author Tomer, Gilad
	 *
	 */
	class SolutionExistCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
		    view.solutionExist(name);
		}
		
	}
	
	/**
	 * Change the properties of the program
	 * @author Tomer, Gilad
	 *
	 */
	class ChangePropertiesCommand implements Command {

		@Override
		public void doCommand(String[] args) throws Exception {
			if(args.length!=2){
				throw new  Exception("Invalid property or algorithm");
			}
			String name = args[0];
			String value = args[1];
			try {
				model.changeProperties(name,value);
			} catch (IOException e) {
				view.displayMessage(e.getMessage());
			}
		}
		
	}
	
	/**
	 * Notify the user on screen that the properties had been changed
	 * @author Tomer
	 *
	 */
	class ChangePropertiesNotifyCommand implements Command {

		@Override
		public void doCommand(String[] args) throws Exception {
			String name = args[0];
			String msg = "The properties " + name + " has changed";
		    view.displayMessage(msg);
		}
	
	}
	
	/**
	 * Display the properties on screen
	 * @author Tomer, Gilad
	 *
	 */
	class DisplayPropertiesCommand implements Command {

		@Override
		public void doCommand(String[] args) throws Exception {
		presenter.Properties p= model.getProperties();
		String msg="Properties :" ;
		view.displayMessage(msg);
		view.displayMessage("Generate Maze Algorithm: "+p.getGenerateMazeAlgorithm());
		view.displayMessage("Solve Maze Algorithm: "+p.getSolveMazeAlgorithm());
		}
	
	}
	
	/**
	 * Display a clue to the user
	 * @author Tomer, Gilad
	 *
	 */
	public class ClueCommand implements Command{

		@Override
		public void doCommand(String[] args) throws Exception {
			if(args.length!=1){
				throw new  Exception("Invalid name or algorithm");
			}
			String name = args[0];
			Maze3D maze = model.getMaze(name);
			if(maze==null){
				throw new  Exception("Invalid maze name");
			}
			view.ClueMove();
		}
		
	}
	
	
	/**
	 * Replace the XML with a new XML file
	 * @author Tomer
	 *
	 */
	public class SwitchXMLCommand implements Command{

		@Override
		public void doCommand(String[] args) throws Exception {
			if (args.length != 1) {
				throw new Exception("Invalid arguments");
			}
			String file = args[0];
			PropertiesLoader xmLoader = new PropertiesLoader();
			xmLoader.setProperties(file);
			model.setProperties(xmLoader.getProperties());
		}
		
	}

}
