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
import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;

import ca.mcgill.ecse.flexibook.application.SystemTime;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.application.SystemTime;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.TimeSlot;

import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;


public class FlexiBookController {


	public FlexiBookController() {	
	}

	//Eric-------------------------------------------------------------------------------------------------------------------

	/**
	 * Login as customer or owner.
	 * As an owner, I want to log in so that I can access the space to manage my business. 
	 * As a customer, I want to log in so that I can manage my appointments.
	 * The owner account is created automatically if it does not exist.
	 * @author Eric Chehata
	 * @param username: The username input to login
	 * @param password: The password input to login
	 * @return Nothing
	 * @throws InvalidInputException: if username doesn't match any customer in the system or if the the password entered is wrong.
	 */

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

		}catch (RuntimeException e) {

			throw new InvalidInputException(e.getMessage());
		}		

	}

	/**
	 * Logout.
	 * As a user, I want to log out of the application so that the next user does not have access to my information
	 * @author Eric Chehata
	 * @return Nothing
	 * @throws InvalidInputException: if user is already logged out.
	 */

	public static void logout () throws InvalidInputException{

		try {
			if (FlexiBookApplication.getCurrentUser() != null) FlexiBookApplication.setCurrentUser(null);
			else throw new InvalidInputException("The user is already logged out");

		}
		catch (RuntimeException e) {

			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 *View appointment calendar
	 *As a user, I want to view the appointment calendar so that I can select a time slot for my appointment and/or browse my scheduled appointments.
	 * @author Eric Chehata
	 * @param username: username of user
	 * @param startDate: Date or week starting at that date for which the user wants to view the appointment calendar
	 * @param isDaily: checks if user wants to view the appointment Calendar for a specific day or specific week
	 * @return TOAppointmentCalendarItem items that contains a list of the TO available time slots in the system and a list of the TO unavailable time slots in the system  
	 * @throws InvalidInputException: if date entered is not valid
	 */
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

				//for(Appointment app : flexibook.getAppointments()) {
				for(int i = 0; i<flexibook.getAppointments().size(); i++) {
					Appointment app = flexibook.getAppointments().get(i);
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

				//for(ServiceCombo combo : getServiceCombos()) {
				for (int i = 0; i< getServiceCombos().size(); i++) {
					ServiceCombo combo = getServiceCombos().get(i);
					//for(ComboItem item : combo.getServices()) {
					for(int j =0; j<combo.getServices().size(); j++) {
						ComboItem item = combo.getServices().get(j);
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



	//Saeid------------------------------------------------------------------------------------------------------------------------




	/**
	 * @author Mohammad Saeid Nafar
	 * Registers a customer account given a username and password
	 * @param username: The username to set to the account
	 * @param password: The password to set to the account
	 * @throws InvalidInputException
	 */
	public static void signUpCustomerAccount(String username, String password) throws InvalidInputException {
		try {
			FlexiBook flexibook = FlexiBookApplication.getFlexibook();

			if(username.equals("") || username == null) {
				throw new InvalidInputException("The user name cannot be empty");
			} else if(password.equals("") || password == null) {
				throw new InvalidInputException("The password cannot be empty");
			} else if(findUser("owner") != null && FlexiBookApplication.getCurrentUser() == findUser("owner") ) {
				throw new InvalidInputException("You must log out of the owner account before creating a customer account");
			} else if(findUser(username) != null) {
				throw new InvalidInputException("The username already exists");
			} else

				flexibook.addCustomer(username, password);

		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * @author Mohammad Saeid Nafar
	 * Updates a user account by setting a new username and and a new passowrd to the account
	 * @param username: The current username set to the account 
	 * @param newUsername: The new username set to the account 
	 * @param newPassword: The new password set to the account
	 * @throws InvalidInputException
	 */
	public static void updateAccount(String username, String newUsername, String newPassword) throws InvalidInputException  {

		try {
			if(newUsername == null || newUsername.length() == 0) {
				throw new InvalidInputException("The user name cannot be empty");
			}

			if(newPassword == null || newPassword.length() == 0) {
				throw new InvalidInputException("The password cannot be empty");
			}
			FlexiBook flexibook = FlexiBookApplication.getFlexibook();

			if (flexibook.getOwner() != null) {
				if(flexibook.getOwner().getUsername().equals(username)){  
					if(username.equals(newUsername)) {
						flexibook.getOwner().setPassword(newPassword);
						return;
					} else {
						throw new InvalidInputException("Changing username of owner is not allowed");
					}
				}
			}

			User currUser = findCustomer(username);
			if(currUser == null) {
				throw new InvalidInputException("User does not exist");
			}
			if (!currUser.setUsername(newUsername)) {
				throw new InvalidInputException("Username not available");
			}
			if(!currUser.setPassword(newPassword)) {
				throw new InvalidInputException("Unable to change password");
			}

		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	/**
	 * @author Mohammad Saeid Nafar
	 * Deletes a customer account given a username 
	 * @param username: The username of the account being deleted
	 * @param target: The username of the account being deleted
	 * @throws InvalidInputException
	 */
	public static void deleteCustomerAccount(String username, String target) throws InvalidInputException {

		try {
			FlexiBook flexibook = FlexiBookApplication.getFlexibook();
			if(!(username.equals(target)) || findOwner(username) != null || findOwner(target) != null) {
				throw new InvalidInputException("You do not have permission to delete this account");
			} else {

				Customer customerToDelete = findCustomer(username);
				List<Appointment> oldAppointments = customerToDelete.getAppointments();

				for(Appointment oldAppointment: oldAppointments) {
					flexibook.removeAppointment(oldAppointment);
					oldAppointment.delete();
				}

				flexibook.removeCustomer(customerToDelete);
				customerToDelete.delete();

			}
			FlexiBookApplication.setCurrentUser(null);


		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}


	}

	//Robert-------------------------------------------------------------------------------------------------

	/**
	 * 1. Define ServiceCombo
	 * @param ownerName is the username of the user trying to define a service combo 
	 * @param SCname is the name of the service combo to be defined
	 * @param mainService is the main Service of the service combo to be defined
	 * @param services is a string that contains the names of the services that should be included in the service combo that is to be defined
	 * @param mandatory is a string containing all the mandatory status of each service in correspondence with the service with the same "index" in the previous parameter (the services names) 
	 * @throws InvalidInputException in the case where a customer tried to define a service combo or an owner is trying to define a service combo with parameters that create conflicts with the system.
	 * defineServiceCombo is a method that basically is taking the previously mentioned parameters and creates a service combo using them.
	 * The first step is to check is the user defining the service combo is the owner. Then the method makes sure the parameters inserted are applicable to the system.
	 * The next step is to create combo items that are assigned to a newly created service combo and applying the parameter to that service combo by manipulating the input strings.
	 */
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

	/**
	 * 2. Update ServiceCombo
	 * @param ownerName is the username of the user trying to update a service combo.
	 * @param SCOldName is the name of the service combo to be updated.
	 * @param newSCName is the new name for the service combo after being updated.
	 * @param mainService is the main Service of the service combo to be after the update.
	 * @param services is a string that contains the names of the services that should be included in the service combo after being updated.
	 * @param mandatory is a string containing all the mandatory status of each service in correspondence with the service with the same "index" in the previous parameter (the services names) after the update of the service combo.
	 * @throws InvalidInputException in the case where a customer tried to update a service combo or an owner is trying to update a service combo with parameters that create conflicts with the system.
	 * updateServiceCombo is a method that must use the previously mentioned input to have an updated service combo. 
	 * The chosen service combo has parameters that are to be replaceed with the ones that are in the input. 
	 * In this method, the first step was to check that the user trying to update a service combo is the owner.
	 * The next step was to make the inserted parameters are valid and that they do not create any conflicts with the system.
	 * After that, to update the service combo, the method looks for the service combo with the old name, then deletes all its parameters then adds the new parameters to the service combo.
	 * By doing that the service combo has completely changed its parameters which means it has been updated.
	 */
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



	/**
	 * 3. Delete ServiceCombo
	 * @param ownerName is the username of the user trying to update a service combo.
	 * @param scDelete is the name of the service combo to be deleted. 
	 * @throws InvalidInputException is for the case where a customer tries to delete a service combo or when an owner tried to delete a service combo with future appointments.
	 * deleteServiceCombo is a method that deletes a certain service combo from the system. 
	 * The first step is to check that the owner is the user trying to do the deletion and that they are doing a deletion of a service combo without future appointments.
	 * The next step is to simply eliminate, delete the service combo from the system.
	 */
	public static void deleteServiceCombo(String ownerName, String scDelete) throws InvalidInputException {
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
			//			else {
			//				throw new InvalidInputException("There is no Service combo with this name");
			//			}
		}
	}


	//Fadi------------------------------------------------------------------------------------------

	/**
	 * author: Fadi Tawfik Beshay
	 * @param name business name stored as string
	 * @param address business address stored as string
	 * @param phoneNumber business phone number stored as string
	 * @param email business email stored as string
	 * @throws InvalidInputException
	 */
	public static void SetUpContactInfo(String name, String address, String phoneNumber, String email) throws InvalidInputException{	
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		if ((email.indexOf('@') == -1) || (email.indexOf('.') == -1) || (email.indexOf('.') < email.indexOf('@')) || (email.indexOf('@') == email.length()-1) || (email.indexOf('.') == email.length()-1)){
			throw new InvalidInputException("Invalid email");
		}
		try {
			FlexiBookApplication.getFlexibook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexibook()));
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * author: Fadi Tawfik Beshay
	 * @param Day enum Day of Week used to determine the corresponding day of the week
	 * @param temp2 Time object representing start time of the business hours
	 * @param temp3 Time object representing end time of the business hours
	 * @throws InvalidInputException
	 */
	public static void SetUpBusinessHours(DayOfWeek Day, Time temp2, Time temp3) throws InvalidInputException{

		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		if (temp2.after(temp3)) {
			throw new InvalidInputException("Start time must be before end time");
		}
		List<BusinessHour> test = FlexiBookApplication.getFlexibook().getBusiness().getBusinessHours();
		for (int i=0; i<test.size(); i++) {
			if (test.get(i).getDayOfWeek().equals(Day)) {
				if (temp3.before(test.get(i).getEndTime()) && (temp2.before(test.get(i).getEndTime()))) {
					throw new InvalidInputException("The business hours cannot overlap");
				}
				if (temp3.after(test.get(i).getEndTime()) && (temp2.before(test.get(i).getEndTime()))) {
					throw new InvalidInputException("The business hours cannot overlap");
				}
				if (temp3.before(test.get(i).getStartTime()) && (temp2.before(test.get(i).getStartTime()))) {
					throw new InvalidInputException("The business hours cannot overlap");
				}
			}
		}
		try {
			BusinessHour tester = new BusinessHour(Day, temp2, temp3, FlexiBookApplication.getFlexibook());
			FlexiBookApplication.getFlexibook().getBusiness().addBusinessHour(tester);
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}

	}

	/**
	 * author: Fadi Tawfik Beshay
	 * @return a string list containing the name, address, phone number and email of the business
	 * @throws InvalidInputException
	 */
	public static List<String> ViewBusinessInfo() throws InvalidInputException {
		List<String> BusinessInfo = null;
		try {
			BusinessInfo.add(FlexiBookApplication.getFlexibook().getBusiness().getName());
			BusinessInfo.add(FlexiBookApplication.getFlexibook().getBusiness().getAddress());
			BusinessInfo.add(FlexiBookApplication.getFlexibook().getBusiness().getPhoneNumber());
			BusinessInfo.add(FlexiBookApplication.getFlexibook().getBusiness().getEmail());	
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
		return BusinessInfo;
	}

	/**
	 * author: Fadi Tawfik Beshay
	 * @param type string indicating the new time slot is a holiday or vacation
	 * @param startDate Date object referring to start date of time slot
	 * @param startTime Time object referring to start time of time slot
	 * @param endDate Date object referring to end date of time slot
	 * @param endTime Time object referring to end time of time slot
	 * @throws InvalidInputException
	 */
	public static void AddaNewTimeSlot(String type, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException{
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to update business information");
		}
		if (endDate.before(startDate) || (startDate.equals(endDate) && endTime.before(startTime))) {
			throw new InvalidInputException("Start time must be before end time");
		}
		List<TimeSlot> n1 = FlexiBookApplication.getFlexibook().getBusiness().getHolidays();
		List<TimeSlot> n2 = FlexiBookApplication.getFlexibook().getBusiness().getVacation();
		for (int i=0; i<n1.size(); i++) {
			if ((n1.get(i).getStartDate().before(startDate) && n1.get(i).getEndDate().after(startDate)) || (n1.get(i).getStartDate().before(endDate) && n1.get(i).getEndDate().after(endDate))) {
				if(type.equals("holiday")) {
					throw new InvalidInputException("Holiday times cannot overlap");
				}
				else {
					throw new InvalidInputException("Holiday and vacation times cannot overlap");
				}
			}
		}
		for (int i=0; i<n2.size(); i++) {
			if ((n2.get(i).getStartDate().before(startDate) && n2.get(i).getEndDate().after(startDate)) || (n2.get(i).getStartDate().before(endDate) && n2.get(i).getEndDate().after(endDate))) {
				if(type.equals("holiday")) {
					throw new InvalidInputException("Holiday and vacation times cannot overlap");
				}
				else {
					throw new InvalidInputException("Vacation times cannot overlap");
				}
			}
		}
		if (startDate.before(SystemTime.getSysDate())) {
			if(type.equals("holiday")) {
				throw new InvalidInputException("Holiday cannot start in the past");
			}
			else {
				throw new InvalidInputException("Vacation cannot start in the past");
			}
		}
		TimeSlot temp = new TimeSlot(startDate, startTime, endDate, endTime, FlexiBookApplication.getFlexibook());
		if (type.equals("holiday")) {
			try {
				FlexiBookApplication.getFlexibook().getBusiness().addHoliday(temp);
			}
			catch(RuntimeException e){
				throw new InvalidInputException(e.getMessage());
			}
		}
		else {
			try {
				FlexiBookApplication.getFlexibook().getBusiness().addVacation(temp);
			}
			catch(RuntimeException e){
				throw new InvalidInputException(e.getMessage());
			}
		}
	}

	/**
	 * author: Fadi Tawfik Beshay
	 * @param name string referring to business name
	 * @param address string referring to business address
	 * @param phoneNumber string referring to business phone number
	 * @param email string referring to business email
	 * @throws InvalidInputException
	 */
	public static void UpdateBasicInfo(String name, String address, String phoneNumber, String email) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		if ((email.indexOf('@') == -1) || (email.indexOf('.') == -1) || (email.indexOf('.') < email.indexOf('@')) || (email.indexOf('@') == email.length()-1) || (email.indexOf('.') == email.length()-1)){
			throw new InvalidInputException("Invalid email");
		}
		try {
			FlexiBookApplication.getFlexibook().getBusiness().setName(name);
			FlexiBookApplication.getFlexibook().getBusiness().setAddress(address);
			FlexiBookApplication.getFlexibook().getBusiness().setPhoneNumber(phoneNumber);
			FlexiBookApplication.getFlexibook().getBusiness().setEmail(email);
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * author: Fadi Tawfik Beshay
	 * @param day1 DayOfWeek object from an enum of the days of the week referring to day of business hours to be updated
	 * @param time Time object referring to start time of the business hours to be changed
	 * @param day2 DayOfWeek object from an enum of the days of the week referring to new day of business hours
	 * @param startTime Time object referring to start time of the updated business hours
	 * @param endTime Time object referring to end time of the updated business hours
	 * @throws InvalidInputException
	 */
	public static void UpdateBusinessHours(DayOfWeek day1, Time time, DayOfWeek day2, Time startTime, Time endTime) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		if (startTime.after(endTime)) {
			throw new InvalidInputException("Start time must be before end time");
		}
		List<BusinessHour> test = FlexiBookApplication.getFlexibook().getBusiness().getBusinessHours();
		for(int i=0; i<test.size();i++) {
			if(!day1.equals(day2)) {
				if (test.get(i).getDayOfWeek().equals(day2)) {
					if(test.get(i).getEndTime().after(startTime)) {
						throw new InvalidInputException("The business hours cannot overlap");
					}

				}
			}
		}
		if(day1.equals(day2)) {
			for(int i=0; i<test.size();i++) {
				if (test.get(i).getDayOfWeek().equals(day1)) {
					if(test.get(i).getStartTime().equals(time)) {
						BusinessHour n1 = new BusinessHour(day1, time, test.get(i).getEndTime(), FlexiBookApplication.getFlexibook());
						FlexiBookApplication.getFlexibook().getBusiness().removeBusinessHour(n1);
						test = FlexiBookApplication.getFlexibook().getBusiness().getBusinessHours();
					}
				}
			}
		}
		try {
			BusinessHour n2 = new BusinessHour(day2, startTime, endTime, FlexiBookApplication.getFlexibook());
			FlexiBookApplication.getFlexibook().getBusiness().addBusinessHour(n2);
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * author: Fadi Tawfik Beshay
	 * @param day1 DayOfWeek object from an enum of the days of the week referring to day of business hours to be removed
	 * @param time Time object referring to start time of the business hours to be removed
	 * @throws InvalidInputException
	 */
	public static void RemoveBusinessHours(DayOfWeek day1, Time time) throws InvalidInputException{
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		List<BusinessHour> test = FlexiBookApplication.getFlexibook().getBusiness().getBusinessHours();
		for(int i=0; i<test.size();i++) {
			if (test.get(i).getDayOfWeek().equals(day1)) {
				if(test.get(i).getStartTime().equals(time)) {
					BusinessHour n1 = new BusinessHour(day1, time, test.get(i).getEndTime(), FlexiBookApplication.getFlexibook());
					FlexiBookApplication.getFlexibook().getBusiness().removeBusinessHour(n1);
				}
			}
		}
	}

	/**
	 * author: Fadi Tawfik Beshay
	 * @param type string indicating whether the time slot is a holiday or vacation
	 * @param date date object referring to start date of time slot to be updated
	 * @param time time object referring to start time of time slot to be updated
	 * @param startDate date object referring to start date of new time slot
	 * @param startTime time object referring to start time of new time slot
	 * @param endDate date object referring to end date of new time slot
	 * @param endTime time object referring to end time of new time slot
	 * @throws InvalidInputException
	 */
	public static void UpdateHolidayOrVacation(String type, Date date, Time time, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException{
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to update business information");
		}
		if (endDate.before(startDate) || (startDate.equals(endDate) && endTime.before(startTime))) {
			throw new InvalidInputException("Start time must be before end time");
		}
		if (startDate.before(SystemTime.getSysDate())) {
			if(type.equals("holiday")) {
				throw new InvalidInputException("Holiday cannot start in the past");
			}
			else {
				throw new InvalidInputException("Vacation cannot start in the past");
			}
		}
		List<TimeSlot> n1 = FlexiBookApplication.getFlexibook().getBusiness().getHolidays();
		List<TimeSlot> n2 = FlexiBookApplication.getFlexibook().getBusiness().getVacation();
		TimeSlot holidayToRemove = null;
		TimeSlot vacationToRemove = null;
		if(type.equals("holiday")) {
			for (int i=0; i<n1.size(); i++) {
				if (n1.get(i).getStartDate().equals(date) && n1.get(i).getStartTime().equals(time)) {
					holidayToRemove = n1.get(i);
					FlexiBookApplication.getFlexibook().getBusiness().removeHoliday(holidayToRemove);
					n1 = FlexiBookApplication.getFlexibook().getBusiness().getHolidays();
				}
			}
		}
		else {
			for (int i=0; i<n2.size(); i++) {
				if (n2.get(i).getStartDate().equals(date) && n2.get(i).getStartTime().equals(time)) {
					vacationToRemove = n2.get(i);
					FlexiBookApplication.getFlexibook().getBusiness().removeVacation(vacationToRemove);
					n2 = FlexiBookApplication.getFlexibook().getBusiness().getVacation();
				}
			}
		}
		for (int i=0; i<n1.size(); i++) {
			if ((n1.get(i).getStartDate().before(startDate) && n1.get(i).getEndDate().after(startDate)) || (n1.get(i).getStartDate().before(endDate) && n1.get(i).getEndDate().after(endDate))) {
				if(type.equals("holiday")) {
					FlexiBookApplication.getFlexibook().getBusiness().addHoliday(holidayToRemove);
					throw new InvalidInputException("Holiday times cannot overlap");
				}
				else {
					FlexiBookApplication.getFlexibook().getBusiness().addHoliday(holidayToRemove);
					throw new InvalidInputException("Holiday and vacation times cannot overlap");
				}
			}
		}
		for (int i=0; i<n2.size(); i++) {
			if ((n2.get(i).getStartDate().before(startDate) && n2.get(i).getEndDate().after(startDate)) || (n2.get(i).getStartDate().before(endDate) && n2.get(i).getEndDate().after(endDate))) {
				if(type.equals("holiday")) {
					FlexiBookApplication.getFlexibook().getBusiness().addVacation(vacationToRemove);
					throw new InvalidInputException("Holiday and vacation times cannot overlap");
				}
				else {
					FlexiBookApplication.getFlexibook().getBusiness().addVacation(vacationToRemove);
					throw new InvalidInputException("Vacation times cannot overlap");
				}
			}
		}
	}


	/**
	 * author: Fadi Tawfik Beshay
	 * @param type string indicating whether the time slot is a holiday or vacation
	 * @param startDate date object referring to start date of time slot to be removed
	 * @param startTime time object referring to start time of time slot to be removed
	 * @param endDate date object referring to end date of time slot to be removed
	 * @param endTime time object referring to end time of time slot to be removed
	 * @throws InvalidInputException
	 */
	public static void RemoveTimeSlot(String type, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException{
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		TimeSlot remove = new TimeSlot(startDate, startTime, endDate, endTime, FlexiBookApplication.getFlexibook());
		try {
			if(type.equals("holiday")) {
				FlexiBookApplication.getFlexibook().getBusiness().removeHoliday(remove);
			}
			else {
				FlexiBookApplication.getFlexibook().getBusiness().removeVacation(remove);
			}
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	
	//Tamara----------------------------------------------------------------------------------------


	//Helper methods-----------------------------------------------------------------------------------


	//Eric

	/**
	 * Helper method to find a specific user
	 * @author Eric Chehata
	 * @param username: username of user sought
	 * @return User sought if found, null otherwise
	 */
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

	/**
	 * Helper method to check if password entered matches the user's password
	 * @author Eric Chehata
	 * @param user
	 * @param password: password entered
	 * @return true if password entered matches the user's password, false otherwise
	 */
	private static boolean checkPassword(User user, String password) {
		if (user.getPassword().equals(password)) return true;

		else return false;
	}

	/**
	 * Helper method to get all available time slots
	 * @author Eric Chehata
	 * @param date
	 * @return list of all available time slots in the FlexiBook system
	 */
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
	/**
	 * Helper method to get all downtime time slots
	 * @author Eric Chehata
	 * @param date
	 * @return list of all downtime time slots in the FlexiBook system
	 */
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

	/**
	 * Helper method to get all unavailable time slots
	 * @author Eric Chehata
	 * @param date
	 * @return list of all unavailable time slots in the FlexiBook system
	 */
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

	/**
	 * Helper method to check if there is overlap between two time slots.
	 * @author Eric Chehata
	 * @param TS1: first Time slot
	 * @param TS2: second Time slot
	 * @return true if there's overlap between the two time slots.
	 */
	private static boolean isOverlap(TimeSlot TS1, TimeSlot TS2) {
		LocalTime S1 = TS1.getStartTime().toLocalTime();
		LocalTime S2 = TS2.getStartTime().toLocalTime();
		LocalTime E1 = TS1.getEndTime().toLocalTime();
		LocalTime E2 = TS2.getEndTime().toLocalTime();

		return S1.isBefore(E2) && S2.isBefore(E1);
	}

	/**
	 * Helper method to get the day of the week corresponding to date input
	 * @author Eric Chehata
	 * @param date: date for which we want the corresponding day of the week.
	 * @param locale: 
	 * @return the day of the week corresponding to date input
	 */
	private static String getDayString(Date date, Locale locale) {
		DateFormat formatter = new SimpleDateFormat("EEEE", locale);
		return formatter.format(date);
	}


	/**
	 * Helper method to get the TO available time slots.
	 * @author Eric Chehata
	 * @param date: date for which we want the TO available time slots.
	 * @return list of the the TO available time slots.
	 */
	private static List<TOTimeSlot> getAvailableTOTimeSlots(Date date){
		List<TOTimeSlot> available =new ArrayList<TOTimeSlot>();

		for(TimeSlot TS : getAvailableTimeSlots(date)) {
			TOTimeSlot TOtimeSlot = new TOTimeSlot(TS.getStartDate(), TS.getStartTime(), TS.getEndDate(),TS.getEndTime());
			available.add(TOtimeSlot);
		}

		return available;
	}
	/**
	 * Helper method to get the TO unavailable time slots.
	 * @author Eric Chehata
	 * @param date: date for which we want the TO unavailable time slots.
	 * @return list of the the TO available time slots.
	 */
	private static List<TOTimeSlot> getUnavailableTOTimeSlots(Date date){
		List<TOTimeSlot> unavailable =new ArrayList<TOTimeSlot>();

		for(TimeSlot TS : getUnavailableTimeSlots(date)) {
			TOTimeSlot TOtimeSlot = new TOTimeSlot(TS.getStartDate(), TS.getStartTime(), TS.getEndDate(),TS.getEndTime());
			unavailable.add(TOtimeSlot);
		}

		return unavailable;
	}


	/**
	 * Helper method to convert a String corresponding to a date into a Date.
	 * @author Eric Chehata
	 * @param d: String of the date we want to convert
	 * @return the Date 
	 */
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

	//Marc--------------------------------------------------------------------------------------------------------

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

	//Saeid--------------------------------------------------------------------------------------------------
	/**
	 * @author Mohammad Saeid Nafar
	 * @param username
	 * @return Owner
	 */
	private static Owner findOwner(String username) {
		Owner foundOwner = null;

		if (FlexiBookApplication.getFlexibook().getOwner() != null) {
			if((FlexiBookApplication.getFlexibook().getOwner().getUsername()).equals(username)){  
				Owner owner = FlexiBookApplication.getFlexibook().getOwner();
				foundOwner = owner;
				return foundOwner;
			}
		}
		return foundOwner;
	}

	/**
	 * @author Mohammad Saeid Nafar
	 * @param username
	 * @return Customer
	 */
	private static Customer findCustomer(String username) {

		Customer foundCustomer = null;
		for (Customer customer : FlexiBookApplication.getFlexibook().getCustomers()) {
			if (customer.getUsername().equals(username)) {
				foundCustomer = customer;
				return foundCustomer;
			}
		}
		return foundCustomer;

	}


	//Robert-----------------------------------------------------------------------------------------------------

	private static ServiceCombo findServiceCombo(String serviceCombo) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof ServiceCombo) {
				if (aService.getName().equals(serviceCombo) ) return (ServiceCombo) aService;
			}
		}
		return null;
	}

	private static List<ServiceCombo> getServiceCombos(){
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		List<ServiceCombo> combos = new ArrayList<ServiceCombo>(); 

		//for (BookableService combo : flexibook.getBookableServices()) {
		for(int i = 0; i<flexibook.getBookableServices().size(); i++) {
			BookableService combo = flexibook.getBookableServices().get(i);
			if (combo instanceof ServiceCombo) {
				ServiceCombo cmb = (ServiceCombo) combo;
				combos.add(cmb);
			}
		}
		return combos;

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

}





