package ca.mcgill.ecse.flexibook.view;

import javax.swing.JTable;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewAppointmentCalendarPage extends Application {
	
	
	private JTable timeSlotstable;
	private Text date;
	private Button daily;
	private Button weekly;
	private ScrollPane scrollPane;
	private DatePicker datePicker;
	//private DatePane 
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		timeSlotstable = new JTable();
		date = new Text("Select date");
		daily = new Button("Daily view");
		weekly = new Button("Weekly view");
		datePicker = new DatePicker();
		
//		try {
//			FlexiBookController.viewAppointmentCalendar(FlexiBookApplication.getCurrentUser().getUsername(), 
//					datePicker., isDaily)
//		}
		
		
	}

}
