package ca.mcgill.ecse.flexibook.view;

	import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
//import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.SystemTime;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
//import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour.TODayOfWeek;
	import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse223.flexibook.controller.TOAppointment;
import ca.mcgill.ecse223.flexibook.controller.TOCustomer;
//import ca.mcgill.ecse223.flexibook.controller.TOBusinessHour.DayOfWeek;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
	import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
	import javafx.stage.Stage;


public class StartEndRegister extends Application{
	  
	   
//	    
//	    private Button startButton;
//	    private Button registerButton;
//	    private Button endButton;
//	    private Button secondButton;
//		private Scene ownerScene;
//		private Scene secondScene;
//		private GridPane gridPaneOwner;
//		private GridPane grid2;		
//		private BorderPane root2;
//		private Text errorText; 
//		private Text customerName;
//		private Text appointmentName;
//		private Text startDate;
//		private Text startTime;
//		//private Text currentDateAndTime;
//		private TextField customerUsernameTextField;
//		private TextField appNameTextField;
//		private TextField appDateTextField;
//		private TextField appStartTimeTextField;
//		//private TextField currentDateAndTimeTextField;
//		private Hyperlink link;
//		private Hyperlink viewApps;
//		private VBox verticalMenuSRE;
//		private Text title;
//		private Text title2;
//		private Text title3;
	private Button startButton;
	private Button registerButton;
	private Button endButton;
	//private Scene ownerAppScene;
	private GridPane gridPaneOwner;
	private BorderPane root2;
//	private Text errorText; 
//	private Text customerName;
//	private Text appointmentName;
//	private Text startDate;
//	private Text startTime;
//	private TextField customerUsernameTextField;
//	private TextField appNameTextField;
//	private TextField appDateTextField;
//	private TextField appStartTimeTextField;
	private Hyperlink startEndLink;
	private Hyperlink link;
	private Hyperlink viewCustomers;
	private Hyperlink viewApps;
	private VBox verticalMenuSRE;
	private Text title;
	private Text title2;
	private ComboBox<String> boxAppointments;
	private Text appointmentsEndStart;
	//private String endStartRegisterInfo;
	private String endStartRegisterCustName;
	private String endStartRegisterServiceName;
	private String endStartRegisterStartTime;
	private String endStartRegisterStartDay;
	

	//Appointments Table
//	private TableView<TOAppointment> appTable;
//	private TableColumn<TOAppointment, String> customerNameCol;
//	private TableColumn<TOAppointment, String> appServiceNameCol;
//	private TableColumn<TOAppointment, Time> startTimeCol;
//	private TableColumn<TOAppointment, Time> endTimeCol;
//	private TableColumn<TOAppointment, Date> dateCol;
//	
//	//Customer Table
//	private TableView<TOCustomer> customerTable;
//	private TableColumn<TOCustomer, String> usernameCol;
//	private TableColumn<TOCustomer, Integer> noShowCol;

