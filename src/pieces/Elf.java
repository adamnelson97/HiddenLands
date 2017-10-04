package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;

public class Elf extends GamePiece {

	//TODO: Write Elf class
	public Elf(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Point playerLocation) {
		return null;
	}

}
