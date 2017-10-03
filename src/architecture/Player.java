package architecture;

import java.util.Scanner;

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

	public Player(int location) {
		//Constructor calls method to reset player stats each level
		resetLevel(location);
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
		damagePoints++;
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
		int playerChoice = 0; //This is what's returned
		while (playerChoice < 1 || playerChoice > 9) {
			displayMenu(); //Calls function to display movement options
			Scanner scan = new Scanner(System.in);
			try {
				playerChoice = scan.nextInt();
				if (playerChoice < 1 || playerChoice > 9) {
					System.out.println("Invalid option, please retry");
				} 
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number, please retry");
			}
		}
		return playerChoice;
	}
	
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










} //End of Class