package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Tree extends GamePiece {

	/*
	 * Symbol: '$'
	 * 
	 * Landscape, no movement or interaction
	 */
	
	public Tree(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		return InteractionResult.NONE;
	}

}
