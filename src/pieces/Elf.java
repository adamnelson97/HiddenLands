package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

public class Elf extends GamePiece implements Moveable {

	/*
	 * Symbol: 'E'
	 * 
	 * Motion: Randomly chooses a direction and moves one space.
	 * 
	 * Interaction: NONE.
	 */

	//Constructor
	public Elf(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY())
			System.out.println("\nYou encounter an Elf! He seems too interested in the trees to talk to you.");
		return InteractionResult.NONE;
	}

	public void move(Drawable[][] pieces, Point playerLocation) {
		Random rand = new Random();
		int n = rand.nextInt(4) + 1;

		try {
			if (n == 1) {
				//Corresponds to moving Right

				if (getLocation().getY() + 1 != playerLocation.getY()
						&& pieces[(int) getLocation().getX()][(int) getLocation().getY() + 1] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(0, 1); //Moves the new point in the desired direction
					setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
			else if (n == 2) {
				//Corresponds to moving Left

				if (getLocation().getY() - 1 != playerLocation.getY()
						&& pieces[(int) getLocation().getX()][(int) getLocation().getY() - 1] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(0, -1); //Moves the new point in the desired direction
					setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
			else if (n == 3) {
				//Corresponds to moving Up
				if (getLocation().getX() - 1 != playerLocation.getX()
						&& pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(-1, 0); //Moves the new point in the desired direction
					setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
			else if (n == 4) {
				//Corresponds to moving Down

				if (getLocation().getX() + 1 != playerLocation.getX()
						&& pieces[(int) getLocation().getX() + 1][(int) getLocation().getY()] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(1, 0); //Moves the new point in the desired direction
					setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {}
	}


} //End of Class
