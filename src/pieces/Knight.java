package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;

public class Knight extends GamePiece implements Moveable {

	/*
	 * Symbol: 'K'
	 * 
	 * Motion: Moves Left one space every turn.
	 * Skips a space if it is occupied by another piece.
	 * If still occupied, moves one pace up.
	 * 
	 * Interaction: ADVANCE. Only interacts on same space.
	 */

	public Knight(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		if (playerLocation == getLocation()) {
			System.out.println("\nYou encounter a Knight! He finds you honorable, and assists you in your quest.");
			return InteractionResult.ADVANCE;
		}
		else {
			return InteractionResult.NONE;
		}
	}

	public void move(Drawable[][] pieces, Point playerLocation) {
		try {
			if (getLocation().getX() - 1 != playerLocation.getX()
					&& pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] == null) {

				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
				Point temp = getLocation(); //Copies location to new point
				temp.translate(-1, 0); //Moves the new point in the desired direction
				setLocation(temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
			}
			else if (getLocation().getX() - 2 != playerLocation.getX()
					&& pieces[(int) getLocation().getX() - 2][(int) getLocation().getY()] == null) {

				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
				Point temp = getLocation(); //Copies location to new point
				temp.translate(-2, 0); //Moves the new point in the desired direction
				setLocation(temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
			}
			else if (getLocation().getY() + 1 != playerLocation.getY()
					&& pieces[(int) getLocation().getX()][(int) getLocation().getY() + 1] == null) {

				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
				Point temp = getLocation(); //Copies location to new point
				temp.translate(0, 1); //Moves the new point in the desired direction
				setLocation(temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}

}
