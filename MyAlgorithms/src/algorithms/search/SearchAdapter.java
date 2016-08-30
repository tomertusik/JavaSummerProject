package algorithms.search;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;

import algorithms.mazeGenerators.Maze3D;

/**
 * Adapt a game into a search problem
 * @author Tomer, Gilad
 *
 */
public class SearchAdapter implements Searchable {
	
	private Maze3D maze;
	double moveCost = 1;
	
	public SearchAdapter(Maze3D maze) {
		this.maze = maze;
	}

	@Override
	public <Position> State<Position> getStartState() {
		State<Position> state = new State<Position>( (Position)maze.getStartPosition());
		return state;
	}

	@Override
	public <Position> State<Position> getGoalState() {
		State<Position> state = new State<Position>((Position) maze.getGoalPosition());
		return state;
	}

	@Override
	public <Position> ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		ArrayList<State<Position>> movesList = new ArrayList<State<Position>>();
		Position p = s.getValue();
		Position[] positionMoves = (Position[]) maze.getPossiblePositions((algorithms.mazeGenerators.Position) p);
		for(Position pos : positionMoves){
			movesList.add(new State<Position>(pos));
		}
		return movesList;
	}

	@Override
	public <Position> double getMoveCost(State<Position> currState, State<Position> neighbor) {
		return moveCost;
	}

}
