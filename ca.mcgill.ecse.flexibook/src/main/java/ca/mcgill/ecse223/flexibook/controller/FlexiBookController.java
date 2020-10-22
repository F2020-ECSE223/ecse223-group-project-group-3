package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.List;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
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

public static void makeAppointment(String customerString, String serviceName, String optionalServicesString, String startDateString, String startTimeString) throws InvalidInputException{
	
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	startTimeString = startTimeString+":00";
	Time startTime = Time.valueOf(startTimeString);
	Date startDate = Date.valueOf(startDateString);
	Time endTime= null;
	Date endDate = startDate;
	Customer customer= (Customer) findUser(customerString);
	
	String[] myArray = optionalServicesString.split(", ");
	List<String> optionalServices = new ArrayList<>();
	//List<String>  optionalServices = new ArrayList<>();
	
	for (String str : myArray) {
	    optionalServices.add(str);
	}
	
//	for(String str2: optionalServices1) {
//		optionalServices.add(BookableService.getWithName(str2));
//	}

//	Iterator <TimeSlot> holidaysIterator = flexiBook.getBusiness().getHolidays().iterator();
//	Iterator <TimeSlot> vacationIterator = flexiBook.getBusiness().getVacation().iterator();
		
	try {
		if(BookableService.hasWithName(serviceName)) {
			BookableService thisService = BookableService.getWithName(serviceName);
			Service service = (Service)thisService;
			
			if (myArray == null) {
				
				endTime = new Time(startTime.getTime() + service.getDuration());
				
			} else {
				endTime = new Time(startTime.getTime() + service.getDuration());
				
				for(int i=0; i< optionalServices.size()-1; i++) {
					Service service2 = (Service) BookableService.getWithName(optionalServices.get(i));
			//		if (service.getDowntimeDuration()>service2.getDuration()) throw new InvalidInputException("here are no available slots for " + serviceName + " on " + startDate + " at " + startTime);
						endTime = new Time(endTime.getTime() + service2.getDuration());
					
					
				}
								
			}
			
			TimeSlot aTimeSlot = new TimeSlot(startDate, startTime, endDate, endTime, flexiBook);
			
			
			if(startDate.before(getCurrentDate()) && startTime.before(getCurrentTime())) {
				if(betweenBusinessHours(startTime) && betweenBusinessHours(endTime)) {
					
					if(!isOverlap(aTimeSlot) && getAvailableTimeSlots().contains(aTimeSlot)) {
						
						flexiBook.addAppointment(customer, thisService, aTimeSlot);
						
							
						}else throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTime);
						
					}else throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTime);
					
				}else throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTime);
				
			} else throw new InvalidInputException("The service does not exist");

	}
	catch (RuntimeException e) {
		throw new InvalidInputException(e.getMessage());
	}
}


public static void UpdateAppointment(String customerString, String appointmentName, String newDateString, String newStartTimeString ) throws InvalidInputException {
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	newStartTimeString = newStartTimeString+":00";
	Time newStartTime = Time.valueOf(newStartTimeString);
	Date newStartDate = Date.valueOf(newDateString);
	Time newEndTime= null;
	Date newEndDate = newStartDate;
	Customer customer= (Customer) findUser(customerString);
	
	if(betweenBusinessHours(newStartTime) && betweenBusinessHours(newEndTime)) {
		
	}
	
}

public static void CancelAppointment(User user, Appointment anAppointment) throws InvalidInputException {
	String customerUsername = anAppointment.getCustomer().getUsername();
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
		
	try {
		if(customerUsername == user.getUsername()) {
			
			if(anAppointment != null) {
				if(anAppointment.getTimeSlot().getStartDate().before(getCurrentDate())) {
					anAppointment.delete();
				}
				else if(anAppointment.getTimeSlot().getStartDate().equals(getCurrentDate())) {
					throw new InvalidInputException("Cannot cancel an appointment on the appointment date");
				}
			}
		}else if(user== flexiBook.getOwner()) {
			throw new InvalidInputException("An owner cannot cancel an appointment");
			}
		else throw new InvalidInputException("A customer can only cancel their own appointments");
	}catch(RuntimeException e) {
		throw new InvalidInputException(e.getMessage());
	}
}

//private static Date getCurrentDate(){
//    Calendar cal = Calendar.getInstance();
//    cal.set(Calendar.HOUR_OF_DAY, 0);
//    cal.set(Calendar.MINUTE, 0);
//    cal.set(Calendar.SECOND, 0);
//    cal.set(Calendar.MILLISECOND, 0);
//    Date date = (Date) cal.getTime();
//    return date;
//  }

//private static boolean duringVacation(TimeSlot aTimeSlot) {
//	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
//	boolean isDuring = false;
//	
//	if(flexiBook.getBusiness().getVacation().contains(aTimeSlot)) {
//		isDuring = true;
//	}
//	
//	return isDuring;
//}
//
//private static boolean duringHolidays(TimeSlot aTimeSlot) {
//	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
//	boolean isDuring = false;
//	
//	if(flexiBook.getBusiness().getHolidays().contains(aTimeSlot)) {
//		isDuring = true;
//	}
//	
//	return isDuring;
//}

private static boolean betweenBusinessHours(Time time) {
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	boolean isBetween = false;
	
	if(flexiBook.getHours().contains(time)) {
		isBetween = true;
	}
	return isBetween; 
	}

private static List<TimeSlot> getAvailableTimeSlots(){
	List<TimeSlot> availableTimeSlots = new ArrayList<TimeSlot>();
	FlexiBook flexibook = FlexiBookApplication.getFlexibook();
	List<TimeSlot> tempList = new ArrayList<TimeSlot>(flexibook.getTimeSlots());
	
	tempList.removeAll(unavailableTimeSlots());
	availableTimeSlots = tempList;
	
	return availableTimeSlots;
}

private static List<TimeSlot> unavailableTimeSlots(){
	List<TimeSlot> unavailableTimeSlots = new ArrayList<TimeSlot>();
	FlexiBook flexibook = FlexiBookApplication.getFlexibook();

	for (Appointment appointment : flexibook.getAppointments()) {
		unavailableTimeSlots.add(appointment.getTimeSlot());
	}
	for (TimeSlot TS : flexibook.getBusiness().getHolidays()) {
		unavailableTimeSlots.add(TS);
	}
	
	for (TimeSlot TS : flexibook.getBusiness().getVacation()) {
		unavailableTimeSlots.add(TS);
	}
	
	return unavailableTimeSlots;
}


private static boolean isOverlap(TimeSlot ts) {
	boolean is = false;
	
	for(int i=0; i <unavailableTimeSlots().size()-1; i++) {
		TimeSlot tempTimeSlot =unavailableTimeSlots().get(i);
		
		if(tempTimeSlot.getEndTime().after(ts.getStartTime()) && tempTimeSlot.getEndTime().before(ts.getEndTime())) {
			is=true;
		}else if (tempTimeSlot.getStartTime().after(ts.getStartTime()) && tempTimeSlot.getStartTime().before(ts.getEndTime())) {
			is = true;
		}else is = false;
		
	}
	return is;
	}

private static Date getCurrentDate() {  
	long millis=System.currentTimeMillis();  
	java.sql.Date date=new java.sql.Date(millis);  
	return date;
	  }  
private static Time getCurrentTime() {
	Calendar cal = Calendar.getInstance();
	Time time = (Time) cal.getTime();
	return time;
}
	
}