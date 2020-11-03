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
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.SystemTime;
import ca.mcgill.ecse.flexibook.model.BookableService;
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


	/**
	 * Add Service
	 * addService is a method that adds a certain service to the system. 
	 * This method just checks that the owner is the user trying to do the addition of a service and 
	 * that they are doing a addition of a service with correct parameters (ensured the serviceSpecificationAdd)
	 * helper method. Also it checks that the owner adds a service that does not already exist.
	 * 
	 * @author Marc Saber
	 * @param serviceName  is the name of the service to add.
	 * @param duration is the duration of the service to add.
	 * @param downtimeDuration is the downtime duration of the service to add.
	 * @param downtimeStart is the start time of the downtime of the service to add.
	 * @param user is the name of FlexiBookApplication user (could be a customer or an owner).
	 * @throws InvalidInputException when a customer tries to add a service, or when an owner tries to
	 * add a service that already exists, or even when he tries to add a service that does not exist,
	 * but that has invalid parameters creating conflict with the system in the 
	 *  (serviceSpecificationAdd) helper method.
	 * 
	 */
	
	
	public static void addService(String serviceName, int duration,int downtimeDuration, 
			int downtimeStart,String user)throws InvalidInputException{
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
	/**
	 * Delete Service
	 * deleteService is a method that deletes a certain service from the system. 
	 * This method just checks that the owner is the user trying to do the deletion of a service and 
	 * that they are doing a deletion of a service without future appointments.
	 * It also checks if this specific service is contained in a service combo. If it is, and is a
	 * mandatory service, it deletes the service combo too . But if it's not mandatory, 
	 * it just deletes the service.
	 * Finally, if the service is not including in future appointments, and not in service combos
	 * it just deletes the service.
	 * @author Marc Saber
	 * @param service is the name of the service to be deleted
	 * @param username is the name of FlexiBookApplication user (could be a customer or an owner).
	 * @throws InvalidInputException when a customer tries to delete a service,
	 * or when an owner tries to delete a service contained in future appointments.
	 */

	public static void deleteService(String service,String username) throws InvalidInputException {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		try {
			if (username.equals("owner")) {
				Service serviceToDelete = findService(service);
				
				for(int i = 0; i<flexibook.getAppointments().size(); i++) {
					Appointment app = flexibook.getAppointments().get(i);
					if (app.getBookableService().getName().equals(service)) {

						if(app.getTimeSlot().getStartDate().after(SystemTime.getSysDate())) {
							throw new InvalidInputException("The service contains future appointments");
						}
						else if(app.getTimeSlot().getStartDate().equals(SystemTime.getSysDate())) {
							flexibook.removeAppointment(app);
							app.delete();

						}
					}
				}
				List<ServiceCombo> a = getServiceCombos();
				for (int i = 0; i< a.size(); i++) {
					ServiceCombo combo = a.get(i);//getServiceCombos().get(1);
					for(int j =0; j<combo.getServices().size(); j++) {
						ComboItem item = combo.getServices().get(j);
						if (item.getService() == findService(service)) {
							if(item.equals(combo.getMainService())) {//getMandatory() == true
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

	/** 
	 * Update Service         
	 * updateService is a method where an owner can update a service.
	 * This specific service to be updated has parameters that are replaced with the ones that are set.
	 * First thing to do was checking wether the user is a customer or an owner, in order to see
	 * if they are allowed to update the service. If they are an owner, the service could
	 * be updated, if not the customer is not authorized to perform this operation.
	 * Then checking the inserted parameters' validity, by using the helper method 
	 * serviceSpecificationUpdate
	 * After that, to update the service, the method looks for the service with the old name 
	 * (using the findService helper method),and sets its new parameters.
	 * By doing that the service has completely changed its parameters which means it has been updated.
	 * @author Marc Saber
	 * @param service is the name of the service to be found.
	 * @param duration is the updated duration of the service.
	 * @param downtimeDuration is the updated downtime time of the service.
	 * @param downtimeStart is the updated start time of the downtime of a service.
	 * @param user is the FlexiBookApplication user (could be a customer or an owner).
	 * @param serviceName is the new name of the service to be updated.
	 * @throws InvalidInputException in the case where a customer tried to update a service 
	 *         or when an owner is doing so, but with parameters creating conflict with the system in the 
	 *         (serviceSpecificationUpdate) helper method.
	 */

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
	 * Registers a customer account given a username and password
	 * @author Mohammad Saeid Nafar
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

				flexibook.addCustomer(username, password,0);

		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * Updates a user account by setting a new username and and a new passowrd to the account
	 * @author Mohammad Saeid Nafar
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
	 * Deletes a customer account given a username 
	 * @author Mohammad Saeid Nafar
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
	 * defineServiceCombo is a method that basically is taking the previously mentioned parameters and creates a service combo using them.
	 * The first step is to check is the user defining the service combo is the owner. Then the method makes sure the parameters inserted are applicable to the system.
	 * The next step is to create combo items that are assigned to a newly created service combo and applying the parameter to that service combo by manipulating the input strings.
	 * @author Robert Aprahamian
	 * @param ownerName is the username of the user trying to define a service combo 
	 * @param SCname is the name of the service combo to be defined
	 * @param mainService is the main Service of the service combo to be defined
	 * @param services is a string that contains the names of the services that should be included in the service combo that is to be defined
	 * @param mandatory is a string containing all the mandatory status of each service in correspondence with the service with the same "index" in the previous parameter (the services names) 
	 * @throws InvalidInputException in the case where a customer tried to define a service combo or an owner is trying to define a service combo with parameters that create conflicts with the system.
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
	 * updateServiceCombo is a method that must use the previously mentioned input to have an updated service combo. 
	 * The chosen service combo has parameters that are to be replaceed with the ones that are in the input. 
	 * In this method, the first step was to check that the user trying to update a service combo is the owner.
	 * The next step was to make the inserted parameters are valid and that they do not create any conflicts with the system.
	 * After that, to update the service combo, the method looks for the service combo with the old name, then deletes all its parameters then adds the new parameters to the service combo.
	 * By doing that the service combo has completely changed its parameters which means it has been updated.
	 * @author Robert Aprahamian
	 * @param ownerName is the username of the user trying to update a service combo.
	 * @param SCOldName is the name of the service combo to be updated.
	 * @param newSCName is the new name for the service combo after being updated.
	 * @param mainService is the main Service of the service combo to be after the update.
	 * @param services is a string that contains the names of the services that should be included in the service combo after being updated.
	 * @param mandatory is a string containing all the mandatory status of each service in correspondence with the service with the same "index" in the previous parameter (the services names) after the update of the service combo.
	 * @throws InvalidInputException in the case where a customer tried to update a service combo or an owner is trying to update a service combo with parameters that create conflicts with the system.
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
	 * deleteServiceCombo is a method that deletes a certain service combo from the system. 
	 * The first step is to check that the owner is the user trying to do the deletion and that they are doing a deletion of a service combo without future appointments.
	 * The next step is to simply eliminate, delete the service combo from the system.
	 * @author Robert Aprahamian
	 * @param ownerName is the username of the user trying to update a service combo.
	 * @param scDelete is the name of the service combo to be deleted. 
	 * @throws InvalidInputException is for the case where a customer tries to delete a service combo or when an owner tried to delete a service combo with future appointments.
	 * 
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

		}
	}


	//Fadi------------------------------------------------------------------------------------------

	/**
	 * This method is used to set up the contact information for a new business in the flexibook application.
	 * @author Fadi Tawfik Beshay
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
	 * This method is used to set up business hours for a business.
	 * @author Fadi Tawfik Beshay
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
	 * This method is used by a user to view the business information.
	 * @author Fadi Tawfik Beshay
	 * @return a string list containing the name, address, phone number and email of the business
	 * @throws InvalidInputException
	 */
	public static List<String> ViewBusinessInfo() throws InvalidInputException {
		List<String> BusinessInfo = new ArrayList<String>();
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
	 * This method adds a new time slot to the flexibook application.
	 * @author Fadi Tawfik Beshay
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
	 * This method is used to update the basic business information.
	 * @author Fadi Tawfik Beshay
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
	 * This method is used to update an existing business hour.
	 * @author Fadi Tawfik Beshay
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
	 * This method is used to remove an existing business hour.
	 * @author Fadi Tawfik Beshay
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
	 * This method is used to update an existing Holiday or vacation time slot.
	 * @author Fadi Tawfik Beshay
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
	 * This method is used to delete an existing time slot.
	 * @author Fadi Tawfik Beshay
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
	/**
	 * The makeAppointment method takes for input the username of the customer, the name of the service desired, 
	 * the optional services if the service desired is a service combo and the desired date and time.
	 * It creates an appointment for the customer in the flexibook application with the inputs. If the 
	 * appointment is unsuccessful due to various reasons, an error is thrown and the system reports
	 * the reason it failed.
	 * @author Tamara Zard Aboujaoudeh
	 * @param customerString is the username of the user
	 * @param serviceName is the name of the service (and the name of the appointment)
	 * @param optionalServicesString is the optional services (it is null if the user desires a service and not a service combo)
	 * @param startDateString is the desired date of the appointment
	 * @param startTimeString is the desired time of the appointment
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
	 * @author Tamara Zard Aboujaoudeh
	 * @param user is a username 
	 * @param customerString is the customer's username
	 * @param appointmentName is the service name and the appointment name
	 * @param oldDateString is the old date of the appointment before the update
	 * @param oldStartTimeString is the old date of the appointment before the update
	 * @param newDateString is the new desired date of the appointment
	 * @param newStartTimeString is the new desired time of the appointment
	 * @param action is the action desired (remove or add)
	 * @param itemString is the item to add or remove
	 * @throws InvalidInputException
	 */
//	public static void updateAppointment(String user, String customerString, String appointmentName, String oldDateString, String oldStartTimeString, String newDateString, String newStartTimeString, String action, String itemString ) throws InvalidInputException {
//		FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
//		
//		oldStartTimeString =oldStartTimeString +":00";
//		newStartTimeString = newStartTimeString+":00";
//		
//		Time newStartTime = toTime(newStartTimeString);
//		Date newStartDate = toDate(newDateString);
//		Time oldStartTime = toTime(oldStartTimeString);
//		Date oldStartDate = toDate(oldDateString);
//		Date oldEndDate = oldStartDate;
//		
//		Date newEndDate = newStartDate;
//		
//		Customer customer= (Customer) findUser(customerString);
//		
//		BookableService service = findBookableService(appointmentName);
//		
//		if(user.equals("owner")) {
//			throw new InvalidInputException("Error: An owner cannot update a customer's appointment");
//		}
//		if(!user.equals(customerString)) {
//			throw new InvalidInputException("Error: A customer can only update their own appointments");
//		}
//		
//	if(!newStartTimeString.equals(oldStartTimeString) || !newDateString.equals(oldDateString)) {	
//		
//		if(newStartDate.before(SystemTime.getSysDate())) {
//			throw new InvalidInputException("unsuccessful");
//		}
//		
//		Locale locale = new Locale("en");
//		String dayOfTheWeek = getDayString(newStartDate, locale);
//			if (dayOfTheWeek.equals("Saturday") || dayOfTheWeek.equals("Sunday")){
//				throw new InvalidInputException("unsuccessful");
//				
//			}
//		Appointment app= findAppointment(customerString, appointmentName, oldDateString, oldStartTimeString);
//		
//		LocalTime localOldEndTime = app.getTimeSlot().getEndTime().toLocalTime();
//		LocalTime localOldStartTime = oldStartTime.toLocalTime();
//		LocalTime localNewStartTime = newStartTime.toLocalTime();
//		
//		Duration dur = Duration.between(localOldStartTime, localOldEndTime);
//		
//		
//		LocalTime localNewEndTime = localNewStartTime.plusMinutes((dur.getSeconds()/60));
//		Time newEndTime = Time.valueOf(localNewEndTime);
//		
//		TimeSlot newTimeSlot = new TimeSlot(newStartDate, newStartTime, newEndDate, newEndTime, flexiBook);
//		TimeSlot oldTimeSlot = app.getTimeSlot();
//		Time oldEndTime = oldTimeSlot.getEndTime();
//		TimeSlot temp = new TimeSlot(newStartDate, oldEndTime, newEndDate, newEndTime, flexiBook);
//
//
//		for(int k = 0; k<flexiBook.getBusiness().getHolidays().size();k++) {
//			TimeSlot holiday = flexiBook.getBusiness().getHolidays().get(k);
//			for(LocalDate localDate = holiday.getStartDate().toLocalDate(); 
//					localDate.isBefore(holiday.getEndDate().toLocalDate().plusDays(1)); 
//					localDate = localDate.plusDays(1))
//			{
//				Date d  = Date.valueOf(localDate);
//				if(d.compareTo(newTimeSlot.getStartDate())==0) {
//					if(isOverlap(holiday, newTimeSlot)) {
//						app.setTimeSlot(oldTimeSlot);
//						throw new InvalidInputException("unsuccessful");
//					}
//				
//				}
//			}
//		}
//		
//		boolean successful = false;
//		for(int i=0; i< getUnavailableTimeSlots(newStartDate).size(); i++) {
//			if(isOverlap(temp, getUnavailableTimeSlots(newStartDate).get(i))) {
//				for(int j=0; j<flexiBook.getAppointments().size(); j++) {
//					Appointment a = flexiBook.getAppointments().get(j);
//					if(a.getTimeSlot().getStartDate().compareTo(newStartDate)==0) {
//						for(int k=0; k<getDowntimeTimeSlots(a).size(); k++) {
//							TimeSlot downtime = getDowntimeTimeSlots(a).get(k);
//							if(s2_isWithin_s1(getDowntimeTimeSlots(a).get(k), newTimeSlot)) {
//								app.setTimeSlot(newTimeSlot);
//								successful = true;
//							}
//						}
//					}
//				}
//			
//			}
//		}
//		
//		
//		for (int i=0; i<getAvailableTimeSlots(newStartDate).size(); i++) {
//			if(s2_isWithin_s1(getAvailableTimeSlots(newStartDate).get(i), temp)) {
//				
//				app.setTimeSlot(newTimeSlot);
//				successful = true;
//				
//				break;
//			}
//
//		}
//		if(successful==false) throw  new InvalidInputException("unsuccessful");
//		
//	}
//	if(action!=null && itemString!=null) {
//		Appointment app = findAppointment(customerString, appointmentName, oldDateString, oldStartTimeString);
//		List <ComboItem> list = app.getChosenItems();
//		List <ComboItem> list2 = new ArrayList<>(list);
//		
//		TimeSlot newTimeSlot = null;
//		
//		ServiceCombo serviceCombo = findServiceCombo(appointmentName);
//		int removedDuration=0;
//		int addedDuration =0;
//		
//		if(action.equals("remove")) {
//			if(!(serviceCombo.getMainService()==null) && serviceCombo.getMainService().getService().getName().equals(itemString)) {
//				throw new InvalidInputException("unsuccessful");
//			}
//			
//			for (int i=0; i< list.size(); i++) {
//
//				if(list.get(i).getService().getName().equals(itemString)) {
//					if(list.get(i).isMandatory()) {
//						throw new InvalidInputException("unsuccessful");
//					}
//					
//					
//					removedDuration = list.get(i).getService().getDuration();
//					list2.remove(list.get(i));
//					
//					break;
//				}
//			}
//			TimeSlot oldTimeSlot = app.getTimeSlot();
//			Duration dur = Duration.between(oldTimeSlot.getStartTime().toLocalTime(), oldTimeSlot.getEndTime().toLocalTime());
//			Duration newDur = dur.minusMinutes(removedDuration);
//			
//			
//			LocalTime localNewEndTime = oldStartTime.toLocalTime().plusMinutes(newDur.getSeconds()/60);
//			Time newEndTime = Time.valueOf(localNewEndTime);
//			newTimeSlot = new TimeSlot(oldStartDate, oldStartTime, oldEndDate, newEndTime, flexiBook);
//			
//			
//		}
//		else if(action.equals("add")) {
//			for(int i=0; i<serviceCombo.getServices().size(); i++) {
//				if(serviceCombo.getServices().get(i).getService().getName().equals(itemString)) {
//					
//					addedDuration = serviceCombo.getServices().get(i).getService().getDuration();
//					list2.add(serviceCombo.getServices().get(i));
//				}
//			}
//			TimeSlot oldTimeSlot = app.getTimeSlot();
//			Duration dur = Duration.between(oldTimeSlot.getStartTime().toLocalTime(), oldTimeSlot.getEndTime().toLocalTime());
//			Duration newDur = dur.plusMinutes(addedDuration);
//			
//			
//			LocalTime localNewEndTime = oldStartTime.toLocalTime().plusMinutes(newDur.getSeconds()/60);
//			Time newEndTime = Time.valueOf(localNewEndTime);
//			newTimeSlot = new TimeSlot(oldStartDate, oldStartTime, oldEndDate, newEndTime, flexiBook);
//			
//			
//		}
//		flexiBook.removeAppointment(app);
//		
//		for(int i=0; i<flexiBook.getBusiness().getHolidays().size(); i++) {
//			TimeSlot aTimeSlot = flexiBook.getBusiness().getHolidays().get(i);
//			if(isOverlap(aTimeSlot, newTimeSlot)) {
//				throw new InvalidInputException("unsuccessful");
//			}
//		}
//
//		
//		for (int i=0; i<getAvailableTimeSlots(newStartDate).size(); i++) {
//			if(s2_isWithin_s1(getAvailableTimeSlots(newStartDate).get(i), newTimeSlot)) {
//				Appointment app2 = new Appointment(customer, serviceCombo, newTimeSlot, flexiBook);
//				
//				for(int j=0; j<list2.size(); j++){
//					app2.addChosenItem(list2.get(j));
//				}
//				break;
//			}
//			else {
//				flexiBook.addAppointment(app);
//				throw new InvalidInputException("unsuccessful");
//			}
//		}			
//	}
//		
//	}

	public static void updateAppointment(String user, String customerString, String appointmentName, String oldDateString, String oldStartTimeString, String newDateString, String newStartTimeString, String action, String itemString, boolean isChange, String newService ) throws InvalidInputException {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();

		Time newStartTime;
		Date newStartDate;
		Time oldStartTime;
		Date oldStartDate;
		Date oldEndDate;
		Date newEndDate;
		try {
			newStartTime = toTime(newStartTimeString);
			newStartDate = toDate(newDateString);
			oldStartTime = toTime(oldStartTimeString);
			oldStartDate = toDate(oldDateString);
			oldEndDate = oldStartDate;
			newEndDate = newStartDate;
		}catch(java.time.DateTimeException e) {
			throw new InvalidInputException("Invalid date and Time");
		}

			if(user.equals("owner")) {
				throw new InvalidInputException("Error: An owner cannot update a customer's appointment");
			}
			if(!user.equals(customerString)) {
				throw new InvalidInputException("Error: A customer can only update their own appointments");
			}	

			Appointment app= findAppointment(customerString, appointmentName, oldDateString, oldStartTimeString);
			BookableService s = findBookableService(newService);
			LocalTime localStart = app.getTimeSlot().getStartTime().toLocalTime();
			LocalTime localEnd = app.getTimeSlot().getEndTime().toLocalTime();
			Duration dur = Duration.between(localStart, localEnd);
			
			LocalTime newLocalEnd = newStartTime.toLocalTime().plusMinutes(dur.toMinutes());
			Time newEndTime = Time.valueOf(newLocalEnd);
			Boolean isAdd;

			if (s!=null) {
				if (isChange) {
					if (s instanceof Service) {
						Service newS = (Service) s;
						LocalTime end = newStartTime.toLocalTime().plusMinutes(newS.getDuration());
						newEndTime = Time.valueOf(end);
					}else {
						ServiceCombo combo = (ServiceCombo) s;
						int duration = 0;
						for (ComboItem item : combo.getServices()) {
							duration += item.getService().getDuration();
						}
						LocalTime end = newStartTime.toLocalTime().plusMinutes(duration);
						newEndTime = Time.valueOf(end);
					}
				}
			}else if (s==null && isChange){
				throw new InvalidInputException("Service not found");
			}

			if(action == null) {
				isAdd = null;
			}
			else {
				if (action.equals("add")) isAdd = Boolean.TRUE;
				else if(action.equals("remove")) isAdd = Boolean.FALSE;
				else throw new InvalidInputException("Action not found");
			}
			TimeSlot TS = new TimeSlot(newStartDate, newStartTime, newEndDate, newEndTime, flexibook);

			if (isAdd == null && itemString == null) {
				try {
				app.updateAppointment(TS, isChange, s, isAdd, null);
				}catch(RuntimeException e) {
					throw new InvalidInputException(e.getMessage());
				}
			}else {
				ServiceCombo combo = (ServiceCombo) app.getBookableService();
				Service optional = findService(itemString);
				if(optional != null) {
					ComboItem opService = new ComboItem(false,optional, combo);
					app.updateAppointment(TS, isChange, s, isAdd, opService);
				}
				else throw new InvalidInputException("Optional service not found");
			}
		

	}
	
	/**
	 * The CancelAppointment method takes for input the username of the customer, his appointment name
	 * and the date and start time of the appointment.
	 * This method cancels an appointment that is already in the system.
	 * If the cancelation is unsuccessful for various reasons, the system will throw an error.
	 * 
	 * The first two input parameters are always the same unless it is a random user or the owner that is
	 * trying to cancel the appointment of a customer. The system then throws an error saying it is
	 * impossible.
	 * @author Tamara Zard Aboujaoudeh
	 * @param user is a username
	 * @param username is the customer's username
	 * @param serviceName is the service name and the appointment name to be cancelled 
	 * @param date is the date of the appointment before cancellation
	 * @param startTimeString is the time of the appointment before cancellation
	 * @throws InvalidInputException
	 */
	public static void cancelAppointment(String user,String username, String serviceName, String date, String startTimeString) throws InvalidInputException {

		FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
		Appointment anAppointment = findAppointment(username, serviceName, date, startTimeString);
		Date startDate = toDate(date);
		Customer c = findCustomer(username);
		
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
				anAppointment.cancelAppointment(c);
			}
					
				
		}catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
			
	}
//	/**
//	 * The CancelAppointment method takes for input the username of the customer, his appointment name
//	 * and the date and start time of the appointment.
//	 * This method cancels an appointment that is already in the system.
//	 * If the cancelation is unsuccessful for various reasons, the system will throw an error.
//	 * 
//	 * The first two input parameters are always the same unless it is a random user or the owner that is
//	 * trying to cancel the appointment of a customer. The system then throws an error saying it is
//	 * impossible.
//	 * @author Tamara Zard Aboujaoudeh
//	 * @param user is a username
//	 * @param username is the customer's username
//	 * @param serviceName is the service name and the appointment name to be cancelled 
//	 * @param date is the date of the appointment before cancellation
//	 * @param startTimeString is the time of the appointment before cancellation
//	 * @throws InvalidInputException
//	 */
//	public static void cancelAppointment(String user,String username, String serviceName, String date, String startTimeString) throws InvalidInputException {
//
//		FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
//		Appointment anAppointment = findAppointment(username, serviceName, date, startTimeString);
//		Date startDate = toDate(date);
//		
//		
//		try {
//			if(user.equals("owner")) {
//				throw new InvalidInputException("An owner cannot cancel an appointment");
//				}
//			else if(!user.equals(username)) {
//			 throw new InvalidInputException("A customer can only cancel their own appointments");
//			}
//			else if(startDate.equals(SystemTime.getSysDate())){
//				throw new InvalidInputException("Cannot cancel an appointment on the appointment date");
//			}
//			
//			else {
//				flexiBook.removeAppointment(anAppointment);
//			}
//					
//				
//		}catch(RuntimeException e) {
//			throw new InvalidInputException(e.getMessage());
//		}
//	}
	
//	private static Appointment findAppointment(String username, String appName, String dateString, String startTimeString) {
	public static void startAppointment(String username, String appName, String dateString, String startTimeString) {
		Appointment a = findAppointment(username ,appName, dateString, startTimeString);
		a.startAppointment();
	}
	
	public static void endAppointment(String username, String appName, String dateString, String startTimeString) {
		Appointment a = findAppointment(username ,appName, dateString, startTimeString);
		a.endAppointment();
	}
	
	public static void registerNoShow(String customerName, String appointment, String dateAndTimeAsOne) {
		//String[] dateAndTime = dateAndTimeAsOne.split("+");
		String date = dateAndTimeAsOne.substring(0, 10);
		String time = dateAndTimeAsOne.substring(11, 16);
		Appointment a = findAppointment(customerName ,appointment, date, time);
		//Customer c = findCustomer(customerName);
		//TimeSlot ts = findTimeSlotOfApp(a.getBookableService().getName(), null,dateAndTime[0], dateAndTime[1]);
		a.registerNoShow();
		
////		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())) {
////			throw new InvalidInputException("You are not authorized to perform this operation");
////		}
//		Customer c = findCustomer(customerName);
//		String[] dateAndTime = dateAndTimeAsOne.split("+");
//		Appointment a = findAppointment(c.getUsername() ,appointment, dateAndTime[0], dateAndTime[1]);
//		if (!(a.getSm().equals(Appointment.Sm.InProgress)) && toTime(dateAndTime[1]).before(SystemTime.getSysTime())) {
//			int noShowCurrent = c.getNoShow();
//			c.setNoShow(noShowCurrent+1);
//		}
	}
	
	//Query methods---------------------------------------------------------------------------------------
	
	/**
	 * @author Eric, Marc, Tamara, Robert, Mohammad Saeid, Fadi
	 * @return list of transfer objects of services.
	 */
	public static List<TOService> getTOServices(){
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		List<TOService> services = new ArrayList<TOService>();
		
		for (int i=0; i<flexibook.getBookableServices().size();i++) {
			BookableService S = flexibook.getBookableServices().get(i);
			if (S instanceof Service) {
				Service service = (Service) S;
				TOService toService = new TOService(service.getName(), service.getDuration(), service.getDowntimeDuration(), service.getDowntimeStart());
				services.add(toService);
			}
			
		}
			
		return services;
	}
	
	/**
	 * @author Eric, Marc, Tamara, Robert, Mohammad Saeid, Fadi
	 * @return list of transfer objects of service combos.
	 */
	public static List<TOServiceCombo> getTOServiceCombos(){
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		List<TOServiceCombo> services = new ArrayList<TOServiceCombo>();
		
		for (int i=0; i<flexibook.getBookableServices().size();i++) {
			BookableService S = flexibook.getBookableServices().get(i);
			if (S instanceof ServiceCombo) {
				ServiceCombo service = (ServiceCombo) S;
				TOServiceCombo s = new TOServiceCombo(service.getName());
				services.add(s);
			}
			
		}
			
		return services;
	}
	

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
	private static Date toDate(String d) {
		String[] dArray = d.split("-");
		int[] intArray = new int[3];
		intArray[0] = Integer.parseInt(dArray[0]);
		intArray[1] = Integer.parseInt(dArray[1]);
		intArray[2] = Integer.parseInt(dArray[2]);
		LocalDate localDate = null;

		localDate = LocalDate.of(intArray[0], intArray[1], intArray[2]);

		return Date.valueOf(localDate);

	}
	
	
	/**
	 * Helper method to convert a String corresponding to a time into a Time.
	 * @author Eric Chehata
	 * @param t: String of the time we want to convert
	 * @return
	 */
	private static Time toTime(String t) {
		String[] tArray = t.split(":");
		int[] intArray = new int[2];
		intArray[0] = Integer.parseInt(tArray[0]);
		intArray[1] = Integer.parseInt(tArray[1]);
		LocalTime localTime = LocalTime.of(intArray[0], intArray[1]);
		return Time.valueOf(localTime);

	}


	//Marc--------------------------------------------------------------------------------------------------------

	
	/**
	 * Helper method that throws valid InvalidInputExceptions, when parameters of a service
	 * to be added are invalid and raise errors.
	 * @author Marc Saber
	 * @param  serviceName is the name of the added service.
     * @param duration is the duration of the added service.
     * @param downtimeDuration is downtime time of the added service.
     * @param downtimeStart is the downtime start time of the added service.
     * @throws InvalidInputException when invalid parameters are set.	 
	 */
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

	/**
	 * Helper method that throws valid InvalidInputExceptions, when parameters of a service
	 * to be updated are invalid and raise errors.
	 * @author Marc Saber
	 * @param  service is the corresponding updated service.
	 * @param  serviceName is the name of the service to be found.
     * @param duration is the updated duration of the service.
     * @param downtimeDuration is the updated downtime time of the service.
     * @param downtimeStart is the updated start time of the downtime of a service.
     * @throws InvalidInputException when invalid parameters are set.	
	 */
		private static void serviceSpecificationUpdate (String service, String serviceName,
				int duration, int downtimeDuration, int downtimeStart)
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
		
		/**
		 * Helper method to get the service combos
		 * @author Marc Saber
		 * @return the list of service combos
		 */
		private static List<ServiceCombo> getServiceCombos(){
			FlexiBook flexibook = FlexiBookApplication.getFlexibook();
			List<ServiceCombo> combos = new ArrayList<ServiceCombo>(); 
			for(int i = 0; i<flexibook.getBookableServices().size(); i++) {
				BookableService combo = flexibook.getBookableServices().get(i);
				if (combo instanceof ServiceCombo) {
					ServiceCombo cmb = (ServiceCombo) combo;
					combos.add(cmb);
				}
			}
			return combos;
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
	/**
	 * findSerivceCombo
	 * This helper method has the goal of finding a specific service combo holding the name put as the input. 
	 * To do that the method iterates over all the bookable services in the flexibook using a for loop.
	 * For each bookable service, if it is a service combo and it has the same name as the one in the input, 
	 * then it is the service combo we are looking for and it is then returned by the method.
	 * @author Robert Aprahamian
	 * @param serviceCombo is the name of the service combo to be found.
	 * @return ServiceCombo that has the name as the parameter serviceCombo. 
	 */
	private static ServiceCombo findServiceCombo(String serviceCombo) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof ServiceCombo) {
				if (aService.getName().equals(serviceCombo) ) return (ServiceCombo) aService;
			}
		}
		return null;
	}

	
	/**
	 * findService
	 * This helper method has the goal of finding a specific service holding the name put as the input. 
	 * To do that the method iterates over all the bookable services in the flexibook using a for loop.
	 * For each bookable service, if it is a service and it has the same name as the one in the input, 
	 * then it is the service we are looking for and it is then returned by the method.
	 * @author Robert Aprahamian
	 * @param service is the name of the service to be found.
	 * @return Service that has the name as the parameter service.
	 */
	private static Service findService(String service) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof Service) {
				if (aService.getName().equals(service) ) return (Service) aService;
			}
		}
		return null;
	}
	
	//Tamara---------------------------------------------------------------------------------------------------------------------
	/**
	 * The findTimeSlotOfApp is a helper method that calculates the time slot of an appointment
	 * depending on the start time, start date and duration of the appointment.
	 * @author Tamara Zard Aboujaoudeh
	 * @param serviceName is the service name
	 * @param optServicesString is the optional services 
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
			int min =0;
			ServiceCombo service = (ServiceCombo)thisService;
			
			if(optServicesString.equals("")) {
				min += service.getMainService().getService().getDuration(); 
			}else {
			
			String[] myArray = optServicesString.split(",");
			List<String> optionalServices = new ArrayList<>();
			
			for (String str : myArray) {
			    optionalServices.add(str);
			}

							
			for(int i=0; i<service.getServices().size(); i++) {
				ComboItem item = service.getServices().get(i);
				if(optionalServices.contains(item.getService().getName()) || item.isMandatory()){
					min += item.getService().getDuration();
				}
			}
		}		
			localEndTime = localStartTime.plusMinutes(min);
			endTime = Time.valueOf(localEndTime);
							
			}
		
		
		TimeSlot aTimeSlot = new TimeSlot(startDate, startTime, endDate, endTime, flexiBook);

		
	return aTimeSlot;
		}
	
	/**
	 * The findAppointment method is a helper method that finds the desired appointment in the flexibook
	 * application using the username of the customer, and his appointment information(name, date, start 
	 * time).
	 * @author Tamara Zard Aboujaoudeh
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


	/**
	 * The findBookabkleService is a helper method that finds the desired Bookable Service, ie 
	 * a normal service or a service combo.
	 * @author Tamara Zard Aboujaoudeh
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
	 * This method is to check if a time slot is within another time slot by comparing the two start 
	 * times, the dates and the end times.
	 * If it returns true then the time slot is within the other, if it returns false then they are 
	 * two disjoint time slots.
	 * @author Tamara Zard Aboujaoudeh
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