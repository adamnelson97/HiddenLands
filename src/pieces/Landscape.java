package pieces;

import java.awt.Point;

import architecture.Drawable;

public class Landscape implements Drawable {


	/*
	 * Symbol: Changes
	 * 
	 * No movement or interaction
	 */

	Point location;
	char symbol;

	public Landscape(char symbol, Point location) {
		this.symbol = symbol;
		this.location = location;
	}

	public void draw() {
		System.out.print(symbol);
	}

}
