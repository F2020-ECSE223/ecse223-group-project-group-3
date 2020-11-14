package ca.mcgill.ecse.flexibook.view;


import java.time.format.DateTimeFormatter;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class FlexiBookPage {
	private static final long serialVersionUID = -4426310869335015542L;
	
	
	//---Appointment Page----------------------------------------------------------------------------------------
	
	//error messages
	private Text errorMakeAppointment;
	private Text errorUpdateAppointment;
	private Text errorCancelAppointment;
	
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
	private String makeAppDateString;

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
	
	//Split pane
	private SplitPane splitPane;
	
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
	
	
	//Horizontal Box
	private HBox horizontalMakeApp;
	
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
    	
    	//Appointment Page------------------------------------------------------
    	makeAppLabel = new Text("Book an appointment!");
    	makeAppLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    	makeAppLabel.setFill(Color.BLUE);
    	makeAppInstruction = new Text("Please enter the information of the appointment you would like to book.");
    	makeAppInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
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
    	
    	
    	updateAppLabel = new Text("Update or change your appointment!");
    	updateAppLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    	updateAppLabel.setFill(Color.BLUE);
    	updateAppFirstInstruction = new Text("Please enter the information of the appointment"
    			+ " you would like to update/change.");
    	updateAppFirstInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
    	
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
    	
    	errorCancelAppointment = new Text("");
    	errorCancelAppointment.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    	errorCancelAppointment.setFill(Color.RED);
    	
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
    
    
    
    splitPane = new SplitPane();
    splitPane.setMinSize(1100, 600);
    splitPane.setMaxSize(1100, 600);
    splitPane.setOrientation(Orientation.VERTICAL);
    splitPane.setStyle("-fx-background-color: LIGHTBLUE;");
    
    gridPaneMakeApp.add(makeAppLabel, 0, 0,2,1);
    gridPaneMakeApp.add(errorMakeAppointment, 6, 0,3,1);
    gridPaneMakeApp.add(makeAppInstruction, 0, 1,5,1);
    gridPaneMakeApp.add(makeAppServiceLabel, 0, 2);
    gridPaneMakeApp.add(makeAppServiceText, 1, 2); 
    gridPaneMakeApp.add(makeAppDateLabel, 3, 2);
    gridPaneMakeApp.add(makeAppDatePicker, 4, 2,2,1);
    gridPaneMakeApp.add(makeAppStartTimeLabel, 5, 2);
    gridPaneMakeApp.add(makeAppStartTimeText, 6, 2);
    gridPaneMakeApp.add(makeAppButton, 4, 3,2,1);
    
    
    gridPaneUpdateApp.add(updateAppLabel, 0, 0,4,1);
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
    
    gridPaneCancelApp.add(cancelAppLabel, 0, 0,3,1);
    gridPaneCancelApp.add(errorCancelAppointment, 5, 0,3,1);
    gridPaneCancelApp.add(cancelAppFirstInstruction, 0, 1,5,1);
    gridPaneCancelApp.add(cancelAppServiceLabel, 0, 2);
    gridPaneCancelApp.add(cancelAppServiceText, 1, 2);
    gridPaneCancelApp.add(cancelAppDateLabel, 2, 2);
    gridPaneCancelApp.add(cancelAppDatePicker, 3, 2);
    gridPaneCancelApp.add(cancelAppStartTimeLabel, 4, 2);
    gridPaneCancelApp.add(cancelAppStartTimeText, 5, 2);
    gridPaneCancelApp.add(cancelAppButton, 3, 3);
    
  
    
    
    verticalMenuMakeApp = new VBox();
    verticalMenuMakeApp.setPadding(new Insets(10));
    verticalMenuMakeApp.setSpacing(8);
    
    verticalMenuUpdateApp = new VBox();
    verticalMenuUpdateApp.setPadding(new Insets(10));
    verticalMenuUpdateApp.setSpacing(8);
    
    verticalMenuCancelApp = new VBox();
    verticalMenuCancelApp.setPadding(new Insets(10));
    verticalMenuCancelApp.setSpacing(8);
    
    Text title = new Text("What do you wish to do?");
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    Text title2 = new Text("What do you wish to do?");
    title2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    Text title3 = new Text("What do you wish to do?");
    title3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    verticalMenuMakeApp.getChildren().add(title);
    verticalMenuUpdateApp.getChildren().add(title2);
    verticalMenuCancelApp.getChildren().add(title3);
    
    makeAppLink1 = new Hyperlink("Make an appointment");
    updateAppLink1 = new Hyperlink("Update an appointment");
    cancelAppLink1 = new Hyperlink ("Cancel an appointment");
    
    makeAppLink2 = new Hyperlink("Make an appointment");
    updateAppLink2 = new Hyperlink("Update an appointment");
    cancelAppLink2 = new Hyperlink ("Cancel an appointment");
    
    makeAppLink3 = new Hyperlink("Make an appointment");
    updateAppLink3 = new Hyperlink("Update an appointment");
    cancelAppLink3 = new Hyperlink ("Cancel an appointment");

    Hyperlink options1[] = new Hyperlink[] {
        makeAppLink1,
        updateAppLink1,
        cancelAppLink1};

    for (int i=0; i<3; i++) {
        VBox.setMargin(options1[i], new Insets(0, 0, 0, 8));
        verticalMenuMakeApp.getChildren().add(options1[i]);
    }
    
    Hyperlink options2[] = new Hyperlink[] {
            makeAppLink2,
            updateAppLink2,
            cancelAppLink2};

        for (int i=0; i<3; i++) {
            VBox.setMargin(options2[i], new Insets(0, 0, 0, 8));
            verticalMenuUpdateApp.getChildren().add(options2[i]);
        }
        
        Hyperlink options3[] = new Hyperlink[] {
                makeAppLink3,
                updateAppLink3,
                cancelAppLink3};

            for (int i=0; i<3; i++) {
                VBox.setMargin(options3[i], new Insets(0, 0, 0, 8));
                verticalMenuCancelApp.getChildren().add(options3[i]);
            }
    
   
    
    makeAppBorderPane = new BorderPane();
    makeAppBorderPane.setLeft(verticalMenuMakeApp);
    makeAppBorderPane.setCenter(gridPaneMakeApp);
    
    updateAppBorderPane = new BorderPane();
    updateAppBorderPane.setLeft(verticalMenuUpdateApp);
    updateAppBorderPane.setCenter(gridPaneUpdateApp);
    
    cancelAppBorderPane = new BorderPane();
    cancelAppBorderPane.setLeft(verticalMenuCancelApp);
    cancelAppBorderPane.setCenter(gridPaneCancelApp);
    
    

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
				makeAppDateString = makeAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				FlexiBookController.makeAppointment(usernameTextField.getText() , makeAppServiceText.getText(), null, makeAppDateString, makeAppStartTimeText.getText());
				errorMakeAppointment.setText("");
			} catch (InvalidInputException e1) {
				errorMakeAppointment.setText(e1.getMessage());
			}
		});
    
    updateAppYes.setOnAction(e->{
    	updateAppServiceYesOrNo = true;
    });
    updateAppNo.setOnAction(e->{
    	updateAppServiceYesOrNo = false;
    });
    
    
    updateAppButton.setOnAction(e->{
		try {
			 updateAppDateString = updateAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			 updateAppNewDateString = updateAppNewDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			FlexiBookController.updateAppointment(usernameTextField.getText() , usernameTextField.getText(), updateAppServiceText.getText(), updateAppDateString, updateAppStartTimeText.getText(),
												  updateAppNewDateString, updateAppNewStartTimeText.getText(), null, null, updateAppServiceYesOrNo, updateAppNewServiceText.getText());
			errorUpdateAppointment.setText("");
		} catch (InvalidInputException e1) {
			errorUpdateAppointment.setText(e1.getMessage());
		}
	});
    
    cancelAppButton.setOnAction(e->{
		try {
			cancelAppDateString = cancelAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			FlexiBookController.cancelAppointment(usernameTextField.getText() , usernameTextField.getText(), cancelAppServiceText.getText(), cancelAppDateString, cancelAppStartTimeText.getText());								  
			errorCancelAppointment.setText("");
		} catch (InvalidInputException e1) {
			errorCancelAppointment.setText(e1.getMessage());
		}
	});
    

    
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
  				    primaryStage.setScene(makeAppScene);
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