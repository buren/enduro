package main;

import views.GUIFormater;
import views.GUIRegister;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args[0].equals("register")) {
			new GUIRegister();
		} else if (args[0].equals("formater")) {
			new GUIFormater();
		} else {
			throw new Exception("Felaktigt argument.");
		}
	}
}
