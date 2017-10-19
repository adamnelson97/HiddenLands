package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

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

	public Dwarf(char symbol, Point location) {
		super(symbol, location);
		lastDirection = 1; //This will make the first movement attempt be Left or Right
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			System.out.println("\nYou encounter a Dwarf! You offer him ale, and he thanks you with treasure!");
			return InteractionResult.GET_POINT;
		}
		else {
			return InteractionResult.NONE;
		}
	}

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
