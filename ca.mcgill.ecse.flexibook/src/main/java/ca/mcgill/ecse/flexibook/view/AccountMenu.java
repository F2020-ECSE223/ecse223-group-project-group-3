package ca.mcgill.ecse.flexibook.view;

import org.kordamp.ikonli.javafx.FontIcon;

import com.jfoenix.controls.JFXButton;


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

public class AccountMenu extends Application{
	
	private Text menuHeader;
	private Text flexiBookAccText;
	
	private DropShadow dSAcc;
	
	private FontIcon profileIcon;
	private FontIcon editAccIcon;
	private FontIcon myAppsIcon;
	private FontIcon backToMainIcon;
	
	private JFXButton profileButton;
	private JFXButton editAccButton;
	private JFXButton myAppsButton;
	private JFXButton backToMainButton;
	
	private HBox accLabelHBox;
	private HBox accIconsHBox;
	private HBox accSloganHBox;
	
	private BorderPane accMenuBorderPane;

	private Scene accMainScene;
	


	
	public static void main (String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		accMenuBorderPane = new BorderPane();
	    accMenuBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
	    accMenuBorderPane.setMinSize(1100, 600); 
  		accMenuBorderPane.setMaxSize(1100, 600); 
	    
	    
	    accLabelHBox = new HBox();
	    menuHeader = new Text("Account");
	    menuHeader.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
	    menuHeader.setFill(Color.BLUE);
    	dSAcc = new DropShadow();
        dSAcc.setOffsetY(3.0f);
        dSAcc.setColor(Color.color(0.4f, 0.4f, 0.4f));
        menuHeader.setEffect(dSAcc);
        menuHeader.setCache(true);
        accLabelHBox.getChildren().add(menuHeader);
        accLabelHBox.setAlignment(Pos.CENTER);
        accMenuBorderPane.setTop(accLabelHBox);
        
        accIconsHBox = new HBox(50);
        accIconsHBox.setAlignment(Pos.CENTER);
        profileIcon = new FontIcon("fa-address-book");
        editAccIcon = new FontIcon("fa-address-card");
        myAppsIcon = new FontIcon("fa-calendar");
        backToMainIcon = new FontIcon("fa-backward");

        profileIcon.getStyleClass().add("icon");
        editAccIcon.getStyleClass().add("icon");
        backToMainIcon.getStyleClass().add("icon");
        myAppsIcon.getStyleClass().add("icon");
        
        profileIcon.setFill(Color.BLUE);
        profileIcon.setIconSize(50);
        editAccIcon.setFill(Color.BLUE);
        editAccIcon.setIconSize(50);
        backToMainIcon.setFill(Color.BLUE);
        backToMainIcon.setIconSize(50);
        myAppsIcon.setFill(Color.BLUE);
        myAppsIcon.setIconSize(50);
       
        profileButton = new JFXButton("View Profile", profileIcon);
        profileButton.setContentDisplay(ContentDisplay.TOP);
        profileButton.getStyleClass().add("main-menu-button");
        profileButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        editAccButton = new JFXButton("Edit Profile", editAccIcon);
        editAccButton.setContentDisplay(ContentDisplay.TOP);
       
        editAccButton.getStyleClass().add("main-menu-button");
        editAccButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        
        myAppsButton = new JFXButton("My appointments", myAppsIcon);
        myAppsButton.setContentDisplay(ContentDisplay.TOP);
        
        backToMainButton = new JFXButton("Main Menu", backToMainIcon);
        backToMainButton.setContentDisplay(ContentDisplay.TOP);
       
        backToMainButton.getStyleClass().add("main-menu-button");
        backToMainButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        
        myAppsButton.getStyleClass().add("main-menu-button");
        myAppsButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        

        accSloganHBox =new HBox();
        accSloganHBox.setAlignment(Pos.CENTER);
        flexiBookAccText = new Text("Flexibook, it's time to get organised!");
        flexiBookAccText.setFont((Font.font("Verdana", FontPosture.ITALIC, 30)));
        flexiBookAccText.setFill(Color.BLUE);
        accSloganHBox.getChildren().add(flexiBookAccText);
        accMenuBorderPane.setBottom(accSloganHBox);
        
        
        accIconsHBox.getChildren().addAll(profileButton, editAccButton, myAppsButton, backToMainButton);
        
       accMenuBorderPane.setCenter(accIconsHBox);
       
       accMainScene = new Scene(accMenuBorderPane);
       primaryStage.setTitle("Account Menu");
       primaryStage.setScene(accMainScene);
       primaryStage.show();
    	
    	

		
	}

}
