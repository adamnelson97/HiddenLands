package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Man extends GamePiece {

	/*
	 * Symbol: 'M'
	 * 
	 * Motion: None
	 * 
	 * Interaction: NONE. The man tells the player the number of hits they can take,
	 * and the number of points they need to advance. Has a 1/3 chance of appearing somewhere on the board.
	 */

	public Man(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		if (playerLocation == getLocation()) {
			System.out.println("\nYou encounter a Man! You ask him some questions to aid you in your quest.");
			System.out.println("Man: 'You can take a maximum of " + Player.POINTS_TO_DIE + " hits before you die.'");
			System.out.println("     'You need " + Player.POINTS_TO_ADVANCE + " prizes to move on to the next level.");
		}
		return InteractionResult.NONE;
	}

} //End of Class
