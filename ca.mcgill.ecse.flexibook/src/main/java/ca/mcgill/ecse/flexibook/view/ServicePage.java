package ca.mcgill.ecse.flexibook.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
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

	//Service Page--------------------------------------------------------------------------------------------------------
	//private Scene ownerMainScene;


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
//-----------------------------------Service combo-----------------------
	private Text errorAddServiceComboMessage;
	private Text errorUpdateServiceComboMessage;
	private Text errorDeleteServiceComboMessage;
	
		
    private Text addServiceCombo;
	private Text addServiceComboInstruction;
	private Text addServiceComboName;
	private TextField addServiceComboNameText;
	private Text addServiceComboDuration;
	private TextField addServiceComboDurationText;
	private Text addServiceComboDowntimeDuration;
	private TextField addServiceComboDowntimeDurationText;
	private Text addServiceComboDowntimeStartTime;
	private TextField addServiceComboDowntimeStartTimeText;
	private Button addServiceComboButton;

	private TextField serviceComboTextField;
	private TextField serviceComboNameTextField;
	private String serviceComboDurationTextField;
	private String serviceComboDowntimeDurationTextField;
	private String serviceComboDowntimeStartTextField;
	

	//Update Service Combo
	private Text updateServiceComboLabel;

	//First instruction message
	private Text updateServiceComboOldInstruction;
	//Service combo name label
	private Text updateServiceComboLabelName;
	//Service combo name text field
	private TextField updateServiceComboText;



	//Second instruction message
	private Text updateServiceComboNewInstruction;
	//Yes no buttons
	private ToggleButton updateServiceComboYes;
	private ToggleButton updateServiceComboNo;

	//Third instruction message
	private Text updateServiceComboInstruction;
	//New service name label
	private Text updateServiceComboNewName;
	//New service name text field
	private TextField updateServiceComboNewNameText;

	//New duration
	private Text updateServiceComboNewDuration;
	//New duration text field
	private TextField updateServiceComboNewDurationText;

	//New downtimeduration 
	private Text updateServiceComboNewDowntimeDuration;
	//New downtimeduration text field
	private TextField updateServiceComboNewDowntimeDurationText;
	//New downtime start time
	private Text updateServiceComboNewDowntimeStartTime;
	//New downtime start time text field
	private TextField updateServiceComboNewDowntimeStartTimeText;
	//New update Service button
	private Button updateServiceComboButton;

	//-------------------------------------------------------------------------------	
	//delete Service
	private Text deleteServiceComboLabel;

	//First instruction message
	private Text deleteServiceComboFirstInstruction;

	//Service name label
	private Text deleteServiceComboNameLabel;
	//Service name text field
	private TextField deleteServiceComboNameText;

	//delete service button
	private Button deleteServiceComboButton;

	

	private GridPane gridPaneAddServiceCombo;
	private GridPane gridPaneUpdateServiceCombo;
	private GridPane gridPanedeleteServiceCombo;
	//private SplitPane spc;
	private Hyperlink addServiceComboLink;
	private Hyperlink updateServiceComboLink;
	private Hyperlink deleteServiceComboLink;
	private Hyperlink mainMenuComboLink;
	//private VBox verticalMenu;
	private BorderPane serviceComboBorderPane;
	private Scene serviceComboScene;
	private VBox verticalMenuCombo;
	
	private Scene ownerMainScene;

