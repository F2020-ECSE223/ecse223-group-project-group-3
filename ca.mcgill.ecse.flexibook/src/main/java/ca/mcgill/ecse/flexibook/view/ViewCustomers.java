package ca.mcgill.ecse.flexibook.view;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import ca.mcgill.ecse223.flexibook.controller.TOCustomer;
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

public class ViewCustomers extends Application{

	private Stage window;
	
	private VBox vbox;
	private HBox hbox;
	private BorderPane viewServicesCalPane;
	private Scene scene;
	
	private TableView<TOCustomer> customerTable;
	private TableColumn<TOCustomer, String> usernameCol;
	private TableColumn<TOCustomer, Integer> noShowCol;

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("View Services");
		
		
		
		usernameCol = new TableColumn<TOCustomer, String>("Customer name");
		usernameCol.setMinWidth(500);
		usernameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		noShowCol = new TableColumn<TOCustomer, Integer>("Number of no-shows");
		noShowCol.setMinWidth(500);
		noShowCol.setCellValueFactory(new PropertyValueFactory<>("noShow"));
	
		

		customerTable = new TableView<TOCustomer>();
		customerTable.setItems(getCustomersData());
		customerTable.getColumns().addAll(usernameCol, noShowCol);
		
		
		vbox = new VBox();
		vbox.getChildren().addAll(customerTable);
		
			
		viewServicesCalPane = new BorderPane();
		viewServicesCalPane.setRight(hbox);
		viewServicesCalPane.setCenter(vbox);
		
		
		scene = new Scene(viewServicesCalPane, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private ObservableList<TOCustomer> getCustomersData() {
		ObservableList<TOCustomer> list = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getTOCustomers().size(); i++) {
			list.add(FlexiBookController.getTOCustomers().get(i));
		}
		return list;
	}
	
	private void refreshCustomersData() {
		customerTable.setItems(getCustomersData());
	}
}
