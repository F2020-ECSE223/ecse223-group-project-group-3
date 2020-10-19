	package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.Service;
import ca.mcgill.ecse.flexibook.application.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.User;

public class FlexiBookController {

	
	public FlexiBookController() {	
	}
	
	public static void login (String username, String password) throws InvalidInputException{
		try {
			if (username.equals("owner") && password.equals("owner")) {
				Owner owner = new Owner(username, password, FlexiBookApplication.getFlexibook());
				FlexiBookApplication.setCurrentUser(owner);
				return;
			}
			
			else if (findUser(username) != null  && checkPassword(findUser(username), password)) {
				FlexiBookApplication.setCurrentUser(findUser(username));
				return;
			}
			
			else throw new InvalidInputException("Username/password not found");

		}
		catch (RuntimeException e) {
			
			throw new InvalidInputException(e.getMessage());
		}		
		
	}
	
	public static void logout () throws InvalidInputException{
		try {
			if (FlexiBookApplication.getCurrentUser() != null) FlexiBookApplication.setCurrentUser(null);
			else throw new InvalidInputException("The user is already logged out");
			
		}
		catch (RuntimeException e) {
			
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void viewAppointmentCalendar(String username, Date date, boolean dailyView) throws InvalidInputException{
		
	}
	
	private static User findUser(String username) {
		User foundUser = null;
		
		if (FlexiBookApplication.getFlexibook().getOwner() != null) {
			if((FlexiBookApplication.getFlexibook().getOwner().getUsername()).equals(username)){  
				Owner owner = FlexiBookApplication.getFlexibook().getOwner();
				foundUser = owner;
				return foundUser;
			}
		}
		for (Customer customer : FlexiBookApplication.getFlexibook().getCustomers()) {
			if (customer.getUsername() == username) {
				foundUser = customer;
				return foundUser;
			}
		}
		
		
		return foundUser;
	}
//---------------------------------------------------------------------------------------------------------------------
	// Robert code for service combo
	
	// 1. Define ServiceCombo 
	public static void defineServiceCombo(BookableService BkService, String SCname, String mainService, String services, String mandatory) {
		
		
		FlexiBookApplication.getFlexibook().addBookableService(BkService);
	}
	
	// 2. Update ServiceCombo
	public static void updateServiceCombo(String SCname) {
		
	}
	
	// 3. Delete ServiceCombo
	
	public static void deleteServiceCombo(String SCname) {
		if (FlexiBookApplication.getCurrentUser() != FlexiBookApplication.getFlexibook().getOwner()) {
			//throw new error
		}
		else {
		BookableService sc = findServiceCombo(SCname);
		if (sc != null) {
			sc.delete();
		}
		else {
			throw new RuntimeException("There is no Service combo with this name");
		}
		List<Appointment> a = FlexiBookApplication.getFlexibook().getAppointments();
		for (Appointment app : a) {
			if (app.getBookableService().getName() == SCname) {
			app.delete();
			}
		}
		}
	}
	
	private static BookableService findServiceCombo(String name) {
		BookableService SCfound = null;
		for (BookableService Sc : FlexiBookApplication.getFlexibook().getBookableServices()) {
			if (Sc.getName() == name ) {
				SCfound = Sc;
			}
		}
		return SCfound;
	}
	
//---------------------------------------------------------------------------------------------------------------------
	private static boolean checkPassword(User user, String password) {
		if (user.getPassword().equals(password)) return true;
		
		else return false;
	}
}
