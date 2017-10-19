package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.GameEngine;
import architecture.InteractionResult;
import architecture.Player;

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
	public abstract InteractionResult interact(Drawable [][] pieces, Player player);

	public Point getLocation() {
		return location;
	}

	public void setLocation(Drawable[][] pieces, Point newLocation) {
		//Ensure the new location is still on the board
		if (newLocation.getX() >= 0 && newLocation.getX() < GameEngine.BOARD_SIZE
				&& newLocation.getY() >= 0 && newLocation.getY() < GameEngine.BOARD_SIZE) {
			location = newLocation;
		}
	}

	public void movePiece(int x, int y, Drawable[][] pieces) {
		//Ensure the new location is currently empty
		if (pieces[(int) getLocation().getX()+x][(int) getLocation().getY()+y] == null) {
			pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
			Point temp = getLocation(); //Copies location to new point
			temp.translate(x, y); //Moves the new point in the desired direction
			setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
			pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
		}
	}

	public void setSymbol(char x) {
		symbol = x;
	}
}
//End of Class
