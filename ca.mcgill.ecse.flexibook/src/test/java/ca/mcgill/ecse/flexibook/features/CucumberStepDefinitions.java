package ca.mcgill.ecse.flexibook.features;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;

import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;

import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse223.flexibook.controller.TOAppointmentCalendarItem;
import ca.mcgill.ecse223.flexibook.controller.TOTimeSlot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CucumberStepDefinitions {


	private static FlexiBook flexibook;
	private String error;
	int errorCntr;

	private User tmpUser = null;
	private int AccountCntrBeforeCreation;
	private TOAppointmentCalendarItem item = null;
	private Service tmpService = null;



	private String oldUsername;
	private String oldPassword;
	private List<Appointment> oldAppointments;






	//Step definitions Eric-------------------------------------------------------------------------------------	

	/**
	 * @author Eric Chehata
	 */
	@Before
	public void setup() {
		FlexiBookApplication.setCurrentUser(null);
		FlexiBookApplication.getFlexibook().delete();
		AccountCntrBeforeCreation = 0;
	}

	/**
	 * @author Eric Chehata
	 */
	@Given("a Flexibook system exists")
	public void a_flexibook_system_exists() {
		flexibook = FlexiBookApplication.getFlexibook();
		error = "";
		errorCntr = 0;
	}


	/**
	 * @author Eric Chehata
	 */
	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			flexibook.addCustomer(columns.get("username"), columns.get("password"));

		}
	}

	/**
	 * @author Eric Chehata
	 */
	@When("the user tries to log in with username {string} and password {string}")
	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {
		AccountCntrBeforeCreation = flexibook.getCustomers().size();

		try {

			FlexiBookController.login(string, string2);
			tmpUser = findUser(string);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}

	}

	/**
	 * @author Eric Chehata
	 */
	@Then("the user should be successfully logged in")
	public void the_user_should_be_successfully_logged_in() {


		assertEquals(FlexiBookApplication.getCurrentUser(), tmpUser);

	}


	/**
	 * @author Eric Chehata
	 */
	@Then("the user should not be logged in")
	public void the_user_should_not_be_logged_in() {
		assertEquals(FlexiBookApplication.getCurrentUser(), null);			
	}


	/**
	 * @author Eric Chehata
	 */

	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String string) {
		assertTrue(error.contains(string));
	}

	/**
	 * @author Eric Chehata
	 */

	@Then("a new account shall be created")
	public void a_new_account_shall_be_created() {
		if (flexibook.getOwner() == null) {
			assertEquals(flexibook.getCustomers().size(), AccountCntrBeforeCreation + 1);
		}
		else {
			assertEquals(flexibook.getCustomers().size() + 1, AccountCntrBeforeCreation + 1);

		}
	}


	/**
	 * @author Eric Chehata
	 */

	@Then("the account shall have username {string} and password {string}")
	public void the_account_shall_have_username_and_password(String string, String string2) {

		if (FlexiBookApplication.getCurrentUser() == null) {

			if (string.equals("owner")) {
				assertEquals(flexibook.getOwner().getUsername(), string);
				assertEquals(flexibook.getOwner().getPassword(), string2);
			}else {
				assertEquals(flexibook.getCustomer(flexibook.getCustomers().size()-1).getUsername(), string);
				assertEquals(flexibook.getCustomer(flexibook.getCustomers().size()-1).getPassword(), string2);
			}
		} else {

			assertEquals(string, FlexiBookApplication.getCurrentUser().getUsername());
			assertEquals(string2, FlexiBookApplication.getCurrentUser().getPassword());

		}
	}

	/**
	 * @author Eric Chehata
	 */
	@Then("the user shall be successfully logged in")
	public void the_user_shall_be_successfully_logged_in() {
		assertEquals(FlexiBookApplication.getCurrentUser(), flexibook.getOwner());
	}

	/**
	 * @author Eric Chehata
	 */
	@Given("an owner account exists in the system with username {string} and password {string}")
	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
		Owner owner = new Owner (string, string2, flexibook);	    
	}

	/**
	 * @author Eric Chehata
	 */
	@Given("the user is logged out")
	public void the_user_is_logged_out() {
		FlexiBookApplication.setCurrentUser(null);			
	}

	/**
	 * @author Eric Chehata
	 */
	@When("the user tries to log out")
	public void the_user_tries_to_log_out() {
		try {

			FlexiBookController.logout();

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	/**
	 * @author Eric Chehata
	 */
	@Given("the user is logged in to an account with username {string}")
	public void the_user_is_logged_in_to_an_account_with_username(String string) {
		FlexiBookApplication.setCurrentUser(findUser(string));

	}


	/**
	 * @author Eric Chehata
	 */

	@Then("the user shall be logged out")
	public void the_user_shall_be_logged_out() {
		assertEquals(FlexiBookApplication.getCurrentUser(), null);
	}



	/**
	 * @author Eric Chehata
	 */
	@Given("an owner account exists in the system")
	public void an_owner_account_exists_in_the_system() {
		if(flexibook.getOwner() == null) {
			Owner owner = new Owner("owner", "owner", flexibook);
		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Given("a business exists in the system")
	public void a_business_exists_in_the_system() {
		if(flexibook.getBusiness()==null) {
			Business business = new Business("Busy Diner", "123 New Str", "(514)987-6543", "busy@gmail.com", flexibook);
		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Given("the following services exist in the system:")
	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			Service service = new Service(columns.get("name"), flexibook, 
					Integer.parseInt(columns.get("duration")), 
					Integer.parseInt(columns.get("downtimeDuration")),
					Integer.parseInt(columns.get("downtimeStart")));
			flexibook.addBookableService(service);
		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Given("the following service combos exist in the system:")
	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			ServiceCombo combo = new ServiceCombo(columns.get("name"), flexibook);

			String services = columns.get("services");
			String[] elements = services.split(",");
			List<String> fixedLenghtList = Arrays.asList(elements);
			ArrayList<String> listOfService = new ArrayList<String>(fixedLenghtList);

			String mandatory = columns.get("mandatory");
			String[] booleans = mandatory.split(",");

			String mainService = columns.get("mainService");

			for (int i = 0; i<listOfService.size(); i++) {
				for(int j = 0; j<booleans.length;j++) {
					if (i == j) {
						Service service = findService(listOfService.get(i));
						boolean isMandatory = false;
						if (booleans[j].equals("true")) isMandatory = true;
						ComboItem item = new ComboItem(isMandatory, service, combo);
						if(service.getName().equals(mainService)) {
							combo.setMainService(item);
						}
					}
				}

			}

			flexibook.addBookableService(combo);
		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Given("the business has the following opening hours:")
	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {

			Time start = toTime(columns.get("startTime"));
			Time end = toTime(columns.get("endTime"));


			DayOfWeek day = null;
			switch (columns.get("day")) {
			case "Monday":
				day = DayOfWeek.Monday;
				break;
			case "Tuesday":
				day = DayOfWeek.Tuesday;
				break;
			case "Wednesday":
				day = DayOfWeek.Wednesday;
				break;
			case "Thursday":
				day = DayOfWeek.Thursday;
				break;
			case "Friday":
				day = DayOfWeek.Friday;
				break;
			case "Saturday":
				day = DayOfWeek.Saturday;
				break;
			case "Sunday":
				day = DayOfWeek.Sunday;
				break;
			}
			BusinessHour BH = new BusinessHour(day, start, end, flexibook);
		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Given("the business has the following holidays:")
	public void the_business_has_the_following_holidays(io.cucumber.datatable.DataTable dataTable) {
		Business business = flexibook.getBusiness();
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);


		for (Map<String, String> columns : rows) {

			Date startDate = toDate(columns.get("startDate"));
			Date endDate = toDate(columns.get("endDate"));
			Time startTime = toTime(columns.get("startTime"));
			Time endTime = toTime(columns.get("endTime"));


			TimeSlot holiday = new TimeSlot(startDate, startTime, endDate, endTime, flexibook);
			business.addHoliday(holiday);
		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Given("the following appointments exist in the system:")
	public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);


		for (Map<String, String> columns : rows) {

			Date date = toDate(columns.get("date"));
			Time startTime = toTime(columns.get("startTime"));
			Time endTime = toTime(columns.get("endTime"));

			TimeSlot TS = new TimeSlot(date, startTime, date, endTime, flexibook);
			Appointment appointment = new Appointment(findCustomer(columns.get("customer")), 
					findBookableService(columns.get("serviceName")),
					TS, flexibook);
			flexibook.addAppointment(appointment);
		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Given("{string} is logged in to their account")
	public void is_logged_in_to_their_account(String string) {
		FlexiBookApplication.setCurrentUser(findUser(string));
	}
	/**
	 * @author Eric Chehata
	 */
	@When("{string} requests the appointment calendar for the week starting on {string}")
	public void requests_the_appointment_calendar_for_the_week_starting_on(String string, String string2) {
		try {

			item = FlexiBookController.viewAppointmentCalendar(string, string2, false);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;


		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Then("the following slots shall be unavailable:")
	public void the_following_slots_shall_be_unavailable(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		List <TOTimeSlot> unavailable = new ArrayList<TOTimeSlot>();

		for (Map<String, String> columns : rows) {

			Date date = toDate(columns.get("date"));
			Time startTime = toTime(columns.get("startTime"));
			Time endTime = toTime(columns.get("endTime"));
			TOTimeSlot TS = new TOTimeSlot(date, startTime, date, endTime);
			unavailable.add(TS);
		}
		List <TOTimeSlot> unavailableFromItems = item.getUnavailableTimeSlots();

		assertEquals(unavailable.size(), unavailableFromItems.size());
		for (int i = 0; i<unavailableFromItems.size(); i++) {
			assertTrue (unavailableFromItems.get(i).getStartDate().compareTo(unavailable.get(i).getStartDate()) == 0);
			assertTrue (unavailableFromItems.get(i).getEndDate().compareTo(unavailable.get(i).getEndDate()) == 0);

			LocalTime startExpected  = unavailable.get(i).getStartTime().toLocalTime();
			LocalTime endExpected  = unavailable.get(i).getEndTime().toLocalTime();
			LocalTime startActual = unavailableFromItems.get(i).getStartTime().toLocalTime();
			LocalTime endActual = unavailableFromItems.get(i).getEndTime().toLocalTime();


			assertTrue (startExpected.compareTo(startActual)==0);
			assertTrue (endExpected.compareTo(endActual)==0);		
		}
	}
	/**
	 * @author Eric Chehata
	 */
	@Then("the following slots shall be available:")
	public void the_following_slots_shall_be_available(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		List <TOTimeSlot> available = new ArrayList<TOTimeSlot>();

		for (Map<String, String> columns : rows) {

			Date date = toDate(columns.get("date"));
			Time startTime = toTime(columns.get("startTime"));
			Time endTime = toTime(columns.get("endTime"));
			TOTimeSlot TS = new TOTimeSlot(date, startTime, date, endTime);
			available.add(TS);
		}

		List <TOTimeSlot> availableFromItems = item.getAvailableTimeSlots();

		assertEquals(available.size(), availableFromItems.size());
		for (int i = 0; i<availableFromItems.size(); i++) {

			assertTrue (availableFromItems.get(i).getStartDate().compareTo(available.get(i).getStartDate()) == 0);
			assertTrue (availableFromItems.get(i).getEndDate().compareTo(available.get(i).getEndDate()) == 0);

			LocalTime startExpected  = available.get(i).getStartTime().toLocalTime();
			LocalTime endExpected  = available.get(i).getEndTime().toLocalTime();
			LocalTime startActual = availableFromItems.get(i).getStartTime().toLocalTime();
			LocalTime endActual = availableFromItems.get(i).getEndTime().toLocalTime();


			assertTrue (startExpected.compareTo(startActual)==0);
			assertTrue (endExpected.compareTo(endActual)==0);

		}

	}
	/**
	 * @author Eric Chehata
	 */
	@When("{string} requests the appointment calendar for the day of {string}")
	public void requests_the_appointment_calendar_for_the_day_of(String string, String string2) {
		try {

			item = FlexiBookController.viewAppointmentCalendar(string, string2, true);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;


		}
	}

	/**
	 * @author Eric Chehata
	 */
	@Then("the system shall report {string}")
	public void the_system_shall_report(String string) {
		assertTrue(error.contains(string));
	}

	/**
	 * @author Eric Chehata
	 */
	@After
	public void tearDown() {
		FlexiBookApplication.setCurrentUser(null);

		flexibook.delete();
	}


	//Saeid Step Definitions------------------------------------------------------------------------------------

	// Sign up for customer Account

	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Given("there is no existing username {string}")
	public void there_is_no_existing_username(String string) {
		// Write code here that turns the phrase above into concrete actions
		User user = findUser(string);
		if(user != null) {
			flexibook.getCustomers().remove(user);
			user.delete();
		}
	}

	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 * @param string2
	 */
	@When("the user provides a new username {string} and a password {string}")
	public void the_user_provides_a_new_username_and_a_password(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		AccountCntrBeforeCreation = flexibook.getCustomers().size();
		try {

			FlexiBookController.signUpCustomerAccount(string, string2);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}

	/**
	 * @author Mohammad Saeid Nafar
	 */
	@Then("a new customer account shall be created")
	public void a_new_customer_account_shall_be_created() {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(flexibook.getCustomers().size(), AccountCntrBeforeCreation + 1);
	}

	/**
	 * @author Mohammad Saeid Nafar
	 */
	@Then("no new account shall be created")
	public void no_new_account_shall_be_created() {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(flexibook.getCustomers().size(), AccountCntrBeforeCreation);
	}


	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Given("there is an existing username {string}")
	public void there_is_an_existing_username(String string) {
		// Write code here that turns the phrase above into concrete actions
		User user = findUser(string);
		if(user == null) {
			if(string.equals("owner")) {
				Owner owner = new Owner(string, "messi", flexibook);
			} else 
				flexibook.addCustomer(string, "messi");
		}

	}


	// Update account

	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 * @param string2
	 */
	@When("the user tries to update account with a new username {string} and password {string}")
	public void the_user_tries_to_update_account_with_a_new_username_and_password(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions

		try {

			oldUsername = FlexiBookApplication.getCurrentUser().getUsername();
			oldPassword = FlexiBookApplication.getCurrentUser().getPassword();

			FlexiBookController.updateAccount(oldUsername, string, string2);

		} catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}

	}


	/**
	 * @author Mohammad Saeid Nafar
	 */
	@Then("the account shall not be updated")
	public void the_account_shall_not_be_updated() {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(oldUsername, FlexiBookApplication.getCurrentUser().getUsername());
		assertEquals(oldPassword, FlexiBookApplication.getCurrentUser().getPassword());

	}


	// delete customer account

	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Given("the account with username {string} has pending appointments")
	public void the_account_with_username_has_pending_appointments(String string) {
		// Write code here that turns the phrase above into concrete actions
		//		if(findCustomer(string).getAppointments().size() == 0) {
		//			BookableService aBookableService = new Service("cut", flexibook, 30, 0, 0);
		//			flexibook.addBookableService(aBookableService);
		//			Date startDate = new Date(2020, 12, 12);
		//			Time time = new Time(25);
		//			TimeSlot timeslot = new TimeSlot(startDate, time, startDate, time, flexibook);
		//			flexibook.addTimeSlot(timeslot);
		//			flexibook.addAppointment(findCustomer(string), flexibook.getBookableService(0), flexibook.getTimeSlot(0));
		//		}

	}


	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@When("the user tries to delete account with the username {string}")
	public void the_user_tries_to_delete_account_with_the_username(String string) {
		// Write code here that turns the phrase above into concrete actions
		if(!(string.equals("owner"))) {
			oldAppointments = findCustomer(string).getAppointments();
		}

		try {

			String username = FlexiBookApplication.getCurrentUser().getUsername();
			FlexiBookController.deleteCustomerAccount(username, string);

		} catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}

	}

	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Then("the account with the username {string} does not exist")
	public void the_account_with_the_username_does_not_exist(String string) {
		// Write code here that turns the phrase above into concrete actions
		if(string.equals("owner")) {
			assertNull(findUser(string));
		} else {
			assertNull((findCustomer(string)));
		}

	}

	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Then("all associated appointments of the account with the username {string} shall not exist")
	public void all_associated_appointments_of_the_account_with_the_username_shall_not_exist(String string) {
		// Write code here that turns the phrase above into concrete actions
		boolean exists = false; 
		for(Appointment appointment: oldAppointments) {
			if(flexibook.getAppointments().contains(appointment)) {
				exists = true;
			}
		}
		assertFalse(exists);
	}


	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Then("the account with the username {string} exists")
	public void the_account_with_the_username_exists(String string) {
		// Write code here that turns the phrase above into concrete actions
		boolean exists = false;
		if(findUser(string) != null) {
			exists = true;
		}
		assertTrue(exists);
	}

	//Marc step definitions----------------------------------------------------------------------------------------------------------------------

	@Given("the Owner with username {string} is logged in")
	public void the_owner_with_username_is_logged_in(String string) {
		if (flexibook.getOwner()!=null) {
			flexibook.getOwner().setUsername(string);
		}
		else {
			new Owner(string, "owner", flexibook);
		}
		FlexiBookApplication.setCurrentUser(flexibook.getOwner());
	}


	@When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
	public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
		try {
			FlexiBookController.addService(string2, Integer.parseInt(string3), Integer.parseInt(string5), Integer.parseInt(string4), string);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	@Then("the service {string} shall exist in the system")
	public void the_service_shall_exist_in_the_system(String string) {
		boolean exists = false;
		if(findService(string)!= null) exists = true;
		assertTrue(exists);
	}

	@Then("the service {string} shall have duration {string}, start of down time {string} and down time duration {string}")
	public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4) {
		assertEquals(findService(string).getDuration(), Integer.parseInt(string2));
		assertEquals(findService(string).getDowntimeStart(), Integer.parseInt(string3));
		assertEquals(findService(string).getDowntimeDuration(), Integer.parseInt(string4));

	}
	@Then("the number of services in the system shall be {string}")
	public void the_number_of_services_in_the_system_shall_be(String string) {

		assertEquals(getNumServices(), Integer.parseInt(string));

	}


	@Then("an error message with content {string} shall be raised")
	public void an_error_message_with_content_shall_be_raised(String string) {
		assertTrue(error.contains(string));
	}

	@Then("the service {string} shall not exist in the system")
	public void the_service_shall_not_exist_in_the_system(String string) {
		boolean exists = false;
		if(findService(string)!= null) exists = true;
		assertFalse(exists);	
	}


	@Then("the service {string} shall still preserve the following properties:")
	public void the_service_shall_still_preserve_the_following_properties(String string, io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		Service service = findService(string);
		for (Map<String, String> columns : rows) {

			assertEquals(columns.get("name"), service.getName());
			assertEquals(Integer.parseInt(columns.get("duration")), service.getDuration());
			assertEquals(Integer.parseInt(columns.get("downtimeStart")), service.getDowntimeStart());
			assertEquals(Integer.parseInt(columns.get("downtimeDuration")), service.getDowntimeDuration());


		}
	}


	@Given("Customer with username {string} is logged in")
	public void customer_with_username_is_logged_in(String string) {
		Customer customer = findCustomer(string);
		if(customer == null) {
			customer = new Customer(string, "password", flexibook);
		}
		FlexiBookApplication.setCurrentUser(customer);
	}



	@When("{string} initiates the deletion of service {string}")
	public void initiates_the_deletion_of_service(String string, String string2) {
		try {
			FlexiBookController.deleteService(string2, string);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	@Then("the number of appointments in the system with service {string} shall be {string}")
	public void the_number_of_appointments_in_the_system_with_service_shall_be(String string, String string2) {
		assertEquals(getNumAppForService(string), Integer.parseInt(string2));
	}
	@Then("the number of appointments in the system shall be {string}")
	public void the_number_of_appointments_in_the_system_shall_be(String string) {
		assertEquals(flexibook.getAppointments().size(), Integer.parseInt(string));
	}


	@Then("the service combos {string} shall not exist in the system")
	public void the_service_combos_shall_not_exist_in_the_system(String string) {
		String[] elements = string.split(",");
		for(int i = 0; i<elements.length; i++) {
			assertNull(findBookableService(elements[i]));
		}
	}
	@Then("the service combos {string} shall not contain service {string}")
	public void the_service_combos_shall_not_contain_service(String string, String string2) {
		ServiceCombo combo = (ServiceCombo) findBookableService(string);
		for(ComboItem item : combo.getServices()) {
			assertFalse(item.getService().getName().equals(string2));
		}
	}
	@Then("the number of service combos in the system shall be {string}")
	public void the_number_of_service_combos_in_the_system_shall_be(String string) {
		assertEquals(getNumServiceCombos(), Integer.parseInt(string));
	}


	@When("{string} initiates the update of the service {string} to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	public void initiates_the_update_of_the_service_to_name_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5, String string6) {
		tmpService = findService(string2);
		try {
			FlexiBookController.updateService(string2, Integer.parseInt(string4), Integer.parseInt(string6), Integer.parseInt(string5), string, string3);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}



	@Then("the service {string} shall be updated to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	public void the_service_shall_be_updated_to_name_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
		Service oldService = tmpService;
		assertEquals(string2, oldService.getName());
		assertEquals(Integer.parseInt(string3), oldService.getDuration());
		assertEquals(Integer.parseInt(string4), oldService.getDowntimeStart());
		assertEquals(Integer.parseInt(string5), oldService.getDowntimeDuration());

	}



	//Fadi Step definitions--------------------------------------------------------------------------------------------------------------------------



	@Given("the system's time and date is {string}")
	public void the_system_s_time_and_date_is(String string) {
		String temp1 = string.substring(0, 10);
		String temp2 = string.substring(11, 16);
		temp2 = temp2+":00";
		Time time = Time.valueOf(temp2);
		Date date = Date.valueOf(temp1);
		SystemTime.setSysDate(date);
		SystemTime.setSysTime(time);
	}


	//Robert Step Definitions-----------------------------------------------------------------------------------	






	//Tamara Step Definitions-----------------------------------------------------------------------------------





	//----------------------------------------------------------------------------------------------------------	
	//Helper methods--------------------------------------------------------------------------------------------	
	//----------------------------------------------------------------------------------------------------------	


	//Eric------------------------------------------------------------------------------------------------------	

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

				}
			}

		}
		return foundUser;
	}	


	/**
	 * Helper method to convert a String corresponding to a time into a Time.
	 * @author Eric Chehata
	 * @param t: String of the time we want to convert
	 * @return the Time 
	 */
	private static Time toTime(String t) {
		String[] tArray = t.split(":");
		int[] intArray = new int[2];
		intArray[0] = Integer.parseInt(tArray[0]);
		intArray[1] = Integer.parseInt(tArray[1]);
		LocalTime localTime = LocalTime.of(intArray[0], intArray[1]);
		return Time.valueOf(localTime);

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


		LocalDate localDate = LocalDate.of(intArray[0], intArray[1], intArray[2]);
		return Date.valueOf(localDate);

	}



	//Saeid-----------------------------------------------------------------------------------------------------


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


	//Marc------------------------------------------------------------------------------------------------------

	private static Service findService(String service) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();

		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof Service) {
				if (aService.getName().equals(service)) return (Service) aService;
			}
		}

		return null;
	}

	private static BookableService findBookableService(String service) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();

		for (BookableService aService : flexibook.getBookableServices()) {

			if (aService.getName().equals(service)) return aService;

		}

		return null;
	}


	private static int getNumServiceCombos() {
		int size = 0;
		for (BookableService S : flexibook.getBookableServices()) {
			if (S instanceof ServiceCombo) size++;
		}
		return size;
	}

	private static int getNumServices() {
		int size = 0;
		for (BookableService S : flexibook.getBookableServices()) {
			if (S instanceof Service) size++;
		}
		return size;
	}

	private static int getNumAppForService(String service) {
		int size = 0;
		for (Appointment app : flexibook.getAppointments()) {
			if(app.getBookableService().getName().equals(service)) size++;
		}
		return size;
	}


}


