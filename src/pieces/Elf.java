package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.ConsoleColors;
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

	/**
	 * Prints the symbol for the piece.
	 * @param unix Determines whether to print the piece in color.
	 */
	@Override
	public void draw(boolean unix) {
		if (unix) System.out.print(ConsoleColors.GREEN_BRIGHT + symbol + ConsoleColors.RESET);
		else System.out.print(symbol);
		
	}

} //End of Class
