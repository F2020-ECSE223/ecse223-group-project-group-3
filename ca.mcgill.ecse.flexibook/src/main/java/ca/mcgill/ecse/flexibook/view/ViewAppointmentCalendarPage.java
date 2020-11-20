package ca.mcgill.ecse.flexibook.view;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private TableView<TOAppointment> table;
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
		
		TableColumn<TOAppointment, String> customerNameCol = new TableColumn<TOAppointment, String>("Customer name");
		customerNameCol.setMinWidth(150);
		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("cutomerName"));
		
		TableColumn<TOAppointment, String> serviceNameCol = new TableColumn<TOAppointment, String>("Service");
		serviceNameCol.setMinWidth(150);
		serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
		
		TableColumn<TOAppointment, Time> startTimeCol = new TableColumn<TOAppointment, Time>("Start Time");
		startTimeCol.setMinWidth(150);
		startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		TableColumn<TOAppointment, Time> endTimeCol = new TableColumn<TOAppointment, Time>("End Time");
		endTimeCol.setMinWidth(150);
		endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		TableColumn<TOAppointment, Date> dateCol = new TableColumn<TOAppointment, Date>("Date");
		dateCol.setMinWidth(150);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

		table = new TableView<TOAppointment>();
		table.setItems(getAppointmentsData(item));
		table.getColumns().addAll(customerNameCol, serviceNameCol, startTimeCol, endTimeCol, dateCol);
		
		
		vbox = new VBox();
		vbox.getChildren().addAll(table);
		
		hbox = new HBox(dailyToggleButton, weeklyToggleButton);
		
		
		viewAppCalPane = new BorderPane();
		viewAppCalPane.setRight(hbox);
		viewAppCalPane.setCenter(vbox);
		
		
		scene = new Scene(viewAppCalPane, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private ObservableList<TOAppointment> getAppointmentsData(TOAppointmentCalendarItem item) {
		ObservableList<TOAppointment> list = FXCollections.observableArrayList();
		for(int i = 0; i<item.getTOAppointments().size(); i++) {
			list.add(item.getTOAppointment(i));
		}
		return list;
	}


}
