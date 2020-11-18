package ca.mcgill.ecse223.flexibook.view;

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

import java.sql.Time;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class SetupBusinessInfo extends Application {

	private Text errorBusinessInfoMessage;
	private Text editBusinessInfo;
	private Text editBusinnessInfoInstruction;
	private Text addBusinessName;
	private TextField addBusinessNameText;
	private Text addAddress;
	private TextField addAddressText;
	private Text addPhoneNumber;
	private TextField addPhoneNumberText;
	private Text addEmail;
	private TextField addEmailText;
	private Button addBusinessButton;
	private GridPane gridPaneBusinessInfo;
	private GridPane gridPaneAddHours;
	private SplitPane splitPane;
	private VBox verticalMenuBusinessInfo;
	private Hyperlink editBusinessInfoLink1;
	private BorderPane addBusinessInfoPane;
	private Scene businessScene;


	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {


		editBusinessInfo = new Text("View/Edit Business Information");
		editBusinessInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		editBusinessInfo.setFill(Color.BLUE);
		editBusinnessInfoInstruction = new Text("Please enter the information of your business.");
		editBusinnessInfoInstruction.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		errorBusinessInfoMessage = new Text("");
		errorBusinessInfoMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		errorBusinessInfoMessage.setFill(Color.RED);


		addBusinessName = new Text("Business Name: ");
		addBusinessNameText = new TextField();
		addBusinessName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	


		addAddress = new Text("Address: ");
		addAddressText = new TextField();
		addAddress.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addPhoneNumber = new Text("Phone Number: ");
		addPhoneNumberText = new TextField();
		addPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));

		addEmail = new Text("E-mail: ");
		addEmailText = new TextField();
		addEmail.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		try {
			addBusinessNameText.setPromptText(FlexiBookController.ViewBusinessInfo().get(0));
		}
		catch(InvalidInputException e) {
			addBusinessNameText.setPromptText("no business name entered");
		}
		try {
			addAddressText.setPromptText(FlexiBookController.ViewBusinessInfo().get(1));
		}
		catch(InvalidInputException e) {
			addAddressText.setPromptText("no address entered");
		}
		try {
			addPhoneNumberText.setPromptText(FlexiBookController.ViewBusinessInfo().get(2));
		}
		catch(InvalidInputException e) {
			addPhoneNumberText.setPromptText("no phone number entered");
		}
		try {
			addEmailText.setPromptText(FlexiBookController.ViewBusinessInfo().get(3));
		}
		catch(InvalidInputException e) {
			addEmailText.setPromptText("no e-mail entered");
		}

		addBusinessButton = new Button("Confirm");


		gridPaneBusinessInfo = new GridPane();
		gridPaneBusinessInfo.setMinSize(500,70);
		gridPaneBusinessInfo.setPadding(new Insets(100,100,100,100));	
		gridPaneBusinessInfo.setVgap(10);
		gridPaneBusinessInfo.setHgap(10);
		gridPaneBusinessInfo.setAlignment(Pos.CENTER);
		gridPaneBusinessInfo.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneAddHours = new GridPane();
		gridPaneAddHours.setMinSize(800, 130);
		gridPaneAddHours.setPadding(new Insets(100, 100, 100, 100));	
		gridPaneAddHours.setVgap(10);
		gridPaneAddHours.setHgap(10);
		gridPaneAddHours.setAlignment(Pos.CENTER);
		gridPaneAddHours.setStyle("-fx-background-color: LIGHTBLUE;");

		splitPane = new SplitPane();
		splitPane.setMinSize(1100, 600);
		splitPane.setMaxSize(1100, 600);
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setStyle("-fx-background-color: LIGHTBLUE;");

		gridPaneBusinessInfo.add(editBusinessInfo, 0, 0,2,1);
		gridPaneBusinessInfo.add(editBusinnessInfoInstruction, 0, 1,5,1);
		gridPaneBusinessInfo.add(addBusinessName, 0, 2);
		gridPaneBusinessInfo.add(addBusinessNameText, 1, 2); 
		gridPaneBusinessInfo.add(addAddress, 3, 2);
		gridPaneBusinessInfo.add(addAddressText,4,2);
		gridPaneBusinessInfo.add(addPhoneNumber, 0, 3);
		gridPaneBusinessInfo.add(addPhoneNumberText, 1, 3);
		gridPaneBusinessInfo.add(addEmail,3,3);
		gridPaneBusinessInfo.add(addEmailText,4,3);   
		gridPaneBusinessInfo.add(addBusinessButton, 2, 7);
	

		verticalMenuBusinessInfo = new VBox();
		verticalMenuBusinessInfo.setPadding(new Insets(10));
		verticalMenuBusinessInfo.setSpacing(8);

		Text title = new Text("Business");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuBusinessInfo.getChildren().add(title);



		editBusinessInfoLink1 = new Hyperlink("View/Edit Business Information");

		Hyperlink options1[] = new Hyperlink[] {
				editBusinessInfoLink1};

		for (int i=0; i<1; i++) {
			VBox.setMargin(options1[i], new Insets(0, 0, 0, 8));
			verticalMenuBusinessInfo.getChildren().add(options1[i]);
		}

		addBusinessInfoPane = new BorderPane();
		addBusinessInfoPane.setLeft(verticalMenuBusinessInfo);
		addBusinessInfoPane.setCenter(gridPaneBusinessInfo);
	
		businessScene = new Scene(addBusinessInfoPane);


		editBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("View/Edit Business Information");
			primaryStage.setScene(businessScene);
		});

		addBusinessButton.setOnAction(e->{
			try {
				FlexiBookController.SetUpContactInfo(addBusinessName.getText(), addAddress.getText(), addPhoneNumber.getText(), addEmail.getText());
				errorBusinessInfoMessage.setText("");
			} catch (InvalidInputException e1) {
				errorBusinessInfoMessage.setText(e1.getMessage());
			}
		});

		primaryStage.setScene(businessScene);
		primaryStage.show();

	}
}