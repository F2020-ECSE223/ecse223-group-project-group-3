package ca.mcgill.ecse.flexibook.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour;
import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour.TODayOfWeek;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class BusinessHours extends Application {

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



	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
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
		addHoursDayText = new ComboBox();
		addHoursDayText.getItems().add("Monday");
		addHoursDayText.getItems().add("Tuesday");
		addHoursDayText.getItems().add("Wednesday");
		addHoursDayText.getItems().add("Thsursday");
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
		updateHoursOldDayText = new ComboBox();
		updateHoursOldDayText.getItems().add("Monday");
		updateHoursOldDayText.getItems().add("Tuesday");
		updateHoursOldDayText.getItems().add("Wednesday");
		updateHoursOldDayText.getItems().add("Thsursday");
		updateHoursOldDayText.getItems().add("Friday");
		updateHoursOldDayText.getItems().add("Saturday");
		updateHoursOldDayText.getItems().add("Sunday");
		updateHoursOldDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		updateHoursOldTime = new Text("Current Start Time: ");
		updateHoursOldTimeText = new TextField();
		updateHoursOldTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateHoursOldTimeText.setPromptText("ex: 00:00");
		
		updateHoursNewDay = new Text("New Day of Week: ");
		updateHoursNewDayText = new ComboBox();
		updateHoursNewDayText.getItems().add("Monday");
		updateHoursNewDayText.getItems().add("Tuesday");
		updateHoursNewDayText.getItems().add("Wednesday");
		updateHoursNewDayText.getItems().add("Thsursday");
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
		deleteHoursDayText = new ComboBox();
		deleteHoursDayText.getItems().add("Monday");
		deleteHoursDayText.getItems().add("Tuesday");
		deleteHoursDayText.getItems().add("Wednesday");
		deleteHoursDayText.getItems().add("Thsursday");
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
		
		verticalMenuaViewHours = new VBox();
		verticalMenuaViewHours.setPadding(new Insets(10));
		verticalMenuaViewHours.setSpacing(8);

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
		businessHoursBorderPane.setCenter(viewBusinessHourTable);

		businessHoursScene = new Scene(businessHoursBorderPane);


		viewHoursLink1.setOnAction(e->{
			primaryStage.setTitle("View Existing Business Hours");
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


		addHoursButton.setOnAction(e->{
			try {
				FlexiBookController.SetUpBusinessHours(DayOfWeek.valueOf((String) addHoursDayText.getSelectionModel().getSelectedItem()), Time.valueOf(addHoursStartTimeText.getText()+":00"), Time.valueOf(addHoursEndTimeText.getText()+":00"));
				errorAddHoursMessage.setText("");
			} catch (InvalidInputException e1) {
				errorAddHoursMessage.setText(e1.getMessage());
			}
		});

		updateHoursButton.setOnAction(e->{
			try {
				FlexiBookController.UpdateBusinessHours(DayOfWeek.valueOf((String) updateHoursOldDayText.getSelectionModel().getSelectedItem()), Time.valueOf(updateHoursOldTimeText.getText()+":00"), DayOfWeek.valueOf((String) updateHoursNewDayText.getSelectionModel().getSelectedItem()), Time.valueOf(updateHoursNewStartTimeText.getText()+":00"), Time.valueOf(updateHoursNewEndTimeText.getText()+":00"));
				errorUpdateHoursMessage.setText("");
			} catch (InvalidInputException e1) {
				errorUpdateHoursMessage.setText(e1.getMessage());
			}
		});

		deleteHoursButton.setOnAction(e->{
			try {
				FlexiBookController.RemoveBusinessHours(DayOfWeek.valueOf((String) deleteHoursDayText.getSelectionModel().getSelectedItem()), Time.valueOf(deleteHoursTimeText.getText()+":00"));
			} catch (InvalidInputException e1) {
				errorDeleteHoursMessage.setText(e1.getMessage());
			}
		});
		
		primaryStage.setScene(businessHoursScene);
		primaryStage.show();

	}
	private ObservableList<TOBusinessHour> getBusinessHourData() {
		ObservableList<TOBusinessHour> businessHourList = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getBusinessHours().size(); i++) {
			businessHourList.add(FlexiBookController.getBusinessHours().get(i));
		}
		return businessHourList;
	}

}
