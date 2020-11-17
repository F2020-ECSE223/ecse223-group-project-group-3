package ca.mcgill.ecse.flexibook.view;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeleteAccount extends Application {
	
	Stage window;
	
	//Initializing labels
	Label username = new Label("Username:");
	Label password = new Label("Password:");
	Label newUsername = new Label("New Username:");
	Label newPassword = new Label("New Password:");
	
	//Initializing Texts
	Text header = new Text("Update Account Information");
	Text errorUpdateText = new Text();
	Text instruction1 = new Text("Please enter your current username and password");
	Text instruction2 = new Text("Please enter your new username and new password");
	
	//Initializing text fields
	TextField usernameText = new TextField();
	TextField passwordText = new TextField();
	TextField newUsernameText = new TextField();
	TextField newPasswordText = new TextField();
	
	//Initializing buttons
	Button deleteAccButton = new Button("Delete Account");
	
	//Initializing hyperlink
	Hyperlink goBackLink = new Hyperlink("Go Back");
	
	//Initializing Grid Pane
	GridPane deleteAccGrid = new GridPane();
	
	//Initializing Border Pane
	BorderPane deleteAccPane = new BorderPane();
	
	//Initializing scenes
	Scene deleteAccScene = new Scene(deleteAccPane);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        window = primaryStage;
        window.setTitle("Update Account Information");
        
        // adjusting grid
        deleteAccGrid.setPadding(new Insets(10, 10, 10, 10));
        deleteAccGrid.setVgap(10);
        deleteAccGrid.setHgap(20);
        
        // adjusting border
        deleteAccPane.setMinSize(800, 500);
        deleteAccPane.setPadding(new Insets(15,15,15,15));
        deleteAccPane.setTop(header);
        deleteAccPane.setCenter(deleteAccGrid);
        deleteAccPane.setTop(goBackLink);
        
        //aligning panes
        BorderPane.setAlignment(header, Pos.TOP_CENTER);
        BorderPane.setAlignment(goBackLink, Pos.TOP_LEFT);
        deleteAccGrid.setAlignment(Pos.CENTER);
		
        
        // adding onto the grid
        deleteAccGrid.add(instruction1, 0, 0);
        deleteAccGrid.add(username, 0, 1);
        usernameText.setPromptText("Username");
        deleteAccGrid.add(usernameText, 1, 1);
        deleteAccGrid.add(password, 0, 2);
        passwordText.setPromptText("Password");
        deleteAccGrid.add(passwordText, 1, 2);
        deleteAccGrid.add(deleteAccButton, 0, 9);
        
        deleteAccButton.setOnAction(e->{
			try {
				FlexiBookController.updateAccount(usernameText.getText(), newUsernameText.getText(),
						newPasswordText.getText());
				errorUpdateText.setText("");
			} catch (InvalidInputException e1) {
				errorUpdateText.setText(e1.getMessage());
			}
		});
        
        
//        goBackLink.setOnAction(e->{
//        	primaryStage.setTitle("Update Account Information");
//        	primaryStage.setScene(scene);
//        });
        

        deleteAccPane.setStyle("-fx-background-color: BEIGE;");
        instruction1.setStyle("-fx-font: normal italic 11px 'Verdana' ");
        instruction2.setStyle("-fx-font: normal italic 11px 'Verdana' ");
        header.setStyle("-fx-font: normal bold 25px 'Verdana' ");
        username.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		password.setStyle("-fx-font: normal bold 15px 'Verdana' ");  
		newUsername.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		newPassword.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		goBackLink.setStyle("-fx-font: normal 12px 'Verdana' ");
        
        Scene scene = new Scene(deleteAccPane);
        window.setScene(scene);
        window.show();
		
	}

}
