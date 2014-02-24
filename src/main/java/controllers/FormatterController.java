package controllers;

import java.io.FileNotFoundException;
import java.util.Iterator;

import models.ModelInitiator;
import models.RaceEvent;
import models.Time;
import race.LapRace;
import race.Race;
import race.SimpleRace;
import race.TimeRace;
import utils.FileReader;
import utils.FileWriter;

public class FormatterController {
    public static final int SIMPLE_RACE = 0, LAP_RACE = 1, TIME_RACE = 2;
    private RaceEvent raceEvent;

    /**
     * Writes the Strings within the Iterator to the given filepath.
     *
     * @param filePath , URL-address of target folder.
     * @param iter     , iterator of strings to be written.
     */
    public static void writeToFile(String filePath, Iterator<String> iter) {
        FileWriter.writeFile(filePath, iter);
    }

    /**
     * Returns a string of the results table generated by the files at each
     * location.
     *
     * @param startPath  , URL-address of file containing start times.
     * @param finishPath , URL-address of file containing start times.
     * @param namePath   , URL-address of file containing start times.
     * @param printLimit , Number of laps to be displayed in the result table.
     * @return A fully formatted result table as a string
     * @throws FileNotFoundException
     */
    public String result(String startPath, String[] finishPath, String namePath, int raceType, String limit, int printLimit) throws FileNotFoundException {
        Race race;
        switch (raceType) {
            case SIMPLE_RACE:
                race = new SimpleRace();
                break;
            case LAP_RACE:
                int lapLimit = Integer.parseInt(limit);
                race = new LapRace(lapLimit);
                break;
            case TIME_RACE:
                Time timeLimit = new Time(limit);
                race = new TimeRace(timeLimit);
                break;
            default:
                throw new IllegalArgumentException("Racetypen finns inte!");
        }
        raceEvent = new RaceEvent(race);
        FileReader fr = new FileReader();

        Iterator fileIterator = fr.readFileByLine(namePath);
        ModelInitiator initiator = new ModelInitiator(fileIterator, raceEvent);

        fileIterator = fr.readFileByLine(startPath);
        initiator.registerStartTimes(fileIterator);

        Iterator[] iterators = new Iterator[finishPath.length];
        for (int i = 0; i < finishPath.length; i++) {
            iterators[i] = fr.readFileByLine(finishPath[i]);
        }
        initiator.registerFinishTimes(iterators);
        
        return raceEvent.print(printLimit);
    }
    
    public void resetRace(){
    	raceEvent = null;
    }
}
