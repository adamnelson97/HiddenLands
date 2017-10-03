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
	
} //End of Class