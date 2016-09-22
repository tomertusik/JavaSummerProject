package view;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
	
	private MazeDisplay mazeDisplay;

	@Override
	public void start() {
		run();
	}

	@Override
	public void displayMaze(Maze3D maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSection(Maze3D maze, String axis, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution<Position> _currentSolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayFiles(File[] _files) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMessage(String msg) {
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox message = new MessageBox(shell);
				message.setMessage(msg);
				message.open();	
			}
		});			
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initWidgets() {
		shell.setLayout(new GridLayout(2, false));
		Composite btnGroup = new Composite(shell, SWT.BORDER);
		btnGroup.setLayout( new RowLayout(SWT.VERTICAL));
		
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
        
        Button btnDisplayMaze = new Button(btnGroup, SWT.PUSH);
        btnDisplayMaze.setText("Display maze");
        btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});
        
        Button btnSolveMaze = new Button(btnGroup, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
	}
	
	protected void showGenerateMazeOptions(){
		Shell shell = new Shell();
		shell.setText("Generate Maze");
		shell.setSize(300, 200);
		
		GridLayout layout = new GridLayout(4, false);
		shell.setLayout(layout);
		
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
				setChanged();
				notifyObservers("generate_maze " + txtName.getText() + " " + txtFloors.getText() + "," + txtRows.getText() + "," + txtCols.getText());
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});
		
		mazeDisplay = new MazeDisplay(shell, SWT.NONE);			
		shell.open();		
	}
	
	

}
