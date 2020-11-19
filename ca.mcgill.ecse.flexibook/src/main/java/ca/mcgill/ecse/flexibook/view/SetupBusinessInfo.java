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

import java.sql.Time;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class SetupBusinessInfo extends Application {

	private Text ownerViewBusinessInfo;
	private Text ownerViewBusinessName;
	private Text ownerViewBusinessNameResult;
	private Text ownerViewPhoneNumber;
	private Text ownerViewPhoneNumberResult;
	private Text ownerViewAddress;
	private Text ownerViewAddressResult;
	private Text ownerViewEmail;
	private Text ownerViewEmailResult;
	
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
	private GridPane gridPaneEditBusinessInfo;
	private GridPane gridPaneownerViewBusinessInfo;
	private SplitPane splitPane;
	private VBox verticalMenuownerViewBusinessInfo;
	private VBox verticalMenuEditBusinessInfo;
	private Hyperlink editBusinessInfoLink1;
	private Hyperlink ownerViewBusinessInfoLink1;
	private Hyperlink ownerBusinessInfoGoBackLink1;
	private Hyperlink ownerBusinessInfoMainMenuLink1;
	private VBox verticalMenuBusinessInfo;
	private BorderPane ownerBusinessInfoPane;
	private Scene ownerBusinessScene;


	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {


		// ownerView Business Info
		//------------------------------------------------------------------------------------------------	
		
		ownerViewBusinessInfo = new Text("View Business Information");
		ownerViewBusinessInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		ownerViewBusinessInfo.setFill(Color.BLUE);


		ownerViewBusinessName = new Text("Business Name: ");
		try {
			ownerViewBusinessNameResult = new Text(FlexiBookController.ViewBusinessInfo().get(0));
		}
		catch(InvalidInputException e) {
			ownerViewBusinessNameResult = new Text("no business name entered");
		}
		
		ownerViewBusinessName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	
		ownerViewBusinessNameResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));  

		ownerViewAddress = new Text("Address: ");
		try {
			ownerViewAddressResult = new Text(FlexiBookController.ViewBusinessInfo().get(1));
		}
		catch(InvalidInputException e) {
			ownerViewAddressResult = new Text("no address entered");
		}
		ownerViewAddress.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewAddressResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
	

		ownerViewPhoneNumber = new Text("Phone Number: ");
		try {
			ownerViewPhoneNumberResult = new Text(FlexiBookController.ViewBusinessInfo().get(2));
		}
		catch(InvalidInputException e) {
			ownerViewPhoneNumberResult = new Text("no address entered");
		}
		ownerViewPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewPhoneNumberResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewPhoneNumberResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		ownerViewEmail = new Text("E-mail: ");
		try {
			ownerViewEmailResult = new Text(FlexiBookController.ViewBusinessInfo().get(3));
		}
		catch(InvalidInputException e) {
			ownerViewEmailResult = new Text("no address entered");
		}
		ownerViewEmail.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		ownerViewEmailResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		
		// Edit Business Info
		//------------------------------------------------------------------------------------------------	
		editBusinessInfo = new Text("Edit Business Information");
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
		
		//------------------------------------------------------------------------------------------------	

		gridPaneownerViewBusinessInfo = new GridPane();
		gridPaneownerViewBusinessInfo.setMinSize(500,70);
		gridPaneownerViewBusinessInfo.setPadding(new Insets(100,100,100,100));	
		gridPaneownerViewBusinessInfo.setVgap(10);
		gridPaneownerViewBusinessInfo.setHgap(10);
		gridPaneownerViewBusinessInfo.setAlignment(Pos.CENTER);
		gridPaneownerViewBusinessInfo.setStyle("-fx-background-color: LIGHTBLUE;");
		
		gridPaneEditBusinessInfo = new GridPane();
		gridPaneEditBusinessInfo.setMinSize(500,70);
		gridPaneEditBusinessInfo.setPadding(new Insets(100,100,100,100));	
		gridPaneEditBusinessInfo.setVgap(10);
		gridPaneEditBusinessInfo.setHgap(10);
		gridPaneEditBusinessInfo.setAlignment(Pos.CENTER);
		gridPaneEditBusinessInfo.setStyle("-fx-background-color: LIGHTBLUE;");
	

		splitPane = new SplitPane();
		splitPane.setMinSize(1100, 600);
		splitPane.setMaxSize(1100, 600);
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setStyle("-fx-background-color: LIGHTBLUE;");
		
		gridPaneownerViewBusinessInfo.add(ownerViewBusinessInfo, 0, 0,2,1);
		gridPaneownerViewBusinessInfo.add(ownerViewBusinessName, 0, 2);
		gridPaneownerViewBusinessInfo.add(ownerViewBusinessNameResult, 1, 2); 
		gridPaneownerViewBusinessInfo.add(ownerViewAddress, 3, 2);
		gridPaneownerViewBusinessInfo.add(ownerViewAddressResult,4,2);
		gridPaneownerViewBusinessInfo.add(ownerViewPhoneNumber, 0, 3);
		gridPaneownerViewBusinessInfo.add(ownerViewPhoneNumberResult, 1, 3);
		gridPaneownerViewBusinessInfo.add(ownerViewEmail,3,3);
		gridPaneownerViewBusinessInfo.add(ownerViewEmailResult,4,3);   

		gridPaneEditBusinessInfo.add(editBusinessInfo, 0, 0,2,1);
		gridPaneEditBusinessInfo.add(editBusinnessInfoInstruction, 0, 1,5,1);
		gridPaneEditBusinessInfo.add(addBusinessName, 0, 2);
		gridPaneEditBusinessInfo.add(addBusinessNameText, 1, 2); 
		gridPaneEditBusinessInfo.add(addAddress, 3, 2);
		gridPaneEditBusinessInfo.add(addAddressText,4,2);
		gridPaneEditBusinessInfo.add(addPhoneNumber, 0, 3);
		gridPaneEditBusinessInfo.add(addPhoneNumberText, 1, 3);
		gridPaneEditBusinessInfo.add(addEmail,3,3);
		gridPaneEditBusinessInfo.add(addEmailText,4,3);   
		gridPaneEditBusinessInfo.add(addBusinessButton, 2, 7);
	

		verticalMenuownerViewBusinessInfo = new VBox();
		verticalMenuownerViewBusinessInfo.setPadding(new Insets(10));
		verticalMenuownerViewBusinessInfo.setSpacing(8);
		
		verticalMenuEditBusinessInfo = new VBox();
		verticalMenuEditBusinessInfo.setPadding(new Insets(10));
		verticalMenuEditBusinessInfo.setSpacing(8);
		
		verticalMenuBusinessInfo = new VBox();
		verticalMenuBusinessInfo.setPadding(new Insets(10));
		verticalMenuBusinessInfo.setSpacing(8);
	

		Text t4 = new Text("Business");
		t4.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenuBusinessInfo.getChildren().add(t4);

		
		ownerViewBusinessInfoLink1 = new Hyperlink("View Business Information");
		editBusinessInfoLink1 = new Hyperlink("Edit Business Information");
		ownerBusinessInfoGoBackLink1 = new Hyperlink("Go Back");
		ownerBusinessInfoMainMenuLink1 = new Hyperlink("Main Menu");

		Hyperlink op1[] = new Hyperlink[] {
				ownerViewBusinessInfoLink1,
				editBusinessInfoLink1,
				ownerBusinessInfoGoBackLink1,
				ownerBusinessInfoMainMenuLink1};

		for (int i=0; i<4; i++) {
			VBox.setMargin(op1[i], new Insets(0, 0, 0, 8));
			verticalMenuBusinessInfo.getChildren().add(op1[i]);
		}
		

		ownerBusinessInfoPane = new BorderPane();
		ownerBusinessInfoPane.setLeft(verticalMenuBusinessInfo);
		ownerBusinessInfoPane.setCenter(gridPaneownerViewBusinessInfo);	
	
		ownerBusinessScene  = new Scene(ownerBusinessInfoPane);


		ownerViewBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("View Business Information");
			ownerBusinessInfoPane.setCenter(gridPaneownerViewBusinessInfo);
		});
		editBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("Edit Business Information");
			ownerBusinessInfoPane.setCenter(gridPaneEditBusinessInfo);
		});
//		ownerBusinessInfoGoBackLink1.setOnAction(e->{
//			primaryStage.setScene(ownerViewBusinessScene);
//		});
//		ownerBusinessInfoMainMenuLink1.setOnAction(e->{
//			primaryStage.setScene(ownerViewBusinessScene);
		

		addBusinessButton.setOnAction(e->{
			try {
				FlexiBookController.SetUpContactInfo(addBusinessName.getText(), addAddress.getText(), addPhoneNumber.getText(), addEmail.getText());
				errorBusinessInfoMessage.setText("");
			} catch (InvalidInputException e1) {
				errorBusinessInfoMessage.setText(e1.getMessage());
			}
		});

		primaryStage.setScene(ownerBusinessScene);
		primaryStage.show();

	}
}