/*
package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import architecture.Drawable;
import architecture.GameEngine;
import pieces.Dragon;
import pieces.Dwarf;
import pieces.Elf;
import pieces.Knight;

public class TestMovingPieces {

	//TODO: Update movement tests for 2D
	
	//Dragon Test
	//Dragon does not move, no test necessary

	//Dwarf Test
	@Test
	public void testDwarfMove() {
		System.out.println("Testing Dwarf Movement");
		//Dwarf starts by moving two spaces to the right, then moves one space to the left
		//Dwarf changes direction each 'turn', even if it does not actually change locations
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Dwarf dwarf = new Dwarf('D', 10);
		pieces[10] = dwarf;
		//Test basic movement
		dwarf.move(pieces, 0); //Moves dwarf to the right
		assertEquals(12, dwarf.getLocation());
		dwarf.move(pieces, 0); //Moves dwarf to the left
		assertEquals(11, dwarf.getLocation());

		//Test that dwarf does not move onto player
		dwarf.move(pieces, 13); //Dwarf tries to move from 11 to 13, which is occupied by the player
		assertEquals(11, dwarf.getLocation());
		dwarf.move(pieces, 13); //Dwarf tries to move from 11 to 10, because direction still changed
		assertEquals(10, dwarf.getLocation());

		//Test that dwarf does not move onto space occupied by existing piece
		Dragon dragon = new Dragon('F', 12);
		pieces[12] = dragon;
		dwarf.move(pieces, 13); //Dwarf tries to move from 10 to 12, which is occupied by the dragon
		assertEquals(10, dwarf.getLocation());
	}

	//Elf Test
	@Test
	public void testElfMove() {
		System.out.println("Testing Elf Movement");
		//Elf moves one space, randomly choosing the direction
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Elf elf = new Elf('E', 10);
		pieces[10] = elf;
		Dragon dragon = new Dragon('F', 12);
		pieces[12] = dragon;
		//Test basic movement
		elf.move(pieces, 9); //Elf can only move to 11. If tries to move to 9, will not move
		assertFalse(elf.getLocation() == 9);
		elf.move(pieces, 9); //Repeatedly move elf, ensure it does not move onto same space as dragon
		assertFalse(elf.getLocation() == 9);
		elf.move(pieces, 9);
		assertFalse(elf.getLocation() == 9);
		elf.move(pieces, 9);
		assertFalse(elf.getLocation() == 9);
	}

	//Knight Test
	@Test
	public void testKnightMove() {
		System.out.println("Testing Knight Movement");
		//Knight moves one space to the left each turn. Skips space if occupied by player or other piece. Else, stays.
		//If Knight is at the far left edge, it resets to the far right edge.
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Knight knight = new Knight('K', 10);	
		pieces[10] = knight;
		Dragon dragon = new Dragon('F', 8);
		pieces[8] = dragon;
		//Test basic movement
		knight.move(pieces, 0); //Knight moves one space to the left
		assertEquals(9, knight.getLocation());
		
		//Test skipping movement
		knight.move(pieces, 0); //Knight moves onto dragon, skips it, moves left again to space 7
		assertEquals(7, knight.getLocation());
		Dragon dragon1 = new Dragon('F', 6);
		pieces[6] = dragon1;
		Dragon dragon2 = new Dragon('F', 5);
		pieces[5] = dragon2;
		knight.move(pieces, 0); //Knight tries to move, but cannot, even with skipping
		assertEquals(7, knight.getLocation());
		
		//Test movement to edge of board
		knight.setLocation(0); //Manually move knight
		knight.move(pieces, 10);
		assertEquals(GameEngine.BOARD_SIZE - 1, knight.getLocation());
	}
} //End of Class

*/