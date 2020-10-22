	package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;


import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;

import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
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
	public static void defineServiceCombo(String SCname, String mainService, String[] services, boolean[] mandatory) {
		Service serviceMain = findService(mainService);
		FlexiBook f = FlexiBookApplication.getFlexibook();
		ServiceCombo sc = new ServiceCombo(SCname, f);
		ComboItem mainItem = new ComboItem(true, serviceMain, null);
		sc.setMainService(mainItem);	
//		List<ComboItem> list = sc.getServices();
		for (int i= 0 ;i<services.length ;i++) {
			if (!services[i].equals(mainService)) {
			ComboItem coI = new ComboItem(false, findService(services[i]),sc);
			coI.setMandatory(mandatory[i]);
			sc.addService(coI);
			}
		}
	}
	
	private static Service findService(String service) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof Service) {
				if (aService.getName().equals(service) ) return (Service) aService;
			}
		}
		return null;
	}
	
	// 2. Update ServiceCombo
	public static void updateServiceCombo(String ownerName, String SCOldName,String newSCName, String mainService, String services, String mandatory) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(ownerName)) {
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		ServiceCombo sc = findServiceCombo(SCOldName);
		sc.setName(newSCName);
		String[] elements = services.split(",");
		String[] mandatories = mandatory.split(",");
		for (ComboItem i :sc.getServices()) {
			sc.removeService(i);
			i.delete();
		}
		for (int i= 0 ;i<elements.length ;i++) {
			boolean isMand = false;
			if(mandatories[i].equals("true")) isMand = true;
			ComboItem coI = new ComboItem(isMand, findService(elements[i]),sc); 
			sc.addService(coI);
			if (coI.getService().getName().equals(mainService)) sc.setMainService(coI);
		}
	}
	
	private static ServiceCombo findServiceCombo(String serviceCombo) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof ServiceCombo) {
				if (aService.getName().equals(serviceCombo) ) return (ServiceCombo) aService;
			}
		}
		return null;
	}
	
	// 3. Delete ServiceCombo
	
	public static void deleteServiceCombo(String SCname) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())) {
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		else {
		BookableService sc = findBookableService(SCname);
		List<Appointment> a = FlexiBookApplication.getFlexibook().getAppointments();
		for (Appointment app : a) {
			if (app.getBookableService().getName() == SCname) {
			throw new InvalidInputException("There are future appointments for the service combo, it cannot be deleted");
			}
		}
		if (sc != null) {
			sc.delete();
		}
		else {
			throw new InvalidInputException("There is no Service combo with this name");
		}
		
		}
	}
	
	private static BookableService findBookableService(String name) {
		BookableService SCfound = null;
		for (BookableService Sc : FlexiBookApplication.getFlexibook().getBookableServices()) {
			if (Sc.getName() == name) {
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
