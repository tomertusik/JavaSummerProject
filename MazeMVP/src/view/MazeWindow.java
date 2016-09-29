package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWT.*;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
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
	
	private MazeDisplay _mazeDisplay;

	@Override
	public void start() {
		run();
	}

	@Override
	public void displayMaze(Maze3D maze) {
		Shell shell = new Shell();
		shell.setText("Maze");
		shell.setSize(500, 500);
		shell.setLayout(new GridLayout(1, false));

		_mazeDisplay = new MazeDisplay(shell, SWT.DOUBLE_BUFFERED, maze);
//		maze.getStartPosition().

		_mazeDisplay.setMazeData(maze.getCrossSectionByZ(maze.getStartPosition().z));
//		_mazeDisplay.setSize(600,600);
//		_mazeDisplay.setSize(new Dimension(600, 600));
//		_mazeDisplay.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
		_mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		shell.open();	
	}

	@Override
	public void displayCrossSection(Maze3D maze, String axis, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution<Position> _currentSolution) {
		_mazeDisplay.printSolution(_currentSolution);		
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
				if(msg!=null){
				message.setMessage(msg);
				message.open();	
				}
					
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
		shell.setText("Maze3D Game");
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
				showDisplayMazeOptions();
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
				showSolveMazeOption();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});
		
		
	}
	
	
	
	protected void showSolveMazeOption() {
		Shell shell = new Shell();
		shell.setText("Solve maze");
		shell.setSize(300, 200);
		shell.setLayout(new GridLayout(1, false));		
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Maze Name: ");
		Text txtName = new Text(shell, SWT.BORDER);
		
		Button btnDisplay = new Button(shell, SWT.PUSH);
		btnDisplay.setText("Display");
		btnDisplay.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("solve " + txtName.getText());
				setChanged();
				notifyObservers("display_solution " + txtName.getText());
				shell.close();		
				}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		shell.open();
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
	
	protected void showDisplayMazeOptions(){
		Shell shell = new Shell();
		
		shell.setText("Display maze");
		shell.setSize(300, 200);
		shell.setLayout(new GridLayout(1, false));
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Maze Name: ");
		Text txtName = new Text(shell, SWT.BORDER);
		
		Button btnDisplay = new Button(shell, SWT.PUSH);
		btnDisplay.setText("Display");
		btnDisplay.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("display " + txtName.getText());
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});
		
//		_mazeDisplay = new MazeDisplay(shell, SWT.NONE);
		shell.open();
	}
	
	

}