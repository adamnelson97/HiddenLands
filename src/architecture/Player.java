package architecture;

import java.util.Scanner;
import architecture.GameEngine;

/*
 * This class contains all data and methods relative to the player,
 * such as health, status, and location.
 */
public class Player implements Drawable {

	public static final int POINTS_TO_ADVANCE = 3;
	public static final int POINTS_TO_DIE = 3;

	private int location;
	private int advancePoints;
	private int damagePoints;
	public enum PlayerStatus {
		DEAD, ADVANCING, OK;
	}
	private PlayerStatus playerStatus;
	
	/*
	 * The difficulty is as follows:
	 * Easy: Two hardcoded levels
	 * Medium: Two hardcoded levels, one random level
	 * Hard: Two hardcoded levels, two random levels
	 * Hardcore: Same as hard, but the player has only one hit point.
	 */
	public enum Difficulty {
		EASY, MEDIUM, HARD, HARDCORE;
	}
	private static Difficulty difficulty;

	public Player(int location) {
		//Constructor calls method to reset player stats each level
		setDifficulty(); //Only called once
		resetLevel(location);
	}

	private void setDifficulty() {
		System.out.println();
		System.out.println("Choose a Difficulty:");
		System.out.println("1: EASY\n2: MEDIUM\n3: HARD\n4: HARDCORE");
		int choice = getPlayerChoice(4);
		switch(choice) {
		case 1:
			difficulty = Difficulty.EASY;
			break;
		case 2:
			difficulty = Difficulty.MEDIUM;
			break;
		case 3:
			difficulty = Difficulty.HARD;
			break;
		case 4:
			difficulty = Difficulty.HARDCORE;
			break;
		}
	}
	
	public void resetLevel(int Location) {
		//Called every level to reset player stats
		playerStatus = PlayerStatus.OK;
		this.location = location;
		damagePoints = 0;
		advancePoints = 0;
	}

	//Drawable Method
	public void draw() {
		System.out.print('P');
	}

	public boolean canAdvance() {
		if (advancePoints >= POINTS_TO_ADVANCE || playerStatus == PlayerStatus.ADVANCING) {
			return true;
		}
		return false;
	}

	public boolean isDead() {
		return (playerStatus == PlayerStatus.DEAD || damagePoints >= POINTS_TO_DIE);
	}

	public void addPoint() {
		advancePoints++;
	}

	public void takeDamage() {
		if (difficulty == Difficulty.HARDCORE) { killed(); }
		else { damagePoints++; }
	}

	public void killed() {
		playerStatus = PlayerStatus.DEAD;
	}

	public void wonAdvance() {
		playerStatus = PlayerStatus.ADVANCING;
	}

	//The remaining methods pertain to changing the player's location
	public void doMove(Drawable[][] pieces) {
		boolean moved = false;
		while (!moved) {
			int choice = getPlayerChoice(9);
			moved = updatePlayerLocation(choice);
		}
	}

	/*
	 * This method is private because it will only be called from
	 * within doMove.
	 */
	private int getPlayerChoice(int MAX_OPTIONS) {
		int playerChoice = 0; //This is what's returned
		while (playerChoice < 1 || playerChoice > MAX_OPTIONS) {
			displayMenu(); //Calls function to display movement options
			Scanner scan = new Scanner(System.in);
			try {
				playerChoice = scan.nextInt();
				if (playerChoice < 1 || playerChoice > MAX_OPTIONS) {
					System.out.println("Invalid option, please retry");
				} 
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number, please retry");
			}
		}
		return playerChoice;
	}
	
	//This method is private because it will only be called from within getPlayerChoice
	private void displayMenu() {
		System.out.println();
		System.out.println("1: Move right");
		System.out.println("2: Move left");
		System.out.println("3: Jump right");
		System.out.println("4: Jump left");
		System.out.println("5: Move up");
		System.out.println("6: Move down");
		System.out.println("7: Jump up");
		System.out.println("8: Jump down");
		System.out.println("9: Stay put");
	}
	
	//This method is private because it will only be called from within doMove
	private boolean updatePlayerLocation(int option) {
		//TODO: Complete updatePlayerLocationMethod
	}










} //End of Class