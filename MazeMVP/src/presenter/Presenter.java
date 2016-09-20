package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

/**
 * Presenter for the 3D maze game
 * @author Tomer, Gilad
 *
 */
public class Presenter implements Observer {
	private Model model;
	private View view;
	private CommandsManager commandsManager;
	private HashMap<String, Command> commands;
	
	public Presenter(Model model, View view) {
		this.model = model;
		this.view = view;
			
		commandsManager = new CommandsManager(model, view);
		commands = commandsManager.getCommandsMap();
	}

	@Override
	public void update(Observable o, Object arg) {
		try{
		String commandLine = (String)arg;
		
		String arr[] = commandLine.split(" ");
		String command = arr[0];			
		
		if(!commands.containsKey(command)) {
			view.displayMessage("Command doesn't exist");			
		}
		else {
			String[] args = null;
			if (arr.length > 1) {
				String commandArgs = commandLine.substring(
						commandLine.indexOf(" ") + 1);
				args = commandArgs.split(" ");							
			}
			else if(arr.length == 1 && !arr[0].equals("exit") && !arr[0].equals("display_properties")){
				throw new Exception("Invalid command parameters");
			}
			Command cmd = commands.get(command);
			
				cmd.doCommand(args);
		}
		} catch (Exception e) {
			view.displayMessage(e.getMessage());
		}	
	}
}
