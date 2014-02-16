package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import sorter.SortFinishTime;
import sorter.SortName;
import sorter.SortStartTime;
import sorter.Sorter;
import models.Participant;
import models.RaceEvent;
import models.Time;

public class Formatter {
    private RaceEvent rc;
    private int lapAmount;

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
     * @param startTimes
     * @param finishTimes
     * @param nameList
     * @param lapAmount
     * @return
     */
    public String generateResultList(ArrayList<Time> startTimes,
                                     ArrayList<Time> finishTimes, ArrayList<String> nameList,
                                     int lapAmount) {
        this.lapAmount = lapAmount;
        if (startTimes.isEmpty() && finishTimes.isEmpty()) {
            return "Listorna är tomma!";
        } else {
            StringBuilder sb = new StringBuilder();

            sb.append("StartNo; Name; TotalTime; StartTime; ResultTime\n");

            int count = startTimes.size();
            for (int i = 0; i < count; i++) {
                Time totalTime = startTimes.get(i)
                        .compareTo(finishTimes.get(i));
                sb.append(i + 1 + "; " + nameList.get(i) + "; " + totalTime
                        + "; " + startTimes.get(i) + "; " + finishTimes.get(i)
                        + "\n");

            }
            return sb.toString();
        }
    }

    /**
     * @param pathToStartFile
     * @param pathToFinishFile
     * @return
     * @throws FileNotFoundException
     */
    public String generateResultList(String pathToStartFile,
                                     String pathToFinishFile, String pathToNameFile, int lapAmount) throws FileNotFoundException {
        this.lapAmount = lapAmount;
        rc = new RaceEvent(lapAmount);

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
     * @param pathToStartFile
     * @param pathsToFinishFiles
     * @param pathToNameFile
     * @param lapAmount
     * @return
     * @throws FileNotFoundException
     */
    public String generateResultList(String pathToStartFile,
                                     String[] pathsToFinishFiles, String pathToNameFile, int lapAmount) throws FileNotFoundException {
        this.lapAmount = lapAmount;
        rc = new RaceEvent(lapAmount);

        String resultString;
        resultString = generateHeader(pathToStartFile, pathsToFinishFiles, pathToNameFile);
        resultString += generateResults();

        return resultString;
    }


    private String generateResults() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= rc.size(); i++) {
            Participant p = new Participant(i);
            Time totalTime = rc.getStart(p).compareTo(rc.getFinish(p));

            sb.append(i + "; " + rc.getName(p) + "; ");
            sb.append((rc.getRace(p).getCurrentLap()) + "; ");
            sb.append(totalTime + "; ");
            sb.append(printTotalLapTimes(p));
            sb.append(printActualLapTimes(p));
            sb.append(rc.getFinish(p) + "\n");
        }
        return sb.toString();
    }

    private String generateHeader(String pathToStartFile,
                                  String[] pathToFinishFile, String pathToNameFile)
            throws FileNotFoundException {

        SortFinishTime sortFinishTime = new SortFinishTime();
        sortFinishTime.insertInfo(pathToFinishFile, "Maltider", rc);

        Sorter sort = new SortName();
        sort.insertInfo(pathToNameFile, "Namn", rc);
        sort = new SortStartTime();
        sort.insertInfo(pathToStartFile, "StartTider", rc);

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

    /**
     * @param participant
     * @param
     * @return
     */
    private String printTotalLapTimes(Participant participant) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= lapAmount; i++) {

            sb.append(rc.getRace(participant).getLapTime(i));
            sb.append("; ");
        }
        return sb.toString();
    }

    /**
     * @param participant
     * @param
     * @return
     */
    private String printActualLapTimes(Participant participant) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= lapAmount; i++) {
            sb.append(rc.getRace(participant).getLapStartTime(i));
            sb.append("; ");
        }
        return sb.toString();
    }
}
