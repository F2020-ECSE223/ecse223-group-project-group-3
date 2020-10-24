package ca.mcgill.ecse.flexibook.controller;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;

public class FlexiBookControllerTest {
	
<<<<<<< HEAD
	private static FlexiBook flexibook = FlexiBookApplication.getFlexibook();
	
	

	public static void loginTestSuccess() {
		String error = null;

		try {
			FlexiBookController.login("User1", "apple");
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		if (error == null && FlexiBookApplication.getCurrentUser() != null
						  && FlexiBookApplication.getCurrentUser().getUsername().equals("User1")
						  && FlexiBookApplication.getCurrentUser().getPassword().equals("apple")) {
			
			System.out.println("loginTestSuccess: Passed.");
						  
						  }
		else System.out.println("loginTestSuccess: Failed. With error message: "+error);
		
		FlexiBookApplication.setCurrentUser(null);
	}
	
	public static void loginTestFailUsername() {
		String error = null;

		
		try {
			FlexiBookController.login("User3", "apple");
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		
		if (error != null && error.equals("Username/password not found")
						  && FlexiBookApplication.getCurrentUser() == null) {
			
			System.out.println("loginTestFailUsername: Passed.");
		
		}
		else System.out.println("loginTestFailUsername: Failed. With error message: "+error);
		
		FlexiBookApplication.setCurrentUser(null);
	}
	
	
	
	public static void loginTestOwner() {
		String error = null;
		
		try {
			FlexiBookController.login("owner", "owner");
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
	if (error == null && FlexiBookApplication.getCurrentUser() != null
					  && FlexiBookApplication.getCurrentUser().getUsername().equals("owner")
			          && FlexiBookApplication.getCurrentUser().getPassword().equals("owner")
			          && flexibook.getOwner() != null) {
		
		System.out.println("loginTestOwner: Passed.");
	}
	else System.out.println("loginTestOwner: Failed. With error message: "+error);
	
	FlexiBookApplication.setCurrentUser(null);
	}
	
	public static void loginTestFailPassword() {
		String error = null;
		
				try {
			FlexiBookController.login("User1", "grape");
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		
	if (error != null && error.equals("Username/password not found")
					  && FlexiBookApplication.getCurrentUser() == null) {
		System.out.println("loginTestFailPassword : Passed");
	}
	else System.out.println("loginTestFailPassword : failed. With error message: "+error);
	
	FlexiBookApplication.setCurrentUser(null);

	}
	
	public static void logoutTestAlreadyLoggedOut() {
		String error = null;
		try {
			FlexiBookController.logout();
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		
		if (error != null && error.equals("The user is already logged out")
						  && FlexiBookApplication.getCurrentUser() == null ){
			System.out.println("logoutTestAlreadyLoggedOut: Passed.");
		}
		else System.out.println("logoutTestAlreadyLoggedOut: Failed.");
	}
	
	public static void logoutTestSuccess() {
		String error = null;
		try {
			FlexiBookController.login("User1", "apple");
			FlexiBookController.logout();
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		
		if (error == null && FlexiBookApplication.getCurrentUser() == null ){
			System.out.println("logoutTestAlreadyLoggedOut: Passed.");
		}
		else System.out.println("logoutTestAlreadyLoggedOut: Failed.");
		
	}
	
	
	
	public static void main (String[] args) {
		FlexiBookApplication.setCurrentUser(null);
		flexibook.addCustomer("User1", "apple");
		flexibook.addCustomer("User2", "grape");
		
		loginTestSuccess();
		loginTestFailUsername();
		loginTestOwner();
		loginTestFailPassword();
		logoutTestAlreadyLoggedOut();
		logoutTestSuccess();
		
	}
=======
>>>>>>> robert
	
	
}
