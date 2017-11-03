package pieces;

import java.awt.Point;

import architecture.Drawable;
import architecture.InteractionResult;
import architecture.Moveable;
import architecture.Player;

/**
 * <h1>Orc</h1>
 * The Orc is a fearsome monster! It is the first boss entity that the player
 * must defeat.
 * @author Adam Nelson
 * @version 1.0
 * @since 2017-10-31
 *
 */
public class Orc extends GamePiece implements Moveable{

	/*
	 * Symbol: 'O'
	 * 
	 * Motion: Moves one space in direction furthest from the player.
	 * 
	 * Interaction: HIT.
	 */

	int health;
	boolean moved; //Ensures Orc moves every turn

	/**
	 * Default GamePiece Constructor. Prints out some messages.
	 * @param symbol The symbol of the object on the board.
	 * @param location The location of the object on the board.
	 */
	public Orc(char symbol, Point location) {
		super(symbol, location);
		health = 8;
		//Message in constructor so it only displays once
		System.out.println("You encounter a hideous, grotesque Orc! This must be the monster");
		System.out.println("terrorizing the people of Corington. Draw your sword and slay the");
		System.out.println("beast! Be warned, he is clever, and attacks only when he has the");
		System.out.println("upper hand.");
		System.out.println();
		System.out.println("You notice he has taken some locals captive. Maybe they can");
		System.out.println("assist you...");
		System.out.println(); 
	}

	/**
	 * Each time the player interacts with the Orc they are injured, but the Orc is
	 * also injured. After a certain amount of hits, the Orc dies and the player has
	 * beaten the level.
	 * @param pieces The game board.
	 * @param player The player object.
	 * @return InteractionResult HIT if not dead yet, ADVANCE if dead, NONE if on different spaces.
	 */
	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();

		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			health--;
			System.out.println("The orc cries out in pain from the sharp cut of your blade!");
			System.out.println("He strikes you back with his monstrous claws!");

			if (health <= 0) return InteractionResult.ADVANCE; //If the orc is dead, the player advances				
			else return InteractionResult.HIT;
		}
		else {
			return InteractionResult.NONE; //No interaction if they are on different spaces
		}
	}

	/**
	 * The Orc moves one space in one direction that it is furthest from the player.
	 * If an object is blocking the Orc's initial path, it then moves in the opposite direction.
	 * @param pieces The game board.
	 * @param playerLocation The player's location.
	 */
	public void move(Drawable[][] pieces, Point playerLocation) {
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double orcX = getLocation().getX();
		double orcY = getLocation().getY();
		double diffX = Math.abs(playerX - orcX);
		double diffY = Math.abs(playerY - orcY);
		moved = false;

		if (diffX > diffY) {
			if (playerX - orcX > 0 && pieces[(int) (getLocation().getX()+1)][(int) getLocation().getY()] == null) { 
				//Move Down
				movePiece(1, 0, pieces);
				moved = true;
			}
			else if (playerX - orcX < 0 && pieces[(int) (getLocation().getX()-1)][(int) getLocation().getY()] == null){ 
				//Move Up
				movePiece(-1, 0, pieces);
				moved = true;
			}
		}

		else {
			if (playerY - orcY > 0 && pieces[(int) (getLocation().getX())][(int) getLocation().getY()+1] == null) { 
				//Move Right
				movePiece(0, 1, pieces);
				moved = true;	
			}
			else if (playerY - orcY < 0 && pieces[(int) (getLocation().getX())][(int) getLocation().getY()-1] == null) { 
				//Move Left
				movePiece(0, -1, pieces);
				moved = true;
			}
		}

		//At this point, if the orc has not moved due to an obstacle it moves in the other direction anyway
		if (!moved) {
			if (playerX - orcX > 0 && pieces[(int) (getLocation().getX()+1)][(int) getLocation().getY()] == null) { 
				//Move Down
				movePiece(1, 0, pieces);
				moved = true;
			}
			else if (playerX - orcX < 0 && pieces[(int) (getLocation().getX()-1)][(int) getLocation().getY()] == null){ 
				//Move Up
				movePiece(-1, 0, pieces);
				moved = true;
			}
		}
		if (!moved) {
			if (playerY - orcY > 0 && pieces[(int) (getLocation().getX())][(int) getLocation().getY()+1] == null) { 
				//Move Right
				movePiece(0, 1, pieces);
				moved = true;	
			}
			else if (playerY - orcY < 0 && pieces[(int) (getLocation().getX())][(int) getLocation().getY()-1] == null) { 
				//Move Left
				movePiece(0, -1, pieces);
				moved = true;
			}
		}
	}

} //End of Class
