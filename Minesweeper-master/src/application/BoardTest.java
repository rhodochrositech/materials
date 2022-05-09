package application;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Setting up the test!");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test are completed!\n");
	}

	// This tests if x value is equal to dummyCell x value
	@Test
	public void testX() {
		Cell dummyCell1 = new Cell("56", false, 5, 6, true);
		assertEquals(dummyCell1.getX(), 5);
		System.out.println("The X value is equal to 5!");
	}
	
	// This tests if y value is equal to dummyCell y value
	@Test
	public void testY() {
		Cell dummyCell2 = new Cell("23", false, 2, 3, true);
		assertEquals(dummyCell2.getY(), 3);
		System.out.println("The Y value is equal to 3!");
	}

}
