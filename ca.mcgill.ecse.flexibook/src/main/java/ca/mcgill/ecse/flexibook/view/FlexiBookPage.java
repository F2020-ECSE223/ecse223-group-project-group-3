package ca.mcgill.ecse.flexibook.view;


import java.time.format.DateTimeFormatter;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXButton;
 
public class FlexiBookPage {
	private static final long serialVersionUID = -4426310869335015542L;
	
	
	//---Appointment Page----------------------------------------------------------------------------------------
	
	//error messages
	private String errorAppointment = "";
	private Text errorMakeAppointment;
	private Text errorUpdateAppointment;
	private Text errorCancelAppointment;
	
	//Make an appointment
	private Text makeAppInstruction;
	
	//Service name label
	private Text makeAppServiceLabel;
	//Service name text field
	private TextField makeAppServiceText ;
	
	//Date label
	private Text makeAppDateLabel;
	//Date picker
	private DatePicker makeAppDatePicker;
	private String makeAppDateString;

	//Start time label
	private Text makeAppStartTimeLabel;
	//Start time text field
	private TextField makeAppStartTimeText;
	//Button to add the appointment
	private Button makeAppButton;
	
	
	//Update Appointment
	
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
	private String updateAppDateString;
	
	//Start Time Label
	private Text updateAppStartTimeLabel;
	//Start Time text field
	private TextField updateAppStartTimeText;
	
	//Second instruction message
	private Text updateAppSecondInstruction;
	//Yes no buttons
	private ToggleButton updateAppYes;
	private ToggleButton updateAppNo;
	private boolean updateAppServiceYesOrNo;
	
	
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
	private String updateAppNewDateString;
	
	//New start Time Label
	private Text updateAppNewStartTimeLabel;
	//New start Time text field
	private TextField updateAppNewStartTimeText;
	//Button to update Appointment
	private Button updateAppButton;
	
	
	//Cancel Appointment
	
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
	private String cancelAppDateString;
	
	//Start time label
	private Text cancelAppStartTimeLabel;
	//Start time text field
	private TextField cancelAppStartTimeText;
	//Button to cancel app
	private Button cancelAppButton;
	
	
	//Grid pane
	private GridPane gridPaneMakeApp;
	private GridPane gridPaneUpdateApp;
	private GridPane gridPaneCancelApp;
	
	
	//Vertical Box
	private VBox verticalMenuMakeApp;
	private VBox verticalMenuUpdateApp;
	private VBox verticalMenuCancelApp;
	private Hyperlink makeAppLink1;
	private Hyperlink updateAppLink1;
	private Hyperlink cancelAppLink1;
	private Hyperlink makeAppLink2;
	private Hyperlink updateAppLink2;
	private Hyperlink cancelAppLink2;
	private Hyperlink makeAppLink3;
	private Hyperlink updateAppLink3;
	private Hyperlink cancelAppLink3;
	private Hyperlink logoutLink1;
	private Hyperlink logoutLink2;
	private Hyperlink logoutLink3;
	private Hyperlink editProfileLink1;
	private Hyperlink editProfileLink2;
	private Hyperlink editProfileLink3;
	
	//Horizontal Box
	private HBox horizontalMakeApp;
	private HBox horizontalUpdateApp;
	private HBox horizontalCancelApp;
	
	//Border Pane
	private BorderPane makeAppBorderPane;
	private BorderPane updateAppBorderPane;
	private BorderPane cancelAppBorderPane;
	
	//FlexiBook logo
	private ImageView flexiBookLogo;
	
	//Creating a scene object
	private Scene makeAppScene;
	private Scene updateAppScene;
	private Scene cancelAppScene;
	
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
    	
