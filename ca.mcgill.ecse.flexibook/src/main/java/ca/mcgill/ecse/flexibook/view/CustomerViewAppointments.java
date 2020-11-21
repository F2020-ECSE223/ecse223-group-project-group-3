package ca.mcgill.ecse.flexibook.view;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
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

public class CustomerViewAppointments extends Application{
	

	private Stage window;
	
	private TableView<TOAppointment> appTable;
	private BorderPane viewAppCalPane;
	private Scene scene;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("View Appointments");
		
	
		
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
		
		appTable = new TableView<TOAppointment>();
		appTable.setItems(getCustomerAppointmentsData("rico"));
		appTable.getColumns().addAll(serviceNameCol, startTimeCol, endTimeCol, dateCol);
		
		
		viewAppCalPane = new BorderPane();
		viewAppCalPane.setCenter(appTable);
		
		
		
		scene = new Scene(viewAppCalPane, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private ObservableList<TOAppointment> getCustomerAppointmentsData(String username) {
		ObservableList<TOAppointment> list = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getCustomerTOAppointments(username).size(); i++) {
			list.add(FlexiBookController.getCustomerTOAppointments(username).get(i));
		}
		return list;
	}

	

}


