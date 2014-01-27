package main;

import models.Participant;
import utils.Enduro;
import utils.FileReader;
import utils.FileWriter;
import views.GUI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args ){
        new GUI();
        ArrayList<Participant> participants = new ArrayList<Participant>();
        Participant p;
        Enduro enduro = Enduro.getInstance();
        for (int i = 0; i < 8; i++) {
            p = new Participant(String.valueOf(i));
            p.setStartTime("1.00.0" + String.valueOf(i));
            p.setEndTime("2.00.0"   + String.valueOf(i));
            participants.add(p);
        }
        // Generate start file
        ArrayList<String> startLines = new ArrayList<String>();
        for (Participant ps : participants) {
            startLines.add(ps.getId() + "; " + ps.getStartTime());
        }
        FileWriter.writeFile(enduro.getResourcePath("start_	file.csv"), startLines.iterator());

        // Generate end file
        ArrayList<String> endLines = new ArrayList<String>();
        for (Participant ps : participants) {
            endLines.add(ps.getId() + "; " + ps.getEndTime());
        }
        FileWriter.writeFile(enduro.getResourcePath("end_file.csv"), endLines.iterator());


        FileReader fileReader = new FileReader();
        Iterator<String> start = null;
        Iterator<String> end = null;
        try {
            start = fileReader.readFileByLine(enduro.getResourcePath("start_file.csv"));
            end = fileReader.readFileByLine(enduro.getResourcePath("end_file.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Generate result file
        ArrayList<String> resultLines = new ArrayList<String>();
        resultLines.add("StartNo; TotalTime; StartTime; ResultTime");
        while (start.hasNext()) {
            String[] startRow = start.next().split(";");
            resultLines.add(startRow[0] + "; --.--.--;" + startRow[1] + "; " + end.next().split(";")[1]);
        }
        FileWriter.writeFile(enduro.getResourcePath("result_file.csv"), resultLines.iterator());
    }

}
