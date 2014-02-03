package utils;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Participant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadPersonalInfo {
	ArrayList <Participant> list;
	Participant p;
	   @Before
	    public void setUp() throws Exception {
		   list = new ArrayList<Participant>();
		   p = new Participant(1);
		   p.setName("Anders Asson");
		   list.add(p);
		   
		   p = new Participant(2);
		   p.setName("Bengt Bsson");
		   list.add(p);
		   
		   p = new Participant(3);
		   p.setName("Chris Csson");
		   list.add(p);
		   
		   p = new Participant(4);
		   p.setName("David Dsson");
		   list.add(p);
		   
		   p = new Participant(5);
		   p.setName("Erik Esson");
		   list.add(p);
		   
		   
	    }
	   
	   @After
	   public void Teardown(){
		   p = null;
		   list = null;
	   }
	  
	   @Test
	   public void readParticipantInfo() {
		   DummyNameListRead d = new DummyNameListRead();
		   ArrayList<Participant> pL = new ArrayList<Participant>();
		   pL = d.readFile(Enduro.getInstance().getResourcePath("../../src/test/resources/acceptanstester/iteration1/acceptanstest3_4/namnfil.txt"));
		   for (int i=0 ; i < list.size(); i++){
			   assertEquals(pL.get(i).getName(), list.get(i).getName());
			   assertEquals(pL.get(i).getId(), list.get(i).getId());
		   }
		  // assertEquals(pL, list);
	   }
}
