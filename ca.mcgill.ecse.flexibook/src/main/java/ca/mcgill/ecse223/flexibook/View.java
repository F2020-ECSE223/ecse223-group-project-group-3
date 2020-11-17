package ca.mcgill.ecse223.flexibook;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View extends Application{
	
	Button button;
	
	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Title of the Windows");
		button = new Button();
		button.setText("Click me");
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
			

}
