package pieces;

import java.awt.Point;

import architecture.Drawable;

public class Rock implements Drawable {


	/*
	 * Symbol: 'o'
	 * 
	 * Landscape, no movement or interaction
	 */

	Point location;
	char symbol;

	public Rock(char symbol, Point location) {
		this.symbol = symbol;
		this.location = location;
	}

	public void draw() {
		System.out.print(symbol);
	}

}
