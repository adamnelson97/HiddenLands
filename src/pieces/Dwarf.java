package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

/**
 * <h1>Dwarf</h1>
 * The Dwarf is a friendly pal who helps the player advance by
 * exchanging points for ale!
 * @author AdamN
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Dwarf extends GamePiece implements Moveable {

	/*
	 * Symbol: 'D'
	 * 
	 * Motion: Randomly moves Right or Left two spaces the first turn.
	 * Randomly moves Up or Down one space the next turn. Repeats pattern.
	 * 
	 * Interaction: GET_POINT.
	 */

	private int lastDirection; //0 is for L/R, 1 is for U/D
	int numPoints; //Tracks the number of advance points the dwarf has given the player
	final static int MAX_POINTS = 4;

	/**
	 * Default GamePiece Constructor. Also sets the starting direction of the Dwarf.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Dwarf(char symbol, Point location) {
		super(symbol, location);
		lastDirection = 1; //This will make the first movement attempt be Left or Right
	}

	/**
	 * Gives the player a point each interaction up to a certain set number.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult GET_POINT if the player has not yet received the max number of points. Otherwise NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			if (numPoints < MAX_POINTS) {
				System.out.println("\nYou encounter a Dwarf! You offer him ale, and he thanks you with treasure!");
				numPoints++;
				return InteractionResult.GET_POINT;
			}
			else {
				System.out.println("You encounter a Dwarf! He is happily drunk, and does not notice you.");
				return InteractionResult.NONE;
			}
		}
		else {
			return InteractionResult.NONE;
		}
	}

	/**
	 * Randomly moves the Dwarf left/right or up/down, based off the previous turn.
	 * @param pieces The game board.
	 * @param playerLocation The player's location.
	 */
	public void move(Drawable[][] pieces, Point playerLocation) {
		Random rand = new Random();
		int n = rand.nextInt(2) + 1; //Chooses a number between 1 and 2

		try {
			if (lastDirection == 1) {
				if (n == 1) {
					//Corresponds to moving Right
					if (getLocation().getY() + 2 != playerLocation.getY()
							&& pieces[(int) getLocation().getX()][(int) getLocation().getY() + 2] == null) {

						movePiece(0, 2, pieces);
					}
				}
				else if (n == 2) {
					//Corresponds to moving Left
					if (getLocation().getY() - 2 != playerLocation.getY()
							&& pieces[(int) getLocation().getX()][(int) getLocation().getY() - 2] == null) {

						movePiece(0, -2, pieces);
					}
				}
				lastDirection = 0;
			}
			else if (lastDirection == 0) {
				if (n == 1) {
					//Corresponds to moving Up
					if (getLocation().getX() + 1 != playerLocation.getX()
							&& pieces[(int) getLocation().getX() + 1][(int) getLocation().getY()] == null) {

						movePiece(1, 0, pieces);
					}
				}
				else if (n == 2) {
					//Corresponds to moving Down
					if (getLocation().getX() - 1 != playerLocation.getX()
							&& pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] == null) {

						movePiece(-1, 0, pieces);
					}
				}
				lastDirection = 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}

} //End of Class
