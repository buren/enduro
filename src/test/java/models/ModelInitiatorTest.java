package models;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import race.LapRace;
import race.Race;

public class ModelInitiatorTest {

	@Before
	public void setUp() throws Exception {
		ModelInitiator mi = new ModelInitiator(null, null);
	}

	@After
	public void tearDown() throws Exception {
	}
}
