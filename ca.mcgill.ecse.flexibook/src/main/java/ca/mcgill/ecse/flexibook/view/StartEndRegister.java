package ca.mcgill.ecse.flexibook.view;

//public class StartEndRegister {
	import ca.mcgill.ecse.flexibook.model.FlexiBook;
	import ca.mcgill.ecse.flexibook.model.User;
	import ca.mcgill.ecse.flexibook.persistence.FlexiBookPersistence;
	import ca.mcgill.ecse.flexibook.view.FlexiBookPage;
	import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
	import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
	import javafx.application.Application;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.GridPane;
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
		private Text currentDateAndTime;
		private TextField usernameTextField;
		private TextField appNameTextField;
		private TextField appDateTextField;
		private TextField appStartTimeTextField;
		private TextField currentDateAndTimeTextField;

		@Override
		public void start(Stage primaryStage) throws Exception {
			
			startButton = new Button("Start Appointment");
			endButton = new Button("End Appointment");
			registerButton = new Button("Register no-show");
	  		root2 = new BorderPane(); 
	  		errorText = new Text(" ");
	  		customerName = new Text("Customer name");
	  		appointmentName = new Text("Appointment name");
	  		startDate = new Text("Start date");
	  		startTime = new Text("Start time");
	  		currentDateAndTime = new Text("Current date and time");
	  		
	  		usernameTextField = new TextField();
	  		appNameTextField = new TextField();
	  		appDateTextField = new TextField();
	  		appStartTimeTextField = new TextField();
	  		currentDateAndTimeTextField = new TextField();
	  		
	  		gridPaneOwner = new GridPane();   
	  		gridPaneOwner.add(customerName, 0, 0);
	  		gridPaneOwner.add(appointmentName, 2, 0);
	  		gridPaneOwner.add(startDate, 4, 0);
	  		gridPaneOwner.add(startTime, 6, 0);
	  		gridPaneOwner.add(currentDateAndTime, 8, 0);
	  		gridPaneOwner.add(startButton,2,4);
	  		gridPaneOwner.add(registerButton,4,4);
	  		gridPaneOwner.add(endButton,6,4);
	  		gridPaneOwner.add(errorText, 4, 6);
	  		gridPaneOwner.add(usernameTextField,0,2);
	  		gridPaneOwner.add(appNameTextField,2,2);
	  		gridPaneOwner.add(appDateTextField,4,2);
	  		gridPaneOwner.add(appStartTimeTextField,6,2);
	  		gridPaneOwner.add(currentDateAndTimeTextField,8,2);
	  		startButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
	  		registerButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
	  		endButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
	  		root2.setStyle("-fx-background-color: CYAN;");
	  		root2.setMinSize(1100, 600); 
	  		root2.setMaxSize(1100, 600);
	  		gridPaneOwner.setVgap(10);
	  		gridPaneOwner.setHgap(20);  
	  		gridPaneOwner.setPadding(new Insets(10, 10, 10, 10)); 
	  		root2.setCenter(gridPaneOwner);
	  		gridPaneOwner.setAlignment(Pos.CENTER);
	  		root2.setCenter(gridPaneOwner);
	  		ownerScene = new Scene(root2);
	  		
	   	    customerName.setStyle("-fx-font: normal bold 20px 'serif' "); 
	   	    appointmentName.setStyle("-fx-font: normal bold 20px 'serif' "); 
	   	    startDate.setStyle("-fx-font: normal bold 20px 'serif' "); 
	   	    startTime.setStyle("-fx-font: normal bold 20px 'serif' "); 
	   	    currentDateAndTime.setStyle("-fx-font: normal bold 20px 'serif' "); 
	  		startButton.setOnAction(e->{
	  			try {
	  				FlexiBookController.startAppointment(usernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
	  				errorText.setText("");
	  			}catch (InvalidInputException e1) {
	  				errorText.setText (e1.getMessage());
	  			}
	  		});
	  		endButton.setOnAction(e->{
	  			try {
	  				FlexiBookController.endAppointment(usernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
	  				errorText.setText("");
	  			}catch (InvalidInputException e1) {
	  				errorText.setText(e1.getMessage());
	  			}
	  		});
	  		registerButton.setOnAction(e->{
	  			try {
	  				FlexiBookController.registerNoShow(usernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText());
	  				errorText.setText(" ");
	  			}catch (InvalidInputException e1) {
	  				errorText.setText(e1.getMessage());
	  			}
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