		@Override
		public void start(Stage primaryStage) throws Exception {
			
//			startButton = new Button("Start Appointment");
//			endButton = new Button("End Appointment");
//			registerButton = new Button("Register no-show");
//			secondButton = new Button("hi");
//	  		root2 = new BorderPane(); 
//	  		errorText = new Text(" ");
//	  		title = new Text("Please enter the information of the appointment you want to edit.");
//	  		
//	  		customerName = new Text("Customer name");
//	  		appointmentName = new Text("Appointment name");
//	  		startDate = new Text("Start date");
//	  		startTime = new Text("Start time");
//	  		//currentDateAndTime = new Text("Current date and time");
//	  		link = new Hyperlink("Main menu");
//	  		viewApps = new Hyperlink("View Appointments");
//	  		verticalMenuSRE = new VBox();
//			verticalMenuSRE.setPadding(new Insets(10));
//			verticalMenuSRE.setSpacing(8);
//			
//			
//	  		customerUsernameTextField = new TextField();
//	  		appNameTextField = new TextField();
//	  		appDateTextField = new TextField();
//	  		appStartTimeTextField = new TextField();
//	  		//currentDateAndTimeTextField = new TextField();
//	  		
//	  		gridPaneOwner = new GridPane();  
//	  		grid2 = new GridPane();  
//
//	  		//Image image2 = new Image("https://visme.co/blog/wp-content/uploads/2017/07/50-Beautiful-and-Minimalist-Presentation-Backgrounds-029.jpg");
//	  	    Image image1 = new Image("http://www.desktopimages.org/pictures/2013/0717/1/orig_433313.jpg");
//
//	  	    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
////
////	  	    Background background2 = new Background(new BackgroundImage(image2,
////	  	            BackgroundRepeat.NO_REPEAT,
////	  	            BackgroundRepeat.NO_REPEAT,
////	  	            BackgroundPosition.CENTER,
////	  	            bSize));
////	  	    
////	  	  root2.setBackground(new Background(new BackgroundImage(image1,
////	              BackgroundRepeat.NO_REPEAT,
////	              BackgroundRepeat.NO_REPEAT,
////	              BackgroundPosition.CENTER,
////	              bSize)));  
//	  	    
//	  	  gridPaneOwner.setBackground(new Background(new BackgroundImage(image1,
//	              BackgroundRepeat.NO_REPEAT,
//	              BackgroundRepeat.NO_REPEAT,
//	              BackgroundPosition.CENTER,
//	              bSize)));
//	  		
//	  		//gridPaneOwner.add(title, 4, 0);
//	  		
//	  		title.setFont(Font.font("Verdana", FontWeight.BOLD,22));
//	  		gridPaneOwner.add(title, 0, 1, 7, 1);
//	  		gridPaneOwner.add(customerName, 0, 2);
//	  		gridPaneOwner.add(appointmentName, 2, 2);
//	  		gridPaneOwner.add(startDate, 4, 2);
//	  		gridPaneOwner.add(startTime, 6, 2);
//	  		//gridPaneOwner.add(currentDateAndTime, 8, 0);
//	  		gridPaneOwner.add(startButton,0,6);
//	  		//startButton.setAlignment(Pos.CENTER);
//	  		gridPaneOwner.add(registerButton,2,6);
//	  		gridPaneOwner.add(endButton,4,6);
//	  		//grid2.add(secondButton, 0, 0);
//	  		gridPaneOwner.add(errorText, 4, 8);
//	  		gridPaneOwner.add(customerUsernameTextField,0,4);
//	  		gridPaneOwner.add(appNameTextField,2,4);
//	  		gridPaneOwner.add(appDateTextField,4,4);
//	  		gridPaneOwner.add(appStartTimeTextField,6,4);
//	  		//gridPaneOwner.add(currentDateAndTimeTextField,8,2);
//	  		//gridPaneOwner.add(link, 0, 12);
//	  		
//	  		Hyperlink optionss[] = new Hyperlink[] {
//					link,
//					viewApps
//					};
//	  		title2 = new Text("What do you wish to do?");
//	  		title3 = new Text(" ");
//	  		title3.setFont(Font.font("Verdana", FontWeight.NORMAL, 35));
//			title2.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
//			verticalMenuSRE.getChildren().add(title3);
//			verticalMenuSRE.getChildren().add(title2);
//	  		for (int i=0; i<2; i++) {
//				VBox.setMargin(optionss[i], new Insets(0, 0, 0, 8));
//				verticalMenuSRE.getChildren().add(optionss[i]);
//			}
//	  		
//	  		startButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
//	  		registerButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
//	  		endButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
//	  		//gridPaneOwner.setStyle("-fx-background-color: LIGHTBLUE;");
//	  		root2.setMinSize(1000, 600); 
//	  		root2.setMaxSize(1000, 600);
//	  		grid2.setMinSize(0, 0);
//	  		grid2.setMaxSize(0, 0);
//	  		gridPaneOwner.setVgap(10);
//	  		gridPaneOwner.setHgap(20);
//	  		gridPaneOwner.setPadding(new Insets(10, 10, 10, 10)); 
//	  		root2.setTop(grid2);
//	  		root2.setCenter(gridPaneOwner);
//	  		root2.setLeft(verticalMenuSRE);
//	  		gridPaneOwner.setAlignment(Pos.CENTER);
//	  		//root2.setCenter(gridPaneOwner);
//	  		ownerScene = new Scene(root2);
//	  		//secondScene = new Scene(grid2);
//	  		
//	   	    //customerName.setStyle("-fx-font: normal bold 20px 'serif' "); 
//	   	    //appointmentName.setStyle("-fx-font: normal bold 20px 'serif' "); 
//	   	    //startDate.setStyle("-fx-font: normal bold 20px 'serif' "); 
//	   	    //startTime.setStyle("-fx-font: normal bold 20px 'serif' "); 
//	   	    startDate.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
//	   	    startTime.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
//	   	    customerName.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
//	   	    appointmentName.setFont(Font.font("Verdana", FontWeight.NORMAL,20));
//	   	    //currentDateAndTime.setStyle("-fx-font: normal bold 20px 'serif' "); 
//	  		startButton.setOnAction(e->{
//	  			Alert alert2 = new Alert(AlertType.CONFIRMATION, "Are you sure you want to start that appointment?", ButtonType.YES, ButtonType.NO);
//	  			alert2.showAndWait();
//	  			if (alert2.getResult() == ButtonType.YES) {
//	  			try {
//	  				
//	  				LocalDate date = LocalDate.now();
//	  				LocalTime time = LocalTime.now();
//	  				Date currentDate = Date.valueOf(date);
//	  				Time currentTime = Time.valueOf(time);
//	  				SystemTime.setSysDate(currentDate);
//	  				SystemTime.setSysTime(currentTime);
//	  				FlexiBookController.login("owner", "owner");
//	  				Time t = Time.valueOf("01:00:00");
//	  				Time t2 = Time.valueOf("23:00:00");
//	  				
////	  				FlexiBookController.SetUpBusinessHours(DayOfWeek.Sunday, t, t2);
////	  				FlexiBookController.addService("wash", 20, 0, 0, "owner");
////	  				FlexiBookController.logout();
////	  				FlexiBookController.signUpCustomerAccount("robertoo", "labboo");
////	  				FlexiBookController.login("robertoo", "labboo");
////	  				FlexiBookController.addService("wash", 20, 0, 0, "owner");
////	  				FlexiBookController.makeAppointment("robertoo", "wash", null, "2020-11-22", "07:15");
////	  				FlexiBookController.logout();
////	  				FlexiBookController.login("owner", "owner");
//	  				//FlexiBookController.startAppointment(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
//	  				FlexiBookController.startAppointment("robertoo", "wash", "2020-11-22", "07:15");
//	  				errorText.setText("Appointment started succesfully");
//	  			}catch (InvalidInputException e1) {
//	  				errorText.setText (e1.getMessage());
//	  			}
//	  			}
//	  		});
//	  		endButton.setOnAction(e->{
//	  			Alert alert3 = new Alert(AlertType .CONFIRMATION, "Are you sure you want to end that appointment?", ButtonType.YES, ButtonType.NO);
//	  			alert3.showAndWait();
//	  			if (alert3.getResult() == ButtonType.YES) {
//	  			try {
//	  				LocalDate date2 = LocalDate.now();
//	  				LocalTime time2 = LocalTime.now();
//	  				Date currentDate2 = Date.valueOf(date2);
//	  				Time currentTime2 = Time.valueOf(time2);
//	  				SystemTime.setSysDate(currentDate2);
//	  				SystemTime.setSysTime(currentTime2);
//	  				FlexiBookController.endAppointment(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(), appStartTimeTextField.getText());
//	  				errorText.setText("");
//	  			}catch (InvalidInputException e1) {
//	  				errorText.setText(e1.getMessage());
//	  			}
//	  			}
//	  		});
//	  		registerButton.setOnAction(e->{
//	  			Alert alert4 = new Alert(AlertType.CONFIRMATION, "Are you sure about registering a no-show for that appointment?", ButtonType.YES, ButtonType.NO);
//	  			alert4.showAndWait();
//	  			if (alert4.getResult() == ButtonType.YES) {
//	  			try {
//	  				LocalDate date3 = LocalDate.now();
//	  				LocalTime time3 = LocalTime.now();
//	  				Date currentDate3 = Date.valueOf(date3);
//	  				Time currentTime3 = Time.valueOf(time3);
//	  				SystemTime.setSysDate(currentDate3);
//	  				SystemTime.setSysTime(currentTime3);
//	  				FlexiBookController.registerNoShow(customerUsernameTextField.getText() , appNameTextField.getText(), appDateTextField.getText(),appStartTimeTextField.getText());
//	  				errorText.setText("");
//	  			}catch (InvalidInputException e1) {
//	  				errorText.setText(e1.getMessage());
//	  			}
//	  			}
//	  		});
//	  		link.setOnAction(e->{ 
//	  			primaryStage.setScene(ownerScene);//main menu scene
//		   	    primaryStage.show();
//	  		}); 
//	  		primaryStage.setTitle("StartEndRegister");
//	   	    primaryStage.setScene(ownerScene);
//	   	    primaryStage.show();
////	   	 primaryStage.setTitle("secondScene");
////	   	    primaryStage.setScene(secondScene);
////	   	    primaryStage.show();
			startButton = new Button("Start Appointment");
			endButton = new Button("End Appointment");
			registerButton = new Button("Register no-show");
			root2 = new BorderPane(); 
			//errorText = new Text(" ");
			title = new Text("Please choose the appointment you want to edit.");
//			boxAppointments = new ComboBox<>();
//			appointmentsEndStart = new Text("Appointment: ");

//			customerName = new Text("Customer name");
//			appointmentName = new Text("Appointment name");
//			startDate = new Text("Start date");
//			startTime = new Text("Start time");
//			
			startEndLink = new Hyperlink("Start/End/Register No-Show");
			link = new Hyperlink("Main menu");
			viewApps = new Hyperlink("View Appointments");
			viewCustomers = new Hyperlink("View Customers");

			verticalMenuSRE = new VBox();
			verticalMenuSRE.setPadding(new Insets(10));
			verticalMenuSRE.setSpacing(8);

//			customerUsernameTextField = new TextField();
//			appNameTextField = new TextField();
//			appDateTextField = new TextField();
//			appStartTimeTextField = new TextField();

			gridPaneOwner = new GridPane();  

			title.setFont(Font.font("Comforta", FontWeight.BOLD,22));
//			gridPaneOwner.add(title, 0, 1, 7, 1);
//			gridPaneOwner.add(customerName, 0, 2);
//			gridPaneOwner.add(appointmentName, 2, 2);
//			gridPaneOwner.add(startDate, 4, 2);
//			gridPaneOwner.add(startTime, 6, 2);
//			gridPaneOwner.add(startButton,0,6);
//			gridPaneOwner.add(registerButton,2,6);
//			gridPaneOwner.add(endButton,4,6);
//			gridPaneOwner.add(errorText, 4, 8);
//			gridPaneOwner.add(customerUsernameTextField,0,4);
//			gridPaneOwner.add(appNameTextField,2,4);
//			gridPaneOwner.add(appDateTextField,4,4);
//			gridPaneOwner.add(appStartTimeTextField,6,4);
			gridPaneOwner.add(title, 0, 1, 7, 1);
			gridPaneOwner.add(appointmentsEndStart, 0, 2);
			gridPaneOwner.add(boxAppointments, 2, 2,2,1);
//			gridPaneOwner.add(startDate, 4, 2);
//			gridPaneOwner.add(startTime, 6, 2);
			gridPaneOwner.add(startButton,0,6);
			gridPaneOwner.add(registerButton,2,6);
			gridPaneOwner.add(endButton,3,6);
//			gridPaneOwner.add(errorText, 4, 8);
//			gridPaneOwner.add(customerUsernameTextField,0,4);
//			gridPaneOwner.add(appNameTextField,2,4);
//			gridPaneOwner.add(appDateTextField,4,4);
//			gridPaneOwner.add(appStartTimeTextField,6,4);


			Hyperlink optionss[] = new Hyperlink[] {
					startEndLink,
					viewApps,
					viewCustomers,
					link
			};
			title2 = new Text("What do you wish to do?");
			title2.setFont(Font.font("Comforta", FontWeight.NORMAL, 15));
			verticalMenuSRE.getChildren().add(title2);
			for (int i=0; i<4; i++) {
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
			//ownerAppScene = new Scene(root2);

	//
//			startDate.setFont(Font.font("Comforta", FontWeight.NORMAL,20));
//			startTime.setFont(Font.font("Comforta", FontWeight.NORMAL,20));
//			customerName.setFont(Font.font("Comforta", FontWeight.NORMAL,20));
//			appointmentName.setFont(Font.font("Comforta", FontWeight.NORMAL,20));
			
			//Customer table
//			usernameCol = new TableColumn<TOCustomer, String>("Customer name");
//			usernameCol.setMinWidth(440);
//			usernameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//			
//			noShowCol = new TableColumn<TOCustomer, Integer>("Number of no-shows");
//			noShowCol.setMinWidth(450);
//			noShowCol.setCellValueFactory(new PropertyValueFactory<>("noShow"));
//		
//			customerTable = new TableView<TOCustomer>();
//			customerTable.setItems(getCustomersData());
//			customerTable.getColumns().addAll(usernameCol, noShowCol);
			

			startButton.setOnAction(e->{
				Alert alert3 = new Alert(AlertType.WARNING, "Are you sure you want to start that appointment?", ButtonType.YES, ButtonType.NO);
				alert3.showAndWait();
				if (alert3.getResult() == ButtonType.YES) {	
					
//					endStartRegisterInfo = (String) boxAppointments.getValue();
//					String[] myArrayEndStartRegisterApp = endStartRegisterInfo.split(", ");
//					List<String> endStartRegisterAppInfos = new ArrayList<>();
//
//					for (String str : myArrayEndStartRegisterApp) {
//						endStartRegisterAppInfos.add(str);
//					}
//					
//					endStartRegisterCustName = endStartRegisterAppInfos.get(0);
//					endStartRegisterServiceName = endStartRegisterAppInfos.get(1);
//					endStartRegisterStartDay = endStartRegisterAppInfos.get(2);
//					endStartRegisterStartTime = endStartRegisterAppInfos.get(3);

					try {
						FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
						FlexiBookController.startAppointment(endStartRegisterCustName , endStartRegisterServiceName, endStartRegisterStartDay, endStartRegisterStartTime);
						Alert a = new Alert(AlertType.CONFIRMATION, "Appointment Started");
						a.showAndWait();
						//refreshEndStartRegisterComboBox();
					}catch (InvalidInputException e1) {
						Alert a = new Alert(AlertType.ERROR, e1.getMessage());
						a.showAndWait();			
					}
				}
			});
			endButton.setOnAction(e->{
				Alert alert3 = new Alert(AlertType.WARNING, "Are you sure you want to end that appointment?", ButtonType.YES, ButtonType.NO);
				alert3.showAndWait();
				if (alert3.getResult() == ButtonType.YES) {	
					try {
//						endStartRegisterInfo = (String) boxAppointments.getValue();
//						String[] myArrayEndStartRegisterApp = endStartRegisterInfo.split(", ");
//						List<String> endStartRegisterAppInfos = new ArrayList<>();
//
//						for (String str : myArrayEndStartRegisterApp) {
//							endStartRegisterAppInfos.add(str);
//						}
//						
//						endStartRegisterCustName = endStartRegisterAppInfos.get(0);
//						endStartRegisterServiceName = endStartRegisterAppInfos.get(1);
//						endStartRegisterStartDay = endStartRegisterAppInfos.get(2);
//						endStartRegisterStartTime = endStartRegisterAppInfos.get(3);

						FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));

						FlexiBookController.endAppointment(endStartRegisterCustName , endStartRegisterServiceName, endStartRegisterStartDay, endStartRegisterStartTime);
						Alert a = new Alert(AlertType.CONFIRMATION, "Appointment Ended");
						a.showAndWait();	
						//refreshEndStartRegisterComboBox();
					}catch (InvalidInputException e1) {
						Alert a = new Alert(AlertType.ERROR, e1.getMessage());
						a.showAndWait();				}
				}
			});
			registerButton.setOnAction(e->{
				Alert alert3 = new Alert(AlertType.WARNING, "Are you sure you want to register a no-show?", ButtonType.YES, ButtonType.NO);
				alert3.showAndWait();
				if (alert3.getResult() == ButtonType.YES) {	
					try {
//						endStartRegisterInfo = (String) boxAppointments.getValue();
//						String[] myArrayEndStartRegisterApp = endStartRegisterInfo.split(", ");
//						List<String> endStartRegisterAppInfos = new ArrayList<>();
//
//						for (String str : myArrayEndStartRegisterApp) {
//							endStartRegisterAppInfos.add(str);
//						}
//						
//						endStartRegisterCustName = endStartRegisterAppInfos.get(0);
//						endStartRegisterServiceName = endStartRegisterAppInfos.get(1);
//						endStartRegisterStartDay = endStartRegisterAppInfos.get(2);
//						endStartRegisterStartTime = endStartRegisterAppInfos.get(3);

						FlexiBookController.setSystemDateAndTime(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
						FlexiBookController.registerNoShow(endStartRegisterCustName , endStartRegisterServiceName, endStartRegisterStartDay, endStartRegisterStartTime);
						Alert a = new Alert(AlertType.CONFIRMATION, "No-show Registered");
						a.showAndWait();	
						//refreshEndStartRegisterComboBox();
					}catch (InvalidInputException e1) {
						Alert a = new Alert(AlertType.ERROR, e1.getMessage());
						a.showAndWait();
					}
				}

			});

			startEndLink.setOnAction(e->{
//				refreshEndStartRegisterComboBox();
//				primaryStage.setTitle("Start/End/Register No-Show");
//				root2.setCenter(gridPaneOwner);
			});

			link.setOnAction(e->{
//				primaryStage.setTitle("Main Menu");
//				primaryStage.setScene(ownerMainScene);
//				primaryStage.show();
//				
			});

			viewCustomers.setOnAction(e->{
//				refreshCustomersData();
//				primaryStage.setTitle("Customers");
//				root2.setCenter(customerTable);
//				primaryStage.show();
//				
			});
			
			viewApps.setOnAction(e->{
//				primaryStage.setTitle("Appointments");
//				refreshAppData();
//				root2.setCenter(appTable);
//				
			});


			//View Appointments Table

//			customerNameCol = new TableColumn<TOAppointment, String>("Customer name");
//			customerNameCol.setMinWidth(180);
//			customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//
//
//			appServiceNameCol = new TableColumn<TOAppointment, String>("Service");
//			appServiceNameCol.setMinWidth(180);
//			appServiceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
//
//			startTimeCol = new TableColumn<TOAppointment, Time>("Start Time");
//			startTimeCol.setMinWidth(180);
//			startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//
//			endTimeCol = new TableColumn<TOAppointment, Time>("End Time");
//			endTimeCol.setMinWidth(180);
//			endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
//
//			dateCol = new TableColumn<TOAppointment, Date>("Date");
//			dateCol.setMinWidth(180);
//			dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
//
//			appTable = new TableView<TOAppointment>();
//			appTable.setItems(getAppointmentsData());
//			appTable.getColumns().addAll(customerNameCol, appServiceNameCol, startTimeCol, endTimeCol, dateCol);
		}
	    
		//@FXML
//		private void loadSecond(ActionEvent e) throws IOException{
//			Parent root = FXMLLoader.load(getClass().getResource("/StartEndRegister.java"));
//			root.translateYProperty().set(ownerScene.getHeight());
//		}
		
	  public static void main(String[] args) {
		   // start UI
		   launch(args);
	}
	}