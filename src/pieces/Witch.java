package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>Witch</h1>
 * The Witch is a beautiful but terrifying enchantress that kills
 * players on sight!
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Witch extends GamePiece {

	/*
	 * Symbol: 'W'. Used to confuse player as it is the same as the Wizard.
	 * 
	 * Motion: None
	 * 
	 * Interaction: KILL. Has range of +/- 1 space from current space. Gives a warning from 2 spaces away.
	 * */

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Witch(char symbol, Point location) {
		super(symbol, location);
	}

	/**
	 * Kills the player if they are within one space in either direction of them.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult KILL if within range, otherwise NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double witchX = getLocation().getX();
		double witchY = getLocation().getY();
		double hitDistance = 1;
		double warnDistance = 2;

		if (playerX <= witchX + hitDistance && playerX  >= witchX - hitDistance && playerY <= witchY + hitDistance && playerY >= witchY - hitDistance) {
			System.out.println("\nYou encounter a Witch! The enchantress is beautiful, but casts a");
			System.out.println("curse of death upon you.");
			return InteractionResult.KILL;
		}
		else if (playerX <= witchX + warnDistance && playerX  >= witchX - warnDistance && playerY <= witchY + warnDistance && playerY >= witchY - warnDistance) {
			System.out.println("\nYou sense an evil prescence ahead. Best to find another route!");
		}
		return InteractionResult.NONE;
	}

}
