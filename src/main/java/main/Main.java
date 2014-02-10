package main;

import models.RaceEvent;
import utils.Controller;
import utils.Enduro;
import views.GUIFormatter;
import views.GUIRegister;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			if (args[0].equals("register")) {
				try {
					GUIRegister reg = new GUIRegister();
					Controller controller = new Controller(new RaceEvent(),reg, args[1]);
					controller.initiate();
				} catch (ArrayIndexOutOfBoundsException e) {
					GUIRegister reg = new GUIRegister();
					Controller controller = new Controller(new RaceEvent(), reg, Enduro.getInstance().getResourcePath(
							"registrationOutput.txt"));
					controller.initiate();
				}
			} else if (args[0].equals("formatter")) {
				new GUIFormatter();
			} else {
				throw new Exception("Felaktigt argument.");
			}
		} catch (ArrayIndexOutOfBoundsException b) {
			GUIRegister reg = new GUIRegister();
			Controller controller = new Controller(new RaceEvent(), reg, Enduro.getInstance().getResourcePath(
					"registrationOutput.txt"));
			controller.initiate();
		}
	}
}
