package architecture;

import java.awt.Point;
import java.util.ArrayList;

import architecture.GameEngine;
import architecture.Player;
import pieces.GamePiece;

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
	public static final int BOARD_SIZE = 15;

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

	//Constructor
	public GameEngine() {
		levels = new LevelEngine();
	}

	//Methods
	public void setupLevel(int levelNum) {
		// LevelEngine needs to create the specified level
		levels.createLevel(levelNum);
		// request data structures from LevelEngine
		pieces = levels.getPieces();
		movingPieces = levels.getMovingPieces();
		interactingPieces = levels.getInteractingPieces();
		// reset player statistics, starting location determined
		// by level engine
		player.resetLevel(levels.getPlayerStartLoc());
	}

	public void displayBoard() {
		for (int j = 0; j < BOARD_SIZE; j++) {
			for (int i = 0; i < BOARD_SIZE; i++) {
				System.out.print('[');
				//Ensure player is always drawn
				if (new Point(j, i) == player.getLocation()) 
					player.draw();
				else if (pieces[j][i] == null)
					System.out.print(' ');
				else
					pieces[j][i].draw();
				System.out.print(']');
			}
		}
		System.out.println();
	}

	public void movePieces() {
		for (Moveable piece : movingPieces) {
			piece.move(pieces, player.getLocation());		
		}	
	}
	
	public void interaction() {
		for (GamePiece piece : interactingPieces) {
			InteractionResult result = piece.interact(pieces, player);		
			if (result == InteractionResult.GET_POINT) {
				player.addPoint(); 
				System.out.println("\nYou just won a prize!\n");
			}
			if (result == InteractionResult.HIT) {
				player.takeDamage();
				System.out.println("\nYou just took a hit!\n");
				if (player.isDead()) {
					System.out.println("Too many hits, you are dead");
					// can only be killed once
					break;
				}
			}
			if (result == InteractionResult.KILL) {
				player.killed();
				System.out.println("\nSomething just killed you!\n");
				// can only be killed once
				break;
			}
			if (result == InteractionResult.ADVANCE) {
				player.wonAdvance();
				System.out.println("\nGood news, you have won an advance!\n");
				// can only advance once
				break;
			}
		}			
	}
	
	public boolean levelFinished() {
		if (player.canAdvance()) {
			if (currentLevel <  numLevels)
				System.out.println("Advancing to next level...\n");
			return true;
		}
		if (player.isDead()) {
			return true;
		}
		return false;	
	}
	
	//TODO: write doOneLevel
	
	public void setNumLevels() {
		Difficulty diff = player.getDifficulty();
		switch(diff) {
		case EASY:
			numLevels = 2;
			break;
		case MEDIUM:
			numLevels = 3;
			break;
		case HARD:
			numLevels = 4;
			break;
		case HARDCORE:
			numLevels = 4;
			break;
		}
	}
	
	public void doOneLevel() {
		while (!levelFinished()) {
			displayBoard();
			// prompt and update the player's location
			player.doMove(pieces);
			interaction();
			movePieces();
		}
	}
	
	public void playGame() {
		// Give player a default location of (0,0)
		player = new Player(new Point(0,0));
		while (currentLevel < numLevels && !player.isDead()) {
			currentLevel++;
			setupLevel(currentLevel);
			doOneLevel();
		}
		// If reach this point, either all levels were completed OR
		// player is dead
		if (player.isDead())
			System.out.println("Too bad, " + player.getName() + ", you lose...");
		else
			System.out.println("Congratulations, " + player.getName() + ", you won!");
		System.exit(0);
	}
	
	public void intro() {
		System.out.println("=====H I D D E N  L A N D S=====");
		System.out.println("   ------by Adam Nelson------");
		System.out.println("Welcome Adventurer! You are about to embark on a quest filled\n" +
				"with danger, mysterious creatures, and treasure! Good luck!\n\n" + 
				"Whilst traveling across the land, you see a village on the horizon.\n" + 
				"Traveling to the village, you arrive at dusk. You enter the local inn, and order an ale.\n" +
				"A wounded old man notices your sword, and approaches you.\n" +
				"'Are you a warrior?' he asks. You nod your head in compliance.\n" +
				"Maybe you can help us!' he exclaims. Every winter, we are ravaged by a terrible beast,\n" +
				"and have to rebuild our homes from his terrible destruction. We are too poor to move\n" +
				"somewhere else, and our finest warriors have never returned. Please help us!\n" +
				"\nHe offers you gold, but you turn it down, saying the honor of helping them is reward enough.\n" +
				"What will you find on your journey? Glory? Love? Power? There is only one way to find out...\n");
	}
	
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.intro(); //Prints opening text, does not affect gameplay
		game.playGame();
	}

} //End of Class
