package ca.mcgill.ecse.flexibook.view;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour;
import ca.mcgill.ecse223.flexibook.controller.TOTimeSlot;
import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour.TODayOfWeek;
import ca.mcgill.ecse223.flexibook.controller.TOService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.control.ToggleGroup;
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

	//----------------------------------------------------------------------------------------------
	//---------------------------------Appointment Page Fields--------------------------------------
	//----------------------------------------------------------------------------------------------
	
	//Make Appointment
	
	private Text errorMakeAppointment;
	private Text errorUpdateAppointment;
	private Text errorCancelAppointment;
	private Text errorMakeComboAppointment;
	private Text errorUpdateComboAppointment;
	private Text error;
	private Text makeAppInstruction;
	private Text makeAppServiceLabel;
	private TextField makeAppServiceText ;
	private Text makeAppDateLabel;
	private DatePicker makeAppDatePicker;
	private String makeAppDateString;
	private Text makeAppStartTimeLabel;
	private TextField makeAppStartTimeText;
	private Button makeAppButton;
	

	private Scene makeAppScene;
	private BorderPane makeAppBorderPane;
	
	
	//Make an appointment combo
	private Text makeAppComboInstruction;
	private Text makeAppComboServiceLabel;
	private TextField makeAppComboServiceText ;
	private Text makeAppComboInstruction1;
	private TextField makeAppComboOptServicesText;
	
	//Date label
	//	private Text makeAppDateLabel;
	//	//Date picker
	//	private DatePicker makeAppDatePicker;
	//	private String makeAppDateString;
	//
	//	//Start time label
	//	private Text makeAppStartTimeLabel;
	//	//Start time text field
	//	private TextField makeAppStartTimeText;
	//	//Button to add the appointment
	//	private Button makeAppButton;
	//	
	//	private Scene makeAppScene;
	//	private BorderPane makeAppBorderPane;

	
	
	//Update Apointment
	
	private Text updateAppFirstInstruction;
	private Text updateAppServiceLabel;
	private TextField updateAppServiceText;
	private Text updateAppDateLabel;
	private DatePicker updateAppDatePicker;
	private String updateAppDateString;
	private Text updateAppStartTimeLabel;
	private TextField updateAppStartTimeText;
	private Text updateAppSecondInstruction;
	ToggleGroup toggleGroupUpdateApp;
	private ToggleButton updateAppYes;
	private ToggleButton updateAppNo;
	private boolean updateAppServiceYesOrNo;
	private Text updateAppThirdInstruction;
	private Text updateAppNewServiceLabel;
	private TextField updateAppNewServiceText;
	private Text updateAppNewDateLabel;
	private DatePicker updateAppNewDatePicker;
	private String updateAppNewDateString;
	private Text updateAppNewStartTimeLabel;
	private TextField updateAppNewStartTimeText;
	private Button updateAppButton;
	private Scene updateAppScene;
	private BorderPane updateAppBorderPane;


	//Cancel Appointment

	private Text cancelAppFirstInstruction;
	private Text cancelAppServiceLabel;
	private TextField cancelAppServiceText;
	private Text cancelAppDateLabel;
	private DatePicker cancelAppDatePicker;
	private String cancelAppDateString;
	private Text cancelAppStartTimeLabel;
	private TextField cancelAppStartTimeText;
	private Button cancelAppButton;
	private Scene cancelAppScene;
	private BorderPane cancelAppBorderPane;
	private GridPane gridPaneMakeApp;
	private GridPane gridPaneUpdateApp;
	private GridPane gridPaneCancelApp;
	private VBox verticalMenuMakeApp;
	private VBox verticalMenuUpdateApp;
	private VBox verticalMenuCancelApp;
	private Hyperlink backMakeAppLink;
	private Hyperlink backAppLink;
	private Hyperlink backToMenuAppLink;
	private Hyperlink backUpdateAppLink;
	private Hyperlink backAppLink1;
	private Hyperlink backToMenuAppLink1;
	private Hyperlink backAppLink2;
	private Hyperlink backToMenuAppLink2;
	private HBox horizontalMakeApp;
	private HBox horizontalUpdateApp;
	private HBox horizontalCancelApp;
	private BorderPane appBorderPane;
	private Scene appScene;
	
	
	
	//--------------------------------------------------------------------------------------------------
	//--------------------------------- Appointment Menu Fields ------------------------------------------------
	//--------------------------------------------------------------------------------------------------		

	private Text appMainPageLabel;
	private Text appMainPageSlogan;

	private DropShadow dSAppMainPage;

	private FontIcon makeAppMainPageIcon;
	private FontIcon updateAppMainPageIcon;
	private FontIcon cancelAppMainPageIcon;
	private FontIcon backToMenuAppMainPageIcon;

	private JFXButton makeAppMainPageButton;
	private JFXButton updateAppMainPageButton;
	private JFXButton cancelAppMainPageButton;
	private JFXButton backToMenuAppMainPageButton;

	private HBox appMainPageLabelHBox;
	private HBox appMainPageIconsHBox;
	private HBox appMainPageSloganHBox;

	private BorderPane appMainPageBorderPane;

	private Scene appMainPageScene;
	
	//Make App page-----------------------------------------------------------------------------------------------
	private Text makeAppMainPageLabel;
	private Text makeAppMainPageSlogan;
	
	private DropShadow dSmakeAppMainPage;
	
	private FontIcon makeAppServiceIcon;
	private FontIcon makeAppComboIcon;
	private FontIcon makeAppBackIcon;
	
	private JFXButton makeAppServiceButton;
	private JFXButton makeAppComboButton;
	private JFXButton makeAppBackButton;
	
	private HBox makeAppMainPageLabelHBox;
	private HBox makeAppMainPageIconsHBox;
	private HBox makeAppMainPageSloganHBox;
	
	private BorderPane makeAppMainPageBorderPane;
	
	private Scene makeAppMainPageScene;
	
	//Update App page---------------------------------------------------------------------------------------------
	private Text updateAppMainPageLabel;
	private Text updateAppMainPageSlogan;

	private DropShadow dSupdateAppMainPage;
	
	private FontIcon updateAppServiceIcon;
	private FontIcon updateAppComboIcon;
	private FontIcon updateAppBackIcon;
	
	private JFXButton updateAppServiceButton;
	private JFXButton updateAppComboButton;
	private JFXButton updateAppBackButton;
	
	private HBox updateAppMainPageLabelHBox;
	private HBox updateAppMainPageIconsHBox;
	private HBox updateAppMainPageSloganHBox;
	
	private BorderPane updateAppMainPageBorderPane;
	
	private Scene updateAppMainPageScene;
	
	//--------------------------------------------------------------------------------------------------
	//--------------------------------- Login Page Fields ------------------------------------------------
	//--------------------------------------------------------------------------------------------------

	private Text flexibook;
	private Text slogan;
	private Text usernameText;      
	private Text passwordText; 
	private Text usernameText2;      
	private Text passwordText2; 
	private Text confirmPasswordText; 
	private Text errorLoginText; 
	private Text errorSignUpText; 
	private TextField usernameTextField;       
	private PasswordField passwordTextField;  
	private TextField usernameTextField2;      
	private PasswordField passwordTextField2; 
	private PasswordField confirmPasswordTextField;  
	private Button loginButton; 
	private Button signupButton;
	private BorderPane root;
	private GridPane gridPaneLogin; 
	private Scene loginScene; 


	//----------------------------------------------------------------------------------------------
	//--------------------------------- Owner Menu Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
	
	private Text welcome;
	private Text flexiBookText;
	private DropShadow dS;
	private FontIcon ownerProfileIcon;
	private FontIcon businessIcon;
	private FontIcon serviceIcon;
	private FontIcon serviceComboIcon;
	private FontIcon appointmentIcon;
	private FontIcon ownerLogoutIcon;
	private JFXButton ownerProfileButton;
	private JFXButton businessButton;
	private JFXButton serviceButton;
	private JFXButton serviceComboButton;
	private JFXButton appointmentButton;
	private JFXButton ownerLogoutButton;
	private HBox ownerLabelHBox;
	private HBox ownerIconsHBox;
	private HBox ownerSloganHBox;
	private BorderPane ownerBorderPane;
	private Scene ownerMainScene;


	//----------------------------------------------------------------------------------------------
	//--------------------------------- Customer Menu Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
		
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

	//----------------------------------------------------------------------------------------------
	//--------------------------------- Start/End Appointment Page Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
	
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
	private TextField customerUsernameTextField;
	private TextField appNameTextField;
	private TextField appDateTextField;
	private TextField appStartTimeTextField;
	private Hyperlink startEndLink;
	private Hyperlink link;
	private Hyperlink viewApps;
	private VBox verticalMenuSRE;
	private Text title;
	private Text title2;
	
	//Appointments Table
	private TableView<TOAppointment> appTable;
	private TableColumn<TOAppointment, String> customerNameCol;
	private TableColumn<TOAppointment, String> serviceNameCol;
	private TableColumn<TOAppointment, Time> startTimeCol;
	private TableColumn<TOAppointment, Time> endTimeCol;
	private TableColumn<TOAppointment, Date> dateCol;
	

	//----------------------------------------------------------------------------------------------
	//--------------------------------- Service Page Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
	
	
	//Add Service
	
	private Text errorAddServiceMessage;
	private Text errorUpdateServiceMessage;
	private Text errordeleteServiceMessage;
	private Text addService;
	private Text addServiceInstruction;
	private Text addServiceName;
	private TextField addServiceNameText;
	private Text addServiceDuration;
	private TextField addServiceDurationText;
	private Text addServiceDowntimeDuration;
	private TextField addServiceDowntimeDurationText;
	private Text addServiceDowntimeStartTime;
	private TextField addServiceDowntimeStartTimeText;
	private Button addServiceButton;



	//Update Service
	
	private Text updateServiceLabel;
	private Text updateServiceOldInstruction;
	private Text updateServiceLabelName;
	private TextField updateServiceText;
	private Text updateServiceNewName;
	private TextField updateServiceNewNameText;
	private Text updateServiceNewDuration;
	private TextField updateServiceNewDurationText;
	private Text updateServiceNewDowntimeDuration;
	private TextField updateServiceNewDowntimeDurationText;
	private Text updateServiceNewDowntimeStartTime;
	private TextField updateServiceNewDowntimeStartTimeText;
	private Button updateServiceButton;
	private Text updateServiceNameInstruction;
	private Text updateServiceDurationInstruction;
	private Text updateServiceDowntimeDurationInstruction;
	private Text updateServiceDowntimeStartTimeInstruction;
	private ToggleButton updateNameYes;
	private ToggleButton updateNameNo;
	private ToggleButton updateDurationYes;
	private ToggleButton updateDurationNo;
	private ToggleButton updateDowntimeDurationYes;
	private ToggleButton updateDowntimeDurationNo;
	private ToggleButton updateDowntimeStartYes;
	private ToggleButton updateDowntimeStartNo;

	//delete Service
	private Text deleteServiceLabel;
	private Text deleteServiceFirstInstruction;
	private Text deleteServiceNameLabel;
	private TextField deleteServiceNameText;
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
	private Hyperlink viewServiceList;
	private GridPane gridPaneViewServiceList;
	private Button updateConfirmServiceButton;
	private Button updateChangeServiceButton;

	//----------------------------------------------------------------------------------------------
	//--------------------------------- Service Combo Page Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
	
	private Text errorAddServiceComboMessage;
	private Text errorUpdateServiceComboMessage;
	private Text errorDeleteServiceComboMessage;

	//Add Service Combo
	private Text addServiceCombo;
	private Text addServiceComboInstruction;
	private Text addServiceComboName;
	private TextField addServiceComboNameText;
	private Text addServiceComboMainService;
	private Text addServiceComboMainServiceText;
	private Text addServiceComboDuration;
	private TextField addServiceComboDurationText;
	private Text addServiceComboDowntimeDuration;
	private TextField addServiceComboDowntimeDurationText;
	private Text addServiceComboDowntimeStartTime;
	private TextField addServiceComboDowntimeStartTimeText;
	private Button addServiceComboButton;
	private Button btn;
	private TextField addServiceTest;
	private TextField serviceComboTextField;
	private TextField serviceComboNameTextField;
	private String serviceComboDurationTextField;
	private String serviceComboDowntimeDurationTextField;
	private String serviceComboDowntimeStartTextField;


	//Update Service Combo
	private Text updateServiceComboLabel;
	private Text updateServiceComboOldInstruction;
	private Text updateServiceComboLabelName;
	private TextField updateServiceComboText;
	private Text updateServiceComboNewInstruction;
	private ToggleButton updateServiceComboYes;
	private ToggleButton updateServiceComboNo;
	private Text updateServiceComboInstruction;
	private Text updateServiceComboNewName;
	private TextField updateServiceComboNewNameText;
	private Text updateServiceComboNewDuration;
	private TextField updateServiceComboNewDurationText;
	private Text updateServiceComboNewDowntimeDuration;
	private TextField updateServiceComboNewDowntimeDurationText;
	private Text updateServiceComboNewDowntimeStartTime;
	private TextField updateServiceComboNewDowntimeStartTimeText;
	private Button updateServiceComboButton;
	private Text serviceComboList;
	private TextField serviceComboListText;
	private TextField serviceComboListText1;
	private TextField serviceComboListText2;
	private Text serviceComboIsMainInstructions;
	private ToggleButton serviceComboIsMainYes;
	private ToggleButton serviceComboIsMainNo;
	private Text serviceComboIsMandatoryInstructions;
	private ToggleButton serviceComboIsMandatoryYes;
	private ToggleButton serviceComboIsMandatoryNo;

	
	//delete Service
	private Text deleteServiceComboLabel;
	private Text deleteServiceComboFirstInstruction;
	private Text deleteServiceComboNameLabel;
	private TextField deleteServiceComboNameText;
	private Button deleteServiceComboButton;
	private GridPane gridPaneAddServiceCombo;
	private GridPane gridPaneUpdateServiceCombo;
	private GridPane gridPanedeleteServiceCombo;
	private Hyperlink addServiceComboLink;
	private Hyperlink updateServiceComboLink;
	private Hyperlink deleteServiceComboLink;
	private Hyperlink mainMenuComboLink;
	private BorderPane serviceComboBorderPane;
	private Scene serviceComboScene;
	private VBox verticalMenuCombo;



	//----------------------------------------------------------------------------------------------
	//--------------------------------- Holidays/Vacation Page Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
			
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
	private VBox verticalMenuTimeSlot;
	private BorderPane TimeSlotBorderPane;
	private Scene timeSlotScene;



	//----------------------------------------------------------------------------------------------
	//--------------------------------- Update Customer Account Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
		
	private Label updateNewUsername;
	private Label updateNewPassword;
	private Label updateConfirmPassword;
	private Text header;
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


	//----------------------------------------------------------------------------------------------
	//--------------------------------- Update Owner Account Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------
	
	private Label newOwnerPassword;
	private Label confirmOwnerPassword;
	private Text ownerHeader;
	private Text instructionOwner11;
	private Text instructionOwner12;
	private PasswordField newOwnerPasswordText;
	private PasswordField confirmOwnerPasswordText;
	private Button updateAccButton;
	private Hyperlink ownerMainMenu;
	private GridPane updateOwnerAccGrid;
	private BorderPane updateOwnerAccRoot;
	private Scene updateOwnerAccscene;



	//----------------------------------------------------------------------------------------------
	//--------------------------------- Owner Business Menu Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
	
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

	//----------------------------------------------------------------------------------------------
	//--------------------------------- View/Edit Business Info Page Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
	
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

	//----------------------------------------------------------------------------------------------
	//--------------------------------- Business Hours Page Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------
	
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

	//----------------------------------------------------------------------------------------------
	//--------------------------------- Customer View Business Info Page Fields ------------------------------------------
	//----------------------------------------------------------------------------------------------	
	
	private Text customerViewBusinessInfo;
	private Text customerViewBusinessName;
	private Text customerViewBusinessNameResult;
	private Text customerViewPhoneNumber;
	private Text customerViewPhoneNumberResult;
	private Text customerViewAddress;
	private Text customerViewAddressResult;
	private Text customerViewEmail;
	private Text customerViewEmailResult;
	private Hyperlink customerViewBusinessInfoBusinessHoursLink1;
	private Hyperlink customerViewBusinessInfoVacationsLink1;
	private Hyperlink customerViewBusinessInfoHolidaysLink1;
	private GridPane gridPanecustomerViewBusinessInfo;
	private SplitPane splitPane7;
	private VBox verticalMenucustomerViewBusinessInfo;
	private Hyperlink customerViewBusinessInfoLink1;
	private Hyperlink customerBusinessInfoMainMenuLink1;
	private BorderPane customerViewBusinessInfoPane;
	private Scene customerViewBusinessScene;




	public FlexiBookPage(Stage stage) {	
		initView(stage);
	}


	public void initView(Stage primaryStage) {



		//----------------------------------------------------------------------------------------------
		//--------------------------------- Login Page ------------------------------------------
		//----------------------------------------------------------------------------------------------	

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
				Alert a = new Alert(AlertType.ERROR, e1.getMessage());
				a.showAndWait();			
				}
		});



		signupButton.setOnAction(e->{
			try {
				if(passwordTextField2.getText().equals(confirmPasswordTextField.getText())) {
					FlexiBookController.signUpCustomerAccount(usernameTextField2.getText() , passwordTextField2.getText());
					Alert a = new Alert(AlertType.CONFIRMATION, "Account successfully created!");
					a.showAndWait();

				}
				else { 
					Alert a = new Alert(AlertType.ERROR, "Your password and confirmation password do not match.");
					a.showAndWait();

				}
			} catch (InvalidInputException e1) {
				Alert a = new Alert(AlertType.ERROR, e1.getMessage());
				a.showAndWait();
			}
		});

		flexibook.setStyle("-fx-font: normal bold 40px 'serif' ");
		slogan.setStyle("-fx-font: normal bold 40px 'serif' ");
		usernameText.setStyle("-fx-font: normal bold 20px 'serif' "); 
		passwordText.setStyle("-fx-font: normal bold 20px 'serif' ");  
		usernameText2.setStyle("-fx-font: normal bold 20px 'serif' "); 
		passwordText2.setStyle("-fx-font: normal bold 20px 'serif' ");  
		confirmPasswordText.setStyle("-fx-font: normal bold 20px 'serif' ");

		root.setStyle("-fx-background-color: LIGHTBLUE;"); 

		//Creating a scene object 
		loginScene = new Scene(root);

		//ViewManager.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.setScene(loginScene);
		primaryStage.show();


		//----------------------------------------------------------------------------------------------
		//--------------------------------- Owner Main Menu ------------------------------------------
		//----------------------------------------------------------------------------------------------		
		
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
		serviceComboIcon = new FontIcon("fa-list-ul");
		ownerLogoutIcon = new FontIcon("fa-sign-out");

		ownerProfileIcon.getStyleClass().add("icon");
		businessIcon.getStyleClass().add("icon");
		appointmentIcon.getStyleClass().add("icon");
		serviceIcon.getStyleClass().add("icon");
		serviceComboIcon.getStyleClass().add("icon");
		ownerLogoutIcon.getStyleClass().add("icon");

		ownerProfileIcon.setFill(Color.BLUE);
		ownerProfileIcon.setIconSize(50);
		businessIcon.setFill(Color.BLUE);
		businessIcon.setIconSize(50);
		appointmentIcon.setFill(Color.BLUE);
		appointmentIcon.setIconSize(50);
		serviceIcon.setFill(Color.BLUE);
		serviceIcon.setIconSize(50);
		serviceComboIcon.setFill(Color.BLUE);
		serviceComboIcon.setIconSize(50);
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
			refreshUpdateService();
			primaryStage.setTitle("Services Page");
			primaryStage.setScene(serviceScene);
			primaryStage.show();

		});
		serviceButton.getStyleClass().add("main-menu-button");
		serviceButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));


		serviceComboButton = new JFXButton("Service combo", serviceComboIcon);
		serviceComboButton.setContentDisplay(ContentDisplay.TOP);
		serviceComboButton.setOnAction(e->{
			primaryStage.setTitle("Service combo Page");
			primaryStage.setScene(serviceComboScene);
			primaryStage.show();
		});
		serviceComboButton.getStyleClass().add("main-menu-button");
		serviceComboButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		appointmentButton = new JFXButton("Appointments", appointmentIcon);
		appointmentButton.setContentDisplay(ContentDisplay.TOP);
		appointmentButton.setOnAction(e->{
			primaryStage.setTitle("Start/End/Register No-Show");
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


		ownerIconsHBox.getChildren().addAll(ownerProfileButton, businessButton, serviceButton, serviceComboButton, appointmentButton, ownerLogoutButton);

		ownerBorderPane.setCenter(ownerIconsHBox);

		ownerMainScene = new Scene(ownerBorderPane);


		//----------------------------------------------------------------------------------------------
		//--------------------------------- Customer Main Menu ------------------------------------------
		//----------------------------------------------------------------------------------------------
		
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
			primaryStage.setTitle("Appointment Menu");
			primaryStage.setScene(appMainPageScene);
			primaryStage.show();
		});
		customerAppButton.getStyleClass().add("main-menu-button");
		customerAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		customerBusinessButton = new JFXButton("Business Information", customerBusinessIcon);
		customerBusinessButton.setContentDisplay(ContentDisplay.TOP);
		customerBusinessButton.setOnAction(e->{
			primaryStage.setTitle("Business Information");
			customerRefreshBusinessInfo();
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
		
		
		//--------------------------------------------------------------------------------------------------
		//--------------------------------- Appointment Menu Fields ------------------------------------------------
		//--------------------------------------------------------------------------------------------------

		appMainPageBorderPane = new BorderPane();
		appMainPageBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
		appMainPageBorderPane.setMinSize(1100, 600); 
		appMainPageBorderPane.setMaxSize(1100, 600); 
		
		appMainPageLabelHBox = new HBox();
		appMainPageLabel = new Text("Appointment menu");
		appMainPageLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
		appMainPageLabel.setFill(Color.BLUE);
		dSAppMainPage = new DropShadow();
		dSAppMainPage.setOffsetY(3.0f);
		dSAppMainPage.setColor(Color.color(0.4f, 0.4f, 0.4f));
		appMainPageLabel.setEffect(dSAppMainPage);
		appMainPageLabel.setCache(true);
		appMainPageLabelHBox.setAlignment(Pos.CENTER);
		appMainPageLabelHBox.getChildren().add(appMainPageLabel);
		appMainPageBorderPane.setTop(appMainPageLabelHBox);
		
		appMainPageIconsHBox = new HBox(50);
		appMainPageIconsHBox.setAlignment(Pos.CENTER);
		makeAppMainPageIcon = new FontIcon("fa-calendar-plus-o");
		updateAppMainPageIcon = new FontIcon("fa-calendar-minus-o");
		cancelAppMainPageIcon = new FontIcon("fa-calendar-times-o");
		backToMenuAppMainPageIcon = new FontIcon("fa-backward");
		
		makeAppMainPageIcon.getStyleClass().add("icon");
		updateAppMainPageIcon.getStyleClass().add("icon");
		cancelAppMainPageIcon.getStyleClass().add("icon");
		backToMenuAppMainPageIcon.getStyleClass().add("icon");
		
		makeAppMainPageIcon.setFill(Color.BLUE);
		makeAppMainPageIcon.setIconSize(50);
		updateAppMainPageIcon.setFill(Color.BLUE);
		updateAppMainPageIcon.setIconSize(50);
		cancelAppMainPageIcon.setFill(Color.BLUE);
		cancelAppMainPageIcon.setIconSize(50);
		backToMenuAppMainPageIcon.setFill(Color.BLUE);
		backToMenuAppMainPageIcon.setIconSize(50);
		
		makeAppMainPageButton = new JFXButton("Make an appointment", makeAppMainPageIcon);
		makeAppMainPageButton.setContentDisplay(ContentDisplay.TOP);
		makeAppMainPageButton.getStyleClass().add("main-menu-button");
		makeAppMainPageButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		makeAppMainPageButton.setOnAction(e->{
			primaryStage.setTitle("Make an appointment");
			primaryStage.setScene(makeAppMainPageScene);
			primaryStage.show();
		});
		
		updateAppMainPageButton = new JFXButton("Update an appointment", updateAppMainPageIcon);
		updateAppMainPageButton.setContentDisplay(ContentDisplay.TOP);
		updateAppMainPageButton.getStyleClass().add("main-menu-button");
		updateAppMainPageButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		updateAppMainPageButton.setOnAction(e->{
			primaryStage.setTitle("Update an appointment");
			primaryStage.setScene(updateAppMainPageScene);
			primaryStage.show();
		});
		
		cancelAppMainPageButton = new JFXButton("Cancel an appointment", cancelAppMainPageIcon);
		cancelAppMainPageButton.setContentDisplay(ContentDisplay.TOP);
		cancelAppMainPageButton.getStyleClass().add("main-menu-button");
		cancelAppMainPageButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		cancelAppMainPageButton.setOnAction(e->{
			primaryStage.setTitle("Cancel Appointment");
			primaryStage.setScene(cancelAppScene);
			primaryStage.show();
		});
		
		backToMenuAppMainPageButton = new JFXButton("Main Menu", backToMenuAppMainPageIcon);
		backToMenuAppMainPageButton.setContentDisplay(ContentDisplay.TOP);
		backToMenuAppMainPageButton.getStyleClass().add("main-menu-button");
		backToMenuAppMainPageButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		backToMenuAppMainPageButton.setOnAction(e->{
			primaryStage.setTitle("Main Menu");
			primaryStage.setScene(customerMainScene);
			primaryStage.show();
		});
		
		appMainPageSloganHBox = new HBox();
		appMainPageSlogan = new Text("Flexibook, it's time to get organised!");
		appMainPageSlogan.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		appMainPageSlogan.setFill(Color.BLUE);
		appMainPageSloganHBox.getChildren().add(appMainPageSlogan);
		appMainPageSloganHBox.setAlignment(Pos.CENTER);
		appMainPageBorderPane.setBottom(appMainPageSloganHBox);

		appMainPageIconsHBox.getChildren().addAll(makeAppMainPageButton, updateAppMainPageButton, cancelAppMainPageButton,backToMenuAppMainPageButton );
		appMainPageBorderPane.setCenter(appMainPageIconsHBox);
		appMainPageScene = new Scene(appMainPageBorderPane);
		
		//----------------------------------------------------------------------------------------------
		//---------------------------------Appointment Page --------------------------------------
		//----------------------------------------------------------------------------------------------
		
		makeAppMainPageBorderPane = new BorderPane();
		makeAppMainPageBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
		makeAppMainPageBorderPane.setMinSize(1100, 600); 
		makeAppMainPageBorderPane.setMaxSize(1100, 600); 
		
		makeAppMainPageLabelHBox = new HBox();
		makeAppMainPageLabel = new Text("Make an appointment");
		makeAppMainPageLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
		makeAppMainPageLabel.setFill(Color.BLUE);
		dSmakeAppMainPage = new DropShadow();
		dSmakeAppMainPage.setOffsetY(3.0f);
		dSmakeAppMainPage.setColor(Color.color(0.4f, 0.4f, 0.4f));
		makeAppMainPageLabel.setEffect(dSAppMainPage);
		makeAppMainPageLabel.setCache(true);
		makeAppMainPageLabelHBox.setAlignment(Pos.CENTER);
		makeAppMainPageLabelHBox.getChildren().add(makeAppMainPageLabel);
		makeAppMainPageBorderPane.setTop(makeAppMainPageLabelHBox);
		
		makeAppMainPageIconsHBox = new HBox(50);
		makeAppMainPageIconsHBox.setAlignment(Pos.CENTER);
		makeAppServiceIcon = new FontIcon("fa-square-o");
		makeAppComboIcon = new FontIcon("fa-square");
		makeAppBackIcon = new FontIcon("fa-backward");
		
		makeAppServiceIcon.getStyleClass().add("icon");
		makeAppComboIcon.getStyleClass().add("icon");
		makeAppBackIcon.getStyleClass().add("icon");
		
		makeAppServiceIcon.setFill(Color.BLUE);
		makeAppServiceIcon.setIconSize(50);
		makeAppComboIcon.setFill(Color.BLUE);
		makeAppComboIcon.setIconSize(50);
		makeAppBackIcon.setFill(Color.BLUE);
		makeAppBackIcon.setIconSize(50);
		
		makeAppServiceButton = new JFXButton("For a service", makeAppServiceIcon);
		makeAppServiceButton.setContentDisplay(ContentDisplay.TOP);
		makeAppServiceButton.getStyleClass().add("main-menu-button");
		makeAppServiceButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		makeAppServiceButton.setOnAction(e->{
			primaryStage.setTitle("Make an appointment");
			primaryStage.setScene(makeAppScene);
			primaryStage.show();
		});
		
		makeAppComboButton = new JFXButton("For a service combo", makeAppComboIcon);
		makeAppComboButton.setContentDisplay(ContentDisplay.TOP);
		makeAppComboButton.getStyleClass().add("main-menu-button");
		makeAppComboButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		makeAppComboButton.setOnAction(e->{
			primaryStage.setTitle("Make an appointment");
			primaryStage.setScene(makeAppScene);
			primaryStage.show();
		});
		
		makeAppBackButton = new JFXButton("Back", makeAppBackIcon);
		makeAppBackButton.setContentDisplay(ContentDisplay.TOP);
		makeAppBackButton.getStyleClass().add("main-menu-button");
		makeAppBackButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		makeAppBackButton.setOnAction(e->{
			primaryStage.setTitle("Appointment Menu");
			primaryStage.setScene(appMainPageScene);
			primaryStage.show();
		});
		
		makeAppMainPageSloganHBox = new HBox();
		makeAppMainPageSlogan = new Text("Flexibook, it's time to get organised!");
		makeAppMainPageSlogan.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		makeAppMainPageSlogan.setFill(Color.BLUE);
		makeAppMainPageSloganHBox.getChildren().add(makeAppMainPageSlogan);
		makeAppMainPageSloganHBox.setAlignment(Pos.CENTER);
		makeAppMainPageBorderPane.setBottom(makeAppMainPageSloganHBox);

		makeAppMainPageIconsHBox.getChildren().addAll(makeAppServiceButton, makeAppComboButton, makeAppBackButton );
	
		makeAppMainPageBorderPane.setCenter(makeAppMainPageIconsHBox);

		makeAppMainPageScene = new Scene(makeAppMainPageBorderPane);
		
		//Update Appointment
		
		updateAppMainPageBorderPane = new BorderPane();
		updateAppMainPageBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
		updateAppMainPageBorderPane.setMinSize(1100, 600); 
		updateAppMainPageBorderPane.setMaxSize(1100, 600); 
		
		updateAppMainPageLabelHBox = new HBox();
		updateAppMainPageLabel = new Text("Update your appointment");
		updateAppMainPageLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
		updateAppMainPageLabel.setFill(Color.BLUE);
		dSupdateAppMainPage = new DropShadow();
		dSupdateAppMainPage.setOffsetY(3.0f);
		dSupdateAppMainPage.setColor(Color.color(0.4f, 0.4f, 0.4f));
		updateAppMainPageLabel.setEffect(dSAppMainPage);
		updateAppMainPageLabel.setCache(true);
		updateAppMainPageLabelHBox.setAlignment(Pos.CENTER);
		updateAppMainPageLabelHBox.getChildren().add(updateAppMainPageLabel);
		updateAppMainPageBorderPane.setTop(updateAppMainPageLabelHBox);
		
		updateAppMainPageIconsHBox = new HBox(50);
		updateAppMainPageIconsHBox.setAlignment(Pos.CENTER);
		updateAppServiceIcon = new FontIcon("fa-square-o");
		updateAppComboIcon = new FontIcon("fa-square");
		updateAppBackIcon = new FontIcon("fa-backward");
		
		updateAppServiceIcon.getStyleClass().add("icon");
		updateAppComboIcon.getStyleClass().add("icon");
		updateAppBackIcon.getStyleClass().add("icon");
		
		updateAppServiceIcon.setFill(Color.BLUE);
		updateAppServiceIcon.setIconSize(50);
		updateAppComboIcon.setFill(Color.BLUE);
		updateAppComboIcon.setIconSize(50);
		updateAppBackIcon.setFill(Color.BLUE);
		updateAppBackIcon.setIconSize(50);
		
		updateAppServiceButton = new JFXButton("For a service", updateAppServiceIcon);
		updateAppServiceButton.setContentDisplay(ContentDisplay.TOP);
		updateAppServiceButton.getStyleClass().add("main-menu-button");
		updateAppServiceButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		updateAppServiceButton.setOnAction(e->{
			primaryStage.setTitle("Update an Appointment");
			primaryStage.setScene(updateAppScene);
			primaryStage.show();
		});
		
		
		updateAppComboButton = new JFXButton("For a service combo", makeAppComboIcon);
		updateAppComboButton.setContentDisplay(ContentDisplay.TOP);
		updateAppComboButton.getStyleClass().add("main-menu-button");
		updateAppComboButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		
		updateAppBackButton = new JFXButton("Back", makeAppBackIcon);
		updateAppBackButton.setContentDisplay(ContentDisplay.TOP);
		updateAppBackButton.getStyleClass().add("main-menu-button");
		updateAppBackButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		updateAppBackButton.setOnAction(e->{
			primaryStage.setTitle("Appointment Menu");
			primaryStage.setScene(appMainPageScene);
			primaryStage.show();
		});
		
		
		updateAppMainPageSloganHBox = new HBox();
		updateAppMainPageSlogan = new Text("Flexibook, it's time to get organised!");
		updateAppMainPageSlogan.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		updateAppMainPageSlogan.setFill(Color.BLUE);
		updateAppMainPageSloganHBox.getChildren().add(updateAppMainPageSlogan);
		updateAppMainPageSloganHBox.setAlignment(Pos.CENTER);
		updateAppMainPageBorderPane.setBottom(updateAppMainPageSloganHBox);


		updateAppMainPageIconsHBox.getChildren().addAll(updateAppServiceButton, updateAppComboButton, updateAppBackButton );

		updateAppMainPageBorderPane.setCenter(updateAppMainPageIconsHBox);
		
		updateAppMainPageScene = new Scene(updateAppMainPageBorderPane);
		
		

		//Appointment Pages------------------------------------------------------
		//Make App Service Page------------------------------------------------
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

		gridPaneMakeApp = new GridPane();
		gridPaneMakeApp.setMinSize(500, 50);
		gridPaneMakeApp.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneMakeApp.setVgap(10);
		gridPaneMakeApp.setHgap(10);
		gridPaneMakeApp.setAlignment(Pos.CENTER);
		gridPaneMakeApp.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneMakeApp.add(makeAppInstruction, 0, 1,5,1);
		gridPaneMakeApp.add(makeAppServiceLabel, 0, 2);
		gridPaneMakeApp.add(makeAppServiceText, 1, 2); 
		makeAppServiceText.setPromptText("Enter a service");
		gridPaneMakeApp.add(makeAppDateLabel, 3, 2);
		gridPaneMakeApp.add(makeAppDatePicker, 4, 2,2,1);
		makeAppDatePicker.setPromptText("yyyy-mm-dd");
		gridPaneMakeApp.add(makeAppStartTimeLabel, 5, 2);
		gridPaneMakeApp.add(makeAppStartTimeText, 6, 2);
		makeAppStartTimeText.setPromptText("ex: 12:00");
		gridPaneMakeApp.add(makeAppButton, 4, 3,2,2);

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

		verticalMenuMakeApp = new VBox();
		verticalMenuMakeApp.setPadding(new Insets(10));
		verticalMenuMakeApp.setSpacing(8);
		verticalMenuMakeApp.setStyle("-fx-background-color: #336699;");


		Text titleMakeApp = new Text("What do you wish to do?");
		titleMakeApp.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuMakeApp.getChildren().add(titleMakeApp);

		backMakeAppLink = new Hyperlink("Make Appointment Menu");
		backMakeAppLink.setStyle("-fx-text-fill: blue;");
		backMakeAppLink.setFont(Font.font("Verdana", 15));
		backAppLink = new Hyperlink("Appointment Menu");
		backAppLink.setStyle("-fx-text-fill: blue;");
		backAppLink.setFont(Font.font("Verdana", 15));
		backToMenuAppLink = new Hyperlink("Main Menu");
		backToMenuAppLink.setStyle("-fx-text-fill: white;");
		backToMenuAppLink.setFont(Font.font("Verdana", 15));

		Hyperlink optionsMakeApp[] = new Hyperlink[] {
				backMakeAppLink,
				backAppLink,
				backToMenuAppLink};

		for (int i=0; i<3; i++) {
			VBox.setMargin(optionsMakeApp[i], new Insets(0, 0, 0, 8));
			verticalMenuMakeApp.getChildren().add(optionsMakeApp[i]);
		}

		HBox makeAppSloganHBox =new HBox();
		makeAppSloganHBox.setAlignment(Pos.CENTER);
		Text makeAppFlexiBookTextApp = new Text("Flexibook, it's time to get organised!");
		makeAppFlexiBookTextApp.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		makeAppFlexiBookTextApp.setFill(Color.BLUE);
		makeAppSloganHBox.getChildren().add(makeAppFlexiBookTextApp);
		makeAppSloganHBox.setStyle("-fx-background-color: #336699;");

		makeAppBorderPane = new BorderPane();
		makeAppBorderPane.setLeft(verticalMenuMakeApp);
		makeAppBorderPane.setCenter(gridPaneMakeApp);
		makeAppBorderPane.setTop(horizontalMakeApp);
		makeAppBorderPane.setBottom(makeAppSloganHBox);

		makeAppScene = new Scene(makeAppBorderPane);

		backMakeAppLink.setOnAction(e->{
			resetMakeAppPage();
			primaryStage.setTitle("Make Appointment Menu");
			primaryStage.setScene(makeAppMainPageScene);
		});

		backAppLink.setOnAction(e->{
			resetMakeAppPage();
			primaryStage.setTitle("Appointment Menu");
			primaryStage.setScene(appMainPageScene);
		});

		backToMenuAppLink.setOnAction(e->{
			resetMakeAppPage();
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(customerMainScene);
		});

		makeAppButton.setOnAction(e->{
			FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
			Alert unsuccessfulMakeApp;
			try {

				if(makeAppServiceText.getText()== null || makeAppServiceText.getText().trim().isEmpty()) {
					errorMakeAppointment.setText("A service should be defined to proceed.");
					unsuccessfulMakeApp = new Alert(AlertType.ERROR, errorMakeAppointment.getText());
					unsuccessfulMakeApp.showAndWait();
				}
				else if(makeAppDatePicker.getValue()==null) {
					errorMakeAppointment.setText("A date should be chosen to proceed.");
					unsuccessfulMakeApp = new Alert(AlertType.ERROR, errorMakeAppointment.getText());
					unsuccessfulMakeApp.showAndWait();
				}
				else if(makeAppStartTimeText.getText() == null || makeAppStartTimeText.getText().trim().isEmpty()) {
					errorMakeAppointment.setText("A time should be chosen to proceed.");
					unsuccessfulMakeApp = new Alert(AlertType.ERROR, errorMakeAppointment.getText());
					unsuccessfulMakeApp.showAndWait();
				}
				else {
					makeAppDateString = makeAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					FlexiBookController.makeAppointment(usernameTextField.getText() , makeAppServiceText.getText(), null, makeAppDateString, makeAppStartTimeText.getText());
					Alert successfulMakeApp = new Alert(AlertType.CONFIRMATION, "Your booking was successful");
					successfulMakeApp.showAndWait();
					errorMakeAppointment.setText("");
					resetMakeAppPage();
				}
			} catch (InvalidInputException e1) {
				errorMakeAppointment.setText(e1.getMessage());
				unsuccessfulMakeApp = new Alert(AlertType.ERROR, errorMakeAppointment.getText());
				unsuccessfulMakeApp.showAndWait();
			}

		}
				);

		//Make App Combo page-----------------------------------------------------------

		//Update App Service page---------------------------------------------------------


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
		toggleGroupUpdateApp = new ToggleGroup();
		updateAppYes = new ToggleButton("Yes");
		updateAppYes.setToggleGroup(toggleGroupUpdateApp);
		updateAppNo = new ToggleButton("No");
		updateAppNo.setToggleGroup(toggleGroupUpdateApp);
		
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
		
		gridPaneUpdateApp = new GridPane();
		gridPaneUpdateApp.setMinSize(800, 250);
		gridPaneUpdateApp.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneUpdateApp.setVgap(10);
		gridPaneUpdateApp.setHgap(10);
		gridPaneUpdateApp.setAlignment(Pos.CENTER);
		gridPaneUpdateApp.setStyle("-fx-background-color: LIGHTBLUE;");

		
		gridPaneUpdateApp.add(updateAppFirstInstruction, 0, 1,6,1);
		gridPaneUpdateApp.add(updateAppServiceLabel, 0, 2);
		gridPaneUpdateApp.add(updateAppServiceText, 1, 2);
		updateAppServiceText.setPromptText("Enter a service");
		gridPaneUpdateApp.add(updateAppDateLabel, 3, 2);
		gridPaneUpdateApp.add(updateAppDatePicker, 4, 2,2,1);
		updateAppDatePicker.setPromptText("yyyy-mm-dd");
		gridPaneUpdateApp.add(updateAppStartTimeLabel, 7, 2);
		gridPaneUpdateApp.add(updateAppStartTimeText, 8, 2);
		updateAppStartTimeText.setPromptText("ex: 12:00");
		gridPaneUpdateApp.add(updateAppSecondInstruction, 0, 3,2,1);
		gridPaneUpdateApp.add(updateAppYes, 4, 3);
		gridPaneUpdateApp.add(updateAppNo, 5, 3);
		gridPaneUpdateApp.add(updateAppThirdInstruction, 0, 4,9,1);
		gridPaneUpdateApp.add(updateAppNewServiceLabel, 0, 5);
		gridPaneUpdateApp.add(updateAppNewServiceText, 1, 5);
		updateAppNewServiceText.setPromptText("Enter a service");
		gridPaneUpdateApp.add(updateAppNewDateLabel, 3, 5);
		gridPaneUpdateApp.add(updateAppNewDatePicker, 4, 5,2,1);
		gridPaneUpdateApp.add(updateAppNewStartTimeLabel, 7, 5);
		gridPaneUpdateApp.add(updateAppNewStartTimeText, 8, 5);
		updateAppNewStartTimeText.setPromptText("ex: 12:00");
		gridPaneUpdateApp.add(updateAppButton, 4, 6,2,1);

		
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

		verticalMenuUpdateApp = new VBox();
		verticalMenuUpdateApp.setPadding(new Insets(10));
		verticalMenuUpdateApp.setSpacing(8);
		verticalMenuUpdateApp.setStyle("-fx-background-color: #336699;");


		Text titleUpdateApp = new Text("What do you wish to do?");
		titleMakeApp.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuUpdateApp.getChildren().add(titleUpdateApp);

		backUpdateAppLink = new Hyperlink("Update Appointment Menu");
		backUpdateAppLink.setStyle("-fx-text-fill: blue;");
		backUpdateAppLink.setFont(Font.font("Verdana", 15));
		backAppLink1 = new Hyperlink("Appointment Menu");
		backAppLink1.setStyle("-fx-text-fill: blue;");
		backAppLink1.setFont(Font.font("Verdana", 15));
		backToMenuAppLink1 = new Hyperlink("Main Menu");
		backToMenuAppLink1.setStyle("-fx-text-fill: white;");
		backToMenuAppLink1.setFont(Font.font("Verdana", 15));
		
		Hyperlink optionsUpdateApp[] = new Hyperlink[] {
				backUpdateAppLink,
				backAppLink1,
				backToMenuAppLink1};

		for (int i=0; i<3; i++) {
			VBox.setMargin(optionsUpdateApp[i], new Insets(0, 0, 0, 8));
			verticalMenuUpdateApp.getChildren().add(optionsUpdateApp[i]);
		}
		
		HBox updateAppSloganHBox =new HBox();
		updateAppSloganHBox.setAlignment(Pos.CENTER);
		Text updateAppFlexiBookTextApp = new Text("Flexibook, it's time to get organised!");
		updateAppFlexiBookTextApp.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		updateAppFlexiBookTextApp.setFill(Color.BLUE);
		updateAppSloganHBox.getChildren().add(updateAppFlexiBookTextApp);
		updateAppSloganHBox.setStyle("-fx-background-color: #336699;");

		updateAppBorderPane = new BorderPane();
		updateAppBorderPane.setLeft(verticalMenuUpdateApp);
		updateAppBorderPane.setCenter(gridPaneUpdateApp);
		updateAppBorderPane.setTop(horizontalUpdateApp);
		updateAppBorderPane.setBottom(updateAppSloganHBox);

		updateAppScene = new Scene(updateAppBorderPane);
		
		backUpdateAppLink.setOnAction(e->{
			resetUpdateAppPage();
			primaryStage.setTitle("Update Appointment Menu");
			primaryStage.setScene(updateAppMainPageScene);
		});
		
		backAppLink1.setOnAction(e->{
			resetUpdateAppPage();
			primaryStage.setTitle("Appointment Menu");
			primaryStage.setScene(appMainPageScene);
		});

		backToMenuAppLink1.setOnAction(e->{
			resetUpdateAppPage();
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(customerMainScene);
		});


		
		updateAppYes.setOnAction(e->{
			updateAppServiceYesOrNo = true;
		});
		updateAppNo.setOnAction(e->{
			updateAppServiceYesOrNo = false;
		});


		updateAppButton.setOnAction(e->{
			FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
			Alert unsuccessfulUpdateApp;
			try {
				if(updateAppServiceText.getText()== null || updateAppServiceText.getText().trim().isEmpty()) {
					errorUpdateAppointment.setText("A service should be defined to proceed.");
					unsuccessfulUpdateApp = new Alert(AlertType.ERROR, errorUpdateAppointment.getText());
					unsuccessfulUpdateApp.showAndWait();
				}
				else if(updateAppDatePicker.getValue()==null) {
					errorUpdateAppointment.setText("A date should be chosen to proceed.");
					unsuccessfulUpdateApp = new Alert(AlertType.ERROR, errorUpdateAppointment.getText());
					unsuccessfulUpdateApp.showAndWait();
				}
				else if(updateAppStartTimeText.getText() == null || updateAppStartTimeText.getText().trim().isEmpty()) {
					errorUpdateAppointment.setText("A time should be chosen to proceed.");
					unsuccessfulUpdateApp = new Alert(AlertType.ERROR, errorUpdateAppointment.getText());
					unsuccessfulUpdateApp.showAndWait();
				}

				else if(updateAppNewDatePicker.getValue()==null) {
					errorUpdateAppointment.setText("A date should be chosen to proceed.");
					unsuccessfulUpdateApp = new Alert(AlertType.ERROR, errorUpdateAppointment.getText());
					unsuccessfulUpdateApp.showAndWait();
				}
				else if(updateAppNewStartTimeText.getText() == null || updateAppNewStartTimeText.getText().trim().isEmpty()) {
					errorUpdateAppointment.setText("A time should be chosen to proceed.");
					unsuccessfulUpdateApp = new Alert(AlertType.ERROR, errorUpdateAppointment.getText());
					unsuccessfulUpdateApp.showAndWait();
				}
				else if(updateAppServiceYesOrNo) {
					if(updateAppNewServiceText.getText()== null || updateAppNewServiceText.getText().trim().isEmpty()) {
						errorUpdateAppointment.setText("A service should be defined to proceed.");
						unsuccessfulUpdateApp = new Alert(AlertType.ERROR, errorUpdateAppointment.getText());
						unsuccessfulUpdateApp.showAndWait();
					}
				}
				else {
					updateAppDateString = updateAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					updateAppNewDateString = updateAppNewDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					FlexiBookController.updateAppointment(usernameTextField.getText() , usernameTextField.getText(), updateAppServiceText.getText(), updateAppDateString, updateAppStartTimeText.getText(),
							updateAppNewDateString, updateAppNewStartTimeText.getText(), null, null, updateAppServiceYesOrNo, updateAppNewServiceText.getText());
					Alert successfulUpdateApp = new Alert(AlertType.CONFIRMATION, "Your appointment was updated successfully");
					successfulUpdateApp.showAndWait();
					errorUpdateAppointment.setText("");
					resetUpdateAppPage();
				}

			} catch (InvalidInputException e1) {
				errorUpdateAppointment.setText(e1.getMessage());
				unsuccessfulUpdateApp = new Alert(AlertType.ERROR, errorUpdateAppointment.getText());
				unsuccessfulUpdateApp.showAndWait();
			} 
		});
		
		//Update App Combo page ----------------------------------------------------------------------
		
		//Cancel App Page------------------------------------------------------------------------------

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


		gridPaneCancelApp = new GridPane();
		gridPaneCancelApp.setMinSize(500, 50);
		gridPaneCancelApp.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneCancelApp.setVgap(10);
		gridPaneCancelApp.setHgap(10);
		gridPaneCancelApp.setAlignment(Pos.CENTER);
		gridPaneCancelApp.setStyle("-fx-background-color: LIGHTBLUE;");


		gridPaneCancelApp.add(cancelAppFirstInstruction, 0, 1,5,1);
		gridPaneCancelApp.add(cancelAppServiceLabel, 0, 2);
		gridPaneCancelApp.add(cancelAppServiceText, 1, 2);
		cancelAppServiceText.setPromptText("Enter a service");
		gridPaneCancelApp.add(cancelAppDateLabel, 2, 2);
		gridPaneCancelApp.add(cancelAppDatePicker, 3, 2);
		cancelAppDatePicker.setPromptText("yyyy-mm-dd");
		gridPaneCancelApp.add(cancelAppStartTimeLabel, 4, 2);
		gridPaneCancelApp.add(cancelAppStartTimeText, 5, 2);
		cancelAppStartTimeText.setPromptText("ex: 12:00");
		gridPaneCancelApp.add(cancelAppButton, 3, 3);


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

		verticalMenuCancelApp = new VBox();
		verticalMenuCancelApp.setPadding(new Insets(10));
		verticalMenuCancelApp.setSpacing(8);
		verticalMenuCancelApp.setStyle("-fx-background-color: #336699;");


		Text titleCancelApp = new Text("What do you wish to do?");
		titleCancelApp.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuCancelApp.getChildren().add(titleCancelApp);

		backAppLink2 = new Hyperlink("Appointment Menu");
		backAppLink2.setStyle("-fx-text-fill: blue;");
		backAppLink2.setFont(Font.font("Verdana", 15));
		backToMenuAppLink2 = new Hyperlink("Main Menu");
		backToMenuAppLink2.setStyle("-fx-text-fill: white;");
		backToMenuAppLink2.setFont(Font.font("Verdana", 15));


		Hyperlink optionsCancelApp[] = new Hyperlink[] {
				backAppLink2,
				backToMenuAppLink2};

		for (int i=0; i<2; i++) {
			VBox.setMargin(optionsCancelApp[i], new Insets(0, 0, 0, 8));
			verticalMenuCancelApp.getChildren().add(optionsCancelApp[i]);
		}


		HBox cancelAppSloganHBox =new HBox();
		cancelAppSloganHBox.setAlignment(Pos.CENTER);
		Text cancelFlexiBookTextApp = new Text("Flexibook, it's time to get organised!");
		cancelFlexiBookTextApp.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		cancelFlexiBookTextApp.setFill(Color.BLUE);
		cancelAppSloganHBox.getChildren().add(cancelFlexiBookTextApp);
		cancelAppSloganHBox.setStyle("-fx-background-color: #336699;");


		cancelAppBorderPane = new BorderPane();
		cancelAppBorderPane.setLeft(verticalMenuCancelApp);
		cancelAppBorderPane.setCenter(gridPaneCancelApp);
		cancelAppBorderPane.setTop(horizontalCancelApp);
		cancelAppBorderPane.setBottom(cancelAppSloganHBox);

		cancelAppScene = new Scene(cancelAppBorderPane);

		backAppLink2.setOnAction(e->{
			resetCancelAppPage();
			primaryStage.setTitle("Appointment Menu");
			primaryStage.setScene(appMainPageScene);
		});

		backToMenuAppLink2.setOnAction(e->{
			resetCancelAppPage();
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(customerMainScene);
		});

		cancelAppButton.setOnAction(e->{
			FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));

			Alert sureToCancel = new Alert(AlertType.WARNING, "Are you sure you want to cancel your appointment?", ButtonType.YES, ButtonType.NO);
			sureToCancel.showAndWait();
			
			Alert unsuccessfulCancel;

			if (sureToCancel.getResult() == ButtonType.YES) {

				try {
					if(cancelAppServiceText.getText()== null || cancelAppServiceText.getText().trim().isEmpty()) {
						errorCancelAppointment.setText("A service should be defined to proceed.");
						unsuccessfulCancel = new Alert(AlertType.ERROR, errorCancelAppointment.getText());
						unsuccessfulCancel.showAndWait();
					}
					else if(cancelAppDatePicker.getValue()==null) {
						errorCancelAppointment.setText("A date should be chosen to proceed.");
						unsuccessfulCancel = new Alert(AlertType.ERROR, errorCancelAppointment.getText());
						unsuccessfulCancel.showAndWait();
					}
					else if(cancelAppStartTimeText.getText() == null || cancelAppStartTimeText.getText().trim().isEmpty()) {
						errorCancelAppointment.setText("A time should be chosen to proceed.");
						unsuccessfulCancel = new Alert(AlertType.ERROR, errorCancelAppointment.getText());
						unsuccessfulCancel.showAndWait();
					}
					else {
						cancelAppDateString = cancelAppDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						FlexiBookController.cancelAppointment(usernameTextField.getText() , usernameTextField.getText(), cancelAppServiceText.getText(), cancelAppDateString, cancelAppStartTimeText.getText());								  
						Alert successfulCancel = new Alert(AlertType.CONFIRMATION, "Your appointment was cancelled successfully");
						successfulCancel.showAndWait();
						errorCancelAppointment.setText("");
						resetCancelAppPage();					}
				
				} catch (InvalidInputException e1) {
					errorCancelAppointment.setText(e1.getMessage());
					unsuccessfulCancel = new Alert(AlertType.ERROR, errorCancelAppointment.getText());
					unsuccessfulCancel.showAndWait();
				}
			}
		});


		//----------------------------------------------------------------------------------------------
		//--------------------------------- Start/End Appointment Page --------------------------------------
		//----------------------------------------------------------------------------------------------		
		
		startButton = new Button("Start Appointment");
		endButton = new Button("End Appointment");
		registerButton = new Button("Register no-show");
		root2 = new BorderPane(); 
		errorText = new Text(" ");
		title = new Text("Please enter the information of the appointment you want to edit.");

		customerName = new Text("Customer name");
		appointmentName = new Text("Appointment name");
		startDate = new Text("Start date");
		startTime = new Text("Start time");
		startEndLink = new Hyperlink("Start/End/Register No-Show");
		link = new Hyperlink("Main menu");
		viewApps = new Hyperlink("View Appointments");
		verticalMenuSRE = new VBox();
		verticalMenuSRE.setPadding(new Insets(10));
		verticalMenuSRE.setSpacing(8);

		customerUsernameTextField = new TextField();
		appNameTextField = new TextField();
		appDateTextField = new TextField();
		appStartTimeTextField = new TextField();

		gridPaneOwner = new GridPane();  

		title.setFont(Font.font("Verdana", FontWeight.BOLD,22));
		gridPaneOwner.add(title, 0, 1, 7, 1);
		gridPaneOwner.add(customerName, 0, 2);
		gridPaneOwner.add(appointmentName, 2, 2);
		gridPaneOwner.add(startDate, 4, 2);
		gridPaneOwner.add(startTime, 6, 2);
		gridPaneOwner.add(startButton,0,6);
		gridPaneOwner.add(registerButton,2,6);
		gridPaneOwner.add(endButton,4,6);
		gridPaneOwner.add(errorText, 4, 8);
		gridPaneOwner.add(customerUsernameTextField,0,4);
		gridPaneOwner.add(appNameTextField,2,4);
		gridPaneOwner.add(appDateTextField,4,4);
		gridPaneOwner.add(appStartTimeTextField,6,4);
		

		Hyperlink optionss[] = new Hyperlink[] {
				startEndLink,
				viewApps,
				link
		};
		title2 = new Text("What do you wish to do?");
		title2.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
		verticalMenuSRE.getChildren().add(title2);
		for (int i=0; i<3; i++) {
			VBox.setMargin(optionss[i], new Insets(0, 0, 0, 8));
			verticalMenuSRE.getChildren().add(optionss[i]);
		}

		startButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		registerButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		endButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		gridPaneOwner.setStyle("-fx-background-color: LIGHTBLUE;");
		root2.setMinSize(1100, 600); 
		root2.setMaxSize(1100, 600);
		gridPaneOwner.setVgap(10);
		gridPaneOwner.setHgap(20);
		gridPaneOwner.setPadding(new Insets(10, 10, 10, 10)); 
		root2.setCenter(gridPaneOwner);
		root2.setLeft(verticalMenuSRE);
		gridPaneOwner.setAlignment(Pos.CENTER);
		ownerAppScene = new Scene(root2);

	
		startDate.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
		startTime.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
		customerName.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
		appointmentName.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
		
		startButton.setOnAction(e->{
			Alert alert3 = new Alert(AlertType.WARNING, "Are you sure you want to start that appointment?", ButtonType.YES, ButtonType.NO);
  			alert3.showAndWait();
  			if (alert3.getResult() == ButtonType.YES) {	
			
			try {
				FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
				FlexiBookController.startAppointment(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
				Alert a = new Alert(AlertType.CONFIRMATION, "Appointment Started");
				a.showAndWait();
			}catch (InvalidInputException e1) {
				Alert a = new Alert(AlertType.ERROR, e1.getMessage());
				a.showAndWait();			
				}
  			}
		});
		endButton.setOnAction(e->{
			Alert alert3 = new Alert(AlertType.WARNING, "Are you sure you want to end that appointment?", ButtonType.YES, ButtonType.NO);
  			alert3.showAndWait();
  			if (alert3.getResult() == ButtonType.YES) {	
			try {
				FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));

				FlexiBookController.endAppointment(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
				Alert a = new Alert(AlertType.CONFIRMATION, "Appointment Ended");
				a.showAndWait();			
				}catch (InvalidInputException e1) {
					Alert a = new Alert(AlertType.ERROR, e1.getMessage());
					a.showAndWait();				}
  			}
		});
		registerButton.setOnAction(e->{
			Alert alert3 = new Alert(AlertType.WARNING, "Are you sure you want to register a no-show?", ButtonType.YES, ButtonType.NO);
  			alert3.showAndWait();
  			if (alert3.getResult() == ButtonType.YES) {	
			try {
				FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
				FlexiBookController.registerNoShow(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
				Alert a = new Alert(AlertType.CONFIRMATION, "No-show Registered");
				a.showAndWait();	
			}catch (InvalidInputException e1) {
				Alert a = new Alert(AlertType.ERROR, e1.getMessage());
				a.showAndWait();
			}
			}
  			
		});
		
		startEndLink.setOnAction(e->{
			primaryStage.setTitle("Start/End/Register No-Show");
			root2.setCenter(gridPaneOwner);
		});
		
		link.setOnAction(e->{
			primaryStage.setTitle("Main Menu");
			primaryStage.setScene(ownerMainScene);
			primaryStage.show();
		});
		
		viewApps.setOnAction(e->{
			primaryStage.setTitle("Appointments");
			refreshAppDate();
			root2.setCenter(appTable);
		});
		
		
		//View Appointments Table

		customerNameCol = new TableColumn<TOAppointment, String>("Customer name");
		customerNameCol.setMinWidth(150);
		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		

		serviceNameCol = new TableColumn<TOAppointment, String>("Service");
		serviceNameCol.setMinWidth(150);
		serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));

		startTimeCol = new TableColumn<TOAppointment, Time>("Start Time");
		startTimeCol.setMinWidth(150);
		startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));

		endTimeCol = new TableColumn<TOAppointment, Time>("End Time");
		endTimeCol.setMinWidth(150);
		endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

		dateCol = new TableColumn<TOAppointment, Date>("Date");
		dateCol.setMinWidth(150);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

		appTable = new TableView<TOAppointment>();
		appTable.setItems(getAppointmentsData());
		appTable.getColumns().addAll(customerNameCol, serviceNameCol, startTimeCol, endTimeCol, dateCol);

		
		
		
		
		

		//----------------------------------------------------------------------------------------------
		//---------------------------------Service Page --------------------------------------
		//----------------------------------------------------------------------------------------------
		
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
		updateServiceLabelName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceText = new TextField();
		updateConfirmServiceButton = new Button("Confirm Service");

		updateChangeServiceButton = new Button("Change Service");
		updateChangeServiceButton.setVisible(false);


		updateServiceNameInstruction = new Text("Do you want to change service name? ");
		updateServiceNameInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceNameInstruction.setVisible(false);
		updateNameYes = new ToggleButton("Yes");
		updateNameYes.setVisible(false);
		updateNameNo = new ToggleButton("No");
		updateNameNo.setVisible(false);
		ToggleGroup group1 = new ToggleGroup();
		updateNameYes.setToggleGroup(group1);
		updateNameNo.setToggleGroup(group1);


		updateServiceDurationInstruction = new Text("Do you want to change service duration? ");
		updateServiceDurationInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceDurationInstruction.setVisible(false);
		updateDurationYes = new ToggleButton("Yes");
		updateDurationYes.setVisible(false);
		updateDurationNo = new ToggleButton("No");
		updateDurationNo.setVisible(false);
		ToggleGroup group2 = new ToggleGroup();
		updateDurationYes.setToggleGroup(group2);
		updateDurationNo.setToggleGroup(group2);

		updateServiceDowntimeDurationInstruction = new Text("Do you want to change your downtime duration? ");
		updateServiceDowntimeDurationInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceDowntimeDurationInstruction.setVisible(false);
		updateDowntimeDurationYes = new ToggleButton("Yes");
		updateDowntimeDurationYes.setVisible(false);
		updateDowntimeDurationNo = new ToggleButton("No");
		updateDowntimeDurationNo.setVisible(false);
		ToggleGroup group3 = new ToggleGroup();
		updateDowntimeDurationYes.setToggleGroup(group3);
		updateDowntimeDurationNo.setToggleGroup(group3);


		updateServiceDowntimeStartTimeInstruction = new Text("Do you want to change your downtime start time?");
		updateServiceDowntimeStartTimeInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceDowntimeStartTimeInstruction.setVisible(false);
		updateDowntimeStartYes = new ToggleButton("Yes");
		updateDowntimeStartYes.setVisible(false);
		updateDowntimeStartNo = new ToggleButton("No");
		updateDowntimeStartNo.setVisible(false);
		ToggleGroup group4 = new ToggleGroup();
		updateDowntimeStartYes.setToggleGroup(group4);
		updateDowntimeStartNo.setToggleGroup(group4);




		errorUpdateServiceMessage = new Text("");
		errorUpdateServiceMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorUpdateServiceMessage.setFill(Color.RED);
		errorUpdateServiceMessage.setVisible(false);




		updateServiceNewName = new Text("New service name: ");
		updateServiceNewName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceNewName.setVisible(false);

		updateServiceNewNameText = new TextField();
		updateServiceNewNameText.setVisible(false);


		updateServiceNewDuration = new Text("      New duration: ");
		updateServiceNewDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceNewDuration.setVisible(false);

		updateServiceNewDurationText = new TextField();
		updateServiceNewDurationText.setVisible(false);


		updateServiceNewDowntimeDuration= new Text("New downtime duration: ");
		updateServiceNewDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceNewDowntimeDuration.setVisible(false);

		updateServiceNewDowntimeDurationText = new TextField();
		updateServiceNewDowntimeDurationText.setVisible(false);

		updateServiceNewDowntimeStartTime= new Text("New downtime start time: ");
		updateServiceNewDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceNewDowntimeStartTime.setVisible(false);

		updateServiceNewDowntimeStartTimeText = new TextField();
		updateServiceNewDowntimeStartTimeText.setVisible(false);

		updateServiceButton = new Button("Update service");
		updateServiceButton.setVisible(false);



		updateConfirmServiceButton.setOnAction(e->{
			if (findService(updateServiceText.getText()) != null) {
				updateChangeServiceButton.setVisible(true);
				updateServiceNameInstruction.setVisible(true);
				updateNameYes.setVisible(true);
				updateNameNo.setVisible(true);
				updateServiceDurationInstruction.setVisible(true);
				updateDurationYes.setVisible(true);
				updateDurationNo.setVisible(true);
				updateServiceDowntimeDurationInstruction.setVisible(true);
				updateDowntimeDurationYes.setVisible(true);
				updateDowntimeDurationNo.setVisible(true);
				updateServiceDowntimeStartTimeInstruction.setVisible(true);
				updateDowntimeStartYes.setVisible(true);
				updateDowntimeStartNo.setVisible(true);
				updateServiceLabelName.setVisible(true);
				updateServiceText.setVisible(true);
				errorUpdateServiceMessage.setVisible(true);
				updateServiceNewName.setVisible(true);
				updateServiceNewNameText.setVisible(true);
				updateServiceNewDuration.setVisible(true);
				updateServiceNewDurationText.setVisible(true);
				updateServiceNewDowntimeDuration.setVisible(true);
				updateServiceNewDowntimeDurationText.setVisible(true);
				updateServiceNewDowntimeStartTime.setVisible(true);
				updateServiceNewDowntimeStartTimeText.setVisible(true);
				updateServiceButton.setVisible(true);
				updateServiceText.setEditable(false);

			}
			else {
				Alert a = new Alert(AlertType.ERROR, "Service not found, please try again");
				a.showAndWait();
			}
		});


		updateChangeServiceButton.setOnAction(e->{
			refreshUpdateService();
		});

		updateNameYes.setOnAction(e->{
			presetUpdateFields(updateNameNo.isSelected(), updateDurationNo.isSelected(),
					updateDowntimeDurationNo.isSelected(),  updateDowntimeStartNo.isSelected());


		});

		updateNameNo.setOnAction(e->{
			presetUpdateFields(updateNameNo.isSelected(), updateDurationNo.isSelected(),
					updateDowntimeDurationNo.isSelected(),  updateDowntimeStartNo.isSelected());


		});


		updateDurationYes.setOnAction(e->{
			presetUpdateFields(updateNameNo.isSelected(), updateDurationNo.isSelected(),
					updateDowntimeDurationNo.isSelected(),  updateDowntimeStartNo.isSelected());


		});

		updateDurationNo.setOnAction(e->{
			presetUpdateFields(updateNameNo.isSelected(), updateDurationNo.isSelected(),
					updateDowntimeDurationNo.isSelected(),  updateDowntimeStartNo.isSelected());


		});

		updateDowntimeDurationYes.setOnAction(e->{
			presetUpdateFields(updateNameNo.isSelected(), updateDurationNo.isSelected(),
					updateDowntimeDurationNo.isSelected(),  updateDowntimeStartNo.isSelected());


		});

		updateDowntimeDurationNo.setOnAction(e->{
			presetUpdateFields(updateNameNo.isSelected(), updateDurationNo.isSelected(),
					updateDowntimeDurationNo.isSelected(),  updateDowntimeStartNo.isSelected());


		});

		updateDowntimeStartYes.setOnAction(e->{
			presetUpdateFields(updateNameNo.isSelected(), updateDurationNo.isSelected(),
					updateDowntimeDurationNo.isSelected(),  updateDowntimeStartNo.isSelected());


		});

		updateDowntimeStartNo.setOnAction(e->{
			presetUpdateFields(updateNameNo.isSelected(), updateDurationNo.isSelected(),
					updateDowntimeDurationNo.isSelected(),  updateDowntimeStartNo.isSelected());


		});



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
		gridPaneUpdateService.add(updateConfirmServiceButton, 3, 2);
		gridPaneUpdateService.add(updateChangeServiceButton, 4, 2);


		gridPaneUpdateService.add(updateServiceNameInstruction,0, 3);
		gridPaneUpdateService.add(updateNameYes,1, 3);
		gridPaneUpdateService.add(updateNameNo,2, 3);

		gridPaneUpdateService.add(updateServiceDurationInstruction, 0, 4);
		gridPaneUpdateService.add(updateDurationYes,1, 4);
		gridPaneUpdateService.add(updateDurationNo,2, 4);

		gridPaneUpdateService.add(updateServiceDowntimeDurationInstruction,0, 5);
		gridPaneUpdateService.add(updateDowntimeDurationYes,1, 5);
		gridPaneUpdateService.add(updateDowntimeDurationNo,2, 5);

		gridPaneUpdateService.add(updateServiceDowntimeStartTimeInstruction, 0, 6);
		gridPaneUpdateService.add(updateDowntimeStartYes,1, 6);
		gridPaneUpdateService.add(updateDowntimeStartNo,2, 6);


		gridPaneUpdateService.add(updateServiceNewName, 0, 9);
		gridPaneUpdateService.add(updateServiceNewNameText, 1, 9);
		gridPaneUpdateService.add(updateServiceNewDuration, 0, 10);
		gridPaneUpdateService.add(updateServiceNewDurationText, 1, 10);
		gridPaneUpdateService.add(updateServiceNewDowntimeDuration, 0,11);
		gridPaneUpdateService.add(updateServiceNewDowntimeDurationText, 1,11);
		gridPaneUpdateService.add(updateServiceNewDowntimeStartTime, 0, 12);
		gridPaneUpdateService.add(updateServiceNewDowntimeStartTimeText, 1,12);
		gridPaneUpdateService.add(updateServiceButton, 1, 13,2,1);

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
		viewServiceList = new Hyperlink("Service List");

		Hyperlink options[] = new Hyperlink[] {
				addServiceLink,
				updateServiceLink,
				deleteServiceLink,
				viewServiceList,
				mainMenuLink};

		for (int i=0; i<5; i++) {
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
			refreshUpdateService();
			serviceBorderPane.setCenter(gridPaneUpdateService);
			primaryStage.setTitle("Update a service");
		});

		deleteServiceLink.setOnAction(e->{
			serviceBorderPane.setCenter(gridPanedeleteService);
			primaryStage.setTitle("Delete a service");
		});  
		viewServiceList.setOnAction(e->{  //Rico adds
			serviceBorderPane.setCenter(gridPaneViewServiceList);
			primaryStage.setTitle("View service list");
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

				FlexiBookController.updateService(updateServiceText.getText(),
						Integer.parseInt(updateServiceNewDurationText.getText()),
						Integer.parseInt(updateServiceNewDowntimeDurationText.getText()),
						Integer.parseInt(updateServiceNewDowntimeStartTimeText.getText()),
						FlexiBookApplication.getCurrentUser().getUsername(), 
						updateServiceNewNameText.getText());
				Alert a = new Alert(AlertType.CONFIRMATION, "Service update successfully");
				a.showAndWait();
				errorUpdateServiceMessage.setText("");
			} catch (InvalidInputException e1) {
				errorUpdateServiceMessage.setText(e1.getMessage());
				Alert a = new Alert(AlertType.ERROR, errorAddServiceMessage.getText());
				a.showAndWait();
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

		//----------------------------------------------------------------------------------------------
		//---------------------------------Service Combo Page --------------------------------------
		//----------------------------------------------------------------------------------------------		
		
		addServiceCombo = new Text("Add a Service combo");
		addServiceCombo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		addServiceCombo.setFill(Color.BLUE);
		addServiceComboInstruction = new Text
				("Please enter the information of the service combo you would like to add.");
		addServiceComboInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorAddServiceComboMessage = new Text("");
		errorAddServiceComboMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorAddServiceComboMessage.setFill(Color.RED);


		addServiceComboName = new Text("Service Combo Name: ");
		addServiceComboNameText = new TextField();
		addServiceComboName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	

		addServiceComboDuration = new Text("Service Combo Duration: ");
		addServiceComboDurationText = new TextField();
		addServiceComboDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addServiceComboDowntimeDuration = new Text("Downtime Duration: ");
		addServiceComboDowntimeDurationText = new TextField();
		addServiceComboDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addServiceComboDowntimeStartTime = new Text("Downtime Start Time: ");
		addServiceComboDowntimeStartTimeText = new TextField();
		addServiceComboDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		serviceComboList = new Text ("add your list of services: ");
		serviceComboListText = new TextField();
		serviceComboList.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		serviceComboListText1 = new TextField();
		serviceComboList.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		serviceComboListText2 = new TextField();
		serviceComboList.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		serviceComboIsMainInstructions= new Text("Main Service  ");
		serviceComboIsMainInstructions.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		serviceComboIsMainYes = new ToggleButton("Yes");
		serviceComboIsMainNo = new ToggleButton("No");

		serviceComboIsMandatoryInstructions= new Text("Mandatory Service  ");
		serviceComboIsMandatoryInstructions.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		serviceComboIsMandatoryYes = new ToggleButton("Yes");
		serviceComboIsMandatoryNo = new ToggleButton("No");

		addServiceComboButton = new Button("Add service combo");
		//--------------------------------------------------------------------------------------------
		updateServiceComboLabel = new Text("Update a Service combo");
		updateServiceComboLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		updateServiceComboLabel.setFill(Color.BLUE);
		updateServiceComboOldInstruction = new Text("Please enter Service Combo Info you would like to update.");   		
		updateServiceComboOldInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		updateServiceComboLabelName = new Text("Service Combo: ");
		updateServiceComboText = new TextField();
		updateServiceComboLabelName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorUpdateServiceComboMessage = new Text("");
		errorUpdateServiceComboMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorUpdateServiceComboMessage.setFill(Color.RED);

		updateServiceComboNewName = new Text("New service combo name: ");
		updateServiceComboNewNameText = new TextField();
		updateServiceComboNewName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceComboNewDuration = new Text("      New duration: ");
		updateServiceComboNewDurationText = new TextField();
		updateServiceComboNewDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceComboNewDowntimeDuration= new Text("New downtime duration: ");
		updateServiceComboNewDowntimeDurationText = new TextField();
		updateServiceComboNewDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceComboNewDowntimeStartTime= new Text("New downtime start time: ");
		updateServiceComboNewDowntimeStartTimeText = new TextField();
		updateServiceComboNewDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		updateServiceComboButton = new Button("Update service combo");
		//-----------------------------------------------------------------------------------------------------------
		deleteServiceComboLabel = new Text("Delete a service combo");
		deleteServiceComboLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		deleteServiceComboLabel.setFill(Color.BLUE);

		deleteServiceComboFirstInstruction = new Text("Please enter the service combo"
				+ " you would like to delete.");
		deleteServiceComboFirstInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		deleteServiceComboNameLabel = new Text("Service combo name: ");
		deleteServiceComboNameText = new TextField();
		deleteServiceComboNameLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorDeleteServiceComboMessage = new Text("");
		errorDeleteServiceComboMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorDeleteServiceComboMessage.setFill(Color.RED);		
		deleteServiceComboButton = new Button("Delete service combo");


		gridPaneAddServiceCombo = new GridPane();
		gridPaneAddServiceCombo.setMinSize(500,70);
		gridPaneAddServiceCombo.setPadding(new Insets(100,100,100,100));	
		gridPaneAddServiceCombo.setVgap(10);
		gridPaneAddServiceCombo.setHgap(10);
		gridPaneAddServiceCombo.setAlignment(Pos.CENTER);
		gridPaneAddServiceCombo.setStyle("-fx-background-color: LIGHTBLUE;");




		//int a = 1;
		//TextField textField[] = new TextField[100];
		Button btn = new Button("Add Service");
		//gridPaneAddServiceCombo.add(btn, 0, 0);
		//btn.setOnAction(e -> {
		//  textField[a] = new TextField();
		//gridPaneAddServiceCombo.add(textField[a], 5, a);
		//  a++;


		gridPaneUpdateServiceCombo = new GridPane();
		gridPaneUpdateServiceCombo.setMinSize(900, 260);
		gridPaneUpdateServiceCombo.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneUpdateServiceCombo.setVgap(10);
		gridPaneUpdateServiceCombo.setHgap(10);
		gridPaneUpdateServiceCombo.setAlignment(Pos.CENTER);
		gridPaneUpdateServiceCombo.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPanedeleteServiceCombo = new GridPane();
		gridPanedeleteServiceCombo.setMinSize(500, 70);
		gridPanedeleteServiceCombo.setPadding(new Insets(100, 100, 100, 100));	
		gridPanedeleteServiceCombo.setVgap(10);
		gridPanedeleteServiceCombo.setHgap(10);
		gridPanedeleteServiceCombo.setAlignment(Pos.CENTER);
		gridPanedeleteServiceCombo.setStyle("-fx-background-color: LIGHTBLUE;");



		splitPane = new SplitPane();
		splitPane.setMinSize(1100, 600);
		splitPane.setMaxSize(1100, 600);
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneAddServiceCombo.add(addServiceCombo, 0, 0,2,1);
		gridPaneAddServiceCombo.add(addServiceComboInstruction, 0, 1,5,1);
		gridPaneAddServiceCombo.add(addServiceComboName, 0, 2);
		gridPaneAddServiceCombo.add(addServiceComboNameText, 1, 2); 
		gridPaneAddServiceCombo.add(addServiceComboDuration, 3, 2);
		gridPaneAddServiceCombo.add(addServiceComboDurationText,4,2);
		gridPaneAddServiceCombo.add(addServiceComboDowntimeDuration, 0, 3);
		gridPaneAddServiceCombo.add(addServiceComboDowntimeDurationText, 1, 3);
		gridPaneAddServiceCombo.add(addServiceComboDowntimeStartTime,3,3);
		gridPaneAddServiceCombo.add(addServiceComboDowntimeStartTimeText,4,3);   
		gridPaneAddServiceCombo.add(addServiceComboButton, 2, 11);
		gridPaneAddServiceCombo.add(btn, 0, 8);
		//gridPaneAddServiceCombo.add(addServiceTest, 0,8);

		gridPaneAddServiceCombo.add(serviceComboList,0,6);
		gridPaneAddServiceCombo.add(serviceComboListText,0,7);
		//gridPaneAddServiceCombo.add(serviceComboListText1,0,8);
		//gridPaneAddServiceCombo.add(serviceComboListText2,0,9);
		gridPaneAddServiceCombo.add(serviceComboIsMainInstructions,1,6);
		gridPaneAddServiceCombo.add(serviceComboIsMainYes,1,7);
		gridPaneAddServiceCombo.add(serviceComboIsMainNo,2,7);
		gridPaneAddServiceCombo.add(serviceComboIsMandatoryInstructions,3,6);
		gridPaneAddServiceCombo.add(serviceComboIsMandatoryYes,3,7);
		gridPaneAddServiceCombo.add(serviceComboIsMandatoryNo,4,7);

		gridPaneUpdateServiceCombo.add(updateServiceComboLabel, 1, 0,2,1);
		gridPaneUpdateServiceCombo.add(updateServiceComboOldInstruction, 0, 1,6,1);
		gridPaneUpdateServiceCombo.add(updateServiceComboLabelName, 0, 2);
		gridPaneUpdateServiceCombo.add(updateServiceComboText, 1, 2);
		gridPaneUpdateServiceCombo.add(updateServiceComboNewName, 0, 5);
		gridPaneUpdateServiceCombo.add(updateServiceComboNewNameText, 1, 5);
		gridPaneUpdateServiceCombo.add(updateServiceComboNewDuration, 2, 5);
		gridPaneUpdateServiceCombo.add(updateServiceComboNewDurationText, 3, 5);
		gridPaneUpdateServiceCombo.add(updateServiceComboNewDowntimeDuration, 0,6);
		gridPaneUpdateServiceCombo.add(updateServiceComboNewDowntimeDurationText, 1, 6);
		gridPaneUpdateServiceCombo.add(updateServiceComboNewDowntimeStartTime, 2, 6);
		gridPaneUpdateServiceCombo.add(updateServiceComboNewDowntimeStartTimeText, 3,6);
		gridPaneUpdateServiceCombo.add(updateServiceComboButton, 2, 7,2,1);

		gridPanedeleteServiceCombo.add(deleteServiceComboLabel,1,0,2,1);
		gridPanedeleteServiceCombo.add(deleteServiceComboFirstInstruction, 0,1,5,1);
		gridPanedeleteServiceCombo.add(deleteServiceComboNameLabel, 0, 2);
		gridPanedeleteServiceCombo.add(deleteServiceComboNameText, 1, 2);
		gridPanedeleteServiceCombo.add(deleteServiceComboButton, 3,2);


		verticalMenuCombo = new VBox();
		verticalMenuCombo.setPadding(new Insets(10));
		verticalMenuCombo.setSpacing(8);

		Text serviceComboMenuTitle = new Text("What do you wish to do?");
		serviceComboMenuTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 18));


		verticalMenuCombo.getChildren().add(serviceComboMenuTitle);


		addServiceComboLink = new Hyperlink("Add a service combo");
		updateServiceComboLink = new Hyperlink("Update a service combo");
		deleteServiceComboLink = new Hyperlink ("Delete a service combo");
		viewServiceList = new Hyperlink("Service List");
		mainMenuComboLink = new Hyperlink("Main Menu");


		Hyperlink optionsCombo[] = new Hyperlink[] {
				addServiceComboLink,
				updateServiceComboLink,
				deleteServiceComboLink,
				viewServiceList,
				mainMenuComboLink};

		for (int i=0; i<5; i++) {
			VBox.setMargin(optionsCombo[i], new Insets(0, 0, 0, 8));
			verticalMenuCombo.getChildren().add(optionsCombo[i]);

		}



		serviceComboBorderPane = new BorderPane();
		serviceComboBorderPane.setLeft(verticalMenuCombo);
		serviceComboBorderPane.setCenter(gridPaneAddServiceCombo);


		serviceComboScene = new Scene(serviceComboBorderPane);




		addServiceComboLink.setOnAction(e->{
			serviceComboBorderPane.setCenter(gridPaneAddServiceCombo);
			primaryStage.setTitle("Add a service combo");
		});
		updateServiceComboLink.setOnAction(e->{
			serviceComboBorderPane.setCenter(gridPaneUpdateServiceCombo);
			primaryStage.setTitle("Update a service combo");
		});

		deleteServiceComboLink.setOnAction(e->{
			serviceComboBorderPane.setCenter(gridPanedeleteServiceCombo);
			primaryStage.setTitle("Delete a service combo");
		});  
		btn.setOnAction(e->{
			int a = 1;
			TextField addServiceTest[] = new TextField[100];
			gridPaneAddServiceCombo.add(btn, 0, 8);
			addServiceTest[a] = new TextField();
			gridPaneAddServiceCombo.add(addServiceTest[a], 5, a);
			a++;


		});



		//rico adds view list


		mainMenuComboLink.setOnAction(e->{
			primaryStage.setScene(ownerMainScene);
			primaryStage.setTitle("Main Menu");
		});  

		addServiceComboButton.setOnAction(e->{
			//			try {
			if(serviceComboNameTextField.getText()== null ||
					serviceComboNameTextField.getText().trim().isEmpty()) {
				errorAddServiceComboMessage.setText("A service combo name should be set");
			}
			if (serviceComboDurationTextField.trim().isEmpty()){
				errorAddServiceComboMessage.setText
				("A service combo duration should be set");
			}
			else if(serviceComboDowntimeDurationTextField.trim().isEmpty()){		    		
				errorAddServiceComboMessage.setText
				("A service combo downtime duration should be set");
			}
			else if(serviceComboDowntimeStartTextField.trim().isEmpty()) {
				errorAddServiceComboMessage.setText("A service combo downtime start time should be set");
			}
			else {
				//					FlexiBookController.defineServiceCombo(FlexiBookApplication.getCurrentUser().getUsername(),
				//							addServiceComboNameText,addServiceComboMainServiceText, 
				//							serviceComboListText,serviceComboIsMandatoryYes ); //change this
				//					
				//					FlexiBookController.addServiceCombo(addServiceComboNameText.getText(),
				//									Integer.parseInt(addServiceComboDurationText.getText()),
				//									Integer.parseInt(addServiceComboDowntimeDurationText.getText()),
				//									Integer.parseInt(addServiceComboDowntimeStartTimeText.getText()), 
				//									FlexiBookApplication.getCurrentUser().getUsername());
				//					Alert a = new Alert(AlertType.CONFIRMATION, "Service combo added successfully");
				//					a.showAndWait();
				//					errorAddServiceComboMessage.setText("");
			}
			//			} catch (InvalidInputException e1) {
			//				errorAddServiceComboMessage.setText(e1.getMessage());
			//				Alert a = new Alert(AlertType.ERROR, errorAddServiceComboMessage.getText());
			//				a.showAndWait();
			//			}
		});


		updateServiceComboButton.setOnAction(e->{
			//try {
			//				FlexiBookController.updateServiceCombo(FlexiBookApplication.getCurrentUser().getUsername(),
			//						updateServiceComboNameText, newSCName, mainService, services, mandatory);
			//				
			//				FlexiBookController.updateService(serviceComboTextField.getText(),
			//						Integer.parseInt(serviceComboDurationTextField),
			//						Integer.parseInt(serviceComboDowntimeDurationTextField),
			//						Integer.parseInt(serviceComboDowntimeStartTextField),
			//						FlexiBookApplication.getCurrentUser().getUsername() , 
			//						serviceComboNameTextField.getText());
			//				errorUpdateServiceComboMessage.setText("");
			//			} catch (InvalidInputException e1) {
			//				errorUpdateServiceComboMessage.setText(e1.getMessage());
			//			}
		});

		deleteServiceComboButton.setOnAction(e->{
			try {
				if(deleteServiceComboNameText.getText()== null || 
						deleteServiceComboNameText.getText().trim().isEmpty()) {
					errorDeleteServiceComboMessage.setText("A service combo name should be set to get deleted");
					Alert a = new Alert(AlertType.ERROR, errorDeleteServiceComboMessage.getText());
					a.showAndWait();
				}
				else {
					FlexiBookController.deleteService(deleteServiceNameText.getText(),
							FlexiBookApplication.getCurrentUser().getUsername());			  
					errorDeleteServiceComboMessage.setText("");
					Alert a = new Alert(AlertType.CONFIRMATION, "Service combo deleted successfully");
					a.showAndWait();
				}
			} catch (InvalidInputException e1) {
				errorDeleteServiceComboMessage.setText(e1.getMessage());
				Alert a = new Alert(AlertType.ERROR, errorDeleteServiceComboMessage.getText());
				a.showAndWait();
			}

		});

		//----------------------------------------------------------------------------------------------
		//--------------------------------- Update Customer Account Page --------------------------------------
		//----------------------------------------------------------------------------------------------

		//Initializing labels
		updateNewUsername = new Label("New Username:");
		updateNewPassword = new Label("New Password:");
		updateConfirmPassword = new Label("Confirm New Password:");

		//Initializing Texts
		header = new Text("Account Management");
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

		updateAccGrid.add(instruction21, 0, 8, 6, 1);
		updateAccGrid.add(instruction22, 0, 9, 6, 1);
		updateAccGrid.add(deleteButton, 0, 11);

		// confirm button action
		updateButton.setOnAction(e->{
			try {
				if(newPasswordText.getText().equals(confirmNewPasswordText.getText())) {
					FlexiBookController.updateAccount(FlexiBookApplication.getCurrentUser().getUsername(), newUsernameText.getText(),
							newPasswordText.getText());
					Alert alert = new Alert(AlertType.CONFIRMATION, "Your username and password have been"
							+ "sucessfully update.");
					alert.showAndWait();				
					} 
				else {
					Alert alert = new Alert(AlertType.ERROR, "Your password and confirmation password do not match.");
					alert.showAndWait();	
				}

			} catch (InvalidInputException e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getMessage());
				alert.showAndWait();				}
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
					Alert a = new Alert(AlertType.CONFIRMATION, "Account Successfully Deleted");
					a.showAndWait();
					primaryStage.setScene(loginScene);
					} 
				catch (InvalidInputException e1) {
					Alert a = new Alert(AlertType.ERROR, e1.getMessage());
					a.showAndWait();				
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


		//----------------------------------------------------------------------------------------------
		//--------------------------------- Update Customer Account Page --------------------------------------
		//----------------------------------------------------------------------------------------------
		
		//Initializing labels
		newOwnerPassword = new Label("New Password:");
		confirmOwnerPassword = new Label("Confirm New Password:");

		//Initializing Texts
		ownerHeader = new Text("Account Management");
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

		// confirm button action
		updateAccButton.setOnAction(e->{
			try {
				if(newOwnerPasswordText.getText().equals(confirmOwnerPasswordText.getText())) {
					FlexiBookController.updateAccount(FlexiBookApplication.getCurrentUser().getUsername(),
							FlexiBookApplication.getCurrentUser().getUsername(), newOwnerPasswordText.getText());
					Alert alert = new Alert(AlertType.CONFIRMATION, "Your password has been"
							+ "sucessfully update.");
					alert.showAndWait();						} 
				else {

					Alert alert = new Alert(AlertType.ERROR, "Your password and confirmation password do not match.");
					alert.showAndWait();
				}

			} catch (InvalidInputException e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getMessage());
				alert.showAndWait();

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


		//----------------------------------------------------------------------------------------------
		//--------------------------------- Business Menu  --------------------------------------
		//----------------------------------------------------------------------------------------------
		
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

		//----------------------------------------------------------------------------------------------
		//--------------------------------- Business Page --------------------------------------
		//----------------------------------------------------------------------------------------------
		
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
		ownerBusinessInfoPane.setMinSize(1100, 500);
		ownerBusinessInfoPane.setLeft(verticalMenuBusinessInfo);
		ownerRefreshBusinessInfo();
		ownerBusinessInfoPane.setCenter(gridPaneownerViewBusinessInfo);	

		ownerBusinessScene  = new Scene(ownerBusinessInfoPane);


		ownerViewBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("View Business Information");
			ownerRefreshBusinessInfo();
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
				if(FlexiBookController.ViewBusinessInfo().get(0).equals("no business name entered")) {
					FlexiBookController.SetUpContactInfo(addBusinessNameText.getText(), addAddressText.getText(), addPhoneNumberText.getText(), addEmailText.getText());
					errorBusinessInfoMessage.setText("");
					Alert alert = new Alert(AlertType.CONFIRMATION,"Business Information Successfully Set Up!");					
					alert.showAndWait();
				}
				else {
					FlexiBookController.UpdateBasicInfo(addBusinessNameText.getText(), addAddressText.getText(), addPhoneNumberText.getText(), addEmailText.getText());
					errorBusinessInfoMessage.setText("");
					Alert alert = new Alert(AlertType.CONFIRMATION, "Business Information Successfully Updated!");					
					alert.showAndWait();
				}
			}
			catch (InvalidInputException e1) {
				errorBusinessInfoMessage.setText(e1.getMessage());
				Alert alert = new Alert(AlertType.WARNING, e1.getMessage());
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

		updateHoursOldDay = new Text("Old Day of Week: ");
		updateHoursOldDayText = new ComboBox<String>();
		updateHoursOldDayText.getItems().add("Monday");
		updateHoursOldDayText.getItems().add("Tuesday");
		updateHoursOldDayText.getItems().add("Wednesday");
		updateHoursOldDayText.getItems().add("Thursday");
		updateHoursOldDayText.getItems().add("Friday");
		updateHoursOldDayText.getItems().add("Saturday");
		updateHoursOldDayText.getItems().add("Sunday");
		updateHoursOldDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateHoursOldTime = new Text("Old Start Time: ");
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

		for (int i=0; i<6; i++) {
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
				FlexiBookController.SetUpBusinessHours((String) addHoursDayText.getSelectionModel().getSelectedItem(), Time.valueOf(addHoursStartTimeText.getText()+":00"), Time.valueOf(addHoursEndTimeText.getText()+":00"));
				errorAddHoursMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, "Business Hours Successfully Added");				alert.showAndWait();
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
				FlexiBookController.UpdateBusinessHours((String) updateHoursOldDayText.getSelectionModel().getSelectedItem(), Time.valueOf(updateHoursOldTimeText.getText()+":00"), (String) updateHoursNewDayText.getSelectionModel().getSelectedItem(), Time.valueOf(updateHoursNewStartTimeText.getText()+":00"), Time.valueOf(updateHoursNewEndTimeText.getText()+":00"));
				errorUpdateHoursMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, "Business Hours Successfully Updated");				alert.showAndWait();
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
				FlexiBookController.RemoveBusinessHours((String) deleteHoursDayText.getSelectionModel().getSelectedItem(), Time.valueOf(deleteHoursTimeText.getText()+":00"));
				errorDeleteHoursMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, "Business Hours Successfully Deleted");				alert.showAndWait();
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

		updateTimeSlotOldDate = new Text("Old Start Date: ");
		updateTimeSlotOldDateText = new TextField();
		updateTimeSlotOldDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotOldDateText.setPromptText("YYYY-MM-DD");

		updateTimeSlotOldTime = new Text("Old Start Time: ");
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

		for (int i=0; i<7; i++) {
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
			FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));

			try {
				String type = (String) addTimeSlotTypeText.getSelectionModel().getSelectedItem();
				FlexiBookController.AddaNewTimeSlot(((String) addTimeSlotTypeText.getSelectionModel().getSelectedItem()), Date.valueOf(addTimeSlotStartDateText.getText()), Time.valueOf(addTimeSlotStartTimeText.getText()+":00"), Date.valueOf(addTimeSlotEndDateText.getText()), Time.valueOf(addTimeSlotEndTimeText.getText()+":00"));
				erroraddTimeSlotMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION,  type+ " Successfully Added");				
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
			FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));

			try {
				String type = (String) updateTimeSlotTypeText.getSelectionModel().getSelectedItem();
				FlexiBookController.UpdateHolidayOrVacation((String) updateTimeSlotTypeText.getSelectionModel().getSelectedItem(), Date.valueOf(updateTimeSlotOldDateText.getText()), Time.valueOf(updateTimeSlotOldTimeText.getText()+":00"), Date.valueOf(updateTimeSlotNewStartDateText.getText()), Time.valueOf(updateTimeSlotNewStartTimeText.getText()+":00"), Date.valueOf(updateTimeSlotNewEndDateText.getText()), Time.valueOf(updateTimeSlotNewEndTimeText.getText()+":00"));
				errorupdateTimeSlotMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, type+" Successfully Updated");		
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
			FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));

			try {
				String type = (String) updateTimeSlotTypeText.getSelectionModel().getSelectedItem();
				FlexiBookController.RemoveTimeSlot((String) deleteTimeSlotTypeText.getSelectionModel().getSelectedItem(), Date.valueOf(deleteTimeSlotStartDateText.getText()), Time.valueOf(deleteTimeSlotStartTimeText.getText()+":00"), Date.valueOf(deleteTimeSlotEndDateText.getText()), Time.valueOf(deleteTimeSlotEndTimeText.getText()+":00"));
				errordeleteTimeSlotMessage.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION, type+" Successfully Deleted");
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

		//----------------------------------------------------------------------------------------------
		//--------------------------------- Customer View Business Info ------------------------------------------
		//----------------------------------------------------------------------------------------------	
					

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


		customerViewBusinessInfoBusinessHoursLink1 = new Hyperlink("View Business Hours");
		customerViewBusinessInfoHolidaysLink1 = new Hyperlink("View Business Holidays");
		customerViewBusinessInfoVacationsLink1 = new Hyperlink("View Business Vacations");
		customerViewBusinessInfoLink1 = new Hyperlink("View Business Information");
		customerBusinessInfoMainMenuLink1 = new Hyperlink("Main Menu");


		Hyperlink optio1[] = new Hyperlink[] {
				customerViewBusinessInfoLink1,
				customerViewBusinessInfoBusinessHoursLink1,
				customerViewBusinessInfoHolidaysLink1,
				customerViewBusinessInfoVacationsLink1,
				customerBusinessInfoMainMenuLink1};

		for (int i=0; i<5; i++) {
			VBox.setMargin(optio1[i], new Insets(0, 0, 0, 8));
			verticalMenucustomerViewBusinessInfo.getChildren().add(optio1[i]);
		}


		customerViewBusinessInfoPane = new BorderPane();
		customerViewBusinessInfoPane.setMinSize(1150, 500);
		customerViewBusinessInfoPane.setLeft(verticalMenucustomerViewBusinessInfo);
		customerRefreshBusinessInfo();
		customerViewBusinessInfoPane.setCenter(gridPanecustomerViewBusinessInfo);

		customerViewBusinessScene  = new Scene(customerViewBusinessInfoPane);


		customerViewBusinessInfoBusinessHoursLink1.setOnAction(e->{
			primaryStage.setTitle("View Business Hours");
			customerViewBusinessInfoPane.setCenter(viewBusinessHourTable);
		});
		customerViewBusinessInfoVacationsLink1.setOnAction(e->{
			primaryStage.setTitle("View Business Vacations");
			refreshVacation(viewVacationTable);
			customerViewBusinessInfoPane.setCenter(viewVacationTable);
		});
		customerViewBusinessInfoHolidaysLink1.setOnAction(e->{
			primaryStage.setTitle("View Business Holidays");
			refreshHoliday(viewHolidayTable);
			customerViewBusinessInfoPane.setCenter(viewHolidayTable);
		});

		customerViewBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("Customer View Business Information");
			customerRefreshBusinessInfo();
			customerViewBusinessInfoPane.setCenter(gridPanecustomerViewBusinessInfo);
		});
		customerBusinessInfoMainMenuLink1.setOnAction(e->{
			primaryStage.setScene(customerMainScene);
		});
	}
	
	
	
	//----------------------------------------------------------------------------------------------
	//--------------------------------- Refresh / Helper Methods --------------------------------------
	//----------------------------------------------------------------------------------------------

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
	
	private ObservableList<TOAppointment> getAppointmentsData() {
		ObservableList<TOAppointment> list = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getTOAppointments().size(); i++) {
			list.add(FlexiBookController.getTOAppointments().get(i));
		}
		return list;
	}
	
	private void refreshAppDate() {
		appTable.setItems(getAppointmentsData());
	}

	private void presetUpdateFields(boolean updateNameNo,boolean updateDurationNo,
			boolean updateDowntimeDurationNo, boolean updateDowntimeStartNo) {
		TOService service = findService(updateServiceText.getText());

		if (updateNameNo) {
			updateServiceNewNameText.setText(service.getName());
			updateServiceNewNameText.setEditable(false);
		}
		else {
			if(updateServiceNewNameText.getText().equals(service.getName())) {
				updateServiceNewNameText.setText("");
			}
			updateServiceNewNameText.setEditable(true);

		}
		if (updateDurationNo) {
			updateServiceNewDurationText.setText(Integer.toString(service.getDuration()));		
			updateServiceNewDurationText.setEditable(false);
		}
		else {
			if(updateServiceNewDurationText.getText().equals(Integer.toString(service.getDuration()))) {
				updateServiceNewDurationText.setText("");
			}
			updateServiceNewDurationText.setEditable(true);

		}
		if (updateDowntimeDurationNo) {
			updateServiceNewDowntimeDurationText.setText(Integer.toString(service.getDowntimeDuration()));
			updateServiceNewDowntimeDurationText.setEditable(false);
		}
		else {
			if(updateServiceNewDowntimeDurationText.getText().equals(Integer.toString(service.getDowntimeDuration()))) {
				updateServiceNewDowntimeDurationText.setText("");
			}
			updateServiceNewDowntimeDurationText.setEditable(true);

		}
		if (updateDowntimeStartNo) {
			updateServiceNewDowntimeStartTimeText.setText(Integer.toString(service.getDowntimeStart()));
			updateServiceNewDowntimeStartTimeText.setEditable(false);
		}
		else {
			if(updateServiceNewDowntimeStartTimeText.getText().equals(Integer.toString(service.getDowntimeStart()))) {
				updateServiceNewDowntimeStartTimeText.setText("");
			}
			updateServiceNewDowntimeStartTimeText.setEditable(true);

		}

	}


	private TOService findService(String name) {
		TOService service = null;

		for(int i=0; i<FlexiBookController.getTOServices().size(); i++) {
			if(FlexiBookController.getTOServices().get(i).getName().equals(name)) service = FlexiBookController.getTOServices().get(i);
		}

		return service;
	}

	private void refreshUpdateService() {
		updateChangeServiceButton.setVisible(false);
		updateServiceNameInstruction.setVisible(false);
		updateNameYes.setVisible(false);
		updateNameNo.setVisible(false);
		updateServiceDurationInstruction.setVisible(false);
		updateDurationYes.setVisible(false);
		updateDurationNo.setVisible(false);
		updateServiceDowntimeDurationInstruction.setVisible(false);
		updateDowntimeDurationYes.setVisible(false);
		updateDowntimeDurationNo.setVisible(false);
		updateServiceDowntimeStartTimeInstruction.setVisible(false);
		updateDowntimeStartYes.setVisible(false);
		updateDowntimeStartNo.setVisible(false);
		errorUpdateServiceMessage.setVisible(false);
		updateServiceNewName.setVisible(false);
		updateServiceNewNameText.setVisible(false);
		updateServiceNewDuration.setVisible(false);
		updateServiceNewDurationText.setVisible(false);
		updateServiceNewDowntimeDuration.setVisible(false);
		updateServiceNewDowntimeDurationText.setVisible(false);
		updateServiceNewDowntimeStartTime.setVisible(false);
		updateServiceNewDowntimeStartTimeText.setVisible(false);
		updateServiceButton.setVisible(false);
		updateServiceText.setText("");
		updateServiceText.setEditable(true);
	}
	
	public void ownerRefreshBusinessInfo() {
		try {
			ownerViewBusinessNameResult.setText(FlexiBookController.ViewBusinessInfo().get(0));
		}
		catch(InvalidInputException e) {
			ownerViewBusinessNameResult.setText("no business name entered");
		}
		try {
			ownerViewAddressResult.setText(FlexiBookController.ViewBusinessInfo().get(1));
		}
		catch(InvalidInputException e) {
			ownerViewAddressResult.setText("no address entered");
		}
		try {
			ownerViewPhoneNumberResult.setText(FlexiBookController.ViewBusinessInfo().get(2));
		}
		catch(InvalidInputException e) {
			ownerViewPhoneNumberResult.setText("no address entered");
		}
		try {
			ownerViewEmailResult.setText(FlexiBookController.ViewBusinessInfo().get(3));
		}
		catch(InvalidInputException e) {
			ownerViewEmailResult.setText("no address entered");
		}
	}
	public void customerRefreshBusinessInfo() {
		try {
			customerViewBusinessNameResult.setText(FlexiBookController.ViewBusinessInfo().get(0));
		}
		catch(InvalidInputException e) {
			customerViewBusinessNameResult.setText("no business name entered");
		}
		try {
			customerViewAddressResult.setText(FlexiBookController.ViewBusinessInfo().get(1));
		}
		catch(InvalidInputException e) {
			customerViewAddressResult.setText("no address entered");
		}
		try {
			customerViewPhoneNumberResult.setText(FlexiBookController.ViewBusinessInfo().get(2));
		}
		catch(InvalidInputException e) {
			customerViewPhoneNumberResult.setText("no address entered");
		}
		try {
			customerViewEmailResult.setText(FlexiBookController.ViewBusinessInfo().get(3));
		}
		catch(InvalidInputException e) {
			customerViewEmailResult.setText("no address entered");
		}
	}
	
	private void resetMakeAppPage() {
		errorMakeAppointment.setText("");
		makeAppServiceText.setText("");
		makeAppDatePicker.setValue(null);
		makeAppStartTimeText.setText("");
	}
	
	private void resetUpdateAppPage() {
		errorUpdateAppointment.setText("");
		updateAppServiceText.setText("");
		updateAppDatePicker.setValue(null);
		updateAppStartTimeText.setText("");
		updateAppNewServiceText.setText("");
		updateAppNewDatePicker.setValue(null);
		updateAppNewStartTimeText.setText("");
		toggleGroupUpdateApp.selectToggle(null);
	}
	
	private void resetCancelAppPage() {
		errorCancelAppointment.setText("");
		cancelAppServiceText.setText("");
		cancelAppDatePicker.setValue(null);
		cancelAppStartTimeText.setText("");
	}


}