//---------------------------------------------------------------------
	

	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		//Service Page---------------------------------------------------------------------------------------------------

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



				Text sericeMenuTitle = new Text("What do you wish to do?");
				sericeMenuTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 18));


				verticalMenu.getChildren().add(sericeMenuTitle);


				addServiceLink = new Hyperlink("Add a service");
				updateServiceLink = new Hyperlink("Update a service");
				deleteServiceLink = new Hyperlink ("Delete a service");
				//mainMenuLink = new Hyperlink("Main Menu");
		
		
				Hyperlink options[] = new Hyperlink[] {
						addServiceLink,
						updateServiceLink,
						deleteServiceLink,
				};
						//mainMenuLink};

				for (int i=0; i<3; i++) {
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
							FlexiBookController.addService(addServiceNameText.getText(),
									Integer.parseInt(addServiceDurationText.getText()),
									Integer.parseInt(addServiceDowntimeDurationText.getText()),
									Integer.parseInt(addServiceDowntimeStartTimeText.getText()), 
									FlexiBookApplication.getCurrentUser().getUsername());
							Alert a = new Alert(AlertType.CONFIRMATION, "Service added successfully");
							a.showAndWait();
							errorAddServiceMessage.setText("");
					}
					} catch (InvalidInputException e1) {
						errorAddServiceMessage.setText(e1.getMessage());
						Alert a = new Alert(AlertType.ERROR, errorAddServiceMessage.getText());
						a.showAndWait();
					}
				});


				updateServiceButton.setOnAction(e->{
					try {
						FlexiBookController.updateService(serviceTextField.getText(),
								Integer.parseInt(serviceDurationTextField),
								Integer.parseInt(serviceDowntimeDurationTextField),
								Integer.parseInt(serviceDowntimeStartTextField),
								FlexiBookApplication.getCurrentUser().getUsername() , 
								serviceNameTextField.getText());
						errorUpdateServiceMessage.setText("");
					} catch (InvalidInputException e1) {
						errorUpdateServiceMessage.setText(e1.getMessage());
					}
				});

				deleteServiceButton.setOnAction(e->{
					try {
						if(deleteServiceNameText.getText()== null || deleteServiceNameText.getText().trim().isEmpty()) {
							errordeleteServiceMessage.setText("A service name should be set to get deleted");
							Alert a = new Alert(AlertType.ERROR, errordeleteServiceMessage.getText());
							a.showAndWait();
						}
						else {
							FlexiBookController.deleteService(deleteServiceNameText.getText(),
									FlexiBookApplication.getCurrentUser().getUsername());			  
							errordeleteServiceMessage.setText("");
							Alert a = new Alert(AlertType.CONFIRMATION, "Service deleted successfully");
							a.showAndWait();
						}
					} catch (InvalidInputException e1) {
						errordeleteServiceMessage.setText(e1.getMessage());
						Alert a = new Alert(AlertType.ERROR, errordeleteServiceMessage.getText());
						a.showAndWait();
					}

				});

	

//-------------------------SERVICE COMBO----------------
addServiceCombo = new Text("Add a Service combo");
addServiceCombo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
addServiceCombo.setFill(Color.BLUE);
addServiceComboInstruction = new Text
		("Please enter the information of the service you would like to add.");
addServiceComboInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

errorAddServiceComboMessage = new Text("");
errorAddServiceComboMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
errorAddServiceComboMessage.setFill(Color.RED);


addServiceComboName = new Text("Service Combo Name: ");
addServiceComboNameText = new TextField();
addServiceComboName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	

addServiceComboDuration = new Text("Service Combo Duration: ");
addServiceComboDurationText = new TextField();
addServiceComboDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

addServiceComboDowntimeDuration = new Text("Downtime Duration: ");
addServiceComboDowntimeDurationText = new TextField();
addServiceComboDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

addServiceComboDowntimeStartTime = new Text("Downtime Start Time: ");
addServiceComboDowntimeStartTimeText = new TextField();
addServiceComboDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

addServiceComboButton = new Button("Add service combo");




//--------------------------------------------------------------------------------------------

updateServiceComboLabel = new Text("Update a Service combo");
updateServiceComboLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
updateServiceComboLabel.setFill(Color.BLUE);
updateServiceComboOldInstruction = new Text("Please enter Service Combo Info you would like to update.");   		
updateServiceComboOldInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


updateServiceComboLabelName = new Text("Service Combo: ");
updateServiceComboText = new TextField();
updateServiceComboLabelName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

errorUpdateServiceComboMessage = new Text("");
errorUpdateServiceComboMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
errorUpdateServiceComboMessage.setFill(Color.RED);

