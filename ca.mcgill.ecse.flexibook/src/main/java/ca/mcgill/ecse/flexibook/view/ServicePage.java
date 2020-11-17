package ca.mcgill.ecse.flexibook.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ServicePage extends Application {

//	private static final long serialVersionUID = -4426310869335015543L;
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
	//--------------------------------------ADD SERVICE----------------------------------------------------------	

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

	//-------------------------------------------------------------------------------	
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


	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {


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

		Text title = new Text("What do you wish to do?");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text title2 = new Text("What do you wish to do?");
		title2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text title3 = new Text("What do you wish to do?");
		title3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuAddService.getChildren().add(title);
		verticalMenuUpdateService.getChildren().add(title2);
		verticalMenuCancelService.getChildren().add(title3);


		addServiceLink1 = new Hyperlink("Add a service");
		updateServiceLink1 = new Hyperlink("Update a service");
		cancelServiceLink1 = new Hyperlink ("Cancel a service");

		addServiceLink2 = new Hyperlink("Add a service");
		updateServiceLink2 = new Hyperlink("Update an appointment");
		cancelServiceLink2 = new Hyperlink ("Cancel an appointment");

		addServiceLink3 = new Hyperlink("Make an appointment");
		updateServiceLink3 = new Hyperlink("Update an appointment");
		cancelServiceLink3 = new Hyperlink ("Cancel an appointment");


		Hyperlink options1[] = new Hyperlink[] {
				addServiceLink1,
				updateServiceLink1,
				cancelServiceLink1};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options1[i], new Insets(0, 0, 0, 8));
			verticalMenuAddService.getChildren().add(options1[i]);
		}

		Hyperlink options2[] = new Hyperlink[] {
				addServiceLink2,
				updateServiceLink2,
				cancelServiceLink2};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options2[i], new Insets(0, 0, 0, 8));
			verticalMenuUpdateService.getChildren().add(options2[i]);
		}

		Hyperlink options3[] = new Hyperlink[] {
				addServiceLink3,
				updateServiceLink3,
				cancelServiceLink3};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options3[i], new Insets(0, 0, 0, 8));
			verticalMenuCancelService.getChildren().add(options3[i]);
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
		
		
		primaryStage.setScene(addServiceScene);
		primaryStage.show();


//		//Login Page----------------------------------------------------------------
//		//initializing labels
//		flexibook = new Text("FlexiBook");
//		slogan = new Text("Time to get Organised!");
//		usernameText = new Text("Username");       
//		passwordText = new Text("Password"); 
//		usernameText2 = new Text("Username");       
//		passwordText2 = new Text("Password"); 
//		confirmPasswordText = new Text("Confirm Passsword");
//		errorLoginText = new Text();
//		errorSignUpText = new Text();
//
//		//initializing text fields
//		usernameTextField = new TextField();       
//		passwordTextField = new PasswordField(); 
//		usernameTextField2 = new TextField();       
//		passwordTextField2 = new PasswordField(); 
//		confirmPasswordTextField = new PasswordField(); 
//
//		//initializing buttons 
//		loginButton = new Button("Login"); 
//		signupButton = new Button("Sign up");
//
//		//initializing Grid Pane 
//		root = new BorderPane();  
//
//		//initializing Grid Pane 
//		gridPaneLogin = new GridPane();    
//
//		//Setting size for the pane 
//		root.setMinSize(1100, 600); 
//		root.setMaxSize(1100, 600); 
//
//		//Setting the padding  
//		gridPaneLogin.setPadding(new Insets(10, 10, 10, 10)); 
//
//		//Setting the vertical and horizontal gaps between the columns 
//		gridPaneLogin.setVgap(10); 
//		gridPaneLogin.setHgap(20);       
//
//		//Setting alignments 
//		root.setTop(flexibook);
//		root.setCenter(gridPaneLogin);
//		root.setBottom(slogan);;
//		BorderPane.setAlignment(flexibook, Pos.TOP_CENTER);
//		gridPaneLogin.setAlignment(Pos.CENTER);
//		BorderPane.setAlignment(slogan, Pos.BOTTOM_CENTER);
//
//
//		//Arranging all the nodes in the grid 
//		gridPaneLogin.add(errorLoginText, 0, 0);
//		gridPaneLogin.add(errorSignUpText, 2, 0);
//		gridPaneLogin.add(usernameText, 0, 1); 
//		gridPaneLogin.add(usernameTextField, 1, 1); 
//		gridPaneLogin.add(passwordText, 0, 2);       
//		gridPaneLogin.add(passwordTextField, 1, 2); 
//		gridPaneLogin.add(loginButton, 0, 4); 
//		gridPaneLogin.add(usernameText2, 2, 1);
//		gridPaneLogin.add(usernameTextField2, 3, 1);
//		gridPaneLogin.add(passwordText2, 2, 2);
//		gridPaneLogin.add(passwordTextField2, 3, 2);
//		gridPaneLogin.add(confirmPasswordText, 2, 3);
//		gridPaneLogin.add(confirmPasswordTextField, 3, 3);
//		gridPaneLogin.add(signupButton, 2, 4);
//
//		//Styling nodes  
//		loginButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
//		signupButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
//
//
//		loginButton.setOnAction(e->{
//			try {
//				FlexiBookController.login(usernameTextField.getText() , passwordTextField.getText());
//				errorLoginText.setText("");
//				primaryStage.setTitle("Service");
//				primaryStage.setScene(addServiceScene);
//				primaryStage.show();
//			} catch (InvalidInputException e1) {
//				errorLoginText.setText(e1.getMessage());
//			}
//		});
//
//
//
//		signupButton.setOnAction(e->{
//			try {
//				if(passwordTextField2.getText().equals(confirmPasswordTextField.getText())) {
//					FlexiBookController.signUpCustomerAccount(usernameTextField2.getText() , passwordTextField2.getText());
//					errorSignUpText.setText("");
//					primaryStage.setTitle("Service");
//					primaryStage.setScene(addServiceScene);
//					primaryStage.show();
//
//				}
//				else { 
//					errorSignUpText.setText("Your password and confirmation password do not match.");
//				}
//			} catch (InvalidInputException e1) {
//				errorSignUpText.setText(e1.getMessage());
//			}
//		});
//
//		flexibook.setStyle("-fx-font: normal bold 40px 'serif' ");
//		slogan.setStyle("-fx-font: normal bold 40px 'serif' ");
//		usernameText.setStyle("-fx-font: normal bold 20px 'serif' "); 
//		passwordText.setStyle("-fx-font: normal bold 20px 'serif' ");  
//		usernameText2.setStyle("-fx-font: normal bold 20px 'serif' "); 
//		passwordText2.setStyle("-fx-font: normal bold 20px 'serif' ");  
//		confirmPasswordText.setStyle("-fx-font: normal bold 20px 'serif' ");
//
//		root.setStyle("-fx-background-color: BEIGE;"); 
//
//		//Creating a scene object 
//		loginScene = new Scene(root);
//
//		//ViewManager.setScene(scene);
//		primaryStage.setTitle("Login");
//		primaryStage.setScene(loginScene);
//		primaryStage.show();



	}


}
