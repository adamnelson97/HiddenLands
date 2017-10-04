package architecture;

import java.awt.Point;
import java.util.ArrayList;

import architecture.Drawable;
import architecture.Moveable;
import pieces.GamePiece;

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
			//levelOne();
			break;
		case 2:
			//levelTwo();
			break;
		case 3:
			//randLevel();
			break;
		case 4:
			//randLevel();
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
		System.out.println("=====H I D D E N  L A N D S=====");
		System.out.println("Welcome Adventurer! You are about to embark on a quest filled");
		System.out.println("with danger, mysterious creatures, and treasure! Good luck!\n");
		System.out.println("-----LEVEL ONE-----");
		
		levelPieces = new Drawable[GameEngine.BOARD_SIZE][GameEngine.BOARD_SIZE];
		movingPieces = new ArrayList<Moveable>();
		interactingPieces = new ArrayList<GamePiece>();
		startingLocation = new Point(7, 7);
		
		
	}
	
	//TODO: Write levelTwo();
	
	//TODO: Write randLevel();
	
} //End of Class
