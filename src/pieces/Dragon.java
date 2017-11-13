package pieces;

import java.awt.Point;

import architecture.ConsoleColors;
import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>Dragon</h1>
 * The Dragon is a fire breathing creature that kills players who
 * disturb it too many times!
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Dragon extends GamePiece {

	/*
	 * Symbol: 'F'. Uses 'F' for 'Fire' since Dwarves use 'D'.
	 * 
	 * Motion: None
	 * 
	 * Interaction: KILL. Dragon is initially sleeping, and gives warning.
	 * If the player lands on that space again they die.
	 */

	int counter;

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Dragon(char symbol, Point location) {
		super(symbol, location);
		counter = 0;
	}

	/**
	 * Only interacts on the same spot. The first interaction wakes the dragon,
	 * the second kills the player.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult Either NONE or KILL if the player has already interacted with the Dragon.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			if (counter == 0) {
				System.out.println("\nA sleeping dragon lays ahead! Tread lightly, adventurer.\n");
				counter++; //Increases counter so the next encounter the player gets a different interaction
				return InteractionResult.NONE;
			}
			else if (counter == 1 ){
				System.out.println("You have awoken the dragon! He sets you ablaze with his flames!");
				return InteractionResult.KILL;
			}
		}
		return InteractionResult.NONE; //Returns none if player is not on the same space
	}
	
	/**
	 * Prints the symbol for the piece.
	 * @param unix Determines whether to print the piece in color.
	 */
	@Override
	public void draw(boolean unix) {
		if (unix) System.out.print(ConsoleColors.RED + symbol + ConsoleColors.RESET);
		else System.out.print(symbol);
		
	}

} //End of Class
