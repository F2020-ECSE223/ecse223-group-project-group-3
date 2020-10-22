package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
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
	Time startTime = Time.valueOf(startTimeString);
	Date startDate = Date.valueOf(startDateString);
	Time endTime= null;
	Date endDate = startDate;
	Customer customer= (Customer) findUser(customerString);
	

	try {
		if(BookableService.hasWithName(serviceName)) {
			BookableService thisService = BookableService.getWithName(serviceName);
			
			TimeSlot aTimeSlot = findTimeSlotOfApp(serviceName, optionalServicesString, startDateString, startTimeString);
			
			if(getAvailableTimeSlots(startDate).contains(aTimeSlot) && !getUnavailableTimeSlots(startDate).contains(aTimeSlot)) {
				flexiBook.addAppointment(customer, thisService, aTimeSlot);
			}else throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTime);
			
//			if(startDate.before(SystemTime.getSysDate()) || (startDate==SystemTime.getSysDate() && startTime.after(SystemTime.getSysTime()))) {
//				if(betweenBusinessHours(startTime) && betweenBusinessHours(endTime)) {
//					
//					if(!isOverlap(aTimeSlot) && getAvailableTimeSlots().contains(aTimeSlot)) {
//						
//						flexiBook.addAppointment(customer, thisService, aTimeSlot);
//						
//							
//						}else throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTime);
//						
//					}else throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTime);
//					
//				}else throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTime);
//				
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

public static void CancelAppointment(String username, String serviceName, String date, String startTimeString) throws InvalidInputException {
	User user= findUser(username);
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	Appointment anAppointment = findAppointment(username, serviceName, date, startTimeString);
	
	
	try {
		if(anAppointment.getCustomer() == user) {
			
			if(anAppointment != null) {
				if(anAppointment.getTimeSlot().getStartDate().before(SystemTime.getSysDate())){
					flexiBook.removeAppointment(anAppointment);
				}
				else if(anAppointment.getTimeSlot().getStartDate().equals(SystemTime.getSysDate())) {
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


private static boolean betweenBusinessHours(Time time) {
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	boolean isBetween = false;
	
	if(flexiBook.getHours().contains(time)) {
		isBetween = true;
	}
	return isBetween; 
	}

//private static List<TimeSlot> getAvailableTimeSlots(){
//	List<TimeSlot> availableTimeSlots = new ArrayList<TimeSlot>();
//	FlexiBook flexibook = FlexiBookApplication.getFlexibook();
//	List<TimeSlot> tempList = new ArrayList<TimeSlot>(flexibook.getTimeSlots());
//	
//	tempList.removeAll(unavailableTimeSlots());
//	availableTimeSlots = tempList;
//	
//	return availableTimeSlots;
//}
//
//private static List<TimeSlot> unavailableTimeSlots(){
//	List<TimeSlot> unavailableTimeSlots = new ArrayList<TimeSlot>();
//	FlexiBook flexibook = FlexiBookApplication.getFlexibook();
//
//	for (Appointment appointment : flexibook.getAppointments()) {
//		unavailableTimeSlots.add(appointment.getTimeSlot());
//	}
//	for (TimeSlot TS : flexibook.getBusiness().getHolidays()) {
//		unavailableTimeSlots.add(TS);
//	}
//	
//	for (TimeSlot TS : flexibook.getBusiness().getVacation()) {
//		unavailableTimeSlots.add(TS);
//	}
//	
//	return unavailableTimeSlots;
//}
//
//
//private static boolean isOverlap(TimeSlot ts) {
//	boolean is = false;
//	
//	for(int i=0; i <unavailableTimeSlots().size()-1; i++) {
//		TimeSlot tempTimeSlot =unavailableTimeSlots().get(i);
//		
//		if(tempTimeSlot.getEndTime().after(ts.getStartTime()) && tempTimeSlot.getEndTime().before(ts.getEndTime())) {
//			is=true;
//		}else if (tempTimeSlot.getStartTime().after(ts.getStartTime()) && tempTimeSlot.getStartTime().before(ts.getEndTime())) {
//			is = true;
//		}else is = false;
//		
//	}
//	return is;
//	}
private static List<TimeSlot> getAvailableTimeSlots(Date date){
	List<TimeSlot> availableTimeSlots = new ArrayList<TimeSlot>();
	FlexiBook flexibook = FlexiBookApplication.getFlexibook();
	Locale locale = new Locale("en");
	String dayOfTheWeek = getDayString(date, locale);
	for (BusinessHour BH : flexibook.getBusiness().getBusinessHours()) {
		if (BH.getDayOfWeek().equals(dayOfTheWeek)) {
			TimeSlot TS = new TimeSlot (date, BH.getStartTime(), date, BH.getEndTime(), flexibook);
			availableTimeSlots.add(TS);
		}
	}

	for (Appointment appointment : flexibook.getAppointments()) {
		if(appointment.getTimeSlot().getStartDate().compareTo(date) == 0) {
			TimeSlot appTS = appointment.getTimeSlot();
			for (TimeSlot TS : availableTimeSlots) {
				if(isOverlap(appTS, TS)) {
					TimeSlot tmp1 = new TimeSlot(date, TS.getStartTime(), date, appTS.getStartTime(), flexibook);
					TimeSlot tmp2 = new TimeSlot(date, appTS.getEndTime(), date, TS.getEndTime(), flexibook);
					availableTimeSlots.remove(TS);
					availableTimeSlots.add(tmp1);
					availableTimeSlots.add(tmp2);
					for (TimeSlot downtime : getDowntimeTimeSlots(appointment)) {
						availableTimeSlots.add(downtime);
					}
				}
			}

		}

	}

	return availableTimeSlots;
}

private static List<TimeSlot> getDowntimeTimeSlots(Appointment app){
	FlexiBook flexibook = FlexiBookApplication.getFlexibook();
	List<TimeSlot> downtimeTimeSlots = new ArrayList<TimeSlot>();
	BookableService S = app.getBookableService();
	if(S instanceof Service) {
		Service service = (Service) S;
		if (service.getDowntimeDuration() != 0) {
			LocalTime startTime = app.getTimeSlot().getStartTime().toLocalTime().plusMinutes(service.getDowntimeStart());
			LocalTime endTime = startTime.plusMinutes(service.getDowntimeDuration());
			Time start = Time.valueOf(startTime);
			Time end = Time.valueOf(endTime);
			TimeSlot TS = new TimeSlot(app.getTimeSlot().getStartDate(), start, app.getTimeSlot().getStartDate(), end, flexibook);
			downtimeTimeSlots.add(TS);
		}
	}else if(S instanceof ServiceCombo) {
		ServiceCombo combo = (ServiceCombo) S;
		for (ComboItem item : combo.getServices()) {
			Service s = item.getService();
			if (s.getDowntimeDuration() != 0) {
				LocalTime startTime = app.getTimeSlot().getStartTime().toLocalTime().plusMinutes(s.getDowntimeStart());
				LocalTime endTime = startTime.plusMinutes(s.getDowntimeDuration());
				Time start = Time.valueOf(startTime);
				Time end = Time.valueOf(endTime);
				TimeSlot TS = new TimeSlot(app.getTimeSlot().getStartDate(), start, app.getTimeSlot().getStartDate(), end, flexibook);
				downtimeTimeSlots.add(TS);
			}
		}

	}


	return downtimeTimeSlots;
}

private static List<TimeSlot> getUnavailableTimeSlots(Date date){
	List<TimeSlot> unavailableTimeSlots = new ArrayList<TimeSlot>();
	FlexiBook flexibook = FlexiBookApplication.getFlexibook();
	Locale locale = new Locale("en");
	String dayOfTheWeek = getDayString(date, locale);
	Time startBHTime = null;
	Time endBHTime = null;
	List<TimeSlot> available = getAvailableTimeSlots(date);

	for (BusinessHour BH : flexibook.getBusiness().getBusinessHours()) {
		if (BH.getDayOfWeek().equals(dayOfTheWeek)) {
			startBHTime = BH.getStartTime();
			endBHTime = BH.getEndTime();
		}
	}
	for (int i = 0; i<available.size(); i++) {
		if (i == 0) {
			TimeSlot first = available.get(i);
			if (first.getStartTime().after(startBHTime)){
				TimeSlot TS = new TimeSlot(date, startBHTime, date, first.getStartTime(), flexibook);
				unavailableTimeSlots.add(TS);
			}
		}else if (i == available.size() - 1) {
			TimeSlot last = available.get(i);
			if (last.getEndTime().before(endBHTime)){
				TimeSlot TS = new TimeSlot(date, last.getEndTime(), date, endBHTime, flexibook);
				unavailableTimeSlots.add(TS);
			}
		}
		if ((sameTime(available.get(i).getEndTime(), available.get(i+1).getEndTime()) == false) && i !=  available.size()-1) {
			TimeSlot ts = new TimeSlot(date, available.get(i).getEndTime(), date, available.get(i+1).getEndTime(), flexibook);
			unavailableTimeSlots.add(ts);
		}
	}

	return unavailableTimeSlots;
}

private static boolean isOverlap(TimeSlot TS1, TimeSlot TS2) {
	if((TS1.getStartTime().before(TS2.getEndTime()) && TS1.getEndTime().before(TS2.getStartTime()))
			|| (TS2.getStartTime().before(TS1.getEndTime()) && TS2.getEndTime().before(TS1.getStartTime()))){
		return true;
	}

	else return false;
}

private static String getDayString(Date date, Locale locale) {
	DateFormat formatter = new SimpleDateFormat("EEEE", locale);
	return formatter.format(date);
}

private static boolean sameTime(Time startTime, Time endTime) {


	LocalTime localStartTime = startTime.toLocalTime();
	LocalTime localEndTime = endTime.toLocalTime();

	Duration d = Duration.between(localStartTime, localEndTime);

	if (d.getSeconds() == 0) return true;
	else return false;

}


private static TimeSlot findTimeSlotOfApp (String serviceName, String optServicesString, String date, String startTimeString) {
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	startTimeString = startTimeString+":00";
	Time startTime = Time.valueOf(startTimeString);
	Date startDate = Date.valueOf(date);
	Time endTime= null;
	Date endDate = startDate;
	BookableService thisService = BookableService.getWithName(serviceName);
	Service service = (Service)thisService;
	
	String[] myArray = optServicesString.split(", ");
	List<String> optionalServices = new ArrayList<>();
	
	for (String str : myArray) {
	    optionalServices.add(str);
	}
	
	if (myArray == null) {
		
		endTime = new Time(startTime.getTime() + service.getDuration());
		
	} else {
		endTime = new Time(startTime.getTime() + service.getDuration());
		
		for(int i=0; i< optionalServices.size()-1; i++) {
			Service service2 = (Service) BookableService.getWithName(optionalServices.get(i));

			endTime = new Time(endTime.getTime() + service2.getDuration());
			
		}
						
	}
	
	TimeSlot aTimeSlot = new TimeSlot(startDate, startTime, endDate, endTime, flexiBook);


return aTimeSlot;
}



private static Appointment findAppointment(String username, String appName, String date, String startTimeString) {
	Customer customer= (Customer) findUser(username);
	BookableService service = BookableService.getWithName(appName);
	TimeSlot aTimeSlot = null;
	aTimeSlot = findTimeSlotOfApp(username, appName, date, startTimeString);
	
	Appointment app=null;
	
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	for(int i=0; i<flexiBook.getAppointments().size(); i++) {
		if(flexiBook.getAppointment(i).getCustomer()== customer) {
			if(flexiBook.getAppointment(i).getBookableService()==service) {
				if(flexiBook.getAppointment(i).getTimeSlot()==aTimeSlot) {
					app = flexiBook.getAppointment(i);
				}
			}
		}
	}

return app;
	}
}