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
					Controller controller = new Controller(new RaceEvent(
							Integer.parseInt(args[1])), reg, args[2]);
					controller.initiate();
				} catch (ArrayIndexOutOfBoundsException e) {
					GUIRegister reg = new GUIRegister();
					Controller controller = new Controller(new RaceEvent(
							Integer.parseInt(args[1])), reg, Enduro
							.getInstance().getResourcePath(
									"registrationOutput.txt"));
					controller.initiate();
				}
			} else if (args[0].equals("formater")) {
				new GUIFormatter();
			} else {
				throw new Exception("Felaktigt argument.");
			}
		} catch (ArrayIndexOutOfBoundsException b) {
			GUIRegister reg = new GUIRegister();
			Controller controller = new Controller(new RaceEvent(
					Integer.parseInt(args[1])), reg, Enduro.getInstance()
					.getResourcePath("registrationOutput.txt"));
			controller.initiate();
		}
	}
}
