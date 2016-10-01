package view;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Character {
	
	private Position pos;
	private Image img;
	
	public Character() {
		img = new Image(null, "images/ironman.png");
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	// draw the character
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