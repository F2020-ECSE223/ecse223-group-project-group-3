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

public class BusinessMenu extends Application{

	private Text businessMenu;

	private DropShadow dS1;

	private FontIcon businessInformationIcon;
	private FontIcon businessHoursIcon;
	private FontIcon holidaysVacationsIcon;
	private FontIcon businessMenuGoBackIcon;

	private JFXButton businessInformationButton;
	private JFXButton businessHoursButton;
	private JFXButton holidaysVacationsButton;
	private JFXButton businessMenuGoBackButton;

	private HBox businessMenuLabelHBox;
	private HBox businessInformationHBox;
	private HBox businessMenuSloganHBox;

	private BorderPane businessMenuBorderPane;

	private Scene businessMenuMainScene;




	public static void main (String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		businessMenuBorderPane = new BorderPane();
		businessMenuBorderPane.setStyle("-fx-background-color: LIGHTBLUE;");
		businessMenuBorderPane.setMinSize(1100, 600); 
		businessMenuBorderPane.setMaxSize(1100, 600); 


		businessMenuLabelHBox = new HBox();
		businessMenu = new Text("Business Menu");
		businessMenu.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD,35));
		businessMenu.setFill(Color.BLUE);
		dS1 = new DropShadow();
		dS1.setOffsetY(3.0f);
		dS1.setColor(Color.color(0.4f, 0.4f, 0.4f));
		businessMenu.setEffect(dS1);
		businessMenu.setCache(true);
		businessMenuLabelHBox.getChildren().add(businessMenu);
		businessMenuLabelHBox.setAlignment(Pos.CENTER);
		businessMenuBorderPane.setTop(businessMenuLabelHBox);

		businessInformationHBox = new HBox(50);
		businessInformationHBox.setAlignment(Pos.CENTER);
		businessInformationIcon = new FontIcon("fa-info");
		businessHoursIcon = new FontIcon("fa-list-alt");
		holidaysVacationsIcon = new FontIcon("fa-calendar-o");
		businessMenuGoBackIcon = new FontIcon("fa-backward");


		businessInformationIcon.getStyleClass().add("icon");
		businessHoursIcon.getStyleClass().add("icon");
		holidaysVacationsIcon.getStyleClass().add("icon");
		businessMenuGoBackIcon.getStyleClass().add("icon");

		businessInformationIcon.setFill(Color.BLUE);
		businessInformationIcon.setIconSize(50);
		businessHoursIcon.setFill(Color.BLUE);
		businessHoursIcon.setIconSize(50);
		holidaysVacationsIcon.setFill(Color.BLUE);
		holidaysVacationsIcon.setIconSize(50);
		businessMenuGoBackIcon.setFill(Color.BLUE);
		businessMenuGoBackIcon.setIconSize(50);

		businessInformationButton = new JFXButton("Business Information", businessInformationIcon);
		businessInformationButton.setContentDisplay(ContentDisplay.TOP);
		businessInformationButton.getStyleClass().add("main-menu-button");
		businessInformationButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));

		businessHoursButton = new JFXButton("Business Hours", businessHoursIcon);
		businessHoursButton.setContentDisplay(ContentDisplay.TOP);
		businessHoursButton.getStyleClass().add("main-menu-button");
		businessHoursButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));


		holidaysVacationsButton = new JFXButton("Holidays and Vacations", holidaysVacationsIcon);
		holidaysVacationsButton.setContentDisplay(ContentDisplay.TOP);
		holidaysVacationsButton.getStyleClass().add("main-menu-button");
		holidaysVacationsButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));
		
		businessMenuGoBackButton = new JFXButton("Main Menu", businessMenuGoBackIcon);
		businessMenuGoBackButton.setContentDisplay(ContentDisplay.TOP);
		businessMenuGoBackButton.getStyleClass().add("main-menu-button");
		businessMenuGoBackButton.setFont(Font.font("Verdana", FontWeight.BOLD,15));


		businessMenuSloganHBox = new HBox();
		businessMenuSloganHBox.setAlignment(Pos.CENTER);
		businessMenuBorderPane.setBottom(businessMenuSloganHBox);


		businessInformationHBox.getChildren().addAll(businessInformationButton, businessHoursButton, holidaysVacationsButton, businessMenuGoBackButton);

		businessMenuBorderPane.setCenter(businessInformationHBox);

		businessMenuMainScene = new Scene(businessMenuBorderPane);
		primaryStage.setTitle("Business Menu");
		primaryStage.setScene(businessMenuMainScene);
		primaryStage.show();


		
	}

}
