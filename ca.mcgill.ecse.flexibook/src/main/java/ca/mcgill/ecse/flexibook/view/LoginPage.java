package ca.mcgill.ecse.flexibook.view;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.application.Application;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 

import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  

public class LoginPage extends Application{ 
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;

	
	//creating label FlexiBook
	private Text flexibook;
	
	//creating label for slogan
	private Text slogan;
	
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
	
	//Creating border pane
	private BorderPane root;

	//Creating a Grid Pane 
	private GridPane gridPane;  
	
	//Creating a scene object 
	private Scene scene; 

	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		
		//initializing labels
		flexibook = new Text("FlexiBook");
		slogan = new Text("Time to get Organised!");
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
		root = new BorderPane();  
		
		//initializing Grid Pane 
		gridPane = new GridPane();    

		//Setting size for the pane 
		root.setMinSize(800, 500); 

		//Setting the padding  
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 

		//Setting the vertical and horizontal gaps between the columns 
		gridPane.setVgap(10); 
		gridPane.setHgap(20);       

		//Setting alignments 
		root.setTop(flexibook);
		root.setCenter(gridPane);
		root.setBottom(slogan);;
		BorderPane.setAlignment(flexibook, Pos.TOP_CENTER);
		gridPane.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(slogan, Pos.BOTTOM_CENTER);
		
		
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
		
		flexibook.setStyle("-fx-font: normal bold 40px 'serif' ");
		slogan.setStyle("-fx-font: normal bold 40px 'serif' ");
		usernameText.setStyle("-fx-font: normal bold 20px 'serif' "); 
		passwordText.setStyle("-fx-font: normal bold 20px 'serif' ");  
		usernameText2.setStyle("-fx-font: normal bold 20px 'serif' "); 
		passwordText2.setStyle("-fx-font: normal bold 20px 'serif' ");  
		confirmPasswordText.setStyle("-fx-font: normal bold 20px 'serif' ");
		
		root.setStyle("-fx-background-color: BEIGE;"); 

		//Creating a scene object 
		scene = new Scene(root);
		
		stage.setTitle("LoginPage");
		stage.setScene(scene);
		stage.show();
		
	}

	   
}
