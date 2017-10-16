package architecture;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import architecture.Drawable;
import architecture.Moveable;
import pieces.Dragon;
import pieces.Dwarf;
import pieces.Elf;
import pieces.GamePiece;
import pieces.Knight;
import pieces.Maiden;
import pieces.Man;
import pieces.Rock;
import pieces.Sage;
import pieces.Tree;
import pieces.Unicorn;
import pieces.Water;
import pieces.Witch;
import pieces.Wizard;

/*
 * This class creates the data structures to store the various game pieces,
 * contains methods with hard-coded level data, as well as methods to create
 * randomly generated levels. 
 */

public class LevelEngine {

	//Attributes
	//The board is stored as a 2D array of pieces
	private Drawable[][] levelPieces;
	private ArrayList<Moveable> movingPieces;
	private ArrayList<GamePiece> interactingPieces;
	private Point startingLocation;

	//Constructor
	public LevelEngine() {
		super();
	}

	public void createLevel(int levelNum) {
		switch (levelNum) {
		case 1:
			levelOne();
			break;
		case 2:
			levelTwo();
			break;
		case 3:
			levelThree();
			break;
		case 4:
			levelFour();
			break;
		default:
			System.out.println("Error: Level " + levelNum + " not found.");
			break;
		}
	}

	public Drawable[][] getPieces() {
		return levelPieces;
	}

	public ArrayList<Moveable> getMovingPieces() {
		return movingPieces;
	}

	public ArrayList<GamePiece> getInteractingPieces() {
		return interactingPieces;
	}

	public Point getPlayerStartLoc() {
		return startingLocation;
	}

	//Level Constructors

	//TODO: Write levelOne()

	public void levelOne() {
		System.out.println("\n	The old man says the monster has a lair in the nearby to the West");
		System.out.println("so you head to the Ehrlain Mountains, a region known for troublesome");
		System.out.println("conjurers of dark magik.");
		System.out.println();

		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 12);
		
		/*
		 * Landscape
		 */
		
		//Top Left cluster
		levelPieces[1][1] = new Tree('#', new Point(1,1));
		levelPieces[1][2] = new Rock('o', new Point(1,2));
		levelPieces[2][1] = new Rock('o', new Point(2,1));
		//Bottom Left pool
		levelPieces[13][0] = new Water('~', new Point(13, 0));
		levelPieces[13][1] = new Water('~', new Point(13, 1));
		levelPieces[14][0] = new Water('~', new Point(14, 0));
		levelPieces[14][1] = new Water('~', new Point(14, 1));
		//Mid Left trees
		levelPieces[5][3] = new Tree('#', new Point(5,3));
		levelPieces[6][3] = new Tree('#', new Point(6,3));
		//Top Right trees
		levelPieces[0][13] = new Tree('#', new Point(0,13));
		levelPieces[0][14] = new Tree('#', new Point(0,14));
		levelPieces[1][14] = new Tree('#', new Point(1,14));
		//Bottom Right cluster
		levelPieces[13][14] = new Tree('#', new Point(13,14));
		levelPieces[14][14] = new Tree('#', new Point(14,14));
		levelPieces[13][13] = new Rock('o', new Point(13,13));
		levelPieces[14][13] = new Rock('o', new Point(14,13));
		levelPieces[14][12] = new Rock('o', new Point(14,12));
		//Center pool
		levelPieces[6][6] = new Water('~', new Point(6,6));
		levelPieces[6][7] = new Water('~', new Point(6,7));
		levelPieces[6][8] = new Water('~', new Point(6,8));
		levelPieces[7][6] = new Water('~', new Point(7,6));
		levelPieces[7][7] = new Water('~', new Point(7,7));
		levelPieces[7][8] = new Water('~', new Point(7,8));
		levelPieces[8][6] = new Water('~', new Point(8,6));
		levelPieces[8][7] = new Water('~', new Point(8,7));
		levelPieces[8][8] = new Water('~', new Point(8,8));

		
		/*
		 * Friendlies
		 */
		
		levelPieces[2][2] = new Dwarf('D', new Point(2,2));
		levelPieces[11][1] = new Elf('E', new Point(11,1));
		levelPieces[11][2] = new Dwarf('D', new Point(11,2));
		
		Random rand = new Random();
		int n = rand.nextInt(3) + 1;
		if (n == 3) levelPieces[12][1] = new Man('M', new Point(12,1));
		if (n == 2) levelPieces[6][5] = new Man('M', new Point(6,5));
		
		levelPieces[12][9] = new Sage('S', new Point(12,9));
		levelPieces[7][11] = new Elf('E', new Point(7,11));
		levelPieces[1][13] = new Elf('E', new Point(1,13));
		
		//Enemies

