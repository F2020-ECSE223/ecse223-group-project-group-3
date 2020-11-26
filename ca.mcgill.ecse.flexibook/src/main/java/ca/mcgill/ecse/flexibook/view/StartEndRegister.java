package ca.mcgill.ecse.flexibook.view;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import ca.mcgill.ecse.flexibook.model.SystemTime;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartEndRegister extends Application{



	private Button startButton;
	private Button registerButton;
	private Button endButton;
	private Scene ownerScene;
	private GridPane gridPaneOwner;
	private BorderPane root2;
	private Text errorText; 
	private Text customerName;
	private Text appointmentName;
	private Text startDate;
	private Text startTime;
	//private Text currentDateAndTime;
	private TextField customerUsernameTextField;
	private TextField appNameTextField;
	private TextField appDateTextField;
	private TextField appStartTimeTextField;
	//private TextField currentDateAndTimeTextField;
	private Hyperlink link;
	private Hyperlink viewApps;
	private VBox verticalMenuSRE;
	private Text title;
	private Text title2;

	@Override
	public void start(Stage primaryStage) throws Exception {

		startButton = new Button("Start Appointment");
		endButton = new Button("End Appointment");
		registerButton = new Button("Register no-show");
		root2 = new BorderPane(); 
		errorText = new Text(" ");
		title = new Text("Please enter the information of the appointment you want to edit.");

		customerName = new Text("Customer name");
		appointmentName = new Text("Appointment name");
		startDate = new Text("Start date");
		startTime = new Text("Start time");
		//currentDateAndTime = new Text("Current date and time");
		link = new Hyperlink("Main menu");
		viewApps = new Hyperlink("View Appointments");
		verticalMenuSRE = new VBox();
		verticalMenuSRE.setPadding(new Insets(10));
		verticalMenuSRE.setSpacing(8);

		customerUsernameTextField = new TextField();
		appNameTextField = new TextField();
		appDateTextField = new TextField();
		appStartTimeTextField = new TextField();
		//currentDateAndTimeTextField = new TextField();

		gridPaneOwner = new GridPane();  
		//gridPaneOwner.add(title, 4, 0);

		title.setFont(Font.font("Verdana", FontWeight.BOLD,22));
		gridPaneOwner.add(title, 0, 1, 7, 1);
		gridPaneOwner.add(customerName, 0, 2);
		gridPaneOwner.add(appointmentName, 2, 2);
		gridPaneOwner.add(startDate, 4, 2);
		gridPaneOwner.add(startTime, 6, 2);
		//gridPaneOwner.add(currentDateAndTime, 8, 0);
		gridPaneOwner.add(startButton,0,6);
		//startButton.setAlignment(Pos.CENTER);
		gridPaneOwner.add(registerButton,2,6);
		gridPaneOwner.add(endButton,4,6);
		gridPaneOwner.add(errorText, 4, 8);
		gridPaneOwner.add(customerUsernameTextField,0,4);
		gridPaneOwner.add(appNameTextField,2,4);
		gridPaneOwner.add(appDateTextField,4,4);
		gridPaneOwner.add(appStartTimeTextField,6,4);
		//gridPaneOwner.add(currentDateAndTimeTextField,8,2);
		//gridPaneOwner.add(link, 0, 12);

		Hyperlink optionss[] = new Hyperlink[] {
				link,
				viewApps
		};
		title2 = new Text("What do you wish to do?");
		title2.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
		verticalMenuSRE.getChildren().add(title2);
		for (int i=0; i<2; i++) {
			VBox.setMargin(optionss[i], new Insets(0, 0, 0, 8));
			verticalMenuSRE.getChildren().add(optionss[i]);
		}

		startButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		registerButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		endButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
		gridPaneOwner.setStyle("-fx-background-color: LIGHTBLUE;");
		root2.setMinSize(1100, 600); 
		root2.setMaxSize(1100, 600);
		gridPaneOwner.setVgap(10);
		gridPaneOwner.setHgap(20);
		gridPaneOwner.setPadding(new Insets(10, 10, 10, 10)); 
		root2.setCenter(gridPaneOwner);
		root2.setLeft(verticalMenuSRE);
		gridPaneOwner.setAlignment(Pos.CENTER);
		//root2.setCenter(gridPaneOwner);
		ownerScene = new Scene(root2);

		//customerName.setStyle("-fx-font: normal bold 20px 'serif' "); 
		//appointmentName.setStyle("-fx-font: normal bold 20px 'serif' "); 
		//startDate.setStyle("-fx-font: normal bold 20px 'serif' "); 
		//startTime.setStyle("-fx-font: normal bold 20px 'serif' "); 
		startDate.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
		startTime.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
		customerName.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
		appointmentName.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
		//currentDateAndTime.setStyle("-fx-font: normal bold 20px 'serif' "); 
		startButton.setOnAction(e->{
			try {
				LocalDate date = LocalDate.now();
				LocalTime time = LocalTime.now();
				Date currentDate = Date.valueOf(date);
				Time currentTime = Time.valueOf(time);
				SystemTime.setSysDate(currentDate);
				SystemTime.setSysTime(currentTime);
				FlexiBookController.startAppointment(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
				errorText.setText("");
			}catch (InvalidInputException e1) {
				errorText.setText (e1.getMessage());
			}
		});
		endButton.setOnAction(e->{
			try {
				LocalDate date2 = LocalDate.now();
				LocalTime time2 = LocalTime.now();
				Date currentDate2 = Date.valueOf(date2);
				Time currentTime2 = Time.valueOf(time2);
				SystemTime.setSysDate(currentDate2);
				SystemTime.setSysTime(currentTime2);
				FlexiBookController.endAppointment(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
				errorText.setText("");
			}catch (InvalidInputException e1) {
				errorText.setText(e1.getMessage());
			}
		});
		registerButton.setOnAction(e->{
			try {
				LocalDate date3 = LocalDate.now();
				LocalTime time3 = LocalTime.now();
				Date currentDate3 = Date.valueOf(date3);
				Time currentTime3 = Time.valueOf(time3);
				SystemTime.setSysDate(currentDate3);
				SystemTime.setSysTime(currentTime3);
				FlexiBookController.registerNoShow(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
				errorText.setText(" ");
			}catch (InvalidInputException e1) {
				errorText.setText(e1.getMessage());
			}
		});
		link.setOnAction(e->{
			primaryStage.setScene(ownerScene);//main menu scene
			primaryStage.show();
		});
		primaryStage.setTitle("StartEndRegister");
		primaryStage.setScene(ownerScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		// start UI
		launch(args);
	}
}