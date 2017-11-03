package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>Wizard</h1>
 * The Wizard is a powerful magician who strikes the player if they
 * get too close!
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Wizard extends GamePiece {


	/* 
	 * Symbol: 'W'. Used to confuse player as it is the same as the Witch.
	 * 
	 * Motion: None
	 * 
	 * Interaction: HIT. Has range of +/- 1 spaces from current space,
	 * i.e. if the Wizard is on space 3, he can hit a target on spaces 1-5.
	 */

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Wizard(char symbol, Point location) {
		super(symbol, location);
	}

	/**
	 * Strikes the player if they are within two spaces in any direction.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult HIT if within range, otherwise NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double wizX = getLocation().getX();
		double wizY = getLocation().getY();
		double hitDistance = 1;

		if (playerX <= wizX + hitDistance && playerX  >= wizX - hitDistance && playerY <= wizY + hitDistance && playerY >= wizY - hitDistance) {
			System.out.println("\nYou encounter a Wizard! His spells play tricks on you, and you");
			System.out.println("injure yourself escaping.");
			return InteractionResult.HIT;
		}
		else {
			return InteractionResult.NONE;
		}
	}

}
