package architecture;

import java.awt.Point;
import java.util.ArrayList;

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
	private ArrayList<GamePiece> interactringPieces;
	private Point startingLocation;
	
	//Constructor
	public LevelEngine() {
		super();
	}
	
	//TODO: write createLevel
	public void createLevel(int levelNum) {
		//clearBoard();
		switch (levelNum) {
		case 1:
			//TODO: write levelOne();
			break;
		case 2:
			//TODO: write levelTwo();
			break;
		case 3:
			//TODO: write randLevel();
			break;
		case 4:
			//TODO: write randLevel();
			break;
		default:
			System.out.println("Error: Level " + levelNum + " not found.");
			break;
		}
	}
	//TODO: write getPieces
	//TODO: write getMovingPieces
	//TODO: write getInteractingPieces
	//TODO: write getPlayerStartLoc

} //End of Class
