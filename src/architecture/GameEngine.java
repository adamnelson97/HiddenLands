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
		printTitle(currentLevel);
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print('|');
			for (int j = 0; j < BOARD_SIZE; j++) {
				//Ensure player is always drawn
				Point temp = new Point(i, j);
				if (temp.getLocation().getX() == player.getLocation().getX()
						&& temp.getLocation().getY() == player.getLocation().getY()) 
					player.draw();
				else if (pieces[i][j] == null)
					System.out.print(' ');
				else
					pieces[i][j].draw();
				System.out.print('|');
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printTitle(int numLevel) {
		switch(numLevel) {
		case 1:
			System.out.println("\n      -----LEVEL ONE-----");
			break;
		case 2:
			System.out.println("\n      -----LEVEL TWO-----");
			break;
		case 3:
			System.out.println("\n      ----LEVEL THREE----");
			break;
		case 4:
			System.out.println("\n      -----LEVEL FOUR----");
			break;
		}
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

	public void setNumLevels(Player player) {
		Difficulty diff = player.getDifficulty();
		switch(diff) {
		case EASY:
			numLevels = 1;
			break;
		case MEDIUM:
			numLevels = 2;
			break;
		case HARD:
			numLevels = 3;
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
		setNumLevels(player);
		while (currentLevel < numLevels && !player.isDead()) {
			currentLevel++;
			setupLevel(currentLevel);
			doOneLevel();
		}
		// If reach this point, either all levels were completed OR
		// player is dead
		if (player.isDead())
			System.out.println("Too bad, " + player.getName() + ", you lose...");
		else {
			System.out.println("Congratulations, " + player.getName() + ", you won!");
			switch(numLevels) {
			case 1:
				System.out.println("You have made sufficient progress on your journey. You have faced formidable\n"
						+ "monsters, but greater threats lay ahead. Play again on Medium to further your quest and\n"
						+ "save the villagers! ");
				break;
			case 2:
				System.out.println("You have made admirable progress along your journey. Word has begun to spread\n"
						+ "that a warrior is going to brave the beast. Strengthen yourself, and play again on Hard to\n"
						+ "complete your conquest!");
				break;
			case 3:
				System.out.println("You have slayed the mighty beast! Huzzah! You begin the long walk back to the\n"
						+ "village to inform the people of the good news...\n"
						+ "'...So you truly slayed it? You killed that evil monster?' a child asks you. You nod your\n"
						+ "head in agreement. You step into the inn you first entered many moons ago. You notice the\n"
						+ "old man, and his eyes widen. 'So it's true!' he exclaims. 'My boy, I never doubted you. But\n"
						+ "word has come here that the great city of Glor Infitae is being laid waste to by an unknown foe."
						+ "Dark forces are surely at work. If you can, we beseech you to help them as you did us!'\n"
						+ "\nYou consider the proposition, and reply 'I will help any good people, for good in this\n"
						+ "world must be protected.'\n"
						+ "\nPlay again on HARDCORE to face the greatest threat in all the land, the fiery rage of\n"
						+ "THE SKELETON KING!");
				break;
			case 4:
				System.out.println("Word has spread of your incredible victory over the terrible Skeleton King! Villagers openly\n"
						+ "cry out their thanks for you, offering meat, ale, horses, and gold as tokens of their gratitude.\n"
						+ "A squire approaches you, and offers you a scroll. The scroll dictates that the Princess, caretaker\n"
						+ "and ruler of Glor Infitae has summoned you for an audience. You mount your horse and stride through the\n"
						+ "city, stopping before the marble pillars of the Royal Hall.\n"
						+ "\nYou dismount, and the guards enter the giant oak doors into the throne room where the Princess sits,\n"
						+ "beaming in light as if an angel. 'Come here, " + player.getName() + "', she says. You approach her and\n"
						+ "kneel before her.\n'No one can question your heroics and selfless actions to defending our noble land.\n"
						+ "It is for this that I now bestow upon you the Heart of Aetna, our guardian spirit, and declare this day\n"
						+ "in your honor!' The hall erupts in racous applause as townspeople cheer your name!\n"
						+ "\nThank you for playing. Please play again!");
				break;
			}
		}
		System.exit(0);
	}

	public void intro() {
		System.out.println("\n\n\n=====H I D D E N  L A N D S=====");
		System.out.println("   ------by Adam Nelson------");
		System.out.println("Welcome Adventurer! You are about to embark on a quest filled\n" +
				"with danger, mysterious creatures, and treasure! Good luck!\n\n" + 
				"Whilst traveling across the land, you see a village on the horizon.\n" + 
				"Traveling to the village, you arrive at dusk. You enter the local inn, and order an ale.\n" +
				"A wounded old man notices your sword, and approaches you.\n" +
				"'Are you a warrior?' he asks. You nod your head in compliance.\n" +
				"Maybe you can help us!' he exclaims. Every winter, we people of Corington are ravaged by a terrible\n" +
				"monster, and have to rebuild our homes from his terrible destruction. We are too poor to move\n" +
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
