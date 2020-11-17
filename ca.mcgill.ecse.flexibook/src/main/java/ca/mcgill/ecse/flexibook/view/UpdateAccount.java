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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateAccount extends Application {
	
	Stage window;
	
	//Initializing labels
	Label username = new Label("Username:");
	Label password = new Label("Password:");
	Label newUsername = new Label("New Username:");
	Label newPassword = new Label("New Password:");
	Label usernameDel = new Label("Username:");
	Label username2Del = new Label("Username To Delete:");
	
	//Initializing Texts
	Text header = new Text("Update Account Information");
	Text header2 = new Text("                                Delete Account");
	Text errorUpdateAccText = new Text();
	Text errorDeleteAccText = new Text();
	Text instruction1 = new Text("Please enter your current username and password");
	Text instruction2 = new Text("Please enter your new username and new password");
	Text instruction3 = new Text("Please enter your username.");
	Text instruction4 = new Text("Please enter the username of the account you wish to delete.");
	Text instruction5 = new Text("Note that you do not have permission to delete any account other than yours.");
	
	//Initializing text fields
	TextField usernameText = new TextField();
	TextField passwordText = new PasswordField();
	TextField newUsernameText = new TextField();
	TextField newPasswordText = new PasswordField();
	TextField usernameDelText = new TextField();
	TextField username2DelText = new TextField();
	
	//Initializing buttons
	Button confirmButton = new Button("Update Account");
	Button deleteButton = new Button("Delete Account");
	
	//Initializing hyperlink
	Hyperlink deleteLink = new Hyperlink("Delete Account");
	Hyperlink goBackLink = new Hyperlink("Go Back");
	
	//Initializing Grid Pane
	GridPane grid = new GridPane();
	GridPane deleteAccGrid = new GridPane();
	
	//Initializing Border Pane
	BorderPane root = new BorderPane();
	BorderPane deleteAccPane = new BorderPane();
	
	//Initializing scenes
	Scene deleteAccScene = new Scene(deleteAccPane);
	Scene scene = new Scene(root);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        window = primaryStage;
        window.setTitle("Update Account Information");
        
        // adjusting grid
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(20);
        
        // adjusting border
        root.setMinSize(800, 500);
        root.setPadding(new Insets(15,15,15,15));
        root.setTop(header);
        root.setCenter(grid);
        root.setBottom(deleteLink);
        
        //aligning panes
        BorderPane.setAlignment(header, Pos.TOP_CENTER);
        BorderPane.setAlignment(deleteLink, Pos.BOTTOM_CENTER);
		grid.setAlignment(Pos.CENTER);
		
        
        // adding onto the grid
		grid.add(instruction1, 0, 0);
        grid.add(username, 0, 1);
        usernameText.setPromptText("Username");
        grid.add(usernameText, 1, 1);
        grid.add(password, 0, 2);
        passwordText.setPromptText("Password");
        grid.add(passwordText, 1, 2);
        
        grid.add(instruction2, 0, 5);
        grid.add(newUsername, 0, 6);
        newUsernameText.setPromptText("New Username");
        grid.add(newUsernameText, 1, 6);
        grid.add(newPassword, 0, 7);
        newPasswordText.setPromptText("New Password");
        grid.add(newPasswordText, 1, 7);
        grid.add(confirmButton, 0, 9);
        grid.add(errorUpdateAccText, 1, 8);
        
        // confirm button action
        confirmButton.setOnAction(e->{
			try {
				FlexiBookController.updateAccount(usernameText.getText(), newUsernameText.getText(),
						newPasswordText.getText());
				errorUpdateAccText.setText("");
			} catch (InvalidInputException e1) {
				errorUpdateAccText.setText(e1.getMessage());
			}
		});
        
        deleteLink.setOnAction(e->{
        	primaryStage.setTitle("Delete Account");
        	primaryStage.setScene(deleteAccScene);
        });
        
        // adjusting deleteAccGrid
        deleteAccGrid.setPadding(new Insets(10, 10, 10, 10));
        deleteAccGrid.setVgap(10);
        deleteAccGrid.setHgap(20);
        
        // adjusting delete border pane
        deleteAccPane.setMinSize(800, 500);
        deleteAccPane.setPadding(new Insets(15,15,15,15));
        deleteAccPane.setCenter(deleteAccGrid);
        deleteAccGrid.setAlignment(Pos.CENTER);
        
        // adding onto delete grid
        deleteAccGrid.add(instruction3, 0, 0);
        deleteAccGrid.add(usernameDel, 0, 1);
        deleteAccGrid.add(instruction4, 0, 4);
        deleteAccGrid.add(instruction5, 0, 5);
        deleteAccGrid.add(username2Del, 0, 6);
        deleteAccGrid.add(usernameDelText, 1, 1);
        deleteAccGrid.add(username2DelText, 1, 6);
        usernameDelText.setPromptText("Your Username");
        username2DelText.setPromptText("Username To Delete");
        deleteAccGrid.add(deleteButton, 0, 8);
        deleteAccGrid.add(errorDeleteAccText, 1, 8);
        
        deleteAccPane.setTop(header2);
        deleteAccPane.setBottom(goBackLink);
        
        // delete button action
        deleteButton.setOnAction(e->{
			try {
				FlexiBookController.deleteCustomerAccount(usernameDelText.getText(),
						username2DelText.getText());
				errorDeleteAccText.setText("");
			} catch (InvalidInputException e1) {
				errorDeleteAccText.setText(e1.getMessage());
			}
		});
        
        goBackLink.setOnAction(e->{
        	primaryStage.setTitle("Update Account Information");
        	primaryStage.setScene(scene);
        });
        

        root.setStyle("-fx-background-color: LIGHTBLUE;");
        instruction1.setStyle("-fx-font: normal italic 11px 'Verdana' ");
        instruction2.setStyle("-fx-font: normal italic 11px 'Verdana' ");
        header.setStyle("-fx-font: normal bold 25px 'Verdana' ");
        username.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		password.setStyle("-fx-font: normal bold 15px 'Verdana' ");  
		newUsername.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		newPassword.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		deleteLink.setStyle("-fx-font: normal 12px 'Verdana' ");
		usernameDel.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		username2Del.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		deleteAccPane.setStyle("-fx-background-color: LIGHTBLUE;");
		instruction3.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		instruction4.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		instruction5.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		header2.setStyle("-fx-font: normal bold 25px 'Verdana' ");
        
        window.setScene(scene);
        window.show();
		
	}

}
