package pieces;

import java.awt.Point;

import architecture.ConsoleColors;
import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>Man</h1>
 * The Man is a quite traveler who gives advice to the player.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Man extends GamePiece {

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Man(char symbol, Point location) {
		super(symbol, location);
	}

	/**
	 * Prints out a message.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult Always NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			System.out.println("\nYou encounter a Man! You ask him some questions to aid you in your quest.");
			System.out.println("Man: 'You can take a maximum of " + Player.POINTS_TO_DIE + " hits before you die.'");
			System.out.println("     'You need " + Player.POINTS_TO_ADVANCE + " prizes to move on to the next level.");
			System.out.println("     'You have currently taken " + player.getDamagePoints() + " hits and have " + player.getAdvancePoints() + " prizes.");
		}
		return InteractionResult.NONE;
	}
	
	/**
	 * Prints the symbol for the piece.
	 * @param unix Determines whether to print the piece in color.
	 */
	@Override
	public void draw(boolean unix) {
		if (unix) System.out.print(ConsoleColors.YELLOW_BRIGHT + symbol + ConsoleColors.RESET);
		else System.out.print(symbol);
		
	}

} //End of Class