		//Identify pieces that interact
		for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
			for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
				if (levelPieces[i][j] instanceof GamePiece) {
					interactingPieces.add((GamePiece) levelPieces[i][j]);
				}
			}
		}

		//Identify pieces that move
		for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
			for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
				if (levelPieces[i][j] instanceof Moveable) {
					movingPieces.add((Moveable) levelPieces[i][j]);
				}
			}
		}

	}

	//TODO: Write levelTwo();

	public void levelTwo() {
		System.out.println("	You have made sufficient progress on your journey. You have faced");
		System.out.println("formidable monsters, but greater threats lay ahead.");
		System.out.println();
		System.out.println("	Out of the foothills, you enter the Srulmvic Timberland, a dense,");
		System.out.println("dark forest covering the Mountains. This land is known for harboring");
		System.out.println("dangerous hordes of foul beings. Beware!");
		System.out.println();

		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 7);

		/*
		 * Pieces Go Here
		 */
		//Landscape
		levelPieces[5][0] = new Rock('o', new Point(5,0));
		levelPieces[4][1] = new Rock('o', new Point(4,1));
		//Friendlies
		levelPieces[5][1] = new Knight('K', new Point(5,1));
		//Enemies

		//Identify pieces that interact
		for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
			for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
				if (levelPieces[i][j] instanceof GamePiece) {
					interactingPieces.add((GamePiece) levelPieces[i][j]);
				}
			}
		}

		//Identify pieces that move
		for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
			for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
				if (levelPieces[i][j] instanceof Moveable) {
					movingPieces.add((Moveable) levelPieces[i][j]);
				}
			}
		}
	}

	//TODO: Write levelThree();

	public void levelThree() {
		System.out.println("	You have made admirable progress along your journey. Word has begun");
		System.out.println("to spread that a warrior is going to brave the beast. You can see");
		System.out.println("the peak of Alindor, the tallest mountain glittering with the light");
		System.out.println("of a thousand stars, up ahead. You spot a small cave opening nearby.");
		System.out.println("That must be the monster's lair...");
		System.out.println();

		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 7);

		/*
		 * Pieces Go Here
		 */
		//Landscape
		levelPieces[5][0] = new Rock('o', new Point(5,0));
		levelPieces[4][1] = new Rock('o', new Point(4,1));
		//Friendlies
		levelPieces[5][1] = new Knight('K', new Point(5,1));
		//Enemies

		//Identify pieces that interact
		for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
			for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
				if (levelPieces[i][j] instanceof GamePiece) {
					interactingPieces.add((GamePiece) levelPieces[i][j]);
				}
			}
		}

		//Identify pieces that move
		for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
			for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
				if (levelPieces[i][j] instanceof Moveable) {
					movingPieces.add((Moveable) levelPieces[i][j]);
				}
			}
		}
	}

	//TODO: Write levelFour();

	public void levelFour() {
		System.out.println("	You have slayed the mighty beast! Huzzah! You begin the long walk");
		System.out.println("back to the village to inform the people of the good news...");
		System.out.println();
		System.out.println("	'...So you truly slayed it? You killed that evil monster?' a child");
		System.out.println("asks you. You nod your head in affirmation. You step into the inn you");
		System.out.println("first entered many moons ago. You notice the old man, and his eyes widen.");
		System.out.println();
		System.out.println("	'So it's true!' he exclaims. 'My boy, I never doubted you. But word has");
		System.out.println("come here that the great city of Glor Infitae is being laid waste to by");
		System.out.println("an unknown foe. Dark forces are surely at work. If you can, we beseech");
		System.out.println("you to help them as you did us!'");
		System.out.println();
		System.out.println("	You consider the proposition, and reply 'I will help any good people,");
		System.out.println("for good in this world must be protected.'");
		System.out.println();
		System.out.println("	You purchase a horse from a villager and ride into the night. You arrive");
		System.out.println("at the city gates late, find rest in a hostel before being awoken by the");
		System.out.println("city guards.");
		System.out.println();
		System.out.println("	'Are you the man who slew the Orc of Alindor?' You tell them your tale,");
		System.out.println("and why you are here. They provide you with stronger armor, a sharper sword,");
		System.out.println("and a bow with which to face the Skeleton King! Rest well, Adventurer, for");
		System.out.println("tomorrow your journey ends, one way or another...");
		System.out.println();

		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 7);

		//TODO: Create Skeleton King boss
		/*
		 * Pieces Go Here
		 */
		//Landscape
		levelPieces[5][0] = new Rock('o', new Point(5,0));
		levelPieces[4][1] = new Rock('o', new Point(4,1));
		//Friendlies
		levelPieces[5][1] = new Knight('K', new Point(5,1));
		//Enemies

		//Identify pieces that interact
		for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
			for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
				if (levelPieces[i][j] instanceof GamePiece) {
					interactingPieces.add((GamePiece) levelPieces[i][j]);
				}
			}
		}

		//Identify pieces that move
		for (int i = 0; i < GameEngine.BOARD_SIZE; i++) {
			for (int j = 0; j < GameEngine.BOARD_SIZE; j++) {
				if (levelPieces[i][j] instanceof Moveable) {
					movingPieces.add((Moveable) levelPieces[i][j]);
				}
			}
		}
	}

} //End of Class
