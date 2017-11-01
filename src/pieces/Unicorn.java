package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

/**
 * <h1>Unicorn</h1>
 * This majestic creature rarely appears, but when it does it both
 * heals the player and gives them a point.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Unicorn extends GamePiece implements Moveable {


	/*
	 * Symbol: 'U'
	 * 
	 * Motion: Appears on board, disappears for three turns, then reappears on the fourth.
	 * Repeat pattern.
	 * 
	 * Interaction: GET_POINT/HEAL. Gives both one point and heals one point of damage.
	 */

	private int present;

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Unicorn(char symbol, Point location) {
		super(symbol, location);
		present = 0;
	}

	/**
	 * If the player is on the same location and the Unicorn is on the board, they are
	 * healed and get a point.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult GET_POINT if on same space, otherwise NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()
				&& present == 0) {

			System.out.println("\nYou encounter a beautiful golden unicorn with a black horn, covered");
			System.out.println("in speckles of starlight. You approach it, and upon touching its horn");
			System.out.println("feel revitalized!");
			System.out.println("YOU HAVE BEEN HEALED BY ONE POINT.");

			//Decrease damage by one if damage is greater than zero
			int playerDamage = player.getDamagePoints();
			if (playerDamage > 0) {
				playerDamage--;
				player.setDamagePoints(playerDamage);
			}
			return InteractionResult.GET_POINT; //Increases score if player actually interacts with unicorn
		}
		return InteractionResult.NONE; //Returns none if player does not interact or unicorn is not 'present'
	}

	/**
	 * While the Unicorn does not change location, it is only on the board once every four turns.
	 * @param pieces The game board.
	 * @param playerLocation The player's location.
	 */
	public void move(Drawable[][] pieces, Point playerLocation) {
		if (present == 3) {
			present = 0;
			pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this;
			//Puts piece back in array so it can be drawn next turn
		}
		else {
			present++;
			pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null;
			//Removes piece from array until time to appear
		}
	}

}
