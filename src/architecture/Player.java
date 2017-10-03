package architecture;

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
	
} //End of Class