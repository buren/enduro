package race;

import models.Time;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StageRaceTest {
    Race stageRace;

    @Before
    public void setUp() {
        stageRace = new StageRace();
    }

    @Test
    public void testPrintEmpty() {
        String expected = "; --.--.--; 1; --.--.--; --.--.--; --.--.--";
        assertEquals(expected, stageRace.print(1));
    }

    @Test
    public void testPrintStartTime() {
        stageRace.addStartTime(new Time("12.00.00"));
        String expected = "; --.--.--; 1; --.--.--; 12.00.00; --.--.--";
        assertEquals(expected, stageRace.print(1));
    }

    @Test
    public void testPrintFinishTime() {
        stageRace.addFinishTime(new Time("13.00.00"));
        String expected = "; --.--.--; 1; --.--.--; --.--.--; 13.00.00";
        assertEquals(expected, stageRace.print(1));
    }

    @Test
    public void testPrint() {
        stageRace.addStartTime(new Time("12.00.00"));
        stageRace.addFinishTime(new Time("13.00.00"));
        String expected = "; 01.00.00; 1; 01.00.00; 12.00.00; 13.00.00";
        assertEquals(expected, stageRace.print(1));
    }

    @Test
    public void testPrintTwoStages() {
        stageRace.addStartTime(new Time("12.00.00"));
        stageRace.addFinishTime(new Time("13.00.00"));
        stageRace.addStartTime(new Time("14.00.00"));
        stageRace.addFinishTime(new Time("15.00.00"));
        String expected = "; 02.00.00; 2; 01.00.00; 01.00.00; 12.00.00; 13.00.00; 14.00.00; 15.00.00";
        assertEquals(expected, stageRace.print(2));
    }

    @Test
    public void testPrintTwoStagesAddTimeInReverseOrder() {
        stageRace.addFinishTime(new Time("13.00.00"));
        stageRace.addStartTime(new Time("12.00.00"));
        stageRace.addFinishTime(new Time("15.00.00"));
        stageRace.addStartTime(new Time("14.00.00"));
        String expected = "; 02.00.00; 2; 01.00.00; 01.00.00; 12.00.00; 13.00.00; 14.00.00; 15.00.00";
        assertEquals(expected, stageRace.print(2));
    }

    @Test
    public void testPrintOverPrintLimit() {
        String expected = "; --.--.--; 1; --.--.--; --.--.--; --.--.--; --.--.--; --.--.--; --.--.--";
        assertEquals(expected, stageRace.print(2));
    }

    @Test
    public void testPrintHeaderSimple() {
        String expected = "; Totaltid; #Etapper; Etapp1; Start1; Mal1\n";
        assertEquals(expected, stageRace.printHeader(1));
    }

    @Test
    public void testPrintHeaderLong() {
        String expected = "; Totaltid; #Etapper; Etapp1; Etapp2; Etapp3; Start1; Mal1; Start2; Mal2; Start3; Mal3\n";
        assertEquals(expected, stageRace.printHeader(3));
    }

    @Test
    public void testLimit() {
        assertTrue(stageRace.testLimit());
    }

    @Test
    public void testCopy() {
        assertTrue(stageRace.copy().getClass().equals(StageRace.class));
    }


}
