package architecture;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import pieces.Landscape;
import architecture.GameEngine;

/*
 * This class contains all data and methods relative to the player,
 * such as health, status, and location.
 */

/**
 * <h1>Player</h1>
 * This class stores all the information relevant to the player, such as their
 * health and location.
 * Uses legacy code from school project.
 * @author Mark Baldwin, Cyndi Rader, Adam Nelson
 * @version 2.0
 * @since 2017-10-31
 *
 */
public class Player implements Drawable {

	public static final int POINTS_TO_ADVANCE = 8;
	public static final int POINTS_TO_DIE = 3;

	private String name;
	private Point location;
	private int advancePoints;
	private int damagePoints;
	public enum PlayerStatus {
		DEAD, ADVANCING, OK;
	}
	private PlayerStatus playerStatus;

	private static Difficulty difficulty;

	private String foreground;
	private String background;

	/**
	 * Constructor that gives the player an initial location and asks for their name
	 * and the desire game difficulty.
	 * @param location The starting location for the player.
	 * @param foregroundCol The foreground display color.
	 * @param backgroundCol The background display color.
	 */
	public Player(Point location, String foregroundCol, String backgroundCol) {
		//Constructor calls method to reset player stats each level
		setName();
		setDiff();
		resetLevel(location);
		foreground = foregroundCol;
		background = backgroundCol;
	}

	/**
	 * Asks the player for their name.
	 */
	@SuppressWarnings("resource")
	private void setName() {
		System.out.print("\nWhat is your name, Adventurer? : ");
		Scanner scan = new Scanner(System.in);
		name = scan.next();
	}

