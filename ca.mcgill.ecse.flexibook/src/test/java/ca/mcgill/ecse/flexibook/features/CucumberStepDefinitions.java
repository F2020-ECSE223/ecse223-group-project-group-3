package ca.mcgill.ecse.flexibook.features;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.SystemTime;
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
	private int errorCntr;
	private User tmpUser = null;
	private int AccountCntrBeforeCreation;
	private TOAppointmentCalendarItem item = null;
	private Service tmpService = null;
	private int numberOfAppTemp =0;
	private String oldUsername;
	private String oldPassword;
	private List<Appointment> oldAppointments;
	private Appointment app;

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
			flexibook.addCustomer(columns.get("username"), columns.get("password"), 0);

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
	@Then("the user shall be successfully logged in")
	public void the_user_shall_be_successfully_logged_in() {
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

		if(flexibook.getBusiness() == null) {
			Business business = new Business ("businessName","location","phoneNumber","email", flexibook);
			FlexiBookApplication.getFlexibook().setBusiness(business);	
		}


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


	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Given("there is no existing username {string}")
	public void there_is_no_existing_username(String string) {
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
		assertEquals(flexibook.getCustomers().size(), AccountCntrBeforeCreation + 1);
	}

	/**
	 * @author Mohammad Saeid Nafar
	 */
	@Then("no new account shall be created")
	public void no_new_account_shall_be_created() {
		assertEquals(flexibook.getCustomers().size(), AccountCntrBeforeCreation);
	}


	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Given("there is an existing username {string}")
	public void there_is_an_existing_username(String string) {
		User user = findUser(string);
		if(user == null) {
			if(string.equals("owner")) {
				Owner owner = new Owner(string, "messi", flexibook);
			} else 
				flexibook.addCustomer(string, "messi",0);
		}

	}


	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 * @param string2
	 */
	@When("the user tries to update account with a new username {string} and password {string}")
	public void the_user_tries_to_update_account_with_a_new_username_and_password(String string, String string2) {

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

		assertEquals(oldUsername, FlexiBookApplication.getCurrentUser().getUsername());
		assertEquals(oldPassword, FlexiBookApplication.getCurrentUser().getPassword());

	}


	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@Given("the account with username {string} has pending appointments")
	public void the_account_with_username_has_pending_appointments(String string) {

	}


	/**
	 * @author Mohammad Saeid Nafar
	 * @param string
	 */
	@When("the user tries to delete account with the username {string}")
	public void the_user_tries_to_delete_account_with_the_username(String string) {
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
		boolean exists = false;
		if(findUser(string) != null) {
			exists = true;
		}
		assertTrue(exists);
	}

	//Marc step definitions----------------------------------------------------------------------------------------------------------------------

	/**
	 * @author Marc Saber
	 */
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

	/**
	 * @author Marc Saber
	 */

	@When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
	public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
		try {
			FlexiBookController.addService(string2, Integer.parseInt(string3), Integer.parseInt(string5), Integer.parseInt(string4), string);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	/**
	 * @author Marc Saber
	 */

	@Then("the service {string} shall exist in the system")
	public void the_service_shall_exist_in_the_system(String string) {
		boolean exists = false;
		if(findService(string)!= null) exists = true;
		assertTrue(exists);
	}

	/**
	 * @author Marc Saber
	 */

	@Then("the service {string} shall have duration {string}, start of down time {string} and down time duration {string}")
	public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4) {
		assertEquals(findService(string).getDuration(), Integer.parseInt(string2));
		assertEquals(findService(string).getDowntimeStart(), Integer.parseInt(string3));
		assertEquals(findService(string).getDowntimeDuration(), Integer.parseInt(string4));

		/**
		 * @author Marc Saber
		 */

	}
	@Then("the number of services in the system shall be {string}")
	public void the_number_of_services_in_the_system_shall_be(String string) {

		assertEquals(getNumServices(), Integer.parseInt(string));

	}

	/**
	 * @author Marc Saber
	 */

	@Then("an error message with content {string} shall be raised")
	public void an_error_message_with_content_shall_be_raised(String string) {
		assertTrue(error.contains(string));
	}


	/**
	 * @author Marc Saber
	 */

	@Then("the service {string} shall not exist in the system")
	public void the_service_shall_not_exist_in_the_system(String string) {
		boolean exists = false;
		if(findService(string)!= null) exists = true;
		assertFalse(exists);	
	}


	/**
	 * @author Marc Saber
	 */

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

	/**
	 * @author Marc Saber
	 */

	@Given("Customer with username {string} is logged in")
	public void customer_with_username_is_logged_in(String string) {
		Customer customer = findCustomer(string);
		if(customer == null) {
			customer = new Customer(string, "password",0, flexibook);
		}
		FlexiBookApplication.setCurrentUser(customer);
	}

	/**
	 * @author Marc Saber
	 */

	@When("{string} initiates the deletion of service {string}")
	public void initiates_the_deletion_of_service(String string, String string2) {
		try {
			FlexiBookController.deleteService(string2, string);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	/**
	 * @author Marc Saber
	 */

	@Then("the number of appointments in the system with service {string} shall be {string}")
	public void the_number_of_appointments_in_the_system_with_service_shall_be(String string, String string2) {
		assertEquals(getNumAppForService(string), Integer.parseInt(string2));
	}

	/**
	 * @author Marc Saber
	 */

	@Then("the number of appointments in the system shall be {string}")
	public void the_number_of_appointments_in_the_system_shall_be(String string) {
		assertEquals(flexibook.getAppointments().size(), Integer.parseInt(string));
	}


	/**
	 * @author Marc Saber
	 */

	@Then("the service combos {string} shall not exist in the system")
	public void the_service_combos_shall_not_exist_in_the_system(String string) {
		String[] elements = string.split(",");
		for(int i = 0; i<elements.length; i++) {
			assertNull(findServiceCombo(elements[i]));
		}
	}

	/**
	 * @author Marc Saber
	 */

	@Then("the service combos {string} shall not contain service {string}")
	public void the_service_combos_shall_not_contain_service(String string, String string2) {
		String[] comboss = string.split(",");

		for(int k=0;k<comboss.length;k++) {
			ServiceCombo combo = findServiceCombo(comboss[k]);
			String servicesinCombo = joinServices(combo);
			String[] servicesinCOmboInArray = servicesinCombo.split(",");
			for(int i=0;i<combo.getServices().size();i++) {
				assertFalse(servicesinCOmboInArray[i].equals(string2));
			}
		}
	}

	/**
	 * @author Marc Saber
	 */


	@Then("the number of service combos in the system shall be {string}")
	public void the_number_of_service_combos_in_the_system_shall_be(String string) {
		assertEquals(getNumServiceCombos(), Integer.parseInt(string));
	}


	/**
	 * @author Marc Saber
	 */

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

	/**
	 * @author Marc Saber
	 */

	@Then("the service {string} shall be updated to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	public void the_service_shall_be_updated_to_name_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
		Service oldService = tmpService;
		assertEquals(string2, oldService.getName());
		assertEquals(Integer.parseInt(string3), oldService.getDuration());
		assertEquals(Integer.parseInt(string4), oldService.getDowntimeStart());
		assertEquals(Integer.parseInt(string5), oldService.getDowntimeDuration());

	}



	//Fadi Step definitions--------------------------------------------------------------------------------------------------------------------------


	/**
	 * @author Fadi Tawfik Beshay
	 */
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


	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Given("no business exists")
	public void no_business_exists() {
		assertEquals(FlexiBookApplication.getFlexibook().getBusiness(), null);
	}


	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to set up the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_set_up_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {

		try {
			FlexiBookController.SetUpContactInfo(string, string2, string3, string4);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("a new business with new {string} and {string} and {string} and {string} shall {string} created")
	public void a_new_business_with_new_and_and_and_shall_created(String string, String string2, String string3, String string4, String string5) {
		if (error.equals("")) {
			assertEquals("be", string5);
			assertEquals(flexibook.getBusiness().getName(), string);
			assertEquals(flexibook.getBusiness().getAddress(), string2);
			assertEquals(flexibook.getBusiness().getPhoneNumber(), string3);
			assertEquals(flexibook.getBusiness().getEmail(), string4);
		}
		else {
			assertEquals("not be", string5);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("an error message {string} shall {string} raised")
	public void an_error_message_shall_raised(String string, String string2) {
		if(string.equals("")) {
			assertEquals("not be",string2);
		}
		else {
			assertEquals("be",string2);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Given("a business exists with the following information:")
	public void a_business_exists_with_the_following_information(io.cucumber.datatable.DataTable dataTable) {

		List<String> rows = dataTable.asList();
		String name = rows.get(4);
		String address = rows.get(5);
		String phoneNumber = rows.get(6);
		String email = rows.get(7);

		FlexiBookApplication.getFlexibook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexibook()));
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Given("the business has a business hour on {string} with start time {string} and end time {string}")
	public void the_business_has_a_business_hour_on_with_start_time_and_end_time(String string, String string2, String string3) {

		DayOfWeek temp = DayOfWeek.valueOf(string);
		string2 = string2+":00";
		string3 = string3+":00";
		Time temp2 = Time.valueOf(string2);
		Time temp3 = Time.valueOf(string3);
		BusinessHour test = new BusinessHour(temp, temp3, temp3, flexibook);
		FlexiBookApplication.getFlexibook().getBusiness().addBusinessHour(test);

	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to add a new business hour on {string} with start time {string} and end time {string}")
	public void the_user_tries_to_add_a_new_business_hour_on_with_start_time_and_end_time(String string, String string2, String string3) {

		DayOfWeek temp = DayOfWeek.valueOf(string);
		string2 = string2+":00";
		string3 = string3+":00";
		Time temp2 = Time.valueOf(string2);
		Time temp3 = Time.valueOf(string3);

		try {
			FlexiBookController.SetUpBusinessHours(temp, temp2, temp3);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("a new business hour shall {string} created")
	public void a_new_business_hour_shall_created(String string) {

		if (error.equals("")) {
			assertEquals("be",string);
		}
		else {
			assertEquals("not be",string);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to access the business information")
	public void the_user_tries_to_access_the_business_information() {
		try {
			List<String> test = FlexiBookController.ViewBusinessInfo();
		}
		catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("the {string} and {string} and {string} and {string} shall be provided to the user")
	public void the_and_and_and_shall_be_provided_to_the_user(String string, String string2, String string3, String string4) {
		try {
			List<String> test = FlexiBookController.ViewBusinessInfo();
			assertEquals(test.get(0), string);
			assertEquals(test.get(1), string2);
			assertEquals(test.get(2), string3);
			assertEquals(test.get(3), string4);
		}
		catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Given("a {string} time slot exists with start time {string} at {string} and end time {string} at {string}")
	public void a_time_slot_exists_with_start_time_at_and_end_time_at(String string, String string2, String string3, String string4, String string5) {

		string3 = string3+":00";
		string5 = string5+":00";
		Time temp3 = Time.valueOf(string3);
		Time temp5 = Time.valueOf(string5);
		Date startDate = Date.valueOf(string2);
		Date endDate = Date.valueOf(string4);

		TimeSlot test = new TimeSlot(startDate, temp3, endDate, temp5, flexibook);

		if(string.equals("holiday")) {
			FlexiBookApplication.getFlexibook().getBusiness().addHoliday(test);
		}
		else {
			FlexiBookApplication.getFlexibook().getBusiness().addVacation(test);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to add a new {string} with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_add_a_new_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5) {

		string3 = string3+":00";
		string5 = string5+":00";
		Time temp3 = Time.valueOf(string3);
		Time temp5 = Time.valueOf(string5);

		Date startDate = Date.valueOf(string2);
		Date endDate = Date.valueOf(string4);


		try {
			FlexiBookController.AddaNewTimeSlot(string, startDate, temp3, endDate, temp5);;
		}
		catch(InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("a new {string} shall {string} be added with start date {string} at {string} and end date {string} at {string}")
	public void a_new_shall_be_added_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {

		string4 = string4+":00";
		string6 = string6+":00";
		Time temp4 = Time.valueOf(string4);
		Time temp6 = Time.valueOf(string6);
		Date startDate = Date.valueOf(string3);
		Date endDate = Date.valueOf(string5);

		try {
			FlexiBookController.AddaNewTimeSlot(string, startDate, temp4, endDate, temp6);
		}
		catch(InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
		if(error.equals("")) {
			assertEquals("be",string2);
		}
		else {
			assertEquals("not be",string2);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to update the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_update_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {

		try {
			FlexiBookController.UpdateBasicInfo(string, string2, string3, string4);
		} catch (InvalidInputException e) {
			error+=e.getMessage();
			errorCntr++;
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("the business information shall {string} updated with new {string} and {string} and {string} and {string}")
	public void the_business_information_shall_updated_with_new_and_and_and(String string, String string2, String string3, String string4, String string5) {

		String error = "";
		try {
			FlexiBookController.UpdateBasicInfo(string2, string3, string4, string5);
		} catch (InvalidInputException e) {
			error+=e.getMessage();
		}
		if (error.equals("")) {
			assertEquals("be", string);
			assertEquals(FlexiBookApplication.getFlexibook().getBusiness().getName(), string2);
			assertEquals(FlexiBookApplication.getFlexibook().getBusiness().getAddress(), string3);
			assertEquals(FlexiBookApplication.getFlexibook().getBusiness().getPhoneNumber(), string4);
			assertEquals(FlexiBookApplication.getFlexibook().getBusiness().getEmail(), string5);
		}
		else {
			assertEquals("not be", string);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to change the business hour {string} at {string} to be on {string} starting at {string} and ending at {string}")
	public void the_user_tries_to_change_the_business_hour_at_to_be_on_starting_at_and_ending_at(String string, String string2, String string3, String string4, String string5) {
		DayOfWeek day1 = DayOfWeek.valueOf(string);
		DayOfWeek day2 = DayOfWeek.valueOf(string3);
		string2 = string2+":00";
		string4 = string4+":00";
		string5 = string5+":00";
		Time temp2 = Time.valueOf(string2);
		Time temp4 = Time.valueOf(string4);
		Time temp5 = Time.valueOf(string5);

		try {
			FlexiBookController.UpdateBusinessHours(day1, temp2, day2, temp4, temp5);
		} catch (InvalidInputException e) {
			error+=e.getMessage();
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("the business hour shall {string} be updated")
	public void the_business_hour_shall_be_updated(String string) {

		if (error.equals("")) {
			assertEquals("be",string);
		}
		else {
			assertEquals("not be",string);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to remove the business hour starting {string} at {string}")
	public void the_user_tries_to_remove_the_business_hour_starting_at(String string, String string2) {
		DayOfWeek day1 = DayOfWeek.valueOf(string);
		string2 = string2+":00";
		Time temp2 = Time.valueOf(string2);
		try {
			FlexiBookController.RemoveBusinessHours(day1, temp2);
		} catch (InvalidInputException e) {
			error+=e.getMessage();
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("the business hour starting {string} at {string} shall {string} exist")
	public void the_business_hour_starting_at_shall_exist(String string, String string2, String string3) {

		DayOfWeek day1 = DayOfWeek.valueOf(string);
		string2 = string2+":00";
		Time temp2 = Time.valueOf(string2);
		try {
			FlexiBookController.RemoveBusinessHours(day1, temp2);
		} catch (InvalidInputException e) {
			error+=e.getMessage();
		}
		if (error.equals("")) {
			assertEquals("not",string3);
		}
		else {
			assertEquals("",string3);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("an error message {string} shall {string} be raised")
	public void an_error_message_shall_be_raised(String string, String string2) {
		if (error.equals("")) {
			assertEquals("not",string2);
		}
		else {
			assertEquals("",string2);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to change the {string} on {string} at {string} to be with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_change_the_on_at_to_be_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6, String string7) {

		string3 = string3+":00";
		string5 = string5+":00";
		string7 = string7+":00";

		Date temp2 = Date.valueOf(string2);
		Time temp3 = Time.valueOf(string3);
		Date temp4 = Date.valueOf(string4);
		Time temp5 = Time.valueOf(string5);
		Date temp6 = Date.valueOf(string6);
		Time temp7 = Time.valueOf(string7);

		try {
			FlexiBookController.UpdateHolidayOrVacation(string, temp2, temp3, temp4, temp5, temp6, temp7);
		}
		catch (InvalidInputException e) {
			error+=e.getMessage();
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("the {string} shall {string} be updated with start date {string} at {string} and end date {string} at {string}")
	public void the_shall_be_updated_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {

		if (error.equals("")) {
			assertEquals("be",string2);
		}
		else {
			assertEquals("not be",string2);
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@When("the user tries to remove an existing {string} with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_remove_an_existing_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5) {
		string3 = string3+":00";
		string5 = string5+":00";
		Time startTime = Time.valueOf(string3);
		Time endTime = Time.valueOf(string5);		
		Date startDate = Date.valueOf(string2);
		Date endDate = Date.valueOf(string4);
		try {
			FlexiBookController.RemoveTimeSlot(string, startDate, startTime, endDate, endTime);
		}
		catch (InvalidInputException e) {
			error+=e.getMessage();
		}
	}

	/**
	 * @author Fadi Tawfik Beshay
	 */
	@Then("the {string} with start date {string} at {string} shall {string} exist")
	public void the_with_start_date_at_shall_exist(String string, String string2, String string3, String string4) {
		if(error.equals("")) {
			assertEquals("not", string4);
		}
		else {
			assertEquals("", string4);
		}
	}


	@Then("the {string} shall {string} updated with start date {string} at {string} and end date {string} at {string}")
	public void the_shall_updated_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}



	
	
	//Robert Step Definitions-----------------------------------------------------------------------------------	

	/**
	 * @author Robert Aprahamian
	 */

	@When("{string} initiates the definition of a service combo {string} with main service {string}, services {string} and mandatory setting {string}")
	public void initiates_the_definition_of_a_service_combo_with_main_service_services_and_mandatory_setting(String string, String string2, String string3, String string4, String string5) throws InvalidInputException{
		// Write code here that turns the phrase above into concrete actions
		try {
			FlexiBookController.defineServiceCombo(string, string2, string3, string4, string5);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;	
		}
	}
	/**
	 * @author Robert Aprahamian
	 */

	@Then("the service combo {string} shall exist in the system")
	public void the_service_combo_shall_exist_in_the_system(String string) {
		assertEquals(findServiceCombo(string).getName(), string);
		// Write code here that turns the phrase above into concrete actions

	}

	/**
	 * @author Robert Aprahamian
	 */

	@Then("the service combo {string} shall contain the services {string} with mandatory setting {string}")
	public void the_service_combo_shall_contain_the_services_with_mandatory_setting(String string, String string2, String string3) {
		ServiceCombo sc = findServiceCombo(string);
		assertEquals(joinServices(sc),string2);
		assertEquals(joinMandatories(sc),string3);
		// Write code here that turns the phrase above into concrete actions
	}
	/**
	 * @author Robert Aprahamian
	 */

	@Then("the main service of the service combo {string} shall be {string}")
	public void the_main_service_of_the_service_combo_shall_be(String string, String string2) {
		assertEquals(findServiceCombo(string).getMainService().getService().getName(),string2);
		// Write code here that turns the phrase above into concrete actions
	}
	/**
	 * @author Robert Aprahamian
	 */

	@Then("the service {string} in service combo {string} shall be mandatory")
	public void the_service_in_service_combo_shall_be_mandatory(String string, String string2) {
		assertEquals(findServiceCombo(string2).getMainService().getMandatory(),true);
		// Write code here that turns the phrase above into concrete actions
	}
	/**
	 * @author Robert Aprahamian
	 */




	//--------------------------------------------
	/**
	 * @author Robert Aprahamian
	 */

	@Then("the service combo {string} shall not exist in the system")
	public void the_service_combo_shall_not_exist_in_the_system(String string) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(findServiceCombo(string),null);
	}
	/**
	 * @author Robert Aprahamian
	 */

	@Then("the service combo {string} shall preserve the following properties:")
	public void the_service_combo_shall_preserve_the_following_properties(String string, io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		ServiceCombo sc = findServiceCombo(rows.get(0).get("name"));
		assertEquals(sc.getName(),rows.get(0).get("name"));
		assertEquals(sc.getMainService().getService().getName(),rows.get(0).get("mainService"));
		assertEquals(joinServices(sc),rows.get(0).get("services"));
		assertEquals(joinMandatories(sc),rows.get(0).get("mandatory"));

	}
	//-------------------------------------------------------------------------
	/**
	 * @author Robert Aprahamian
	 */

	@When("{string} initiates the update of service combo {string} to name {string}, main service {string} and services {string} and mandatory setting {string}")
	public void initiates_the_update_of_service_combo_to_name_main_service_and_services_and_mandatory_setting(String string, String string2, String string3, String string4, String string5, String string6) {
		// Write code here that turns the phrase above into concrete actions
		try {
			FlexiBookController.updateServiceCombo(string, string2, string3, string4, string5, string6);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}
	/**
	 * @author Robert Aprahamian
	 */

	@Then("the service combo {string} shall be updated to name {string}")
	public void the_service_combo_shall_be_updated_to_name(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(findServiceCombo(string2).getName(),string2);
	}

	/**
	 * @author Robert Aprahamian
	 */

	@When("{string} initiates the deletion of service combo {string}")
	public void initiates_the_deletion_of_service_combo(String string, String string2) {
		try {
			FlexiBookController.deleteServiceCombo(string, string2);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}



	//Tamara Step Definitions-----------------------------------------------------------------------------------


	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 */
	@Given("{string} has a {string} appointment with optional sevices {string} on {string} at {string}")
	public void has_a_appointment_with_optional_sevices_on_at(String string, String string2, String string3, String string4, String string5) {

		Customer customer = (Customer) findUser(string);
		ServiceCombo serviceCombo = findServiceCombo(string2);
		TimeSlot aTimeSlot = findTimeSlotOfApp(string2,string3, string4, string5);

		Appointment app = new Appointment(customer, serviceCombo, aTimeSlot, flexibook);

		String[] myArray = string3.split(",");
		List<String> optionalServices = new ArrayList<>();

		for (String str : myArray) {
			optionalServices.add(str);
		}

		for(int i=0; i<serviceCombo.getServices().size(); i++) {
			ComboItem item = serviceCombo.getServices().get(i);
			if(optionalServices.contains(item.getService().getName())){
				app.addChosenItem(item);
			}
		}

	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 */
	@When("{string} attempts to cancel their {string} appointment on {string} at {string}")
	public void attempts_to_cancel_their_appointment_on_at(String string, String string2, String string3, String string4) {
		numberOfAppTemp = flexibook.getAppointments().size();
		try {
			FlexiBookController.cancelAppointment(string,string, string2, string3, string4);
			
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 */
	@When("{string} attempts to cancel {string}'s {string} appointment on {string} at {string}")
	public void attempts_to_cancel_s_appointment_on_at(String string, String string2, String string3, String string4, String string5) {
		numberOfAppTemp = flexibook.getAppointments().size();
		try {
			FlexiBookController.cancelAppointment(string, string2, string3, string4, string5);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}


	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 */
	@Then("{string} shall have a {string} appointment on {string} from {string} to {string}")
	public void shall_have_a_appointment_on_from_to(String string, String string2, String string3, String string4, String string5) {
		boolean is =false; 
		if(findAppointment(string, string2, string3, string4) != null) {
			is=true;
		}

		assertTrue(is);
	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param int1
	 */
	@Then("there shall be {int} more appointment in the system")
	public void there_shall_be_more_appointment_in_the_system(Integer int1) {

		assertEquals(flexibook.getAppointments().size(), numberOfAppTemp +int1);
	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 */
	@Then("{string}'s {string} appointment on {string} at {string} shall be removed from the system")
	public void s_appointment_on_at_shall_be_removed_from_the_system(String string, String string2, String string3, String string4) {

		numberOfAppTemp = flexibook.getAppointments().size();
		try{
			FlexiBookController.cancelAppointment(string, string, string2, string3, string4);
			numberOfAppTemp--;
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param int1
	 */
	@Then("there shall be {int} less appointment in the system")
	public void there_shall_be_less_appointment_in_the_system(Integer int1) {
		assertEquals(flexibook.getAppointments().size(), int1);
		
	}

	
	/**
	 * @author Eric Chehata
	 * @param dataTable
	 */
	@Given("the business has the following opening hours")
	public void the_business_has_the_following_opening_hours2(io.cucumber.datatable.DataTable dataTable) {

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
	 * @param dataTable
	 */
	@Given("the business has the following holidays")
	public void the_business_has_the_following_holidays2(io.cucumber.datatable.DataTable dataTable) {

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
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 */
	@When("{string} schedules an appointment on {string} for {string} at {string}")
	public void schedules_an_appointment_on_for_at(String string, String string2, String string3, String string4) {
		try {
			numberOfAppTemp = flexibook.getAppointments().size();
			FlexiBookController.makeAppointment(string, string3, null, string2, string4);

		}
		catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}
	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 */
	@When("{string} schedules an appointment on {string} for {string} with {string} at {string}")
	public void schedules_an_appointment_on_for_at(String string, String string2, String string3, String string4, String string5) {

		try {
			numberOfAppTemp = flexibook.getAppointments().size();
			FlexiBookController.makeAppointment(string, string3, string4, string2, string5);
		}
		catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 * @param string6
	 */
	@When("{string} attempts to update their {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_their_appointment_on_at_to_at(String string, String string2, String string3, String string4, String string5, String string6) {

		try {
			numberOfAppTemp = flexibook.getAppointments().size();
			FlexiBookController.updateAppointment(string,string, string2, string3, string4, string5, string6, null, null, false, null);
			error = "successful";
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 * @param string6
	 */
	@When("{string} attempts to {string} {string} from their {string} appointment on {string} at {string}")

	public void attempts_to_update_their_appointment_to(String string, String string2, String string3, String string4, String string5, String string6) {

		try {
			numberOfAppTemp = flexibook.getAppointments().size();
			FlexiBookController.updateAppointment(string,string, string4, string5, string6, string5, string6, string2, string3, false, null);
			error = "successful";
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 */
	@Then("the system shall report that the update was {string}")
	public void the_system_shall_report_that_the_update_was(String string) {
		assertTrue(error.contains(string));
	}

	/**
	 * @author Tamara Zard Aboujaoudeh
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 * @param string6
	 * @param string7
	 */
	@When("{string} attempts to update {string}'s {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_s_appointment_on_at_to_at(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
		try {
			FlexiBookController.updateAppointment(string, string2,string3, string4, string5, string6, string7, null, null, false, null);
		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;

		}
	}

	
	//----------------------------------------------------------------------------------------------------------
	//-------------------------New Step Definitions for Appointment management process--------------------------
	//----------------------------------------------------------------------------------------------------------
	

	//Y
		@Given("{string} has {int} no-show records")
		public void has_no_show_records(String string, Integer int1) {
			Customer c = findCustomer(string);
			if (c.getNoShow()!=int1) c.setNoShow(int1); 
			// Write code here that turns the phrase above into concrete actions
		}
		//Y
		@When("{string} makes a {string} appointment for the date {string} and time {string} at {string}")
		public void makes_a_appointment_for_the_date_and_time_at(String string, String string2, String string3, String string4, String string5) {
			// Write code here that turns the phrase above into concrete actions
			try {
				numberOfAppTemp = flexibook.getAppointments().size();
				SystemTime.setSysDateAndTime(string5);
				FlexiBookController.makeAppointment(string, string2, null, string3, string4);
				app = findAppointment(string, string2, string3, string4);
			}
			catch (InvalidInputException e){
				error+=e.getMessage();
				errorCntr++;
			}
		} 
		//Y
		@When("{string} attempts to change the service in the appointment to {string} at {string}")
		public void attempts_to_change_the_service_in_the_appointment_to_at(String string, String string2, String string3) {
			try {
				SystemTime.setSysDateAndTime(string3);
				Customer c = findCustomer(string);
				FlexiBookController.updateAppointment(c.getUsername(), c.getUsername(), app.getBookableService().getName(), app.getTimeSlot().getStartDate().toString(), app.getTimeSlot().getStartTime().toString(), app.getTimeSlot().getStartDate().toString(), app.getTimeSlot().getStartTime().toString(), null, null, true,string2);
			}
			catch(InvalidInputException e) {
				error+=e.getMessage();
				errorCntr++;
			}
			
			// Write code here that turns the phrase above into concrete actions
		}
		//Y
		@Then("the appointment shall be booked")
		public void the_appointment_shall_be_booked() {
			// Write code here that turns the phrase above into concrete actions
			assertEquals(app.getSm(), Appointment.Sm.Booked);
		}
		//Y
		@Then("the service in the appointment shall be {string}")
		public void the_service_in_the_appointment_shall_be(String string) {
			// Write code here that turns the phrase above into concrete actions
			if (app.getBookableService() instanceof Service) {
				assertEquals(app.getBookableService().getName(),string);
			}
		}
		//Y
		@Then("the appointment shall be for the date {string} with start time {string} and end time {string}")
		public void the_appointment_shall_be_for_the_date_with_start_time_and_end_time(String string, String string2, String string3) {
			// Write code here that turns the phrase above into concrete actions
			assertEquals(app.getTimeSlot().getStartDate(),toDate(string));
			assertEquals(app.getTimeSlot().getStartTime(),toTime(string2));
			assertEquals(app.getTimeSlot().getEndTime(),toTime(string3));
		}
		//Y
		@Then("the username associated with the appointment shall be {string}")
		public void the_username_associated_with_the_appointment_shall_be(String string) {
			// Write code here that turns the phrase above into concrete actions
			assertEquals(app.getCustomer().getUsername(), string);
		}
		//Y
		@Then("the user {string} shall have {int} no-show records")
		public void the_user_shall_have_no_show_records(String string, Integer int1) {
			// Write code here that turns the phrase above into concrete actions
			Customer c = findCustomer(string);
			assertEquals(c.getNoShow(),int1);
		}
		//Y
		@Then("the system shall have {int} appointments")
		public void the_system_shall_have_appointments(Integer int1) {
			// Write code here that turns the phrase above into concrete actions
			assertEquals(flexibook.getAppointments().size(),int1);
		}
//		//N
		@When("{string} attempts to update the date to {string} and time to {string} at {string}")
		public void attempts_to_update_the_date_to_and_time_to_at(String string, String string2, String string3, String string4) {
			// Write code here that turns the phrase above into concrete actions
			try {
				SystemTime.setSysDateAndTime(string4);
				Customer c = findCustomer(string);
				FlexiBookController.updateAppointment(c.getUsername(), c.getUsername(), app.getBookableService().getName(), app.getTimeSlot().getStartDate().toString(), app.getTimeSlot().getStartTime().toString(), string2, string3, null, null,false,null);
			}
			catch(InvalidInputException e) {
				error+=e.getMessage();
				errorCntr++;
			}
			
		}
		//Y but wait
		@When("{string} attempts to cancel the appointment at {string}")
		public void attempts_to_cancel_the_appointment_at(String string, String string2) {
			try {
				Customer c = findCustomer(string);
				String s = app.getBookableService().getName();
				String date = string2.substring(0, 10);
				//String time = string2.substring(11, 16);
				//String[] dateAndTime = string2.split("+");
				//String date = dateAndTime[0];
				String startTimeString = app.getTimeSlot().getStartTime().toString();
				//WHICH CANCEL APPOINTMENT OLD OR NEW?
				FlexiBookController.cancelAppointment(c.getUsername(), c.getUsername(), s, date, startTimeString);
			}
			catch (InvalidInputException e){
				error+=e.getMessage();
				errorCntr++;	
			}
			// Write code here that turns the phrase above into concrete actions
		}
//		//N
		@Then("the system shall have {int} appointment")
		public void the_system_shall_have_appointment(Integer int1) {
			// Write code here that turns the phrase above into concrete actions
			assertEquals(flexibook.getAppointments().size(),int1);
		}
//		//N
		@When("{string} makes a {string} appointment without choosing optional services for the date {string} and time {string} at {string}")
		public void makes_a_appointment_without_choosing_optional_services_for_the_date_and_time_at(String string, String string2, String string3, String string4, String string5) {
			try {
				Customer c = findCustomer(string);
				SystemTime.setSysDateAndTime(string5);
				FlexiBookController.makeAppointment(string, string2, null, string3, string4);
				}
				catch(InvalidInputException e) {
					error+=e.getMessage();
					errorCntr++;
				}
			// Write code here that turns the phrase above into concrete actions
			
		}
//		//N
		@When("{string} attempts to add the optional service {string} to the service combo in the appointment at {string}")
		public void attempts_to_add_the_optional_service_to_the_service_combo_in_the_appointment_at(String string, String string2, String string3) {
			try {
				SystemTime.setSysDateAndTime(string3);
				Customer c = findCustomer(string);
				FlexiBookController.updateAppointment(c.getUsername(), c.getUsername(), app.getBookableService().getName(), app.getTimeSlot().getStartDate().toString(), app.getTimeSlot().getStartTime().toString(), null, null, "add", string2,false,null);
			}
			catch(InvalidInputException e) {
				error+=e.getMessage();
				errorCntr++;
			}
			// Write code here that turns the phrase above into concrete actions
		}
//		//N
		@Then("the service combo in the appointment shall be {string}")
		public void the_service_combo_in_the_appointment_shall_be(String string) {
			assertEquals(app.getBookableService().getName(), string);
			// Write code here that turns the phrase above into concrete actions
		}
		//N
		@Then("the service combo shall have {string} selected services")
		public void the_service_combo_shall_have_selected_services(String string) {
			StringBuffer sb = new StringBuffer();
		      for(int i = 0; i < app.getChosenItems().size(); i++) {
		         sb.append(app.getChosenItems().get(i));
		      }
		    String optionalServices = sb.toString();
			assertEquals(optionalServices, string);
			// Write code here that turns the phrase above into concrete actions
		}
		//Y
		@When("the owner starts the appointment at {string}")
		public void the_owner_starts_the_appointment_at(String string) {
			// Write code here that turns the phrase above into concrete actions
			
//			try {
				String date = string.substring(0, 10);
				String time = string.substring(11, 16);
				//Customer c = findCustomer(string);
				
				FlexiBookController.startAppointment(app.getCustomer().getUsername(),app.getBookableService().getName(),date, time);
//			}
//			catch (InvalidInputException e){
//				error+=e.getMessage();
//				errorCntr++;
//			}
			
		}
//		//N
		@When("the owner ends the appointment at {string}")
		public void the_owner_ends_the_appointment_at(String string) {
			String[] dateAndTime = string.split("+");
			FlexiBookController.endAppointment(flexibook.getOwner().getUsername(),app.getBookableService().getName(),dateAndTime[0], dateAndTime[1]);
			// Write code here that turns the phrase above into concrete actions
		}
		//Y 
		@Then("the appointment shall be in progress")
		public void the_appointment_shall_be_in_progress() {
			// Write code here that turns the phrase above into concrete actions
			assertEquals(app.getSm(), Appointment.Sm.InProgress);
		}
		//Y
		@When("the owner attempts to register a no-show for the appointment at {string}")
		public void the_owner_attempts_to_register_a_no_show_for_the_appointment_at(String string) {
			// Write code here that turns the phrase above into concrete actions
			//try {
				String[] dateAndTime = string.split("+");
				Customer c = findCustomer(string);
				
				FlexiBookController.registerNoShow(c.getUsername(),app.getBookableService().getName(), string);
		//	}
//			catch (InvalidInputException e){
//				error+=e.getMessage();
//				errorCntr++;	
//			}
		}
		//Y
		@When("the owner attempts to end the appointment at {string}")
		public void the_owner_attempts_to_end_the_appointment_at(String string) {
//			try {
				String[] dateAndTime = string.split("+");
				FlexiBookController.endAppointment(flexibook.getOwner().getUsername(),app.getBookableService().getName(),dateAndTime[0], dateAndTime[1]);
//			}
//			catch (InvalidInputException e){
//				error+=e.getMessage();
//				errorCntr++;	
//			}
			// Write code here that turns the phrase above into concrete actions
		}




	//----------------------------------------------------------------------------------------------------------	
	//-----------------------------------------------Helper methods---------------------------------------------
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

	/**
	 * Helper method to find a specific service
	 * @author Marc Saber
	 * @param service is the service name.
	 * @return service name if found, null otherwise.
	 */

	private static Service findService(String service) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();

		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof Service) {
				if (aService.getName().equals(service)) return (Service) aService;
			}
		}

		return null;
	}

	/**
	 * Helper method to find a specific bookable service
	 * @author Marc Saber
	 * @param service is the bookable service name.
	 * @return bookable service name if found, null otherwise.
	 */


	private static BookableService findBookableService(String service) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();

		for (BookableService aService : flexibook.getBookableServices()) {

			if (aService.getName().equals(service)) return aService;

		}

		return null;
	}
	/**
	 * Helper method to get the number of service combos
	 * @author Marc Saber
	 */

	private static int getNumServiceCombos() {
		int size = 0;
		for (BookableService S : flexibook.getBookableServices()) {
			if (S instanceof ServiceCombo) size++;
		}
		return size;
	}

	/**
	 * Helper method to get the number of services
	 * @author Marc Saber
	 */

	private static int getNumServices() {
		int size = 0;
		for (BookableService S : flexibook.getBookableServices()) {
			if (S instanceof Service) size++;
		}
		return size;
	}

	/**
	 * Helper method to get the number of appointments for a specific service
	 * @author Marc Saber
	 */

	private static int getNumAppForService(String service) {
		int size = 0;
		for (Appointment app : flexibook.getAppointments()) {
			if(app.getBookableService().getName().equals(service)) size++;
		}
		return size;
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
		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof ServiceCombo) {
				if (aService.getName().equals(serviceCombo) ) return (ServiceCombo) aService;
			}
		}
		return null;
	}

	/**
	 * joinServices
	 * joinServices is a method that has the goal of listing all the services' names in one string by separating them by a ",".
	 * The first step to do it was to create an empty string, then take each service in the service combo and add its name to the end of the string.
	 * If it is the last service, only the name is added to the string of services. 
	 * If it is not the last one, the name is added followed by a ",". 
	 * By doing that for all the services we create a single string having all the names of the services of the given service combo.
	 * The last step is certainly to return this final string.
	 * @author Robert Aprahamian
	 * @param sc is the service combo that has the services we want to have in the form of a string.
	 * @return String that has all the services in the service combo put as the input
	 */
	private static String joinServices(ServiceCombo sc) {
		String namesOfServices = "";
		for (ComboItem i : sc.getServices()) {
			if (sc.getService(sc.getServices().size()-1).equals(i)) {
				namesOfServices = namesOfServices + i.getService().getName();
			}
			else namesOfServices = namesOfServices + i.getService().getName() + ",";
		}
		return namesOfServices;
	}


	/**
	 * joinMandatories
	 * joinMandatories is a method that has the goal of listing all the services' mandatory statuses in one string by separating them by a ",".
	 * The first step to do it was to create an empty string, then take each service in the service combo and add its mandatory status to the end of the string.
	 * If it is the last service, only the mandatory status is added to the string of . 
	 * If it is not the last one, the mandatory status is added followed by a ",". 
	 * By doing that for all the services we create a single string having all the mandatory statuses of the services of the given service combo.
	 * The last step is certainly to return this final string.
	 * @author Robert Aprahamian
	 * @param sc is the service combo that has the services we want to find their corresponding mandatory status and have them in the form of a string.
	 * @return String that has all the mandatory status of all the services in the service combo put as the input.
	 */

	private static String joinMandatories(ServiceCombo sc) {
		String mandatories = "";
		for (ComboItem i : sc.getServices()) {
			if (sc.getService(sc.getServices().size()-1).equals(i)) {
				if (i.getMandatory()==true) mandatories = mandatories + "true";
				else mandatories = mandatories + "false";	
			}
			else if (i.getMandatory()==true) mandatories = mandatories + "true" + ",";
			else mandatories = mandatories + "false" + ",";
		}
		return mandatories;
	}




	//-----------------------------------------------------------------------------------------------------------------------------------------------


	/**
	 * The findTimeSlotOfApp is a helper method that calculates the time slot of an appointment
	 * depending on the start time, start date and duration of the appointment.
	 * @author Tamara Zard Aboujaoudeh
	 * @param serviceName
	 * @param optServicesString
	 * @param date
	 * @param startTimeString
	 * @return
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
	 * The findAppointment method is a helper method that finds the desired appointment in the flexibook
	 * application using the username of the customer, and his appointment information(name, date, start 
	 * time).
	 * @author Tamara Zard Aboujaoudeh
	 * @param username
	 * @param appName
	 * @param dateString
	 * @param startTimeString
	 * @return
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




}
