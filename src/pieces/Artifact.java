package pieces;

import java.awt.Point;
import java.util.Random;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Artifact extends GamePiece {

	/*
	 * Symbol: 'A'
	 * 
	 * Motion: None
	 * 
	 * Interaction: ADVANCE/KILL. Has a 50% chance of either interaction.
	 */
	public Artifact(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			System.out.println("You encounter an ancient artifact! Upon inspecting it, it begins to glow.");
			System.out.println("----F L A S H----");
			System.out.println("You have been transported somewhere new and mysterious!");

			Random rand = new Random();
			int n = rand.nextInt(2) + 1;

			if (n == 1) {
				System.out.println("You encounter a ghoul. He smites you for disturbing his eternal slumber!");
				return InteractionResult.KILL;
			}
			else if (n == 2) {
				System.out.println("You encounter a spirit, who rewards you with wisdom and regained\nstamina for your journey.");
				return InteractionResult.ADVANCE;
			}
		}
		return InteractionResult.NONE; //If playerLocation != getLocation()
	}

} //End of Class