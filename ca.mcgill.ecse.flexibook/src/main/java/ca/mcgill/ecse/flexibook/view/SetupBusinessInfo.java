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

import java.sql.Time;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class SetupBusinessInfo extends Application {

//	private static final long serialVersionUID = -4426310869335015543L;
	//error messages
	private Text errorBusinessInfoMessage;
	private Text errorAddHoursMessage;
	private Text errorUpdateHoursMessage;

	// View/Edit Business Info
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

	// Add Business Hours

	private Text addNewBusinessHoursLabel;
	private Text addHoursInstruction;
	private Text addHoursDay;
	private TextField addHoursDayText;
	private Text addHoursStartTime;
	private TextField addHoursStartTimeText;
	private Text addHoursEndTime;
	private TextField addHoursEndTimeText;
	private Button addHoursButton;

	//-------------------------------------------------------------------------------	
	//Update Business Hours
	private Text updateHoursLabel;
	private Text updateHoursInstruction;
	private Text updateHoursOldDay;
	private TextField updateHoursOldDayText;
	private Text updateHoursNewDay;
	private TextField updateHoursNewDayText;
	private Text updateHoursOldTime;
	private TextField updateHoursOldTimeText;
	private Text updateHoursNewStartTime;
	private TextField updateHoursNewStartTimeText;
	private Text updateHoursNewEndTime;
	private TextField updateHoursNewEndTimeText;

	private Button updateHoursButton;


	//Grid pane
	private GridPane gridPaneBusinessInfo;
	private GridPane gridPaneAddHours;
	private GridPane gridPaneUpdateHours;
	private GridPane gridPaneDeleteHours;

	//Split pane
	private SplitPane businessSplitPane;
	//Lines seperatinon
	//	private Line line1;
	//	private Line line2;
	private VBox verticalMenuBusinessInfo;
	private VBox verticalMenuAddHours;
	private VBox verticalMenuUpdateHours;
	private VBox verticalMenuDeleteHours;
	private Hyperlink editBusinessInfoLink1;
	private Hyperlink addHoursLink1;
	private Hyperlink updateHoursLink1;
	private Hyperlink removeHoursLink1;
	private Hyperlink editBusinessInfoLink2;
	private Hyperlink addHoursLink2;
	private Hyperlink updateHoursLink2;
	private Hyperlink removeHoursLink2;
	private Hyperlink editBusinessInfoLink3;
	private Hyperlink addHoursLink3;
	private Hyperlink updateHoursLink3;
	private Hyperlink removeHoursLink3;
	private Hyperlink editBusinessInfoLink4;
	private Hyperlink addHoursLink4;
	private Hyperlink updateHoursLink4;
	private Hyperlink removeHoursLink4;
	

	
	//	//Horizontal Box
	//	private HBox horizontalMakeApp;
	//	
	//Border Pane
	private BorderPane addBusinessInfoPane;
	private BorderPane updateBusinessBorderPane;
	private BorderPane cancelBusinessBorderPane;
	//	
	//	//FlexiBook logo
	//	private ImageView flexiBookLogo;
	//	
	//Creating a scene object
	private Scene businessScene;
	private Scene updateBusinessScene;
	private Scene cancelBusinessScene;


	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {


		editBusinessInfo = new Text("View/Edit Business Information");
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


		//--------------------------------------------------------------------------------------------


		addNewBusinessHoursLabel = new Text("Add New Business Hours");
		addNewBusinessHoursLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		addNewBusinessHoursLabel.setFill(Color.BLUE);
		addHoursInstruction = new Text("Please enter new business hours below.");   		
		addHoursInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		addHoursDay = new Text("Day Of Week: ");
		addHoursDayText = new TextField();
		addHoursDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addHoursDayText.setPromptText("ex: Monday, Tuesday, etc");

		errorAddHoursMessage = new Text("");
		errorAddHoursMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorAddHoursMessage.setFill(Color.RED);

		addHoursStartTime = new Text("Start Time: ");
		addHoursStartTimeText = new TextField();
		addHoursStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addHoursStartTimeText.setPromptText("ex: 00:00");

		addHoursEndTime = new Text("End Time: ");
		addHoursEndTimeText = new TextField();
		addHoursEndTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		addHoursEndTimeText.setPromptText("ex: 00:00");



		addHoursButton = new Button("Add");

		//-----------------------------------------------------------------------------------------------------------

		updateHoursLabel = new Text("Update Business Hours");
		updateHoursLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		updateHoursLabel.setFill(Color.BLUE);

		updateHoursInstruction = new Text("Please enter the following information for the business hours you wish to update.");
		updateHoursInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateHoursOldDay = new Text("Current Day of Week: ");
		updateHoursOldDayText = new TextField();
		updateHoursOldDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateHoursOldDayText.setPromptText("ex: Monday, Tuesday, etc");
		
		updateHoursOldTime = new Text("Current Start Time: ");
		updateHoursOldTimeText = new TextField();
		updateHoursOldTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateHoursOldTimeText.setPromptText("ex: 00:00");
		
		updateHoursNewDay = new Text("New Day of Week: ");
		updateHoursNewDayText = new TextField();
		updateHoursNewDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateHoursNewDayText.setPromptText("ex: Monday, Tuesday, etc");
		
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


		gridPaneBusinessInfo = new GridPane();
		gridPaneBusinessInfo.setMinSize(500,70);
		gridPaneBusinessInfo.setPadding(new Insets(100,100,100,100));	
		gridPaneBusinessInfo.setVgap(10);
		gridPaneBusinessInfo.setHgap(10);
		gridPaneBusinessInfo.setAlignment(Pos.CENTER);
		gridPaneBusinessInfo.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneAddHours = new GridPane();
		gridPaneAddHours.setMinSize(800, 130);
		gridPaneAddHours.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneAddHours.setVgap(10);
		gridPaneAddHours.setHgap(10);
		gridPaneAddHours.setAlignment(Pos.CENTER);
		gridPaneAddHours.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneUpdateHours = new GridPane();
		gridPaneUpdateHours.setMinSize(500, 70);
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



		businessSplitPane = new SplitPane();
		businessSplitPane.setMinSize(1100, 600);
		businessSplitPane.setMaxSize(1100, 600);
		businessSplitPane.setOrientation(Orientation.VERTICAL);
		businessSplitPane.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneBusinessInfo.add(editBusinessInfo, 0, 0,2,1);
		gridPaneBusinessInfo.add(editBusinnessInfoInstruction, 0, 1,5,1);
		gridPaneBusinessInfo.add(addBusinessName, 0, 2);
		gridPaneBusinessInfo.add(addBusinessNameText, 1, 2); 
		gridPaneBusinessInfo.add(addAddress, 3, 2);
		gridPaneBusinessInfo.add(addAddressText,4,2);
		gridPaneBusinessInfo.add(addPhoneNumber, 0, 3);
		gridPaneBusinessInfo.add(addPhoneNumberText, 1, 3);
		gridPaneBusinessInfo.add(addEmail,3,3);
		gridPaneBusinessInfo.add(addEmailText,4,3);   
		gridPaneBusinessInfo.add(addBusinessButton, 2, 7);
		
		
		gridPaneAddHours.add(addNewBusinessHoursLabel, 0, 0,2,1);
		gridPaneAddHours.add(addHoursInstruction, 0, 1,5,1);
		gridPaneAddHours.add(addHoursDay, 0, 2);
		gridPaneAddHours.add(addHoursDayText, 1, 2); 
		gridPaneAddHours.add(addHoursStartTime, 3, 2);
		gridPaneAddHours.add(addHoursStartTimeText,4,2);
		gridPaneAddHours.add(addHoursEndTime,3,3);
		gridPaneAddHours.add(addHoursEndTimeText,4,3);   
		gridPaneAddHours.add(addHoursButton, 2, 7);

		
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
		

		verticalMenuBusinessInfo = new VBox();
		verticalMenuBusinessInfo.setPadding(new Insets(10));
		verticalMenuBusinessInfo.setSpacing(8);
		
		verticalMenuAddHours = new VBox();
		verticalMenuAddHours.setPadding(new Insets(10));
		verticalMenuAddHours.setSpacing(8);

		verticalMenuUpdateHours = new VBox();
		verticalMenuUpdateHours.setPadding(new Insets(10));
		verticalMenuUpdateHours.setSpacing(8);
		
		verticalMenuDeleteHours = new VBox();
		verticalMenuDeleteHours.setPadding(new Insets(10));
		verticalMenuDeleteHours.setSpacing(8);

		Text title = new Text("Business");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuBusinessInfo.getChildren().add(title);
		verticalMenuAddHours.getChildren().add(title);
		verticalMenuUpdateHours.getChildren().add(title);
		verticalMenuDeleteHours.getChildren().add(title);


		editBusinessInfoLink1 = new Hyperlink("View/Edit Business Information");
		addHoursLink1 = new Hyperlink("Add New Business Hours");
		updateHoursLink1 = new Hyperlink ("Update Business Hours");
		removeHoursLink1 = new Hyperlink ("Delete Business Hours");

		editBusinessInfoLink2 = new Hyperlink("View/Edit Business Information");
		addHoursLink2 = new Hyperlink("Add New Business Hours");
		updateHoursLink2 = new Hyperlink ("Update Business Hours");
		removeHoursLink2 = new Hyperlink ("Delete Business Hours");

		editBusinessInfoLink3 = new Hyperlink("View/Edit Business Information");
		addHoursLink3 = new Hyperlink("Add New Business Hours");
		updateHoursLink3 = new Hyperlink ("Update Business Hours");
		removeHoursLink3 = new Hyperlink ("Delete Business Hours");
		
		editBusinessInfoLink4 = new Hyperlink("View/Edit Business Information");
		addHoursLink4 = new Hyperlink("Add New Business Hours");
		updateHoursLink4 = new Hyperlink ("Update Business Hours");
		removeHoursLink4 = new Hyperlink ("Delete Business Hours");


		Hyperlink options1[] = new Hyperlink[] {
				editBusinessInfoLink1,
				addHoursLink1,
				updateHoursLink1};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options1[i], new Insets(0, 0, 0, 8));
			verticalMenuBusinessInfo.getChildren().add(options1[i]);
		}

		Hyperlink options2[] = new Hyperlink[] {
				editBusinessInfoLink2,
				addHoursLink2,
				updateHoursLink2};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options2[i], new Insets(0, 0, 0, 8));
			verticalMenuAddHours.getChildren().add(options2[i]);
		}

		Hyperlink options3[] = new Hyperlink[] {
				editBusinessInfoLink3,
				addHoursLink3,
				updateHoursLink3};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options3[i], new Insets(0, 0, 0, 8));
			verticalMenuUpdateHours.getChildren().add(options3[i]);
		}

		addBusinessInfoPane = new BorderPane();
		addBusinessInfoPane.setLeft(verticalMenuBusinessInfo);
		addBusinessInfoPane.setCenter(gridPaneBusinessInfo);

		updateBusinessBorderPane = new BorderPane();
		updateBusinessBorderPane.setLeft(verticalMenuAddHours);
		updateBusinessBorderPane.setCenter(gridPaneAddHours);

		cancelBusinessBorderPane = new BorderPane();
		cancelBusinessBorderPane.setLeft(verticalMenuUpdateHours);
		cancelBusinessBorderPane.setCenter(gridPaneUpdateHours);
		

		businessScene = new Scene(addBusinessInfoPane);
		updateBusinessScene = new Scene(updateBusinessBorderPane);
		cancelBusinessScene = new Scene(cancelBusinessBorderPane);


		editBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("Add a service");
			primaryStage.setScene(businessScene);
		});
		addHoursLink1.setOnAction(e->{
			primaryStage.setTitle("Update a service");
			primaryStage.setScene(updateBusinessScene);
		});

		updateHoursLink1.setOnAction(e->{
			primaryStage.setTitle("Update Business Hours");
			primaryStage.setScene(cancelBusinessScene);
		});  

		addHoursLink2.setOnAction(e->{
			primaryStage.setTitle("Add a service");
			primaryStage.setScene(businessScene);
		});

		addHoursLink2.setOnAction(e->{
			primaryStage.setTitle("Update a service");
			primaryStage.setScene(updateBusinessScene);
		});

		updateHoursLink2.setOnAction(e->{
			primaryStage.setTitle("Update Business Hours");
			primaryStage.setScene(cancelBusinessScene);
		});

		editBusinessInfoLink3.setOnAction(e->{
			primaryStage.setTitle("Add a service");
			primaryStage.setScene(businessScene);
		});

		addHoursLink3.setOnAction(e->{
			primaryStage.setTitle("Update a service");
			primaryStage.setScene(updateBusinessScene);
		});

		updateHoursLink3.setOnAction(e->{
			primaryStage.setTitle("Update Business Hours");
			primaryStage.setScene(cancelBusinessScene);
		});


		addBusinessButton.setOnAction(e->{
			try {
				FlexiBookController.SetUpContactInfo(addBusinessName.getText(), addAddress.getText(), addPhoneNumber.getText(), addEmail.getText());
				errorBusinessInfoMessage.setText("");
			} catch (InvalidInputException e1) {
				errorBusinessInfoMessage.setText(e1.getMessage());
			}
		});


		addHoursButton.setOnAction(e->{
			try {
				FlexiBookController.SetUpBusinessHours(DayOfWeek.valueOf(addHoursDay.getText()), Time.valueOf(addHoursStartTime.getText()), Time.valueOf(addHoursEndTime.getText()));
				errorAddHoursMessage.setText("");
			} catch (InvalidInputException e1) {
				errorAddHoursMessage.setText(e1.getMessage());
			}
		});

		updateHoursButton.setOnAction(e->{
			try {
				FlexiBookController.UpdateBusinessHours(DayOfWeek.valueOf(updateHoursOldDay.getText()), Time.valueOf(updateHoursOldTime.getText()), DayOfWeek.valueOf(updateHoursNewDay.getText()), Time.valueOf(updateHoursNewStartTime.getText()), Time.valueOf(updateHoursNewEndTime.getText()));
				errorUpdateHoursMessage.setText("");
			} catch (InvalidInputException e1) {
				errorUpdateHoursMessage.setText(e1.getMessage());
			}
		});
		
		
		primaryStage.setScene(businessScene);
		primaryStage.show();

	}
}