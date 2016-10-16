package view;

import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;


/**
 * Draw and display the maze on the game board
 * @author Tomer
 *
 */
public class MazeDisplay extends Canvas {
	
	MazeWindow view;
	Maze3D maze;
	int [][][] myMaze;
	Character character;
	int [][] mazeData;
	int currentZ;
	int clueLastZ;
	
/**
 * Set the maze data (2 dimension)
 * @param mazeData
 */
	public void setMazeData(int[][] mazeData) {
		this.mazeData = mazeData;
	}

	/**
	 * CTOR
	 * @param parent
	 * @param style
	 * @param maze
	 * @param view
	 */
	public MazeDisplay(Shell parent, int style, Maze3D maze,MazeWindow view,Image img) {
		super(parent, style);
		this.view = view;
		if(maze!=null){
		myMaze=maze.getMaze();
		character = new Character(img);
		character.setPos(maze.getStartPosition());
		currentZ=character.getPos().z;	
		}
		
		this.maze=maze;
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				String[] _posibleMoves =maze.getPossibleMoves(character.getPos());
				String _myDer=null;
				boolean _validMove=false;
				switch (e.keyCode) {
				case SWT.PAGE_UP:	
					_myDer="Up";
					break;
				case SWT.PAGE_DOWN:	
					_myDer="Down";
					break;
				case SWT.ARROW_RIGHT:	
					_myDer="Right";
					break;
				case SWT.ARROW_LEFT:		
					_myDer="Left";
					break;
				case SWT.ARROW_UP:		
					_myDer="Forward";
					break;
				case SWT.ARROW_DOWN:		
					_myDer="Backward";
					break;
				}
				for (int i = 0; i < _posibleMoves.length; i++) {
					if (_myDer==_posibleMoves[i]) {
						_validMove=true; 
					}
				}
				
				if(_validMove ){
					character.move(_myDer);
					if(_myDer=="Up" ||_myDer=="Down"){	
						currentZ=character.getPos().z;
						setMazeData(maze.getCrossSectionByZ(character.getPos().z));
					}
					
				redraw();	
				}
				if (character.getPos().equals(maze.getGoalPosition())){
					view.displayMessage("Congratulations you solved the maze !");
				}
			}
		});

		this.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				if (mazeData == null){
						Image imgBack = new Image(null, (getClass().getClassLoader().getResourceAsStream("avengerscover.jpeg")));
						e.gc.drawImage(imgBack, 0, 0, imgBack.getBounds().width, imgBack.getBounds().height, 0, 0,
								getSize().x, getSize().y);
						return;
									}
				
				   e.gc.setForeground(new Color(null,0,0,0));
				   e.gc.setBackground(new Color(null,0,0,0));

				   int width=getSize().x;  
				   int height=getSize().y;

				   int w=width/mazeData[0].length;
				   int h=height/mazeData.length;
				   
				   Image _wallImg = new Image(null, (getClass().getClassLoader().getResourceAsStream("wall.jpg")));
				   Image _bananaImg=new Image(null, (getClass().getClassLoader().getResourceAsStream("goal.png")));
				   Image _twowayImg=new Image(null, (getClass().getClassLoader().getResourceAsStream("twoway.png")));
				   Image _downImg=new Image(null, (getClass().getClassLoader().getResourceAsStream("down.png")));
				   Image _upImg=new Image(null, (getClass().getClassLoader().getResourceAsStream("up.png")));
				   
				   for(int i=0;i<mazeData.length;i++)
				      for(int j=0;j<mazeData[i].length;j++){
				          if(mazeData[i][j]==1){
				        	  e.gc.drawImage(_wallImg, 0, 0, _wallImg.getBounds().width, _wallImg.getBounds().height, 
					    				w * j, h * i, w, h);
				          }
				          else if( maze.getGoalPosition().y==i  &&
				        		  maze.getGoalPosition().x==j  &&
				        		  maze.getGoalPosition().z== character.getPos().z){
				        	  
				      		e.gc.drawImage(_bananaImg, 0, 0, _bananaImg.getBounds().width, _bananaImg.getBounds().height, 
				    				w * j, h * i, w, h);

				          } 
				          else if (myMaze[character.getPos().z-1][i][j]==0 && myMaze[character.getPos().z+1][i][j]==0){

					      		e.gc.drawImage(_twowayImg, 0, 0, _twowayImg.getBounds().width, _twowayImg.getBounds().height, 
					    				w * j, h * i, w, h);
					      		
				          } else if (myMaze[character.getPos().z-1][i][j]==0){


					      		e.gc.drawImage(_downImg, 0, 0, _downImg.getBounds().width, _downImg.getBounds().height, 
					    				w * j, h * i, w, h);
				          }else if(myMaze[character.getPos().z+1][i][j]==0){
				        	  
					      		e.gc.drawImage(_upImg, 0, 0, _upImg.getBounds().width, _upImg.getBounds().height, 
					    				w * j, h * i, w, h);				        	
				          }
				    
				          e.gc.setForeground(new Color(null,0,0,0));
						  e.gc.setBackground(new Color(null,0,0,0));
				      }
				  character.draw(w, h, e.gc);
			}
		});
		

		this.redraw();
		this.update();
		this.setVisible(true);
	}
	

	/**
	 * Draw and display the solution of the maze while moving the character
	 * @param solution
	 */
public void printSolution(Solution<Position> solution) {

		TimerTask task = new TimerTask() {
			
			int i = solution.getStatesList().indexOf(new State<Position> (character.getPos()) );
			
			@Override
			public void run() {
				getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						if (i == solution.getStatesList().size()) {
							cancel();
							return;
						}
						if (i==-1)
						{
							
						i=0;
						}

						character.setPos(solution.getStatesList().get(i++).getValue());
						setMazeData(myMaze[character.getPos().z]);
						redraw();
						if (character.getPos().equals(maze.getGoalPosition())){
								
							displayMessage("Congratulations the maze got solved for you!");
							}
						}
				});

			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 500);
	}


/**
 * Display message on screen
 * @param msg
 */
public void displayMessage(String msg) {
	view.display.syncExec(new Runnable() {
		
		@Override
		public void run() {
			MessageBox message = new MessageBox(view.shell);
			if(msg!=null){
			message.setMessage(msg);
			message.open();	
			}
				
		}
	});			
}

/**
 * Draw and display the clue for the user
 * @param sol
 */
public void ClueDisplay() {
	if(character.getPos().z > maze.getGoalPosition().z)
		displayMessage("The exit is below you! move to a lower floor");
	else if(character.getPos().z < maze.getGoalPosition().z)
		displayMessage("The exit is above you! move to an higher floor");
	else
		displayMessage("The exit is on this floor! find it");
}}


