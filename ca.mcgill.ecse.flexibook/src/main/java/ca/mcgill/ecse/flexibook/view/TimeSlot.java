package ca.mcgill.ecse.flexibook.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
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
import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour;
import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour.TODayOfWeek;
import ca.mcgill.ecse223.flexibook.controller.TOTimeSlot;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class TimeSlot extends Application {

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
	private ComboBox addTimeSlotTypeText;
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
	private ComboBox updateTimeSlotTypeText;
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
	private ComboBox deleteTimeSlotTypeText;
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
	private Hyperlink addTimeSlotLink2;
	private Hyperlink updateTimeSlotLink2;
	private Hyperlink deleteTimeSlotLink2;
	private Hyperlink timeSlotGoBackLink2;
	private Hyperlink timeSlotMainMenuLink2;
	private Hyperlink addTimeSlotLink3;
	private Hyperlink updateTimeSlotLink3;
	private Hyperlink deleteTimeSlotLink3;
	private Hyperlink timeSlotGoBackLink3;
	private Hyperlink timeSlotMainMenuLink3;

	private BorderPane addTimeSlotBorderPane;
	private BorderPane updateTimeSlotBorderPane;
	private BorderPane deleteTimeSlotBorderPane;

	private Scene addTimeSlotScene;
	private Scene updateTimeSlotScene;
	private Scene deleteTimeSlotScene;


	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// View Holidays	
		
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
//		viewHolidayTable.setItems(getHolidayData());
		viewHolidayTable.getColumns().addAll(startDateHolidayCol, startTimeHolidayCol, endDateHolidayCol, endTimeHolidayCol);
		
		// View Vacations	
		
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
//		viewVacationTable.setItems(getVacationData());
		viewVacationTable.getColumns().addAll(startDateVacationCol, startTimeVacationCol, endDateVacationCol, endTimeVacationCol);

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
		addTimeSlotTypeText = new ComboBox();
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

		updateTimeSlot = new Text("Edit Existing Time Slot");
		updateTimeSlot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		updateTimeSlot.setFill(Color.BLUE);
		
		updateTimeSlotInstruction = new Text("Please enter the following information for the time slot you wish to update.");
		updateTimeSlotInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		updateTimeSlotType = new Text("Type: ");
		updateTimeSlotTypeText = new ComboBox();
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
		deleteTimeSlotTypeText = new ComboBox();
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

		Text title = new Text("Time Slots");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text title2 = new Text("Time Slots");
		title2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text title3 = new Text("Time Slots");
		title3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuaddTimeSlot.getChildren().add(title);
		verticalMenuupdateTimeSlot.getChildren().add(title2);
		verticalMenuDeleteTimeSlot.getChildren().add(title3);


		addTimeSlotLink1 = new Hyperlink("Add New Time Slot");
		updateTimeSlotLink1 = new Hyperlink("Update Existing Time Slot");
		deleteTimeSlotLink1 = new Hyperlink ("Delete Existing Time Slot");
		timeSlotGoBackLink1 = new Hyperlink ("Go Back");
		timeSlotMainMenuLink1 = new Hyperlink ("Main Menu");

		addTimeSlotLink2 = new Hyperlink("Add New Time Slot");
		updateTimeSlotLink2 = new Hyperlink("Update Existing Time Slot");
		deleteTimeSlotLink2 = new Hyperlink ("Delete Existing Time Slot");
		timeSlotGoBackLink2 = new Hyperlink ("Go Back");
		timeSlotMainMenuLink2 = new Hyperlink ("Main Menu");

		addTimeSlotLink3 = new Hyperlink("Add New Time Slot");
		updateTimeSlotLink3 = new Hyperlink("Update Existing Time Slot");
		deleteTimeSlotLink3 = new Hyperlink ("Delete Existing Time Slot");
		timeSlotGoBackLink3 = new Hyperlink ("Go Back");
		timeSlotMainMenuLink3 = new Hyperlink ("Main Menu");

		Hyperlink opt1[] = new Hyperlink[] {
				addTimeSlotLink1,
				updateTimeSlotLink1,
				deleteTimeSlotLink1,
				timeSlotGoBackLink1,
				timeSlotMainMenuLink1};

		for (int i=0; i<5; i++) {
			VBox.setMargin(opt1[i], new Insets(0, 0, 0, 8));
			verticalMenuaddTimeSlot.getChildren().add(opt1[i]);
		}

		Hyperlink opt2[] = new Hyperlink[] {
				addTimeSlotLink2,
				updateTimeSlotLink2,
				deleteTimeSlotLink2,
				timeSlotGoBackLink2,
				timeSlotMainMenuLink2};

		for (int i=0; i<5; i++) {
			VBox.setMargin(opt2[i], new Insets(0, 0, 0, 8));
			verticalMenuupdateTimeSlot.getChildren().add(opt2[i]);
		}

		Hyperlink opt3[] = new Hyperlink[] {
				addTimeSlotLink3,
				updateTimeSlotLink3,
				deleteTimeSlotLink3,
				timeSlotGoBackLink3,
				timeSlotMainMenuLink3};

		for (int i=0; i<5; i++) {
			VBox.setMargin(opt3[i], new Insets(0, 0, 0, 8));
			verticalMenuDeleteTimeSlot.getChildren().add(opt3[i]);
		}

		addTimeSlotBorderPane = new BorderPane();
		addTimeSlotBorderPane.setLeft(verticalMenuaddTimeSlot);
		addTimeSlotBorderPane.setCenter(gridPaneaddTimeSlot);

		updateTimeSlotBorderPane = new BorderPane();
		updateTimeSlotBorderPane.setLeft(verticalMenuupdateTimeSlot);
		updateTimeSlotBorderPane.setCenter(gridPaneupdateTimeSlot);

		deleteTimeSlotBorderPane = new BorderPane();
		deleteTimeSlotBorderPane.setLeft(verticalMenuDeleteTimeSlot);
		deleteTimeSlotBorderPane.setCenter(gridPanedeleteTimeSlot);

		addTimeSlotScene = new Scene(addTimeSlotBorderPane);
		updateTimeSlotScene = new Scene(updateTimeSlotBorderPane);
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
				FlexiBookController.AddaNewTimeSlot((String) addTimeSlotTypeText.getSelectionModel().getSelectedItem(), Date.valueOf(addTimeSlotStartDateText.getText()), Time.valueOf(addTimeSlotStartTimeText.getText()+":00"), Date.valueOf(addTimeSlotEndDateText.getText()), Time.valueOf(addTimeSlotEndTimeText.getText()+":00"));
				erroraddTimeSlotMessage.setText("");
			} catch (InvalidInputException e1) {
				erroraddTimeSlotMessage.setText(e1.getMessage());
				gridPaneaddTimeSlot.add(erroraddTimeSlotMessage, 2, 3);
			}
			catch(RuntimeException a) {
				erroraddTimeSlotMessage.setText("Invalid Inputs");
				gridPaneaddTimeSlot.add(erroraddTimeSlotMessage, 3, 8);
			}

		});

		updateTimeSlotButton.setOnAction(e->{
			try {
				FlexiBookController.UpdateHolidayOrVacation((String) updateTimeSlotTypeText.getSelectionModel().getSelectedItem(), Date.valueOf(updateTimeSlotOldDateText.getText()), Time.valueOf(updateTimeSlotOldTimeText.getText()+":00"), Date.valueOf(updateTimeSlotNewStartDateText.getText()), Time.valueOf(updateTimeSlotNewStartTimeText.getText()+":00"), Date.valueOf(updateTimeSlotNewEndDateText.getText()), Time.valueOf(updateTimeSlotNewEndTimeText.getText()+":00"));
				errorupdateTimeSlotMessage.setText("");
			} catch (InvalidInputException e1) {
				errorupdateTimeSlotMessage.setText(e1.getMessage());
			}
		});

		deleteTimeSlotButton.setOnAction(e->{
			try {
				FlexiBookController.RemoveTimeSlot((String) deleteTimeSlotTypeText.getSelectionModel().getSelectedItem(), Date.valueOf(deleteTimeSlotStartDateText.getText()), Time.valueOf(deleteTimeSlotStartTimeText.getText()+":00"), Date.valueOf(deleteTimeSlotEndDateText.getText()), Time.valueOf(deleteTimeSlotEndTimeText.getText()+":00"));
			} catch (InvalidInputException e1) {
				errordeleteTimeSlotMessage.setText(e1.getMessage());
			}
		});
		
		primaryStage.setScene(addTimeSlotScene);
		primaryStage.show();

	}

}
