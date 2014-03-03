package race;

import models.Time;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LapRaceTest {


    @Test
    public void testCopyLapRace() {
        Race lapRace = new LapRace(1);
        assertTrue(lapRace.copy().getClass().equals(LapRace.class));
    }

    @Test
    public void testCopyLapRaceSameLimit() {
        Race lapRaceCopy = new LapRace(1).copy();
        lapRaceCopy.addFinishTime(new Time("12.12.12"));
        assertFalse(lapRaceCopy.testLimit());
    }

    @Test
    public void testLimitLapRace() {
        Race lapRace = new LapRace(3);
        assertTrue(lapRace.testLimit());
    }

    @Test
    public void testOverLimitLapRaceOver() {
        Race lapRace = new LapRace(3);
        lapRace.addFinishTime(new Time("00.00.00"));
        lapRace.addFinishTime(new Time("00.01.00"));
        lapRace.addFinishTime(new Time("00.02.00"));
        assertFalse(lapRace.testLimit());
    }

}
