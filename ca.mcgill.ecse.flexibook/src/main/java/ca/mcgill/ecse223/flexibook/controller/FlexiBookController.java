package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
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

	public static void viewAppointmentCalendar(String username, Date date, boolean dailyView) throws InvalidInputException{
		try {

			//if(date.get)


		}catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
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
					//return foundUser;
				}
			}



		}
		return foundUser;
	}	

	private static boolean checkPassword(User user, String password) {
		if (user.getPassword().equals(password)) return true;

		else return false;
	}


	private static List<TimeSlot> getAvailableTimeSlots(Date date, boolean isDaily){
		List<TimeSlot> availableTimeSlots = new ArrayList<TimeSlot>();
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();

		for (BusinessHour BH : flexibook.getBusiness().getBusinessHours()) {

		}

		for (Appointment appointment : flexibook.getAppointments()) {


		}

		return availableTimeSlots;
	}

	private static List<TimeSlot> getUnavailableTimeSlots(Date date, boolean isDaily){
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

	private static boolean isOverlap(TimeSlot TS) {
		return true;
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






}