	/**
	 * Asks the player to select a difficulty.
	 */
	@SuppressWarnings("resource")
	private void setDiff() {
		Scanner scan = new Scanner(System.in);
		int playerChoice = 0;

		System.out.println();
		System.out.println("1: Easy\n2: Medium\n3: Hard\n4: HARDCORE");
		System.out.print("Choose a Difficulty: ");
		do {
			while (!scan.hasNextInt()) {
				scan.next();
				System.out.print("Invalid option. Please retry: ");
			}
			playerChoice = scan.nextInt();
			if (playerChoice < 1 || playerChoice > 4) System.out.print("Invalid option. Please retry: ");
		} while(playerChoice < 1 || playerChoice > 4);

		switch(playerChoice) {
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

	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * Resets player stats at the beginning of each level.
	 * @param location The new location for the player.
	 */
	public void resetLevel(Point location) {
		//Called every level to reset player stats
		playerStatus = PlayerStatus.OK;
		this.location = location;
		damagePoints = 0;
		advancePoints = 0;
	}

	/**
	 * Prints the player onto the board.
	 * @param unix Determines whether to print in color.
	 */
	public void draw(boolean unix) {
		if (unix) System.out.print(foreground + background + 'P' + ConsoleColors.RESET);
		else System.out.print('P');
	}

	/**
	 * Returns whether the player has enough points to advance or if a piece has given them an ADVANCE.
	 * @return boolean Ability to advance to the next level.
	 */
	public boolean canAdvance() {
		if (advancePoints >= POINTS_TO_ADVANCE || playerStatus == PlayerStatus.ADVANCING) {
			return true;
		}
		return false;
	}

	/**
	 * Returns whether the player has taken enough damage to die or if a piece has KILLed them.
	 * @return boolean Whether the player is still living.
	 */
	public boolean isDead() {
		return (playerStatus == PlayerStatus.DEAD || damagePoints >= POINTS_TO_DIE);
	}

	/**
	 * Increases the player's score when they get a GET_POINT interaction.
	 */
	public void addPoint() {
		advancePoints++;
	}

	/**
	 * Increases the player's damage when they get a HIT interaction.
	 */
	public void takeDamage() {
		damagePoints++;
	}

	public void killed() {
		playerStatus = PlayerStatus.DEAD;
	}

	public void wonAdvance() {
		playerStatus = PlayerStatus.ADVANCING;
	}

	//The remaining methods pertain to changing the player's location

	/**
	 * Asks the player for a direction to move to, asking until they select an option
	 * that is still on the board and not blocked by landscape.
	 * @param pieces The game board.
	 */
	public void doMove(Drawable[][] pieces) {
		boolean moved = false;
		while (!moved) {
			int choice = getPlayerChoice();
			moved = updatePlayerLocation(pieces, choice);
		}
	}

	/*
	 * This method is private because it will only be called from
	 * within doMove.
	 */

	/**
	 * Records the player's desired direction of movement.
	 * @return int This number corresponds to a specific direction.
	 */
	@SuppressWarnings("resource")
	private int getPlayerChoice() {

		int playerChoice = 0;

		do {
			displayMenu();
			Scanner in = new Scanner(System.in);
			playerChoice = in.nextInt();
			if (playerChoice < 1 || playerChoice > 9) System.out.println("Invalid option. Please retry.");
		} while(playerChoice < 1 || playerChoice > 9);
		//scan.close();
		System.out.println();
		System.out.println();
		return playerChoice;
	}

	//This method is private because it will only be called from within getPlayerChoice
	/**
	 * Simply prints movement options.
	 */
	private void displayMenu() {
		System.out.println();
		System.out.println("7: Up and Left    8: Up       9: Up and Right");
		System.out.println("4: Left           5: Stay Put 6: Right");
		System.out.println("1: Down and Left  2: Down     3: Down and Right");
		System.out.print("Choose a movement: ");
	}

	//This method is private because it will only be called from within doMove
	/**
	 * Moves the player if the choose a valid direction. Otherwise it returns false
	 * indicating they need to choose a different direction.
	 * @param pieces The game board.
	 * @param option The desired movement direction.
	 * @return boolean Whether the player has moved or not.
	 */
	private boolean updatePlayerLocation(Drawable[][] pieces, int option) {
		boolean moved = false;
		int x = 0;
		int y = 0;
		//(-U +D / -L +R)

		//Move Up and Left
		if (option == 7	&& location.getY() > 0 && location.getX() > 0) { x = -1; y = -1;}
		//Move Up
		if (option == 8	&& location.getX() > 0) { x = -1; y = 0; }
		//Move Up and Right
		if (option == 9	&& location.getY() < GameEngine.BOARD_SIZE - 1 && location.getX() > 0) { x = -1; y = 1;}
		//Move Left
		if (option == 4	&& location.getY() > 0) { x = 0; y = -1;}
		//Move Right
		if (option == 6	&& location.getY() < GameEngine.BOARD_SIZE-1) { x = 0; y = 1;}
		//Move Down and Left
		if (option == 1	&& location.getY() > 0 && location.getX() < GameEngine.BOARD_SIZE-1) { x = 1; y = -1;}
		//Move Down
		if (option == 2 && location.getX() < (GameEngine.BOARD_SIZE - 1)) { x = 1; y = 0;}
		//Move Down and Right
		if (option == 3	&& location.getY() < GameEngine.BOARD_SIZE-1 && location.getX() < GameEngine.BOARD_SIZE-1) { x = 1; y = 1;}
		//Stay put
		if (option == 5) { moved = true; x = 0; y = 0;}

		if (pieces[(int) getLocation().getX() + x][(int) getLocation().getY() + y] instanceof Landscape) {
		} //Does nothing if the player is attempting to move onto landscape
		else {
			location.translate(x, y);
			moved = true;
		}

		if (!moved) {
			System.out.println("Invalid option, please retry");
			return false;
		}

		return true; //Returns true if a valid option is chosen
	}

	public Point getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public int getAdvancePoints() {
		return advancePoints;
	}

	public int getDamagePoints() {
		return damagePoints;
	}

	public void setAdvancePoints(int advancePoints) {
		this.advancePoints = advancePoints;
	}

	public void setDamagePoints(int damagePoints) {
		this.damagePoints = damagePoints;
	}

} //End of Class