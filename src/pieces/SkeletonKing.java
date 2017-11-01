package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.GameEngine;
import architecture.InteractionResult;
import architecture.Player;

/**
 * <h1>Skeleton King</h1>
 * This is the second boss entity. The player faces the Skeleton King
 * on level four.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 */
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

	/**
	 * Constructor with output message. Sets the maximum number of moves the player has. 
	 * @param symbol The actual symbol attached to the object.
	 * @param location The location of the object on the array.
	 */
	public SkeletonKing(char symbol, Point location) {
		super(symbol, location);
		moveCounter = 60; //Starting number of moves the player has to beat the level
		allDead = false;
		pathClear = false;
		System.out.println("\nThe Skeleton King has raised minions from the dead to");
		System.out.println("terrorize the city! You must slay him before he gets");
		System.out.println("too strong and can't be stopped!");
	}

	/**
	 * If the level is clear of Skeletons, the path to the end of the level is cleared.
	 * Decrements the turn counter, and if the counter equals zero the player is killed.
	 * @param pieces The game board.
	 * @param player The player object.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		if (!allDead) {
			allDead = true; //Changes this value to true. Loops will change back to false if Skeleton exists.
			for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
				for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
					if (pieces[i][j] instanceof Skeleton) allDead = false;
				}
			}
		}
		
		//Clears the path if all Skeleton minions are dead
		if (allDead && !pathClear) {
			pieces[1][7] = null; //Removes the rock blocking the path to end space
			pieces[0][7] = new Landscape('@', new Point(0, 7));
			System.out.println("\nA path has appeared! Go to the top of the cliff");
			System.out.println("and shoot the Skeleton King with your bow!");
			pathClear = true; //Ensures this block only runs once
		}
		
		//Checks to see if the player is in the location to end the game.
		if (allDead) {
			Point playerLocation = player.getLocation();
			if (playerLocation.getX() == 0 && playerLocation.getY() == 7) {
				System.out.println("\nSummiting the cliff, you draw back your bow.");
				System.out.println("With your last arrow, you shoot the weakened");
				System.out.println("Skeleton King and destroy him!");
				return InteractionResult.ADVANCE; //Ends level
			}
		}
		
		//Kills the player if they run out of turns.
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
