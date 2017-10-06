package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Maiden extends GamePiece {

	/*
	 * Symbol: 'M'
	 * 
	 * Motion: None
	 * 
	 * Interaction: NONE. Heals the player, then disappears.
	 */

	boolean interacted;
	
	//Constructor
	public Maiden(char symbol, Point location) {
		super(symbol, location);
		interacted = false;
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()
				&& !interacted) {
			System.out.println("\nYou encounter a fair Maiden! She takes you to a cottage to rest,");
			System.out.println("giving you bread, meat, and ale. You fall asleep...");
			System.out.println("You awaken rejuvenated, but the maiden is gone...");
			player.setDamagePoints(0);
			pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null;
			//The piece is removed from the board so it cannot be abused for free health by the player
			interacted = true;
		}
		return InteractionResult.NONE;
	}

}
