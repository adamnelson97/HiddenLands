package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Unicorn extends GamePiece {

	
	/*
	 * Symbol: 'U'
	 * 
	 * Motion: Appears on board, disappears for three turns, then reappears.
	 * Repeat pattern.
	 * 
	 * Interaction: GET_POINT/HEAL. Gives both one point and heals one point of damage.
	 */
		
	public Unicorn(char symbol, Point location) {
		super(symbol, location);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

}
