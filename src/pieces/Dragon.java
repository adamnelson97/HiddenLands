package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;

public class Dragon extends GamePiece {

	/*
	 * Symbol: 'F'. Dwarves use 'D' so Dragons will use 'F' for 'Fire'
	 * 
	 * Motion: None
	 * 
	 * Interaction: NONE. Dragon is 'sleeping', so it only implements Drawable.
	 */
	
	public Dragon(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		System.out.println("\nA sleeping dragon lays ahead! Tread lightly, adventurer.\n");
		return null;
	}

}
