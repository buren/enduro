package main;

import utils.Enduro;
import views.GUIFormatter;
import views.GUIRegister;
import controllers.FormatterController;
import controllers.RegisterController;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			if (args[0].equals("register")) {
				try {
					RegisterController regCont = new RegisterController(args[1]);
					GUIRegister reg = new GUIRegister(regCont);
				} catch (ArrayIndexOutOfBoundsException e) {
					RegisterController regCont = new RegisterController(Enduro
							.getInstance().getResourcePath(
									"registrationOutput.txt"));
					GUIRegister reg = new GUIRegister(regCont);
				}
			} else if (args[0].equals("formatter")) {
				FormatterController formCont = new FormatterController();
				new GUIFormatter(formCont);
			} else {
				throw new Exception("Felaktigt argument.");
			}
		} catch (ArrayIndexOutOfBoundsException b) {

			RegisterController regCont = new RegisterController(Enduro
					.getInstance().getResourcePath("registrationOutput.txt"));
			GUIRegister reg = new GUIRegister(regCont);
		}
	}
}
