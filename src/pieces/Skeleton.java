package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

public class Skeleton extends GamePiece implements Moveable {

	/*
	 * Symbol: 'X'
	 * 
	 * Motion: Randomly moves in a 9x9 square.
	 * The Skeleton can also move onto the same space as the player.
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
				System.out.println("\nYou encounter a ghastly Skeleton! He reaches out to grab you");
				System.out.println("with his bony hands.");
				if (health > 1) {
					health--;
					return InteractionResult.NONE;
				}
				else {
					System.out.println("\nThe Skeleton goes to strangle you!");
					System.out.println("You cut the Skeleton down with your sword, shattering");
					System.out.println("his bones!");
					living = false; //prevents future interactions with the skeleton
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Removes the skeleton from the board
					return InteractionResult.HIT;
				}
			}
		}
		return InteractionResult.NONE;
	}

	public void move(Drawable[][] pieces, Point playerLocation) {
		//Tracks whether the Skeleton has moved this turn. The Skeleton
		//will attempt movements until a valid one is found.
		boolean moved = false;

		//Used to randomly determine movement.
		Random rand = new Random();

		//Used to translate the Skeleton according to the random number
		int x = 0; 
		int y = 0;

		while (!moved) {
			int n = rand.nextInt(9)+1; //Generates a random number between 1 and 9 inclusive
			switch(n) {
			case 1: //Down and Left
				x = 1;
				y = -1;
				break;
			case 2: //Down
				x = 1;
				break;
			case 3: //Down and Right
				x = 1;
				y = 1;
				break;
			case 4: //Left
				y = -1;
				break;
			case 5: //Stay Put
				break;
			case 6: //Right
				y = 1;
				break;
			case 7: //Up and Left
				x = -1;
				y = -1;
				break;
			case 8: //Up
				x = -1;
				break;
			case 9: //Up and Right
				x = -1;
				y = 1;
				break;
			}

			//Check to make sure new location is in the Skeleton's bounds
			//Checks X-bounds
			if (getLocation().getX() + x <= startX + 1 && getLocation().getX() + x >= startX - 1) {
				//Checks Y-bounds
				if (getLocation().getY() + y <= startY + 1 && getLocation().getY() + y >= startY - 1) {
					/*
					 * If the new location is valid, the piece checks that the destination is currently
					 * void of other entities (excluding the player).
					 */
					if (pieces[(int) getLocation().getX() + x][(int) getLocation().getY() + y] == null) {
						movePiece(x, y, pieces);
						moved = true;
					}
				}
			}

		}
	}

} //End of Class
