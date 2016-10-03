package view;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * Game window of the maze
 * @author Tomer, gilad
 *
 */
public class MazeWindow extends BasicWindow implements View {
	
	private MazeDisplay _mazeDisplay;
	private String mazeName;
	@SuppressWarnings("unused")
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
		
		// Set the shell graphics and buttons group
		shell.setLayout(new GridLayout(1, false));	
		shell.setText("Avengers Maze Game");
		shell.setSize(800,500);
		shell.setBackground(new Color(null, 0, 51, 102));
		shell.setImage(new Image(null, "images/goal.png"));
		Composite btnGroup = new Composite(shell, SWT.BORDER);
		btnGroup.setBackground(new Color(null, 0, 51, 102));
		btnGroup.setLayout( new RowLayout(SWT.HORIZONTAL));
		
		// generate maze button
		Button btnGenerateMaze = new Button(btnGroup, SWT.PUSH);
		btnGenerateMaze.setText("Generate maze");	
		
        btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//set the generate button graphics and set the characters selection
				Shell shell = new Shell();
				shell.setText("Character Screen");
				shell.setSize(600, 600);
				shell.setLayout(new GridLayout(1,false));
				Label lblName = new Label(shell, SWT.NONE);
				lblName.setText("Please choose your character ");
				Composite btnGroup = new Composite(shell, SWT.BORDER);
				btnGroup.setLayout( new GridLayout(3,false));
				btnGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				
				//iron man butto
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
				
				// hulk button
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
				
				// black widow button
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
				
				// spider man button
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
				
				// captain america button
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
				
				// ant man button
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
				
				// hawk eye button
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
				
				// nick fury button
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
				