    	//Customer Page---------------------------------------------------------
	    BorderPane customerBorderPane = new BorderPane();
	    customerBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
	    customerBorderPane.setMinSize(1100, 600); 
  		customerBorderPane.setMaxSize(1100, 600); 
	    
	    
	    HBox customerLabelHBox = new HBox();
    	Text customerLabel = new Text("Welcome!");
    	customerLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
    	customerLabel.setFill(Color.BLUE);
    	DropShadow dS = new DropShadow();
        dS.setOffsetY(3.0f);
        dS.setColor(Color.color(0.4f, 0.4f, 0.4f));
        customerLabel.setEffect(dS);
        customerLabel.setCache(true);
        customerLabelHBox.getChildren().add(customerLabel);
        customerLabelHBox.setAlignment(Pos.CENTER);
        customerBorderPane.setTop(customerLabelHBox);
        
        HBox customerIconsHBox = new HBox();
        customerIconsHBox.setAlignment(Pos.CENTER);
        FontIcon customerProfileIcon = new FontIcon("fa-user-circle-o");
        FontIcon customerMakeAppIcon = new FontIcon("fa-calendar-check-o");
        FontIcon customerUpdateAppIcon = new FontIcon("fa-calendar-minus-o");
        FontIcon customerCancelAppIcon = new FontIcon("fa-calendar-times-o");
        FontIcon customerLogoutIcon = new FontIcon("fa-sign-out");
        
        customerProfileIcon.getStyleClass().add("icon");
        customerMakeAppIcon.getStyleClass().add("icon");
        customerUpdateAppIcon.getStyleClass().add("icon");
        customerCancelAppIcon.getStyleClass().add("icon");
        customerLogoutIcon.getStyleClass().add("icon");
        
        customerProfileIcon.setFill(Color.BLUE);
        customerProfileIcon.setIconSize(50);
        customerMakeAppIcon.setFill(Color.BLUE);
        customerMakeAppIcon.setIconSize(50);
        customerUpdateAppIcon.setFill(Color.BLUE);
        customerUpdateAppIcon.setIconSize(50);
        customerCancelAppIcon.setFill(Color.BLUE);
        customerCancelAppIcon.setIconSize(50);
        customerLogoutIcon.setFill(Color.BLUE);
        customerLogoutIcon.setIconSize(50);
        
        JFXButton customerProfileButton = new JFXButton("Account", customerProfileIcon);
        customerProfileButton.setContentDisplay(ContentDisplay.TOP);
        //customerProfileButton.setOnAction(e->switchToAppointment());
        customerProfileButton.getStyleClass().add("main-menu-button");
        customerProfileButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        JFXButton customerMakeAppButton = new JFXButton("Make an appointment", customerMakeAppIcon);
        customerMakeAppButton.setContentDisplay(ContentDisplay.TOP);
        customerMakeAppButton.setOnAction(e->{
        		primaryStage.setTitle("Make an ppointment");
			    primaryStage.setScene(makeAppScene);
			    primaryStage.show();
        });
        customerMakeAppButton.getStyleClass().add("main-menu-button");
        customerMakeAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        JFXButton customerUpdateAppButton = new JFXButton("Update your appointment", customerUpdateAppIcon);
        customerUpdateAppButton.setContentDisplay(ContentDisplay.TOP);
        customerUpdateAppButton.setOnAction(e->{
        		primaryStage.setTitle("Update an appointment");
			    primaryStage.setScene(updateAppScene);
			    primaryStage.show();
        });
        customerUpdateAppButton.getStyleClass().add("main-menu-button");
        customerUpdateAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        JFXButton customerCancelAppButton = new JFXButton("Cancel your appointment", customerCancelAppIcon);
        customerCancelAppButton.setContentDisplay(ContentDisplay.TOP);
        customerCancelAppButton.setOnAction(e->{
        		primaryStage.setTitle("Cancel an appointment");
			    primaryStage.setScene(cancelAppScene);
			    primaryStage.show();
        });
        customerCancelAppButton.getStyleClass().add("main-menu-button");
        customerCancelAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        JFXButton customerLogoutButton = new JFXButton("Logout", customerLogoutIcon);
        customerLogoutButton.setContentDisplay(ContentDisplay.TOP);
//        customerLogoutButton.setOnAction(e->{
//        		primaryStage.setTitle("Update an ppointment");
//			    primaryStage.setScene(updateAppScene);
//			    primaryStage.show();
//        });
        customerLogoutButton.getStyleClass().add("main-menu-button");
        customerLogoutButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        HBox customerSloganHBox =new HBox();
        customerSloganHBox.setAlignment(Pos.CENTER);
        Text flexiBookText = new Text("Flexibook, it's time to get organised!");
        flexiBookText.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
        flexiBookText.setFill(Color.BLUE);
        customerSloganHBox.getChildren().add(flexiBookText);
        customerBorderPane.setBottom(customerSloganHBox);
        