updateServiceComboNewInstruction= new Text("Do you wish to change you service combo name? ");
updateServiceComboNewInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
updateServiceComboYes = new ToggleButton("Yes");
updateServiceComboNo = new ToggleButton("No");
updateServiceComboInstruction = new Text("Note:If one of the service info hasn't changed, kindly rewrite it. ");

updateServiceComboInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

updateServiceComboNewName = new Text("New service combo name: ");
updateServiceComboNewNameText = new TextField();
updateServiceComboNewName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

updateServiceComboNewDuration = new Text("      New duration: ");
updateServiceComboNewDurationText = new TextField();
updateServiceComboNewDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

updateServiceComboNewDowntimeDuration= new Text("New downtime duration: ");
updateServiceComboNewDowntimeDurationText = new TextField();
updateServiceComboNewDowntimeDuration.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

updateServiceComboNewDowntimeStartTime= new Text("New downtime start time: ");
updateServiceComboNewDowntimeStartTimeText = new TextField();
updateServiceComboNewDowntimeStartTime.setFont(Font.font("Verdana", FontWeight.NORMAL,15));


updateServiceComboButton = new Button("Update service combo");

//-----------------------------------------------------------------------------------------------------------

deleteServiceComboLabel = new Text("Delete a service combo");
deleteServiceComboLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
deleteServiceComboLabel.setFill(Color.BLUE);

deleteServiceComboFirstInstruction = new Text("Please enter the service combo"
		+ " you would like to delete.");
deleteServiceComboFirstInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

deleteServiceComboNameLabel = new Text("Service combo name: ");
deleteServiceComboNameText = new TextField();
deleteServiceComboNameLabel.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

errorDeleteServiceComboMessage = new Text("");
errorDeleteServiceComboMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
errorDeleteServiceComboMessage.setFill(Color.RED);		
deleteServiceComboButton = new Button("Delete service combo");


gridPaneAddServiceCombo = new GridPane();
gridPaneAddServiceCombo.setMinSize(500,70);
gridPaneAddServiceCombo.setPadding(new Insets(100,100,100,100));	
gridPaneAddServiceCombo.setVgap(10);
gridPaneAddServiceCombo.setHgap(10);
gridPaneAddServiceCombo.setAlignment(Pos.CENTER);
gridPaneAddServiceCombo.setStyle("-fx-background-color: LIGHTBLUE;");

gridPaneUpdateServiceCombo = new GridPane();
gridPaneUpdateServiceCombo.setMinSize(800, 130);
gridPaneUpdateServiceCombo.setPadding(new Insets(100, 100, 100, 100));	
gridPaneUpdateServiceCombo.setVgap(10);
gridPaneUpdateServiceCombo.setHgap(10);
gridPaneUpdateServiceCombo.setAlignment(Pos.CENTER);
gridPaneUpdateServiceCombo.setStyle("-fx-background-color: LIGHTBLUE;");

gridPanedeleteServiceCombo = new GridPane();
gridPanedeleteServiceCombo.setMinSize(500, 70);
gridPanedeleteServiceCombo.setPadding(new Insets(100, 100, 100, 100));	
gridPanedeleteServiceCombo.setVgap(10);
gridPanedeleteServiceCombo.setHgap(10);
gridPanedeleteServiceCombo.setAlignment(Pos.CENTER);
gridPanedeleteServiceCombo.setStyle("-fx-background-color: LIGHTBLUE;");

splitPane = new SplitPane();
splitPane.setMinSize(1100, 600);
splitPane.setMaxSize(1100, 600);
splitPane.setOrientation(Orientation.VERTICAL);
splitPane.setStyle("-fx-background-color: LIGHTBLUE;");

