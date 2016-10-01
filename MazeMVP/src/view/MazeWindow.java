package view;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MazeWindow extends BasicWindow implements View {
	
	private MazeDisplay _mazeDisplay;
	private String mazeName;

	@Override
	public void start() {
		run();
	}
	
	@Override
	public void displayMaze(Maze3D maze) {
		shell.setBackground(new Color(null, 153, 0, 0));
		if(_mazeDisplay != null)
			_mazeDisplay.dispose();
		_mazeDisplay = new MazeDisplay(shell, SWT.DOUBLE_BUFFERED, maze,this);
		_mazeDisplay.setMazeData(maze.getCrossSectionByZ(maze.getStartPosition().z));
		_mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
	}

	@Override
	public void displayCrossSection(Maze3D maze, String axis, int index) {
		// No need for that.
	}

	@Override
	public void displaySolution(Solution<Position> _currentSolution) {
		_mazeDisplay.printSolution(_currentSolution);		
	}

	@Override
	public void displayFiles(File[] _files) {		
	}

	@Override
	public void displayMessage(String msg) {
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox message = new MessageBox(shell);
				if(msg!=null){
				message.setMessage(msg);
				message.open();	
				}
					
			}
		});			
	}

	@Override
	public void exit() {		
	}

	@Override
	protected void initWidgets() {

		shell.setLayout(new GridLayout(1, false));	
		shell.setText("Avengers Maze Game");
		shell.setSize(800,500);
		shell.setBackground(new Color(null, 0, 51, 102));
		Composite btnGroup = new Composite(shell, SWT.BORDER);
		btnGroup.setLayout( new RowLayout(SWT.HORIZONTAL));
		
		Button btnGenerateMaze = new Button(btnGroup, SWT.PUSH);
		btnGenerateMaze.setText("Generate maze");	
		
        btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			   showGenerateMazeOptions();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});
        
        Button btnSolveMaze = new Button(btnGroup, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		btnSolveMaze.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					setChanged();
					notifyObservers("solve " + mazeName);				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {				
				}
			});
		
		Button btnClue = new Button(btnGroup, SWT.PUSH);
		btnClue.setText("Clue");
		btnClue.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});
		
		_mazeDisplay = new MazeDisplay(shell,SWT.DOUBLE_BUFFERED,null,this);
		_mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	protected void showGenerateMazeOptions(){
		Shell shell = new Shell();
		shell.setText("Generate Maze");
		shell.setSize(300, 200);
		
		shell.setLayout(new GridLayout(4, false));
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Maze Name: ");
		Text txtName = new Text(shell, SWT.BORDER);
		
		Label lblFloors = new Label(shell, SWT.NONE);
		lblFloors.setText("Floors: ");
		Text txtFloors = new Text(shell, SWT.BORDER);
		
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setText("Rows: ");
		Text txtRows = new Text(shell, SWT.BORDER);
		
		Label lblCols = new Label(shell, SWT.NONE);
		lblCols.setText("Cols: ");
		Text txtCols = new Text(shell, SWT.BORDER);
		
		Button btnGenerate = new Button(shell, SWT.PUSH);
		btnGenerate.setText("Generate");
		btnGenerate.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mazeName = txtName.getText();
				setChanged();
				notifyObservers("generate_maze " + txtName.getText() + " " + txtFloors.getText() + "," + txtRows.getText() + "," + txtCols.getText());
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});	
	
		
		shell.open();		
	}

	@Override
	public void mazeReady(String name) {
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				setChanged();
				notifyObservers("display " + name);
				
				_mazeDisplay.setVisible(true);
				_mazeDisplay.redraw();
				shell.setVisible(true);
				shell.redraw();
				shell.setSize(799,500);
				}
		});			
	}

	@Override
	public void solutionReady(String name) {
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				setChanged();
				notifyObservers("display_solution " + name);
				}
		});			
	}

	@Override
	public void solutionExist(String name) {
	    setChanged();
		notifyObservers("solution_ready " + name);
	}
	
	

}