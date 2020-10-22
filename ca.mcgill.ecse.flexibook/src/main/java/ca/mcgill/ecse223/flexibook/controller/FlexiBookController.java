package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.util.List;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
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
	
	private static boolean checkPassword(User user, String password) {
		if (user.getPassword().equals(password)) return true;
		
		else return false;
	}
	// My Part
	
	
	@SuppressWarnings("unused")
	public static void addService(String serviceName, int duration,int downtimeDuration, 
			                         int downtimeStart,String user) 
			throws InvalidInputException{
	    try {
		String ownerUsername = FlexiBookApplication.getFlexibook().getOwner().getUsername();	
		List<BookableService> Service =FlexiBookApplication.getFlexibook().getBookableServices();
		
		for (BookableService S:Service) {
		    if (S instanceof Service) { 
				 Service service = (Service) S;				
			if (service.getName() == serviceName)  { 
				throw new InvalidInputException("Service " + serviceName + " already exists");
						}
		}
		}
		if (((findUser(user)).getUsername().equals(ownerUsername))) {	
		Service service1 = new Service (serviceName,FlexiBookApplication.getFlexibook(),duration,
				                        downtimeDuration,downtimeStart);
		serviceSpecification(serviceName,duration,downtimeDuration,downtimeStart);
			}
			 
		else
		throw new InvalidInputException ("Unauthorized attempt to add a service");
		}
					   catch (RuntimeException e) {
							throw new InvalidInputException(e.getMessage());
					   }	    	
	    }
	
	public static void updateService (String service, int duration,int downtimeDuration, 
			                           int downtimeStart,String user,String serviceName) throws InvalidInputException {
		String ownerUsername = FlexiBookApplication.getFlexibook().getOwner().getUsername();

		try {
			serviceSpecification(serviceName,duration,downtimeDuration,downtimeStart);
			
			if (!(user.equals(ownerUsername))) {
				throw new InvalidInputException ("Unauthorized attempt to update a service");
			}
			else {
			 Service S = findService (service);			 
			S.setName(serviceName);  
			S.setDowntimeStart(downtimeStart);
			S.setDowntimeDuration(downtimeDuration);
			S.setDuration(duration);
		}
		}
		  catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
		  }
	}
	
	public static void serviceSpecification (String serviceName, int duration, int downtimeDuration, int downtimeStart)
			throws InvalidInputException {
		if (downtimeDuration < 0) {
			throw new InvalidInputException("Downtime duration must be 0");
	  }
	  if (downtimeStart + downtimeDuration >0 && downtimeDuration == 0) {
			throw new InvalidInputException("Downtime duration must be positive");
	}
		if (duration <= 0) {
			throw new InvalidInputException("A service Duration must always be positive");
		}
	 if (downtimeStart < 0) {
			throw new InvalidInputException("Downtime must not start before the beginning of the service");
	}
	 if ((downtimeDuration > 0) && (downtimeStart == 0)) {
			throw new InvalidInputException("Downtime must not start at the beginning of the service");
		}
	  if (downtimeStart + downtimeDuration <= duration) {
			throw new InvalidInputException("Downtime must not end after a service");
		}
	
	}
	
	
	
	
	public static void deleteService(String service,String username) throws InvalidInputException {
		try {
			
		List<BookableService> Service =FlexiBookApplication.getFlexibook().getBookableServices();	
		for (BookableService S:Service) {
		if (S instanceof ServiceCombo) { 
			ServiceCombo serviceCombo = (ServiceCombo) S;			
			BookableService service1 = findService(service);	
			String deletedService =service1.getName();
			if (serviceCombo.getMainService().getServiceCombo().getName().equals(deletedService)) 
			{
			FlexiBookApplication.getFlexibook().removeBookableService(service1);
		          service1.delete();			
	//	throw new InvalidInputException ("This is a main service contained in a ServiceCombo");  
			}	
			
		
			if (S.getFlexiBook().hasAppointments() == true) {
				FlexiBookApplication.getFlexibook().removeBookableService(service1);
				   service1.delete();	
 
	//  throw new InvalidInputException ("The Service is contained in future appointments");
			
			}
		
			}
		}
		String ownerUsername = FlexiBookApplication.getFlexibook().getOwner().getUsername();
		BookableService service1 = findService(service);		 
			if (username == ownerUsername) {				
				   if (service1 != null) {
				    service1.delete();	
		    FlexiBookApplication.getFlexibook().removeBookableService(service1);
				   }	
				   return;
			}			
		    else 
			throw new InvalidInputException ("Unauthorized attempt to delete a service");
		}
		
		
				   catch (RuntimeException e) {
						throw new InvalidInputException(e.getMessage());
				   }
		
		}
			  
			private static Service findService(String service) {

				for (BookableService aService : FlexiBookApplication.getFlexibook().getBookableServices()) {
					if (aService instanceof Service) {
						if (aService.getName().equals(service)) {
							return (Service) aService;
						}
					}
			}
				return null;				
			}
}





