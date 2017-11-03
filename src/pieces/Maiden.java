package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>Maiden</h1>
 * The Maiden is a friendly girl who heals the player of all
 * wounds, but then promptly disappears to tend to someone else.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Maiden extends GamePiece {

	/*
	 * Symbol: 'M'
	 * 
	 * Motion: None
	 * 
	 * Interaction: NONE. Heals the player, then disappears.
	 */

	boolean interacted;
	
	/**
	 * Default GamePiece Constructor, also sets the boolean for if the player
	 * has interacted with the Maiden yet.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Maiden(char symbol, Point location) {
		super(symbol, location);
		interacted = false;
	}

	/**
	 * If the player is on the same spot as the Maiden and has not interacted yet, they are healed.
	 * The Maiden is then removed from the board.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult Always NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()
				&& !interacted) {
			System.out.println("\nYou encounter a fair Maiden! She offers you bread, meat,");
			System.out.println("and ale. You fall asleep...");
			System.out.println("You awaken rejuvenated, but the maiden is gone...");
			System.out.println("YOUR HEALTH HAS BEEN FULLY RESTORED.");
			player.setDamagePoints(0);
			pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null;
			//The piece is removed from the board so it cannot be abused for free health by the player
			interacted = true; //Prevents future interactions with player
		}
		return InteractionResult.NONE;
	}

}
