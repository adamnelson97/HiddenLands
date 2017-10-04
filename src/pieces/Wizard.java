package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;

public class Wizard extends GamePiece {

	
	//TODO: write Wizard class
	
	public Wizard(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		return null;
	}

}
