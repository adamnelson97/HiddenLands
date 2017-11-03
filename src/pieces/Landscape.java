package pieces;

import java.awt.Point;

import architecture.Drawable;

/**
 * <h1>Landscape</h1>
 * This is a generic class that can be constructed with
 * different symbols to represent different types of landscape.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Landscape implements Drawable {


	/*
	 * Symbol: Changes
	 * 
	 * No movement or interaction
	 */

	Point location;
	char symbol;

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Landscape(char symbol, Point location) {
		this.symbol = symbol;
		this.location = location;
	}

	/**
	 * Prints the symbol for the Landscape object.
	 */
	public void draw() {
		System.out.print(symbol);
	}

}
