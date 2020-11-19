package ca.mcgill.ecse.flexibook.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ServicePage extends Application {

	//	private static final long serialVersionUID = -4426310869335015543L;
	//error messages
	private Text errorAddServiceMessage;
	private Text errorUpdateServiceMessage;
	private Text errordeleteServiceMessage;
	//add a service
	private Text addService;
	private Text addServiceInstruction;
	//Service name label
	private Text addServiceName;
	//Service name text field
	private TextField addServiceNameText;
	//service duration
	private Text addServiceDuration;
	//Service duration text field
	private TextField addServiceDurationText;
	//Service downtimeduration
	private Text addServiceDowntimeDuration;
	//Service downtime duration text field
	private TextField addServiceDowntimeDurationText;
	//Service start time of downtime
	private Text addServiceDowntimeStartTime;
	//Service start time of downtime text field
	private TextField addServiceDowntimeStartTimeText;

	private Button addServiceButton;

	private TextField serviceTextField;
	private TextField serviceNameTextField;
	private String serviceDurationTextField;
	private String serviceDowntimeDurationTextField;
	private String serviceDowntimeStartTextField;
	//--------------------------------------ADD SERVICE----------------------------------------------------------	

	//Update Service
	private Text updateServiceLabel;

	//First instruction message
	private Text updateServiceOldInstruction;
	//Service name label
	private Text updateServiceLabelName;
	//Service name text field
	private TextField updateServiceText;



	//Second instruction message
	private Text updateServiceNewInstruction;
	//Yes no buttons
	private ToggleButton updateServiceYes;
	private ToggleButton updateServiceNo;

	//Third instruction message
	private Text updateServiceInstruction;
	//New service name label
	private Text updateServiceNewName;
	//New service name text field
	private TextField updateServiceNewNameText;

	//New duration
	private Text updateServiceNewDuration;
	//New duration text field
	private TextField updateServiceNewDurationText;

	//New downtimeduration 
	private Text updateServiceNewDowntimeDuration;
	//New downtimeduration text field
	private TextField updateServiceNewDowntimeDurationText;
	//New downtime start time
	private Text updateServiceNewDowntimeStartTime;
	//New downtime start time text field
	private TextField updateServiceNewDowntimeStartTimeText;
	//New update Service button
	private Button updateServiceButton;

	//-------------------------------------------------------------------------------	
	//delete Service
	private Text deleteServiceLabel;

	//First instruction message
	private Text deleteServiceFirstInstruction;

	//Service name label
	private Text deleteServiceNameLabel;
	//Service name text field
	private TextField deleteServiceNameText;

	//delete service button
	private Button deleteServiceButton;


	private GridPane gridPaneAddService;
	private GridPane gridPaneUpdateService;
	private GridPane gridPanedeleteService;
	private SplitPane splitPane;
	private Hyperlink addServiceLink;
	private Hyperlink updateServiceLink;
	private Hyperlink deleteServiceLink;
	private Hyperlink mainMenuLink;
	private VBox verticalMenu;
	private BorderPane serviceBorderPane;
	private Scene serviceScene;



	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {


		addService = new Text("Add a Service");
		addService.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		addService.setFill(Color.BLUE);
		addServiceInstruction = new Text
				("Please enter the information of the service you would like to add.");
		addServiceInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorAddServiceMessage = new Text("");
		errorAddServiceMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorAddServiceMessage.setFill(Color.RED);


		addServiceName = new Text("Service Name: ");
		addServiceNameText = new TextField();
		addServiceName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	

		addServiceDuration = new Text("Service Duration: ");
		addServiceDurationText = new TextField();
		addServiceDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addServiceDowntimeDuration = new Text("Downtime Duration: ");
		addServiceDowntimeDurationText = new TextField();
		addServiceDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addServiceDowntimeStartTime = new Text("Downtime Start Time: ");
		addServiceDowntimeStartTimeText = new TextField();
		addServiceDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addServiceButton = new Button("Add service");


		//--------------------------------------------------------------------------------------------

		updateServiceLabel = new Text("Update a Service");
		updateServiceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		updateServiceLabel.setFill(Color.BLUE);
		updateServiceOldInstruction = new Text("Please enter Service Info you would like to update.");   		
		updateServiceOldInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		updateServiceLabelName = new Text("Service: ");
		updateServiceText = new TextField();
		updateServiceLabelName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorUpdateServiceMessage = new Text("");
		errorUpdateServiceMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorUpdateServiceMessage.setFill(Color.RED);

		updateServiceNewInstruction= new Text("Do you wish to change you service name? ");
		updateServiceNewInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		updateServiceYes = new ToggleButton("Yes");
		updateServiceNo = new ToggleButton("No");
		updateServiceInstruction = new Text("Note:If one of the service info hasn't changed, kindly rewrite it. ");

		updateServiceInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceNewName = new Text("New service name: ");
		updateServiceNewNameText = new TextField();
		updateServiceNewName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceNewDuration = new Text("      New duration: ");
		updateServiceNewDurationText = new TextField();
		updateServiceNewDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceNewDowntimeDuration= new Text("New downtime duration: ");
		updateServiceNewDowntimeDurationText = new TextField();
		updateServiceNewDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		updateServiceNewDowntimeStartTime= new Text("New downtime start time: ");
		updateServiceNewDowntimeStartTimeText = new TextField();
		updateServiceNewDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


		updateServiceButton = new Button("Update service");

		//-----------------------------------------------------------------------------------------------------------

		deleteServiceLabel = new Text("Delete a service");
		deleteServiceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		deleteServiceLabel.setFill(Color.BLUE);

		deleteServiceFirstInstruction = new Text("Please enter the service"
				+ " you would like to delete.");
		deleteServiceFirstInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		deleteServiceNameLabel = new Text("Service name: ");
		deleteServiceNameText = new TextField();
		deleteServiceNameLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errordeleteServiceMessage = new Text("");
		errordeleteServiceMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errordeleteServiceMessage.setFill(Color.RED);		
		deleteServiceButton = new Button("Delete service");


		gridPaneAddService = new GridPane();
		gridPaneAddService.setMinSize(500,70);
		gridPaneAddService.setPadding(new Insets(100,100,100,100));	
		gridPaneAddService.setVgap(10);
		gridPaneAddService.setHgap(10);
		gridPaneAddService.setAlignment(Pos.CENTER);
		gridPaneAddService.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneUpdateService = new GridPane();
		gridPaneUpdateService.setMinSize(800, 130);
		gridPaneUpdateService.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneUpdateService.setVgap(10);
		gridPaneUpdateService.setHgap(10);
		gridPaneUpdateService.setAlignment(Pos.CENTER);
		gridPaneUpdateService.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPanedeleteService = new GridPane();
		gridPanedeleteService.setMinSize(500, 70);
		gridPanedeleteService.setPadding(new Insets(100, 100, 100, 100));	
		gridPanedeleteService.setVgap(10);
		gridPanedeleteService.setHgap(10);
		gridPanedeleteService.setAlignment(Pos.CENTER);
		gridPanedeleteService.setStyle("-fx-background-color: LIGHTBLUE;");



		splitPane = new SplitPane();
		splitPane.setMinSize(1100, 600);
		splitPane.setMaxSize(1100, 600);
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneAddService.add(addService, 0, 0,2,1);
		gridPaneAddService.add(addServiceInstruction, 0, 1,5,1);
		gridPaneAddService.add(addServiceName, 0, 2);
		gridPaneAddService.add(addServiceNameText, 1, 2); 
		gridPaneAddService.add(addServiceDuration, 3, 2);
		gridPaneAddService.add(addServiceDurationText,4,2);
		gridPaneAddService.add(addServiceDowntimeDuration, 0, 3);
		gridPaneAddService.add(addServiceDowntimeDurationText, 1, 3);
		gridPaneAddService.add(addServiceDowntimeStartTime,3,3);
		gridPaneAddService.add(addServiceDowntimeStartTimeText,4,3);   
		gridPaneAddService.add(addServiceButton, 2, 7);


		gridPaneUpdateService.add(updateServiceLabel, 1, 0,2,1);
		gridPaneUpdateService.add(updateServiceOldInstruction, 0, 1,6,1);
		gridPaneUpdateService.add(updateServiceLabelName, 0, 2);
		gridPaneUpdateService.add(updateServiceText, 1, 2);
		gridPaneUpdateService.add(updateServiceNewInstruction, 0, 3,2,1);
		gridPaneUpdateService.add(updateServiceYes,  2, 3);
		gridPaneUpdateService.add(updateServiceNo,  3, 3);
		gridPaneUpdateService.add(updateServiceInstruction, 0, 8,9,1);
		gridPaneUpdateService.add(updateServiceNewName, 0, 5);
		gridPaneUpdateService.add(updateServiceNewNameText, 1, 5);
		gridPaneUpdateService.add(updateServiceNewDuration, 2, 5);
		gridPaneUpdateService.add(updateServiceNewDurationText, 3, 5);
		gridPaneUpdateService.add(updateServiceNewDowntimeDuration, 0,6);
		gridPaneUpdateService.add(updateServiceNewDowntimeDurationText, 1, 6);
		gridPaneUpdateService.add(updateServiceNewDowntimeStartTime, 2, 6);
		gridPaneUpdateService.add(updateServiceNewDowntimeStartTimeText, 3,6);
		gridPaneUpdateService.add(updateServiceButton, 2, 7,2,1);

		gridPanedeleteService.add(deleteServiceLabel,1,0,2,1);
		gridPanedeleteService.add(deleteServiceFirstInstruction, 0,1,5,1);
		gridPanedeleteService.add(deleteServiceNameLabel, 0, 2);
		gridPanedeleteService.add(deleteServiceNameText, 1, 2);
		gridPanedeleteService.add(deleteServiceButton, 3,2);


		verticalMenu = new VBox();
		verticalMenu.setPadding(new Insets(10));
		verticalMenu.setSpacing(8);

		

		Text title = new Text("What do you wish to do?");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		

		verticalMenu.getChildren().add(title);


		addServiceLink = new Hyperlink("Add a service");
		updateServiceLink = new Hyperlink("Update a service");
		deleteServiceLink = new Hyperlink ("Delete a service");
		mainMenuLink = new Hyperlink("Main Menu");

		Hyperlink options[] = new Hyperlink[] {
				addServiceLink,
				updateServiceLink,
				deleteServiceLink,
				mainMenuLink};

		for (int i=0; i<4; i++) {
			VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
			verticalMenu.getChildren().add(options[i]);
		}



		serviceBorderPane = new BorderPane();
		serviceBorderPane.setLeft(verticalMenu);
		serviceBorderPane.setCenter(gridPaneAddService);


		serviceScene = new Scene(serviceBorderPane);

		addServiceLink.setOnAction(e->{
			serviceBorderPane.setCenter(gridPaneAddService);
			primaryStage.setTitle("Add a service");
		});
		updateServiceLink.setOnAction(e->{
			serviceBorderPane.setCenter(gridPaneUpdateService);
			primaryStage.setTitle("Update a service");
		});

		deleteServiceLink.setOnAction(e->{
			serviceBorderPane.setCenter(gridPanedeleteService);
			primaryStage.setTitle("Delete a service");
		});  



		addServiceButton.setOnAction(e->{
			try {
				if(serviceNameTextField.getText()== null || serviceNameTextField.getText().trim().isEmpty()) {
					errorAddServiceMessage.setText("A service name should be set");
				}
				if (serviceDurationTextField.trim().isEmpty()){
					errorAddServiceMessage.setText("A service duration should be set");
				}
				else if(serviceDowntimeDurationTextField.trim().isEmpty()){		    		
					errorAddServiceMessage.setText("A service downtime duration should be set");
				}
				else if(serviceDowntimeStartTextField.trim().isEmpty()) {
					errorAddServiceMessage.setText("A downtime start time should be set");
				}
				else {
					FlexiBookController.addService(serviceNameTextField.getText(),Integer.parseInt(serviceDurationTextField),
							Integer.parseInt(serviceDowntimeDurationTextField),Integer.parseInt(serviceDowntimeStartTextField), 
							FlexiBookApplication.getCurrentUser().getUsername());

					errorAddServiceMessage.setText("");
				}
			} catch (InvalidInputException e1) {
				errorAddServiceMessage.setText(e1.getMessage());
			}
		});


		updateServiceButton.setOnAction(e->{
			try {
				FlexiBookController.updateService(serviceTextField.getText(),Integer.parseInt(serviceDurationTextField),
						Integer.parseInt(serviceDowntimeDurationTextField),Integer.parseInt(serviceDowntimeStartTextField),
						FlexiBookApplication.getCurrentUser().getUsername() , serviceNameTextField.getText());
				errorUpdateServiceMessage.setText("");
			} catch (InvalidInputException e1) {
				errorUpdateServiceMessage.setText(e1.getMessage());
			}
		});

		deleteServiceButton.setOnAction(e->{
			try {
				if(serviceNameTextField.getText()== null || serviceNameTextField.getText().trim().isEmpty()) {
					errorAddServiceMessage.setText("A service name should be set to get deleted");
				}
				else {
					FlexiBookController.deleteService(serviceNameTextField.getText(),
							FlexiBookApplication.getCurrentUser().getUsername());			  
					errordeleteServiceMessage.setText("");
				}
			} catch (InvalidInputException e1) {
				errordeleteServiceMessage.setText(e1.getMessage());
			}

		});



		primaryStage.setScene(serviceScene);
		primaryStage.show();

	}
}