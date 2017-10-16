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
	 * +/- one space and adjacent to the player, blocking their way. Injures the player one point.
	 * Kills the player if they are directly on it.
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

		if (playerX == trapX && playerY == trapY) {
			System.out.println("\nYou have fallen into a trap!");
			return InteractionResult.KILL;
		}
		if (playerX == trapX && playerY <= trapY + 1 && playerY >= trapY - 1
				|| playerY == trapY && playerX <= trapX + 1 && playerX >= trapX - 1) {
			if (interacted == false) {
				System.out.println("\nYou have sprung a trap! You are injured by spikes, and your path");
				System.out.println("is blocked. You must retreat to safety!");
				interacted = true;
				setSymbol('&');
				return InteractionResult.HIT;
			}
		}
		return InteractionResult.NONE;
	}

}
