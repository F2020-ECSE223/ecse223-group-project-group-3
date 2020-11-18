package ca.mcgill.ecse223.flexibook.view;

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

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class TimeSlot extends Application {

	//error messages
	private Text erroraddTimeSlotMessage;
	private Text errorupdateTimeSlotMessage;
	private Text errordeleteTimeSlotMessage;
	
	// Add Time Slot
	//------------------------------------------------------------------------------------------------	
	
	private Text addTimeSlot;
	private Text addTimeSlotInstruction;
	private Text addTimeSlotType;
	private TextField addTimeSlotTypeText;
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
	private TextField updateTimeSlotTypeText;
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
	private TextField deleteTimeSlotTypeText;
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

	private SplitPane splitPane;
	private VBox verticalMenuaddTimeSlot;
	private VBox verticalMenuupdateTimeSlot;
	private VBox verticalMenuCancelService;
	private Hyperlink addTimeSlotLink1;
	private Hyperlink updateTimeSlotLink1;
	private Hyperlink deleteTimeSlotLink1;
	private Hyperlink addTimeSlotLink2;
	private Hyperlink updateTimeSlotLink2;
	private Hyperlink deleteTimeSlotLink2;
	private Hyperlink addTimeSlotLink3;
	private Hyperlink updateTimeSlotLink3;
	private Hyperlink deleteTimeSlotLink3;

	private BorderPane addTimeSlotBorderPane;
	private BorderPane updateServiceBorderPane;
	private BorderPane deleteTimeSlotBorderPane;

	private Scene addTimeSlotScene;
	private Scene updateTimeSlotScene;
	private Scene deleteTimeSlotScene;


	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Add Time Slot
		//--------------------------------------------------------------------------------------------

		addTimeSlot = new Text("Add New Time Slot");
		addTimeSlot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		addTimeSlot.setFill(Color.BLUE);
		addTimeSlotInstruction = new Text("Please enter the following for new time slot.");
		addTimeSlotInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		erroraddTimeSlotMessage = new Text("");
		erroraddTimeSlotMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		erroraddTimeSlotMessage.setFill(Color.RED);


		addTimeSlotType = new Text("Type: ");
		addTimeSlotTypeText = new TextField();
		addTimeSlotType.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	
		addTimeSlotTypeText.setPromptText("holiday or vacation");

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

		updateTimeSlot = new Text("Edit Existing Time Slot");
		updateTimeSlot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		updateTimeSlot.setFill(Color.BLUE);
		
		updateTimeSlotInstruction = new Text("Please enter the following information for the time slot you wish to update.");
		updateTimeSlotInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		updateTimeSlotType = new Text("Type: ");
		updateTimeSlotTypeText = new TextField();
		updateTimeSlotType.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	
		updateTimeSlotTypeText.setPromptText("holiday or vacation");

		updateTimeSlotOldDate = new Text("Current Start Date: ");
		updateTimeSlotOldDateText = new TextField();
		updateTimeSlotOldDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotOldDateText.setPromptText("ex: Monday, Tuesday, etc");
		
		updateTimeSlotOldTime = new Text("Current Start Time: ");
		updateTimeSlotOldTimeText = new TextField();
		updateTimeSlotOldTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotOldTimeText.setPromptText("ex: 00:00");
		
		updateTimeSlotNewStartDate = new Text("New Start Date: ");
		updateTimeSlotNewStartDateText = new TextField();
		updateTimeSlotNewStartDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotNewStartDateText.setPromptText("ex: Monday, Tuesday, etc");
		
		updateTimeSlotNewEndDate = new Text("New Start Date: ");
		updateTimeSlotNewEndDateText = new TextField();
		updateTimeSlotNewEndDate.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateTimeSlotNewEndDateText.setPromptText("ex: Monday, Tuesday, etc");
		
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


		// Delete Business Hours
		//-----------------------------------------------------------------------------------------------------------

		deleteTimeSlot = new Text("Delete Existing Business Hours");
		deleteTimeSlot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		deleteTimeSlot.setFill(Color.BLUE);

		deleteTimeSlotInstruction = new Text("Please enter the following for the time slot you would like to delete.");
		deleteTimeSlotInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		deleteTimeSlotType = new Text("Type: ");
		deleteTimeSlotTypeText = new TextField();
		deleteTimeSlotType.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	
		deleteTimeSlotTypeText.setPromptText("holiday or vacation");

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

		splitPane = new SplitPane();
		splitPane.setMinSize(1100, 600);
		splitPane.setMaxSize(1100, 600);
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setStyle("-fx-background-color: LIGHTBLUE;");
		
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


		verticalMenuaddTimeSlot = new VBox();
		verticalMenuaddTimeSlot.setPadding(new Insets(10));
		verticalMenuaddTimeSlot.setSpacing(8);

		verticalMenuupdateTimeSlot = new VBox();
		verticalMenuupdateTimeSlot.setPadding(new Insets(10));
		verticalMenuupdateTimeSlot.setSpacing(8);

		verticalMenuCancelService = new VBox();
		verticalMenuCancelService.setPadding(new Insets(10));
		verticalMenuCancelService.setSpacing(8);

		Text title = new Text("Time Slots");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text title2 = new Text("Time Slots");
		title2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text title3 = new Text("Time Slots");
		title3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuaddTimeSlot.getChildren().add(title);
		verticalMenuupdateTimeSlot.getChildren().add(title2);
		verticalMenuCancelService.getChildren().add(title3);


		addTimeSlotLink1 = new Hyperlink("Add New Time Slot");
		updateTimeSlotLink1 = new Hyperlink("Update Existing Time Slot");
		deleteTimeSlotLink1 = new Hyperlink ("Delete Existing Time Slot");

		addTimeSlotLink2 = new Hyperlink("Add New Time Slot");
		updateTimeSlotLink2 = new Hyperlink("Update Existing Time Slot");
		deleteTimeSlotLink2 = new Hyperlink ("Delete Existing Time Slot");

		addTimeSlotLink3 = new Hyperlink("Add New Time Slot");
		updateTimeSlotLink3 = new Hyperlink("Update Existing Time Slot");
		deleteTimeSlotLink3 = new Hyperlink ("Delete Existing Time Slot");

		Hyperlink options1[] = new Hyperlink[] {
				addTimeSlotLink1,
				updateTimeSlotLink1,
				deleteTimeSlotLink1};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options1[i], new Insets(0, 0, 0, 8));
			verticalMenuaddTimeSlot.getChildren().add(options1[i]);
		}

		Hyperlink options2[] = new Hyperlink[] {
				addTimeSlotLink2,
				updateTimeSlotLink2,
				deleteTimeSlotLink2};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options2[i], new Insets(0, 0, 0, 8));
			verticalMenuupdateTimeSlot.getChildren().add(options2[i]);
		}

		Hyperlink options3[] = new Hyperlink[] {
				addTimeSlotLink3,
				updateTimeSlotLink3,
				deleteTimeSlotLink3};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options3[i], new Insets(0, 0, 0, 8));
			verticalMenuCancelService.getChildren().add(options3[i]);
		}

		addTimeSlotBorderPane = new BorderPane();
		addTimeSlotBorderPane.setLeft(verticalMenuaddTimeSlot);
		addTimeSlotBorderPane.setCenter(gridPaneaddTimeSlot);

		updateServiceBorderPane = new BorderPane();
		updateServiceBorderPane.setLeft(verticalMenuupdateTimeSlot);
		updateServiceBorderPane.setCenter(gridPaneupdateTimeSlot);

		deleteTimeSlotBorderPane = new BorderPane();
		deleteTimeSlotBorderPane.setLeft(verticalMenuCancelService);
		deleteTimeSlotBorderPane.setCenter(gridPanedeleteTimeSlot);

		addTimeSlotScene = new Scene(addTimeSlotBorderPane);
		updateTimeSlotScene = new Scene(updateServiceBorderPane);
		deleteTimeSlotScene = new Scene(deleteTimeSlotBorderPane);


		addTimeSlotLink1.setOnAction(e->{
			primaryStage.setTitle("Add New Business Hours");
			primaryStage.setScene(addTimeSlotScene);
		});
		updateTimeSlotLink1.setOnAction(e->{
			primaryStage.setTitle("Update Existing Business Hours");
			primaryStage.setScene(updateTimeSlotScene);
		});

		deleteTimeSlotLink1.setOnAction(e->{
			primaryStage.setTitle("Delete Existing Business Hours");
			primaryStage.setScene(deleteTimeSlotScene);
		});  

		addTimeSlotLink2.setOnAction(e->{
			primaryStage.setTitle("Add New Business Hours");
			primaryStage.setScene(addTimeSlotScene);
		});

		updateTimeSlotLink2.setOnAction(e->{
			primaryStage.setTitle("Update Existing Business Hours");
			primaryStage.setScene(updateTimeSlotScene);
		});

		deleteTimeSlotLink2.setOnAction(e->{
			primaryStage.setTitle("Delete Existing Business Hours");
			primaryStage.setScene(deleteTimeSlotScene);
		});

		addTimeSlotLink3.setOnAction(e->{
			primaryStage.setTitle("Add New Business Hours");
			primaryStage.setScene(addTimeSlotScene);
		});

		updateTimeSlotLink3.setOnAction(e->{
			primaryStage.setTitle("Update Existing Business Hours");
			primaryStage.setScene(updateTimeSlotScene);
		});

		deleteTimeSlotLink3.setOnAction(e->{
			primaryStage.setTitle("Delete Existing Business Hours");
			primaryStage.setScene(deleteTimeSlotScene);
		});


		addTimeSlotButton.setOnAction(e->{
			try {
				FlexiBookController.AddaNewTimeSlot(addTimeSlotTypeText.getText(), Date.valueOf(addTimeSlotStartDateText.getText()), Time.valueOf(addTimeSlotStartTimeText.getText()+":00"), Date.valueOf(addTimeSlotEndDateText.getText()), Time.valueOf(addTimeSlotEndTimeText.getText()+":00"));
				erroraddTimeSlotMessage.setText("");
			} catch (InvalidInputException e1) {
				erroraddTimeSlotMessage.setText(e1.getMessage());
			}
		});

		updateTimeSlotButton.setOnAction(e->{
			try {
				FlexiBookController.UpdateHolidayOrVacation(updateTimeSlotTypeText.getText(), Date.valueOf(updateTimeSlotOldDateText.getText()), Time.valueOf(updateTimeSlotOldTimeText.getText()+":00"), Date.valueOf(updateTimeSlotNewStartDateText.getText()), Time.valueOf(updateTimeSlotNewStartTimeText.getText()+":00"), Date.valueOf(updateTimeSlotNewEndDateText.getText()), Time.valueOf(updateTimeSlotNewEndTimeText.getText()+":00"));
				errorupdateTimeSlotMessage.setText("");
			} catch (InvalidInputException e1) {
				errorupdateTimeSlotMessage.setText(e1.getMessage());
			}
		});

		deleteTimeSlotButton.setOnAction(e->{
			try {
				FlexiBookController.RemoveTimeSlot(deleteTimeSlotTypeText.getText(), Date.valueOf(deleteTimeSlotStartDateText.getText()), Time.valueOf(deleteTimeSlotStartTimeText.getText()+":00"), Date.valueOf(deleteTimeSlotEndDateText.getText()), Time.valueOf(deleteTimeSlotEndTimeText.getText()+":00"));
			} catch (InvalidInputException e1) {
				errordeleteTimeSlotMessage.setText(e1.getMessage());
			}
		});
		
		primaryStage.setScene(addTimeSlotScene);
		primaryStage.show();

	}

}
