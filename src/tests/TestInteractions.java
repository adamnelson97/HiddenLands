package tests;

import static org.junit.Assert.*;
import pieces.Dwarf;
import pieces.Elf;
import pieces.Knight;
import pieces.Man;
import pieces.Witch;
import pieces.Wizard;
import architecture.Drawable;
import architecture.GameEngine;
import architecture.InteractionResult;

import org.junit.Test;

public class TestInteractions {
	
	//TODO: Update interaction tests for 2D

	//Dragon Test
	//Dragon has no interaction, no test necessary

	//Dwarf Test
	@Test
	public void testDwarfInteraction() {
		System.out.println("Testing Dwarf Interaction");
		//Player gains point on same space, nothing on other spaces
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Dwarf dwarf = new Dwarf('D', 10);
		pieces[10] = dwarf;
		//Test for GET_POINT interaction
		assertEquals(InteractionResult.GET_POINT, dwarf.interact(pieces, 10));
		//Test for NONE interaction
		assertEquals(InteractionResult.NONE, dwarf.interact(pieces, 8));
		assertEquals(InteractionResult.NONE, dwarf.interact(pieces, 9));
		assertEquals(InteractionResult.NONE, dwarf.interact(pieces, 11));
		assertEquals(InteractionResult.NONE, dwarf.interact(pieces, 12));
	}

	//Elf Test
	@Test
	public void testElfInteraction() {
		System.out.println("Testing Elf Interaction");
		//Interaction should always be NONE
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Elf elf = new Elf('E', 10);
		pieces[10] = elf;
		//Test for NONE interaction
		assertEquals(InteractionResult.NONE, elf.interact(pieces, 8));
		assertEquals(InteractionResult.NONE, elf.interact(pieces, 9));
		assertEquals(InteractionResult.NONE, elf.interact(pieces, 10)); //Same space as player
		assertEquals(InteractionResult.NONE, elf.interact(pieces, 11));
		assertEquals(InteractionResult.NONE, elf.interact(pieces, 12));
	}

	//Knight Test
	@Test
	public void testKnightInteraction() {
		System.out.println("Testing Knight Interaction");
		//Advances player on same space, nothing on other spaces
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Knight knight = new Knight('K', 10);
		pieces[10] = knight;
		//Test for ADVANCE interaction
		assertEquals(InteractionResult.ADVANCE, knight.interact(pieces, 10));
		//Test for NONE interaction
		assertEquals(InteractionResult.NONE, knight.interact(pieces, 8));
		assertEquals(InteractionResult.NONE, knight.interact(pieces, 9));
		assertEquals(InteractionResult.NONE, knight.interact(pieces, 11));
		assertEquals(InteractionResult.NONE, knight.interact(pieces, 12));
	}

	//Man Test
	@Test
	public void testManInteraction() {
		System.out.println("Testing Man Interaction");
		//Interaction should always be NONE
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Man man = new Man('M', 10);
		pieces[10] = man;
		//Test for NONE interaction
		assertEquals(InteractionResult.NONE, man.interact(pieces, 8));
		assertEquals(InteractionResult.NONE, man.interact(pieces, 9));
		assertEquals(InteractionResult.NONE, man.interact(pieces, 10)); //Same space as player
		assertEquals(InteractionResult.NONE, man.interact(pieces, 11));
		assertEquals(InteractionResult.NONE, man.interact(pieces, 12));
	}

	//Witch Test
	@Test
	public void testWitchInteraction() {
		System.out.println("Testing Witch Interaction");
		//Witch KILLs on same space +/- 1 space.
		//Gives warning with NONE interaction on on +/- 2 spaces.
		//Gives NONE interaction for all other spaces
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Witch witch = new Witch('W', 10);
		pieces[10] = witch;
		//Test for KILL interaction
		assertEquals(InteractionResult.KILL, witch.interact(pieces, 9)); //1 space left of player
		assertEquals(InteractionResult.KILL, witch.interact(pieces, 10)); //Same space as player
		assertEquals(InteractionResult.KILL, witch.interact(pieces, 11)); //1 space right of player
		//Test for NONE interaction
		assertEquals(InteractionResult.NONE, witch.interact(pieces, 7)); //3 spaces left of player
		assertEquals(InteractionResult.NONE, witch.interact(pieces, 8)); //2 spaces left of player, prints warning to console
		assertEquals(InteractionResult.NONE, witch.interact(pieces, 12)); //2 spaces right of player, prints warning to console
		assertEquals(InteractionResult.NONE, witch.interact(pieces, 13)); //3 spaces left of player
	}

	//Wizard Test
	@Test
	public void testWizardInteraction() {
		System.out.println("Testing Wizard Interaction");
		//Wizard HITs on same space +/- 2 spaces.
		//Gives NONE interaction for all other spaces
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Wizard wizard = new Wizard('W', 10);
		pieces[10] = wizard;
		//Test for HIT interaction
		assertEquals(InteractionResult.HIT, wizard.interact(pieces, 8)); //2 spaces left of player
		assertEquals(InteractionResult.HIT, wizard.interact(pieces, 9)); //1 space left of player
		assertEquals(InteractionResult.HIT, wizard.interact(pieces, 10)); //Same space as player
		assertEquals(InteractionResult.HIT, wizard.interact(pieces, 11)); //1 space right of player
		assertEquals(InteractionResult.HIT, wizard.interact(pieces, 12)); //2 spaces right of player
		//Test for NONE interaction
		assertEquals(InteractionResult.NONE, wizard.interact(pieces, 7)); //3 spaces left of player
		assertEquals(InteractionResult.NONE, wizard.interact(pieces, 13)); //3 spaces left of player
	}

} //End of Class	