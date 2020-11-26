package ca.mcgill.ecse.flexibook.view;

import javafx.application.Application;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccountProfile extends Application{
	
	private Text accUsername;
	private Text accNoShow;
	
	private Hyperlink viewProfile;
	private Hyperlink backAccMenu;
	private Hyperlink backMainMenu;
	
	public static void main (String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
	}

}
