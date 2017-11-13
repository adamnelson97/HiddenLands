package pieces;

import java.awt.Point;

import architecture.ConsoleColors;
import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>Trap</h1>
 * The Trap is, well a trap, that injures the player when it is sprung.
 * If the player steps directly onto the trap they are killed.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Trap extends GamePiece {

	boolean interacted;

	/**
	 * Default GamePiece Constructor. "Sets" the Trap with a boolean.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Trap(char symbol, Point location) {
		super(symbol, location);
		interacted = false;
	}

	/**
	 * If the player is on a space directly adjacent to the Trap,
	 * it activates and HITs them. Subsequent steps on adjacent spaces
	 * do not injure the player. Directly moving onto the Trap kills them.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult HIT if sprung, KILL if on the Trap, otherwise NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double trapX = getLocation().getX();
		double trapY = getLocation().getY();

		if (playerX == trapX && playerY == trapY) {
			System.out.println("\nYou have fallen into a trap!");
			return InteractionResult.KILL;
		}
		if (playerX == trapX && playerY <= trapY + 1 && playerY >= trapY - 1
				|| playerY == trapY && playerX <= trapX + 1 && playerX >= trapX - 1) {
			if (interacted == false) {
				System.out.println("\nYou have sprung a trap! You are injured by spikes, and your path");
				System.out.println("is blocked. You must retreat to safety!");
				interacted = true;
				setSymbol('&');
				return InteractionResult.HIT;
			}
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
