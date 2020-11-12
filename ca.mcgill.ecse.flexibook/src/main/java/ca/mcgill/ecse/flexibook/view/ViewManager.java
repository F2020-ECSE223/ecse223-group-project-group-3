package ca.mcgill.ecse.flexibook.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {
	
	private static Scene mainScene;
	
	public static void setScene(Scene scene) {
		mainScene = scene;
	}
	
	public static void startView(Stage stage) {
		new LoginPage(stage);
		//Setting title to the Stage 
		stage.setTitle("CSS Example"); 

		//Adding scene to the stage 
		stage.setScene(mainScene);

		//Displaying the contents of the stage 
		stage.show(); 
	}

}
