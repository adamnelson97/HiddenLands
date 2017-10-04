package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.GameEngine;
import architecture.InteractionResult;

/*
 * Abstract class that defines the information and methods that unique child pieces will use
 */

//This is an abstract class because it contains an abstract 
//method AND an instance variable
public abstract class GamePiece implements Drawable {
	// Interfaces cannot have instance variables
	private char symbol;
	// Stores the piece location, which will be needed to interact
	// with the player. 
	private Point location;

	public GamePiece(char symbol, Point location) {
		super();
		this.symbol = symbol;
		this.location = location;
	}

	// Abstract classes can have concrete/defined methods
	public void draw() {
		System.out.print(symbol);
	}

	// Abstract classes should have at least one abstract method
	public abstract InteractionResult interact(Drawable [][] pieces, Point playerLocation);

	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point newLocation) {
		// Ensure the location remains on the board
		if (newLocation.getX() >= 0 && newLocation.getX() < GameEngine.BOARD_SIZE
				&& newLocation.getY() >= 0 && newLocation.getY() < GameEngine.BOARD_SIZE)
			location = newLocation;
	}
}
 //End of Class
