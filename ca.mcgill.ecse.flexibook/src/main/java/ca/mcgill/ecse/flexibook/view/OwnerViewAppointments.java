package ca.mcgill.ecse.flexibook.view;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OwnerViewAppointments extends Application{


	private Stage window;

	private TableView<TOAppointment> appTable;
	private TableColumn<TOAppointment, String> customerNameCol;
	private TableColumn<TOAppointment, String> serviceNameCol;
	private TableColumn<TOAppointment, Time> startTimeCol;
	private TableColumn<TOAppointment, Time> endTimeCol;
	private TableColumn<TOAppointment, Date> dateCol;
	
	private BorderPane viewAppCalPane;
	private Scene scene;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setTitle("View Appointments");

		customerNameCol = new TableColumn<TOAppointment, String>("Customer name");
		customerNameCol.setMinWidth(150);
		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		

		serviceNameCol = new TableColumn<TOAppointment, String>("Service");
		serviceNameCol.setMinWidth(150);
		serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));

		startTimeCol = new TableColumn<TOAppointment, Time>("Start Time");
		startTimeCol.setMinWidth(150);
		startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));

		endTimeCol = new TableColumn<TOAppointment, Time>("End Time");
		endTimeCol.setMinWidth(150);
		endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

		dateCol = new TableColumn<TOAppointment, Date>("Date");
		dateCol.setMinWidth(150);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

		appTable = new TableView<TOAppointment>();
		appTable.setItems(getAppointmentsData());
		appTable.getColumns().addAll(customerNameCol, serviceNameCol, startTimeCol, endTimeCol, dateCol);


		viewAppCalPane = new BorderPane();
		viewAppCalPane.setCenter(appTable);



		scene = new Scene(viewAppCalPane, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private ObservableList<TOAppointment> getAppointmentsData() {
		ObservableList<TOAppointment> list = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getTOAppointments().size(); i++) {
			list.add(FlexiBookController.getTOAppointments().get(i));
		}
		return list;
	}

}
