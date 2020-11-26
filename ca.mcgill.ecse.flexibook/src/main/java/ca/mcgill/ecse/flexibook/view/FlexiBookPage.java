package ca.mcgill.ecse.flexibook.view;


import java.time.format.DateTimeFormatter;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
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
import javafx.scene.control.Label;
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
	private Text error;

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


	///Owner Main Menu----------------------------------------------------------------------------------------------
	private Text welcome;
	private Text flexiBookText;

	private DropShadow dS;

//	private FontIcon ownerProfileIcon;
//	private FontIcon businessIcon;
//	private FontIcon serviceIcon;
//	private FontIcon appointmentIcon;
//	private FontIcon ownerLogoutIcon;

	private JFXButton ownerProfileButton;
	private JFXButton businessButton;
	private JFXButton serviceButton;
	private JFXButton appointmentButton;
	private JFXButton ownerLogoutButton;

	private HBox ownerLabelHBox;
	private HBox ownerIconsHBox;
	private HBox ownerSloganHBox;

	private BorderPane ownerBorderPane;

	private Scene ownerMainScene;


	//customer main menu------------------------------------------------------------------------------------------

	private Text customerLabel;
	private Text flexibookSlogan;

	private HBox customerLabelHBox;
	private HBox customerIconsHBox;
	private HBox customerSloganHBox;

