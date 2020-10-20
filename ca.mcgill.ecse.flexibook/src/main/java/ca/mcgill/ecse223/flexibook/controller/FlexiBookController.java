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
	private static void addService(String ServiceName, int Duration,int DowntimeDuration, 
			                         int DowntimeStart,String user) 
			throws InvalidInputException{
	    try {
		String ownerUsername = FlexiBookApplication.getFlexibook().getOwner().getUsername();
		if ((findUser(user)).equals(ownerUsername)) {	
		Service service = new Service (ServiceName,FlexiBookApplication.getFlexibook(),DowntimeDuration,
				DowntimeStart,Duration);
		service.setName(ServiceName);  // here or in update?
		service.setDuration(Duration);   //here of update?
		service.setDowntimeStart(DowntimeStart);
		service.setDowntimeDuration(DowntimeDuration);
		}
		
		
		   else if (!(findUser(user)).equals(ownerUsername))
				throw new InvalidInputException ("Unauthorized attempt to add a service");
						}
					   catch (RuntimeException e) {
							throw new InvalidInputException(e.getMessage());
					   }	    	
	    }
	
	public static void updateService (String ServiceName, int Duration,int DowntimeDuration, 
			                           int DowntimeStart,String user,Service service) throws InvalidInputException {
		String ownerUsername = FlexiBookApplication.getFlexibook().getOwner().getUsername();
		try {
			if (service.getName().equals(ServiceName)) {
				throw new InvalidInputException ("Service" + ServiceName + "already exists");
			}
			if (!(findUser(user)).equals(ownerUsername)) {
				throw new InvalidInputException ("Unauthorized attempt to update a service");
			}
			service.setName(ServiceName);  
			service.setDowntimeStart(DowntimeStart);
			service.setDowntimeDuration(DowntimeDuration);
		}
		  catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
		  }
	}
	
	
	
	public static void deleteService(String service,String username) throws InvalidInputException {
		try {
			//FlexiBookApplication.getFlexibook();			
		List<BookableService> Service =FlexiBookApplication.getFlexibook().getBookableServices();	
		for (BookableService S:Service) {
			if (S.getFlexiBook().hasAppointments()==true) {
			throw new InvalidInputException ("The Service is contained in future appointments");	
			}
			if (S instanceof ServiceCombo) { 
				ServiceCombo serviceCombo = (ServiceCombo) S;
				BookableService service1 = findService(service);	
				String deletedService =service1.getName();
				if (serviceCombo.getMainService().getServiceCombo().getName().equals(deletedService)) 
				{
				serviceCombo.delete();
			throw new InvalidInputException ("This is a main service contained in a ServiceCombo");  //me or servicecombos job
				}				
			}
		}
		String ownerUsername = FlexiBookApplication.getFlexibook().getOwner().getUsername();
		BookableService service1 = findService(service);		 
			if (username == ownerUsername) {				
				   if (service1 != null) {
				    service1.delete();	
					// int newSize = FlexiBookApplication.getFlexibook().getBookableServices().size()-1;
				   }	
				   return;
			}			
		    else //if (!(findUser(username)).equals(ownerUsername))  //! dk if can write this
			throw new InvalidInputException ("Unauthorized attempt to delete a service");
		}
		
		
				   catch (RuntimeException e) {
						throw new InvalidInputException(e.getMessage());
				   }
		
		}
			  
			private static BookableService findService(String name) {
				BookableService foundService = null;
				for (BookableService service : FlexiBookApplication.getFlexibook().getBookableServices()) {
					if (service.getName() == name) {
						foundService = service;
						break;
					}
				}
			return foundService;
			}					
}
		



