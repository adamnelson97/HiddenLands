package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Rock extends GamePiece {

	
	/*
	 * Symbol: '@'
	 * 
	 * Landscape, no movement or interaction
	 */
	
	public Rock(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		return InteractionResult.NONE;
	}

}
