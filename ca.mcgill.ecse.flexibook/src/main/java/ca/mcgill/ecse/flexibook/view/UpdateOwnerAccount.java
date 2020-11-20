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

public class UpdateOwnerAccount extends Application {

	Stage window;
	private Label newOwnerPassword;
	private Label confirmOwnerPassword;
	private Text ownerHeader;
	private Text errorUpdateOwnerAccText;
	private Text instructionOwner11;
	private Text instructionOwner12;
	private PasswordField newOwnerPasswordText;
	private PasswordField confirmOwnerPasswordText;
	private Button updateAccButton;
	private Hyperlink ownerMainMenu;
	private GridPane updateOwnerAccGrid;
	private BorderPane updateOwnerAccRoot;
	private Scene scene;



	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setTitle("Account Management");

		//Initializing labels
		newOwnerPassword = new Label("New Password:");
		confirmOwnerPassword = new Label("Confirm New Password:");

		//Initializing Texts
		ownerHeader = new Text("Account Management");
		errorUpdateOwnerAccText = new Text();
		instructionOwner11 = new Text("If you wish to update your account information, please enter your"
				+ " new password");
		instructionOwner12 = new Text("and proceed by clicking the 'Update Account' button below.");


		//Initializing text fields
		newOwnerPasswordText = new PasswordField();
		confirmOwnerPasswordText = new PasswordField();


		//Initializing buttons
		updateAccButton = new Button("Update Account");

		//Initializing hyperlink
		ownerMainMenu = new Hyperlink("Return to Main Menu");

		//Initializing updateOwnerAccGrid Pane
		updateOwnerAccGrid = new GridPane();

		//Initializing Border Pane
		updateOwnerAccRoot = new BorderPane();



		// adjusting updateOwnerAccGrid
		updateOwnerAccGrid.setPadding(new Insets(10, 10, 10, 10));
		updateOwnerAccGrid.setVgap(10);
		updateOwnerAccGrid.setHgap(20);

		// adjusting border
		updateOwnerAccRoot.setMinSize(800, 500);
		updateOwnerAccRoot.setPadding(new Insets(15,15,15,15));
		updateOwnerAccRoot.setTop(ownerHeader);
		updateOwnerAccRoot.setCenter(updateOwnerAccGrid);
		updateOwnerAccRoot.setBottom(ownerMainMenu);

		//aligning panes
		BorderPane.setAlignment(ownerHeader, Pos.TOP_CENTER);
		BorderPane.setAlignment(ownerMainMenu, Pos.BOTTOM_CENTER);
		updateOwnerAccGrid.setAlignment(Pos.CENTER);


		// adding onto the updateOwnerAccGrid
		updateOwnerAccGrid.add(instructionOwner11, 0, 0, 6, 1);
		updateOwnerAccGrid.add(instructionOwner12, 0, 1, 6, 1);
		updateOwnerAccGrid.add(newOwnerPassword, 0, 3);
		newOwnerPasswordText.setPromptText("New Password");
		updateOwnerAccGrid.add(newOwnerPasswordText, 1, 3);
		updateOwnerAccGrid.add(confirmOwnerPassword, 0, 4);
		confirmOwnerPasswordText.setPromptText("Re-enter New Password");
		updateOwnerAccGrid.add(confirmOwnerPasswordText, 1, 4);
		updateOwnerAccGrid.add(updateAccButton, 0, 6);
		updateOwnerAccGrid.add(errorUpdateOwnerAccText, 1, 6);

		// confirm button action
		updateAccButton.setOnAction(e->{
			try {
				if(newOwnerPasswordText.getText().equals(confirmOwnerPasswordText.getText())) {
					FlexiBookController.updateAccount(FlexiBookApplication.getCurrentUser().getUsername(),
							FlexiBookApplication.getCurrentUser().getUsername(), newOwnerPasswordText.getText());
					errorUpdateOwnerAccText.setText("");
				} else {
					errorUpdateOwnerAccText.setText("Your password and confirmation password do not match.");
				}

			} catch (InvalidInputException e1) {
				errorUpdateOwnerAccText.setText(e1.getMessage());
			}
		});

		//        ownerMainMenu.setOnAction(e->{
		//        	primaryStage.setTitle("Main Menu");
		//        	primaryStage.setScene(ownerMainScene);
		//        });


		updateOwnerAccRoot.setStyle("-fx-background-color: LIGHTBLUE;");
		instructionOwner11.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		instructionOwner12.setStyle("-fx-font: normal italic 11px 'Verdana' ");
		ownerHeader.setStyle("-fx-font: normal bold 25px 'Verdana' ");
		newOwnerPassword.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
		confirmOwnerPassword.setStyle("-fx-font: normal bold 15px 'Verdana' ");
		ownerMainMenu.setStyle("-fx-font: normal 12px 'Verdana' ");

		//Initializing scenes
		scene = new Scene(updateOwnerAccRoot);

		window.setScene(scene);
		window.show();

	}

}
