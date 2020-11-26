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


public class customerBusinessInformation extends Application {

	private Text customerViewBusinessInfo;
	private Text customerViewBusinessName;
	private Text customerViewBusinessNameResult;
	private Text customerViewPhoneNumber;
	private Text customerViewPhoneNumberResult;
	private Text customerViewAddress;
	private Text customerViewAddressResult;
	private Text customerViewEmail;
	private Text customerViewEmailResult;
	

	private GridPane gridPanecustomerViewBusinessInfo;
	private SplitPane splitPane7;
	private VBox verticalMenucustomerViewBusinessInfo;
	private Hyperlink customerViewBusinessInfoLink1;
	private Hyperlink customerBusinessInfoGoBackLink1;
	private Hyperlink customerBusinessInfoMainMenuLink1;
	private BorderPane customerViewBusinessInfoPane;
	private Scene customerViewBusinessScene;


	
	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {


		// customerView Business Info
		//------------------------------------------------------------------------------------------------	
		
		customerViewBusinessInfo = new Text("View Business Information");
		customerViewBusinessInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		customerViewBusinessInfo.setFill(Color.BLUE);


		customerViewBusinessName = new Text("Business Name: ");
		try {
			customerViewBusinessNameResult = new Text(FlexiBookController.ViewBusinessInfo().get(0));
		}
		catch(InvalidInputException e) {
			customerViewBusinessNameResult = new Text("no business name entered");
		}
		
		customerViewBusinessName.setFont(Font.font("Verdana", FontWeight.NORMAL,15));   	
		customerViewBusinessNameResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));  

		customerViewAddress = new Text("Address: ");
		try {
			customerViewAddressResult = new Text(FlexiBookController.ViewBusinessInfo().get(1));
		}
		catch(InvalidInputException e) {
			customerViewAddressResult = new Text("no address entered");
		}
		customerViewAddress.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewAddressResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
	

		customerViewPhoneNumber = new Text("Phone Number: ");
		try {
			customerViewPhoneNumberResult = new Text(FlexiBookController.ViewBusinessInfo().get(2));
		}
		catch(InvalidInputException e) {
			customerViewPhoneNumberResult = new Text("no address entered");
		}
		customerViewPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewPhoneNumberResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewPhoneNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewPhoneNumberResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		customerViewEmail = new Text("E-mail: ");
		try {
			customerViewEmailResult = new Text(FlexiBookController.ViewBusinessInfo().get(3));
		}
		catch(InvalidInputException e) {
			customerViewEmailResult = new Text("no address entered");
		}
		customerViewEmail.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		customerViewEmailResult.setFont(Font.font("Verdana", FontWeight.NORMAL,15));
		
		
		//------------------------------------------------------------------------------------------------	

		gridPanecustomerViewBusinessInfo = new GridPane();
		gridPanecustomerViewBusinessInfo.setMinSize(500,70);
		gridPanecustomerViewBusinessInfo.setPadding(new Insets(100,100,100,100));	
		gridPanecustomerViewBusinessInfo.setVgap(10);
		gridPanecustomerViewBusinessInfo.setHgap(10);
		gridPanecustomerViewBusinessInfo.setAlignment(Pos.CENTER);
		gridPanecustomerViewBusinessInfo.setStyle("-fx-background-color: LIGHTBLUE;");
	

		splitPane7 = new SplitPane();
		splitPane7.setMinSize(1100, 600);
		splitPane7.setMaxSize(1100, 600);
		splitPane7.setOrientation(Orientation.VERTICAL);
		splitPane7.setStyle("-fx-background-color: LIGHTBLUE;");
		
		gridPanecustomerViewBusinessInfo.add(customerViewBusinessInfo, 0, 0,2,1);
		gridPanecustomerViewBusinessInfo.add(customerViewBusinessName, 0, 2);
		gridPanecustomerViewBusinessInfo.add(customerViewBusinessNameResult, 1, 2); 
		gridPanecustomerViewBusinessInfo.add(customerViewAddress, 3, 2);
		gridPanecustomerViewBusinessInfo.add(customerViewAddressResult,4,2);
		gridPanecustomerViewBusinessInfo.add(customerViewPhoneNumber, 0, 3);
		gridPanecustomerViewBusinessInfo.add(customerViewPhoneNumberResult, 1, 3);
		gridPanecustomerViewBusinessInfo.add(customerViewEmail,3,3);
		gridPanecustomerViewBusinessInfo.add(customerViewEmailResult,4,3);   
	

		verticalMenucustomerViewBusinessInfo = new VBox();
		verticalMenucustomerViewBusinessInfo.setPadding(new Insets(10));
		verticalMenucustomerViewBusinessInfo.setSpacing(8);
	
	

		Text t6 = new Text("Business");
		t6.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		verticalMenucustomerViewBusinessInfo.getChildren().add(t6);

		
		customerViewBusinessInfoLink1 = new Hyperlink("View Business Information");
		customerBusinessInfoGoBackLink1 = new Hyperlink("Go Back");
		customerBusinessInfoMainMenuLink1 = new Hyperlink("Main Menu");
		

		Hyperlink optio1[] = new Hyperlink[] {
				customerViewBusinessInfoLink1,
				customerBusinessInfoGoBackLink1,
				customerBusinessInfoMainMenuLink1};

		for (int i=0; i<3; i++) {
			VBox.setMargin(optio1[i], new Insets(0, 0, 0, 8));
			verticalMenucustomerViewBusinessInfo.getChildren().add(optio1[i]);
		}
	

		customerViewBusinessInfoPane = new BorderPane();
		customerViewBusinessInfoPane.setLeft(verticalMenucustomerViewBusinessInfo);
		customerViewBusinessInfoPane.setCenter(gridPanecustomerViewBusinessInfo);
		
	
		customerViewBusinessScene  = new Scene(customerViewBusinessInfoPane);


		customerViewBusinessInfoLink1.setOnAction(e->{
			primaryStage.setTitle("customerView Business Information");
			primaryStage.setScene(customerViewBusinessScene);
		});
//		customerBusinessInfoGoBackLink1.setOnAction(e->{
//			primaryStage.setScene(customerViewBusinessScene);
//		});
//		customerBusinessInfoGoBackLink2.setOnAction(e->{
//			primaryStage.setScene(editBusinessScene);
//		});	
//		customerBusinessInfoMainMenuLink1.setOnAction(e->{
//			primaryStage.setScene(customerViewBusinessScene);
//		});
//		customerBusinessInfoMainMenuLink2.setOnAction(e->{
//			primaryStage.setScene(editBusinessScene);
//		});	
		


		primaryStage.setScene(customerViewBusinessScene);
		primaryStage.show();

	}
}