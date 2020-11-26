package ca.mcgill.ecse.flexibook.view;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import ca.mcgill.ecse223.flexibook.controller.TOServiceCombo;
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

public class ViewServiceCombosPage extends Application{

	private Stage window;
	
	private VBox vbox;
	private HBox hbox;
	private BorderPane viewServicesCalPane;
	private Scene scene;
	
	private TableView<TOServiceCombo> serviceComboTable;
	private TableColumn<TOServiceCombo, String> serviceComboNameCol;
	private TableColumn<TOServiceCombo, String> mainServiceCol;
	private TableColumn<TOServiceCombo, String> mandatoryServicesCol;
	private TableColumn<TOServiceCombo, String> opServicesCol;

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("View ServiceCombos");
		
		
		
		serviceComboNameCol = new TableColumn<TOServiceCombo, String>("Service Combo Name");
		serviceComboNameCol.setMinWidth(150);
		serviceComboNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		mainServiceCol = new TableColumn<TOServiceCombo, String>("Main Service");
		mainServiceCol.setMinWidth(150);
		mainServiceCol.setCellValueFactory(new PropertyValueFactory<>("mainService"));
		
		mandatoryServicesCol = new TableColumn<TOServiceCombo, String>("Mandatory Services");
		mandatoryServicesCol.setMinWidth(250);
		mandatoryServicesCol.setCellValueFactory(new PropertyValueFactory<>("mandatoryServices"));
		
		opServicesCol = new TableColumn<TOServiceCombo, String>("Optional Services");
		opServicesCol.setMinWidth(250);
		opServicesCol.setCellValueFactory(new PropertyValueFactory<>("opServices"));
		


		serviceComboTable = new TableView<TOServiceCombo>();
		serviceComboTable.setItems(getServiceCombosData());
		serviceComboTable.getColumns().addAll(serviceComboNameCol, mainServiceCol, mandatoryServicesCol, opServicesCol);
		
		
		vbox = new VBox();
		vbox.getChildren().addAll(serviceComboTable);
		
			
		viewServicesCalPane = new BorderPane();
		viewServicesCalPane.setRight(hbox);
		viewServicesCalPane.setCenter(vbox);
		
		
		scene = new Scene(viewServicesCalPane, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private ObservableList<TOServiceCombo> getServiceCombosData() {
		ObservableList<TOServiceCombo> list = FXCollections.observableArrayList();
		for(int i = 0; i<FlexiBookController.getTOServiceCombos().size(); i++) {
			list.add(FlexiBookController.getTOServiceCombos().get(i));
		}
		return list;
	}
	
	private void refreshServiceCombosData() {
		serviceComboTable.setItems(getServiceCombosData());
	}
}
