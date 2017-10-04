package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;

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
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		if (playerLocation == getLocation()) {
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
		
		if (lastDirection == 1) {
			if (n == 1) {
				//Corresponds to moving Right
				if (getLocation().getX() + 2 != playerLocation.getX()
						&& pieces[(int) getLocation().getX() + 2][(int) getLocation().getY()] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(2, 0); //Moves the new point in the desired direction
					setLocation(temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
			else if (n == 2) {
				//Corresponds to moving Left
				if (getLocation().getX() - 2 != playerLocation.getX()
						&& pieces[(int) getLocation().getX() - 2][(int) getLocation().getY()] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(-2, 0); //Moves the new point in the desired direction
					setLocation(temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
			lastDirection = 0;
		}
		else if (lastDirection == 1) {
			if (n == 1) {
				//Corresponds to moving Up
				if (getLocation().getY() + 1 != playerLocation.getY()
						&& pieces[(int) getLocation().getX()][(int) getLocation().getY() + 1] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(0, 1); //Moves the new point in the desired direction
					setLocation(temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
			else if (n == 2) {
				//Corresponds to moving Down
				if (getLocation().getY() - 1 != playerLocation.getY()
						&& pieces[(int) getLocation().getX()][(int) getLocation().getY() - 1] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(0, -1); //Moves the new point in the desired direction
					setLocation(temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
			lastDirection = 1;
		}
	}

} //End of Class
