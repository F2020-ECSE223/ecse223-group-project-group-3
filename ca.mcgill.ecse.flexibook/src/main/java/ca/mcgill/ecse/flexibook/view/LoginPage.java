package ca.mcgill.ecse.flexibook.view;
import javax.swing.JFrame;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 

import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  

public class LoginPage extends JFrame{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	//creating label username 
	private Text usernameText;      

	//creating label password 
	private Text passwordText; 
	
	//creating label username for sign up
	private Text usernameText2;      

	//creating label password for sign up
	private Text passwordText2; 
	
	//creating label for confirm password
	private Text confirmPasswordText; 
	
	//creating label error for login message
	private Text errorLoginText; 
	
	//creating label error for login message
	private Text errorSignUpText; 

	//Creating Text Field for username        
	private TextField usernameTextField;       

	//Creating Text Field for password        
	private PasswordField passwordTextField;  
	
	//creating Text Field username for sign up
	private TextField usernameTextField2;      

	//creating Text Field password for sign up
	private PasswordField passwordTextField2; 

	//creating Text Field for confirm password
	private PasswordField confirmPasswordTextField; 

	//Creating login button 
	private Button loginButton; 
	
	//Creating signup button 
	private Button signupButton;

	//Creating a Grid Pane 
	private GridPane gridPane;  
	
	//Creating a scene object 
	private Scene scene; 



	public LoginPage(Stage stage) {
		initComponents(stage);
	}

	private void initComponents(Stage stage) {
		//initializing labels
		usernameText = new Text("Username");       
		passwordText = new Text("Password"); 
		usernameText2 = new Text("Username");       
		passwordText2 = new Text("Password"); 
		confirmPasswordText = new Text("Confirm Passsword");
		errorLoginText = new Text();
		errorSignUpText = new Text();
		
		//initializing text fields
		usernameTextField = new TextField();       
		passwordTextField = new PasswordField(); 
		usernameTextField2 = new TextField();       
		passwordTextField2 = new PasswordField(); 
		confirmPasswordTextField = new PasswordField(); 

		//initializing buttons 
		loginButton = new Button("Login"); 
		signupButton = new Button("Sign up");

		//initializing Grid Pane 
		gridPane = new GridPane();    

		//Setting size for the pane 
		gridPane.setMinSize(400, 200); 

		//Setting the padding  
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 

		//Setting the vertical and horizontal gaps between the columns 
		gridPane.setVgap(5); 
		gridPane.setHgap(5);       

		//Setting the Grid alignment 
		gridPane.setAlignment(Pos.CENTER); 

		//Arranging all the nodes in the grid 
		gridPane.add(errorLoginText, 0, 0);

		gridPane.add(errorSignUpText, 2, 0);
		gridPane.add(usernameText, 0, 1); 
		gridPane.add(usernameTextField, 1, 1); 
		gridPane.add(passwordText, 0, 2);       
		gridPane.add(passwordTextField, 1, 2); 
		gridPane.add(loginButton, 0, 4); 
		gridPane.add(usernameText2, 2, 1);
		gridPane.add(usernameTextField2, 3, 1);
		gridPane.add(passwordText2, 2, 2);
		gridPane.add(passwordTextField2, 3, 2);
		gridPane.add(confirmPasswordText, 2, 3);
		gridPane.add(confirmPasswordTextField, 3, 3);
		gridPane.add(signupButton, 2, 4);

		//Styling nodes  
		loginButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
		signupButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
		
		
		loginButton.setOnAction(e->{
			try {
				FlexiBookController.login(usernameTextField.getText() , passwordTextField.getText());
				errorLoginText.setText("");
			} catch (InvalidInputException e1) {
				errorLoginText.setText(e1.getMessage());
			}
		});
		
		
		
		signupButton.setOnAction(e->{
			try {
				if(passwordTextField2.getText().equals(confirmPasswordTextField.getText())) {
					FlexiBookController.signUpCustomerAccount(usernameTextField2.getText() , passwordTextField2.getText());
					errorSignUpText.setText("");
				}
				else { 
					errorSignUpText.setText("Your password and confirmation password do not match.");
				}
			} catch (InvalidInputException e1) {
				errorSignUpText.setText(e1.getMessage());
			}
		});
		
		usernameText.setStyle("-fx-font: normal bold 20px 'serif' "); 
		passwordText.setStyle("-fx-font: normal bold 20px 'serif' ");  
		usernameText2.setStyle("-fx-font: normal bold 20px 'serif' "); 
		passwordText2.setStyle("-fx-font: normal bold 20px 'serif' ");  
		confirmPasswordText.setStyle("-fx-font: normal bold 20px 'serif' ");
		
		gridPane.setStyle("-fx-background-color: BEIGE;"); 

		//Creating a scene object 
		scene = new Scene(gridPane);
		
		ViewManager.setScene(scene);

		
	}      
}
