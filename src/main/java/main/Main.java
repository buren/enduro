package main;

import utils.Enduro;
import views.GUIFormater;
import views.GUIRegister;

public class Main {

	public static void main(String[] args) throws Exception {
		try{
			if (args[0].equals("register")) {
		
			try{
				new GUIRegister (args[1]);
			} catch(ArrayIndexOutOfBoundsException e){
				new GUIRegister (Enduro.getInstance().getResourcePath("registrationOutput.txt"));
			}
		} else if (args[0].equals("formater")) {
			new GUIFormater();
		} else {
			throw new Exception("Felaktigt argument.");
		}
		}catch(ArrayIndexOutOfBoundsException b){
			new GUIRegister (Enduro.getInstance().getResourcePath("registrationOutput.txt"));
		}
	}
}
