package ca.mcgill.ecse.flexibook.view;


import java.sql.Date;
import java.time.LocalDate;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import ca.mcgill.ecse223.flexibook.controller.TOTimeSlot;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewAppointmentCalendarPage extends Application {
	
	private Stage window;
	private TableView table;
	private VBox vbox;
	private HBox hbox;
	private ToggleButton dailyToggleButton;
	private ToggleButton weeklyToggleButton;
	private ToggleGroup toggleGroup;
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
		weeklyToggleButton = new ToggleButton("weekly view");
		toggleGroup = new ToggleGroup();
		
		dailyToggleButton.setToggleGroup(toggleGroup);
		weeklyToggleButton.setToggleGroup(toggleGroup);

		boolean daily = dailyToggleButton.isSelected();
		String date = LocalDate.now().toString();
		TOAppointmentCalendarItem item = FlexiBookController.viewAppointmentCalendar("rico", date,  daily);
		
		TableColumn<TOTimeSlot, String> availableTimeSlotsCol = new TableColumn("Available time slots");
		availableTimeSlotsCol.setMinWidth(200);
		availableTimeSlotsCol.setCellValueFactory(new PropertyValueFactory<>("availableTimeSlots"));
		
		table = new TableView();
		table.setItems(getAvailableTimeSlots(item));
		table.getColumns().addAll(availableTimeSlotsCol);
		
		vbox = new VBox();
		vbox.getChildren().addAll(table);
		
		hbox = new HBox(dailyToggleButton, weeklyToggleButton);
		
		
		viewAppCalPane = new BorderPane();
		viewAppCalPane.setRight(hbox);
		viewAppCalPane.setCenter(vbox);
		
		
		scene = new Scene(viewAppCalPane, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private ObservableList<TOTimeSlot> getAvailableTimeSlots(TOAppointmentCalendarItem item){
		ObservableList<TOTimeSlot> available = FXCollections.observableArrayList();
		for(TOTimeSlot TS : item.getAvailableTimeSlots()) {
			available.add(TS);
		}
		return available;
	}

}
