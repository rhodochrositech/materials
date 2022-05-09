package application;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserEntryTest {

	@Before
	public void setUp() throws Exception {
		System.out.print("Preparing the test...\n");
	}

	@After
	public void tearDown() throws Exception {
		System.out.print("...Test is complete!\n");
	}

	@Test
	public void testGameDifficulty() {
		Date testDate = new Date();
		UserEntry userTest = new UserEntry("easy", "10 x 10", testDate, 2.02);
		assertEquals("The game difficulty is easy", "easy", userTest.getGameDif());
	}
	
	@Test
	public void testGameSize() {
		Date testDate = new Date();
		UserEntry boardTest = new UserEntry("hard", "10 x 10", testDate, 3.55);
		assertNotNull("should not be null", boardTest.getGameSize());
	}
}
