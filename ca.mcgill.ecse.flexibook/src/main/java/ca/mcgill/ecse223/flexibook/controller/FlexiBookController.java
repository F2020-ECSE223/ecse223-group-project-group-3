package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
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
			if (customer.getUsername().equals(username)) {
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
/**
 * @author tamara
 * The makeAppointment method takes for input the username of the customer, the name of the service desired, 
 * the optional services if the service desired is a service combo and the desired date and time.
 * It creates an appointment for the customer in the flexibook application for the inputs. If the 
 * appointment is unsuccessful due to various reasons, an error is thrown and the system reports
 * that the reason it failed.
 * @param customerString
 * @param serviceName
 * @param optionalServicesString
 * @param startDateString
 * @param startTimeString
 * @throws InvalidInputException
 */
public static void makeAppointment(String customerString, String serviceName, String optionalServicesString, String startDateString, String startTimeString) throws InvalidInputException{
	
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	Date startDate = toDate(startDateString);

	try {
		if(customerString.equals("owner")) {
			throw new InvalidInputException("An owner cannot make an appointment");
		}
		if(startDate.before(SystemTime.getSysDate())) {
			throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTimeString);
		}
			Customer customer= (Customer) findUser(customerString);
			BookableService thisService = findBookableService(serviceName);
			
			TimeSlot aTimeSlot = findTimeSlotOfApp(serviceName, optionalServicesString, startDateString, startTimeString);
			
			
			Locale locale = new Locale("en");
			String dayOfTheWeek = getDayString(startDate, locale);
				if (dayOfTheWeek.equals("Saturday") || dayOfTheWeek.equals("Sunday")){
					throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTimeString);
					
				}	
			
			
			for(int i=0; i< getUnavailableTimeSlots(startDate).size(); i++) {
				if(isOverlap(aTimeSlot, getUnavailableTimeSlots(startDate).get(i))) {
					throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTimeString);
				}
			}
			
			for (int i=0; i<getAvailableTimeSlots(startDate).size(); i++) {
				if(s2_isWithin_s1(getAvailableTimeSlots(startDate).get(i), aTimeSlot)) {
					flexiBook.addAppointment(customer, thisService, aTimeSlot);
					break;
				}
				else {
					throw new InvalidInputException("There are no available slots for " + serviceName + " on " + startDate + " at " + startTimeString);
				}
			}		
	}
	catch (RuntimeException e) {
		throw new InvalidInputException(e.getMessage());
	}
}

/**
 * @author tamara
 * The UpdateAppointment method takes for input the username of the customer, his/her 
 * appointment name, the date and time of the appointment, the new date and time of the 
 * appointment, the desired action (remove or add) and the desired ComboItem to remove/add.
 * If the customer wants to change the time or date of the appointment, then the inputs action and 
 * item string will be equal to null. The method will update the appointment and the system will 
 * return whether the update was successful or not.
 * 
 *  If the customer wants to add or remove a ComboItem from their service combo, the newDateString 
 *  and newStartTimeString inputs will have to be the same as oldDateString and oldStartTimeString.
 *  Then depending on the chosen action, the method updates the appointment and the system throws
 *  whether it was successful or not.
 *  
 * The first two input parameters are always set the same unless it is a random user or the owner
 * who is trying to update the appointment of another customer. In this case the two first inputs
 * are not equal and the system throws an error.
 * @param user
 * @param customerString
 * @param appointmentName
 * @param oldDateString
 * @param oldStartTimeString
 * @param newDateString
 * @param newStartTimeString
 * @param action
 * @param itemString
 * @throws InvalidInputException
 */
