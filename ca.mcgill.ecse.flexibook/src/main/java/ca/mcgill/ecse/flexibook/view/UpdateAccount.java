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
import javafx.scene.control.ButtonType;
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
	private Label updateNewUsername;
	private Label updateNewPassword;
	private Label updateConfirmPassword;
	private Text header;
	private Text errorUpdateAccText;
	private Text errorDeleteAccText;
	private Text instruction11;
	private Text instruction12;
	private Text instruction21;
	private Text instruction22;
	private TextField newUsernameText;
	private PasswordField newPasswordText;
	private PasswordField confirmNewPasswordText;
	private Button updateButton;
	private Button deleteButton;
	private Hyperlink mainMenu;
	private GridPane updateAccGrid;
	private BorderPane updateAccRoot;
	private Scene updateAccScene;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setTitle("Account Management");


		//Initializing labels
		updateNewUsername = new Label("New Username:");
		updateNewPassword = new Label("New Password:");
		updateConfirmPassword = new Label("Confirm New Password:");

		//Initializing Texts
		header = new Text("Account Management");
		errorUpdateAccText = new Text();
		errorDeleteAccText = new Text();
		instruction11 = new Text("If you wish to update your account information, please enter your"
				+ " new username");
		instruction12 = new Text("and new password and proceed by clicking the 'Update Account' button below.");
		instruction21 = new Text("If you wish to delete your account, please click the 'Delete Account'"
				+ " button below.");
		instruction22 = new Text("Please note that all your current appointments will be canceled.");


		//Initializing text fields
		newUsernameText = new TextField();
		newPasswordText = new PasswordField();
		confirmNewPasswordText = new PasswordField();


		//Initializing buttons
		updateButton = new Button("Update Account");
		deleteButton = new Button("Delete Account");

		//Initializing hyperlink
		mainMenu = new Hyperlink("Return to Main Menu");

		//Initializing updateAccGrid Pane
		updateAccGrid = new GridPane();

		//Initializing Border Pane
		updateAccRoot = new BorderPane();


		// adjusting updateAccGrid
		updateAccGrid.setPadding(new Insets(10, 10, 10, 10));
		updateAccGrid.setVgap(10);
		updateAccGrid.setHgap(20);

		// adjusting border
		updateAccRoot.setMinSize(800, 500);
		updateAccRoot.setPadding(new Insets(15,15,15,15));
		updateAccRoot.setTop(header);
		updateAccRoot.setCenter(updateAccGrid);
		updateAccRoot.setBottom(mainMenu);

		//aligning panes
		BorderPane.setAlignment(header, Pos.TOP_CENTER);
		BorderPane.setAlignment(mainMenu, Pos.BOTTOM_CENTER);
		updateAccGrid.setAlignment(Pos.CENTER);


		// adding onto the updateAccGrid
		updateAccGrid.add(instruction11, 0, 0, 6, 1);
		updateAccGrid.add(instruction12, 0, 1, 6, 1);
		updateAccGrid.add(updateNewUsername, 0, 3);
		newUsernameText.setPromptText("New Username");
		updateAccGrid.add(newUsernameText, 1, 3);
		updateAccGrid.add(updateNewPassword, 0, 4);
		newPasswordText.setPromptText("New Password");
		updateAccGrid.add(newPasswordText, 1, 4);
		updateAccGrid.add(updateConfirmPassword, 0, 5);
		confirmNewPasswordText.setPromptText("Re-enter New Password");
		updateAccGrid.add(confirmNewPasswordText, 1, 5);
		updateAccGrid.add(updateButton, 0, 6);
		updateAccGrid.add(errorUpdateAccText, 1, 6);

		updateAccGrid.add(instruction21, 0, 8, 6, 1);
		updateAccGrid.add(instruction22, 0, 9, 6, 1);
		updateAccGrid.add(deleteButton, 0, 11);
		updateAccGrid.add(errorDeleteAccText, 1, 11);

		// confirm button action
		updateButton.setOnAction(e->{
			try {
				if(newPasswordText.getText().equals(confirmNewPasswordText.getText())) {
					FlexiBookController.updateAccount(FlexiBookApplication.getCurrentUser().getUsername(), newUsernameText.getText(),
							newPasswordText.getText());
					errorUpdateAccText.setText("");
				} else {
					errorUpdateAccText.setText("Your password and confirmation password do not match.");
				}

			} catch (InvalidInputException e1) {
				errorUpdateAccText.setText(e1.getMessage());
			}
		});

		// delete button action
		deleteButton.setOnAction(e->{
			Alert alert = new Alert(AlertType.WARNING, "Are you sure you want to delete your account? ",
					ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				try {
					FlexiBookController.deleteCustomerAccount(FlexiBookApplication.getCurrentUser().getUsername(),
							FlexiBookApplication.getCurrentUser().getUsername());
					errorDeleteAccText.setText("");
				} catch (InvalidInputException e1) {
					errorDeleteAccText.setText(e1.getMessage());
				}
			}


		});

		//        mainMenu.setOnAction(e->{
		//        	primaryStage.setTitle("");
		//        	primaryStage.setScene();
		//        });


		updateAccRoot.setStyle("-fx-background-color: LIGHTBLUE;");
		instruction11.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		instruction12.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		instruction21.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		instruction22.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		header.setStyle("-fx-font: normal bold 25px 'Verdana' ");
		updateNewUsername.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		updateNewPassword.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		updateConfirmPassword.setStyle("-fx-font: normal bold 15px 'Verdana' ");
		mainMenu.setStyle("-fx-font: normal 12px 'Verdana' ");

		//Initializing scenes
		updateAccScene = new Scene(updateAccRoot);

		window.setScene(updateAccScene);
		window.show();

	}

}
