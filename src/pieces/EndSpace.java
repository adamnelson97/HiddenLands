package pieces;

import java.awt.Point;

import architecture.Drawable;

/**
 * <h1>EndSpace</h1>
 * This is an entity for the specific purpose of ending the
 * level when the player steps on it. Since the player must
 * walk onto this space, it cannot be a landscape object. 
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-11-09
 * @see Landscape
 *
 */
public class EndSpace implements Drawable {

	Point location;
	char symbol;
	
	/**
	 * Default constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public EndSpace(char symbol, Point location) {
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
