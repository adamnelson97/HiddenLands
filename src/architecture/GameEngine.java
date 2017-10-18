package architecture;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

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
				System.out.println("\nYou just earned a point!\n");
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
				System.out.println("\nGood news, you beat the level!\n");
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
	
	public void cheatcode() {
		System.out.print("Do you have a cheatcode? If not, just hit enter: ");
		Scanner in = new Scanner(System.in);
		String code = in.nextLine();
		
		if (code.equalsIgnoreCase("Gimli")) {
			currentLevel = 1;
			System.out.println("Skipping to Level Two...");
		}
		else if (code.equalsIgnoreCase("Legolas")) {
			currentLevel = 2;
			System.out.println("Skipping to Level Three...");
		}
		else if (code.equalsIgnoreCase("Aragorn")) {
			currentLevel = 3;
			System.out.println("Skipping to Level Four...");
		}
	}

	public void playGame() {
		System.out.println("NOTE: o = Rocks, # = Trees, ~ = Water, P = Player\n");
		// Give player a default location of (0,0)
		player = new Player(new Point(0,0));
		setNumLevels(player);
		cheatcode(); //Checks to see if the player knows any cheat codes. Also used for debugging
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
			System.out.println("Congratulations, " + player.getName() + ", you won!\n");
			switch(numLevels) {
			case 1:
				System.out.println("You have made sufficient progress on your journey. You have faced");
				System.out.println("formidable monsters, but greater threats lay ahead. Play again on");
				System.out.println("Medium to further your quest and save the villagers! ");
				System.out.println();
				break;
			case 2:
				System.out.println("You have made admirable progress along your journey. Word has begun");
				System.out.println("to spread that a warrior is going to brave the beast. Strengthen");
				System.out.println("yourself, and play again on Hard to complete your conquest!");
				System.out.println();
				break;
			case 3:
				System.out.println("You have slayed the mighty beast! Huzzah! You begin the long walk");
				System.out.println("back to the village to inform the people of the good news...");
				System.out.println();
				System.out.println("'...So you truly slayed it? You killed that evil monster?' a child");
				System.out.println("asks you. You nod your head in affirmation. You step into the inn you");
				System.out.println("first entered many moons ago. You notice the old man, and his eyes widen.");
				System.out.println();
				System.out.println("'So it's true!' he exclaims. 'My boy, I never doubted you. But word has");
				System.out.println("come here that the great city of Glor Infitae is being laid waste to by");
				System.out.println("an unknown foe. Dark forces are surely at work. If you can, we beseech");
				System.out.println("you to help them as you did us!'");
				System.out.println();
				System.out.println("You consider the proposition, and reply 'I will help any good people,");
				System.out.println("for good in this world must be protected.'");
				System.out.println();
				System.out.println("Play again on HARDCORE to face the greatest threat in all the land,");
				System.out.println("the fiery rage of THE SKELETON KING!");
				System.out.println();
				break;
			case 4:
				System.out.println("Word has spread of your incredible victory over the terrible Skeleton King!");
				System.out.println("Villagers openly cry out their thanks for you, offering meat, ale,");
				System.out.println("horses, and gold as tokens of their gratitude. A squire approaches");
				System.out.println("you, and offers you a scroll. The scroll dictates that the Princess");
				System.out.println("and half elvish ruler of Glor Infitae Iselria Theris has summoned you");
				System.out.println("for an audience. You mount your horse and stride through the city,");
				System.out.println("stopping before the marble pillars of the Royal Hall.");
				System.out.println();
				System.out.println("You dismount, and the guards enter the giant oak doors into the throne");
				System.out.println("room where the Princess sits, beaming in light as if an angel.");
				System.out.println();
				System.out.println("'Come here, " + player.getName() + "', she says. You approach her and");
				System.out.println("kneel before her.");
				System.out.println();
				System.out.println("'No one can question your heroics and selfless actions to defending");
				System.out.println("our noble land. It is for your nobility and chivalry that I now bestow");
				System.out.println("upon you the Heart of Aetna, our guardian spirit, and declare this day");
				System.out.println("in your honor!'");
				System.out.println();
				System.out.println("The hall erupts in racous applause as townspeople cheer your name!");
				System.out.println();
				System.out.println("Thank you for playing. Please play again!");
				System.out.println();
				break;
			}
		}
		System.exit(0);
	}

	public void intro() {
		System.out.println("\n=====H I D D E N  L A N D S=====");
		System.out.println("   ------by Adam Nelson------");
		System.out.println("\nWelcome Adventurer! You are about to embark on a quest filled");
		System.out.println("with danger, mysterious creatures, and treasure! Good luck!");
		System.out.println();
		System.out.println("Whilst traveling across the land, you see a village on the horizon.");
		System.out.println("Traveling to the village, you arrive at dusk. You enter the local");
		System.out.println("inn, and order an ale. A wounded old man notices your sword, and");
		System.out.println("approaches you.");
		System.out.println();
		System.out.println("'Are you a warrior?' he asks. You nod your head in compliance.");
		System.out.println();
		System.out.println("'Maybe you can help us!' he exclaims. 'Every winter, we people of");
		System.out.println("Corington are ravaged by a terrible monster, and have to rebuild our");
		System.out.println("homes from his terrible destruction. We are too poor to move somewhere");
		System.out.println("else, and our finest warriors have never returned. Please help us!");
		System.out.println();
		System.out.println("He offers you gold, but you turn it down, saying the honor of helping");
		System.out.println("them is reward enough.");
		System.out.println();
		System.out.println("What will you find on your journey? Glory? Love? Power? There is only");
		System.out.println("one way to find out...");
		System.out.println();
		System.out.println("COLLECT POINTS/KILL MONSTERS TO BEAT EACH LEVEL");
	}

	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.intro(); //Prints opening text, does not affect gameplay
		game.playGame();
	}

} //End of Class
