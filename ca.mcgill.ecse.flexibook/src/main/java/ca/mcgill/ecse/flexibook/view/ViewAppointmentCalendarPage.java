package ca.mcgill.ecse.flexibook.view;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import ca.mcgill.ecse223.flexibook.controller.TOTimeSlot;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
	

	private TableView<TOTimeSlot> avTimeSlots;
	private TableView<TOTimeSlot> unavTimeSlots;
	private HBox toggleButtons;
	private ToggleButton dailyToggleButton;
	private ToggleButton weeklyToggleButton;
	private ToggleGroup toggleGroup;
	private BorderPane timeSlotTables;
	private BorderPane viewAppCalPane;
	private Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("Appointment Calendar");
		

		
		dailyToggleButton = new ToggleButton("Daily view");
		dailyToggleButton.setSelected(true);
		weeklyToggleButton = new ToggleButton("weekly view");
		toggleGroup = new ToggleGroup();
		
		dailyToggleButton.setToggleGroup(toggleGroup);
		weeklyToggleButton.setToggleGroup(toggleGroup);
		
		toggleButtons = new HBox(dailyToggleButton, weeklyToggleButton);
		toggleButtons.setAlignment(Pos.CENTER);
		
		
		
		boolean daily = dailyToggleButton.isSelected();
		String date = LocalDate.now().toString();
		
		
		
		TableColumn<TOTimeSlot, Date> availableDateCol = new TableColumn<TOTimeSlot, Date>("Date");
		availableDateCol.setMinWidth(150);
		availableDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

		TableColumn<TOTimeSlot, Time> availableStartTimeCol = new TableColumn<TOTimeSlot, Time>("Start Time");
		availableStartTimeCol.setMinWidth(150);
		availableStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		TableColumn<TOTimeSlot, Time> availableEndTimeCol = new TableColumn<TOTimeSlot, Time>("End Time");
		availableEndTimeCol.setMinWidth(150);
		availableEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		TableColumn<TOTimeSlot, Date> unavailableDateCol = new TableColumn<TOTimeSlot, Date>("Date");
		unavailableDateCol.setMinWidth(150);
		unavailableDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

		TableColumn<TOTimeSlot, Time> unavailableStartTimeCol = new TableColumn<TOTimeSlot, Time>("Start Time");
		unavailableStartTimeCol.setMinWidth(150);
		unavailableStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		TableColumn<TOTimeSlot, Time> unavailableEndTimeCol = new TableColumn<TOTimeSlot, Time>("End Time");
		unavailableEndTimeCol.setMinWidth(150);
		unavailableEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));


		avTimeSlots = new TableView<TOTimeSlot>();
		avTimeSlots.setItems(getAvTimeSlotData());
		avTimeSlots.getColumns().addAll(availableDateCol, availableStartTimeCol, availableEndTimeCol);
		
		
		unavTimeSlots = new TableView<TOTimeSlot>();
		unavTimeSlots.setItems(getUnavTimeSlotData());
		unavTimeSlots.getColumns().addAll(unavailableDateCol, unavailableStartTimeCol, unavailableEndTimeCol);
		
		timeSlotTables = new BorderPane();
		timeSlotTables.setLeft(avTimeSlots);
		timeSlotTables.setRight(unavTimeSlots);
		
		viewAppCalPane = new BorderPane();
		viewAppCalPane.setTop(toggleButtons);
		viewAppCalPane.setCenter(timeSlotTables);
		
		dailyToggleButton.setOnAction(e->{
			refreshAvTimeSlots();
		    refreshUnavTimeSlots();	
			});
		
		weeklyToggleButton.setOnAction(e->{
			refreshAvTimeSlots();
		    refreshUnavTimeSlots();			
		    });
		
		scene = new Scene(viewAppCalPane, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
	private ObservableList<TOTimeSlot> getAvTimeSlotData() {
		TOAppointmentCalendarItem item = null;
		try {
			item = FlexiBookController.viewAppointmentCalendar("rico", LocalDate.now().toString(), dailyToggleButton.isSelected());
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
		try {
			item = FlexiBookController.viewAppointmentCalendar("rico", LocalDate.now().toString(), dailyToggleButton.isSelected());
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
	
	
	private void refreshAvTimeSlots() {
		avTimeSlots.setItems(getAvTimeSlotData());

	}
	
	private void refreshUnavTimeSlots() {
		unavTimeSlots.setItems(getUnavTimeSlotData());

	}

}
