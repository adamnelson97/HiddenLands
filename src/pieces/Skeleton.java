package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

public class Skeleton extends GamePiece implements Moveable {

	/*
	 * Symbol: 'X'
	 * 
	 * Motion: Randomly moves in a 9x9 square.
	 * 
	 * Interaction: Has 3 health points. First two interactions are NONE.
	 * Third interaction kills skeleton, and injures player by one point.
	 */

	private int health; //Health of the skeleton
	private boolean living; //Determines whether the skeleton is still alive
	private int startX; //Stores the initial location of the skeleton which limits its movements
	private int startY;

	public Skeleton(char symbol, Point location) {
		super(symbol, location);
		health = 3;
		living = true;
		startX = (int) location.getX();
		startY = (int) location.getY();
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		if (living) { //Only interacts if the skeleton is still alive
			Point playerLocation = player.getLocation();
			if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) { //Only interacts on same location
				System.out.println("You encounter a ghastly Skeleton! He reaches out to grab you");
				System.out.println("with his bony hands.");
				if (health > 1) {
					health--;
					return InteractionResult.NONE;
				}
				else {
					System.out.println("You cut the Skeleton down with your sword!");
					System.out.println("With his last act on this earth, he cuts you in return.");
					living = false; //prevents future interactions with the skeleton
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Removes the skeleton from the board
					return InteractionResult.HIT;
				}
			}
		}
		return InteractionResult.NONE;
	}

	@Override
	public void move(Drawable[][] pieces, Point playerLocation) {
		// TODO Movement method for Skeleton

	}

}
