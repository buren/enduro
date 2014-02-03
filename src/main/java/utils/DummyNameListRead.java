package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import models.Participant;

public class DummyNameListRead {
	private FileReader fr;
	
	public DummyNameListRead() {
		fr = new FileReader();
		
	}
	
	public ArrayList<Participant> readFile(String fileName) {
		ArrayList<Participant> list = new ArrayList<Participant>();
		try {
			Iterator itr =fr.readFileByLine(fileName);
			itr.next();
			
			Participant p;
			while(itr.hasNext()) {
				String line = (String) itr.next();
				String[] lines = line.split("; ");
				p = new Participant(Integer.parseInt(lines[0]));
				p.setName(lines[1]);
				list.add(p);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
