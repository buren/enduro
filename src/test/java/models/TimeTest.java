package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TimeTest {
    Time time;

    @Before
    public void setUp() {
        time = new Time("12.12.12");
    }

    @Test
    public void testTimeToString() {
        Time time2 = new Time("05.10.00");
        assertEquals("05.10.00", time2.toString());
    }

    @Test
    public void testEmptyTime() {
        time = new Time();
        assertTrue(time.isEmpty());
    }

    @Test
    public void testEmptyTimeString() {
        time = new Time();
        assertEquals("--.--.--", time.toString());
    }

    @Test
    public void testEmptySeconds() {
        time = new Time(0);
        assertTrue(time.isEmpty());
    }

    @Test
    public void testGetDifference() {
        Time time2 = new Time("13.13.13");
        assertEquals("01.01.01", time.getDifference(time2).toString());
    }

    @Test
    public void testGetDifferenceIsAbsolute() {
        Time time2 = new Time("10.10.10");
        assertEquals("02.02.02", time.getDifference(time2).toString());
    }

    @Test
    public void testGetDifferenceWithEmptyTime() {
        Time time2 = new Time();
        Time shouldBeEmpty = time.getDifference(time2);
        assertTrue(shouldBeEmpty.isEmpty());
    }

    @Test
    public void testIsBefore() {
        Time time2 = new Time("13.00.00");
        assertTrue(time.isBefore(time2));
    }

    @Test
    public void testIsNotBefore() {
        Time time2 = new Time("09.30.00");
        assertFalse(time.isBefore(time2));
    }

    @Test
    public void testEqual() {
        Time time2 = new Time("12.12.12");
        assertTrue(time.equals(time2));
    }

    @Test
    public void testNotEqual() {
        Time time2 = new Time("12.00.00");
        assertFalse(time.equals(time2));
    }

    @Test
    public void testEqualWithNull() {
        assertFalse(time.equals(null));
    }

    @Test
    public void testCompareToEqual() {
        Time time2 = new Time("12.12.12");
        assertTrue(time.compareTo(time2) == 0);
    }

    @Test
    public void testCompareToLarger() {
        Time time2 = new Time("13.00.00");
        assertTrue(time.compareTo(time2) < 0);
    }

    @Test
    public void testCompareToSmaller() {
        Time time2 = new Time("11.00.00");
        assertTrue(time.compareTo(time2) > 0);
    }

    @Test
    public void testEqualWithSameObject() {
        assertTrue(time.equals(time));
    }

    @Test
    public void testEqualWithDifferentObject() {
        assertFalse(time.equals(this));
    }

}