gridPaneAddServiceCombo.add(addServiceCombo, 0, 0,2,1);
gridPaneAddServiceCombo.add(addServiceComboInstruction, 0, 1,5,1);
gridPaneAddServiceCombo.add(addServiceComboName, 0, 2);
gridPaneAddServiceCombo.add(addServiceComboNameText, 1, 2); 
gridPaneAddServiceCombo.add(addServiceComboDuration, 3, 2);
gridPaneAddServiceCombo.add(addServiceComboDurationText,4,2);
gridPaneAddServiceCombo.add(addServiceComboDowntimeDuration, 0, 3);
gridPaneAddServiceCombo.add(addServiceComboDowntimeDurationText, 1, 3);
gridPaneAddServiceCombo.add(addServiceComboDowntimeStartTime,3,3);
gridPaneAddServiceCombo.add(addServiceComboDowntimeStartTimeText,4,3);   
gridPaneAddServiceCombo.add(addServiceComboButton, 2, 7);


gridPaneUpdateServiceCombo.add(updateServiceComboLabel, 1, 0,2,1);
gridPaneUpdateServiceCombo.add(updateServiceComboOldInstruction, 0, 1,6,1);
gridPaneUpdateServiceCombo.add(updateServiceComboLabelName, 0, 2);
gridPaneUpdateServiceCombo.add(updateServiceComboText, 1, 2);
gridPaneUpdateServiceCombo.add(updateServiceComboNewInstruction, 0, 3,2,1);
gridPaneUpdateServiceCombo.add(updateServiceComboYes,  2, 3);
gridPaneUpdateServiceCombo.add(updateServiceComboNo,  3, 3);
gridPaneUpdateServiceCombo.add(updateServiceComboInstruction, 0, 8,9,1);
gridPaneUpdateServiceCombo.add(updateServiceComboNewName, 0, 5);
gridPaneUpdateServiceCombo.add(updateServiceComboNewNameText, 1, 5);
gridPaneUpdateServiceCombo.add(updateServiceComboNewDuration, 2, 5);
gridPaneUpdateServiceCombo.add(updateServiceComboNewDurationText, 3, 5);
gridPaneUpdateServiceCombo.add(updateServiceComboNewDowntimeDuration, 0,6);
gridPaneUpdateServiceCombo.add(updateServiceComboNewDowntimeDurationText, 1, 6);
gridPaneUpdateServiceCombo.add(updateServiceComboNewDowntimeStartTime, 2, 6);
gridPaneUpdateServiceCombo.add(updateServiceComboNewDowntimeStartTimeText, 3,6);
gridPaneUpdateServiceCombo.add(updateServiceComboButton, 2, 7,2,1);

gridPanedeleteServiceCombo.add(deleteServiceComboLabel,1,0,2,1);
gridPanedeleteServiceCombo.add(deleteServiceComboFirstInstruction, 0,1,5,1);
gridPanedeleteServiceCombo.add(deleteServiceComboNameLabel, 0, 2);
gridPanedeleteServiceCombo.add(deleteServiceComboNameText, 1, 2);
gridPanedeleteServiceCombo.add(deleteServiceComboButton, 3,2);


verticalMenuCombo = new VBox();
verticalMenuCombo.setPadding(new Insets(10));
verticalMenuCombo.setSpacing(8);



Text serviceComboMenuTitle = new Text("What do you wish to do?");
serviceComboMenuTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 18));


verticalMenuCombo.getChildren().add(serviceComboMenuTitle);


addServiceComboLink = new Hyperlink("Add a service combo");
updateServiceComboLink = new Hyperlink("Update a service combo");
deleteServiceComboLink = new Hyperlink ("Delete a service combo");
mainMenuComboLink = new Hyperlink("Main Menu");

Hyperlink optionsCombo[] = new Hyperlink[] {
		addServiceComboLink,
		updateServiceComboLink,
		deleteServiceComboLink,
		mainMenuComboLink};

for (int i=0; i<4; i++) {
	VBox.setMargin(optionsCombo[i], new Insets(0, 0, 0, 8));
	verticalMenuCombo.getChildren().add(optionsCombo[i]);
}



serviceComboBorderPane = new BorderPane();
serviceComboBorderPane.setLeft(verticalMenuCombo);
serviceComboBorderPane.setCenter(gridPaneAddServiceCombo);


serviceComboScene = new Scene(serviceComboBorderPane);

