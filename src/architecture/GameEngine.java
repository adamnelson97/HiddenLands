package architecture;

import java.util.ArrayList;

/*
 * This class contains the main method, initiates LevelEngine,
 * contains the method to display the board to the console,
 * and other methods that establish/change the game from a high level. 
 */

public class GameEngine {

	/*
	 * The game board is a square with an odd number of spaces
	 * so there is an exact center.
	 */
	public static final int BOARD_SIZE = 21;
	
	/*
	 * The number of levels for the game are determined by the difficulty.
	 * There are a minimum of two levels, which are hard coded into LevelEngine.
	 * All other levels are randomly generated.
	 */
	public static int numLevels;
	//Track the current level
	private int currentLevel;
	//GameEngine has one instance of LevelEngine
	private LevelEngine levels;
	//Create the game board
	private Drawable[][] pieces;
	//Track which pieces can move
	private ArrayList<Moveable> movingPieces;
	//Track which pieces interact with the player
	private ArrayList<GamePiece> interactingPieces;
	//GameEngine has one instance of Player
	private Player player;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

} //End of Class
