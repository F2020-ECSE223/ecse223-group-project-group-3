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

import java.sql.Time;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class BusinessHours extends Application {

	//error messages
	private Text errorAddHoursMessage;
	private Text errorUpdateHoursMessage;
	private Text errorDeleteHoursMessage;
	
	// Add Hours
	//------------------------------------------------------------------------------------------------	
	
	private Text addHours;
	private Text addHoursInstruction;
	private Text addHoursDay;
	private TextField addHoursDayText;
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

	// Delete Hours
	//-------------------------------------------------------------------------------	
	private Text deleteHoursLabel;
	private Text deleteHoursInstruction;
	private Text deleteHoursDay;
	private TextField deleteHoursDayText;
	private Text deleteHoursTime;
	private TextField deleteHoursTimeText;

	private Button deleteHoursButton;


	//Grid pane
	private GridPane gridPaneaddHours;
	private GridPane gridPaneUpdateHours;
	private GridPane gridPaneDeleteHours;

	private SplitPane splitPane;
	private VBox verticalMenuaddHours;
	private VBox verticalMenuUpdateHours;
	private VBox verticalMenuCancelService;
	private Hyperlink addHoursLink1;
	private Hyperlink updateHoursLink1;
	private Hyperlink deleteHoursLink1;
	private Hyperlink addHoursLink2;
	private Hyperlink updateHoursLink2;
	private Hyperlink deleteHoursLink2;
	private Hyperlink addHoursLink3;
	private Hyperlink updateHoursLink3;
	private Hyperlink deleteHoursLink3;

	private BorderPane addHoursBorderPane;
	private BorderPane updateServiceBorderPane;
	private BorderPane deleteHoursBorderPane;

	private Scene addHoursScene;
	private Scene updateHoursScene;
	private Scene deleteHoursScene;


	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {

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
		addHoursDayText = new TextField();
		addHoursDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	
		addHoursDayText.setPromptText("ex: Monday, Tuesday, etc");

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


		// Delete Business Hours
		//-----------------------------------------------------------------------------------------------------------

		deleteHoursLabel = new Text("Delete Existing Business Hours");
		deleteHoursLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		deleteHoursLabel.setFill(Color.BLUE);

		deleteHoursInstruction = new Text("Please enter the following for the business hours you would like to delete.");
		deleteHoursInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		deleteHoursDay = new Text("Day of Week: ");
		deleteHoursDayText = new TextField();
		deleteHoursDay.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		deleteHoursDayText.setPromptText("ex: Monday, Tuesday, etc");
		
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

		splitPane = new SplitPane();
		splitPane.setMinSize(1100, 600);
		splitPane.setMaxSize(1100, 600);
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setStyle("-fx-background-color: LIGHTBLUE;");

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

		verticalMenuCancelService = new VBox();
		verticalMenuCancelService.setPadding(new Insets(10));
		verticalMenuCancelService.setSpacing(8);

		Text title = new Text("Business Hours");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text title2 = new Text("Business Hours");
		title2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Text title3 = new Text("Business Hours");
		title3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuaddHours.getChildren().add(title);
		verticalMenuUpdateHours.getChildren().add(title2);
		verticalMenuCancelService.getChildren().add(title3);


		addHoursLink1 = new Hyperlink("Add New Business Hours");
		updateHoursLink1 = new Hyperlink("Update Existing Business Hours");
		deleteHoursLink1 = new Hyperlink ("Delete Existing Business Hours");

		addHoursLink2 = new Hyperlink("Add New Business Hours");
		updateHoursLink2 = new Hyperlink("Update Existing Business Hours");
		deleteHoursLink2 = new Hyperlink ("Delete Existing Business Hours");

		addHoursLink3 = new Hyperlink("Add New Business Hours");
		updateHoursLink3 = new Hyperlink("Update Existing Business Hours");
		deleteHoursLink3 = new Hyperlink ("Delete Existing Business Hours");

		Hyperlink options1[] = new Hyperlink[] {
				addHoursLink1,
				updateHoursLink1,
				deleteHoursLink1};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options1[i], new Insets(0, 0, 0, 8));
			verticalMenuaddHours.getChildren().add(options1[i]);
		}

		Hyperlink options2[] = new Hyperlink[] {
				addHoursLink2,
				updateHoursLink2,
				deleteHoursLink2};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options2[i], new Insets(0, 0, 0, 8));
			verticalMenuUpdateHours.getChildren().add(options2[i]);
		}

		Hyperlink options3[] = new Hyperlink[] {
				addHoursLink3,
				updateHoursLink3,
				deleteHoursLink3};

		for (int i=0; i<3; i++) {
			VBox.setMargin(options3[i], new Insets(0, 0, 0, 8));
			verticalMenuCancelService.getChildren().add(options3[i]);
		}

		addHoursBorderPane = new BorderPane();
		addHoursBorderPane.setLeft(verticalMenuaddHours);
		addHoursBorderPane.setCenter(gridPaneaddHours);

		updateServiceBorderPane = new BorderPane();
		updateServiceBorderPane.setLeft(verticalMenuUpdateHours);
		updateServiceBorderPane.setCenter(gridPaneUpdateHours);

		deleteHoursBorderPane = new BorderPane();
		deleteHoursBorderPane.setLeft(verticalMenuCancelService);
		deleteHoursBorderPane.setCenter(gridPaneDeleteHours);

		addHoursScene = new Scene(addHoursBorderPane);
		updateHoursScene = new Scene(updateServiceBorderPane);
		deleteHoursScene = new Scene(deleteHoursBorderPane);


		addHoursLink1.setOnAction(e->{
			primaryStage.setTitle("Add New Business Hours");
			primaryStage.setScene(addHoursScene);
		});
		updateHoursLink1.setOnAction(e->{
			primaryStage.setTitle("Update Existing Business Hours");
			primaryStage.setScene(updateHoursScene);
		});

		deleteHoursLink1.setOnAction(e->{
			primaryStage.setTitle("Delete Existing Business Hours");
			primaryStage.setScene(deleteHoursScene);
		});  

		addHoursLink2.setOnAction(e->{
			primaryStage.setTitle("Add New Business Hours");
			primaryStage.setScene(addHoursScene);
		});

		updateHoursLink2.setOnAction(e->{
			primaryStage.setTitle("Update Existing Business Hours");
			primaryStage.setScene(updateHoursScene);
		});

		deleteHoursLink2.setOnAction(e->{
			primaryStage.setTitle("Delete Existing Business Hours");
			primaryStage.setScene(deleteHoursScene);
		});

		addHoursLink3.setOnAction(e->{
			primaryStage.setTitle("Add New Business Hours");
			primaryStage.setScene(addHoursScene);
		});

		updateHoursLink3.setOnAction(e->{
			primaryStage.setTitle("Update Existing Business Hours");
			primaryStage.setScene(updateHoursScene);
		});

		deleteHoursLink3.setOnAction(e->{
			primaryStage.setTitle("Delete Existing Business Hours");
			primaryStage.setScene(deleteHoursScene);
		});


		addHoursButton.setOnAction(e->{
			try {
				FlexiBookController.SetUpBusinessHours(DayOfWeek.valueOf(addHoursDayText.getText()), Time.valueOf(addHoursStartTimeText.getText()+":00"), Time.valueOf(addHoursEndTimeText.getText()+":00"));
				errorAddHoursMessage.setText("");
			} catch (InvalidInputException e1) {
				errorAddHoursMessage.setText(e1.getMessage());
			}
		});

		updateHoursButton.setOnAction(e->{
			try {
				FlexiBookController.UpdateBusinessHours(DayOfWeek.valueOf(updateHoursOldDayText.getText()), Time.valueOf(updateHoursOldTimeText.getText()+":00"), DayOfWeek.valueOf(updateHoursNewDayText.getText()), Time.valueOf(updateHoursNewStartTimeText.getText()+":00"), Time.valueOf(updateHoursNewEndTimeText.getText()+":00"));
				errorUpdateHoursMessage.setText("");
			} catch (InvalidInputException e1) {
				errorUpdateHoursMessage.setText(e1.getMessage());
			}
		});

		deleteHoursButton.setOnAction(e->{
			try {
				FlexiBookController.RemoveBusinessHours(DayOfWeek.valueOf(deleteHoursDayText.getText()), Time.valueOf(deleteHoursTimeText.getText()+":00"));
			} catch (InvalidInputException e1) {
				errorDeleteHoursMessage.setText(e1.getMessage());
			}
		});
		
		primaryStage.setScene(addHoursScene);
		primaryStage.show();

	}

}
