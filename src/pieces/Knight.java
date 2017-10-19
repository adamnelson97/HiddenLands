package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.GameEngine;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

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

	public Knight(char symbol, Point location) {
		super(symbol, location);
	}

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

	public void move(Drawable[][] pieces, Point playerLocation) {
		try {
			//Move Left
			if (getLocation().getY() - 1 != playerLocation.getY()
					&& getLocation().getY() > 0
					&& pieces[(int) getLocation().getX()][(int) getLocation().getY() - 1] == null) {

				moveKnight(0, -1, pieces);
			}
			//Skip Left past Trap
			else if (getLocation().getY() - 1 != playerLocation.getY()
					&& getLocation().getY() > 0
					&& pieces[(int) getLocation().getX()][(int) getLocation().getY() - 1] instanceof Trap) {

				moveKnight(0, -2, pieces);	
			}
			//Move Up
			else if (getLocation().getX() - 1 != playerLocation.getX()
					&& getLocation().getX() > 0
					&& pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] == null) {

				moveKnight(-1, 0, pieces);				
			}
			//Skip Up past Trap
			else if (getLocation().getY() - 1 != playerLocation.getY()
					&& getLocation().getX() > 0
					&& pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] instanceof Trap) {

				moveKnight(-2, 0, pieces);
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

	private void moveKnight(int x, int y, Drawable[][] pieces) {
		pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
		Point temp = getLocation(); //Copies location to new point
		temp.translate(x, y); //Moves the new point in the desired direction
		setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
		pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
	}
}