public static void UpdateAppointment(String user, String customerString, String appointmentName, String oldDateString, String oldStartTimeString, String newDateString, String newStartTimeString, String action, String itemString ) throws InvalidInputException {
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	
	oldStartTimeString =oldStartTimeString +":00";
	newStartTimeString = newStartTimeString+":00";
	
	Time newStartTime = toTime(newStartTimeString);
	Date newStartDate = toDate(newDateString);
	Time oldStartTime = toTime(oldStartTimeString);
	Date oldStartDate = toDate(oldDateString);
	Date oldEndDate = oldStartDate;
	
	Date newEndDate = newStartDate;
	
	Customer customer= (Customer) findUser(customerString);
	
	BookableService service = findBookableService(appointmentName);
	
	if(user.equals("owner")) {
		throw new InvalidInputException("An owner cannot update a customer's appointment");
	}
	if(!user.equals(customerString)) {
		throw new InvalidInputException("A customer can only update their own appointments");
	}
	
if(!newStartTimeString.equals(oldStartTimeString) || !newDateString.equals(oldDateString)) {	
	
	if(newStartDate.before(SystemTime.getSysDate())) {
		throw new InvalidInputException("unsuccessful");
	}
	
	Locale locale = new Locale("en");
	String dayOfTheWeek = getDayString(newStartDate, locale);
		if (dayOfTheWeek.equals("Saturday") || dayOfTheWeek.equals("Sunday")){
			throw new InvalidInputException("unsuccessful");
			
		}
	Appointment app= findAppointment(customerString, appointmentName, oldDateString, oldStartTimeString);
	
	
	LocalTime localOldEndTime = app.getTimeSlot().getEndTime().toLocalTime();
	LocalTime localOldStartTime = oldStartTime.toLocalTime();
	LocalTime localNewStartTime = newStartTime.toLocalTime();
	
	Duration dur = Duration.between(localOldStartTime, localOldEndTime);
	
	
	LocalTime localNewEndTime = localNewStartTime.plusMinutes((dur.getSeconds()/60));
	Time newEndTime = Time.valueOf(localNewEndTime);
	
	TimeSlot newTimeSlot = new TimeSlot(newStartDate, newStartTime, newEndDate, newEndTime, flexiBook);
	
	//CancelAppointment(customerString, customerString,appointmentName, oldDateString, oldStartTimeString );
	flexiBook.removeAppointment(app);
	//app.delete();
	List <Appointment> appointments = flexiBook.getAppointments();
//	for(int i=0; i<flexiBook.getBusiness().getHolidays().size(); i++) {
//		TimeSlot aTimeSlot = flexiBook.getBusiness().getHolidays().get(i);
//		if(isOverlap(aTimeSlot, newTimeSlot)) {
//			throw new InvalidInputException("unsuccessful");
//		}
//	}
//	
	for(int k = 0; k<flexiBook.getBusiness().getHolidays().size();k++) {
		TimeSlot holiday = flexiBook.getBusiness().getHolidays().get(k);
		for(LocalDate localDate = holiday.getStartDate().toLocalDate(); 
				localDate.isBefore(holiday.getEndDate().toLocalDate().plusDays(1)); 
				localDate = localDate.plusDays(1))
		{
			Date d  = Date.valueOf(localDate);
			if(d.compareTo(newTimeSlot.getStartDate())==0) {
				if(isOverlap(holiday, newTimeSlot)) {
					throw new InvalidInputException("unsuccessful");
				}
			
			}
		}
	}
	
	
	for(int i=0; i< getUnavailableTimeSlots(newStartDate).size(); i++) {
		if(isOverlap(newTimeSlot, getUnavailableTimeSlots(newStartDate).get(i))) {
			flexiBook.addAppointment(app);
			throw new InvalidInputException("unsuccessful");
		}
	}
	
	for (int i=0; i<getAvailableTimeSlots(newStartDate).size(); i++) {
		if(s2_isWithin_s1(getAvailableTimeSlots(newStartDate).get(i), newTimeSlot)) {
			flexiBook.addAppointment(customer, service, newTimeSlot);
			break;
		}
		else {
			flexiBook.addAppointment(app);
			throw new InvalidInputException("unsuccessful");
		}
	}		
	
}
if(action!=null && itemString!=null) {
	Appointment app = findAppointment(customerString, appointmentName, oldDateString, oldStartTimeString);
	List <ComboItem> list = app.getChosenItems();
	List <ComboItem> list2 = new ArrayList<>(list);
	
	TimeSlot newTimeSlot = null;
	
	ServiceCombo serviceCombo = findServiceCombo(appointmentName);
	int removedDuration=0;
	int addedDuration =0;
	
	if(action.equals("remove")) {
		if(!(serviceCombo.getMainService()==null) && serviceCombo.getMainService().getService().getName().equals(itemString)) {
			throw new InvalidInputException("unsuccessful");
		}
		
		for (int i=0; i< list.size(); i++) {

			if(list.get(i).getService().getName().equals(itemString)) {
				if(list.get(i).isMandatory()) {
					throw new InvalidInputException("unsuccessful");
				}
				
				
				removedDuration = list.get(i).getService().getDuration();
				list2.remove(list.get(i));
				
				break;
			}
		}
		TimeSlot oldTimeSlot = app.getTimeSlot();
		Duration dur = Duration.between(oldTimeSlot.getStartTime().toLocalTime(), oldTimeSlot.getEndTime().toLocalTime());
		Duration newDur = dur.minusMinutes(removedDuration);
		
		
		LocalTime localNewEndTime = oldStartTime.toLocalTime().plusMinutes(newDur.getSeconds()/60);
		Time newEndTime = Time.valueOf(localNewEndTime);
		newTimeSlot = new TimeSlot(oldStartDate, oldStartTime, oldEndDate, newEndTime, flexiBook);
		
		
	}
	else if(action.equals("add")) {
		for(int i=0; i<serviceCombo.getServices().size(); i++) {
			if(serviceCombo.getServices().get(i).getService().getName().equals(itemString)) {
				
				addedDuration = serviceCombo.getServices().get(i).getService().getDuration();
				list2.add(serviceCombo.getServices().get(i));
			}
		}
		TimeSlot oldTimeSlot = app.getTimeSlot();
		Duration dur = Duration.between(oldTimeSlot.getStartTime().toLocalTime(), oldTimeSlot.getEndTime().toLocalTime());
		Duration newDur = dur.plusMinutes(addedDuration);
		
		
		LocalTime localNewEndTime = oldStartTime.toLocalTime().plusMinutes(newDur.getSeconds()/60);
		Time newEndTime = Time.valueOf(localNewEndTime);
		newTimeSlot = new TimeSlot(oldStartDate, oldStartTime, oldEndDate, newEndTime, flexiBook);
		
		
	}
	flexiBook.removeAppointment(app);
	
	for(int i=0; i<flexiBook.getBusiness().getHolidays().size(); i++) {
		TimeSlot aTimeSlot = flexiBook.getBusiness().getHolidays().get(i);
		if(isOverlap(aTimeSlot, newTimeSlot)) {
			throw new InvalidInputException("unsuccessful");
		}
	}
	for(int i=0; i< getUnavailableTimeSlots(newStartDate).size(); i++) {
		if(isOverlap(newTimeSlot, getUnavailableTimeSlots(newStartDate).get(i))) {
			flexiBook.addAppointment(app);
			throw new InvalidInputException("unsuccessful");
		}
	}
	
	for (int i=0; i<getAvailableTimeSlots(newStartDate).size(); i++) {
		if(s2_isWithin_s1(getAvailableTimeSlots(newStartDate).get(i), newTimeSlot)) {
			Appointment app2 = new Appointment(customer, serviceCombo, newTimeSlot, flexiBook);
			
			for(int j=0; j<list2.size(); j++){
				app2.addChosenItem(list2.get(j));
			}
			break;
		}
		else {
			flexiBook.addAppointment(app);
			throw new InvalidInputException("unsuccessful");
		}
	}			
}
	
}

