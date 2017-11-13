package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.ConsoleColors;
import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>Artifact</h1>
 * The Artifact is a mysterious relic containing a spirit. It
 * can either kill the player or advance them to the next level.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 * 
 */
public class Artifact extends GamePiece {

	/*
	 * Symbol: 'A'
	 * 
	 * Motion: None
	 * 
	 * Interaction: ADVANCE/KILL. Has a 50% chance of either interaction.
	 */
	
	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Artifact(char symbol, Point location) {
		super(symbol, location);
	}

	/**
	 * Randomly kills or advances the player if they are at the same location.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult Either KILL or ADVANCE depending on the randomly generated number.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			System.out.println("\nYou encounter an ancient artifact! Upon inspecting it, it begins to glow.");
			System.out.println("----F L A S H----");
			System.out.println("You have been transported somewhere new and mysterious!");

			Random rand = new Random();
			int n = rand.nextInt(2) + 1;

			switch(n) {
			case 1:
				System.out.println("You encounter a ghoul. He smites you for disturbing his eternal slumber!");
				return InteractionResult.KILL;
			case 2:
				System.out.println("You encounter a spirit, who rewards you with wisdom and regained");
				System.out.println("stamina for your journey.");
				return InteractionResult.ADVANCE;
			}
		}
		return InteractionResult.NONE; //If playerLocation != getLocation()
	}

	/**
	 * Prints the symbol for the piece.
	 * @param unix Determines whether to print the piece in color.
	 */
	@Override
	public void draw(boolean unix) {
		if (unix) System.out.print(ConsoleColors.PURPLE + symbol + ConsoleColors.RESET);
		else System.out.print(symbol);
		
	}

} //End of Class