package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;

public class Knight extends GamePiece {

	//TODO: write Knight class
	
	public Knight(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		return null;
	}

}
