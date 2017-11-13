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

/**
 * <h1>GameEngine</h1>
 * This class is the heart of the game. It calls new levels, prints
 * the board, and moves the pieces around after each turn.
 * 
 * Includes legacy code from school project.
 * @author Mark Baldwin, Cyndi Rader, Adam Nelson
 * @version 2.0
 * @since 2017-10-31
 *
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
	//Determine if the user is playing on a unix-based shell
	private boolean unix;
	//Store the color of the player's foreground
	private String foregroundCol;
	//Store the color of the player's background
	private String backgroundCol;

	/**
	 * Constructor creates a LevelEngine object used to populate the board
	 * with new pieces every level.
	 */
	public GameEngine() {
		levels = new LevelEngine();
	}

	//Methods

	/**
	 * Calls a new level from the level engine, and updates the board
	 * and lists of interacting/moving pieces.
	 * @param levelNum The current level the player is about to play.
	 */
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

	/**
	 * Prints out the contents of the board to the console.
	 */
	public void displayBoard() {
		printTitle(currentLevel);
		System.out.println(" ______________________________");
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print('|');
			for (int j = 0; j < BOARD_SIZE; j++) {
				//Ensure player is always drawn
				Point temp = new Point(i, j);
				if (temp.getLocation().getX() == player.getLocation().getX()
						&& temp.getLocation().getY() == player.getLocation().getY()) 
					player.draw(unix);
				else if (pieces[i][j] == null)
					System.out.print(' ');
				else
					pieces[i][j].draw(unix);
				System.out.print(' ');
			}
			System.out.print('|');
			System.out.println();
		}
		System.out.println(" ------------------------------");
	}

	/**
	 * Simple method to print the title of the current level before printing the board.
	 * @param numLevel The current level.
	 */
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

	/**
	 * Calls the move method for each piece.
	 */
	public void movePieces() {
		for (Moveable piece : movingPieces) {
			//System.out.println("Moving: " + piece.toString()); //Debugging
			piece.move(pieces, player.getLocation());		
		}	
	}

	/**
	 * Calls the interact method for each piece. Changes the status of the player
	 * after each interaction. HIT injures the player by one point, GET_POINT gives the player
	 * one point, KILL ends the game, and ADVANCE sends the player to the next level.
	 */
	public void interaction() {
		for (GamePiece piece : interactingPieces) {
			//System.out.println("Interacting with: " + piece.toString()); //Debugging
			InteractionResult result = piece.interact(pieces, player);		
			if (result == InteractionResult.GET_POINT) {
				player.addPoint(); 
				System.out.println("\nYou just earned a point!");
				System.out.println("You currently have " + player.getAdvancePoints() + " points.");
			}
			if (result == InteractionResult.HIT) {
				player.takeDamage();
				System.out.println("\nYou just took a hit!");
				if (player.isDead()) {
					System.out.println("Too many hits, you are dead");
					// can only be killed once
					break;
				}
				else {
					System.out.println("You have taken " + player.getDamagePoints() + " hits.");
				}
			}
			if (result == InteractionResult.KILL) {
				player.killed();
				System.out.println("\nSomething just killed you!");
				// can only be killed once
				break;
			}
			if (result == InteractionResult.ADVANCE) {
				player.wonAdvance();
				System.out.println("\nGood news, you beat the level!");
				// can only advance once
				break;
			}
		}			
	}

	/**
	 * If the player has beaten the level, they are sent to the next one.
	 * If they have died, the game is over.
	 * If they have beaten all levels, the game is over.
	 * @return boolean Whether the game is over or if the player can advance to the next level.
	 */
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

	/**
	 * Sets the number of levels that need to be completed based off the difficulty.
	 * @param player The player object.
	 */
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

	/**
	 * This method performs a 'turn' consisting of moving the player, interacting with
	 * pieces, and then moving the other pieces, until the player dies or the level is beaten.
	 */
	public void doOneLevel() {
		while (!levelFinished()) {
			displayBoard();
			// prompt and update the player's location
			player.doMove(pieces);
			interaction();
			movePieces();
		}
	}

	/**
	 * If the player knows a cheatcode they can skip to a certain level.
	 */
	@SuppressWarnings("resource")
	public void cheatcode() {
		System.out.print("Do you have a cheatcode? If not, just hit enter: ");
		Scanner in = new Scanner(System.in);
		String code = in.nextLine();

		if (code.equalsIgnoreCase("Gimli")) {
			currentLevel = 1;
			System.out.println("\nSkipping to Level Two...");
		}
		else if (code.equalsIgnoreCase("Legolas")) {
			currentLevel = 2;
			System.out.println("\nSkipping to Level Three...");
		}
		else if (code.equalsIgnoreCase("Aragorn")) {
			currentLevel = 3;
			System.out.println("\nSkipping to Level Four...");
		}
		//in.close(); Do NOT close the scanner! It produces lots of errors
	}

	/**
	 * Sets whether the player is playing on a unix-based shell, allowing for color printing to the console.
	 */
	@SuppressWarnings("resource")
	public void setUnix() {
		System.out.print("\nAre you playing on a Unix based shell? (Yes/No) ");
		Scanner in = new Scanner(System.in);
		String response = in.nextLine();

		if (response.equalsIgnoreCase("YES")) unix = true;
		else if (response.equalsIgnoreCase("NO")) unix = false;
		else {
			System.out.println("Please enter a correct option.");
			setUnix();
		}
	}

	/**
	 * User sets the foreground color for their player.
	 */
	@SuppressWarnings("resource")
	public void setForegroundCol() {
		System.out.println("What color would you like for your player's foreground? Enter the corresponding number.");
		System.out.println("1. Black");
		System.out.println("2. Red");
		System.out.println("3. Green");
		System.out.println("4. Yellow");
		System.out.println("5. Blue");
		System.out.println("6. Purple");
		System.out.println("7. Cyan");
		System.out.println("8. White");

		Scanner in = new Scanner(System.in);
		int color = in.nextInt();

		switch(color) {
		case 1: foregroundCol = ConsoleColors.BLACK_BRIGHT;
		case 2: foregroundCol = ConsoleColors.RED_BRIGHT;
		case 3: foregroundCol = ConsoleColors.GREEN_BRIGHT;
		case 4: foregroundCol = ConsoleColors.YELLOW_BRIGHT;
		case 5: foregroundCol = ConsoleColors.BLUE_BRIGHT;
		case 6: foregroundCol = ConsoleColors.PURPLE_BRIGHT;
		case 7: foregroundCol = ConsoleColors.CYAN_BRIGHT;
		case 8: foregroundCol = ConsoleColors.WHITE_BRIGHT;

		default: System.out.println("Please enter a valid color."); setForegroundCol();
		}
	}

	/**
	 * User sets the background color for their player.
	 */
	@SuppressWarnings("resource")
	public void setBackgroundCol() {
		System.out.println("What color would you like for your player's background? Enter the corresponding number.");
		System.out.println("1. Black");
		System.out.println("2. Red");
		System.out.println("3. Green");
		System.out.println("4. Yellow");
		System.out.println("5. Blue");
		System.out.println("6. Purple");
		System.out.println("7. Cyan");
		System.out.println("8. White");

		Scanner in = new Scanner(System.in);
		int color = in.nextInt();

		switch(color) {
		case 1: backgroundCol = ConsoleColors.BLACK_BACKGROUND_BRIGHT;
		case 2: backgroundCol = ConsoleColors.RED_BACKGROUND_BRIGHT;
		case 3: backgroundCol = ConsoleColors.GREEN_BACKGROUND_BRIGHT;
		case 4: backgroundCol = ConsoleColors.YELLOW_BACKGROUND_BRIGHT;
		case 5: backgroundCol = ConsoleColors.BLUE_BACKGROUND_BRIGHT;
		case 6: backgroundCol = ConsoleColors.PURPLE_BACKGROUND_BRIGHT;
		case 7: backgroundCol = ConsoleColors.CYAN_BACKGROUND_BRIGHT;
		case 8: backgroundCol = ConsoleColors.WHITE_BACKGROUND_BRIGHT;

		default: System.out.println("Please enter a valid color."); setBackgroundCol();
		}
	}

	/**
	 * Creates a new player object for the game, determines the required number of levels, asks for
	 * a cheatcode, and then begins gameplay until all levels are beaten or if the player dies.
	 */
	@SuppressWarnings({ "unused", "resource" })
	public void playGame() {
		/*
		 * Determine if using unix-based shell, set up the player,
		 * establish the difficulty,
		 * and prompt for a cheat code.
		 */
		setUnix(); //Sets whether the player is playing on a unix based shell.
		if (unix) {
			setForegroundCol();
			setBackgroundCol();
		}
		else {
			foregroundCol = ConsoleColors.WHITE_BRIGHT;
			backgroundCol = ConsoleColors.BLACK_BACKGROUND;
		}
		// Give player a default location of (0,0)
		player = new Player(new Point(0,0));
		setNumLevels(player);
		cheatcode(); //Checks to see if the player knows any cheat codes. Also used for debugging
		if (unix) {
			System.out.println("NOTE: o = Rocks, "
					+ ConsoleColors.GREEN + "#" + ConsoleColors.RESET + " = Trees, "
					+ ConsoleColors.CYAN + "~" + ConsoleColors.RESET + " = Water, "
					+ ConsoleColors.RED_BRIGHT + "$" + ConsoleColors.RESET + " = Fire, P = Player\n");
		}
		else System.out.println("NOTE: o = Rocks, # = Trees, ~ = Water, $ = Fire, P = Player\n");



		/*
		 * Loop creates and runs levels as long as the player is alive.
		 */
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
		System.out.println("Hit enter to exit the game.");
		Scanner in = new Scanner(System.in);
		String code = in.nextLine();
		System.exit(0);
	}

	/**
	 * Simple method just prints expositional text.
	 */
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

	/**
	 * Creates a new GameEngine and begins gameplay.
	 * @param args No arguments used for main method.
	 */
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.intro(); //Prints opening text, does not affect gameplay
		game.playGame();
	}

} //End of Class
