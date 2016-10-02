package view;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

/**
 * Represents the character of the game
 * @author Tomer, Gilad
 *
 */
public class Character {
	
	private Position pos; // position of the character
	private Image img; // image of the character
	
	public Character(Image img) {
		this.img = img;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	/**
	 * Draws the character in the maze
	 * @param cellWidth
	 * @param cellHeight
	 * @param gc
	 */
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 
				cellWidth * pos.x, cellHeight * pos.y, cellWidth, cellHeight);
	}
	
	public void move(String _myDer) {
		switch (_myDer) {
		case "Up":	
			pos.z++;
			break;
		case "Down":
			pos.z--;
			break;
		case "Backward":	
			pos.y++;
			break;
		case "Forward":
			pos.y--;
			break;
		case "Left":
			pos.x--;
			break;
		case "Right":
			pos.x++;
			break;
		default:
			break;
		}
	}
}