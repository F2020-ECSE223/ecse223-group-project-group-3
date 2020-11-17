package ca.mcgill.ecse223.flexibook.view;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SetupBusinessInfo extends Application{
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Group(), 500, 300);

		TextField nameField = new TextField();
		TextField  addressField = new TextField();
		TextField numberField = new TextField();
		TextField emailField = new TextField();
		Button reset = new Button();
		reset.setText("Confirm");
		reset.setStyle("-fx-background-color: dodgerblue; -fx-text-fill: white"); 
	    
	    
	    GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(20);
		
		Label businessInfo = new Label("Enter Your Business Info");
		Label name = new Label("Name of Business: ");
		Label address = new Label("Address: ");
		Label number = new Label("Phone Number: ");
		Label email = new Label("E-mail: ");
		
		
		businessInfo.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
		businessInfo.setStyle("-fx-text-fill: dodgerblue");
		name.setFont(Font.font ("Verdana", 15));
		address.setFont(Font.font ("Verdana", 15));
		number.setFont(Font.font ("Verdana", 15));
		email.setFont(Font.font ("Verdana", 15));
		
	    grid.add(businessInfo, 0, 0);
	    grid.add(name, 0, 2);
	    grid.add(nameField, 1, 2);
	    grid.add(address, 0, 3);
	    grid.add(addressField, 1, 3);
	    grid.add(number, 0, 4);
	    grid.add(numberField, 1, 4);
	    grid.add(email, 0, 5);
	    grid.add(emailField, 1, 5);
	    grid.add(reset, 1, 6);
	   
	    
	    Group root = (Group) scene.getRoot();
	    root.getChildren().add(grid);
	    primaryStage.setTitle("Set Up Business Info");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    reset.setOnAction(e->{
		    try {
		    	FlexiBookController.SetUpContactInfo(name.getText(), address.getText(), number.getText(), email.getText());		    	
		    }
		    catch(NullPointerException a){
		    	grid.add(new Label("Error"), 0, 7);
		    } catch (InvalidInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    
	    
	    
	    
	}

}

