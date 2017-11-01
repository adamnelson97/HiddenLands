package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

/**
 * <h1>Elf</h1>
 * The Elf is a friendly woodland creature that generally ignores
 * what is happening around it.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Elf extends GamePiece implements Moveable {

	/*
	 * Symbol: 'E'
	 * 
	 * Motion: Randomly chooses a direction and moves one space.
	 * 
	 * Interaction: NONE.
	 */

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Elf(char symbol, Point location) {
		super(symbol, location);
	}

	/**
	 * The Elf ignores everything and gives no interaction.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult Always returns NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY())
			System.out.println("\nYou encounter an Elf! He seems too interested in the trees to talk to you.");
		return InteractionResult.NONE;
	}

	/**
	 * Randomly moves the Elf one space left, right, up, or down.
	 * @param pieces The game board.
	 * @param playerLocation The player's location.
	 */
	public void move(Drawable[][] pieces, Point playerLocation) {
		Random rand = new Random();
		int n = rand.nextInt(4) + 1;

		try {
			if (n == 1) {
				//Corresponds to moving Right

				if (getLocation().getY() + 1 != playerLocation.getY()
						&& pieces[(int) getLocation().getX()][(int) getLocation().getY() + 1] == null) {

					movePiece(0, 1, pieces);
				}
			}
			else if (n == 2) {
				//Corresponds to moving Left
				if (getLocation().getY() - 1 != playerLocation.getY()
						&& pieces[(int) getLocation().getX()][(int) getLocation().getY() - 1] == null) {

					movePiece(0, -1, pieces);
				}
			}
			else if (n == 3) {
				//Corresponds to moving Up
				if (getLocation().getX() - 1 != playerLocation.getX()
						&& pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] == null) {

					movePiece(-1, 0, pieces);
				}
			}
			else if (n == 4) {
				//Corresponds to moving Down
				if (getLocation().getX() + 1 != playerLocation.getX()
						&& pieces[(int) getLocation().getX() + 1][(int) getLocation().getY()] == null) {

					movePiece(1, 0, pieces);
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {}
	}


} //End of Class
