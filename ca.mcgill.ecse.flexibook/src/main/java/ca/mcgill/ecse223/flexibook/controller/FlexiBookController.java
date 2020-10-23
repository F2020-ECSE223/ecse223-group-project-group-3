	package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
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
	public static void defineServiceCombo(String ownerName, String SCname, String mainService, String services, String mandatory) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())) {
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		if (findServiceCombo(SCname)!=null) throw new InvalidInputException("Service combo " + SCname + " already exists");
		if (findService(mainService) == null) throw new InvalidInputException("Service " + mainService + " does not exist");
		
		String[] elements = services.split(",");
		String[] mandatories = mandatory.split(",");
		
		if (elements.length<2) {
			throw new InvalidInputException("A service Combo must contain at least 2 services");
		}
		for (int l=0;l<elements.length;l++) {
			if (findService(elements[l])==null) {
				throw new InvalidInputException("Service "+elements[l]+" does not exist");
			}
		}
		List<String> a = Arrays.asList(elements);
		ArrayList<String> listOfService = new ArrayList<String>(a);
		if (!listOfService.contains(mainService)) {
			throw new InvalidInputException("Main service must be included in the services");
		}
		int mainIndex = 0;
		for (int m=0;m<elements.length;m++) {
			if (elements[m].equals(mainService)) mainIndex = m;
		}
		if (!mandatories[mainIndex].equals("true")) throw new InvalidInputException("Main service must be mandatory");
		
		FlexiBook f = FlexiBookApplication.getFlexibook();
		ServiceCombo sc = new ServiceCombo(SCname, f);
		
		for (int i= 0 ;i<elements.length ;i++) {
			boolean isMand = false;
			if(mandatories[i].equals("true")) isMand = true;
			ComboItem coI = new ComboItem(isMand, findService(elements[i]),sc); 
			sc.addService(coI);
			if (coI.getService().getName().equals(mainService)) sc.setMainService(coI);
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
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())) {
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		if (!SCOldName.equals(newSCName) && findServiceCombo(newSCName)!=null) throw new InvalidInputException("Service combo " + newSCName + " already exists");
		ServiceCombo sc = findServiceCombo(SCOldName);
		
		String[] elements = services.split(",");
		String[] mandatories = mandatory.split(",");
		if (elements.length<2) throw new InvalidInputException("A service Combo must have at least 2 services");
		for (int l=0;l<elements.length;l++) {
			if (findService(elements[l])==null) {
				throw new InvalidInputException("Service "+elements[l]+" does not exist");
			}
		}
		
		if (findService(mainService) == null) throw new InvalidInputException("Service " + mainService + " does not exist");
		List<String> a = Arrays.asList(elements);
		ArrayList<String> listOfService = new ArrayList<String>(a);
		if (!listOfService.contains(mainService)) {
			throw new InvalidInputException("Main service must be included in the services");
		}
		
		int mainIndex = 0;
		for (int m=0;m<elements.length;m++) {
			if (elements[m].equals(mainService)) mainIndex = m;
		}
		if (!mandatories[mainIndex].equals("true")) throw new InvalidInputException("Main service must be mandatory");
		sc.setName(newSCName);
		
		ArrayList<ComboItem> items = new ArrayList<ComboItem>();
		for (int j=0;j<sc.getServices().size();j++) {
			items.add(sc.getService(j));
		}
		for (int i= 0 ;i<elements.length ;i++) {
			boolean isMand = false;
			if(mandatories[i].equals("true")) isMand = true;
			ComboItem coI = new ComboItem(isMand, findService(elements[i]),sc); 
			sc.addService(coI);
			if (coI.getService().getName().equals(mainService)) sc.setMainService(coI);
		}
		int sizeMax = items.size();
		for (int k = 0;k<sizeMax;k++) {
			items.get(k).delete();
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
	
	public static void deleteServiceCombo(String SCname, String scDelete) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())) {
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		else {
		ServiceCombo sc = findServiceCombo(scDelete);
		
		List<Appointment> a = sc.getAppointments();//FlexiBookApplication.getFlexibook().getAppointments();
		for (int i =0;i<a.size();i++) {
			if (a.get(i).getTimeSlot().getStartDate().after(SystemTime.getSysDate())) throw new InvalidInputException("Service combo "+scDelete+" has future appointments"); 
			if (a.get(i).getTimeSlot().getStartDate().equals(SystemTime.getSysDate())) {
				if (a.get(i).getTimeSlot().getEndTime().after(SystemTime.getSysTime())) throw new InvalidInputException("Service combo "+scDelete+" has future appointments");
				else a.get(i).delete();
			}
			if (a.get(i).getTimeSlot().getStartDate().before(SystemTime.getSysDate())) {
				a.get(i).delete();
			}
		}

		if (sc != null) {
			sc.delete();
		}
//		else {
//			throw new InvalidInputException("There is no Service combo with this name");
//		}
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
