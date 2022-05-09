package application;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellTest1 {

	@Before
	public void setUp() throws Exception {
		System.out.println("Setting up the test!");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test are completed!\n");
	}
	
	// test if cell is a bomb
	@Test
	public void testCell() {
		Cell testCell = new Cell("45", false, 4, 5, true);
		assertEquals("This is not bomb", false, testCell.isBomb());
		System.out.println("This is not bomb");
	}
	
	// tests if cell is not null by id
	@Test
	public void testNullCellId() {
		Cell testCell = new Cell("45", false, 4, 5, true);
		assertNotNull(testCell.getTileId());
		System.out.println("Value is not null");
	}

	// tests if cell is covered (masked)
	@Test
	public void testIsMasked() {
		Cell testCell = new Cell("61", false, 6, 1, true);
		assertTrue("Cell is created", testCell.isMasked());
		System.out.println("This is masked");
	}
	
}
