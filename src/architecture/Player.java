package architecture;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import architecture.GameEngine;

/*
 * This class contains all data and methods relative to the player,
 * such as health, status, and location.
 */
public class Player implements Drawable {

	public static final int POINTS_TO_ADVANCE = 3;
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

	public Player(Point location) {
		//Constructor calls method to reset player stats each level
		setNameDiff();
		resetLevel(location);
	}

	private void setNameDiff() {
		System.out.print("What is your name, Adventurer? : ");
		Scanner scan = new Scanner(System.in);
		name = scan.next();

		int playerChoice = 0;

		do {
			System.out.println();
			System.out.println("1: Easy\n2: Medium\n3: Hard\n4: HARDCORE");
			System.out.print("Choose a Difficulty: ");
			playerChoice = scan.nextInt();
			if (playerChoice < 1 || playerChoice > 4) System.out.println("Invalid option. Please retry.");
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

	public void resetLevel(Point location) {
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
			int choice = getPlayerChoice();
			moved = updatePlayerLocation(choice);
		}
	}

	/*
	 * This method is private because it will only be called from
	 * within doMove.
	 */
	private int getPlayerChoice() {

		int playerChoice = 0;

		do {
			displayMenu();
			String input = null;
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				input = bufferedReader.readLine();
				playerChoice = Integer.parseInt(input);
			} catch (NumberFormatException ex) {
				System.out.println("Invalid option. Please retry.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (playerChoice < 1 || playerChoice > 9) System.out.println("Invalid option. Please retry.");
		} while(playerChoice < 1 || playerChoice > 9);
		//scan.close();
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
		System.out.print("Choose a movement: ");
	}

	//This method is private because it will only be called from within doMove
	private boolean updatePlayerLocation(int option) {
		boolean moved = false;
		//TODO (-U +D / -L +R)
		//Move Right
		if (option == 1 && location.getY() < (GameEngine.BOARD_SIZE - 1)) {
			location.translate(0, 1);
			moved = true;
		}
		//Move Left
		if (option == 2 && location.getY() > 0) {
			location.translate(0, -1);
			moved = true;
		}
		//Jump Right
		if (option == 3 && location.getY() < (GameEngine.BOARD_SIZE - 2)) {
			location.translate(0, 2);
			moved = true;
		}
		//Jump Left
		if (option == 4 && location.getY() > 1) {
			location.translate(0, -2);
			moved = true;
		}
		//Move Up
		if (option == 5 && location.getX() > 0) {
			location.translate(-1, 0);
			moved = true;
		}
		//Move Down
		if (option == 6 && location.getX() < (GameEngine.BOARD_SIZE - 1)) {
			location.translate(1, 0);
			moved = true;
		}
		//Jump Up
		if (option == 7 && location.getX() > 1) {
			location.translate(-2, 0);
			moved = true;
		}
		//Jump Down
		if (option == 8 && location.getX() < (GameEngine.BOARD_SIZE - 2)) {
			location.translate(2, 0);
			moved = true;
		}
		//Stay put
		if (option == 9) { moved = true; }

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