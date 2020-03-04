package mvc;

import controller.Controller;
import view.UserView;

public class MVC {

	public static void main(String args[]) {
		UserView ui = new UserView();
		Controller c = new Controller(ui);

		ui.setVisible(true);
	}

}