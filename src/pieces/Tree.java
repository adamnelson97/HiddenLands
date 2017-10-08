package pieces;

import java.awt.Point;

import architecture.Drawable;

public class Tree implements Drawable {

	/*
	 * Symbol: '$'
	 * 
	 * Landscape, no movement or interaction
	 */
	
	Point location;
	char symbol;

	public Tree(char symbol, Point location) {
		this.symbol = symbol;
		this.location = location;
	}

	public void draw() {
		System.out.print(symbol);
	}

}
