package ca.mcgill.ecse.flexibook.view;


import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class FlexiBookPage {
	private static final long serialVersionUID = -4426310869335015542L;
	
	
	//---Appointment Page----------------------------------------------------------------------------------------
	
	
	private Text errorMessage;
	
	//Make an appointment
	private Text makeAppLabel;
	private Text makeAppInstruction;
	
	//Service name label
	private Text makeAppServiceLabel;
	//Service name text field
	private TextField makeAppServiceText ;
	
	//Date label
	private Text makeAppDateLabel;
	//Date picker
	private DatePicker makeAppDatePicker;

	//Start time label
	private Text makeAppStartTimeLabel;
	//Start time text field
	private TextField makeAppStartTimeText;
	//Button to add the appointment
	private Button makeAppButton;
	
	
	//Update Appointment
	private Text updateAppLabel;
	
	//First instruction message
	private Text updateAppFirstInstruction;
	//Service name label
	private Text updateAppServiceLabel;
	//Service name text field
	private TextField updateAppServiceText;
	
	//Date label
	private Text updateAppDateLabel;
	//Date picker
	private DatePicker updateAppDatePicker;
	
	//Start Time Label
	private Text updateAppStartTimeLabel;
	//Start Time text field
	private TextField updateAppStartTimeText;
	
	//Second instruction message
	private Text updateAppSecondInstruction;
	//Yes no buttons
	private ToggleButton updateAppYes;
	private ToggleButton updateAppNo;
	
	
	//Third instruction message
	private Text updateAppThirdInstruction;
	//New service name label
	private Text updateAppNewServiceLabel;
	//New service name text field
	private TextField updateAppNewServiceText;
	
	//New date label
	private Text updateAppNewDateLabel;
	//New date picker
	private DatePicker updateAppNewDatePicker;
	
	//New start Time Label
	private Text updateAppNewStartTimeLabel;
	//New start Time text field
	private TextField updateAppNewStartTimeText;
	//Button to update Appointment
	private Button updateAppButton;
	
	
	//Cancel Appointment
	private Text cancelAppLabel;
	
	//First instruction message
	private Text cancelAppFirstInstruction;
	
	//Service name label
	private Text cancelAppServiceLabel;
	//Service name text field
	private TextField cancelAppServiceText;
	
	//Date label
	private Text cancelAppDateLabel;
	// Date picker
	private DatePicker cancelAppDatePicker;
	
	//Start time label
	private Text cancelAppStartTimeLabel;
	//Start time text field
	private TextField cancelAppStartTimeText;
	//Button to cancel app
	private Button cancelAppButton;
	
	
	//Grid pane
	private GridPane gridPane;
	private GridPane gridPane2;
	private GridPane gridPane3;
	
	//Split pane
	private SplitPane splitPane;
	
	//Creating a scene object
	private Scene appScene;
	
	//--Login Page------------------------------------------------------------------------------------------------
	
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
		private GridPane gridPaneLogin;  
		
		//Creating a scene object 
		private Scene loginScene; 
		
	public FlexiBookPage(Stage stage) {	
		initView(stage);
	}

    
    public void initView(Stage primaryStage) {
    	
    	//Appointment Page------------------------------------------------------
    	makeAppLabel = new Text("Book an appointment!");
    	makeAppLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    	makeAppLabel.setFill(Color.BLUE);
    	makeAppInstruction = new Text("Please enter the information of the appointment you would like to book.");
    	makeAppInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	makeAppServiceLabel = new Text("Service: ");
    	makeAppServiceText = new TextField();
    	makeAppServiceLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	makeAppDateLabel = new Text("Date: ");
    	makeAppDatePicker = new DatePicker();
    	makeAppDateLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	makeAppStartTimeLabel = new Text("Start time: ");
    	makeAppStartTimeText = new TextField();
    	makeAppStartTimeLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	makeAppButton = new Button("Add appointment");
    	
    	
    	updateAppLabel = new Text("Update or change your appointment!");
    	updateAppLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    	updateAppLabel.setFill(Color.BLUE);
    	updateAppFirstInstruction = new Text("Please enter the information of the appointment"
    			+ " you would like to update/change.");
    	updateAppFirstInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    			
    	
    	updateAppServiceLabel = new Text("Service: ");
    	updateAppServiceText = new TextField();
    	updateAppServiceLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	updateAppDateLabel = new Text("      Date: ");
    	updateAppDatePicker = new DatePicker();
    	updateAppDateLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	updateAppStartTimeLabel = new Text("Start time: ");
    	updateAppStartTimeText = new TextField();
    	updateAppStartTimeLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	updateAppSecondInstruction= new Text("Do you wish to change you service? ");
    	updateAppSecondInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	updateAppYes = new ToggleButton("Yes");
    	updateAppNo = new ToggleButton("No");
    	updateAppThirdInstruction = new Text("Note: If you selected 'No' above, leave the first"
    			+ " box empty. Fill the rest of the boxes with your new desired time slot.");
    	updateAppThirdInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	updateAppNewServiceLabel = new Text("New service: ");
    	updateAppNewServiceText = new TextField();
    	updateAppNewServiceLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	updateAppNewDateLabel = new Text("      New date: ");
    	updateAppNewDatePicker = new DatePicker();
    	updateAppNewDateLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	updateAppNewStartTimeLabel = new Text("New start time: ");
    	updateAppNewStartTimeText = new TextField();
    	updateAppNewStartTimeLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	updateAppButton = new Button("Update appointment");
    	
    	
    	cancelAppLabel = new Text("Cancel your appointment!");
    	cancelAppLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    	cancelAppLabel.setFill(Color.BLUE);
    	
    	cancelAppFirstInstruction = new Text("Please enter the information of the appointment"
    			+ " you would like to cancel.");
    	cancelAppFirstInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	cancelAppServiceLabel = new Text("Service: ");
    	cancelAppServiceText = new TextField();
    	cancelAppServiceLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	cancelAppDateLabel = new Text("Date: ");
    	cancelAppDatePicker = new DatePicker();
    	cancelAppDateLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	cancelAppStartTimeLabel = new Text("Start time: ");
    	cancelAppStartTimeText = new TextField();
    	cancelAppStartTimeLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
    	cancelAppButton = new Button("Cancel appointment");
    			
    	
    gridPane = new GridPane();
    gridPane.setMinSize(500, 50);
    gridPane.setPadding(new Insets(100, 100, 100, 100));	
    gridPane.setVgap(10);
    gridPane.setHgap(10);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setStyle("-fx-background-color: LIGHTBLUE;");
    
    gridPane2 = new GridPane();
    gridPane2.setMinSize(800, 250);
    gridPane2.setPadding(new Insets(100, 100, 100, 100));	
    gridPane2.setVgap(10);
    gridPane2.setHgap(10);
    gridPane2.setAlignment(Pos.CENTER);
    gridPane2.setStyle("-fx-background-color: LIGHTBLUE;");
    
    gridPane3 = new GridPane();
    gridPane3.setMinSize(500, 50);
    gridPane3.setPadding(new Insets(100, 100, 100, 100));	
    gridPane3.setVgap(10);
    gridPane3.setHgap(10);
    gridPane3.setAlignment(Pos.CENTER);
    gridPane3.setStyle("-fx-background-color: LIGHTBLUE;");
    
    
    
    splitPane = new SplitPane();
    splitPane.setMinSize(1100, 600);
    splitPane.setMaxSize(1100, 600);
    splitPane.setOrientation(Orientation.VERTICAL);
    splitPane.setStyle("-fx-background-color: LIGHTBLUE;");
    
    gridPane.add(makeAppLabel, 0, 0,2,1);
    gridPane.add(makeAppInstruction, 0, 1,5,1);
    gridPane.add(makeAppServiceLabel, 0, 2);
    gridPane.add(makeAppServiceText, 1, 2); 
    gridPane.add(makeAppDateLabel, 3, 2);
    gridPane.add(makeAppDatePicker, 4, 2,2,1);
    gridPane.add(makeAppStartTimeLabel, 5, 2);
    gridPane.add(makeAppStartTimeText, 6, 2);
    gridPane.add(makeAppButton, 4, 3,2,1);
    
    
    gridPane2.add(updateAppLabel, 0, 0,4,1);
    gridPane2.add(updateAppFirstInstruction, 0, 1,6,1);
    gridPane2.add(updateAppServiceLabel, 0, 2);
    gridPane2.add(updateAppServiceText, 1, 2);
    gridPane2.add(updateAppDateLabel, 3, 2);
    gridPane2.add(updateAppDatePicker, 4, 2,2,1);
    gridPane2.add(updateAppStartTimeLabel, 7, 2);
    gridPane2.add(updateAppStartTimeText, 8, 2);
    gridPane2.add(updateAppSecondInstruction, 0, 3,2,1);
    gridPane2.add(updateAppYes, 4, 3);
    gridPane2.add(updateAppNo, 5, 3);
    gridPane2.add(updateAppThirdInstruction, 0, 4,9,1);
    gridPane2.add(updateAppNewServiceLabel, 0, 5);
    gridPane2.add(updateAppNewServiceText, 1, 5);
    gridPane2.add(updateAppNewDateLabel, 3, 5);
    gridPane2.add(updateAppNewDatePicker, 4, 5,2,1);
    gridPane2.add(updateAppNewStartTimeLabel, 7, 5);
    gridPane2.add(updateAppNewStartTimeText, 8, 5);
    gridPane2.add(updateAppButton, 4, 6,2,1);
    
    gridPane3.add(cancelAppLabel, 0, 0,3,1);
    gridPane3.add(cancelAppFirstInstruction, 0, 1,5,1);
    gridPane3.add(cancelAppServiceLabel, 0, 2);
    gridPane3.add(cancelAppServiceText, 1, 2);
    gridPane3.add(cancelAppDateLabel, 2, 2);
    gridPane3.add(cancelAppDatePicker, 3, 2);
    gridPane3.add(cancelAppStartTimeLabel, 4, 2);
    gridPane3.add(cancelAppStartTimeText, 5, 2);
    gridPane3.add(cancelAppButton, 3, 3);
    
    
    splitPane.getItems().addAll(gridPane,gridPane2,gridPane3);
        
    
    appScene = new Scene(splitPane);
    

    
    //Login Page----------------------------------------------------------------
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
  		gridPaneLogin = new GridPane();    

  		//Setting size for the pane 
  		root.setMinSize(1100, 600); 
  		root.setMaxSize(1100, 600); 

  		//Setting the padding  
  		gridPaneLogin.setPadding(new Insets(10, 10, 10, 10)); 

  		//Setting the vertical and horizontal gaps between the columns 
  		gridPaneLogin.setVgap(10); 
  		gridPaneLogin.setHgap(20);       

  		//Setting alignments 
  		root.setTop(flexibook);
  		root.setCenter(gridPaneLogin);
  		root.setBottom(slogan);;
  		BorderPane.setAlignment(flexibook, Pos.TOP_CENTER);
  		gridPaneLogin.setAlignment(Pos.CENTER);
  		BorderPane.setAlignment(slogan, Pos.BOTTOM_CENTER);
  		
  		
  		//Arranging all the nodes in the grid 
  		gridPaneLogin.add(errorLoginText, 0, 0);
  		gridPaneLogin.add(errorSignUpText, 2, 0);
  		gridPaneLogin.add(usernameText, 0, 1); 
  		gridPaneLogin.add(usernameTextField, 1, 1); 
  		gridPaneLogin.add(passwordText, 0, 2);       
  		gridPaneLogin.add(passwordTextField, 1, 2); 
  		gridPaneLogin.add(loginButton, 0, 4); 
  		gridPaneLogin.add(usernameText2, 2, 1);
  		gridPaneLogin.add(usernameTextField2, 3, 1);
  		gridPaneLogin.add(passwordText2, 2, 2);
  		gridPaneLogin.add(passwordTextField2, 3, 2);
  		gridPaneLogin.add(confirmPasswordText, 2, 3);
  		gridPaneLogin.add(confirmPasswordTextField, 3, 3);
  		gridPaneLogin.add(signupButton, 2, 4);

  		//Styling nodes  
  		loginButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
  		signupButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
  		
  		
  		loginButton.setOnAction(e->{
  			try {
  				FlexiBookController.login(usernameTextField.getText() , passwordTextField.getText());
  				errorLoginText.setText("");
  			    primaryStage.setTitle("Appointment");
  			    primaryStage.setScene(appScene);
  			    primaryStage.show();
  			} catch (InvalidInputException e1) {
  				errorLoginText.setText(e1.getMessage());
  			}
  		});
  		
  		
  		
  		signupButton.setOnAction(e->{
  			try {
  				if(passwordTextField2.getText().equals(confirmPasswordTextField.getText())) {
  					FlexiBookController.signUpCustomerAccount(usernameTextField2.getText() , passwordTextField2.getText());
  					errorSignUpText.setText("");
  				    primaryStage.setTitle("Appointment");
  				    primaryStage.setScene(appScene);
  				    primaryStage.show();
  					
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
  		loginScene = new Scene(root);
  		
  		//ViewManager.setScene(scene);
  	    primaryStage.setTitle("Login");
  	    primaryStage.setScene(loginScene);
  	    primaryStage.show();

  		
    
    }
 
}