/**
 * @author tamara
 * The CancelAppointment method takes for input the username of the customer, his appointment name
 * and the date and start time of the appointment.
 * This method cancels an appointment that is already in the system.
 * If the cancelation is unsuccessful for various reasons, the system will throw an error.
 * 
 * The first two input parameters are always the same unless it is a random user or the owner that is
 * trying to cancel the appointment of a customer. The system then throws an error saying it is
 * impossible.
 * @param user
 * @param username
 * @param serviceName
 * @param date
 * @param startTimeString
 * @throws InvalidInputException
 */
public static void CancelAppointment(String user,String username, String serviceName, String date, String startTimeString) throws InvalidInputException {

	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	Appointment anAppointment = findAppointment(username, serviceName, date, startTimeString);
	Date startDate = toDate(date);
	
	
	try {
		if(user.equals("owner")) {
			throw new InvalidInputException("An owner cannot cancel an appointment");
			}
		else if(!user.equals(username)) {
		 throw new InvalidInputException("A customer can only cancel their own appointments");
		}
		else if(startDate.equals(SystemTime.getSysDate())){
			throw new InvalidInputException("Cannot cancel an appointment on the appointment date");
		}
		
		else {
			flexiBook.removeAppointment(anAppointment);
		}
				
			
	}catch(RuntimeException e) {
		throw new InvalidInputException(e.getMessage());
	}
}


