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

public class OwnerMenu extends Application{
	
	private Text welcome;
	private Text flexiBookText;
	
	private DropShadow dS;
	
	private FontIcon ownerProfileIcon;
	private FontIcon businessIcon;
	private FontIcon serviceIcon;
	private FontIcon appointmentIcon;
	private FontIcon ownerLogoutIcon;
	
	private JFXButton ownerProfileButton;
	private JFXButton businessButton;
	private JFXButton serviceButton;
	private JFXButton appointmentButton;
	private JFXButton ownerLogoutButton;
	
	private HBox ownerLabelHBox;
	private HBox ownerIconsHBox;
	private HBox ownerSloganHBox;
	
	private BorderPane ownerBorderPane;

	private Scene ownerMainScene;
	


	
	public static void main (String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ownerBorderPane = new BorderPane();
	    ownerBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
	    ownerBorderPane.setMinSize(1100, 600); 
  		ownerBorderPane.setMaxSize(1100, 600); 
	    
	    
	    ownerLabelHBox = new HBox();
	    welcome = new Text("Welcome!");
	    welcome.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
	    welcome.setFill(Color.BLUE);
    	dS = new DropShadow();
        dS.setOffsetY(3.0f);
        dS.setColor(Color.color(0.4f, 0.4f, 0.4f));
        welcome.setEffect(dS);
        welcome.setCache(true);
        ownerLabelHBox.getChildren().add(welcome);
        ownerLabelHBox.setAlignment(Pos.CENTER);
        ownerBorderPane.setTop(ownerLabelHBox);
        
        ownerIconsHBox = new HBox(50);
        ownerIconsHBox.setAlignment(Pos.CENTER);
        ownerProfileIcon = new FontIcon("fa-user-circle-o");
        businessIcon = new FontIcon("fa-briefcase");
        appointmentIcon = new FontIcon("fa-calendar");
        serviceIcon = new FontIcon("fa-tasks");
        ownerLogoutIcon = new FontIcon("fa-sign-out");
        
        ownerProfileIcon.getStyleClass().add("icon");
        businessIcon.getStyleClass().add("icon");
        appointmentIcon.getStyleClass().add("icon");
        serviceIcon.getStyleClass().add("icon");
        ownerLogoutIcon.getStyleClass().add("icon");
        
        ownerProfileIcon.setFill(Color.BLUE);
        ownerProfileIcon.setIconSize(50);
        businessIcon.setFill(Color.BLUE);
        businessIcon.setIconSize(50);
        appointmentIcon.setFill(Color.BLUE);
        appointmentIcon.setIconSize(50);
        serviceIcon.setFill(Color.BLUE);
        serviceIcon.setIconSize(50);
        ownerLogoutIcon.setFill(Color.BLUE);
        ownerLogoutIcon.setIconSize(50);
        
        ownerProfileButton = new JFXButton("Account", ownerProfileIcon);
        ownerProfileButton.setContentDisplay(ContentDisplay.TOP);
        //customerProfileButton.setOnAction(e->switchToAppointment());
        ownerProfileButton.getStyleClass().add("main-menu-button");
        ownerProfileButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        businessButton = new JFXButton("Business", businessIcon);
        businessButton.setContentDisplay(ContentDisplay.TOP);
       
        businessButton.getStyleClass().add("main-menu-button");
        businessButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        
        serviceButton = new JFXButton("Services", serviceIcon);
        serviceButton.setContentDisplay(ContentDisplay.TOP);
        
        appointmentButton = new JFXButton("Appointments", appointmentIcon);
        appointmentButton.setContentDisplay(ContentDisplay.TOP);
       
        appointmentButton.getStyleClass().add("main-menu-button");
        appointmentButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        serviceButton.getStyleClass().add("main-menu-button");
        serviceButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        ownerLogoutButton = new JFXButton("Logout", ownerLogoutIcon);
        ownerLogoutButton.setContentDisplay(ContentDisplay.TOP);
        
        ownerLogoutButton.getStyleClass().add("main-menu-button");
        ownerLogoutButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        ownerSloganHBox =new HBox();
        ownerSloganHBox.setAlignment(Pos.CENTER);
        flexiBookText = new Text("Flexibook, it's time to get organised!");
        flexiBookText.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
        flexiBookText.setFill(Color.BLUE);
        ownerSloganHBox.getChildren().add(flexiBookText);
        ownerBorderPane.setBottom(ownerSloganHBox);
        
        
        ownerIconsHBox.getChildren().addAll(ownerProfileButton, businessButton, serviceButton, appointmentButton, ownerLogoutButton);
        
       ownerBorderPane.setCenter(ownerIconsHBox);
       
       ownerMainScene = new Scene(ownerBorderPane);
       primaryStage.setTitle("Owner Main Menu");
       primaryStage.setScene(ownerMainScene);
       primaryStage.show();
    	
    	

		
	}

}
