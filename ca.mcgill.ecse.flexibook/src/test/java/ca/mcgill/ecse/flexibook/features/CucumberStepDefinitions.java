package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {


	private FlexiBook flexibook;
	private String error;
	int errorCntr;

	private int AccountCntrBeforeCreation;
	private String oldUsername;
	private String oldPassword;
	private List<Appointment> oldAppointments;


	@Before
	public void setup() {
		FlexiBookApplication.setCurrentUser(null);
		FlexiBookApplication.getFlexibook().delete();
		AccountCntrBeforeCreation = 0;
	}


	@Given("a Flexibook system exists")
	public void a_flexibook_system_exists() {
		flexibook = FlexiBookApplication.getFlexibook();
		error = "";
		errorCntr = 0;
	}

	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			flexibook.addCustomer(columns.get("username"), columns.get("password"));

		}
	}

	@When("the user tries to log in with username {string} and password {string}")
	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {
		AccountCntrBeforeCreation = flexibook.getCustomers().size();

		try {

			FlexiBookController.login(string, string2);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}

	}

	@Then("the user should be successfully logged in")
	public void the_user_should_be_successfully_logged_in() {


		assertEquals(FlexiBookApplication.getCurrentUser(), flexibook.getCustomer(0));

	}

	@Then("the user should not be logged in")
	public void the_user_should_not_be_logged_in() {
		assertEquals(FlexiBookApplication.getCurrentUser(), null);			
	}

	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String string) {
		assertTrue(error.contains(string));
	}

	@Then("a new account shall be created")
	public void a_new_account_shall_be_created() {
		if (flexibook.getOwner() == null) {
			assertEquals(flexibook.getCustomers().size(), AccountCntrBeforeCreation + 1);
		}
		else {
			assertEquals(flexibook.getCustomers().size() + 1, AccountCntrBeforeCreation + 1);

		}
	}

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

	@Then("the user shall be successfully logged in")
	public void the_user_shall_be_successfully_logged_in() {
		assertEquals(FlexiBookApplication.getCurrentUser(), flexibook.getOwner());
	}

	@Given("an owner account exists in the system with username {string} and password {string}")
	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
		Owner owner = new Owner (string, string2, flexibook);	    
	}

	@Given("the user is logged out")
	public void the_user_is_logged_out() {
		FlexiBookApplication.setCurrentUser(null);			
	}

	@When("the user tries to log out")
	public void the_user_tries_to_log_out() {
		try {

			FlexiBookController.logout();

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}
	}


	@Given("the user is logged in to an account with username {string}")
	public void the_user_is_logged_in_to_an_account_with_username(String string) {
		FlexiBookApplication.setCurrentUser(findUser(string));

	}

	@Then("the user shall be logged out")
	public void the_user_shall_be_logged_out() {
		assertEquals(FlexiBookApplication.getCurrentUser(), null);
	}

	@Given("the system's time and date is {string}")
	public void the_system_s_time_and_date_is(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("an owner account exists in the system")
	public void an_owner_account_exists_in_the_system() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("a business exists in the system")
	public void a_business_exists_in_the_system() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("the following services exist in the system:")
	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Given("the following service combos exist in the system:")
	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Given("the business has the following opening hours:")
	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Given("the business has the following holidays:")
	public void the_business_has_the_following_holidays(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Given("the following appointments exist in the system:")
	public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Given("{string} is logged in to their account")
	public void is_logged_in_to_their_account(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("{string} requests the appointment calendar for the week starting on {string}")
	public void requests_the_appointment_calendar_for_the_week_starting_on(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the following slots shall be unavailable:")
	public void the_following_slots_shall_be_unavailable(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Then("the following slots shall be available:")
	public void the_following_slots_shall_be_available(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@When("{string} requests the appointment calendar for the day of {string}")
	public void requests_the_appointment_calendar_for_the_day_of(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the system shall report {string}")
	public void the_system_shall_report(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@After
	public void tearDown() {
		FlexiBookApplication.setCurrentUser(null);
		flexibook.delete();
	}

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

}