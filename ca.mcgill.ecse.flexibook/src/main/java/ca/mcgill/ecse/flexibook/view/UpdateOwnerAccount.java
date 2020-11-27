package ca.mcgill.ecse.flexibook.view;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateOwnerAccount extends Application {
	
	Stage window;
	
	//Initializing labels
	Label newPassword = new Label("New Password:");
	Label confirmPassword = new Label("Confirm New Password:");
	
	//Initializing Texts
	Text header = new Text("Account Management");
	Text errorUpdateAccText = new Text();
	Text errorDeleteAccText = new Text();
	Text instruction11 = new Text("If you wish to update your account information, please enter your"
			+ " new password");
	Text instruction12 = new Text("and proceed by clicking the 'Update Account' button below.");
	
	
	//Initializing text fields
	TextField newPasswordText = new PasswordField();
	TextField confirmPasswordText = new PasswordField();

	
	//Initializing buttons
	Button updateButton = new Button("Update Account");
	
	//Initializing hyperlink
	Hyperlink mainMenu = new Hyperlink("Return to Main Menu");
	
	//Initializing Grid Pane
	GridPane grid = new GridPane();
	
	//Initializing Border Pane
	BorderPane root = new BorderPane();
	
	//Initializing scenes
	Scene scene = new Scene(root);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        window = primaryStage;
        window.setTitle("Account Management");
        
        // adjusting grid
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(20);
        grid.setHgap(20);
        
        // adjusting border
        root.setMinSize(1200, 600);
        root.setPadding(new Insets(15,15,15,15));
        root.setTop(header);
        root.setCenter(grid);
        root.setBottom(mainMenu);
        
        //aligning panes
        BorderPane.setAlignment(header, Pos.TOP_CENTER);
        BorderPane.setAlignment(mainMenu, Pos.BOTTOM_CENTER);
		grid.setAlignment(Pos.CENTER);
		
        
        // adding onto the grid
		grid.add(instruction11, 0, 0, 6, 1);
		grid.add(instruction12, 0, 1, 6, 1);
	    grid.add(newPassword, 0, 3);
	    newPasswordText.setPromptText("New Password");
	    grid.add(newPasswordText, 1, 3);
	    grid.add(confirmPassword, 0, 4);
	    confirmPasswordText.setPromptText("Re-enter New Password");
	    grid.add(confirmPasswordText, 1, 4);
        grid.add(updateButton, 0, 6);
        grid.add(errorUpdateAccText, 1, 6);
        
        // confirm button action
        updateButton.setOnAction(e->{
			try {
				if(newPasswordText.getText().equals(confirmPasswordText.getText())) {
					FlexiBookController.updateAccount(FlexiBookApplication.getCurrentUser().getUsername(),
							FlexiBookApplication.getCurrentUser().getUsername(), newPasswordText.getText());
					Alert alert = new Alert(AlertType.CONFIRMATION, "Your password has been"
							+ "sucessfully update.");
					alert.showAndWait();
					errorUpdateAccText.setText("");
				} else {
					errorUpdateAccText.setText("Your password and confirmation password do not match.");
				}
				
			} catch (InvalidInputException e1) {
				errorUpdateAccText.setText(e1.getMessage());
			}
		});       

        root.setStyle("-fx-background-color: rgb(" + 255 + "," + 253 + ", " + 242 + ");");
        instruction11.setFont(Font.font("Comforta",15));
        instruction12.setFont(Font.font("Comforta",15));
        header.setFont(Font.font("Comforta", FontWeight.BOLD,35));
        header.setFill(Color.rgb(16,55,93));
        newPassword.setFont(Font.font("Comforta", FontWeight.BOLD,20));
		newPassword.setTextFill(Color.rgb(16,55,93));
		confirmPassword.setFont(Font.font("Comforta", FontWeight.BOLD,20));
		confirmPassword.setTextFill(Color.rgb(16,55,93));
		mainMenu.setFont(Font.font("Comforta",15));
		mainMenu.setTextFill(Color.rgb(16,55,93));
		updateButton.setStyle("-fx-background-color: rgb(" + 16 + "," + 55 + ", " + 93 + "); -fx-text-fill: rgb(" + 255 + "," + 253 + ", " + 242 + ");"); 
        
        window.setScene(scene);
        window.show();
		
	}

}
