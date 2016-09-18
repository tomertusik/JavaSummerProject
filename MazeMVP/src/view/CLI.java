package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Observable;
/**
 * Command line, works with the view, run the print menu and options for the user
 * @author Tomer, Gilad
 *
 */
public class CLI extends Observable {
	
	private BufferedReader in;
	private PrintWriter out;
	
	/**
	 * CTOR
	 * @param in
	 * @param out
	 */
	public CLI(Reader in, Writer out){
		this.in = new BufferedReader(in);
		this.out = new PrintWriter(out);
	}
	
	/**
	 * Prints the possible commands for the user
	 */
	private void printMenu() {
		out.println("\nPlease choose command:");
		out.flush();
	}
	
	/**
	 * Starts the program, runs the instructions + commands available
	 * @throws IOException
	 */
	public void start() throws IOException{
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				
				out.println("Instructions for commands:");
				out.println("1.tap exit in order to exit the program");
				out.println("2.generate_maze - tap the command <name> <floors,rows,columns> -> for example: generate_maze Mazey 3,5,7");
				out.println("3.display - tap the command <name> -> for example: display Mazey");
				out.println("4.display_cross_section - tap the command <name> <axis(X/Y/Z)> <index> -> example: display_cross_section Mazey X 5");
				out.println("5.save_maze- tap the command <name> <file name> -> example: save_maze Mazey test");
				out.println("6.load_maze- tap the command <file name> <name> -> example: load_maze test Mazey");
				out.println("7.solve - tap the command solve <name> <algorithm(DFS/ BFS)> -> example: solve Mazey DFS");
				out.println("8.display_solution - tap the command <name> -> example: display_solution Mazey ");
				out.println("9.dir - tap the command <path> -> example: dir D:/JavaProjects/MazeMVC/src/view");
			
				while (true) {
					
					printMenu();
					try {
						String commandLine = in.readLine();
						setChanged();
						notifyObservers(commandLine);
						
						if (commandLine.equals("exit"))
							break;
						
					} catch (IOException e) {
						out.println(e.getMessage());
					}
				}
			}			
		});
		thread.start();		
	}
}

