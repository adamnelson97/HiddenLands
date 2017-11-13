package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.ConsoleColors;
import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

/**
 * <h1>Sage</h1>
 * The Sage is a wise old man who gives advice to the player if they have enough experience.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Sage extends GamePiece implements Moveable {

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Sage(char symbol, Point location) {
		super(symbol, location);
	}

	/**
	 * If the player has the required number of points, the Sage talks to them.
	 * Otherwise it ignores them.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult Always NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();

		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			if (player.getAdvancePoints() >= 2) {
				Random rand = new Random();
				int n = rand.nextInt(4) + 1;

				System.out.println("\nYou encounter a wise sage! Listen to his helpful words...");
				switch(n) {
				case 1:
					System.out.println("'The Elves, Men, and Sages will neither help nor hinder your quest.");
					System.out.println("However, they may tell you a tale worth listening to...'");
					break;
				case 2:
					System.out.println("'The Dwarves and Knights are kind folk and will aid you in your quest.'");
					break;
				case 3:
					System.out.println("'There are many dangerous creatures in these lands, including");
					System.out.println("Dragons, Witches, and Wizards! Be wary of them.'");
					break;
				case 4:
					System.out.println("'Artifacts are particularly mysterious. The spirits they contain");
					System.out.println("may or may not like you!'");
				}
			}
			else {
				System.out.println("\nYou encounter a wise sage!");
				System.out.println("'You are too inexperienced for my wisdom! Come back later.'");
			}
		}
		return InteractionResult.NONE;
	}

	/**
	 * Moves in the same way as the Elf.
	 * @param pieces The game board.
	 * @param playerLocation The player's location.
	 * @see Elf
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
	
	/**
	 * Prints the symbol for the piece.
	 * @param unix Determines whether to print the piece in color.
	 */
	@Override
	public void draw(boolean unix) {
		if (unix) System.out.print(ConsoleColors.CYAN + symbol + ConsoleColors.RESET);
		else System.out.print(symbol);
		
	}

} //End of Class