private static List<TimeSlot> getAvailableTimeSlots(Date date){
	List<TimeSlot> availableTimeSlots = new ArrayList<TimeSlot>();
	FlexiBook flexibook = FlexiBookApplication.getFlexibook();
	Locale locale = new Locale("en");
	String dayOfTheWeek = getDayString(date, locale);
	for (BusinessHour BH : flexibook.getHours()) {
		if (BH.getDayOfWeek().toString().equals(dayOfTheWeek)) {
			TimeSlot TS = new TimeSlot (date, BH.getStartTime(), date, BH.getEndTime(), flexibook);
			availableTimeSlots.add(TS);
		}
	}
	for (Appointment appointment : flexibook.getAppointments()) {
		if(appointment.getTimeSlot().getStartDate().compareTo(date) == 0) {
			TimeSlot appTS = appointment.getTimeSlot();
			if (availableTimeSlots.size()!=0) {
				for(int i = 0; i<availableTimeSlots.size(); i++) {
					TimeSlot TS = availableTimeSlots.get(i);
					if(isOverlap(appTS, TS)) {

						LocalTime S1 = appTS.getStartTime().toLocalTime();
						LocalTime S2 = TS.getStartTime().toLocalTime();
						LocalTime E1 = appTS.getEndTime().toLocalTime();
						LocalTime E2 = TS.getEndTime().toLocalTime();

						if (S1.compareTo(S2) == 0 && E1.compareTo(E2)==0) {
							availableTimeSlots.remove(TS);
						}
						else if(S1.compareTo(S2) == 0) {
							TimeSlot tmp = new TimeSlot(date, appTS.getEndTime(), date, TS.getEndTime(), flexibook);
							availableTimeSlots.add(tmp);
							availableTimeSlots.remove(TS);
						}
						else if(E1.compareTo(E2)==0) {
							TimeSlot tmp = new TimeSlot(date, TS.getStartTime(), date, appTS.getStartTime(), flexibook);
							availableTimeSlots.add(tmp);
							availableTimeSlots.remove(TS);
						}
						else {
							TimeSlot tmp1 = new TimeSlot(date, TS.getStartTime(), date, appTS.getStartTime(), flexibook);
							TimeSlot tmp2 = new TimeSlot(date, appTS.getEndTime(), date, TS.getEndTime(), flexibook);
							availableTimeSlots.remove(TS);
							availableTimeSlots.add(tmp1);
							availableTimeSlots.add(tmp2);
						}
					}
					for(int j = 0; i<getDowntimeTimeSlots(appointment).size();i++) {
						TimeSlot downtime = getDowntimeTimeSlots(appointment).get(j);
						availableTimeSlots.add(downtime);

					}
				}
			}

		}
	}

	for(int k = 0; k<flexibook.getBusiness().getHolidays().size();k++) {
		TimeSlot holiday = flexibook.getBusiness().getHolidays().get(k);
		for(LocalDate localDate = holiday.getStartDate().toLocalDate(); 
				localDate.isBefore(holiday.getEndDate().toLocalDate().plusDays(1)); 
				localDate = localDate.plusDays(1))
		{
			Date d  = Date.valueOf(localDate);
			if(d.compareTo(date)==0) {
				for(int i = 0; i<availableTimeSlots.size(); i++) {
					availableTimeSlots.remove(i);
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
		int minutes = 0;
		ServiceCombo combo = (ServiceCombo) S;
		for (ComboItem item : combo.getServices()) {
			Service s = item.getService();
			minutes += s.getDuration(); 
			if (s.getDowntimeDuration() != 0) {
				minutes -= s.getDuration();
				LocalTime startTime = app.getTimeSlot().getStartTime().toLocalTime().plusMinutes(s.getDowntimeStart() + minutes);
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
	for (BusinessHour BH : flexibook.getHours()) {
		if (BH.getDayOfWeek().toString().equals(dayOfTheWeek)) {
			TimeSlot TS = new TimeSlot (date, BH.getStartTime(), date, BH.getEndTime(), flexibook);
			unavailableTimeSlots.add(TS);
		}	
	}
	List<TimeSlot> available = getAvailableTimeSlots(date);

	for (int i = 0; i<available.size(); i++) {

		TimeSlot av = available.get(i);

		for (int j = 0; j<unavailableTimeSlots.size(); j++) {
			TimeSlot un = unavailableTimeSlots.get(j);
			if(isOverlap(av, un)) {

				LocalTime S1 = av.getStartTime().toLocalTime();
				LocalTime S2 = un.getStartTime().toLocalTime();
				LocalTime E1 = av.getEndTime().toLocalTime();
				LocalTime E2 = un.getEndTime().toLocalTime();

				if (S1.compareTo(S2) == 0 && E1.compareTo(E2)==0) {
					unavailableTimeSlots.remove(un);
				}
				else if(S1.compareTo(S2) == 0) {
					TimeSlot tmp = new TimeSlot(date, av.getEndTime(), date, un.getEndTime(), flexibook);
					unavailableTimeSlots.add(tmp);
					unavailableTimeSlots.remove(un);
				}
				else if(E1.compareTo(E2)==0) {
					TimeSlot tmp = new TimeSlot(date, un.getStartTime(), date, av.getStartTime(), flexibook);
					unavailableTimeSlots.add(tmp);
					unavailableTimeSlots.remove(un);
				}
				else {
					TimeSlot tmp1 = new TimeSlot(date, un.getStartTime(), date, av.getStartTime(), flexibook);
					TimeSlot tmp2 = new TimeSlot(date, av.getEndTime(), date, un.getEndTime(), flexibook);
					unavailableTimeSlots.remove(un);
					unavailableTimeSlots.add(tmp1);
					unavailableTimeSlots.add(tmp2);
				}
			}

		}
	}

	return unavailableTimeSlots;
}

private static boolean isOverlap(TimeSlot TS1, TimeSlot TS2) {
	LocalTime S1 = TS1.getStartTime().toLocalTime();
	LocalTime S2 = TS2.getStartTime().toLocalTime();
	LocalTime E1 = TS1.getEndTime().toLocalTime();
	LocalTime E2 = TS2.getEndTime().toLocalTime();

	return S1.isBefore(E2) && S2.isBefore(E1);
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

/**
 * @author tamara
 * The findTimeSlotOfApp is a helper method that calculates the time slot of an appointment
 * depending on the start time, start date and duration of the appointment.
 * @param serviceName
 * @param optServicesString
 * @param date
 * @param startTimeString
 * @return TimeSlot
 */
private static TimeSlot findTimeSlotOfApp (String serviceName, String optServicesString, String date, String startTimeString) {
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	startTimeString = startTimeString+":00";
	Time startTime = toTime(startTimeString);
	Date startDate = toDate(date);
	Time endTime= null;
	Date endDate = startDate;
	BookableService thisService = findBookableService(serviceName);
	
	LocalTime localStartTime = startTime.toLocalTime();
	LocalTime localEndTime;
	
	
	if (optServicesString == null) {
		Service service = (Service)thisService;
		
		localEndTime = localStartTime.plusMinutes(service.getDuration());
		
		endTime = Time.valueOf(localEndTime);
		
		
	} else {
		String[] myArray = optServicesString.split(",");
		List<String> optionalServices = new ArrayList<>();
		
		for (String str : myArray) {
		    optionalServices.add(str);
		}

		ServiceCombo service = (ServiceCombo)thisService;
		
		int min =0;
						
		for(int i=0; i<service.getServices().size(); i++) {
			ComboItem item = service.getServices().get(i);
			if(optionalServices.contains(item.getService().getName()) || item.isMandatory()){
				min += item.getService().getDuration();
			}
		}
		if(service.getMainService()!=null) {
			min+=service.getMainService().getService().getDuration();		
		}
		
		
		localEndTime = localStartTime.plusMinutes(min);
		endTime = Time.valueOf(localEndTime);
						
	}
	
	TimeSlot aTimeSlot = new TimeSlot(startDate, startTime, endDate, endTime, flexiBook);

	
return aTimeSlot;
	}

/**
 * @author tamara
 * The findAppointment method is a helper method that finds the desired appointment in the flexibook
 * application using the username of the customer, and his appointment information(name, date, start 
 * time).
 * @param username
 * @param appName
 * @param dateString
 * @param startTimeString
 * @return Appointment
 */
private static Appointment findAppointment(String username, String appName, String dateString, String startTimeString) {
	Customer customer= (Customer) findUser(username);
	BookableService service = findBookableService(appName);
	Time startTime = toTime(startTimeString);
	Date date = toDate(dateString);
	LocalTime localStartTime =startTime.toLocalTime();

	Appointment app=null;
	
	FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
	for(int i=0; i<flexiBook.getAppointments().size(); i++) {
		if(flexiBook.getAppointment(i).getCustomer().getUsername().equals(username)) {
			if(flexiBook.getAppointment(i).getBookableService().getName().equals(appName)) {
				if(flexiBook.getAppointment(i).getTimeSlot().getStartDate().compareTo(date)==0) {
					if(flexiBook.getAppointment(i).getTimeSlot().getStartTime().toLocalTime().compareTo(localStartTime)==0) {
						app = flexiBook.getAppointment(i);
				}
			}
		}
	}
}

return app;
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


private static Time toTime(String t) {
	String[] tArray = t.split(":");
	int[] intArray = new int[2];
	intArray[0] = Integer.parseInt(tArray[0]);
	intArray[1] = Integer.parseInt(tArray[1]);
	LocalTime localTime = LocalTime.of(intArray[0], intArray[1]);
	return Time.valueOf(localTime);

}

private static Date toDate(String d) {

	String[] dArray = d.split("-");
	int[] intArray = new int[3];
	intArray[0] = Integer.parseInt(dArray[0]);
	intArray[1] = Integer.parseInt(dArray[1]);
	intArray[2] = Integer.parseInt(dArray[2]);


	LocalDate localDate = LocalDate.of(intArray[0], intArray[1], intArray[2]);
	return Date.valueOf(localDate);

}
/**
 * @author tamara
 * The findBookabkleService is a helper method that finds the desired Bookable Service, ie 
 * a normal service or a service combo.
 * @param service
 * @return BookableService
 */
private static BookableService findBookableService(String service) {

	FlexiBook flexibook = FlexiBookApplication.getFlexibook();

	for (BookableService aService : flexibook.getBookableServices()) {
		if (aService instanceof Service) {
			if (aService.getName().equals(service)) return (Service) aService;
		}
		else if(aService instanceof ServiceCombo) {
			if(aService.getName().equals(service)) return (ServiceCombo) aService;
		}
	}

	return null;
}
/**
 * @author tamara
 * This method is to check if a time slot is within another time slot by comparing the two start 
 * times, the dates and the end times.
 * If it returns true then the time slot is within the other, if it returns false then they are 
 * two disjoint time slots.
 * @param S1
 * @param S2
 * @return boolean
 */
private static boolean s2_isWithin_s1 (TimeSlot S1, TimeSlot S2) {
	boolean isWithin = false;
	
	LocalTime startTime1 = S1.getStartTime().toLocalTime();
	LocalTime startTime2 = S2.getStartTime().toLocalTime();
	LocalTime endTime1 = S1.getEndTime().toLocalTime();
	LocalTime endTime2 = S2.getEndTime().toLocalTime();
	
	Date date1 = S1.getStartDate();
	Date date2 = S2.getStartDate();
	
	if(startTime1.compareTo(startTime2)<0 || startTime1.compareTo(startTime2)==0) {
		if(endTime1.compareTo(endTime2)>0 || endTime1.compareTo(endTime2)==0){
			isWithin = true;
		}
	}
return isWithin;		
}

}