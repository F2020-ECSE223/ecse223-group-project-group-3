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
	
	private BorderPane viewAppCalPane;
	private Scene scene;

	private TableView<TOAppointment> cusAppTable;
	private TableColumn<TOAppointment, String> cusServiceNameCol;
	private TableColumn<TOAppointment, Time> cusStartTimeCol;
	private TableColumn<TOAppointment, Time> cusEndTimeCol;
	private TableColumn<TOAppointment, Date> cusDateCol;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("View Appointments");
		
	
		
		cusServiceNameCol = new TableColumn<TOAppointment, String>("Service");
		cusServiceNameCol.setMinWidth(150);
		cusServiceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
		
		cusStartTimeCol = new TableColumn<TOAppointment, Time>("Start Time");
		cusStartTimeCol.setMinWidth(150);
		cusStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		cusEndTimeCol = new TableColumn<TOAppointment, Time>("End Time");
		cusEndTimeCol.setMinWidth(150);
		cusEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		cusDateCol = new TableColumn<TOAppointment, Date>("Date");
		cusDateCol.setMinWidth(150);
		cusDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		cusAppTable = new TableView<TOAppointment>();
		cusAppTable.setItems(getCustomerAppointmentsData("rico"));
		cusAppTable.getColumns().addAll(cusServiceNameCol, cusStartTimeCol, cusEndTimeCol, cusDateCol);
		
		
		viewAppCalPane = new BorderPane();
		viewAppCalPane.setCenter(cusAppTable);
		
		
		
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


