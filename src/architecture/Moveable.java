package architecture;

import java.awt.Point;

/*
 * Moveable extends Drawable because ALL pieces must be able to
 * be shown. 
 */

/**
 * <h1>Moveable Interface</h1>
 * Interface that calls the move method, which is different for each piece.
 * Uses legacy code from school project.
 * @author Mark Baldwin, Cyndi Rader, Adam Nelson
 * @version 2.0
 * @since 2017-10-31
 *
 */
public interface Moveable extends Drawable{
	/*
	 * move takes the list of pieces and the player's location and 
	 * updates the list of pieces (i.e., moves this piece to a new
	 * location). 
	 * 
	 * NOTE: It is possible with this function signature for a piece
	 * to move other pieces. This could make for interesting game play..
	 * but should be done with caution! If safety becomes a concern,
	 * this method could be modified to return the new location, with
	 * additional calls in GameEngine to move the piece. 
	 */
	public void move(Drawable[][] pieces, Point playerLocation);

}
