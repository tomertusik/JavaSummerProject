package algorithms.mazeGenerators;

/**
 * Position in the maze
 * @author Tomer, Gilad
 *
 */
public class Position {
	public int x; // columns
	public int y; // rows
	public int z; // floors
	
	public Position(int z, int y, int x){ // constructor
		this.z = z;
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() { // prints a position
		return "(" + z + "," + y + "," + x +")";
	}
	
	@Override
	public boolean equals(Object obj) { // compare method
		Position pos = (Position)obj;
		return (this.z == pos.z && this.y == pos.y && this.x == pos.x);
	}

}
