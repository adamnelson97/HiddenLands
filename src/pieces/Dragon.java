package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;

public class Dragon extends GamePiece {

	/*
	 * Symbol: 'F'. Uses 'F' for 'Fire' since Dwarves use 'D'.
	 * 
	 * Motion: None
	 * 
	 * Interaction: KILL. Dragon is initially sleeping, and gives warning. If the player lands on that space again they die
	 */

	int counter;

	public Dragon(char symbol, Point location) {
		super(symbol, location);
		counter = 0;
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		if (counter == 0) {
			System.out.println("\nA sleeping dragon lays ahead! Tread lightly, adventurer.\n");
			counter++; //Increases counter so the next encounter the player gets a different interaction
			return InteractionResult.NONE;
		}
		else {
			System.out.println("You have awoken the dragon! He sets you ablaze with his flames!");
			return InteractionResult.KILL;
		}
	}
	
} //End of Class
