package Run;

import MVC.Controller;
import MVC.Model;
import MVC.View;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(controller);
		controller.setView(view);
	}
}