        customerIconsHBox.getChildren().addAll(customerProfileButton, customerMakeAppButton, customerUpdateAppButton, customerCancelAppButton, customerLogoutButton);
        
       customerBorderPane.setCenter(customerIconsHBox);
       
    	Scene customerMainScene = new Scene(customerBorderPane);
    	
    	
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
  			    primaryStage.setScene(makeAppScene);
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
  				    primaryStage.setScene(customerMainScene);
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

  	    
  	    
    	
    	    	
    	//Appointment Page------------------------------------------------------

    	makeAppInstruction = new Text("Please enter the information of the appointment you would like to book.");
    	makeAppInstruction.setFont(Font.font("Verdana", FontWeight.BOLD,15));
    	
    	errorMakeAppointment = new Text("");
    	errorMakeAppointment.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    	errorMakeAppointment.setFill(Color.RED);
    	
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
    	
    	updateAppFirstInstruction = new Text("Please enter the information of the appointment"
    			+ " you would like to update/change.");
    	updateAppFirstInstruction.setFont(Font.font("Verdana", FontWeight.BOLD,15));
    	
    	errorUpdateAppointment = new Text("");
    	errorUpdateAppointment.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    	errorUpdateAppointment.setFill(Color.RED);
    			
    	
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
    	updateAppSecondInstruction.setFont(Font.font("Verdana", FontWeight.BOLD,15));
    	updateAppYes = new ToggleButton("Yes");
    	updateAppNo = new ToggleButton("No");
    	updateAppThirdInstruction = new Text("Note: If you selected 'No' above, leave the first"
    			+ " box empty. Fill the rest of the boxes with your new desired time slot.");
    	updateAppThirdInstruction.setFont(Font.font("Verdana", FontWeight.BOLD,15));
    	
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
    	
    	
    	errorCancelAppointment = new Text("");
    	errorCancelAppointment.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    	errorCancelAppointment.setFill(Color.RED);
    	
    	cancelAppFirstInstruction = new Text("Please enter the information of the appointment"
    			+ " you would like to cancel.");
    	cancelAppFirstInstruction.setFont(Font.font("Verdana", FontWeight.BOLD,15));
    	
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
    			
    	
    gridPaneMakeApp = new GridPane();
    gridPaneMakeApp.setMinSize(500, 50);
    gridPaneMakeApp.setPadding(new Insets(100, 100, 100, 100));	
    gridPaneMakeApp.setVgap(10);
    gridPaneMakeApp.setHgap(10);
    gridPaneMakeApp.setAlignment(Pos.CENTER);
    gridPaneMakeApp.setStyle("-fx-background-color: LIGHTBLUE;");
    
    gridPaneUpdateApp = new GridPane();
    gridPaneUpdateApp.setMinSize(800, 250);
    gridPaneUpdateApp.setPadding(new Insets(100, 100, 100, 100));	
    gridPaneUpdateApp.setVgap(10);
    gridPaneUpdateApp.setHgap(10);
    gridPaneUpdateApp.setAlignment(Pos.CENTER);
    gridPaneUpdateApp.setStyle("-fx-background-color: LIGHTBLUE;");
    
