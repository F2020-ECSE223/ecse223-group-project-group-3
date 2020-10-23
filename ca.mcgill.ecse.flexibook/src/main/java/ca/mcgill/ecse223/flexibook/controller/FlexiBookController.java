
  
package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
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

	public static TOAppointmentCalendarItem viewAppointmentCalendar(String username, String startDate, boolean isDaily) throws InvalidInputException{
		TOAppointmentCalendarItem item = null;
		try {
			Date start = toDate(startDate);
			item = new TOAppointmentCalendarItem(start);
			if(!isDaily) {

				LocalDate localEndDate = start.toLocalDate().plusDays(7);
				Date end = Date.valueOf(localEndDate);

				for (LocalDate localDate = start.toLocalDate(); localDate.isBefore(end.toLocalDate()); localDate = localDate.plusDays(1))
				{
					Date date = Date.valueOf(localDate);
					for (TOTimeSlot ts : getAvailableTOTimeSlots(date)) {
						item.addAvailableTimeSlot(ts);
					}
					for (TOTimeSlot ts : getUnavailableTOTimeSlots(date)) {
						item.addUnavailableTimeSlot(ts);
					}
				}
			}else {
				for (TOTimeSlot ts : getAvailableTOTimeSlots(start)) {
					item.addAvailableTimeSlot(ts);
				}
				for (TOTimeSlot ts : getUnavailableTOTimeSlots(start)) {
					item.addUnavailableTimeSlot(ts);
				}
			}

		}catch(java.time.DateTimeException e) {
			throw new InvalidInputException(startDate+" is not a valid date");
		}
		return item;
	}


	//Marc------------------------------------------------------------------------------------------------------------------


	public static void addService(String serviceName, int duration,int downtimeDuration, 
			int downtimeStart,String user) 
					throws InvalidInputException{
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		try {

			if (findService(serviceName) != null) {
				throw new InvalidInputException("Service " + serviceName + " already exists");
			}

			if (user.equals("owner")) {	
				serviceSpecificationAdd(serviceName,duration,downtimeDuration,downtimeStart);
				Service service = new Service (serviceName, flexibook,duration, downtimeDuration,downtimeStart);
			}

			else
				throw new InvalidInputException ("You are not authorized to perform this operation");
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}	    	
	}


	public static void deleteService(String service,String username) throws InvalidInputException {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		try {
			if (username.equals("owner")) {
				Service serviceToDelete = findService(service);

				for(Appointment app : flexibook.getAppointments()) {
					if (app.getBookableService().getName().equals(service)) {

						if(app.getTimeSlot().getStartDate().after(SystemTime.getSysDate())) {
							throw new InvalidInputException("The service contains future appointments");
						}
						else {
							flexibook.removeAppointment(app);
							app.delete();

						}
					}
				}

				for(ServiceCombo combo : getServiceCombos()) {
					
					for(ComboItem item : combo.getServices()) {
						if (item.getService() == findService(service)) {
							if(item.getMandatory() == true) {
								combo.removeService(item);
								item.delete();
								flexibook.removeBookableService(combo);
								combo.delete();

							}
							else {
								combo.removeService(item);
								item.delete();

							}
						}
					}
				}

				flexibook.removeBookableService(serviceToDelete);
				serviceToDelete.delete();
			}
			else {
				throw new InvalidInputException("You are not authorized to perform this operation");
			}


		}catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}


	}

	public static void updateService (String service, int duration,int downtimeDuration, 
			int downtimeStart,String user,String serviceName) throws InvalidInputException {

		try {
			serviceSpecificationUpdate(service, serviceName,duration,downtimeDuration,downtimeStart);

			if (user.equals("owner")) {
				Service S = findService (service);			 
				S.setName(serviceName);  
				S.setDowntimeStart(downtimeStart);
				S.setDowntimeDuration(downtimeDuration);
				S.setDuration(duration);
			}
			else {
				throw new InvalidInputException ("You are not authorized to perform this operation");

			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	private static void serviceSpecificationAdd (String serviceName, int duration, int downtimeDuration, int downtimeStart)
			throws InvalidInputException {

		if (duration <= 0) {
			throw new InvalidInputException("Duration must be positive");
		}

		else if(downtimeStart > duration) {
			throw new InvalidInputException("Downtime must not start after the end of the service");
		}

		else if (downtimeStart >0 && downtimeDuration <= 0) {
			throw new InvalidInputException("Downtime duration must be positive");
		}

		else if (downtimeDuration < 0) {
			throw new InvalidInputException("Downtime duration must be 0");
		}
		else if ((downtimeDuration > 0) && (downtimeStart == 0)) {
			throw new InvalidInputException("Downtime must not start at the beginning of the service");
		}
		else if (downtimeStart < 0) {
			throw new InvalidInputException("Downtime must not start before the beginning of the service");
		}

		else if (downtimeStart + downtimeDuration > duration) {
			throw new InvalidInputException("Downtime must not end after the service");
		}
		else if(downtimeStart > duration) {
			throw new InvalidInputException("Downtime must not start after the end of the service");
		}

	}


	private static void serviceSpecificationUpdate (String service, String serviceName, int duration, int downtimeDuration, int downtimeStart)
			throws InvalidInputException {

		if (duration <= 0) {
			throw new InvalidInputException("Duration must be positive");
		}
		else if(downtimeStart > duration) {
			throw new InvalidInputException("Downtime must not start after the end of the service");
		}

		else if (downtimeStart >0 && downtimeDuration <= 0) {
			throw new InvalidInputException("Downtime duration must be positive");
		}

		else if (downtimeDuration < 0) {
			throw new InvalidInputException("Downtime duration must be 0");
		}
		else if ((downtimeDuration > 0) && (downtimeStart == 0)) {
			throw new InvalidInputException("Downtime must not start at the beginning of the service");
		}
		else if (downtimeStart < 0) {
			throw new InvalidInputException("Downtime must not start before the beginning of the service");
		}

		else if (downtimeStart + downtimeDuration > duration) {
			throw new InvalidInputException("Downtime must not end after the service");
		}
		else if((!service.equals(serviceName)) && findService(serviceName) != null) {
			throw new InvalidInputException("Service " +serviceName+ " already exists");
		}

	}

	private static List<ServiceCombo> getServiceCombos(){
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		List<ServiceCombo> combos = new ArrayList<ServiceCombo>(); 

		for (BookableService combo : flexibook.getBookableServices()) {
			if (combo instanceof ServiceCombo) {
				ServiceCombo cmb = (ServiceCombo) combo;
				combos.add(cmb);
			}
		}
		return combos;
	}


	//------------------------------------------------------------------------------------------------------------------------

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
				TimeSlot un = unavailableTimeSlots.get(i);
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