//	private FontIcon customerProfileIcon;
//	private FontIcon customerMakeAppIcon;
//	private FontIcon customerUpdateAppIcon;
//	private FontIcon customerCancelAppIcon;
//	private FontIcon customerLogoutIcon;
	
	private JFXButton customerProfileButton;
	private JFXButton customerMakeAppButton;
	private JFXButton customerUpdateAppButton;
	private JFXButton customerLogoutButton;
	private JFXButton customerCancelAppButton;
	
	private BorderPane customerBorderPane;

	private Scene customerMainScene;

	
	
	//Start / End appointment Page -------------------------------------------------------------------------------------
	private Button startButton;
	private Button registerButton;
	private Button endButton;
	private Scene ownerAppScene;
	private GridPane gridPaneOwner;
	private BorderPane root2;
	private Text errorText; 
	private Text customerName;
	private Text appointmentName;
	private Text startDate;
	private Text startTime;
	private Text currentDateAndTime;
	private TextField customerUsernameTextField;
	private TextField appNameTextField;
	private TextField appDateTextField;
	private TextField appStartTimeTextField;
	private TextField currentDateAndTimeTextField;
	
	//Service Page--------------------------------------------------------------------------------------------------------
	
	
	//error messages
	private Text errorAddServiceMessage;
	private Text errorUpdateServiceMessage;
	private Text errorCancelServiceMessage;
	//add a service
	private Text addService;
	private Text addServiceInstruction;
	//Service name label
	private Text addServiceName;
	//Service name text field
	private TextField addServiceNameText;
	//service duration
	private Text addServiceDuration;
	//Service duration text field
	private TextField addServiceDurationText;
	//Service downtimeduration
	private Text addServiceDowntimeDuration;
	//Service downtime duration text field
	private TextField addServiceDowntimeDurationText;
	//Service start time of downtime
	private Text addServiceDowntimeStartTime;
	//Service start time of downtime text field
	private TextField addServiceDowntimeStartTimeText;

	private Button addServiceButton;

	private TextField serviceTextField;
	private TextField serviceNameTextField;
	private String serviceDurationTextField;
	private String serviceDowntimeDurationTextField;
	private String serviceDowntimeStartTextField;
	
	//Update Appointment
		private Text updateServiceLabel;

		//First instruction message
		private Text updateServiceOldInstruction;
		//Service name label
		private Text updateServiceLabelName;
		//Service name text field
		private TextField updateServiceText;



		//Second instruction message
		private Text updateServiceNewInstruction;
		//Yes no buttons
		private ToggleButton updateServiceYes;
		private ToggleButton updateServiceNo;

		//Third instruction message
		private Text updateServiceInstruction;
		//New service name label
		private Text updateServiceNewName;
		//New service name text field
		private TextField updateServiceNewNameText;

		//New date label
		private Text updateServiceNewDuration;
		//New date picker
		private TextField updateServiceNewDurationText;

		//New start Time Label
		private Text updateServiceNewDowntimeDuration;
		//New start Time text field
		private TextField updateServiceNewDowntimeDurationText;
		//Button to update Appointment
		private Text updateServiceNewDowntimeStartTime;

		private TextField updateServiceNewDowntimeStartTimeText;

		private Button updateServiceButton;
		//Cancel Appointment
		private Text cancelServiceLabel;

		//First instruction message
		private Text cancelServiceFirstInstruction;

		//Service name label
		private Text cancelServiceNameLabel;
		//Service name text field
		private TextField cancelServiceNameText;


		private Button cancelServiceButton;


		//Grid pane
		private GridPane gridPaneAddService;
		private GridPane gridPaneUpdateService;
		private GridPane gridPaneCancelService;

		//Split pane
		private SplitPane splitPane;
		//Lines seperatinon
		//	private Line line1;
		//	private Line line2;
		private VBox verticalMenuAddService;
		private VBox verticalMenuUpdateService;
		private VBox verticalMenuCancelService;
		private Hyperlink addServiceLink1;
		private Hyperlink updateServiceLink1;
		private Hyperlink cancelServiceLink1;
		private Hyperlink addServiceLink2;
		private Hyperlink updateServiceLink2;
		private Hyperlink cancelServiceLink2;
		private Hyperlink addServiceLink3;
		private Hyperlink updateServiceLink3;
		private Hyperlink cancelServiceLink3;


		//	//Horizontal Box
		//	private HBox horizontalMakeApp;
		//	
		//Border Pane
		private BorderPane addServiceBorderPane;
		private BorderPane updateServiceBorderPane;
		private BorderPane cancelServiceBorderPane;
		//	
		//	//FlexiBook logo
		//	private ImageView flexiBookLogo;
		//	
		//Creating a scene object
		private Scene addServiceScene;
		private Scene updateServiceScene;
		private Scene cancelServiceScene;
		
		
		//Update Account-------------------------------------------------------------------------------------------------
		
		//Initializing labels
		Label updateUsernameText = new Label("Username:");
		Label updatePasswordText = new Label("Password:");
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
		TextField updateUsernameTextField = new TextField();
		TextField updatePasswordTextField = new PasswordField();
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
		BorderPane accountRoot = new BorderPane();
		BorderPane deleteAccPane = new BorderPane();
		
		//Initializing scenes
		Scene deleteAccScene = new Scene(deleteAccPane);
		Scene updateAccScene = new Scene(accountRoot);
	
	public FlexiBookPage(Stage stage) {	
		initView(stage);
	}


	public void initView(Stage primaryStage) {

		//Login Page----------------------------------------------------------------


		//initializing labels
		flexibook = new Text("FlexiBook");
		slogan = new Text("Time to get Organised!");
		usernameText = new Text("Username");       
		passwordText = new Text("Password"); 
		usernameText2 = new Text("Username");       
		passwordText2 = new Text("Password"); 
		confirmPasswordText = new Text("Confirm Passsword");
		errorLoginText = new Text("");
		errorSignUpText = new Text("");
		error = new Text("");

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
				primaryStage.setTitle("Main Menu");
				if(FlexiBookApplication.getCurrentUser().getUsername().equals("owner")) {
					welcome.setText("Welcome "+FlexiBookApplication.getCurrentUser().getUsername()+"!");
					primaryStage.setScene(ownerMainScene);
					primaryStage.show();
				}
				else {
					customerLabel.setText("Welcome "+FlexiBookApplication.getCurrentUser().getUsername()+"!");
					primaryStage.setScene(customerMainScene);
					primaryStage.show();
				}
			} catch (InvalidInputException e1) {
				errorLoginText.setText(e1.getMessage());
			}
		});



		signupButton.setOnAction(e->{
			try {
				if(passwordTextField2.getText().equals(confirmPasswordTextField.getText())) {
					FlexiBookController.signUpCustomerAccount(usernameTextField2.getText() , passwordTextField2.getText());
					errorSignUpText.setText("Account successfully created!");
					

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


		//Owner main menu-----------------------------------------------------------------------------------------------
		ownerBorderPane = new BorderPane();
		ownerBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
		ownerBorderPane.setMinSize(1100, 600); 
		ownerBorderPane.setMaxSize(1100, 600); 


		ownerLabelHBox = new HBox();
		welcome = new Text("Welcome!");
		welcome.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
		welcome.setFill(Color.BLUE);
		dS = new DropShadow();
		dS.setOffsetY(3.0f);
		dS.setColor(Color.color(0.4f, 0.4f, 0.4f));
		welcome.setEffect(dS);
		welcome.setCache(true);
		ownerLabelHBox.getChildren().add(welcome);
		ownerLabelHBox.setAlignment(Pos.CENTER);
		ownerBorderPane.setTop(ownerLabelHBox);

		ownerIconsHBox = new HBox(50);
		ownerIconsHBox.setAlignment(Pos.CENTER);
//		ownerProfileIcon = new FontIcon("fa-user-circle-o");
//		businessIcon = new FontIcon("fa-briefcase");
//		appointmentIcon = new FontIcon("fa-calendar");
//		serviceIcon = new FontIcon("fa-tasks");
//		ownerLogoutIcon = new FontIcon("fa-sign-out");
//
//		ownerProfileIcon.getStyleClass().add("icon");
//		businessIcon.getStyleClass().add("icon");
//		appointmentIcon.getStyleClass().add("icon");
//		serviceIcon.getStyleClass().add("icon");
//		ownerLogoutIcon.getStyleClass().add("icon");
//
//		ownerProfileIcon.setFill(Color.BLUE);
//		ownerProfileIcon.setIconSize(50);
//		businessIcon.setFill(Color.BLUE);
//		businessIcon.setIconSize(50);
//		appointmentIcon.setFill(Color.BLUE);
//		appointmentIcon.setIconSize(50);
//		serviceIcon.setFill(Color.BLUE);
//		serviceIcon.setIconSize(50);
//		ownerLogoutIcon.setFill(Color.BLUE);
//		ownerLogoutIcon.setIconSize(50);

		//ownerProfileButton = new JFXButton("Account", ownerProfileIcon);
		ownerProfileButton.setContentDisplay(ContentDisplay.TOP);
		ownerProfileButton.setOnAction(e->{
			primaryStage.setTitle("Account Page");
			primaryStage.setScene(updateAccScene);
			primaryStage.show();
		});
		ownerProfileButton.getStyleClass().add("main-menu-button");
		ownerProfileButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		//businessButton = new JFXButton("Business", businessIcon);
		businessButton.setContentDisplay(ContentDisplay.TOP);

		businessButton.getStyleClass().add("main-menu-button");
		businessButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));


		//serviceButton = new JFXButton("Services", serviceIcon);
		serviceButton.setContentDisplay(ContentDisplay.TOP);
		serviceButton.setOnAction(e->{
			primaryStage.setTitle("Services Page");
			primaryStage.setScene(addServiceScene);
			primaryStage.show();
			
		});
		serviceButton.getStyleClass().add("main-menu-button");
		serviceButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		
		//appointmentButton = new JFXButton("Appointments", appointmentIcon);
		appointmentButton.setContentDisplay(ContentDisplay.TOP);
		appointmentButton.setOnAction(e->{
			primaryStage.setTitle("StartEndRegister");
	   	    primaryStage.setScene(ownerAppScene);
	   	    primaryStage.show();
		});
		appointmentButton.getStyleClass().add("main-menu-button");
		appointmentButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		

		//ownerLogoutButton = new JFXButton("Logout", ownerLogoutIcon);
		ownerLogoutButton.setContentDisplay(ContentDisplay.TOP);
		ownerLogoutButton.setOnAction(e->{
			try {
				FlexiBookController.logout();
				resetLoginPage();
				primaryStage.setTitle("LoginPage");
				primaryStage.setScene(loginScene);
				primaryStage.show();
			} catch (InvalidInputException e1) {
				error.setText(e1.getMessage());
				gridPaneLogin.add(error, 3, 3);
			}

		});
		ownerLogoutButton.getStyleClass().add("main-menu-button");
		ownerLogoutButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		ownerSloganHBox =new HBox();
		ownerSloganHBox.setAlignment(Pos.CENTER);
		flexiBookText = new Text("Flexibook, it's time to get organised!");
		flexiBookText.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		flexiBookText.setFill(Color.BLUE);
		ownerSloganHBox.getChildren().add(flexiBookText);
		ownerBorderPane.setBottom(ownerSloganHBox);


		ownerIconsHBox.getChildren().addAll(ownerProfileButton, businessButton, serviceButton, appointmentButton, ownerLogoutButton);

		ownerBorderPane.setCenter(ownerIconsHBox);

		ownerMainScene = new Scene(ownerBorderPane);


		//Customer Page---------------------------------------------------------
		
		customerBorderPane = new BorderPane();
		customerBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
		customerBorderPane.setMinSize(1100, 600); 
		customerBorderPane.setMaxSize(1100, 600); 


		customerLabelHBox = new HBox();
		customerLabel = new Text("Welcome!");
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

		customerIconsHBox = new HBox();
		customerIconsHBox.setAlignment(Pos.CENTER);
//		customerProfileIcon = new FontIcon("fa-user-circle-o");
//		customerMakeAppIcon = new FontIcon("fa-calendar-check-o");
//		customerUpdateAppIcon = new FontIcon("fa-calendar-minus-o");
//		customerCancelAppIcon = new FontIcon("fa-calendar-times-o");
//		customerLogoutIcon = new FontIcon("fa-sign-out");
//
//		customerProfileIcon.getStyleClass().add("icon");
//		customerMakeAppIcon.getStyleClass().add("icon");
//		customerUpdateAppIcon.getStyleClass().add("icon");
//		customerCancelAppIcon.getStyleClass().add("icon");
//		customerLogoutIcon.getStyleClass().add("icon");
//
//		customerProfileIcon.setFill(Color.BLUE);
//		customerProfileIcon.setIconSize(50);
//		customerMakeAppIcon.setFill(Color.BLUE);
//		customerMakeAppIcon.setIconSize(50);
//		customerUpdateAppIcon.setFill(Color.BLUE);
//		customerUpdateAppIcon.setIconSize(50);
//		customerCancelAppIcon.setFill(Color.BLUE);
//		customerCancelAppIcon.setIconSize(50);
//		customerLogoutIcon.setFill(Color.BLUE);
//		customerLogoutIcon.setIconSize(50);
//
//		customerProfileButton = new JFXButton("Account", customerProfileIcon);
		customerProfileButton.setContentDisplay(ContentDisplay.TOP);
		customerProfileButton.setOnAction(e->{
			primaryStage.setTitle("Account Page");
			primaryStage.setScene(updateAccScene);
			primaryStage.show();
		});
		customerProfileButton.getStyleClass().add("main-menu-button");
		customerProfileButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		//customerMakeAppButton = new JFXButton("Make an appointment", customerMakeAppIcon);
		customerMakeAppButton.setContentDisplay(ContentDisplay.TOP);
		customerMakeAppButton.setOnAction(e->{
			primaryStage.setTitle("Make an ppointment");
			primaryStage.setScene(makeAppScene);
			primaryStage.show();
		});
		customerMakeAppButton.getStyleClass().add("main-menu-button");
		customerMakeAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		//customerUpdateAppButton = new JFXButton("Update your appointment", customerUpdateAppIcon);
		customerUpdateAppButton.setContentDisplay(ContentDisplay.TOP);
		customerUpdateAppButton.setOnAction(e->{
			primaryStage.setTitle("Update an appointment");
			primaryStage.setScene(updateAppScene);
			primaryStage.show();
		});
		customerUpdateAppButton.getStyleClass().add("main-menu-button");
		customerUpdateAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		//customerCancelAppButton = new JFXButton("Cancel your appointment", customerCancelAppIcon);
		customerCancelAppButton.setContentDisplay(ContentDisplay.TOP);
		customerCancelAppButton.setOnAction(e->{
			primaryStage.setTitle("Cancel an appointment");
			primaryStage.setScene(cancelAppScene);
			primaryStage.show();
		});
		customerCancelAppButton.getStyleClass().add("main-menu-button");
		customerCancelAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		//customerLogoutButton = new JFXButton("Logout", customerLogoutIcon);
		customerLogoutButton.setContentDisplay(ContentDisplay.TOP);
		customerLogoutButton.setOnAction(e->{
			try {
				FlexiBookController.logout();
				resetLoginPage();
				primaryStage.setTitle("LoginPage");
				primaryStage.setScene(loginScene);
				primaryStage.show();
			} catch (InvalidInputException e1) {
				error.setText(e1.getMessage());
				gridPaneLogin.add(error, 3, 3);
			}

		});
		customerLogoutButton.getStyleClass().add("main-menu-button");
		customerLogoutButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		customerSloganHBox =new HBox();
		customerSloganHBox.setAlignment(Pos.CENTER);
		flexibookSlogan = new Text("Flexibook, it's time to get organised!");
		flexibookSlogan.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		flexibookSlogan.setFill(Color.BLUE);
		customerSloganHBox.getChildren().add(flexibookSlogan);
		customerBorderPane.setBottom(customerSloganHBox);

		customerIconsHBox.getChildren().addAll(customerProfileButton, customerMakeAppButton, customerUpdateAppButton, customerCancelAppButton, customerLogoutButton);

		customerBorderPane.setCenter(customerIconsHBox);

		customerMainScene = new Scene(customerBorderPane);	



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

		logoutLink1.setOnAction(e->{
			try {
				FlexiBookController.logout();
				resetLoginPage();
				primaryStage.setTitle("LoginPage");
				primaryStage.setScene(loginScene);
				primaryStage.show();
			} catch (InvalidInputException e1) {
				error.setText(e1.getMessage());
				gridPaneLogin.add(error, 3, 3);
			}
		});

		logoutLink2.setOnAction(e->{
			try {
				FlexiBookController.logout();
				resetLoginPage();
				primaryStage.setTitle("LoginPage");
				primaryStage.setScene(loginScene);
				primaryStage.show();
			} catch (InvalidInputException e1) {
				error.setText(e1.getMessage());
				gridPaneLogin.add(error, 3, 3);
			}
		});

		logoutLink3.setOnAction(e->{
			try {
				FlexiBookController.logout();
				resetLoginPage();
				primaryStage.setTitle("LoginPage");
				primaryStage.setScene(loginScene);
				primaryStage.show();
			} catch (InvalidInputException e1) {
				error.setText(e1.getMessage());
				gridPaneLogin.add(error, 3, 3);
			}
		});

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


		//Start / End appointment----------------------------------------------------------------------------------------
		startButton = new Button("Start Appointment");
		endButton = new Button("End Appointment");
		registerButton = new Button("Register no-show");
  		root2 = new BorderPane(); 
  		errorText = new Text(" ");
  		customerName = new Text("Customer name");
  		appointmentName = new Text("Appointment name");
  		startDate = new Text("Start date");
  		startTime = new Text("Start time");
  		currentDateAndTime = new Text("Current date and time");
  		
  		customerUsernameTextField = new TextField();
  		appNameTextField = new TextField();
  		appDateTextField = new TextField();
  		appStartTimeTextField = new TextField();
  		currentDateAndTimeTextField = new TextField();
  		
  		gridPaneOwner = new GridPane();   
  		gridPaneOwner.add(customerName, 0, 0);
  		gridPaneOwner.add(appointmentName, 2, 0);
  		gridPaneOwner.add(startDate, 4, 0);
  		gridPaneOwner.add(startTime, 6, 0);
  		gridPaneOwner.add(currentDateAndTime, 8, 0);
  		gridPaneOwner.add(startButton,2,4);
  		gridPaneOwner.add(registerButton,4,4);
  		gridPaneOwner.add(endButton,6,4);
  		gridPaneOwner.add(errorText, 4, 6);
  		gridPaneOwner.add(customerUsernameTextField,0,2);
  		gridPaneOwner.add(appNameTextField,2,2);
  		gridPaneOwner.add(appDateTextField,4,2);
  		gridPaneOwner.add(appStartTimeTextField,6,2);
  		gridPaneOwner.add(currentDateAndTimeTextField,8,2);
  		startButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
  		registerButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
  		endButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
  		root2.setStyle("-fx-background-color: CYAN;");
  		root2.setMinSize(1100, 600); 
  		root2.setMaxSize(1100, 600);
  		gridPaneOwner.setVgap(10);
  		gridPaneOwner.setHgap(20);  
  		gridPaneOwner.setPadding(new Insets(10, 10, 10, 10)); 
  		root2.setCenter(gridPaneOwner);
  		gridPaneOwner.setAlignment(Pos.CENTER);
  		root2.setCenter(gridPaneOwner);
  		ownerAppScene = new Scene(root2);
  		
   	    customerName.setStyle("-fx-font: normal bold 20px 'serif' "); 
   	    appointmentName.setStyle("-fx-font: normal bold 20px 'serif' "); 
   	    startDate.setStyle("-fx-font: normal bold 20px 'serif' "); 
   	    startTime.setStyle("-fx-font: normal bold 20px 'serif' "); 
   	    currentDateAndTime.setStyle("-fx-font: normal bold 20px 'serif' "); 
  		startButton.setOnAction(e->{
  			try {
  				FlexiBookController.startAppointment(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
  				errorText.setText("");
  			}catch (InvalidInputException e1) {
  				errorText.setText (e1.getMessage());
  			}
  		});
  		endButton.setOnAction(e->{
  			try {
  				FlexiBookController.endAppointment(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
  				errorText.setText("");
  			}catch (InvalidInputException e1) {
  				errorText.setText(e1.getMessage());
  			}
  		});
  		registerButton.setOnAction(e->{
  			try {
  				FlexiBookController.registerNoShow(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
  				errorText.setText(" ");
  			}catch (InvalidInputException e1) {
  				errorText.setText(e1.getMessage());
  			}
  		});
  		
  		
  		
  		//Service Page---------------------------------------------------------------------------------------------------
  		
  		addService = new Text("Add a Service");
		addService.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		addService.setFill(Color.BLUE);
		addServiceInstruction = new Text
				("Please enter the information of the service you would like to add.");
		addServiceInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorAddServiceMessage = new Text("");
		errorAddServiceMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorAddServiceMessage.setFill(Color.RED);


		addServiceName = new Text("Service Name: ");
		addServiceNameText = new TextField();
		addServiceName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	

		addServiceDuration = new Text("Service Duration: ");
		addServiceDurationText = new TextField();
		addServiceDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addServiceDowntimeDuration = new Text("Downtime Duration: ");
		addServiceDowntimeDurationText = new TextField();
		addServiceDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addServiceDowntimeStartTime = new Text("Downtime Start Time: ");
		addServiceDowntimeStartTimeText = new TextField();
		addServiceDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addServiceButton = new Button("Add service");


		//--------------------------------------------------------------------------------------------

		updateServiceLabel = new Text("Update a Service");
		updateServiceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		updateServiceLabel.setFill(Color.BLUE);
		updateServiceOldInstruction = new Text("Please enter Service Info you would like to update.");   		
		updateServiceOldInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		updateServiceLabelName = new Text("Service: ");
		updateServiceText = new TextField();
		updateServiceLabelName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorUpdateServiceMessage = new Text("");
		errorUpdateServiceMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorUpdateServiceMessage.setFill(Color.RED);

		updateServiceNewInstruction= new Text("Do you wish to change you service name? ");
		updateServiceNewInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceYes = new ToggleButton("Yes");
		updateServiceNo = new ToggleButton("No");
		updateServiceInstruction = new Text("Note:If one of the service info hasn't changed, kindly rewrite it. "
				+ "Once you click on update service, your service"
				+ " will have updated fields.");
		updateServiceInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceNewName = new Text("New service name: ");
		updateServiceNewNameText = new TextField();
		updateServiceNewName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceNewDuration = new Text("      New duration: ");
		updateServiceNewDurationText = new TextField();
		updateServiceNewDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceNewDowntimeDuration= new Text("New downtime duration: ");
		updateServiceNewDowntimeDurationText = new TextField();
		updateServiceNewDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceNewDowntimeStartTime= new Text("New downtime start time: ");
		updateServiceNewDowntimeStartTimeText = new TextField();
		updateServiceNewDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		updateServiceButton = new Button("Update service");

		//-----------------------------------------------------------------------------------------------------------

		cancelServiceLabel = new Text("Cancel a service");
		cancelServiceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		cancelServiceLabel.setFill(Color.BLUE);

		cancelServiceFirstInstruction = new Text("Please enter the service"
				+ " you would like to cancel.");
		cancelServiceFirstInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		cancelServiceNameLabel = new Text("Service name: ");
		cancelServiceNameText = new TextField();
		cancelServiceNameLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorCancelServiceMessage = new Text("");
		errorCancelServiceMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorCancelServiceMessage.setFill(Color.RED);

		//    	cancelAppDateLabel = new Text("Date: ");
		//    	cancelAppDatePicker = new DatePicker();
		//    	cancelAppDateLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		//    	
		//    	cancelAppStartTimeLabel = new Text("Start time: ");
		//    	cancelAppStartTimeText = new TextField();
		//    	cancelAppStartTimeLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		//    	
		cancelServiceButton = new Button("Cancel service");


		gridPaneAddService = new GridPane();
		gridPaneAddService.setMinSize(500,70);
		gridPaneAddService.setPadding(new Insets(100,100,100,100));	
		gridPaneAddService.setVgap(10);
		gridPaneAddService.setHgap(10);
		gridPaneAddService.setAlignment(Pos.CENTER);
		gridPaneAddService.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneUpdateService = new GridPane();
		gridPaneUpdateService.setMinSize(800, 130);
		gridPaneUpdateService.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneUpdateService.setVgap(10);
		gridPaneUpdateService.setHgap(10);
		gridPaneUpdateService.setAlignment(Pos.CENTER);
		gridPaneUpdateService.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneCancelService = new GridPane();
		gridPaneCancelService.setMinSize(500, 70);
		gridPaneCancelService.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneCancelService.setVgap(10);
		gridPaneCancelService.setHgap(10);
		gridPaneCancelService.setAlignment(Pos.CENTER);
		gridPaneCancelService.setStyle("-fx-background-color: LIGHTBLUE;");



		splitPane = new SplitPane();
		splitPane.setMinSize(1100, 600);
		splitPane.setMaxSize(1100, 600);
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneAddService.add(addService, 0, 0,2,1);
		gridPaneAddService.add(addServiceInstruction, 0, 1,5,1);
		gridPaneAddService.add(addServiceName, 0, 2);
		gridPaneAddService.add(addServiceNameText, 1, 2); 
		gridPaneAddService.add(addServiceDuration, 3, 2);
		gridPaneAddService.add(addServiceDurationText,4,2);
		gridPaneAddService.add(addServiceDowntimeDuration, 0, 3);
		gridPaneAddService.add(addServiceDowntimeDurationText, 1, 3);
		gridPaneAddService.add(addServiceDowntimeStartTime,3,3);
		gridPaneAddService.add(addServiceDowntimeStartTimeText,4,3);   
		gridPaneAddService.add(addServiceButton, 2, 7);


		gridPaneUpdateService.add(updateServiceLabel, 1, 0,2,1);
		gridPaneUpdateService.add(updateServiceOldInstruction, 0, 1,6,1);
		gridPaneUpdateService.add(updateServiceLabelName, 0, 2);
		gridPaneUpdateService.add(updateServiceText, 1, 2);
		gridPaneUpdateService.add(updateServiceNewInstruction, 0, 3,2,1);
		gridPaneUpdateService.add(updateServiceYes,  2, 3);
		gridPaneUpdateService.add(updateServiceNo,  3, 3);
		gridPaneUpdateService.add(updateServiceInstruction, 0, 6,9,1);
		gridPaneUpdateService.add(updateServiceNewName, 0, 5);
		gridPaneUpdateService.add(updateServiceNewNameText, 1, 5);
		gridPaneUpdateService.add(updateServiceNewDuration, 2, 5);
		gridPaneUpdateService.add(updateServiceNewDurationText, 3, 5);
		gridPaneUpdateService.add(updateServiceNewDowntimeDuration, 4,5);
		gridPaneUpdateService.add(updateServiceNewDowntimeDurationText, 6, 5);
		gridPaneUpdateService.add(updateServiceNewDowntimeStartTime, 7, 5);
		gridPaneUpdateService.add(updateServiceNewDowntimeStartTimeText, 8, 5);
		gridPaneUpdateService.add(updateServiceButton, 3, 7,2,1);

		gridPaneCancelService.add(cancelServiceLabel,1,0,2,1);
		gridPaneCancelService.add(cancelServiceFirstInstruction, 0,1,5,1);
		gridPaneCancelService.add(cancelServiceNameLabel, 0, 2);
		gridPaneCancelService.add(cancelServiceNameText, 1, 2);
		gridPaneCancelService.add(cancelServiceButton, 3,2);


		verticalMenuAddService = new VBox();
		verticalMenuAddService.setPadding(new Insets(10));
		verticalMenuAddService.setSpacing(8);

		verticalMenuUpdateService = new VBox();
		verticalMenuUpdateService.setPadding(new Insets(10));
		verticalMenuUpdateService.setSpacing(8);

		verticalMenuCancelService = new VBox();
		verticalMenuCancelService.setPadding(new Insets(10));
		verticalMenuCancelService.setSpacing(8);

		Text serviceMenuTitle1 = new Text("What do you wish to do?");
		serviceMenuTitle1.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text serviceMenuTitle2 = new Text("What do you wish to do?");
		serviceMenuTitle2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text serviceMenuTitle3 = new Text("What do you wish to do?");
		serviceMenuTitle3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuAddService.getChildren().add(serviceMenuTitle1);
		verticalMenuUpdateService.getChildren().add(serviceMenuTitle2);
		verticalMenuCancelService.getChildren().add(serviceMenuTitle3);


		addServiceLink1 = new Hyperlink("Add a service");
		updateServiceLink1 = new Hyperlink("Update a service");
		cancelServiceLink1 = new Hyperlink ("Cancel a service");

		addServiceLink2 = new Hyperlink("Add a service");
		updateServiceLink2 = new Hyperlink("Update a service");
		cancelServiceLink2 = new Hyperlink ("Cancel a service");

		addServiceLink3 = new Hyperlink("Add a service");
		updateServiceLink3 = new Hyperlink("Update a service");
		cancelServiceLink3 = new Hyperlink ("Cancel a service");

		Hyperlink options4[] = new Hyperlink[] {
				addServiceLink1,
				updateServiceLink1,
				cancelServiceLink1};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options4[i], new Insets(0, 0, 0, 8));
			verticalMenuAddService.getChildren().add(options4[i]);
		}

		Hyperlink options5[] = new Hyperlink[] {
				addServiceLink2,
				updateServiceLink2,
				cancelServiceLink2};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options5[i], new Insets(0, 0, 0, 8));
			verticalMenuUpdateService.getChildren().add(options5[i]);
		}

		Hyperlink options6[] = new Hyperlink[] {
				addServiceLink3,
				updateServiceLink3,
				cancelServiceLink3};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options6[i], new Insets(0, 0, 0, 8));
			verticalMenuCancelService.getChildren().add(options6[i]);
		}

		addServiceBorderPane = new BorderPane();
		addServiceBorderPane.setLeft(verticalMenuAddService);
		addServiceBorderPane.setCenter(gridPaneAddService);

		updateServiceBorderPane = new BorderPane();
		updateServiceBorderPane.setLeft(verticalMenuUpdateService);
		updateServiceBorderPane.setCenter(gridPaneUpdateService);

		cancelServiceBorderPane = new BorderPane();
		cancelServiceBorderPane.setLeft(verticalMenuCancelService);
		cancelServiceBorderPane.setCenter(gridPaneCancelService);

		addServiceScene = new Scene(addServiceBorderPane);
		updateServiceScene = new Scene(updateServiceBorderPane);
		cancelServiceScene = new Scene(cancelServiceBorderPane);


		addServiceLink1.setOnAction(e->{
			primaryStage.setTitle("Add a service");
			primaryStage.setScene(addServiceScene);
		});
		updateServiceLink1.setOnAction(e->{
			primaryStage.setTitle("Update a service");
			primaryStage.setScene(updateServiceScene);
		});

		cancelServiceLink1.setOnAction(e->{
			primaryStage.setTitle("Cancel a service");
			primaryStage.setScene(cancelServiceScene);
		});  

		addServiceLink2.setOnAction(e->{
			primaryStage.setTitle("Add a service");
			primaryStage.setScene(addServiceScene);
		});

		updateServiceLink2.setOnAction(e->{
			primaryStage.setTitle("Update a service");
			primaryStage.setScene(updateServiceScene);
		});

		cancelServiceLink2.setOnAction(e->{
			primaryStage.setTitle("Cancel a service");
			primaryStage.setScene(cancelServiceScene);
		});

		addServiceLink3.setOnAction(e->{
			primaryStage.setTitle("Add a service");
			primaryStage.setScene(addServiceScene);
		});

		updateServiceLink3.setOnAction(e->{
			primaryStage.setTitle("Update a service");
			primaryStage.setScene(updateServiceScene);
		});

		cancelServiceLink3.setOnAction(e->{
			primaryStage.setTitle("Cancel a service");
			primaryStage.setScene(cancelServiceScene);
		});


		addServiceButton.setOnAction(e->{
			try {
				FlexiBookController.addService(serviceNameTextField.getText(),Integer.parseInt(serviceDurationTextField),
						Integer.parseInt(serviceDowntimeDurationTextField),Integer.parseInt(serviceDowntimeStartTextField), 
						FlexiBookApplication.getCurrentUser().getUsername());

				errorAddServiceMessage.setText("");
			} catch (InvalidInputException e1) {
				errorAddServiceMessage.setText(e1.getMessage());
			}
		});


		updateServiceButton.setOnAction(e->{
			try {
				FlexiBookController.updateService(serviceTextField.getText(),Integer.parseInt(serviceDurationTextField),
						Integer.parseInt(serviceDowntimeDurationTextField),Integer.parseInt(serviceDowntimeStartTextField),
						FlexiBookApplication.getCurrentUser().getUsername() , serviceNameTextField.getText());
				errorUpdateServiceMessage.setText("");
			} catch (InvalidInputException e1) {
				errorUpdateServiceMessage.setText(e1.getMessage());
			}
		});

		cancelServiceButton.setOnAction(e->{
			try {
				FlexiBookController.deleteService(serviceNameTextField.getText(),FlexiBookApplication.getCurrentUser().getUsername());			  
				errorCancelServiceMessage.setText("");
			} catch (InvalidInputException e1) {
				errorCancelServiceMessage.setText(e1.getMessage());
			}
		});
		
		//Account Page--------------------------------------------------------------------------------------------------
		
		
		// adjusting grid
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(20);
        
        // adjusting border
        accountRoot.setMinSize(800, 500);
        accountRoot.setPadding(new Insets(15,15,15,15));
        accountRoot.setTop(header);
        accountRoot.setCenter(grid);
        accountRoot.setBottom(deleteLink);
        
        
        //aligning panes
        BorderPane.setAlignment(header, Pos.TOP_CENTER);
        BorderPane.setAlignment(deleteLink, Pos.BOTTOM_CENTER);
		grid.setAlignment(Pos.CENTER);
		
        
        // adding onto the grid
		grid.add(instruction1, 0, 0);
        grid.add(updateUsernameText, 0, 1);
        updateUsernameTextField.setPromptText("Username");
        grid.add(updateUsernameTextField, 1, 1);
        grid.add(updatePasswordText, 0, 2);
        updatePasswordTextField.setPromptText("Password");
        grid.add(updatePasswordTextField, 1, 2);
        
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
				FlexiBookController.updateAccount(updateUsernameTextField.getText(), newUsernameText.getText(),
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
        	primaryStage.setScene(updateAccScene);
        });
        

        accountRoot.setStyle("-fx-background-color: LIGHTBLUE;");
        instruction1.setStyle("-fx-font: normal italic 11px 'Verdana' ");
        instruction2.setStyle("-fx-font: normal italic 11px 'Verdana' ");
        header.setStyle("-fx-font: normal bold 25px 'Verdana' ");
        updateUsernameText.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
        updatePasswordText.setStyle("-fx-font: normal bold 15px 'Verdana' ");  
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
  	
	}

	private void resetLoginPage() {
		errorLoginText.setText("");
		errorSignUpText.setText("");
		usernameTextField.setText("");
		usernameTextField2.setText("");
		passwordTextField.setText("");
		passwordTextField2.setText("");
		confirmPasswordTextField.setText("");
		error.setText("");
	}

}