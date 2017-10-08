package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

public class Orc extends GamePiece implements Moveable{

	/*
	 * Symbol: 'O'
	 * 
	 * Motion: Moves two spaces in direction closest to player.
	 * 
	 * Interaction: HIT. Does two points of damage first interaction,
	 * next interaction is cool down. Repeat pattern.
	 */

	boolean cooldown;

	public Orc(char symbol, Point location) {
		super(symbol, location);
		cooldown = false;
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();

		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			if (!cooldown) { //If the orc is not on cooldown...
				
				//Increase damage by one
				int playerDamage = player.getDamagePoints();
				playerDamage++;
				player.setDamagePoints(playerDamage);
				
				cooldown = true; //Won't attack next turn
				
				return InteractionResult.HIT; //Increase damage by second point
			}
			else {
				cooldown = false; //Allows orc to attack next turn
				return InteractionResult.NONE;
			}
		}
		else {
			return InteractionResult.NONE;
		}
	}

	public void move(Drawable[][] pieces, Point playerLocation) {
		// TODO Auto-generated method stub

	}

}
