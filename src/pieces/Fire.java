package pieces;

import java.awt.Point;

import architecture.Drawable;

public class Fire implements Drawable {


	/*
	 * Symbol: '$'
	 * 
	 * Landscape, no movement or interaction
	 */

	Point location;
	char symbol;

	public Fire(char symbol, Point location) {
		this.symbol = symbol;
		this.location = location;
	}

	public void draw() {
		System.out.print(symbol);
	}

}
