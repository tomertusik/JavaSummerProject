package presenter;
/**
 * Interface for commands type in the controller
 * @author Tomer, Gilad
 *
 */
public interface Command {
	/**
	 * Do the command needed
	 * @param args
	 * @throws Exception
	 */
	public void doCommand(String[] args) throws Exception;
}
