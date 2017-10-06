package architecture;

import java.awt.Point;
import java.util.ArrayList;

import architecture.Drawable;
import architecture.Moveable;
import pieces.Dragon;
import pieces.Dwarf;
import pieces.GamePiece;
import pieces.Knight;
import pieces.Maiden;
import pieces.Man;
import pieces.Sage;
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
		System.out.println("The old man says the monster has a lair in the nearby to the West\n"
				+ " so you head to the Ehrlain Mountains, a region known for troublesome\n"
				+ "conjurers of dark magik.\n");
		
		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 7); //Center of board

		//Pieces Go Here
		levelPieces[5][0] = new Knight('K', new Point(5,0));

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
		System.out.println("You have made sufficient progress on your journey. You have faced formidable\n"
				+ "monsters, but greater threats lay ahead.\n"
				+ "Out of the foothills, you enter the Old Wood, a dense, dark forest covering the Mountains."
				+ "This land is known for harboring dangerous hordes of foul beings. Beware!\n");

		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 7);
		
		//Pieces Go Here

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
		System.out.println("You have made admirable progress along your journey. Word has begun to spread\n"
				+ "that a warrior is going to brave the beast. You can see the peak of Alindor,\n"
				+ "the tallest mountain, up ahead. You spot a small cave opening nearby. That must be\n"
				+ "the monster's lair...");
		
		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 7);
		
		//Pieces Go Here

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
		System.out.println("You have slayed the mighty beast! Huzzah! You begin the long walk back to the\n"
				+ "village to inform the people of the good news...\n"
				+ "'...So you truly slayed it? You killed that evil monster?' a child asks you. You nod your\n"
				+ "head in agreement. You step into the inn you first entered many moons ago. You notice the\n"
				+ "old man, and his eyes widen. 'So it's true!' he exclaims. 'My boy, I never doubted you. But\n"
				+ "word has come here that the great city of Glor Infitae is being laid waste to by an unknown foe."
				+ "Dark forces are surely at work. If you can, we beseech you to help them as you did us!'\n"
				+ "\nYou consider the proposition, and reply 'I will help any good people, for good in this\n"
				+ "world must be protected.'\n"
				+ "\nYou purchase a horse from a villager and ride into the night. You arrive at the city gates late, find\n"
				+ "rest in a hostel before being awoken by the city guards. 'Are you the man who slew the monster of Alindor?'\n"
				+ "You tell them your tale, and why you are here. They provide you with stronger armor, a sharper sword,\n"
				+ "and a bow with which to face the Skeleton King. Rest well, Adventurer, for tomorrow you face him...");
		
		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 7);
		
		//Pieces Go Here

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
