package ca.mcgill.ecse.flexibook.view;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import ca.mcgill.ecse223.flexibook.controller.TOTimeSlot;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewAppointmentCalendarPage extends Application {
	
	private Stage window;
	private Scene scene;

	private TableView<TOTimeSlot> avTimeSlots;
	private TableView<TOTimeSlot> unavTimeSlots;
	private Text viewTSDate;
	private DatePicker viewTSDatePicker;
	private HBox topTable;
	private ToggleButton dailyToggleButton;
	private ToggleButton weeklyToggleButton;
	private ToggleGroup toggleGroup;
	private HBox timeSlotTables;
	private BorderPane viewAppCalPane;
	private TableColumn availableTSCol;
	private TableColumn<TOTimeSlot, Time> availableStartTimeCol;
	private TableColumn<TOTimeSlot, Date> availableDateCol;
	private TableColumn<TOTimeSlot, Time> availableEndTimeCol;
	private TableColumn unavailableTSCol;
	private TableColumn<TOTimeSlot, Date> unavailableDateCol;
	private TableColumn<TOTimeSlot, Time> unavailableStartTimeCol;
	private TableColumn<TOTimeSlot, Time> unavailableEndTimeCol;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("Appointment Calendar");
		

		viewTSDate = new Text("Date: ");
		viewTSDatePicker = new DatePicker();
		viewTSDatePicker.setPromptText("dd-mm-yyyy");
		dailyToggleButton = new ToggleButton("Daily view");
		dailyToggleButton.setSelected(true);
		weeklyToggleButton = new ToggleButton("weekly view");
		toggleGroup = new ToggleGroup();
		
		dailyToggleButton.setToggleGroup(toggleGroup);
		weeklyToggleButton.setToggleGroup(toggleGroup);
		
		topTable = new HBox(viewTSDate, viewTSDatePicker, dailyToggleButton, weeklyToggleButton);
		topTable.setAlignment(Pos.CENTER);
		
		
		availableTSCol = new TableColumn ("Available Time Slots");
		
		availableDateCol = new TableColumn<TOTimeSlot, Date>("Date");
		availableDateCol.setMinWidth(150);
		availableDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

		availableStartTimeCol = new TableColumn<TOTimeSlot, Time>("Start Time");
		availableStartTimeCol.setMinWidth(150);
		availableStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		availableEndTimeCol = new TableColumn<TOTimeSlot, Time>("End Time");
		availableEndTimeCol.setMinWidth(150);
		availableEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		unavailableTSCol = new TableColumn ("Unavailable Time Slots");
		
		unavailableDateCol = new TableColumn<TOTimeSlot, Date>("Date");
		unavailableDateCol.setMinWidth(150);
		unavailableDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

		unavailableStartTimeCol = new TableColumn<TOTimeSlot, Time>("Start Time");
		unavailableStartTimeCol.setMinWidth(150);
		unavailableStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		unavailableEndTimeCol = new TableColumn<TOTimeSlot, Time>("End Time");
		unavailableEndTimeCol.setMinWidth(150);
		unavailableEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

		availableTSCol.getColumns().addAll(availableDateCol, availableStartTimeCol, availableEndTimeCol);
		avTimeSlots = new TableView<TOTimeSlot>();
		avTimeSlots.setItems(getAvTimeSlotData());
		avTimeSlots.getColumns().addAll(availableTSCol);
		
		for(int i =0; i<availableTSCol.getColumns().size(); i++) {
			 ((TableColumn) availableTSCol.getColumns().get(i)).setStyle("-fx-background-color:lightgreen");
		}
		
		
		
		unavailableTSCol.getColumns().addAll(unavailableDateCol, unavailableStartTimeCol, unavailableEndTimeCol);
		unavTimeSlots = new TableView<TOTimeSlot>();
		unavTimeSlots.setItems(getUnavTimeSlotData());
		unavTimeSlots.getColumns().addAll(unavailableTSCol);
		
		for(int i =0; i<unavailableTSCol.getColumns().size(); i++) {
			 ((TableColumn) unavailableTSCol.getColumns().get(i)).setStyle("-fx-background-color:lightpink");
		
		}
		
		timeSlotTables = new HBox();
		timeSlotTables.getChildren().addAll(avTimeSlots, unavTimeSlots);
		
		viewAppCalPane = new BorderPane();
		viewAppCalPane.setTop(topTable);
		viewAppCalPane.setCenter(timeSlotTables);
		
		viewTSDatePicker.setOnAction(e->{
			refreshTimeSlots();

		});
		
		dailyToggleButton.setOnAction(e->{
			refreshTimeSlots();
			});
		
		weeklyToggleButton.setOnAction(e->{
			refreshTimeSlots();		
		    });
		
		scene = new Scene(viewAppCalPane, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
	private ObservableList<TOTimeSlot> getAvTimeSlotData() {
		TOAppointmentCalendarItem item = null;
		String date = null;
		if(viewTSDatePicker.getValue()!=null) {
		date = viewTSDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		else date = LocalDate.now().toString();

		try {
			item = FlexiBookController.viewAppointmentCalendar("rico", date, dailyToggleButton.isSelected());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<TOTimeSlot> list = FXCollections.observableArrayList();
		
		for(int i = 0; i<item.getAvailableTimeSlots().size(); i++) {
			list.add(item.getAvailableTimeSlot(i));

		}
		return list;
	}
	
	private ObservableList<TOTimeSlot> getUnavTimeSlotData() {
		TOAppointmentCalendarItem item = null;
		String date = null;
		if(viewTSDatePicker.getValue()!=null) {
		date = viewTSDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		else date = LocalDate.now().toString();

		try {
			item = FlexiBookController.viewAppointmentCalendar("rico", date, dailyToggleButton.isSelected());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<TOTimeSlot> list = FXCollections.observableArrayList();
		
		for(int i = 0; i<item.getUnavailableTimeSlots().size(); i++) {
			list.add(item.getUnavailableTimeSlot(i));

		}
		return list;
	}
	
	
	private void refreshTimeSlots() {
		avTimeSlots.setItems(getAvTimeSlotData());
		unavTimeSlots.setItems(getUnavTimeSlotData());
	}



}
