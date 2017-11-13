package pieces;

import java.awt.Point;

import architecture.ConsoleColors;
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
	 * Prints the symbol for the piece.
	 * @param unix Determines whether to print the piece in color.
	 */
	public void draw(boolean unix) {
		if (unix) {
			switch(symbol) {
			case 'o': System.out.print(ConsoleColors.WHITE + symbol + ConsoleColors.RESET); break;
			case '#': System.out.print(ConsoleColors.GREEN + symbol + ConsoleColors.RESET); break;
			case '~': System.out.print(ConsoleColors.BLUE + symbol + ConsoleColors.RESET); break;
			case '$': System.out.print(ConsoleColors.RED_BRIGHT + symbol + ConsoleColors.RESET); break;
			default: System.out.print(ConsoleColors.WHITE + symbol + ConsoleColors.RESET); break;
			}
		}
		else System.out.print(symbol);
		
	}

}
