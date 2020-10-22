package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
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
		User user = findUser(username);
		try {
			if (username.equals("owner") && password.equals("owner")) {
				Owner owner = new Owner(username, password, FlexiBookApplication.getFlexibook());
				FlexiBookApplication.setCurrentUser(owner);
				return;

			}
			else if (user != null  && checkPassword(user, password)) {
				FlexiBookApplication.setCurrentUser(user);
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

	public static List<TOAppointmentCalendarItem> viewAppointmentCalendar(String username, String startDate, boolean isDaily) throws InvalidInputException{
		List<TOAppointmentCalendarItem> items = new ArrayList<TOAppointmentCalendarItem>();
		try {
			Date start = toDate(startDate);
			Date end = null;
			if(isDaily) {
				end = start;
			}
			else {
				LocalDate localEndDate = start.toLocalDate().plusDays(7);
				end = Date.valueOf(localEndDate);
			}


			for (LocalDate localDate = start.toLocalDate(); localDate.isBefore(end.toLocalDate()); localDate = localDate.plusDays(1))
			{
				Date date = Date.valueOf(localDate);
				TOAppointmentCalendarItem item = new TOAppointmentCalendarItem(date);
				for (TOTimeSlot ts : getAvailableTOTimeSlots(date)) {
					item.addAvailableTimeSlot(ts);
				}
				for (TOTimeSlot ts : getUnavailableTOTimeSlots(date)) {
					item.addUnavailableTimeSlot(ts);
				}
				items.add(item);	
			}



		}catch(java.time.DateTimeException e) {
			throw new InvalidInputException(startDate+" is not a valid date");
		}
		return items;
	}



	private static User findUser(String username) {
		User foundUser = null;

		if (username.equals("owner")) {

			Owner owner = FlexiBookApplication.getFlexibook().getOwner();
			foundUser = owner;
			return foundUser;

		}
		else {
			for (Customer customer : FlexiBookApplication.getFlexibook().getCustomers()) {
				if (customer.getUsername().equals(username)) {
					foundUser = customer;
					return foundUser;
				}
			}

		}
		return foundUser;
	}	

	private static boolean checkPassword(User user, String password) {
		if (user.getPassword().equals(password)) return true;

		else return false;
	}


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

	private static Service findService(String service) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();

		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof Service) {
				if (aService.getName().equals(service)) return (Service) aService;
			}
		}

		return null;
	}


	private static List<TOTimeSlot> getAvailableTOTimeSlots(Date date){
		List<TOTimeSlot> available =new ArrayList<TOTimeSlot>();

		for(TimeSlot TS : getAvailableTimeSlots(date)) {
			TOTimeSlot TOtimeSlot = new TOTimeSlot(TS.getStartDate(), TS.getStartTime(), TS.getEndDate(),TS.getEndTime());
			available.add(TOtimeSlot);
		}

		return available;
	}

	private static List<TOTimeSlot> getUnavailableTOTimeSlots(Date date){
		List<TOTimeSlot> unavailable =new ArrayList<TOTimeSlot>();

		for(TimeSlot TS : getUnavailableTimeSlots(date)) {
			TOTimeSlot TOtimeSlot = new TOTimeSlot(TS.getStartDate(), TS.getStartTime(), TS.getEndDate(),TS.getEndTime());
			unavailable.add(TOtimeSlot);
		}

		return unavailable;
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

	private static Date toDate(String d) throws InvalidInputException {
		String[] dArray = d.split("-");
		int[] intArray = new int[3];
		intArray[0] = Integer.parseInt(dArray[0]);
		intArray[1] = Integer.parseInt(dArray[1]);
		intArray[2] = Integer.parseInt(dArray[2]);
		LocalDate localDate = null;

		localDate = LocalDate.of(intArray[0], intArray[1], intArray[2]);

		return Date.valueOf(localDate);

	}


}
