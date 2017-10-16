package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Trap extends GamePiece {

	/*
	 * Symbol: &
	 * 
	 * Motion: None
	 * 
	 * Interaction: Appears blank at first, but appears when player is within
	 * +/- one space, blocking their way. Injures the player one point.
	 */

	boolean interacted;

	public Trap(char symbol, Point location) {
		super(symbol, location);
		interacted = false;
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double trapX = getLocation().getX();
		double trapY = getLocation().getY();
		double hitDistance = 1;

		if (playerX <= trapX + hitDistance && playerX  >= trapX - hitDistance && playerY <= trapY + hitDistance && playerY >= trapY - hitDistance) {
			if (interacted == false) {
				System.out.println("You have sprung a trap! You are injured by spikes, and your path");
				System.out.println("is blocked. You must retreat to safety!");
				interacted = true;
				setSymbol('&');
				return InteractionResult.HIT;
			}
		}
		return InteractionResult.NONE;
	}

}
