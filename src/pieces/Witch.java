package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Player;

public class Witch extends GamePiece {

	/*
	 * Symbol: 'W'. Used to confuse player as it is the same as the Wizard.
	 * 
	 * Motion: None
	 * 
	 * Interaction: KILL. Has range of +/- 1 space from current space. Gives a warning from 2 spaces away.
	 * */

	//Constructor
	public Witch(char symbol, Point location) {
		super(symbol, location);
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double witchX = getLocation().getX();
		double witchY = getLocation().getY();
		double hitDistance = 1;
		double warnDistance = 2;

		if (playerX <= witchX + hitDistance && playerX  >= witchX - hitDistance && playerY <= witchY + hitDistance && playerY >= witchY - hitDistance) {
			System.out.println("\nYou encounter a Witch! The enchantress is beautiful, but casts a");
			System.out.println("curse of death upon you.");
			return InteractionResult.KILL;
		}
		else if (playerX <= witchX + warnDistance && playerX  >= witchX - warnDistance && playerY <= witchY + warnDistance && playerY >= witchY - warnDistance) {
			System.out.println("\nYou sense an evil prescence ahead. Best to find another route!");
			return InteractionResult.NONE;
		}
		else {
			return InteractionResult.NONE;
		}	}

}
