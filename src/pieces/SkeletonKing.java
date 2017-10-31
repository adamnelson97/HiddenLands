package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.GameEngine;
import architecture.InteractionResult;
import architecture.Player;

public class SkeletonKing extends GamePiece {

	/*
	 * Motion: None
	 * 
	 * Interaction: NONE interaction until a timer runs out. If the player
	 * fails to beat the level in time, they die with KILL.
	 */

	private int moveCounter; //Tracks the number of moves the player has left to beat the level
	private boolean allDead; //Tracks whether all the Skeleton minions are dead
	private boolean pathClear; //Tracks whether the path to the end space is clear or not

	public SkeletonKing(char symbol, Point location) {
		super(symbol, location);
		moveCounter = 100; //Starting number of moves the player has to beat the level
		allDead = false;
		pathClear = false;
		System.out.println("\nThe Skeleton King has raised minions from the dead to");
		System.out.println("terrorize the city! You must slay him before he gets");
		System.out.println("too strong and can't be stopped!");
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		//TODO Complete method
		if (!allDead) {
			allDead = true; //Changes this value to true. Loops will change back to false if Skeleton exists.
			for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
				for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
					if (pieces[i][j] instanceof Skeleton) allDead = false;
				}
			}
		}
		
		if (allDead && !pathClear) {
			pieces[1][7] = null; //Removes the rock blocking the path to end space
			pieces[0][7] = new Landscape('@', new Point(0, 7));
			System.out.println("\nA path has appeared! Go to the top of the cliff");
			System.out.println("and shoot the Skeleton King with your bow!");
			pathClear = true; //Ensures this block only runs once
		}
		
		if (allDead) {
			Point playerLocation = player.getLocation();
			if (playerLocation.getX() == 1 && playerLocation.getY() == 7) {
				System.out.println("\nSummiting the cliff, you draw back your bow.");
				System.out.println("With your last arrow, you shoot the weakened");
				System.out.println("Skeleton King and destroy him!");
				return InteractionResult.ADVANCE; //Ends level
			}
		}
		
		if (moveCounter == 0) {
			System.out.println("\nYou are too late! The Skeleton King has too much dark");
			System.out.println("energy to be destoyed! He opens his mouth and a stream");
			System.out.println("of darkness surrounds you...");
			return InteractionResult.KILL;
		}
		
		System.out.println("\nYou have " + moveCounter + " turns left to defeat the");
		System.out.println("Skeleton King.");
		moveCounter--;
		return InteractionResult.NONE;
	}

}
