package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;

public class Man extends GamePiece {

	//TODO: write Man Class
	
	public Man(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		return null;
	}

}
