package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.GameEngine;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

/**
 * <h1>Knight</h1>
 * The Knight is an honorable soldier who is always willing to assist a
 * noble warrior on a quest.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-20-31
 *
 */
public class Knight extends GamePiece implements Moveable {

	/*
	 * Symbol: 'K'
	 * 
	 * Motion: Moves Left one space every turn.
	 * Skips a space if it is occupied by another piece.
	 * If still occupied, moves one pace up.
	 * Resets to right edge if at left edge.
	 * Resets to bottom if at top edge.
	 * 
	 * Interaction: ADVANCE. Only interacts on same space.
	 */

	/**
	 * Default GamePiece Constructor.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Knight(char symbol, Point location) {
		super(symbol, location);
	}

	/**
	 * Advances the player to the next level if they are on the same space.
	 * @param pieces The game board.
	 * @param player The player's location.
	 * @return InteractionResult ADVANCE if on same space, otherwise NONE.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			System.out.println("\nYou encounter a Knight! He finds you honorable, and assists you in your quest.");
			return InteractionResult.ADVANCE;
		}
		else {
			return InteractionResult.NONE;
		}
	}

	/**
	 * The Knight tries to moves in the following order, stopping when it can move to a valid location:
	 * 1) Tries to move one space left.
	 * 2) If the space immediately left is occupied by a Trap, it tries to move two spaces to the left.
	 * 3) Tries to move one space up.
	 * 4) If the space immediately up is occupied by a Trap, it tries to move two spaces up.
	 * 5) If the Knight is on the very left edge of the board, it is moved to the far right edge.
	 * 6) If the Knight is on the very top edge of the board, it is moved to the far bottom edge.
	 * @param pieces The game board.
	 * @param playerLocation The player's location.
	 */
	public void move(Drawable[][] pieces, Point playerLocation) {
		try {
			//Move Left
			if (getLocation().getY() > 0
					&& pieces[(int) getLocation().getX()][(int) getLocation().getY() - 1] == null) {

				movePiece(0, -1, pieces);
			}

			//Skip Left past Trap
			else if (getLocation().getY() > 0
					&& pieces[(int) getLocation().getX()][(int) getLocation().getY() - 1] instanceof Trap) {

				movePiece(0, -2, pieces);	
			}

			//Move Up
			else if (getLocation().getX() > 0
					&& pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] == null) {

				movePiece(-1, 0, pieces);				
			}

			//Skip Up past Trap
			else if (getLocation().getX() > 0
					&& pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] instanceof Trap) {

				movePiece(-2, 0, pieces);
			}

			//Reset Right
			else if (getLocation().getY() == 0 && pieces[(int) getLocation().getX()][GameEngine.BOARD_SIZE - 1] == null) {
				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
				Point temp = getLocation();
				int tempX = (int) getLocation().getX();
				temp.setLocation(tempX, GameEngine.BOARD_SIZE-1);
				setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
			}

			//Reset Bottom
			else if (getLocation().getX() == 0 && pieces[GameEngine.BOARD_SIZE - 1][(int) getLocation().getY()] == null) {
				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
				Point temp = getLocation();
				int tempY = (int) getLocation().getY();
				temp.setLocation(GameEngine.BOARD_SIZE-1, tempY);
				setLocation(pieces, temp); 
				pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
}
