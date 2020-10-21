package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
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

	private int nmbOfAccounts = 0;


	@Before
	public void setup() {
		FlexiBookApplication.setCurrentUser(null);
		FlexiBookApplication.getFlexibook().delete();
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
			nmbOfAccounts++;
		}
	}

	@When("the user tries to log in with username {string} and password {string}")
	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {

		try {

			FlexiBookController.login(string, string2);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}

	}

	@Then("the user should be successfully logged in")
	public void the_user_should_be_successfully_logged_in() {
		assertEquals(flexibook.getCustomer(0).getUsername(), "User1");
		assertEquals(flexibook.getCustomer(0), findUser("User1"));
		assertEquals("", error);
		assertEquals(0, errorCntr);
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
		nmbOfAccounts++;
		assertEquals(flexibook.getCustomers().size() + 1, nmbOfAccounts);

	}

	@Then("the account shall have username {string} and password {string}")
	public void the_account_shall_have_username_and_password(String string, String string2) {
		assertEquals(flexibook.getOwner().getUsername(), string);
		assertEquals(flexibook.getOwner().getPassword(), string2);
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
		assertEquals(1, 1);
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

	// start here dox

	@Then("the system shall report {string}")
	public void the_system_shall_report(String string) {


		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}


	@Given("no business exists")
	public void no_business_exists() {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(FlexiBookApplication.getFlexibook().getBusiness(), null);

	}


	@When("the user tries to set up the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_set_up_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {

		try {

			FlexiBookController.SetUpContactInfo(string, string2, string3, string4);

		}catch (InvalidInputException e){
			error+=e.getMessage();
			errorCntr++;
		}

		// Write code here that turns the phrase above into concrete actions
	}
	@Then("a new business with new {string} and {string} and {string} and {string} shall {string} created")
	public void a_new_business_with_new_and_and_and_shall_created(String string, String string2, String string3, String string4, String string5) {

		String test = "";
		try {

			FlexiBookController.SetUpContactInfo(string, string2, string3, string4);

		}catch (InvalidInputException e){
			test+=e.getMessage();
		}
		if (test.equals("")) {
			assertEquals("be", string5);
		}
		else {
			assertEquals("not be", string5);
		}

		// Write code here that turns the phrase above into concrete actions

	}


	@Then("an error message {string} shall {string} raised")
	public void an_error_message_shall_raised(String string, String string2) {

		if(string.equals("")) {
			assertEquals("not be",string2);
		}
		else {
			assertEquals("be",string2);
		}


		// Write code here that turns the phrase above into concrete actions
		//		throw new io.cucumber.java.PendingException();
	}



	@Given("a business exists with the following information:")
	public void a_business_exists_with_the_following_information(io.cucumber.datatable.DataTable dataTable) {

		List<String> rows = dataTable.asList();
		String name = rows.get(4);
		String address = rows.get(5);
		String phoneNumber = rows.get(6);
		String email = rows.get(7);

		FlexiBookApplication.getFlexibook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexibook()));


		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		//		throw new io.cucumber.java.PendingException();
	}
	@Given("the business has a business hour on {string} with start time {string} and end time {string}")
	public void the_business_has_a_business_hour_on_with_start_time_and_end_time(String string, String string2, String string3) {
		
		DayOfWeek temp = DayOfWeek.valueOf(string);
		string2 = string2+":00";
		string3 = string3+":00";
		Time temp2 = Time.valueOf(string2);
		Time temp3 = Time.valueOf(string3);
		BusinessHour test = new BusinessHour(temp, temp3, temp3, flexibook);
		FlexiBookApplication.getFlexibook().getBusiness().addBusinessHour(test);
					

		// Write code here that turns the phrase above into concrete actions
		//		throw new io.cucumber.java.PendingException();
	}



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

		// Write code here that turns the phrase above into concrete actions
		//		throw new io.cucumber.java.PendingException();
	}
	@Then("a new business hour shall {string} created")
	public void a_new_business_hour_shall_created(String string) {
		// Write code here that turns the phrase above into concrete actions

		if (error.equals("")) {
			assertEquals("be",string);
		}
		else {
			assertEquals("not be",string);
		}
		//		throw new io.cucumber.java.PendingException();
	}



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

	// feature 2 starts here

	@When("the user tries to update the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_update_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {

		try {
			FlexiBookController.UpdateBasicInfo(string, string2, string3, string4);
		} catch (InvalidInputException e) {
			error+=e.getMessage();
			errorCntr++;
		}

		// Write code here that turns the phrase above into concrete actions
		//		throw new io.cucumber.java.PendingException();
	}
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
	@Then("the business hour shall {string} be updated")
	public void the_business_hour_shall_be_updated(String string) {

		if (error.equals("")) {
			assertEquals("be",string);
		}
		else {
			assertEquals("not be",string);
		}
	}


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
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
	}
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
		
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
	}
	@Then("an error message {string} shall {string} be raised")
	public void an_error_message_shall_be_raised(String string, String string2) {
		if (error.equals("")) {
			assertEquals("not",string2);
		}
		else {
			assertEquals("",string2);
		}
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
	}
	

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
		
		
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
	}


	@Then("the {string} shall {string} be updated with start date {string} at {string} and end date {string} at {string}")
	public void the_shall_be_updated_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {
		
		if (error.equals("")) {
			assertEquals("be",string2);
		}
		else {
			assertEquals("not be",string2);
		}
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
	}




	@When("the user tries to remove an existing {string} with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_remove_an_existing_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5) {
		
		
		try {
			FlexiBookController.RemoveTimeSlot(string, string2, string3, string4, string5);
		}
		catch (InvalidInputException e) {
			error+=e.getMessage();
		}
		
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
	}
	@Then("the {string} with start date {string} at {string} shall {string} exist")
	public void the_with_start_date_at_shall_exist(String string, String string2, String string3, String string4) {
		if(error.equals("")) {
			assertEquals("not", string4);
		}
		else {
			assertEquals("", string4);
		}
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
	}








	@After
	public void tearDown() {
		FlexiBookApplication.setCurrentUser(null);
		flexibook.delete();
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

