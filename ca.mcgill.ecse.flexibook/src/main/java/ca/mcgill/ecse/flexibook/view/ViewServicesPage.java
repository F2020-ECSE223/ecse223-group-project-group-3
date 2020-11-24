package ca.mcgill.ecse.flexibook.view;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import ca.mcgill.ecse223.flexibook.controller.TOService;
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

public class ViewServicesPage extends Application{

	private Stage window;
	
	private VBox vbox;
	private HBox hbox;
	private BorderPane viewServicesCalPane;
	private Scene scene;
	
	private TableView<TOService> serviceTable;
	private TableColumn<TOService, String> serviceNameCol;
	private TableColumn<TOService, Integer> durationCol;
	private TableColumn<TOService, Integer> downtimeStartCol;
	private TableColumn<TOService, Integer> downtimeDurationCol;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("View Services");
		
		
		
		serviceNameCol = new TableColumn<TOService, String>("Service name");
		serviceNameCol.setMinWidth(150);
		serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		durationCol = new TableColumn<TOService, Integer>("Duration");
		durationCol.setMinWidth(150);
		durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
		
		downtimeStartCol = new TableColumn<TOService, Integer>("Downtime Start");
		downtimeStartCol.setMinWidth(150);
		downtimeStartCol.setCellValueFactory(new PropertyValueFactory<>("downtimeStart"));
		
		downtimeDurationCol = new TableColumn<TOService, Integer>("Downtime Duration");
		downtimeDurationCol.setMinWidth(150);
		downtimeDurationCol.setCellValueFactory(new PropertyValueFactory<>("downtimeDuration"));
		

		serviceTable = new TableView<TOService>();
		serviceTable.setItems(getServicesData());
		serviceTable.getColumns().addAll(serviceNameCol, durationCol, downtimeStartCol, downtimeDurationCol);
		
		
		vbox = new VBox();
		vbox.getChildren().addAll(serviceTable);
		
			
		viewServicesCalPane = new BorderPane();
		viewServicesCalPane.setRight(hbox);
		viewServicesCalPane.setCenter(vbox);
		
		
		scene = new Scene(viewServicesCalPane, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private ObservableList<TOService> getServicesData() {
		ObservableList<TOService> list = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getTOServices().size(); i++) {
			list.add(FlexiBookController.getTOServices().get(i));
		}
		return list;
	}
}
