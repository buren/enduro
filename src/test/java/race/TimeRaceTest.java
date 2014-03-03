package race;

import models.Time;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeRaceTest {

    @Test
    public void testCopyTimeRace() {
        Race timeRace = new TimeRace(new Time("01.00.00"));
        assertTrue(timeRace.copy().getClass().equals(TimeRace.class));
    }

    @Test
    public void testCopyTimeRaceSameLimit() {
        Race timeRaceCopy = new TimeRace(new Time("01.00.00")).copy();;
        timeRaceCopy.setStart(new Time("12.00.00"));
        timeRaceCopy.addTime(new Time("13.00.00"));
        assertFalse(timeRaceCopy.testLimit());
    }

    @Test
    public void testLimitTimeRace() {
        Race timeRace = new TimeRace(new Time("01.00.00"));
        timeRace.setStart(new Time("12.00.00"));
        assertTrue(timeRace.testLimit());
    }

    @Test
    public void testOverLimitTimeRace() {
        Race timeRace = new TimeRace(new Time("01.00.00"));
        timeRace.setStart(new Time("12.00.00"));
        timeRace.addTime(new Time("13.00.00"));
        assertFalse(timeRace.testLimit());
    }
}
