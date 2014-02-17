package utils;

import models.Participant;
import models.RaceEvent;
import models.Time;
import sorter.SortFinishTime;
import sorter.SortName;
import sorter.SortStartTime;
import sorter.Sorter;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class Formatter {

	
    private RaceEvent raceEvent;
    private int lapAmount;

    /**
     * Creates a formatter
     * @param n number of laps for this event
     */
    public Formatter(int n) {
        raceEvent = new RaceEvent(n);
    }

    /**
     * Creates a formatter
     * @param s the timelimit
     */
    public Formatter (String s){
    	raceEvent = new RaceEvent(s);
    }

    /**
     * Actually returns the first line of the file.
     *
     * @param filePath - Path to file
     * @return A string with columns in the format
     * "columnOne; columnTwo; columnThree; columnFour" should be
     * returned.
     * @throws FileNotFoundException
     */
    public String readColumnNames(String filePath) throws FileNotFoundException {
        FileReader f = new FileReader();

        Iterator<String> itr = f.readFileByLine(filePath);
        String columns;
        if (itr.hasNext()) {
            columns = itr.next();
        } else {
            throw new IllegalStateException("Indatafilen är tom.");
        }
        return columns;
    }

    /**
     *
     * Generates a result string.
     * @param pathToStartFile URL to the start file
     * @param pathToFinishFile URL to the finish file
     * @param pathToNameFile URL to the name file
     * @param lapAmount The number of laps
     * @return A formated list containing the information
     * @throws FileNotFoundException
     */
    public String generateResultList(String pathToStartFile,
                                     String pathToFinishFile, String pathToNameFile, int lapAmount) throws FileNotFoundException {
        this.lapAmount = lapAmount;
        String[] pathsToFinishFiles = new String[1];
        pathsToFinishFiles[0] = pathToFinishFile;

        String resultString;
        resultString = generateHeader(pathToStartFile, pathsToFinishFiles, pathToNameFile);
        resultString += generateResults();

        return resultString;
    }

    /**
     * Generates a result list for multiple finishTime files
     *
     * @param pathToStartFile URL to the start file
     * @param pathsToFinishFiles URL to the finish files
     * @param pathToNameFile URL to the name file
     * @param lapAmount The number of laps
     * @return A formated list containing the information
     * @return
     * @throws FileNotFoundException
     */
    public String generateResultList(String pathToStartFile,
                                     String[] pathsToFinishFiles, String pathToNameFile, int lapAmount) throws FileNotFoundException {
        this.lapAmount = lapAmount;
        String resultString;
        resultString = generateHeader(pathToStartFile, pathsToFinishFiles, pathToNameFile);
        resultString += generateResults();

        return resultString;
    }

    /**
     * Generates the finished result string
     * @return the finished result string
     */
    private String generateResults() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= raceEvent.size(); i++) {
            Participant p = new Participant(i);
            Time totalTime = raceEvent.getStart(p).compareTo(raceEvent.getFinish(p));

            sb.append(i + "; " + raceEvent.getName(p) + "; ");
            sb.append((raceEvent.getRace(p).getFinishedLaps()) + "; ");
            sb.append(totalTime + "; ");
            sb.append(printTotalLapTimes(p));
            sb.append(printActualLapTimes(p));
            sb.append(raceEvent.getFinish(p) + "\n");
        }
        return sb.toString();
    }

    /**
     * generates the header and adds information from files to sorters.
     */
    private String generateHeader(String pathToStartFile,
                                  String[] pathToFinishFile, String pathToNameFile)
            throws FileNotFoundException {


        Sorter sort = new SortName();
        sort.insertInfo(pathToNameFile, "Namn", raceEvent);
        sort = new SortStartTime();
        sort.insertInfo(pathToStartFile, "StartTider", raceEvent);

        SortFinishTime sortFinishTime = new SortFinishTime();
        sortFinishTime.insertInfo(pathToFinishFile, "Maltider", raceEvent);

        StringBuilder sb = new StringBuilder();
        sb.append("StartNo; Name; #Laps; TotalTime; ");

        for (int i = 1; i <= lapAmount; i++)
            sb.append("Lap" + i + "; ");

        sb.append("Start; ");
        for (int i = 1; i < lapAmount; i++)
            sb.append("Checkpoint" + i + "; ");

        sb.append("Finish\n");
        return sb.toString();
    }


    private String printTotalLapTimes(Participant participant) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= lapAmount; i++) {

            sb.append(raceEvent.getRace(participant).getLapTime(i));
            sb.append("; ");
        }
        return sb.toString();
    }


    private String printActualLapTimes(Participant participant) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= lapAmount; i++) {
            sb.append(raceEvent.getRace(participant).getLapStartTime(i));
            sb.append("; ");
        }
        return sb.toString();
    }
}
