package application;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testX() {
		//fail("Not yet implemented");
		Board testBoard = new Board(8, 7, "easy");
		assertEquals("This is a valid x coord", 8, testBoard.getMx());
	}
	
	@Test
	public void testY() {
		//fail("Not yet implemented");
		Board testBoard = new Board(34, 12, "hard");
		assertEquals("This is a valid y coord", 12, testBoard.getMy());
	}
	
	
}

