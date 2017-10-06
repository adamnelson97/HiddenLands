package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Wizard extends GamePiece {


	/* 
	 * Symbol: 'W'. Used to confuse player as it is the same as the Witch.
	 * 
	 * Motion: None
	 * 
	 * Interaction: HIT. Has range of +/- 2 spaces from current space,
	 * i.e. if the Wizard is on space 3, he can hit a target on spaces 1-5.
	 */

	//Constructor
	public Wizard(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double wizX = getLocation().getX();
		double wizY = getLocation().getY();
		double hitDistance = 2;
		
		if (playerX <= wizX + hitDistance && playerX  >= wizX - hitDistance && playerY <= wizY + hitDistance && playerY >= wizY - hitDistance) {
			System.out.println("\nYou encounter a Wizard! His spells play tricks on you, and you injure yourself escaping.");
			return InteractionResult.HIT;
		}
		else {
			return InteractionResult.NONE;
		}
	}

}
