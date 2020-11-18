package ca.mcgill.ecse.flexibook.view;

import org.kordamp.ikonli.javafx.FontIcon;

import com.jfoenix.controls.JFXButton;

import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerMenu extends Application{
	
	private Text customerLabel;
	private Text flexiBookText;

	private HBox customerLabelHBox;
	private HBox customerIconsHBox;
	private HBox customerSloganHBox;

	private FontIcon customerProfileIcon;
	private FontIcon customerAppIcon;
	private FontIcon customerLogoutIcon;
	
	private JFXButton customerProfileButton;
	private JFXButton customerAppButton;
	private JFXButton customerLogoutButton;
	
	private BorderPane customerBorderPane;

	private Scene customerMainScene;

	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		customerBorderPane = new BorderPane();
		customerBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
		customerBorderPane.setMinSize(1100, 600); 
		customerBorderPane.setMaxSize(1100, 600); 


		customerLabelHBox = new HBox();
		customerLabel = new Text("Welcome!");
		customerLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
		customerLabel.setFill(Color.BLUE);
		DropShadow dS = new DropShadow();
		dS.setOffsetY(3.0f);
		dS.setColor(Color.color(0.4f, 0.4f, 0.4f));
		customerLabel.setEffect(dS);
		customerLabel.setCache(true);
		customerLabelHBox.getChildren().add(customerLabel);
		customerLabelHBox.setAlignment(Pos.CENTER);
		customerBorderPane.setTop(customerLabelHBox);

		customerIconsHBox = new HBox();
		customerIconsHBox.setAlignment(Pos.CENTER);
		customerProfileIcon = new FontIcon("fa-user-circle-o");
		customerAppIcon =  new FontIcon("fa-calendar");
		customerLogoutIcon = new FontIcon("fa-sign-out");

		customerProfileIcon.getStyleClass().add("icon");
		customerAppIcon.getStyleClass().add("icon");
		customerLogoutIcon.getStyleClass().add("icon");

		customerProfileIcon.setFill(Color.BLUE);
		customerProfileIcon.setIconSize(50);
		customerAppIcon.setFill(Color.BLUE);
		customerAppIcon.setIconSize(50);
		customerLogoutIcon.setFill(Color.BLUE);
		customerLogoutIcon.setIconSize(50);

		customerProfileButton = new JFXButton("Account", customerProfileIcon);
		customerProfileButton.setContentDisplay(ContentDisplay.TOP);
//		customerProfileButton.setOnAction(e->{
//			primaryStage.setTitle("Account Page");
//			primaryStage.setScene(updateAccScene);
//			primaryStage.show();
//		});
		customerProfileButton.getStyleClass().add("main-menu-button");
		customerProfileButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		customerAppButton = new JFXButton("My appointments", customerAppIcon);
		customerAppButton.setContentDisplay(ContentDisplay.TOP);
//		customerMakeAppButton.setOnAction(e->{
//			primaryStage.setTitle("Make an ppointment");
//			primaryStage.setScene(makeAppScene);
//			primaryStage.show();
//		});
		customerAppButton.getStyleClass().add("main-menu-button");
		customerAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

//		customerUpdateAppButton = new JFXButton("Update your appointment", customerUpdateAppIcon);
//		customerUpdateAppButton.setContentDisplay(ContentDisplay.TOP);
//		customerUpdateAppButton.setOnAction(e->{
//			primaryStage.setTitle("Update an appointment");
//			primaryStage.setScene(updateAppScene);
//			primaryStage.show();
//		});
//		customerUpdateAppButton.getStyleClass().add("main-menu-button");
//		customerUpdateAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
//
//		customerCancelAppButton = new JFXButton("Cancel your appointment", customerCancelAppIcon);
//		customerCancelAppButton.setContentDisplay(ContentDisplay.TOP);
//		customerCancelAppButton.setOnAction(e->{
//			primaryStage.setTitle("Cancel an appointment");
//			primaryStage.setScene(cancelAppScene);
//			primaryStage.show();
//		});
//		customerCancelAppButton.getStyleClass().add("main-menu-button");
//		customerCancelAppButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		customerLogoutButton = new JFXButton("Logout", customerLogoutIcon);
		customerLogoutButton.setContentDisplay(ContentDisplay.TOP);
//		customerLogoutButton.setOnAction(e->{
//			try {
//				FlexiBookController.logout();
//				resetLoginPage();
//				primaryStage.setTitle("LoginPage");
//				primaryStage.setScene(loginScene);
//				primaryStage.show();
//			} catch (InvalidInputException e1) {
//				error.setText(e1.getMessage());
//				gridPaneLogin.add(error, 3, 3);
//			}
//
//		});
		customerLogoutButton.getStyleClass().add("main-menu-button");
		customerLogoutButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		customerSloganHBox =new HBox();
		customerSloganHBox.setAlignment(Pos.CENTER);
		flexiBookText = new Text("Flexibook, it's time to get organised!");
		flexiBookText.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
		flexiBookText.setFill(Color.BLUE);
		customerSloganHBox.getChildren().add(flexiBookText);
		customerBorderPane.setBottom(customerSloganHBox);

		customerIconsHBox.getChildren().addAll(customerProfileButton, customerAppButton, customerLogoutButton);

		customerBorderPane.setCenter(customerIconsHBox);

		customerMainScene = new Scene(customerBorderPane);	
		
		primaryStage.setTitle("Customer main menu");
		primaryStage.setScene(customerMainScene);
		primaryStage.show();
	}

}
