package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;

public class Witch extends GamePiece {

	//TODO: write Witch class
	
	public Witch(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		return null;
	}

}
