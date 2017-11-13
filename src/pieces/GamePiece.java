package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.GameEngine;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>GamePiece</h1>
 * This is the abstract class that all other pieces are derived from.
 * It includes an abstract method, getters and setters, and other methods.
 * 
 * Includes legacy code from school project.
 * @author Mark Baldwin, Cyndi Rader, Adam Nelson
 * @version 2.0
 * @since 2017-10-31
 *
 */
public abstract class GamePiece implements Drawable {
	// Interfaces cannot have instance variables
	private char symbol;
	// Stores the piece location, which will be needed to interact
	// with the player. 
	private Point location;

	/**
	 * Constructor that all pieces use.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	
	public GamePiece(char symbol, Point location) {
		super();
		this.symbol = symbol;
		this.location = location;
	}

	/**
	 * Abstract method, prints the symbol for the object onto the game board.
	 * If the player is using a unix-based shell, it prints in color.
	 * @param unix Whether the player is using a unix-based shell.
	 */
	public abstract void draw(boolean unix);

	/**
	 * Abstract method, each piece interacts with the player differently.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult Either NONE, GET_POINT, HIT, KILL, or ADVANCE.
	 */
	public abstract InteractionResult interact(Drawable [][] pieces, Player player);

	
	public Point getLocation() {
		return location;
	}

	/**
	 * Determines if the new location trying to be passed is a valid. The location is only
	 * changed if it is still on the board. This changes where the piece "thinks" it is located.
	 * @param pieces The game board.
	 * @param newLocation The desired new location.
	 */
	public void setLocation(Drawable[][] pieces, Point newLocation) {
		//Ensure the new location is still on the board
		if (newLocation.getX() >= 0 && newLocation.getX() < GameEngine.BOARD_SIZE
				&& newLocation.getY() >= 0 && newLocation.getY() < GameEngine.BOARD_SIZE) {
			location = newLocation;
		}
	}

	/**
	 * This method changes the location of the piece on the board, and changes where it will
	 * actually be drawn. This changes where the piece "is actually" located.
	 * @param x The distance to be translated up or down on the board.
	 * @param y The distance to be translated left or right on the board.
	 * @param pieces The game board.
	 */
	public void movePiece(int x, int y, Drawable[][] pieces) {
		/*
		 * Alright, here is how this insanity works:
		 * 1. Piece of Type X wants to move.
		 * 2. In Type X's move method, you must check if the new location is currently null
		 *    in pieces. You cannot simply check this in movePiece or setLocation. Just accept that
		 *    this problem goes back very deep into the code structure and would take forever to fix.
		 *    NOT WORTH IT.
		 * 3. If the new location is currently null, call movePiece with the desired translation.
		 * 4. movePiece will nullify the piece's current location, and call setLocation with the new location.
		 * 5. If the new location is still on the board, the piece's Point location is changed.
		 * 6. Regardless of whether or not the piece moved, the piece is added back to the array.
		 *    It will have the proper location, moved or not.
		 *    
		 * NOTE: This only works for translating around the board. To set a brand new location from scratch,
		 * you must do it 'manually'. See the Knight move method for an example.
		 */
		//System.out.println("Current Location: " + getLocation().getX() + "," + getLocation().getY());
		pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
		Point temp = getLocation(); //Copies location to new point
		temp.translate(x, y); //Moves the new point in the desired direction
		setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
		pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
		//System.out.println("New Location: " + getLocation().getX() + "," + getLocation().getY());
	}

	public void setSymbol(char x) {
		symbol = x;
	}
}
//End of Class
