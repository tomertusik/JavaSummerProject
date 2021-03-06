package view;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/**
 * Basic view window that runs the program on screen
 * @author Tomer, Gilad
 *
 */
public abstract class BasicWindow extends Observable implements Runnable {

	protected Display display;
	protected Shell shell;
	
	/**
	 * Add all the screen displays and buttons and a game board
	 */
	protected abstract void initWidgets();
	
	@Override
	public void run() {
		display = new Display();  // our display
		shell = new Shell(display); // our window

		initWidgets();
		
		shell.open();
				
		zoom();

		// main event loop
		while(!shell.isDisposed()){ // while window isn't closed
		
		   // 1. read events, put then in a queue.
		   // 2. dispatch the assigned listener
		   if(!display.readAndDispatch()){ 	// if the queue is empty
		      display.sleep(); 			// sleep until an event occurs 
		   }
		
		} // shell is disposed

		display.dispose(); // dispose OS components
	}

	/**
	 * Zoom in and out with the mouse wheel
	 */
	protected abstract void zoom();
}