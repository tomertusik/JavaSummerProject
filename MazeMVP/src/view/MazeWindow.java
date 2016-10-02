package view;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
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
import algorithms.search.State;

/**
 * Game window of the maze
 * @author Tomer, gilad
 *
 */
public class MazeWindow extends BasicWindow implements View {
	
	private MazeDisplay _mazeDisplay;
	private String mazeName;
	private Maze3D maze;
	private Image characterImg;

	@Override
	public void start() {
		run();
	}
	
	@Override
	public void displayMaze(Maze3D maze) {
		shell.setBackground(new Color(null, 153, 0, 0));
		if(_mazeDisplay != null)
			_mazeDisplay.dispose();
		this.maze = maze;
		_mazeDisplay = new MazeDisplay(shell, SWT.DOUBLE_BUFFERED, maze,this,characterImg);
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
				Shell shell = new Shell();
				shell.setText("Character Screen");
				shell.setSize(600, 600);
				shell.setLayout(new GridLayout(1,false));
				Label lblName = new Label(shell, SWT.NONE);
				lblName.setText("Please choose your character ");
				Composite btnGroup = new Composite(shell, SWT.BORDER);
				btnGroup.setLayout( new GridLayout(3,false));
				btnGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				
				Button ironMan = new Button(btnGroup, SWT.PUSH);
				ironMan.setImage(new Image(null, "images/ironman.png"));
				ironMan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				ironMan.setText("Iron Man");
				ironMan.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = ironMan.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {						
					}
				});
				
				Button hulk = new Button(btnGroup, SWT.PUSH);
				hulk.setImage(new Image(null, "images/hulk.png"));
				hulk.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				hulk.setText("The Hulk");
				hulk.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = hulk.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
				
				Button widow = new Button(btnGroup, SWT.PUSH);
				widow.setImage(new Image(null, "images/widow.png"));
				widow.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				widow.setText("Black Widow");
				widow.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = widow.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
				
				Button spider = new Button(btnGroup, SWT.PUSH);
				spider.setImage(new Image(null, "images/spiderman.png"));
				spider.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				spider.setText("Spider Man");
				spider.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = spider.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
				
				Button captain = new Button(btnGroup, SWT.PUSH);
				captain.setImage(new Image(null, "images/captain.png"));
				captain.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				captain.setText("Captain America");
				captain.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = captain.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
				
				Button antman = new Button(btnGroup, SWT.PUSH);
				antman.setImage(new Image(null, "images/antman.png"));
				antman.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				antman.setText("Ant Man");
				antman.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = antman.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
				
				Button hawkeye = new Button(btnGroup, SWT.PUSH);
				hawkeye.setImage(new Image(null, "images/hawkeye.png"));
				hawkeye.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				hawkeye.setText("Hawk Eye");
				hawkeye.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = hawkeye.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
				
				Button nickfury = new Button(btnGroup, SWT.PUSH);
				nickfury.setImage(new Image(null, "images/nickfury.png"));
				nickfury.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				nickfury.setText("Nick Fury");
				nickfury.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = nickfury.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
				
				Button thor = new Button(btnGroup, SWT.PUSH);
				thor.setImage(new Image(null, "images/thor.png"));
				thor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				thor.setText("Thor");
				thor.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						characterImg = thor.getImage();
						showGenerateMazeOptions();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
				
				shell.open();
				
//			   showGenerateMazeOptions();
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
		setChanged();
		btnClue.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("clue " + getMazeName());				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		_mazeDisplay = new MazeDisplay(shell,SWT.DOUBLE_BUFFERED,null,this,null);
		_mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	/**
	 * Shows the user the options for generating maze
	 */
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
				shell.setSize(800, 500);
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

	public String getMazeName() {
		return mazeName;
	}

	@Override
	public void ClueMove(Solution<Position> sol) {
		_mazeDisplay.ClueDisplay(sol);
	}


	
	

}