    gridPaneCancelApp = new GridPane();
    gridPaneCancelApp.setMinSize(500, 50);
    gridPaneCancelApp.setPadding(new Insets(100, 100, 100, 100));	
    gridPaneCancelApp.setVgap(10);
    gridPaneCancelApp.setHgap(10);
    gridPaneCancelApp.setAlignment(Pos.CENTER);
    gridPaneCancelApp.setStyle("-fx-background-color: LIGHTBLUE;");
    
    
//    gridPaneMakeApp.add(makeAppLabel, 0, 0,2,1);
    gridPaneMakeApp.add(errorMakeAppointment, 6, 0);
    gridPaneMakeApp.add(makeAppInstruction, 0, 1,5,1);
    gridPaneMakeApp.add(makeAppServiceLabel, 0, 2);
    gridPaneMakeApp.add(makeAppServiceText, 1, 2); 
    gridPaneMakeApp.add(makeAppDateLabel, 3, 2);
    gridPaneMakeApp.add(makeAppDatePicker, 4, 2,2,1);
    gridPaneMakeApp.add(makeAppStartTimeLabel, 5, 2);
    gridPaneMakeApp.add(makeAppStartTimeText, 6, 2);
    gridPaneMakeApp.add(makeAppButton, 4, 3,2,2);
    
    
//    gridPaneUpdateApp.add(updateAppLabel, 0, 0,4,1);
    gridPaneUpdateApp.add(errorUpdateAppointment, 8, 0, 3, 1);
    gridPaneUpdateApp.add(updateAppFirstInstruction, 0, 1,6,1);
    gridPaneUpdateApp.add(updateAppServiceLabel, 0, 2);
    gridPaneUpdateApp.add(updateAppServiceText, 1, 2);
    gridPaneUpdateApp.add(updateAppDateLabel, 3, 2);
    gridPaneUpdateApp.add(updateAppDatePicker, 4, 2,2,1);
    gridPaneUpdateApp.add(updateAppStartTimeLabel, 7, 2);
    gridPaneUpdateApp.add(updateAppStartTimeText, 8, 2);
    gridPaneUpdateApp.add(updateAppSecondInstruction, 0, 3,2,1);
    gridPaneUpdateApp.add(updateAppYes, 4, 3);
    gridPaneUpdateApp.add(updateAppNo, 5, 3);
    gridPaneUpdateApp.add(updateAppThirdInstruction, 0, 4,9,1);
    gridPaneUpdateApp.add(updateAppNewServiceLabel, 0, 5);
    gridPaneUpdateApp.add(updateAppNewServiceText, 1, 5);
    gridPaneUpdateApp.add(updateAppNewDateLabel, 3, 5);
    gridPaneUpdateApp.add(updateAppNewDatePicker, 4, 5,2,1);
    gridPaneUpdateApp.add(updateAppNewStartTimeLabel, 7, 5);
    gridPaneUpdateApp.add(updateAppNewStartTimeText, 8, 5);
    gridPaneUpdateApp.add(updateAppButton, 4, 6,2,1);
    
//    gridPaneCancelApp.add(cancelAppLabel, 0, 0,3,1);
    gridPaneCancelApp.add(errorCancelAppointment, 5, 0,3,1);
    gridPaneCancelApp.add(cancelAppFirstInstruction, 0, 1,5,1);
    gridPaneCancelApp.add(cancelAppServiceLabel, 0, 2);
    gridPaneCancelApp.add(cancelAppServiceText, 1, 2);
    gridPaneCancelApp.add(cancelAppDateLabel, 2, 2);
    gridPaneCancelApp.add(cancelAppDatePicker, 3, 2);
    gridPaneCancelApp.add(cancelAppStartTimeLabel, 4, 2);
    gridPaneCancelApp.add(cancelAppStartTimeText, 5, 2);
    gridPaneCancelApp.add(cancelAppButton, 3, 3);
    
