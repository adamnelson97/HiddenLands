package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

/**
 * <h1>Elf</h1>
 * This entity is a friendly wood elf that doesn't do much.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
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
	 * Prints an output message if at the same location as the player.
	 * @param pieces The game board.
	 * @param player The player object.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY())
			System.out.println("\nYou encounter an Elf! He seems too interested in the trees to talk to you.");
		return InteractionResult.NONE;
	}

	/**
	 * Randomly moves up, down, left, or right.
	 * @param pieces The game board.
	 * @param player The player's location.
	 */
	public void move(Drawable[][] pieces, Point playerLocation) {
		Random rand = new Random();
		int n = rand.nextInt(4) + 1;
		int x = 0;
		int y = 0;

		try {
			switch(n) {
			case 1:
				y = 1; break;
			case 2:
				y = -1; break;
			case 3:
				x = 1; break;
			case 4:
				x = -1; break;
			}

			if (getLocation().getX() + x != playerLocation.getX()
					&& getLocation().getY() + y != playerLocation.getY()
					&& pieces[(int) getLocation().getX() + x][(int) getLocation().getY() + y] == null) {
				movePiece(x, y, pieces);
			}

		} catch (ArrayIndexOutOfBoundsException e) {}
	}


} //End of Class
