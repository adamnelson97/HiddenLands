package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

public class Orc extends GamePiece implements Moveable{

	/*
	 * Symbol: 'O'
	 * 
	 * Motion: Moves two spaces in direction closest to player.
	 * 
	 * Interaction: HIT. Does two points of damage first interaction,
	 * next interaction is cool down. Repeat pattern.
	 */

	boolean cooldown;
	int health;

	public Orc(char symbol, Point location) {
		super(symbol, location);
		cooldown = false;
		health = 4;
		//Message in constructor so it only displays once
		System.out.println("You encounter a hideous, grotesque Orc! This must be" +
				"the monster terrorizing the people of Corington. Draw your sword and" +
				"slay the beast! Be warned, he is clever, and attacks only when he" +
				"has the upper hand...");
	}

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();
		
		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			health--; //An interaction injures the orc regardless of cooldown
			if (health <= 0) return InteractionResult.ADVANCE; //If the orc is dead, the player advances
			
			if (!cooldown) { //If the orc is not on cooldown...
				
				//Increase damage by one
				int playerDamage = player.getDamagePoints();
				playerDamage++;
				player.setDamagePoints(playerDamage);
				
				cooldown = true; //Won't attack next turn
				
				return InteractionResult.HIT; //Increase damage by second point
			}
			else {
				cooldown = false; //Allows orc to attack next turn
				return InteractionResult.NONE;
			}
		}
		else {
			return InteractionResult.NONE;
		}
	}

	public void move(Drawable[][] pieces, Point playerLocation) {
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double orcX = getLocation().getX();
		double orcY = getLocation().getY();
		
	}

}