				// thor button
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
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});
        
        // solve maze button
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
		
		// clue button
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
		
		// change properties to new xml file button
		Button btnProperties = new Button(btnGroup, SWT.PUSH);
		btnProperties.setText("Open New Properties");
		btnProperties.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog propertiesDialog = new FileDialog(shell, SWT.SAVE);
				propertiesDialog.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
				propertiesDialog.setFilterExtensions(new String[] { "*.xml", "*.*" });
				propertiesDialog.setFilterPath("c:\\");
				propertiesDialog.open();
				setChanged();
				notifyObservers("switch_xml " + propertiesDialog.getFilterPath() + "\\" + propertiesDialog.getFileName());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		// change properties of the game by the user itself
		Button btnChangeProperties = new Button(btnGroup, SWT.PUSH);
		btnChangeProperties.setText("Change Properties");
		btnChangeProperties.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell shell = new Shell();
				shell.setText("Properties");
				shell.setSize(200, 200);
				shell.setLayout(new GridLayout(1,false));
				Label lblName = new Label(shell, SWT.NONE);
				lblName.setText("Properties option:");
				Composite btnGroup = new Composite(shell, SWT.BORDER);
				btnGroup.setLayout( new GridLayout(1,false));
				btnGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				
				// change the generate maze algorithm
				Button btnGenerateAlgo = new Button(btnGroup, SWT.PUSH);
				btnGenerateAlgo.setText("Maze Generate Algorithm");
				btnGenerateAlgo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				btnGenerateAlgo.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						Shell generateshell = new Shell();
						generateshell.setText("Generate maze algorithm");
						generateshell.setSize(400, 400);
						generateshell.setLayout(new GridLayout(1,false));
						Label lblName = new Label(generateshell, SWT.NONE);
						lblName.setText("Please selecet your algorithm:");
						Composite btnGroup = new Composite(generateshell, SWT.BORDER);
						btnGroup.setLayout( new GridLayout(1,false));
						btnGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						
						// Choose the DFS algorithm
						Button dfs = new Button(btnGroup, SWT.PUSH);
						dfs.setText("DFS- generate a long path maze");
						dfs.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						dfs.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent arg0) {
								setChanged();
								notifyObservers("change_properties ChangeGenerate DFS");
								generateshell.close();
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent arg0) {								
							}
						});
						
						// Choose the RDC algorithm
						Button rdc = new Button(btnGroup, SWT.PUSH);
						rdc.setText("RDC- generate a maze from a random cell");
						rdc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						rdc.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent arg0) {
								setChanged();
								notifyObservers("change_properties ChangeGenerate RDC");
								generateshell.close();
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent arg0) {								
							}
						});
						
						// Choose the simple maze algorithm
						Button simple = new Button(btnGroup, SWT.PUSH);
						simple.setText("Simple- generate a simple maze");
						simple.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						simple.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent arg0) {
								setChanged();
								notifyObservers("change_properties ChangeGenerate SimpleMaze");
								generateshell.close();
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent arg0) {								
							}
						});
						
						generateshell.open();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {						
					}
				});
				
				// choose the solve maze algorithm
				Button btnSolveAlgo = new Button(btnGroup, SWT.PUSH);
				btnSolveAlgo.setText("Maze Solve Algorithm");
				btnSolveAlgo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				btnSolveAlgo.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						Shell solveshell = new Shell();
						solveshell.setText("Solve maze algorithm");
						solveshell.setSize(350, 300);
						solveshell.setLayout(new GridLayout(1,false));
						Label lblName = new Label(solveshell, SWT.NONE);
						lblName.setText("Please selecet your algorithm:");
						Composite btnGroup = new Composite(solveshell, SWT.BORDER);
						btnGroup.setLayout( new GridLayout(1,false));
						btnGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						
						// Choose the DFS algorithm
						Button dfs = new Button(btnGroup, SWT.PUSH);
						dfs.setText("DFS- chooses one path until hits a wall");
						dfs.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						dfs.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent arg0) {
								setChanged();
								notifyObservers("change_properties ChangeSolve DFS");
								solveshell.close();
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent arg0) {								
							}
						});
						
						// Choose the DFS algorithm
						Button bfs = new Button(btnGroup, SWT.PUSH);
						bfs.setText("BFS- go to all directions and return the shortest path");
						bfs.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						bfs.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent arg0) {
								setChanged();
								notifyObservers("change_properties ChangeSolve BFS");
								solveshell.close();
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent arg0) {								
							}
						});
						
						solveshell.open();
						shell.close();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {						
					}
				});
				
				
				shell.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
		});
		
		// display the game graphics
		_mazeDisplay = new MazeDisplay(shell,SWT.DOUBLE_BUFFERED,null,this,null);
		_mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	/**
	 * Shows the user the options for generating maze
	 */
	protected void showGenerateMazeOptions(){
		Shell shell = new Shell();
		shell.setText("Generate Maze");
		shell.setSize(300, 150);
		
		shell.setLayout(new GridLayout(4, false));
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Maze Name: ");
		lblName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Label lblFloors = new Label(shell, SWT.NONE);
		lblFloors.setText("Floors: ");
		Text txtFloors = new Text(shell, SWT.BORDER);
		lblFloors.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		txtFloors.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setText("Rows: ");
		Text txtRows = new Text(shell, SWT.BORDER);
		lblRows.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Label lblCols = new Label(shell, SWT.NONE);
		lblCols.setText("Cols: ");
		Text txtCols = new Text(shell, SWT.BORDER);
		lblCols.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		txtCols.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Button btnGenerate = new Button(shell, SWT.PUSH);
		btnGenerate.setText("Generate");
		btnGenerate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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

	@Override
	protected void zoom() {
		//  5 Points bonus 
		shell.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseScrolled(MouseEvent e) {
				int wheelNumber = e.count;
				if ((e.stateMask & SWT.CONTROL) == SWT.CONTROL) {
					if ((wheelNumber < 0 && shell.getSize().x > 200 && shell.getSize().y > 200) || (wheelNumber > 0 && shell.getSize().x < 2000 && shell.getSize().y < 2000)) {
						shell.setSize(shell.getSize().x + wheelNumber, shell.getSize().y + wheelNumber);
					}
				}
			}
		});

	}

	
	

}