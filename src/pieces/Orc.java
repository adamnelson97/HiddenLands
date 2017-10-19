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
	 * Motion: Moves one space in each direction towards the player.
	 * 
	 * Interaction: HIT.
	 */

	int health;

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

	@Override
	public InteractionResult interact(Drawable[][] pieces, Player player) {
		Point playerLocation = player.getLocation();

		if (playerLocation.getX() == getLocation().getX() && playerLocation.getY() == getLocation().getY()) {
			health--; //An interaction injures the orc regardless of cooldown
			System.out.println("The orc cries out in pain from the sharp cut of your blade!");
			System.out.println("He strikes you back with his monstrous claws!");

			if (health <= 0) return InteractionResult.ADVANCE; //If the orc is dead, the player advances				
			else return InteractionResult.HIT; //Increase damage by second point
		}
		else {
			return InteractionResult.NONE; //No interaction if they are on different spaces
		}
	}

	public void move(Drawable[][] pieces, Point playerLocation) {
		double playerX = playerLocation.getX();
		double playerY = playerLocation.getY();
		double orcX = getLocation().getX();
		double orcY = getLocation().getY();
		double diffX = Math.abs(playerX - orcX);
		double diffY = Math.abs(playerY - orcY);

		if (diffX > diffY) {
			if (playerX - orcX > 0) { //Move Down
				//Still only moves if grid is devoid of other entities, but now does not care about player's location
				if (pieces[(int) getLocation().getX() + 1][(int) getLocation().getY()] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(1, 0); //Moves the new point in the desired direction
					setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
			else if (playerX - orcX < 0 ){ //Move Up
				if (pieces[(int) getLocation().getX() - 1][(int) getLocation().getY()] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(-1, 0); //Moves the new point in the desired direction
					setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}
			}
		}
		
		else {
			if (playerY - orcY > 0) { //Move Right
				if (pieces[(int) getLocation().getX()][(int) getLocation().getY() + 1] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(0, 1); //Moves the new point in the desired direction
					setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}		}
			else if (playerY - orcY < 0) { //Move Left
				if (pieces[(int) getLocation().getX()][(int) getLocation().getY() - 1] == null) {

					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = null; //Clears old location on the board
					Point temp = getLocation(); //Copies location to new point
					temp.translate(0, -1); //Moves the new point in the desired direction
					setLocation(pieces, temp); //If the new point is valid, the piece is moved. Otherwise, it stays put.
					pieces[(int) getLocation().getX()][(int) getLocation().getY()] = this; //Moves piece to new location on the board
				}		
			}
		}
	}

} //End of Class
