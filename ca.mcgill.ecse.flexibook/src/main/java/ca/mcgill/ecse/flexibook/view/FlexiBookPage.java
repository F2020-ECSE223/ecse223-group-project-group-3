package ca.mcgill.ecse.flexibook.view;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.SystemTime;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour;
import ca.mcgill.ecse223.flexibook.controller.TOTimeSlot;
import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour.TODayOfWeek;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private VBox verticalMenuApp;
	private Hyperlink makeAppLink;
	private Hyperlink updateAppLink;
	private Hyperlink cancelAppLink;
	private Hyperlink backToMenuAppLink;


	//Horizontal Box
	private HBox horizontalMakeApp;
	private HBox horizontalUpdateApp;
	private HBox horizontalCancelApp;

	//Border Pane
	private BorderPane appBorderPane;



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


	///Owner Main Menu----------------------------------------------------------------------------------------------
	private Text welcome;
	private Text flexiBookText;

	private DropShadow dS;

	private FontIcon ownerProfileIcon;
	private FontIcon businessIcon;
	private FontIcon serviceIcon;
	private FontIcon appointmentIcon;
	private FontIcon ownerLogoutIcon;

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

	private FontIcon customerProfileIcon;
	private FontIcon customerAppIcon;
	private FontIcon customerBusinessIcon;
	private FontIcon customerLogoutIcon;

	private JFXButton customerProfileButton;
	private JFXButton customerAppButton;
	private JFXButton customerBusinessButton;
	private JFXButton customerLogoutButton;

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
	private Text errordeleteServiceMessage;
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
	//--------------------------------------ADD SERVICE----------------------------------------------------------	

	//Update Service
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

	//New duration
	private Text updateServiceNewDuration;
	//New duration text field
	private TextField updateServiceNewDurationText;

	//New downtimeduration 
	private Text updateServiceNewDowntimeDuration;
	//New downtimeduration text field
	private TextField updateServiceNewDowntimeDurationText;
	//New downtime start time
	private Text updateServiceNewDowntimeStartTime;
	//New downtime start time text field
	private TextField updateServiceNewDowntimeStartTimeText;
	//New update Service button
	private Button updateServiceButton;

	//-------------------------------------------------------------------------------	
	//delete Service
	private Text deleteServiceLabel;

	//First instruction message
	private Text deleteServiceFirstInstruction;

	//Service name label
	private Text deleteServiceNameLabel;
	//Service name text field
	private TextField deleteServiceNameText;

	//delete service button
	private Button deleteServiceButton;


	private GridPane gridPaneAddService;
	private GridPane gridPaneUpdateService;
	private GridPane gridPanedeleteService;
	private SplitPane sP;
	private Hyperlink addServiceLink;
	private Hyperlink updateServiceLink;
	private Hyperlink deleteServiceLink;
	private Hyperlink mainMenuLink;
	private VBox verticalMenu;
	private BorderPane serviceBorderPane;
	private Scene serviceScene;

	// Time Slot ----------------------------------------------------------------------------------------
	//error messages
	private Text erroraddTimeSlotMessage;
	private Text errorupdateTimeSlotMessage;
	private Text errordeleteTimeSlotMessage;

	// View Holidays	
	private VBox verticalMenuaViewHoliday;
	private Hyperlink viewHolidayLink1;
	private TableView<TOTimeSlot> viewHolidayTable;
	
	// View Vacation
	private VBox verticalMenuaViewVacation;
	private Hyperlink viewVacationLink1;
	private TableView<TOTimeSlot> viewVacationTable;
	
	// Add Time Slot
	//------------------------------------------------------------------------------------------------	

	private Text addTimeSlot;
	private Text addTimeSlotInstruction;
	private Text addTimeSlotType;
	private ComboBox<String> addTimeSlotTypeText;
	private Text addTimeSlotStartDate;
	private TextField addTimeSlotStartDateText;
	private Text addTimeSlotEndDate;
	private TextField addTimeSlotEndDateText;
	private Text addTimeSlotStartTime;
	private TextField addTimeSlotStartTimeText;
	private Text addTimeSlotEndTime;
	private TextField addTimeSlotEndTimeText;

	private Button addTimeSlotButton;

	// Update Time Slot
	//------------------------------------------------------------------------------------------------	

	private Text updateTimeSlot;
	private Text updateTimeSlotInstruction;
	private Text updateTimeSlotType;
	private ComboBox<String> updateTimeSlotTypeText;
	private Text updateTimeSlotOldDate;
	private TextField updateTimeSlotOldDateText;
	private Text updateTimeSlotNewStartDate;
	private TextField updateTimeSlotNewStartDateText;
	private Text updateTimeSlotNewEndDate;
	private TextField updateTimeSlotNewEndDateText;
	private Text updateTimeSlotOldTime;
	private TextField updateTimeSlotOldTimeText;
	private Text updateTimeSlotNewStartTime;
	private TextField updateTimeSlotNewStartTimeText;
	private Text updateTimeSlotNewEndTime;
	private TextField updateTimeSlotNewEndTimeText;

	private Button updateTimeSlotButton;

	// Delete Time Slot
	//-------------------------------------------------------------------------------	
	private Text deleteTimeSlot;
	private Text deleteTimeSlotInstruction;
	private Text deleteTimeSlotType;
	private ComboBox<String> deleteTimeSlotTypeText;
	private Text deleteTimeSlotStartDate;
	private TextField deleteTimeSlotStartDateText;
	private Text deleteTimeSlotEndDate;
	private TextField deleteTimeSlotEndDateText;
	private Text deleteTimeSlotStartTime;
	private TextField deleteTimeSlotStartTimeText;
	private Text deleteTimeSlotEndTime;
	private TextField deleteTimeSlotEndTimeText;

	private Button deleteTimeSlotButton;


	//Grid pane
	private GridPane gridPaneaddTimeSlot;
	private GridPane gridPaneupdateTimeSlot;
	private GridPane gridPanedeleteTimeSlot;

	private SplitPane splitPane5;
	private VBox verticalMenuaddTimeSlot;
	private VBox verticalMenuupdateTimeSlot;
	private VBox verticalMenuDeleteTimeSlot;
	private Hyperlink addTimeSlotLink1;
	private Hyperlink updateTimeSlotLink1;
	private Hyperlink deleteTimeSlotLink1;
	private Hyperlink timeSlotGoBackLink1;
	private Hyperlink timeSlotMainMenuLink1;



	//Update Customer Account-------------------------------------------------------------------------------------------------

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
	
	
	//Update Owner Account------------------------------------------------------------------------------------------------
	
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
	private Scene updateOwnerAccscene;

	
	
	// Owner Business Menu-----------------------------------------------------------------
	private Text businessMenu;
	private DropShadow dS1;
	private FontIcon businessInformationIcon;
	private FontIcon businessHoursIcon;
	private FontIcon holidaysVacationsIcon;
	private FontIcon businessMenuGoBackIcon;
	private JFXButton businessInformationButton;
	private JFXButton businessHoursButton;
	private JFXButton holidaysVacationsButton;
	private JFXButton businessMenuGoBackButton;
	private HBox businessMenuLabelHBox;
	private HBox businessInformationHBox;
	private HBox businessMenuSloganHBox;
	private BorderPane businessMenuBorderPane;
	private Scene businessMenuMainScene;

	//Business Page-----------------------------------------------------------------
	private Text ownerViewBusinessInfo;
	private Text ownerViewBusinessName;
	private Text ownerViewBusinessNameResult;
	private Text ownerViewPhoneNumber;
	private Text ownerViewPhoneNumberResult;
	private Text ownerViewAddress;
	private Text ownerViewAddressResult;
	private Text ownerViewEmail;
	private Text ownerViewEmailResult;

	private Text errorBusinessInfoMessage;
	private Text editBusinessInfo;
	private Text editBusinnessInfoInstruction;
	private Text addBusinessName;
	private TextField addBusinessNameText;
	private Text addAddress;
	private TextField addAddressText;
	private Text addPhoneNumber;
	private TextField addPhoneNumberText;
	private Text addEmail;
	private TextField addEmailText;
	private Button addBusinessButton;
	private GridPane gridPaneEditBusinessInfo;
	private GridPane gridPaneownerViewBusinessInfo;
	private SplitPane splitPane;
	private VBox verticalMenuownerViewBusinessInfo;
	private VBox verticalMenuEditBusinessInfo;
	private Hyperlink editBusinessInfoLink1;
	private Hyperlink ownerViewBusinessInfoLink1;
	private Hyperlink ownerBusinessInfoGoBackLink1;
	private Hyperlink ownerBusinessInfoMainMenuLink1;
	private VBox verticalMenuBusinessInfo;
	private BorderPane ownerBusinessInfoPane;
	private Scene ownerBusinessScene;

	// Business hours ---------------------------------------------
	//error messages
	private Text errorAddHoursMessage;
	private Text errorUpdateHoursMessage;
	private Text errorDeleteHoursMessage;
	
	// View Hours
	//------------------------------------------------------------------------------------------------	
	
	private GridPane gridPaneViewHours;
	private VBox verticalMenuaViewHours;
	private Hyperlink viewHoursLink1;
	private TableView<TOBusinessHour> viewBusinessHourTable;

	// Add Hours
	//------------------------------------------------------------------------------------------------	

	private Text addHours;
	private Text addHoursInstruction;
	private Text addHoursDay;
	private ComboBox addHoursDayText;
	private Text addHoursStartTime;
	private TextField addHoursStartTimeText;
	private Text addHoursEndTime;
	private TextField addHoursEndTimeText;
	private Button addHoursButton;

	// Update Hours
	//------------------------------------------------------------------------------------------------	

	private Text updateHoursLabel;
	private Text updateHoursInstruction;
	private Text updateHoursOldDay;
	private ComboBox updateHoursOldDayText;
	private Text updateHoursNewDay;
	private ComboBox updateHoursNewDayText;
	private Text updateHoursOldTime;
	private TextField updateHoursOldTimeText;
	private Text updateHoursNewStartTime;
	private TextField updateHoursNewStartTimeText;
	private Text updateHoursNewEndTime;
	private TextField updateHoursNewEndTimeText;
	private Button updateHoursButton;

	// Delete Hours
	//-------------------------------------------------------------------------------	
	private Text deleteHoursLabel;
	private Text deleteHoursInstruction;
	private Text deleteHoursDay;
	private ComboBox deleteHoursDayText;
	private Text deleteHoursTime;
	private TextField deleteHoursTimeText;
	private Button deleteHoursButton;

	//Grid pane
	private GridPane gridPaneaddHours;
	private GridPane gridPaneUpdateHours;
	private GridPane gridPaneDeleteHours;

	private SplitPane splitPane3;
	private VBox verticalMenuaddHours;
	private VBox verticalMenuUpdateHours;
	private VBox verticalMenuDeleteHours;
	private Hyperlink addHoursLink1;
	private Hyperlink updateHoursLink1;
	private Hyperlink deleteHoursLink1;
	private Hyperlink businessHoursGoBackLink1;
	private Hyperlink businessHoursMainMenuLink1;
	private VBox verticalMenuHours;
	private BorderPane businessHoursBorderPane;
	private Scene businessHoursScene;

	// Customer Business Information----------------------------------------------------------------
	private Text customerViewBusinessInfo;
	private Text customerViewBusinessName;
	private Text customerViewBusinessNameResult;
	private Text customerViewPhoneNumber;
	private Text customerViewPhoneNumberResult;
	private Text customerViewAddress;
	private Text customerViewAddressResult;
	private Text customerViewEmail;
	private Text customerViewEmailResult;


	private GridPane gridPanecustomerViewBusinessInfo;
	private SplitPane splitPane7;
	private VBox verticalMenucustomerViewBusinessInfo;
	private Hyperlink customerViewBusinessInfoLink1;
	private Hyperlink customerBusinessInfoMainMenuLink1;
	private BorderPane customerViewBusinessInfoPane;
	private Scene customerViewBusinessScene;


	private VBox verticalMenuTimeSlot;


	private BorderPane TimeSlotBorderPane;


	private Scene timeSlotScene;



	public FlexiBookPage(Stage stage) {	
		initView(stage);
	}


	public void initView(Stage primaryStage) {

		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		Date currentDate = Date.valueOf(date);
		Time currentTime = Time.valueOf(time);
		SystemTime.setSysDate(currentDate);
		SystemTime.setSysTime(currentTime);

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
		ownerProfileIcon = new FontIcon("fa-user-circle-o");
		businessIcon = new FontIcon("fa-briefcase");
		appointmentIcon = new FontIcon("fa-calendar");
		serviceIcon = new FontIcon("fa-tasks");
		ownerLogoutIcon = new FontIcon("fa-sign-out");

		ownerProfileIcon.getStyleClass().add("icon");
		businessIcon.getStyleClass().add("icon");
		appointmentIcon.getStyleClass().add("icon");
		serviceIcon.getStyleClass().add("icon");
		ownerLogoutIcon.getStyleClass().add("icon");

		ownerProfileIcon.setFill(Color.BLUE);
		ownerProfileIcon.setIconSize(50);
		businessIcon.setFill(Color.BLUE);
		businessIcon.setIconSize(50);
		appointmentIcon.setFill(Color.BLUE);
		appointmentIcon.setIconSize(50);
		serviceIcon.setFill(Color.BLUE);
		serviceIcon.setIconSize(50);
		ownerLogoutIcon.setFill(Color.BLUE);
		ownerLogoutIcon.setIconSize(50);

		ownerProfileButton = new JFXButton("Account", ownerProfileIcon);
		ownerProfileButton.setContentDisplay(ContentDisplay.TOP);
		ownerProfileButton.setOnAction(e->{
			primaryStage.setTitle("Account Page");
			primaryStage.setScene(updateOwnerAccscene);
			primaryStage.show();
		});
		ownerProfileButton.getStyleClass().add("main-menu-button");
		ownerProfileButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		businessButton = new JFXButton("Business", businessIcon);
		businessButton.setContentDisplay(ContentDisplay.TOP);
		businessButton.setOnAction(e->{
			primaryStage.setTitle("Business Page");
			primaryStage.setScene(businessMenuMainScene);
			primaryStage.show();
		});
		businessButton.getStyleClass().add("main-menu-button");
		businessButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));


		serviceButton = new JFXButton("Services", serviceIcon);
		serviceButton.setContentDisplay(ContentDisplay.TOP);
		serviceButton.setOnAction(e->{
			primaryStage.setTitle("Services Page");
			primaryStage.setScene(serviceScene);
			primaryStage.show();

		});
		serviceButton.getStyleClass().add("main-menu-button");
		serviceButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		appointmentButton = new JFXButton("Appointments", appointmentIcon);
		appointmentButton.setContentDisplay(ContentDisplay.TOP);
		appointmentButton.setOnAction(e->{
			primaryStage.setTitle("StartEndRegister");
			primaryStage.setScene(ownerAppScene);
			primaryStage.show();
		});
		appointmentButton.getStyleClass().add("main-menu-button");
		appointmentButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));



		ownerLogoutButton = new JFXButton("Logout", ownerLogoutIcon);
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


		//Customer Main Menu---------------------------------------------------------

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

		customerIconsHBox = new HBox(50);
		customerIconsHBox.setAlignment(Pos.CENTER);
		customerProfileIcon = new FontIcon("fa-user-circle-o");
		customerAppIcon = new FontIcon("fa-calendar");
		customerBusinessIcon = new FontIcon("fa-briefcase");
		customerLogoutIcon = new FontIcon("fa-sign-out");

		customerProfileIcon.getStyleClass().add("icon");
		customerAppIcon.getStyleClass().add("icon");
		customerBusinessIcon.getStyleClass().add("icon");
		customerLogoutIcon.getStyleClass().add("icon");

		customerProfileIcon.setFill(Color.BLUE);
		customerProfileIcon.setIconSize(50);
		customerAppIcon.setFill(Color.BLUE);
		customerAppIcon.setIconSize(50);
		customerBusinessIcon.setFill(Color.BLUE);
		customerBusinessIcon.setIconSize(50);
		customerLogoutIcon.setFill(Color.BLUE);
		customerLogoutIcon.setIconSize(50);

		customerProfileButton = new JFXButton("Account", customerProfileIcon);
		customerProfileButton.setContentDisplay(ContentDisplay.TOP);
		customerProfileButton.setOnAction(e->{
			primaryStage.setTitle("Account Page");
			primaryStage.setScene(updateAccScene);
			primaryStage.show();
		});
		customerProfileButton.getStyleClass().add("main-menu-button");
		customerProfileButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		customerAppButton = new JFXButton("My appointments", customerAppIcon);
		customerAppButton.setContentDisplay(ContentDisplay.TOP);
		customerAppButton.setOnAction(e->{
			primaryStage.setTitle("Make an appointment");
			primaryStage.setScene(appScene);
			primaryStage.show();
		});
		customerAppButton.getStyleClass().add("main-menu-button");
		customerAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		customerBusinessButton = new JFXButton("Business Information", customerBusinessIcon);
		customerBusinessButton.setContentDisplay(ContentDisplay.TOP);
		customerBusinessButton.setOnAction(e->{
			primaryStage.setTitle("Business Information");
			primaryStage.setScene(customerViewBusinessScene);
			primaryStage.show();
		});
		customerBusinessButton.getStyleClass().add("main-menu-button");
		customerBusinessButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		customerLogoutButton = new JFXButton("Logout", customerLogoutIcon);
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

		customerIconsHBox.getChildren().addAll(customerProfileButton, customerAppButton, customerBusinessButton, customerLogoutButton);

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

		gridPaneMakeApp.add(errorMakeAppointment, 6, 0);
		gridPaneMakeApp.add(makeAppInstruction, 0, 1,5,1);
		gridPaneMakeApp.add(makeAppServiceLabel, 0, 2);
		gridPaneMakeApp.add(makeAppServiceText, 1, 2); 
		gridPaneMakeApp.add(makeAppDateLabel, 3, 2);
		gridPaneMakeApp.add(makeAppDatePicker, 4, 2,2,1);
		gridPaneMakeApp.add(makeAppStartTimeLabel, 5, 2);
		gridPaneMakeApp.add(makeAppStartTimeText, 6, 2);
		gridPaneMakeApp.add(makeAppButton, 4, 3,2,2);

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


		verticalMenuApp = new VBox();
		verticalMenuApp.setPadding(new Insets(10));
		verticalMenuApp.setSpacing(8);
		verticalMenuApp.setStyle("-fx-background-color: #336699;");


		Text titleApp = new Text("What do you wish to do?");
		titleApp.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuApp.getChildren().add(titleApp);

		makeAppLink = new Hyperlink("Make an appointment");
		makeAppLink.setStyle("-fx-text-fill: blue;");
		makeAppLink.setFont(Font.font("Verdana", 15));
		updateAppLink = new Hyperlink("Update an appointment");
		updateAppLink.setStyle("-fx-text-fill: blue;");
		updateAppLink.setFont(Font.font("Verdana", 15));
		cancelAppLink = new Hyperlink ("Cancel an appointment");
		cancelAppLink.setStyle("-fx-text-fill: blue;");
		cancelAppLink.setFont(Font.font("Verdana", 15));
		backToMenuAppLink = new Hyperlink("Main Menu");
		backToMenuAppLink.setStyle("-fx-text-fill: white;");
		backToMenuAppLink.setFont(Font.font("Verdana", 15));


		Hyperlink optionsApp[] = new Hyperlink[] {
				makeAppLink,
				updateAppLink,
				cancelAppLink,
				backToMenuAppLink};

		for (int i=0; i<4; i++) {
			VBox.setMargin(optionsApp[i], new Insets(0, 0, 0, 8));
			verticalMenuApp.getChildren().add(optionsApp[i]);
		}


		HBox appSloganHBox =new HBox();
		appSloganHBox.setAlignment(Pos.CENTER);
		Text flexiBookTextApp = new Text("Flexibook, it's time to get organised!");
		flexiBookTextApp.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		flexiBookTextApp.setFill(Color.BLUE);
		appSloganHBox.getChildren().add(flexiBookTextApp);
		appSloganHBox.setStyle("-fx-background-color: #336699;");


		appBorderPane = new BorderPane();
		appBorderPane.setLeft(verticalMenuApp);
		appBorderPane.setCenter(gridPaneMakeApp);
		appBorderPane.setTop(horizontalMakeApp);
		appBorderPane.setBottom(appSloganHBox);

		appScene = new Scene(appBorderPane);



		makeAppLink.setOnAction(e->{
			appBorderPane.setCenter(gridPaneMakeApp);
			appBorderPane.setTop(horizontalMakeApp);
			primaryStage.setTitle("Make an appointment");
		});

		updateAppLink.setOnAction(e->{
			primaryStage.setTitle("Update an appointment");
			appBorderPane.setCenter(gridPaneUpdateApp);
			appBorderPane.setTop(horizontalUpdateApp);
		});

		cancelAppLink.setOnAction(e->{
			primaryStage.setTitle("Cancel an appointment");
			appBorderPane.setCenter(gridPaneCancelApp);
			appBorderPane.setTop(horizontalCancelApp);
		});  


		backToMenuAppLink.setOnAction(e->{
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(customerMainScene);
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
				FlexiBookController.registerNoShow(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText());
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
		updateServiceInstruction = new Text("Note:If one of the service info hasn't changed, kindly rewrite it. ");

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

		deleteServiceLabel = new Text("Delete a service");
		deleteServiceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		deleteServiceLabel.setFill(Color.BLUE);

		deleteServiceFirstInstruction = new Text("Please enter the service"
				+ " you would like to delete.");
		deleteServiceFirstInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		deleteServiceNameLabel = new Text("Service name: ");
		deleteServiceNameText = new TextField();
		deleteServiceNameLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errordeleteServiceMessage = new Text("");
		errordeleteServiceMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errordeleteServiceMessage.setFill(Color.RED);		
		deleteServiceButton = new Button("Delete service");


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

		gridPanedeleteService = new GridPane();
		gridPanedeleteService.setMinSize(500, 70);
		gridPanedeleteService.setPadding(new Insets(100, 100, 100, 100));	
		gridPanedeleteService.setVgap(10);
		gridPanedeleteService.setHgap(10);
		gridPanedeleteService.setAlignment(Pos.CENTER);
		gridPanedeleteService.setStyle("-fx-background-color: LIGHTBLUE;");



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
		gridPaneUpdateService.add(updateServiceInstruction, 0, 8,9,1);
		gridPaneUpdateService.add(updateServiceNewName, 0, 5);
		gridPaneUpdateService.add(updateServiceNewNameText, 1, 5);
		gridPaneUpdateService.add(updateServiceNewDuration, 2, 5);
		gridPaneUpdateService.add(updateServiceNewDurationText, 3, 5);
		gridPaneUpdateService.add(updateServiceNewDowntimeDuration, 0,6);
		gridPaneUpdateService.add(updateServiceNewDowntimeDurationText, 1, 6);
		gridPaneUpdateService.add(updateServiceNewDowntimeStartTime, 2, 6);
		gridPaneUpdateService.add(updateServiceNewDowntimeStartTimeText, 3,6);
		gridPaneUpdateService.add(updateServiceButton, 2, 7,2,1);

		gridPanedeleteService.add(deleteServiceLabel,1,0,2,1);
		gridPanedeleteService.add(deleteServiceFirstInstruction, 0,1,5,1);
		gridPanedeleteService.add(deleteServiceNameLabel, 0, 2);
		gridPanedeleteService.add(deleteServiceNameText, 1, 2);
		gridPanedeleteService.add(deleteServiceButton, 3,2);


		verticalMenu = new VBox();
		verticalMenu.setPadding(new Insets(10));
		verticalMenu.setSpacing(8);



		Text sericeMenuTitle = new Text("What do you wish to do?");
		sericeMenuTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 18));


		verticalMenu.getChildren().add(sericeMenuTitle);


		addServiceLink = new Hyperlink("Add a service");
		updateServiceLink = new Hyperlink("Update a service");
		deleteServiceLink = new Hyperlink ("Delete a service");
		mainMenuLink = new Hyperlink("Main Menu");

		Hyperlink options[] = new Hyperlink[] {
				addServiceLink,
				updateServiceLink,
				deleteServiceLink,
				mainMenuLink};

		for (int i=0; i<4; i++) {
			VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
			verticalMenu.getChildren().add(options[i]);
		}



		serviceBorderPane = new BorderPane();
		serviceBorderPane.setLeft(verticalMenu);
		serviceBorderPane.setCenter(gridPaneAddService);


		serviceScene = new Scene(serviceBorderPane);

		addServiceLink.setOnAction(e->{
			serviceBorderPane.setCenter(gridPaneAddService);
			primaryStage.setTitle("Add a service");
		});
		updateServiceLink.setOnAction(e->{
			serviceBorderPane.setCenter(gridPaneUpdateService);
			primaryStage.setTitle("Update a service");
		});

		deleteServiceLink.setOnAction(e->{
			serviceBorderPane.setCenter(gridPanedeleteService);
			primaryStage.setTitle("Delete a service");
		});  

		mainMenuLink.setOnAction(e->{
			primaryStage.setScene(ownerMainScene);
			primaryStage.setTitle("Main Menu");
		});  

		addServiceButton.setOnAction(e->{
			try {
				if(addServiceNameText.getText()== null || addServiceNameText.getText().trim().isEmpty()) {
					errorAddServiceMessage.setText("A service name should be set");
				}
				if (addServiceDurationText.getText().trim().isEmpty()){
					errorAddServiceMessage.setText("A service duration should be set");
				}
				else if(addServiceDowntimeDurationText.getText().trim().isEmpty()){		    		
					errorAddServiceMessage.setText("A service downtime duration should be set");
				}
				else if(addServiceDowntimeStartTimeText.getText().trim().isEmpty()) {
					errorAddServiceMessage.setText("A downtime start time should be set");
				}
				else {
					FlexiBookController.addService(addServiceNameText.getText(),Integer.parseInt(addServiceDurationText.getText()),
							Integer.parseInt(addServiceDowntimeDurationText.getText()),Integer.parseInt(addServiceDowntimeStartTimeText.getText()), 
							FlexiBookApplication.getCurrentUser().getUsername());
					Alert a = new Alert(AlertType.CONFIRMATION, "Service added successfully");
					a.showAndWait();
					errorAddServiceMessage.setText("");
				}
			} catch (InvalidInputException e1) {
				errorAddServiceMessage.setText(e1.getMessage());
				Alert a = new Alert(AlertType.ERROR, errorAddServiceMessage.getText());
				a.showAndWait();
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

		deleteServiceButton.setOnAction(e->{
			try {
				if(deleteServiceNameText.getText()== null || deleteServiceNameText.getText().trim().isEmpty()) {
					errordeleteServiceMessage.setText("A service name should be set to get deleted");
					Alert a = new Alert(AlertType.ERROR, errordeleteServiceMessage.getText());
					a.showAndWait();
				}
				else {
					FlexiBookController.deleteService(deleteServiceNameText.getText(),
							FlexiBookApplication.getCurrentUser().getUsername());			  
					errordeleteServiceMessage.setText("");
					Alert a = new Alert(AlertType.CONFIRMATION, "Service deleted successfully");
					a.showAndWait();
				}
			} catch (InvalidInputException e1) {
				errordeleteServiceMessage.setText(e1.getMessage());
				Alert a = new Alert(AlertType.ERROR, errordeleteServiceMessage.getText());
				a.showAndWait();
			}

		});


		//Update Customer Account--------------------------------------------------------------------------------------------------


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

		        mainMenu.setOnAction(e->{
		        	primaryStage.setTitle("Main Menu");
		        	primaryStage.setScene(customerMainScene);
		        });


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
		
		
		//Update Owner Account--------------------------------------------------------------------------------------------
		
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

				ownerMainMenu.setOnAction(e->{
					primaryStage.setTitle("Main Menu");
					primaryStage.setScene(ownerMainScene);
				});


				updateOwnerAccRoot.setStyle("-fx-background-color: LIGHTBLUE;");
				instructionOwner11.setStyle("-fx-font: normal italic 11px 'Verdana' ");
				instructionOwner12.setStyle("-fx-font: normal italic 11px 'Verdana' ");
				ownerHeader.setStyle("-fx-font: normal bold 25px 'Verdana' ");
				newOwnerPassword.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
				confirmOwnerPassword.setStyle("-fx-font: normal bold 15px 'Verdana' ");
				ownerMainMenu.setStyle("-fx-font: normal 12px 'Verdana' ");

				//Initializing scenes
				updateOwnerAccscene = new Scene(updateOwnerAccRoot);
		

		// Business Menu--------------------------------------------------------------------------------------------------
		businessMenuBorderPane = new BorderPane();
		businessMenuBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
		businessMenuBorderPane.setMinSize(1100, 600); 
		businessMenuBorderPane.setMaxSize(1100, 600); 


		businessMenuLabelHBox = new HBox();
		businessMenu = new Text("Business Menu");
		businessMenu.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
		businessMenu.setFill(Color.BLUE);
		dS1 = new DropShadow();
		dS1.setOffsetY(3.0f);
		dS1.setColor(Color.color(0.4f, 0.4f, 0.4f));
		businessMenu.setEffect(dS1);
		businessMenu.setCache(true);
		businessMenuLabelHBox.getChildren().add(businessMenu);
		businessMenuLabelHBox.setAlignment(Pos.CENTER);
		businessMenuBorderPane.setTop(businessMenuLabelHBox);

		businessInformationHBox = new HBox(50);
		businessInformationHBox.setAlignment(Pos.CENTER);
		businessInformationIcon = new FontIcon("fa-info");
		businessHoursIcon = new FontIcon("fa-list-alt");
		holidaysVacationsIcon = new FontIcon("fa-calendar-o");
		businessMenuGoBackIcon = new FontIcon("fa-backward");


		businessInformationIcon.getStyleClass().add("icon");
		businessHoursIcon.getStyleClass().add("icon");
		holidaysVacationsIcon.getStyleClass().add("icon");
		businessMenuGoBackIcon.getStyleClass().add("icon");

		businessInformationIcon.setFill(Color.BLUE);
		businessInformationIcon.setIconSize(50);
		businessHoursIcon.setFill(Color.BLUE);
		businessHoursIcon.setIconSize(50);
		holidaysVacationsIcon.setFill(Color.BLUE);
		holidaysVacationsIcon.setIconSize(50);
		businessMenuGoBackIcon.setFill(Color.BLUE);
		businessMenuGoBackIcon.setIconSize(50);

		businessInformationButton = new JFXButton("Business Information", businessInformationIcon);
		businessInformationButton.setContentDisplay(ContentDisplay.TOP);
		businessInformationButton.getStyleClass().add("main-menu-button");
		businessInformationButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		businessHoursButton = new JFXButton("Business Hours", businessHoursIcon);
		businessHoursButton.setContentDisplay(ContentDisplay.TOP);
		businessHoursButton.getStyleClass().add("main-menu-button");
		businessHoursButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));


		holidaysVacationsButton = new JFXButton("Holidays and Vacations", holidaysVacationsIcon);
		holidaysVacationsButton.setContentDisplay(ContentDisplay.TOP);
		holidaysVacationsButton.getStyleClass().add("main-menu-button");
		holidaysVacationsButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		businessMenuGoBackButton = new JFXButton("Main Menu", businessMenuGoBackIcon);
		businessMenuGoBackButton.setContentDisplay(ContentDisplay.TOP);
		businessMenuGoBackButton.getStyleClass().add("main-menu-button");
		businessMenuGoBackButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));


		businessMenuSloganHBox = new HBox();
		businessMenuSloganHBox.setAlignment(Pos.CENTER);
		businessMenuBorderPane.setBottom(businessMenuSloganHBox);


		businessInformationHBox.getChildren().addAll(businessInformationButton, businessHoursButton, holidaysVacationsButton, businessMenuGoBackButton);

		businessMenuBorderPane.setCenter(businessInformationHBox);

		businessInformationButton.setOnAction(e->{
			primaryStage.setScene(ownerBusinessScene);
		});
		businessMenuGoBackButton.setOnAction(e->{
			primaryStage.setScene(ownerMainScene);
		});
		businessHoursButton.setOnAction(e->{
			primaryStage.setScene(businessHoursScene);
		});
		holidaysVacationsButton.setOnAction(e->{
			primaryStage.setScene(timeSlotScene);
		});

		businessMenuMainScene = new Scene(businessMenuBorderPane);

		// ownerView Business Info
		//------------------------------------------------------------------------------------------------	

		ownerViewBusinessInfo = new Text("View Business Information");
		ownerViewBusinessInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		ownerViewBusinessInfo.setFill(Color.BLUE);


		ownerViewBusinessName = new Text("Business Name: ");
		try {
			ownerViewBusinessNameResult = new Text(FlexiBookController.ViewBusinessInfo().get(0));
		}
		catch(InvalidInputException e) {
			ownerViewBusinessNameResult = new Text("no business name entered");
		}

		ownerViewBusinessName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	
		ownerViewBusinessNameResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));  

		ownerViewAddress = new Text("Address: ");
		try {
			ownerViewAddressResult = new Text(FlexiBookController.ViewBusinessInfo().get(1));
		}
		catch(InvalidInputException e) {
			ownerViewAddressResult = new Text("no address entered");
		}
		ownerViewAddress.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewAddressResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));



		ownerViewPhoneNumber = new Text("Phone Number: ");
		try {
			ownerViewPhoneNumberResult = new Text(FlexiBookController.ViewBusinessInfo().get(2));
		}
		catch(InvalidInputException e) {
			ownerViewPhoneNumberResult = new Text("no address entered");
		}
		ownerViewPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewPhoneNumberResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewPhoneNumberResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		ownerViewEmail = new Text("E-mail: ");
		try {
			ownerViewEmailResult = new Text(FlexiBookController.ViewBusinessInfo().get(3));
		}
		catch(InvalidInputException e) {
			ownerViewEmailResult = new Text("no address entered");
		}
		ownerViewEmail.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewEmailResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		// Edit Business Info
		//------------------------------------------------------------------------------------------------	
		editBusinessInfo = new Text("Edit Business Information");
		editBusinessInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		editBusinessInfo.setFill(Color.BLUE);
		editBusinnessInfoInstruction = new Text("Please enter the information of your business.");
		editBusinnessInfoInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorBusinessInfoMessage = new Text("");
		errorBusinessInfoMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorBusinessInfoMessage.setFill(Color.RED);


		addBusinessName = new Text("Business Name: ");
		addBusinessNameText = new TextField();
		addBusinessName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	


		addAddress = new Text("Address: ");
		addAddressText = new TextField();
		addAddress.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addPhoneNumber = new Text("Phone Number: ");
		addPhoneNumberText = new TextField();
		addPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addEmail = new Text("E-mail: ");
		addEmailText = new TextField();
		addEmail.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		try {
			addBusinessNameText.setPromptText(FlexiBookController.ViewBusinessInfo().get(0));
		}
		catch(InvalidInputException e) {
			addBusinessNameText.setPromptText("no business name entered");
		}
		try {
			addAddressText.setPromptText(FlexiBookController.ViewBusinessInfo().get(1));
		}
		catch(InvalidInputException e) {
			addAddressText.setPromptText("no address entered");
		}
		try {
			addPhoneNumberText.setPromptText(FlexiBookController.ViewBusinessInfo().get(2));
		}
		catch(InvalidInputException e) {
			addPhoneNumberText.setPromptText("no phone number entered");
		}
		try {
			addEmailText.setPromptText(FlexiBookController.ViewBusinessInfo().get(3));
		}
		catch(InvalidInputException e) {
			addEmailText.setPromptText("no e-mail entered");
		}

		addBusinessButton = new Button("Confirm");

		//------------------------------------------------------------------------------------------------	

		gridPaneownerViewBusinessInfo = new GridPane();
		gridPaneownerViewBusinessInfo.setMinSize(500,70);
		gridPaneownerViewBusinessInfo.setPadding(new Insets(100,100,100,100));	
		gridPaneownerViewBusinessInfo.setVgap(10);
		gridPaneownerViewBusinessInfo.setHgap(10);
		gridPaneownerViewBusinessInfo.setAlignment(Pos.CENTER);
		gridPaneownerViewBusinessInfo.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneEditBusinessInfo = new GridPane();
		gridPaneEditBusinessInfo.setMinSize(500,70);
		gridPaneEditBusinessInfo.setPadding(new Insets(100,100,100,100));	
		gridPaneEditBusinessInfo.setVgap(10);
		gridPaneEditBusinessInfo.setHgap(10);
		gridPaneEditBusinessInfo.setAlignment(Pos.CENTER);
		gridPaneEditBusinessInfo.setStyle("-fx-background-color: LIGHTBLUE;");


		sP = new SplitPane();
		sP.setMinSize(1100, 600);
		sP.setMaxSize(1100, 600);
		sP.setOrientation(Orientation.VERTICAL);
		sP.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneownerViewBusinessInfo.add(ownerViewBusinessInfo, 0, 0,2,1);
		gridPaneownerViewBusinessInfo.add(ownerViewBusinessName, 0, 2);
		gridPaneownerViewBusinessInfo.add(ownerViewBusinessNameResult, 1, 2); 
		gridPaneownerViewBusinessInfo.add(ownerViewAddress, 3, 2);
		gridPaneownerViewBusinessInfo.add(ownerViewAddressResult,4,2);
		gridPaneownerViewBusinessInfo.add(ownerViewPhoneNumber, 0, 3);
		gridPaneownerViewBusinessInfo.add(ownerViewPhoneNumberResult, 1, 3);
		gridPaneownerViewBusinessInfo.add(ownerViewEmail,3,3);
		gridPaneownerViewBusinessInfo.add(ownerViewEmailResult,4,3);   

		gridPaneEditBusinessInfo.add(editBusinessInfo, 0, 0,2,1);
		gridPaneEditBusinessInfo.add(editBusinnessInfoInstruction, 0, 1,5,1);
		gridPaneEditBusinessInfo.add(addBusinessName, 0, 2);
		gridPaneEditBusinessInfo.add(addBusinessNameText, 1, 2); 
		gridPaneEditBusinessInfo.add(addAddress, 3, 2);
		gridPaneEditBusinessInfo.add(addAddressText,4,2);
		gridPaneEditBusinessInfo.add(addPhoneNumber, 0, 3);
		gridPaneEditBusinessInfo.add(addPhoneNumberText, 1, 3);
		gridPaneEditBusinessInfo.add(addEmail,3,3);
		gridPaneEditBusinessInfo.add(addEmailText,4,3);   
		gridPaneEditBusinessInfo.add(addBusinessButton, 2, 7);


		verticalMenuownerViewBusinessInfo = new VBox();
		verticalMenuownerViewBusinessInfo.setPadding(new Insets(10));
		verticalMenuownerViewBusinessInfo.setSpacing(8);

		verticalMenuEditBusinessInfo = new VBox();
		verticalMenuEditBusinessInfo.setPadding(new Insets(10));
		verticalMenuEditBusinessInfo.setSpacing(8);


		verticalMenuownerViewBusinessInfo = new VBox();
		verticalMenuownerViewBusinessInfo.setPadding(new Insets(10));
		verticalMenuownerViewBusinessInfo.setSpacing(8);

		verticalMenuEditBusinessInfo = new VBox();
		verticalMenuEditBusinessInfo.setPadding(new Insets(10));
		verticalMenuEditBusinessInfo.setSpacing(8);

		verticalMenuBusinessInfo = new VBox();
		verticalMenuBusinessInfo.setPadding(new Insets(10));
		verticalMenuBusinessInfo.setSpacing(8);


		Text t4 = new Text("Business");
		t4.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuBusinessInfo.getChildren().add(t4);


		ownerViewBusinessInfoLink1 = new Hyperlink("View Business Information");
		editBusinessInfoLink1 = new Hyperlink("Edit Business Information");
		ownerBusinessInfoGoBackLink1 = new Hyperlink("Go Back");
		ownerBusinessInfoMainMenuLink1 = new Hyperlink("Main Menu");

		Hyperlink op1[] = new Hyperlink[] {
				ownerViewBusinessInfoLink1,
				editBusinessInfoLink1,
				ownerBusinessInfoGoBackLink1,
				ownerBusinessInfoMainMenuLink1};

		for (int i=0; i<4; i++) {
			VBox.setMargin(op1[i], new Insets(0, 0, 0, 8));
			verticalMenuBusinessInfo.getChildren().add(op1[i]);
		}


		ownerBusinessInfoPane = new BorderPane();
		ownerBusinessInfoPane.setLeft(verticalMenuBusinessInfo);
		ownerBusinessInfoPane.setCenter(gridPaneownerViewBusinessInfo);	

		ownerBusinessScene  = new Scene(ownerBusinessInfoPane);


		ownerViewBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("View Business Information");
			ownerBusinessInfoPane.setCenter(gridPaneownerViewBusinessInfo);
		});
		editBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("Edit Business Information");
			ownerBusinessInfoPane.setCenter(gridPaneEditBusinessInfo);
		});

		ownerBusinessInfoGoBackLink1.setOnAction(e->{
			primaryStage.setScene(businessMenuMainScene);
		});
		ownerBusinessInfoMainMenuLink1.setOnAction(e->{
			primaryStage.setScene(ownerMainScene);
		});


		addBusinessButton.setOnAction(e->{
			try {
				FlexiBookController.SetUpContactInfo(addBusinessName.getText(), addAddress.getText(), addPhoneNumber.getText(), addEmail.getText());
				errorBusinessInfoMessage.setText("");
			} catch (InvalidInputException e1) {
				errorBusinessInfoMessage.setText(e1.getMessage());
				Alert alert = new Alert(AlertType.WARNING, errorBusinessInfoMessage.getText());
				alert.showAndWait();
			}
			catch(RuntimeException a) {
				errorBusinessInfoMessage.setText("Invalid Inputs");
				Alert alert = new Alert(AlertType.WARNING, errorBusinessInfoMessage.getText());
				alert.showAndWait();
			}
		});

		// View Hours
		//------------------------------------------------------------------------------------------------	

		TableColumn<TOBusinessHour, TODayOfWeek> dayOfWeekCol = new TableColumn<TOBusinessHour, TODayOfWeek>("Day Of Week");
		dayOfWeekCol.setMinWidth(300);
		dayOfWeekCol.setCellValueFactory(new PropertyValueFactory<>("TODayOfWeek"));


		TableColumn<TOBusinessHour, Time> businessHourStartTimeCol = new TableColumn<TOBusinessHour, Time>("Start Time");
		businessHourStartTimeCol.setMinWidth(300);
		businessHourStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));

		TableColumn<TOBusinessHour, Time> businessHourEndTimeCol = new TableColumn<TOBusinessHour, Time>("End Time");
		businessHourEndTimeCol.setMinWidth(300);
		businessHourEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

		viewBusinessHourTable = new TableView<TOBusinessHour>();
		viewBusinessHourTable.setItems(getBusinessHourData());
		viewBusinessHourTable.getColumns().addAll(dayOfWeekCol, businessHourStartTimeCol, businessHourEndTimeCol);

		// Add Business Hours
		//--------------------------------------------------------------------------------------------

		addHours = new Text("Add New Business Hours");
		addHours.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		addHours.setFill(Color.BLUE);
		addHoursInstruction = new Text("Please enter the following for new business hours.");
		addHoursInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorAddHoursMessage = new Text("");
		errorAddHoursMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorAddHoursMessage.setFill(Color.RED);


		addHoursDay = new Text("Day of Week: ");		
		addHoursDayText = new ComboBox<String>();
		addHoursDayText.getItems().add("Monday");
		addHoursDayText.getItems().add("Tuesday");
		addHoursDayText.getItems().add("Wednesday");
		addHoursDayText.getItems().add("Thursday");
		addHoursDayText.getItems().add("Friday");
		addHoursDayText.getItems().add("Saturday");
		addHoursDayText.getItems().add("Sunday");	
		addHoursDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));  

		addHoursStartTime = new Text("Start Time: ");
		addHoursStartTimeText = new TextField();
		addHoursStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addHoursStartTimeText.setPromptText("ex: 00:00");


		addHoursEndTime = new Text("End Time: ");
		addHoursEndTimeText = new TextField();
		addHoursEndTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addHoursEndTimeText.setPromptText("ex: 00:00");



		addHoursButton = new Button("Add");

		// Update Business Hours
		//--------------------------------------------------------------------------------------------

		updateHoursLabel = new Text("Edit Existing Business Hours");
		updateHoursLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		updateHoursLabel.setFill(Color.BLUE);

		updateHoursInstruction = new Text("Please enter the following information for the business hours you wish to update.");
		updateHoursInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateHoursOldDay = new Text("Current Day of Week: ");
		updateHoursOldDayText = new ComboBox<String>();
		updateHoursOldDayText.getItems().add("Monday");
		updateHoursOldDayText.getItems().add("Tuesday");
		updateHoursOldDayText.getItems().add("Wednesday");
		updateHoursOldDayText.getItems().add("Thursday");
		updateHoursOldDayText.getItems().add("Friday");
		updateHoursOldDayText.getItems().add("Saturday");
		updateHoursOldDayText.getItems().add("Sunday");
		updateHoursOldDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateHoursOldTime = new Text("Current Start Time: ");
		updateHoursOldTimeText = new TextField();
		updateHoursOldTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateHoursOldTimeText.setPromptText("ex: 00:00");

		updateHoursNewDay = new Text("New Day of Week: ");
		updateHoursNewDayText = new ComboBox<String>();
		updateHoursNewDayText.getItems().add("Monday");
		updateHoursNewDayText.getItems().add("Tuesday");
		updateHoursNewDayText.getItems().add("Wednesday");
		updateHoursNewDayText.getItems().add("Thursday");
		updateHoursNewDayText.getItems().add("Friday");
		updateHoursNewDayText.getItems().add("Saturday");
		updateHoursNewDayText.getItems().add("Sunday");
		updateHoursNewDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateHoursNewStartTime = new Text("New Start Time: ");
		updateHoursNewStartTimeText = new TextField();
		updateHoursNewStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateHoursNewStartTimeText.setPromptText("ex: 00:00");

		updateHoursNewEndTime = new Text("New End Time: ");
		updateHoursNewEndTimeText = new TextField();
		updateHoursNewEndTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateHoursNewEndTimeText.setPromptText("ex: 00:00");

		errorUpdateHoursMessage = new Text("");
		errorUpdateHoursMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorUpdateHoursMessage.setFill(Color.RED);


		updateHoursButton = new Button("Update");


		// Delete Business Hours
		//-----------------------------------------------------------------------------------------------------------

		deleteHoursLabel = new Text("Delete Existing Business Hours");
		deleteHoursLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		deleteHoursLabel.setFill(Color.BLUE);

		deleteHoursInstruction = new Text("Please enter the following for the business hours you would like to delete.");
		deleteHoursInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		deleteHoursDay = new Text("Day of Week: ");
		deleteHoursDayText = new ComboBox<String>();
		deleteHoursDayText.getItems().add("Monday");
		deleteHoursDayText.getItems().add("Tuesday");
		deleteHoursDayText.getItems().add("Wednesday");
		deleteHoursDayText.getItems().add("Thursday");
		deleteHoursDayText.getItems().add("Friday");
		deleteHoursDayText.getItems().add("Saturday");
		deleteHoursDayText.getItems().add("Sunday");
		deleteHoursDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		deleteHoursTime = new Text("Start Time: ");
		deleteHoursTimeText = new TextField();
		deleteHoursTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		deleteHoursTimeText.setPromptText("ex: 00:00");

		errorDeleteHoursMessage = new Text("");
		errorDeleteHoursMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorDeleteHoursMessage.setFill(Color.RED);

		deleteHoursButton = new Button("Delete");

		//-----------------------------------------------------------------------------------------------------------

		gridPaneaddHours = new GridPane();
		gridPaneaddHours.setMinSize(500,70);
		gridPaneaddHours.setPadding(new Insets(100,100,100,100));	
		gridPaneaddHours.setVgap(10);
		gridPaneaddHours.setHgap(10);
		gridPaneaddHours.setAlignment(Pos.CENTER);
		gridPaneaddHours.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneUpdateHours = new GridPane();
		gridPaneUpdateHours.setMinSize(800, 130);
		gridPaneUpdateHours.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneUpdateHours.setVgap(10);
		gridPaneUpdateHours.setHgap(10);
		gridPaneUpdateHours.setAlignment(Pos.CENTER);
		gridPaneUpdateHours.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneDeleteHours = new GridPane();
		gridPaneDeleteHours.setMinSize(500, 70);
		gridPaneDeleteHours.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneDeleteHours.setVgap(10);
		gridPaneDeleteHours.setHgap(10);
		gridPaneDeleteHours.setAlignment(Pos.CENTER);
		gridPaneDeleteHours.setStyle("-fx-background-color: LIGHTBLUE;");

		splitPane3 = new SplitPane();
		splitPane3.setMinSize(1100, 600);
		splitPane3.setMaxSize(1100, 600);
		splitPane3.setOrientation(Orientation.VERTICAL);
		splitPane3.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneaddHours.add(addHours, 0, 0,2,1);
		gridPaneaddHours.add(addHoursInstruction, 0, 1,5,1);
		gridPaneaddHours.add(addHoursDay, 0, 2);
		gridPaneaddHours.add(addHoursDayText, 1, 2); 
		gridPaneaddHours.add(addHoursStartTime, 3, 2);
		gridPaneaddHours.add(addHoursStartTimeText,4,2);
		gridPaneaddHours.add(addHoursEndTime, 3, 3);
		gridPaneaddHours.add(addHoursEndTimeText, 4, 3); 
		gridPaneaddHours.add(addHoursButton, 2, 7);

		gridPaneUpdateHours.add(updateHoursLabel, 0, 0,2,1);
		gridPaneUpdateHours.add(updateHoursInstruction, 0, 1,5,1);
		gridPaneUpdateHours.add(updateHoursOldDay, 0, 2);
		gridPaneUpdateHours.add(updateHoursOldDayText, 1, 2); 
		gridPaneUpdateHours.add(updateHoursNewDay, 3, 2);
		gridPaneUpdateHours.add(updateHoursNewDayText,4,2);
		gridPaneUpdateHours.add(updateHoursOldTime, 0, 3);
		gridPaneUpdateHours.add(updateHoursOldTimeText, 1, 3);
		gridPaneUpdateHours.add(updateHoursNewStartTime,3,3);
		gridPaneUpdateHours.add(updateHoursNewStartTimeText,4,3);  
		gridPaneUpdateHours.add(updateHoursNewEndTime,3,4);
		gridPaneUpdateHours.add(updateHoursNewEndTimeText,4,4);   
		gridPaneUpdateHours.add(updateHoursButton, 2, 7);

		gridPaneDeleteHours.add(deleteHoursLabel, 0, 0,2,1);
		gridPaneDeleteHours.add(deleteHoursInstruction, 0, 1,5,1);
		gridPaneDeleteHours.add(deleteHoursDay, 0, 2);
		gridPaneDeleteHours.add(deleteHoursDayText, 1, 2); 
		gridPaneDeleteHours.add(deleteHoursTime, 0, 3);
		gridPaneDeleteHours.add(deleteHoursTimeText,1,3);
		gridPaneDeleteHours.add(deleteHoursButton, 2,7);

		verticalMenuaddHours = new VBox();
		verticalMenuaddHours.setPadding(new Insets(10));
		verticalMenuaddHours.setSpacing(8);

		verticalMenuUpdateHours = new VBox();
		verticalMenuUpdateHours.setPadding(new Insets(10));
		verticalMenuUpdateHours.setSpacing(8);

		verticalMenuDeleteHours = new VBox();
		verticalMenuDeleteHours.setPadding(new Insets(10));
		verticalMenuDeleteHours.setSpacing(8);

		verticalMenuHours = new VBox();
		verticalMenuHours.setPadding(new Insets(10));
		verticalMenuHours.setSpacing(8);

		Text t = new Text("Business Hours");
		t.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuHours.getChildren().add(t);

		viewHoursLink1 = new Hyperlink("View Existing Business Hours");
		addHoursLink1 = new Hyperlink("Add New Business Hours");
		updateHoursLink1 = new Hyperlink("Update Existing Business Hours");
		deleteHoursLink1 = new Hyperlink ("Delete Existing Business Hours");
		businessHoursGoBackLink1 = new Hyperlink ("Go Back");
		businessHoursMainMenuLink1 = new Hyperlink ("Main Menu");


		Hyperlink o1[] = new Hyperlink[] {
				viewHoursLink1,
				addHoursLink1,
				updateHoursLink1,
				deleteHoursLink1,
				businessHoursGoBackLink1,
				businessHoursMainMenuLink1};

		for (int i=0; i<5; i++) {
			VBox.setMargin(o1[i], new Insets(0, 0, 0, 8));
			verticalMenuHours.getChildren().add(o1[i]);
		}

		businessHoursBorderPane = new BorderPane();
		businessHoursBorderPane.setMinSize(1100, 500);
		businessHoursBorderPane.setLeft(verticalMenuHours);
		refreshBusinessHours(viewBusinessHourTable);
		businessHoursBorderPane.setCenter(viewBusinessHourTable);

		businessHoursScene = new Scene(businessHoursBorderPane);


		viewHoursLink1.setOnAction(e->{
			primaryStage.setTitle("View Existing Business Hours");
			refreshBusinessHours(viewBusinessHourTable);
			businessHoursBorderPane.setCenter(viewBusinessHourTable);
		});
		addHoursLink1.setOnAction(e->{
			primaryStage.setTitle("Add New Business Hours");
			businessHoursBorderPane.setCenter(gridPaneaddHours);
		});
		updateHoursLink1.setOnAction(e->{
			primaryStage.setTitle("Update Existing Business Hours");
			businessHoursBorderPane.setCenter(gridPaneUpdateHours);
		});

		deleteHoursLink1.setOnAction(e->{
			primaryStage.setTitle("Delete Existing Business Hours");
			businessHoursBorderPane.setCenter(gridPaneDeleteHours);
		});  

		businessHoursGoBackLink1.setOnAction(e->{
			primaryStage.setScene(businessMenuMainScene);
		});
		businessHoursMainMenuLink1.setOnAction(e->{
			primaryStage.setScene(ownerMainScene);
		});



		addHoursButton.setOnAction(e->{
			try {
				FlexiBookController.SetUpBusinessHours(DayOfWeek.valueOf((String) addHoursDayText.getSelectionModel().getSelectedItem()), Time.valueOf(addHoursStartTimeText.getText()+":00"), Time.valueOf(addHoursEndTimeText.getText()+":00"));
				errorAddHoursMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, errorAddHoursMessage.getText());
				alert.showAndWait();
			} catch (InvalidInputException e1) {
				errorAddHoursMessage.setText(e1.getMessage());
				Alert alert = new Alert(AlertType.WARNING, errorAddHoursMessage.getText());
				alert.showAndWait();
			}
			catch(RuntimeException a) {
				errorAddHoursMessage.setText("Invalid Inputs");
				Alert alert = new Alert(AlertType.WARNING, errorAddHoursMessage.getText());
				alert.showAndWait();
			}
		});

		updateHoursButton.setOnAction(e->{
			try {
				FlexiBookController.UpdateBusinessHours(DayOfWeek.valueOf((String) updateHoursOldDayText.getSelectionModel().getSelectedItem()), Time.valueOf(updateHoursOldTimeText.getText()+":00"), DayOfWeek.valueOf((String) updateHoursNewDayText.getSelectionModel().getSelectedItem()), Time.valueOf(updateHoursNewStartTimeText.getText()+":00"), Time.valueOf(updateHoursNewEndTimeText.getText()+":00"));
				errorUpdateHoursMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, errorUpdateHoursMessage.getText());
				alert.showAndWait();
			} catch (InvalidInputException e1) {
				errorUpdateHoursMessage.setText(e1.getMessage());
				Alert alert = new Alert(AlertType.WARNING, errorUpdateHoursMessage.getText());
				alert.showAndWait();
			}
			catch(RuntimeException a) {
				errorUpdateHoursMessage.setText("Invalid Inputs");
				Alert alert = new Alert(AlertType.WARNING, errorUpdateHoursMessage.getText());
				alert.showAndWait();
			}
		});

		deleteHoursButton.setOnAction(e->{
			try {
				FlexiBookController.RemoveBusinessHours(DayOfWeek.valueOf((String) deleteHoursDayText.getSelectionModel().getSelectedItem()), Time.valueOf(deleteHoursTimeText.getText()+":00"));
				errorDeleteHoursMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, errorDeleteHoursMessage.getText());
				alert.showAndWait();
			} catch (InvalidInputException e1) {
				errorDeleteHoursMessage.setText(e1.getMessage());
				Alert alert = new Alert(AlertType.WARNING, errorDeleteHoursMessage.getText());
				alert.showAndWait();
			}
			catch(RuntimeException a) {
				errorDeleteHoursMessage.setText("Invalid Inputs");
				Alert alert = new Alert(AlertType.WARNING, errorDeleteHoursMessage.getText());
				alert.showAndWait();
			}
		});

		// Time Slot ------------------------------------------------------------------------
		
		// View Holidays	
		//--------------------------------------------------------------------------------------------
		
		TableColumn<TOTimeSlot, Date> startDateHolidayCol = new TableColumn<TOTimeSlot, Date>("Start Date");
		startDateHolidayCol.setMinWidth(250);
		startDateHolidayCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		
		
		TableColumn<TOTimeSlot, Time> startTimeHolidayCol = new TableColumn<TOTimeSlot, Time>("Start Time");
		startTimeHolidayCol.setMinWidth(250);
		startTimeHolidayCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		TableColumn<TOTimeSlot, Date> endDateHolidayCol = new TableColumn<TOTimeSlot, Date>("End Date");
		endDateHolidayCol.setMinWidth(250);
		endDateHolidayCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		
		TableColumn<TOTimeSlot, Time> endTimeHolidayCol = new TableColumn<TOTimeSlot, Time>("End Time");
		endTimeHolidayCol.setMinWidth(250);
		endTimeHolidayCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

		viewHolidayTable = new TableView<TOTimeSlot>();
		viewHolidayTable.setItems(getHolidayData());
		viewHolidayTable.getColumns().addAll(startDateHolidayCol, startTimeHolidayCol, endDateHolidayCol, endTimeHolidayCol);
		
		// View Vacations	
		//--------------------------------------------------------------------------------------------
		
		TableColumn<TOTimeSlot, Date> startDateVacationCol = new TableColumn<TOTimeSlot, Date>("Start Date");
		startDateVacationCol.setMinWidth(250);
		startDateVacationCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		
		
		TableColumn<TOTimeSlot, Time> startTimeVacationCol = new TableColumn<TOTimeSlot, Time>("Start Time");
		startTimeVacationCol.setMinWidth(250);
		startTimeVacationCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		TableColumn<TOTimeSlot, Date> endDateVacationCol = new TableColumn<TOTimeSlot, Date>("End Date");
		endDateVacationCol.setMinWidth(250);
		endDateVacationCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		
		TableColumn<TOTimeSlot, Time> endTimeVacationCol = new TableColumn<TOTimeSlot, Time>("End Time");
		endTimeVacationCol.setMinWidth(250);
		endTimeVacationCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

		viewVacationTable = new TableView<TOTimeSlot>();
		viewVacationTable.setItems(getVacationData());
		viewVacationTable.getColumns().addAll(startDateVacationCol, startTimeVacationCol, endDateVacationCol, endTimeVacationCol);

		
		
		// Add Time Slot
		//--------------------------------------------------------------------------------------------

		addTimeSlot = new Text("Add New Holiday or Vacation");
		addTimeSlot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		addTimeSlot.setFill(Color.BLUE);
		addTimeSlotInstruction = new Text("Please enter the following for new time slot.");
		addTimeSlotInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		erroraddTimeSlotMessage = new Text("");
		erroraddTimeSlotMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		erroraddTimeSlotMessage.setFill(Color.RED);


		addTimeSlotType = new Text("Type: ");
		addTimeSlotTypeText = new ComboBox<String>();
		addTimeSlotTypeText.getItems().add("Holiday");
		addTimeSlotTypeText.getItems().add("Vacation");
		addTimeSlotType.setFont(Font.font("Verdana", FontWeight.NORMAL,15));  

		addTimeSlotStartDate = new Text("Start Date: ");
		addTimeSlotStartDateText = new TextField();
		addTimeSlotStartDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addTimeSlotStartDateText.setPromptText("YYYY-MM-DD");

		addTimeSlotEndDate = new Text("End Date: ");
		addTimeSlotEndDateText = new TextField();
		addTimeSlotEndDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addTimeSlotEndDateText.setPromptText("YYYY-MM-DD");

		addTimeSlotStartTime = new Text("Start Time: ");
		addTimeSlotStartTimeText = new TextField();
		addTimeSlotStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addTimeSlotStartTimeText.setPromptText("ex: 00:00");

		addTimeSlotEndTime = new Text("End Time: ");
		addTimeSlotEndTimeText = new TextField();
		addTimeSlotEndTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addTimeSlotEndTimeText.setPromptText("ex: 00:00");

		addTimeSlotButton = new Button("Add");

		// Update Time Slot
		//--------------------------------------------------------------------------------------------

		updateTimeSlot = new Text("Edit Existing Holiday or Vacation");
		updateTimeSlot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		updateTimeSlot.setFill(Color.BLUE);

		updateTimeSlotInstruction = new Text("Please enter the following information for the time slot you wish to update.");
		updateTimeSlotInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateTimeSlotType = new Text("Type: ");
		updateTimeSlotTypeText = new ComboBox<String>();
		updateTimeSlotTypeText.getItems().add("Holiday");
		updateTimeSlotTypeText.getItems().add("Vacation");
		updateTimeSlotType.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateTimeSlotOldDate = new Text("Current Start Date: ");
		updateTimeSlotOldDateText = new TextField();
		updateTimeSlotOldDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotOldDateText.setPromptText("YYYY-MM-DD");

		updateTimeSlotOldTime = new Text("Current Start Time: ");
		updateTimeSlotOldTimeText = new TextField();
		updateTimeSlotOldTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotOldTimeText.setPromptText("ex: 00:00");

		updateTimeSlotNewStartDate = new Text("New Start Date: ");
		updateTimeSlotNewStartDateText = new TextField();
		updateTimeSlotNewStartDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotNewStartDateText.setPromptText("YYYY-MM-DD");

		updateTimeSlotNewEndDate = new Text("New End Date: ");
		updateTimeSlotNewEndDateText = new TextField();
		updateTimeSlotNewEndDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotNewEndDateText.setPromptText("YYYY-MM-DD");

		updateTimeSlotNewStartTime = new Text("New Start Time: ");
		updateTimeSlotNewStartTimeText = new TextField();
		updateTimeSlotNewStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotNewStartTimeText.setPromptText("ex: 00:00");

		updateTimeSlotNewEndTime = new Text("New End Time: ");
		updateTimeSlotNewEndTimeText = new TextField();
		updateTimeSlotNewEndTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotNewEndTimeText.setPromptText("ex: 00:00");

		errorupdateTimeSlotMessage = new Text("");
		errorupdateTimeSlotMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorupdateTimeSlotMessage.setFill(Color.RED);


		updateTimeSlotButton = new Button("Update");


		// Delete Time Slot
		//-----------------------------------------------------------------------------------------------------------

		deleteTimeSlot = new Text("Delete Existing Holiday or Vacation");
		deleteTimeSlot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		deleteTimeSlot.setFill(Color.BLUE);

		deleteTimeSlotInstruction = new Text("Please enter the following for the time slot you would like to delete.");
		deleteTimeSlotInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		deleteTimeSlotType = new Text("Type: ");
		deleteTimeSlotTypeText = new ComboBox<String>();
		deleteTimeSlotTypeText.getItems().add("Holiday");
		deleteTimeSlotTypeText.getItems().add("Vacation");
		deleteTimeSlotType.setFont(Font.font("Verdana", FontWeight.NORMAL,15));  

		deleteTimeSlotStartDate = new Text("Start Date: ");
		deleteTimeSlotStartDateText = new TextField();
		deleteTimeSlotStartDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		deleteTimeSlotStartDateText.setPromptText("YYYY-MM-DD");

		deleteTimeSlotEndDate = new Text("End Date: ");
		deleteTimeSlotEndDateText = new TextField();
		deleteTimeSlotEndDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		deleteTimeSlotEndDateText.setPromptText("YYYY-MM-DD");

		deleteTimeSlotStartTime = new Text("Start Time: ");
		deleteTimeSlotStartTimeText = new TextField();
		deleteTimeSlotStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		deleteTimeSlotStartTimeText.setPromptText("ex: 00:00");

		deleteTimeSlotEndTime = new Text("End Time: ");
		deleteTimeSlotEndTimeText = new TextField();
		deleteTimeSlotEndTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		deleteTimeSlotEndTimeText.setPromptText("ex: 00:00");


		errordeleteTimeSlotMessage = new Text("");
		errordeleteTimeSlotMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errordeleteTimeSlotMessage.setFill(Color.RED);

		deleteTimeSlotButton = new Button("Delete");

		//-----------------------------------------------------------------------------------------------------------

		gridPaneaddTimeSlot = new GridPane();
		gridPaneaddTimeSlot.setMinSize(500,70);
		gridPaneaddTimeSlot.setPadding(new Insets(100,100,100,100));	
		gridPaneaddTimeSlot.setVgap(10);
		gridPaneaddTimeSlot.setHgap(10);
		gridPaneaddTimeSlot.setAlignment(Pos.CENTER);
		gridPaneaddTimeSlot.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneupdateTimeSlot = new GridPane();
		gridPaneupdateTimeSlot.setMinSize(800, 130);
		gridPaneupdateTimeSlot.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneupdateTimeSlot.setVgap(10);
		gridPaneupdateTimeSlot.setHgap(10);
		gridPaneupdateTimeSlot.setAlignment(Pos.CENTER);
		gridPaneupdateTimeSlot.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPanedeleteTimeSlot = new GridPane();
		gridPanedeleteTimeSlot.setMinSize(500, 70);
		gridPanedeleteTimeSlot.setPadding(new Insets(100, 100, 100, 100));	
		gridPanedeleteTimeSlot.setVgap(10);
		gridPanedeleteTimeSlot.setHgap(10);
		gridPanedeleteTimeSlot.setAlignment(Pos.CENTER);
		gridPanedeleteTimeSlot.setStyle("-fx-background-color: LIGHTBLUE;");

		splitPane5 = new SplitPane();
		splitPane5.setMinSize(1100, 600);
		splitPane5.setMaxSize(1100, 600);
		splitPane5.setOrientation(Orientation.VERTICAL);
		splitPane5.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneaddTimeSlot.add(addTimeSlot, 0, 0,2,1);
		gridPaneaddTimeSlot.add(addTimeSlotInstruction, 0, 1,5,1);
		gridPaneaddTimeSlot.add(addTimeSlotType, 0, 2);
		gridPaneaddTimeSlot.add(addTimeSlotTypeText, 1, 2); 
		gridPaneaddTimeSlot.add(addTimeSlotStartDate, 0, 3);
		gridPaneaddTimeSlot.add(addTimeSlotStartDateText,1,3);
		gridPaneaddTimeSlot.add(addTimeSlotEndDate, 0, 4);
		gridPaneaddTimeSlot.add(addTimeSlotEndDateText, 1, 4);
		gridPaneaddTimeSlot.add(addTimeSlotStartTime,3,3);
		gridPaneaddTimeSlot.add(addTimeSlotStartTimeText,4,3);  
		gridPaneaddTimeSlot.add(addTimeSlotEndTime,3,4);
		gridPaneaddTimeSlot.add(addTimeSlotEndTimeText,4,4);   
		gridPaneaddTimeSlot.add(addTimeSlotButton, 2, 7);

		gridPaneupdateTimeSlot.add(updateTimeSlot, 0, 0,2,1);
		gridPaneupdateTimeSlot.add(updateTimeSlotInstruction, 0, 1,5,1);
		gridPaneupdateTimeSlot.add(updateTimeSlotType, 0, 2);
		gridPaneupdateTimeSlot.add(updateTimeSlotTypeText, 1, 2); 
		gridPaneupdateTimeSlot.add(updateTimeSlotOldDate, 0, 3);
		gridPaneupdateTimeSlot.add(updateTimeSlotOldDateText, 1, 3); 
		gridPaneupdateTimeSlot.add(updateTimeSlotNewStartDate, 3, 2);
		gridPaneupdateTimeSlot.add(updateTimeSlotNewStartDateText,4,2);
		gridPaneupdateTimeSlot.add(updateTimeSlotOldTime, 0, 4);
		gridPaneupdateTimeSlot.add(updateTimeSlotOldTimeText, 1, 4);
		gridPaneupdateTimeSlot.add(updateTimeSlotNewStartTime,3,3);
		gridPaneupdateTimeSlot.add(updateTimeSlotNewStartTimeText,4,3);  
		gridPaneupdateTimeSlot.add(updateTimeSlotNewEndDate,3,4);
		gridPaneupdateTimeSlot.add(updateTimeSlotNewEndDateText,4,4);   
		gridPaneupdateTimeSlot.add(updateTimeSlotNewEndTime,3,5);
		gridPaneupdateTimeSlot.add(updateTimeSlotNewEndTimeText,4,5);   
		gridPaneupdateTimeSlot.add(updateTimeSlotButton, 2, 7);


		gridPanedeleteTimeSlot.add(deleteTimeSlot, 0, 0,2,1);
		gridPanedeleteTimeSlot.add(deleteTimeSlotInstruction, 0, 1,5,1);
		gridPanedeleteTimeSlot.add(deleteTimeSlotType, 0, 2);
		gridPanedeleteTimeSlot.add(deleteTimeSlotTypeText, 1, 2); 
		gridPanedeleteTimeSlot.add(deleteTimeSlotStartDate, 0, 3);
		gridPanedeleteTimeSlot.add(deleteTimeSlotStartDateText,1,3);
		gridPanedeleteTimeSlot.add(deleteTimeSlotEndDate, 0, 4);
		gridPanedeleteTimeSlot.add(deleteTimeSlotEndDateText, 1, 4);
		gridPanedeleteTimeSlot.add(deleteTimeSlotStartTime,3,3);
		gridPanedeleteTimeSlot.add(deleteTimeSlotStartTimeText,4,3);  
		gridPanedeleteTimeSlot.add(deleteTimeSlotEndTime,3,4);
		gridPanedeleteTimeSlot.add(deleteTimeSlotEndTimeText,4,4);   
		gridPanedeleteTimeSlot.add(deleteTimeSlotButton, 2, 7);


		verticalMenuaViewHoliday = new VBox();
		verticalMenuaViewHoliday.setPadding(new Insets(10));
		verticalMenuaViewHoliday.setSpacing(8);
		
		verticalMenuaViewVacation = new VBox();
		verticalMenuaViewVacation.setPadding(new Insets(10));
		verticalMenuaViewVacation.setSpacing(8);
		
		verticalMenuaddTimeSlot = new VBox();
		verticalMenuaddTimeSlot.setPadding(new Insets(10));
		verticalMenuaddTimeSlot.setSpacing(8);

		verticalMenuupdateTimeSlot = new VBox();
		verticalMenuupdateTimeSlot.setPadding(new Insets(10));
		verticalMenuupdateTimeSlot.setSpacing(8);

		verticalMenuDeleteTimeSlot = new VBox();
		verticalMenuDeleteTimeSlot.setPadding(new Insets(10));
		verticalMenuDeleteTimeSlot.setSpacing(8);

		verticalMenuTimeSlot = new VBox();
		verticalMenuTimeSlot.setPadding(new Insets(10));
		verticalMenuTimeSlot.setSpacing(8);

		Text titlee1 = new Text("Holidays and Vacations");
		titlee1.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuTimeSlot.getChildren().add(titlee1);

		viewHolidayLink1 = new Hyperlink("View Existing Holidays");
		viewVacationLink1 = new Hyperlink("View Existing Vacations");
		addTimeSlotLink1 = new Hyperlink("Add New Holiday or Vacation");
		updateTimeSlotLink1 = new Hyperlink("Update Existing Holiday or Vacation");
		deleteTimeSlotLink1 = new Hyperlink ("Delete Existing Holiday or Vacation");
		timeSlotGoBackLink1 = new Hyperlink ("Go Back");
		timeSlotMainMenuLink1 = new Hyperlink ("Main Menu");


		Hyperlink opt1[] = new Hyperlink[] {
				viewHolidayLink1,
				viewVacationLink1,
				addTimeSlotLink1,
				updateTimeSlotLink1,
				deleteTimeSlotLink1,
				timeSlotGoBackLink1,
				timeSlotMainMenuLink1};

		for (int i=0; i<5; i++) {
			VBox.setMargin(opt1[i], new Insets(0, 0, 0, 8));
			verticalMenuTimeSlot.getChildren().add(opt1[i]);
		}


		TimeSlotBorderPane = new BorderPane();
		TimeSlotBorderPane.setLeft(verticalMenuTimeSlot);
		refreshHoliday(viewHolidayTable);
		TimeSlotBorderPane.setCenter(viewHolidayTable);

		timeSlotScene = new Scene(TimeSlotBorderPane);

		viewHolidayLink1.setOnAction(e->{
			refreshHoliday(viewHolidayTable);
			TimeSlotBorderPane.setCenter(viewHolidayTable);
			primaryStage.setTitle("View Existing Holidays");
		});
		viewVacationLink1.setOnAction(e->{
			refreshVacation(viewVacationTable);
			TimeSlotBorderPane.setCenter(viewVacationTable);
			primaryStage.setTitle("View Existing Vacations");
		});
		addTimeSlotLink1.setOnAction(e->{
			TimeSlotBorderPane.setCenter(gridPaneaddTimeSlot);
			primaryStage.setTitle("Add New Holiday or Vacation");
		});
		updateTimeSlotLink1.setOnAction(e->{
			TimeSlotBorderPane.setCenter(gridPaneupdateTimeSlot);
			primaryStage.setTitle("Update Existing Holiday or Vacation");
		});

		deleteTimeSlotLink1.setOnAction(e->{
			TimeSlotBorderPane.setCenter(gridPanedeleteTimeSlot);
			primaryStage.setTitle("Delete Existing Holiday or Vacation");
		});  
		timeSlotGoBackLink1.setOnAction(e->{
			primaryStage.setScene(businessMenuMainScene);
			primaryStage.setTitle("Business Menu");
		});
		timeSlotMainMenuLink1.setOnAction(e->{
			primaryStage.setScene(ownerMainScene);
			primaryStage.setTitle("Main Menu");

		});


		addTimeSlotButton.setOnAction(e->{
			LocalDate date2 = LocalDate.now();
			LocalTime time2 = LocalTime.now();
			Date currentDate2 = Date.valueOf(date);
			Time currentTime2 = Time.valueOf(time);
			SystemTime.setSysDate(currentDate);
			SystemTime.setSysTime(currentTime);
			try {
				FlexiBookController.AddaNewTimeSlot(((String) addTimeSlotTypeText.getSelectionModel().getSelectedItem()), Date.valueOf(addTimeSlotStartDateText.getText()), Time.valueOf(addTimeSlotStartTimeText.getText()+":00"), Date.valueOf(addTimeSlotEndDateText.getText()), Time.valueOf(addTimeSlotEndTimeText.getText()+":00"));
				erroraddTimeSlotMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, erroraddTimeSlotMessage.getText());
				alert.showAndWait();
			} catch (InvalidInputException e1) {
				erroraddTimeSlotMessage.setText(e1.getMessage());
				Alert alert = new Alert(AlertType.WARNING, erroraddTimeSlotMessage.getText());
				alert.showAndWait();
			}
			catch(RuntimeException a) {
				erroraddTimeSlotMessage.setText("Invalid Inputs");
				Alert alert = new Alert(AlertType.WARNING, erroraddTimeSlotMessage.getText());
				alert.showAndWait();
			}

		});

		updateTimeSlotButton.setOnAction(e->{
			LocalDate date2 = LocalDate.now();
			LocalTime time2 = LocalTime.now();
			Date currentDate2 = Date.valueOf(date);
			Time currentTime2 = Time.valueOf(time);
			SystemTime.setSysDate(currentDate);
			SystemTime.setSysTime(currentTime);
			try {
				FlexiBookController.UpdateHolidayOrVacation((String) updateTimeSlotTypeText.getSelectionModel().getSelectedItem(), Date.valueOf(updateTimeSlotOldDateText.getText()), Time.valueOf(updateTimeSlotOldTimeText.getText()+":00"), Date.valueOf(updateTimeSlotNewStartDateText.getText()), Time.valueOf(updateTimeSlotNewStartTimeText.getText()+":00"), Date.valueOf(updateTimeSlotNewEndDateText.getText()), Time.valueOf(updateTimeSlotNewEndTimeText.getText()+":00"));
				errorupdateTimeSlotMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, errorupdateTimeSlotMessage.getText());
				alert.showAndWait();
			} catch (InvalidInputException e1) {
				errorupdateTimeSlotMessage.setText(e1.getMessage());
				Alert alert = new Alert(AlertType.WARNING, errorupdateTimeSlotMessage.getText());
				alert.showAndWait();
			}
			catch(RuntimeException a) {
				errorupdateTimeSlotMessage.setText("Invalid Inputs");
				Alert alert = new Alert(AlertType.WARNING, errorupdateTimeSlotMessage.getText());
				alert.showAndWait();
			}
		});

		deleteTimeSlotButton.setOnAction(e->{
			LocalDate date2 = LocalDate.now();
			LocalTime time2 = LocalTime.now();
			Date currentDate2 = Date.valueOf(date);
			Time currentTime2 = Time.valueOf(time);
			SystemTime.setSysDate(currentDate);
			SystemTime.setSysTime(currentTime);
			try {
				FlexiBookController.RemoveTimeSlot((String) deleteTimeSlotTypeText.getSelectionModel().getSelectedItem(), Date.valueOf(deleteTimeSlotStartDateText.getText()), Time.valueOf(deleteTimeSlotStartTimeText.getText()+":00"), Date.valueOf(deleteTimeSlotEndDateText.getText()), Time.valueOf(deleteTimeSlotEndTimeText.getText()+":00"));
				errordeleteTimeSlotMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, errordeleteTimeSlotMessage.getText());
				alert.showAndWait();
			} catch (InvalidInputException e1) {
				errordeleteTimeSlotMessage.setText(e1.getMessage());
				Alert alert = new Alert(AlertType.WARNING, errordeleteTimeSlotMessage.getText());
				alert.showAndWait();
			}
			catch(RuntimeException a) {
				errordeleteTimeSlotMessage.setText("Invalid Inputs");
				Alert alert = new Alert(AlertType.WARNING, errordeleteTimeSlotMessage.getText());
				alert.showAndWait();
			}
		});

		// customerView Business Info
		//------------------------------------------------------------------------------------------------	

		customerViewBusinessInfo = new Text("View Business Information");
		customerViewBusinessInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		customerViewBusinessInfo.setFill(Color.BLUE);


		customerViewBusinessName = new Text("Business Name: ");
		try {
			customerViewBusinessNameResult = new Text(FlexiBookController.ViewBusinessInfo().get(0));
		}
		catch(InvalidInputException e) {
			customerViewBusinessNameResult = new Text("no business name entered");
		}

		customerViewBusinessName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	
		customerViewBusinessNameResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));  

		customerViewAddress = new Text("Address: ");
		try {
			customerViewAddressResult = new Text(FlexiBookController.ViewBusinessInfo().get(1));
		}
		catch(InvalidInputException e) {
			customerViewAddressResult = new Text("no address entered");
		}
		customerViewAddress.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewAddressResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));



		customerViewPhoneNumber = new Text("Phone Number: ");
		try {
			customerViewPhoneNumberResult = new Text(FlexiBookController.ViewBusinessInfo().get(2));
		}
		catch(InvalidInputException e) {
			customerViewPhoneNumberResult = new Text("no address entered");
		}
		customerViewPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewPhoneNumberResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewPhoneNumberResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		customerViewEmail = new Text("E-mail: ");
		try {
			customerViewEmailResult = new Text(FlexiBookController.ViewBusinessInfo().get(3));
		}
		catch(InvalidInputException e) {
			customerViewEmailResult = new Text("no address entered");
		}
		customerViewEmail.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewEmailResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		//------------------------------------------------------------------------------------------------	

		gridPanecustomerViewBusinessInfo = new GridPane();
		gridPanecustomerViewBusinessInfo.setMinSize(500,70);
		gridPanecustomerViewBusinessInfo.setPadding(new Insets(100,100,100,100));	
		gridPanecustomerViewBusinessInfo.setVgap(10);
		gridPanecustomerViewBusinessInfo.setHgap(10);
		gridPanecustomerViewBusinessInfo.setAlignment(Pos.CENTER);
		gridPanecustomerViewBusinessInfo.setStyle("-fx-background-color: LIGHTBLUE;");


		splitPane7 = new SplitPane();
		splitPane7.setMinSize(1100, 600);
		splitPane7.setMaxSize(1100, 600);
		splitPane7.setOrientation(Orientation.VERTICAL);
		splitPane7.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPanecustomerViewBusinessInfo.add(customerViewBusinessInfo, 0, 0,2,1);
		gridPanecustomerViewBusinessInfo.add(customerViewBusinessName, 0, 2);
		gridPanecustomerViewBusinessInfo.add(customerViewBusinessNameResult, 1, 2); 
		gridPanecustomerViewBusinessInfo.add(customerViewAddress, 3, 2);
		gridPanecustomerViewBusinessInfo.add(customerViewAddressResult,4,2);
		gridPanecustomerViewBusinessInfo.add(customerViewPhoneNumber, 0, 3);
		gridPanecustomerViewBusinessInfo.add(customerViewPhoneNumberResult, 1, 3);
		gridPanecustomerViewBusinessInfo.add(customerViewEmail,3,3);
		gridPanecustomerViewBusinessInfo.add(customerViewEmailResult,4,3);   


		verticalMenucustomerViewBusinessInfo = new VBox();
		verticalMenucustomerViewBusinessInfo.setPadding(new Insets(10));
		verticalMenucustomerViewBusinessInfo.setSpacing(8);



		Text t6 = new Text("Business");
		t6.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenucustomerViewBusinessInfo.getChildren().add(t6);


		customerViewBusinessInfoLink1 = new Hyperlink("View Business Information");
		customerBusinessInfoMainMenuLink1 = new Hyperlink("Main Menu");


		Hyperlink optio1[] = new Hyperlink[] {
				customerViewBusinessInfoLink1,
				customerBusinessInfoMainMenuLink1};

		for (int i=0; i<2; i++) {
			VBox.setMargin(optio1[i], new Insets(0, 0, 0, 8));
			verticalMenucustomerViewBusinessInfo.getChildren().add(optio1[i]);
		}


		customerViewBusinessInfoPane = new BorderPane();
		customerViewBusinessInfoPane.setLeft(verticalMenucustomerViewBusinessInfo);
		customerViewBusinessInfoPane.setCenter(gridPanecustomerViewBusinessInfo);


		customerViewBusinessScene  = new Scene(customerViewBusinessInfoPane);


		customerViewBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("customerView Business Information");
			primaryStage.setScene(customerViewBusinessScene);
		});
		customerBusinessInfoMainMenuLink1.setOnAction(e->{
			primaryStage.setScene(customerMainScene);
		});


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
	
	private ObservableList<TOBusinessHour> getBusinessHourData() {
		ObservableList<TOBusinessHour> businessHourList = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getBusinessHours().size(); i++) {
			businessHourList.add(FlexiBookController.getBusinessHours().get(i));
		}
		return businessHourList;
	}
	private void refreshBusinessHours(TableView table) {
		table.setItems(getBusinessHourData());
	}
	private void refreshVacation(TableView table) {
		table.setItems(getVacationData());
	}
	private void refreshHoliday(TableView table) {
		table.setItems(getHolidayData());
	}
	private ObservableList<TOTimeSlot> getHolidayData() {
		ObservableList<TOTimeSlot> holidayList = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getHolidays().size(); i++) {
			holidayList.add(FlexiBookController.getHolidays().get(i));
		}
		return holidayList;
	}
	private ObservableList<TOTimeSlot> getVacationData() {
		ObservableList<TOTimeSlot> vacationList = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getVacation().size(); i++) {
			vacationList.add(FlexiBookController.getVacation().get(i));
		}
		return vacationList;
	}
}

