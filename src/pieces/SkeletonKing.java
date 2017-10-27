package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class SkeletonKing extends GamePiece {

	/*
	 * Motion: None
	 * 
	 * Interaction: NONE interaction until a timer runs out. If the player
	 * fails to beat the level in time, they die with KILL.
	 */

	private static int moveCounter; //Tracks the number of moves the player has left to beat the level

	public SkeletonKing(char symbol, Point location) {
		super(symbol, location);
		moveCounter = 30; //Starting number of moves the player has to beat the level
		System.out.println("\nThe Skeleton King has raised minions from the dead to");
		System.out.println("terrorize the city! You must slay him before he gets");
		System.out.println("too strong and can't be stopped!");
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		//TODO Complete method
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
