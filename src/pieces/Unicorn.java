package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

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

	public Unicorn(char symbol, Point location) {
		super(symbol, location);
		present = 0;
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()
				&& present == 0) {
			System.out.println("\nYou encounter a beautiful golden unicorn with a black horn, covered in" +
					"speckles of starlight. You approach it, and upon touching its horn feel revitalized!");

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