    horizontalMakeApp = new HBox();
    horizontalMakeApp.setPadding(new Insets(15, 12, 15, 12));
    horizontalMakeApp.setSpacing(10);
    horizontalMakeApp.setStyle("-fx-background-color: #336699;");
    DropShadow ds = new DropShadow();
    ds.setOffsetY(3.0f);
    ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
    Text makeAppLabel = new Text("Book your appointment now!");
    makeAppLabel.setFill(Color.BLUE);
    makeAppLabel.setEffect(ds);
    makeAppLabel.setCache(true);
    makeAppLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,30));
    horizontalMakeApp.setAlignment(Pos.CENTER);
    horizontalMakeApp.getChildren().addAll(makeAppLabel);
    
    
    horizontalUpdateApp = new HBox();
    horizontalUpdateApp.setPadding(new Insets(15, 12, 15, 12));
    horizontalUpdateApp.setSpacing(10);
    horizontalUpdateApp.setStyle("-fx-background-color: #336699;");
    DropShadow ds1 = new DropShadow();
    ds1.setOffsetY(3.0f);
    ds1.setColor(Color.color(0.4f, 0.4f, 0.4f));
    Text updateAppLabel = new Text("Update or change your appointment!");
    updateAppLabel.setFill(Color.BLUE);
    updateAppLabel.setEffect(ds1);
    updateAppLabel.setCache(true);
    updateAppLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,30));
    horizontalUpdateApp.setAlignment(Pos.CENTER);
    horizontalUpdateApp.getChildren().addAll(updateAppLabel);
    
    horizontalCancelApp = new HBox();
    horizontalCancelApp.setPadding(new Insets(15, 12, 15, 12));
    horizontalCancelApp.setSpacing(10);
    horizontalCancelApp.setStyle("-fx-background-color: #336699;");
    DropShadow ds2 = new DropShadow();
    ds2.setOffsetY(3.0f);
    ds2.setColor(Color.color(0.4f, 0.4f, 0.4f));
    Text cancelAppLabel = new Text("Cancel your appointment!");
    cancelAppLabel.setFill(Color.BLUE);
    cancelAppLabel.setEffect(ds1);
    cancelAppLabel.setCache(true);
    cancelAppLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,30));
    horizontalCancelApp.setAlignment(Pos.CENTER);
    horizontalCancelApp.getChildren().addAll(cancelAppLabel);
    
    
    verticalMenuMakeApp = new VBox();
    verticalMenuMakeApp.setPadding(new Insets(10));
    verticalMenuMakeApp.setSpacing(8);
    verticalMenuMakeApp.setStyle("-fx-background-color: #336699;");
    
    verticalMenuUpdateApp = new VBox();
    verticalMenuUpdateApp.setPadding(new Insets(10));
    verticalMenuUpdateApp.setSpacing(8);
    verticalMenuUpdateApp.setStyle("-fx-background-color: #336699;");
    
    verticalMenuCancelApp = new VBox();
    verticalMenuCancelApp.setPadding(new Insets(10));
    verticalMenuCancelApp.setSpacing(8);
    verticalMenuCancelApp.setStyle("-fx-background-color: #336699;");
    
    Text title = new Text("What do you wish to do?");
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    Text title2 = new Text("What do you wish to do?");
    title2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    Text title3 = new Text("What do you wish to do?");
    title3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    verticalMenuMakeApp.getChildren().add(title);
    verticalMenuUpdateApp.getChildren().add(title2);
    verticalMenuCancelApp.getChildren().add(title3);
    
    editProfileLink1 = new Hyperlink("My account");
    editProfileLink1.setStyle("-fx-text-fill: blue;");
    editProfileLink1.setFont(Font.font("Verdana", 15));
    makeAppLink1 = new Hyperlink("Make an appointment");
    makeAppLink1.setStyle("-fx-text-fill: blue;");
    makeAppLink1.setFont(Font.font("Verdana", 15));
    updateAppLink1 = new Hyperlink("Update an appointment");
    updateAppLink1.setStyle("-fx-text-fill: blue;");
    updateAppLink1.setFont(Font.font("Verdana", 15));
    cancelAppLink1 = new Hyperlink ("Cancel an appointment");
    cancelAppLink1.setStyle("-fx-text-fill: blue;");
    cancelAppLink1.setFont(Font.font("Verdana", 15));
    logoutLink1 = new Hyperlink ("Logout");
    logoutLink1.setStyle("-fx-text-fill: blue;");
    logoutLink1.setFont(Font.font("Verdana", 15));
    
    editProfileLink2 = new Hyperlink("My account");
    editProfileLink2.setStyle("-fx-text-fill: blue;");
    editProfileLink2.setFont(Font.font("Verdana", 15));
    makeAppLink2 = new Hyperlink("Make an appointment");
    makeAppLink2.setStyle("-fx-text-fill: blue;");
    makeAppLink2.setFont(Font.font("Verdana", 15));
    updateAppLink2 = new Hyperlink("Update an appointment");
    updateAppLink2.setStyle("-fx-text-fill: blue;");
    updateAppLink2.setFont(Font.font("Verdana", 15));
    cancelAppLink2 = new Hyperlink ("Cancel an appointment");
    cancelAppLink2.setStyle("-fx-text-fill: blue;");
    cancelAppLink2.setFont(Font.font("Verdana", 15));
    logoutLink2 = new Hyperlink ("Logout");
    logoutLink2.setStyle("-fx-text-fill: blue;");
    logoutLink2.setFont(Font.font("Verdana", 15));
    
    editProfileLink3 = new Hyperlink("My account");
    editProfileLink3.setStyle("-fx-text-fill: blue;");
    editProfileLink3.setFont(Font.font("Verdana", 15));
    makeAppLink3 = new Hyperlink("Make an appointment");
    makeAppLink3.setStyle("-fx-text-fill: blue;");
    makeAppLink3.setFont(Font.font("Verdana", 15));
    updateAppLink3 = new Hyperlink("Update an appointment");
    updateAppLink3.setStyle("-fx-text-fill: blue;");
    updateAppLink3.setFont(Font.font("Verdana", 15));
    cancelAppLink3 = new Hyperlink ("Cancel an appointment");
    cancelAppLink3.setStyle("-fx-text-fill: blue;");
    cancelAppLink3.setFont(Font.font("Verdana", 15));
    logoutLink3 = new Hyperlink ("Logout");
    logoutLink3.setStyle("-fx-text-fill: blue;");
    logoutLink3.setFont(Font.font("Verdana", 15));

    Hyperlink options1[] = new Hyperlink[] {
        editProfileLink1,
        makeAppLink1,
        updateAppLink1,
        cancelAppLink1,
        logoutLink1};

    for (int i=0; i<5; i++) {
        VBox.setMargin(options1[i], new Insets(0, 0, 0, 8));
        verticalMenuMakeApp.getChildren().add(options1[i]);
    }
    
    Hyperlink options2[] = new Hyperlink[] {
    		editProfileLink2,
            makeAppLink2,
            updateAppLink2,
            cancelAppLink2,
            logoutLink2};

        for (int i=0; i<5; i++) {
            VBox.setMargin(options2[i], new Insets(0, 0, 0, 8));
            verticalMenuUpdateApp.getChildren().add(options2[i]);
        }
        
        Hyperlink options3[] = new Hyperlink[] {
        		editProfileLink3,
                makeAppLink3,
                updateAppLink3,
                cancelAppLink3,
                logoutLink3};

            for (int i=0; i<5; i++) {
                VBox.setMargin(options3[i], new Insets(0, 0, 0, 8));
                verticalMenuCancelApp.getChildren().add(options3[i]);
            }
    
    
    HBox makeAppSloganHBox =new HBox();
    makeAppSloganHBox.setAlignment(Pos.CENTER);
    Text flexiBookTextMakeApp = new Text("Flexibook, it's time to get organised!");
    flexiBookTextMakeApp.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
    flexiBookTextMakeApp.setFill(Color.BLUE);
    makeAppSloganHBox.getChildren().add(flexiBookTextMakeApp);
    makeAppSloganHBox.setStyle("-fx-background-color: #336699;");
    
    HBox updateAppSloganHBox =new HBox();
    updateAppSloganHBox.setAlignment(Pos.CENTER);
    Text flexiBookTextUpdateApp = new Text("Flexibook, it's time to get organised!");
    flexiBookTextUpdateApp.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
    flexiBookTextUpdateApp.setFill(Color.BLUE);
    updateAppSloganHBox.getChildren().add(flexiBookTextUpdateApp);
    updateAppSloganHBox.setStyle("-fx-background-color: #336699;");
    
    HBox cancelAppSloganHBox =new HBox();
    cancelAppSloganHBox.setAlignment(Pos.CENTER);
    Text flexiBookTextCancelApp = new Text("Flexibook, it's time to get organised!");
    flexiBookTextCancelApp.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
    flexiBookTextCancelApp.setFill(Color.BLUE);
    cancelAppSloganHBox.getChildren().add(flexiBookTextCancelApp);
    cancelAppSloganHBox.setStyle("-fx-background-color: #336699;");
    
    
    makeAppBorderPane = new BorderPane();
    makeAppBorderPane.setLeft(verticalMenuMakeApp);
    makeAppBorderPane.setCenter(gridPaneMakeApp);
    makeAppBorderPane.setTop(horizontalMakeApp);
    makeAppBorderPane.setBottom(makeAppSloganHBox);
    
    
    updateAppBorderPane = new BorderPane();
    updateAppBorderPane.setLeft(verticalMenuUpdateApp);
    updateAppBorderPane.setCenter(gridPaneUpdateApp);
    updateAppBorderPane.setTop(horizontalUpdateApp);
    updateAppBorderPane.setBottom(updateAppSloganHBox);
    
    cancelAppBorderPane = new BorderPane();
    cancelAppBorderPane.setLeft(verticalMenuCancelApp);
    cancelAppBorderPane.setCenter(gridPaneCancelApp);
    cancelAppBorderPane.setTop(horizontalCancelApp);
    cancelAppBorderPane.setBottom(cancelAppSloganHBox);
    
    

    makeAppScene = new Scene(makeAppBorderPane);
    updateAppScene = new Scene(updateAppBorderPane);
    cancelAppScene = new Scene(cancelAppBorderPane);
    
    
    makeAppLink1.setOnAction(e->{
    	primaryStage.setTitle("Make an appointment");
    	primaryStage.setScene(makeAppScene);
    });
    
    updateAppLink1.setOnAction(e->{
    	primaryStage.setTitle("Update an appointment");
    	primaryStage.setScene(updateAppScene);
    });
    
    cancelAppLink1.setOnAction(e->{
    	primaryStage.setTitle("Cancel an appointment");
    	primaryStage.setScene(cancelAppScene);
    });  
    
    makeAppLink2.setOnAction(e->{
    	primaryStage.setTitle("Make an appointment");
    	primaryStage.setScene(makeAppScene);
    });
    
    updateAppLink2.setOnAction(e->{
    	primaryStage.setTitle("Update an appointment");
    	primaryStage.setScene(updateAppScene);
    });
    
    cancelAppLink2.setOnAction(e->{
    	primaryStage.setTitle("Cancel an appointment");
    	primaryStage.setScene(cancelAppScene);
    });
    
    makeAppLink3.setOnAction(e->{
    	primaryStage.setTitle("Make an appointment");
    	primaryStage.setScene(makeAppScene);
    });
    
    updateAppLink3.setOnAction(e->{
    	primaryStage.setTitle("Update an appointment");
    	primaryStage.setScene(updateAppScene);
    });
    
    cancelAppLink3.setOnAction(e->{
    	primaryStage.setTitle("Cancel an appointment");
    	primaryStage.setScene(cancelAppScene);
    });
    
    
    makeAppButton.setOnAction(e->{
    	
			try {
		    	if(makeAppServiceText.getText()== null || makeAppServiceText.getText().trim().isEmpty()) {
		    		errorMakeAppointment.setText("A service should be defined to proceed.");
		    	}
		    	else if(makeAppDatePicker.getValue()==null) {
		    		errorMakeAppointment.setText("A date should be chosen to proceed.");
		    	}
		    	else if(makeAppStartTimeText.getText() == null || makeAppStartTimeText.getText().trim().isEmpty()) {
		    		errorMakeAppointment.setText("A time should be chosen to proceed.");
		    	}
		    	else {
		        makeAppDateString = makeAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				FlexiBookController.makeAppointment(usernameTextField.getText() , makeAppServiceText.getText(), null, makeAppDateString, makeAppStartTimeText.getText());
				errorMakeAppointment.setText("");
		    	}
			} catch (InvalidInputException e1) {
				errorMakeAppointment.setText(e1.getMessage());
			}
    	}
    	);
    
    updateAppYes.setOnAction(e->{
    	updateAppServiceYesOrNo = true;
    });
    updateAppNo.setOnAction(e->{
    	updateAppServiceYesOrNo = false;
    });
    
    
    updateAppButton.setOnAction(e->{
		try {
			if(updateAppServiceText.getText()== null || updateAppServiceText.getText().trim().isEmpty()) {
	    		errorUpdateAppointment.setText("A service should be defined to proceed.");
	    	}
	    	else if(updateAppDatePicker.getValue()==null) {
	    		errorUpdateAppointment.setText("A date should be chosen to proceed.");
	    	}
	    	else if(updateAppStartTimeText.getText() == null || updateAppStartTimeText.getText().trim().isEmpty()) {
	    		errorUpdateAppointment.setText("A time should be chosen to proceed.");
	    	}
	    	
	    	else if(updateAppNewDatePicker.getValue()==null) {
	    		errorUpdateAppointment.setText("A date should be chosen to proceed.");
	    	}
	    	else if(updateAppNewStartTimeText.getText() == null || updateAppNewStartTimeText.getText().trim().isEmpty()) {
	    		errorUpdateAppointment.setText("A time should be chosen to proceed.");
	    	}
	    	else if(updateAppServiceYesOrNo) {
					 if(updateAppNewServiceText.getText()== null || updateAppNewServiceText.getText().trim().isEmpty()) {
			    		errorUpdateAppointment.setText("A service should be defined to proceed.");
			    	}
				}
	    	else {
			 updateAppDateString = updateAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			 updateAppNewDateString = updateAppNewDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			FlexiBookController.updateAppointment(usernameTextField.getText() , usernameTextField.getText(), updateAppServiceText.getText(), updateAppDateString, updateAppStartTimeText.getText(),
												  updateAppNewDateString, updateAppNewStartTimeText.getText(), null, null, updateAppServiceYesOrNo, updateAppNewServiceText.getText());
			errorUpdateAppointment.setText("");
	    	}
		
		} catch (InvalidInputException e1) {
			errorUpdateAppointment.setText(e1.getMessage());
		} 
	});
    
    cancelAppButton.setOnAction(e->{
    	
    	Alert alert = new Alert(AlertType.WARNING, "Are you sure you want to cancel your appointment?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
    	    
		try {
			if(cancelAppServiceText.getText()== null || cancelAppServiceText.getText().trim().isEmpty()) {
	    		errorCancelAppointment.setText("A service should be defined to proceed.");
	    	}
	    	else if(cancelAppDatePicker.getValue()==null) {
	    		errorCancelAppointment.setText("A date should be chosen to proceed.");
	    	}
	    	else if(cancelAppStartTimeText.getText() == null || cancelAppStartTimeText.getText().trim().isEmpty()) {
	    		errorCancelAppointment.setText("A time should be chosen to proceed.");
	    	}
	    	else {
			cancelAppDateString = cancelAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			FlexiBookController.cancelAppointment(usernameTextField.getText() , usernameTextField.getText(), cancelAppServiceText.getText(), cancelAppDateString, cancelAppStartTimeText.getText());								  
			errorCancelAppointment.setText("");
	    	}
		} catch (InvalidInputException e1) {
			errorCancelAppointment.setText(e1.getMessage());
			}
    	}
	});
  		
    
    }
 
}