addServiceComboLink.setOnAction(e->{
	serviceComboBorderPane.setCenter(gridPaneAddServiceCombo);
	primaryStage.setTitle("Add a service combo");
});
updateServiceComboLink.setOnAction(e->{
	serviceComboBorderPane.setCenter(gridPaneUpdateServiceCombo);
	primaryStage.setTitle("Update a service combo");
});

deleteServiceComboLink.setOnAction(e->{
	serviceComboBorderPane.setCenter(gridPanedeleteServiceCombo);
	primaryStage.setTitle("Delete a service combo");
});  

mainMenuLink.setOnAction(e->{
	primaryStage.setScene(ownerMainScene);
	primaryStage.setTitle("Main Menu");
});  

addServiceComboButton.setOnAction(e->{
	try {
		if(serviceComboNameTextField.getText()== null ||
				serviceComboNameTextField.getText().trim().isEmpty()) {
			errorAddServiceComboMessage.setText("A service combo name should be set");
		}
		if (serviceComboDurationTextField.trim().isEmpty()){
			errorAddServiceComboMessage.setText
			("A service combo duration should be set");
		}
		else if(serviceComboDowntimeDurationTextField.trim().isEmpty()){		    		
			errorAddServiceComboMessage.setText
			("A service combo downtime duration should be set");
		}
		else if(serviceComboDowntimeStartTextField.trim().isEmpty()) {
			errorAddServiceComboMessage.setText("A service combo downtime start time should be set");
		}
		else {
			//FlexiBookController
			
			FlexiBookController.addService(addServiceComboNameText.getText(),
					Integer.parseInt(addServiceComboDurationText.getText()),
					Integer.parseInt(addServiceComboDowntimeDurationText.getText()),
					Integer.parseInt(addServiceComboDowntimeStartTimeText.getText()), 
					FlexiBookApplication.getCurrentUser().getUsername());
			Alert a = new Alert(AlertType.CONFIRMATION, "Service combo added successfully");
			a.showAndWait();
			errorAddServiceMessage.setText("");
	}
	} catch (InvalidInputException e1) {
		errorAddServiceMessage.setText(e1.getMessage());
		Alert a = new Alert(AlertType.ERROR, errorAddServiceComboMessage.getText());
		a.showAndWait();
	}
});


updateServiceButton.setOnAction(e->{
//	try {
//		FlexiBookController.updateServiceCombo(FlexiBookApplication.getCurrentUser().getUsername(),
//				updateServiceComboNameText, newSCName, mainService, services, mandatory);
//		
//		FlexiBookController.updateService(serviceComboTextField.getText(),
//				Integer.parseInt(serviceComboDurationTextField),
//				Integer.parseInt(serviceComboDowntimeDurationTextField),
//				Integer.parseInt(serviceComboDowntimeStartTextField),
//				FlexiBookApplication.getCurrentUser().getUsername() , 
//				serviceComboNameTextField.getText());
//		errorUpdateServiceComboMessage.setText("");
//	} catch (InvalidInputException e1) {
//		errorUpdateServiceComboMessage.setText(e1.getMessage());
//	}
});

deleteServiceComboButton.setOnAction(e->{
	try {
		if(deleteServiceComboNameText.getText()== null || 
				deleteServiceComboNameText.getText().trim().isEmpty()) {
			errorDeleteServiceComboMessage.setText("A service combo name should be set to get deleted");
			Alert a = new Alert(AlertType.ERROR, errorDeleteServiceComboMessage.getText());
			a.showAndWait();
		}
		else {
			FlexiBookController.deleteService(deleteServiceNameText.getText(),
					FlexiBookApplication.getCurrentUser().getUsername());			  
			errorDeleteServiceComboMessage.setText("");
			Alert a = new Alert(AlertType.CONFIRMATION, "Service combo deleted successfully");
			a.showAndWait();
		}
	} catch (InvalidInputException e1) {
		errorDeleteServiceComboMessage.setText(e1.getMessage());
		Alert a = new Alert(AlertType.ERROR, errorDeleteServiceComboMessage.getText());
		a.showAndWait();
	}

});
	}
}


