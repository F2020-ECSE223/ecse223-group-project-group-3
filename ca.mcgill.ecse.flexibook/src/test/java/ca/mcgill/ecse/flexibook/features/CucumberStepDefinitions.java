package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {

	// TODO you can add step implementations here

		@Given("a Flexibook system exists")
		public void a_flexibook_system_exists() {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}

		@Given("the following customers exist in the system:")
		public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		    // Double, Byte, Short, Long, BigInteger or BigDecimal.
		    //
		    // For other transformations you can register a DataTableType.
		    throw new io.cucumber.java.PendingException();
		}
		
		@When("the user tries to log in with username {string} and password {string}")
		public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		
		@Then("the user should be successfully logged in")
		public void the_user_should_be_successfully_logged_in() {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}

		@Then("the user should not be logged in")
		public void the_user_should_not_be_logged_in() {
			// Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
		}
		
		@Then("an error message {string} shall be raised")
		public void an_error_message_shall_be_raised(String string) {
		    // Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
		}

		@Then("a new account shall be created")
		public void a_new_account_shall_be_created() {
		    // Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
		}
		
		@Then("the account shall have username {string} and password {string}")
		public void the_account_shall_have_username_and_password(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
		}
		
		@Then("the user shall be successfully logged in")
		public void the_user_shall_be_successfully_logged_in() {
		    // Write code here that turns the phrase above into concrete actions
			throw new io.cucumber.java.PendingException();
		}
		
		@Given("an owner account exists in the system with username {string} and password {string}")
		public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		
		@Given("the user is logged out")
		public void the_user_is_logged_out() {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		
		@When("the user tries to log out")
		public void the_user_tries_to_log_out() {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		
		
		@Given("the user is logged in to an account with username {string}")
		public void the_user_is_logged_in_to_an_account_with_username(String string) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		
		@Then("the user shall be logged out")
		public void the_user_shall_be_logged_out() {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
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

}

