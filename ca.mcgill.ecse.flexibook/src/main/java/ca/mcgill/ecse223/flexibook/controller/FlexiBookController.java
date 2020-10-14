package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;

import ca.mcgill.ecse.flexibook.model.User;

public class FlexiBookController {

	
	public FlexiBookController() {	
	}
	
	public static void login (String username, String password) {
		
	}
	
	public static void logout () {
		
	}
	
	public static void viewAppointmentCalendar(String username, Date date, boolean dailyView) {
		
	}
	
	private static User findUser(String username) {
		User userFound = null;
		
//		for (User user : FlexiBookApplication.)
		return userFound;
	}
	
	private static boolean checkPassword(User user, String password) {
		return true;
	}
}
