package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.User;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;

//---------
public class CucumberStepDefinitions {


	private FlexiBook flexibook;
	private String error;
	int errorCntr;
	private int AccountCntrBeforeCreation;
	
	@Before
	public void setup() {
		FlexiBookApplication.setCurrentUser(null);
		FlexiBookApplication.getFlexibook().delete();
		AccountCntrBeforeCreation = 0;
	}
//	@Given("a Flexibook system exists")
//	public void a_flexibook_system_exists() {
//		flexibook = FlexiBookApplication.getFlexibook();
//		error = "";
//		errorCntr = 0;
//	}
//	@Given("the following customers exist in the system:")
//	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//		for (Map<String, String> columns : rows) {
//			flexibook.addCustomer(columns.get("username"), columns.get("password"));
//		
//		}
//	}
//	@When("the user tries to log in with username {string} and password {string}")
//	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {
//		AccountCntrBeforeCreation = flexibook.getCustomers().size();
//		try {
//			FlexiBookController.login(string, string2);
//		}catch (InvalidInputException e){
//			error+=e.getMessage();
//			errorCntr++;
//		}
//	}
//	@Then("the user should be successfully logged in")
//	public void the_user_should_be_successfully_logged_in() {
//		assertEquals(FlexiBookApplication.getCurrentUser(), flexibook.getCustomer(0));
//	}
//	@Then("the user should not be logged in")
//	public void the_user_should_not_be_logged_in() {
//		assertEquals(FlexiBookApplication.getCurrentUser(), null);			
//	}
//	@Then("an error message {string} shall be raised")
//	public void an_error_message_shall_be_raised(String string) {
//		assertTrue(error.contains(string));
//	}
//	@Then("a new account shall be created")
//	public void a_new_account_shall_be_created() {
//		if (flexibook.getOwner() == null) {
//			assertEquals(flexibook.getCustomers().size(), AccountCntrBeforeCreation + 1);
//		}
//		else {
//			assertEquals(flexibook.getCustomers().size() + 1, AccountCntrBeforeCreation + 1);
//		}
//	}

//	@Then("the account shall have username {string} and password {string}")
//	public void the_account_shall_have_username_and_password(String string, String string2) {
//		if (string.equals("owner")) {
//			assertEquals(flexibook.getOwner().getUsername(), string);
//			assertEquals(flexibook.getOwner().getPassword(), string2);
//		}else {
//			assertEquals(flexibook.getCustomer(flexibook.getCustomers().size()-1).getUsername(), string);
//			assertEquals(flexibook.getCustomer(flexibook.getCustomers().size()-1).getPassword(), string2);
//		}
////		if (string.equals("owner")) {
////			assertEquals(flexibook.getOwner().getUsername(), string);
////			assertEquals(flexibook.getOwner().getPassword(), string2);
////		}else {
////			assertEquals(flexibook.getCustomer(flexibook.getCustomers().size()-1).getUsername(), string);
////			assertEquals(flexibook.getCustomer(flexibook.getCustomers().size()-1).getPassword(), string2);
////		}
//
//		assertEquals(string, FlexiBookApplication.getCurrentUser().getUsername());
//		assertEquals(string2, FlexiBookApplication.getCurrentUser().getPassword());
//	}

//	@Then("the user shall be successfully logged in")
//	public void the_user_shall_be_successfully_logged_in() {
//		assertEquals(FlexiBookApplication.getCurrentUser(), flexibook.getOwner());
//	}
//	@Given("an owner account exists in the system with username {string} and password {string}")
//	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
//		Owner owner = new Owner (string, string2, flexibook);	    
//	}
//	@Given("the user is logged out")
//	public void the_user_is_logged_out() {
//		FlexiBookApplication.setCurrentUser(null);			
//	}
//	@When("the user tries to log out")
//	public void the_user_tries_to_log_out() {
//		try {
//			FlexiBookController.logout();
//		}catch (InvalidInputException e){
//			error+=e.getMessage();
//			errorCntr++;
//		}
//	}
//	@Given("the user is logged in to an account with username {string}")
//	public void the_user_is_logged_in_to_an_account_with_username(String string) {
//		FlexiBookApplication.setCurrentUser(findUser(string));
//	}
//	@Then("the user shall be logged out")
//	public void the_user_shall_be_logged_out() {
//		assertEquals(FlexiBookApplication.getCurrentUser(), null);
//	}
//	@Given("the system's time and date is {string}")
//	public void the_system_s_time_and_date_is(String string) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//	//cwR
//	@Given("an owner account exists in the system")
//	public void an_owner_account_exists_in_the_system() {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//	//cwR
//	@Given("a business exists in the system")
//	public void a_business_exists_in_the_system() {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//	//cwR
//	@Given("the following services exist in the system:")
//	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		// Write code here that turns the phrase above into concrete actions
//		// For automatic transformation, change DataTable to one of
//		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//		// Double, Byte, Short, Long, BigInteger or BigDecimal.
//		//
//		// For other transformations you can register a DataTableType.
//		throw new io.cucumber.java.PendingException();
//	}
//	//cwr
//	@Given("the following service combos exist in the system:")
//	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		// Write code here that turns the phrase above into concrete actions
//		// For automatic transformation, change DataTable to one of
//		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//		// Double, Byte, Short, Long, BigInteger or BigDecimal.
//		//
//		// For other transformations you can register a DataTableType.
//		throw new io.cucumber.java.PendingException();
//	}
//	@Given("the business has the following opening hours:")
//	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
//		// Write code here that turns the phrase above into concrete actions
//		// For automatic transformation, change DataTable to one of
//		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//		// Double, Byte, Short, Long, BigInteger or BigDecimal.
//		//
//		// For other transformations you can register a DataTableType.
//		throw new io.cucumber.java.PendingException();
//	}
//	@Given("the business has the following holidays:")
//	public void the_business_has_the_following_holidays(io.cucumber.datatable.DataTable dataTable) {
//		// Write code here that turns the phrase above into concrete actions
//		// For automatic transformation, change DataTable to one of
//		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//		// Double, Byte, Short, Long, BigInteger or BigDecimal.
//		//
//		// For other transformations you can register a DataTableType.
//		throw new io.cucumber.java.PendingException();
//	}
	//cwR
//	@Given("the following appointments exist in the system:")
//	public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		// Write code here that turns the phrase above into concrete actions
//		// For automatic transformation, change DataTable to one of
//		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//		// Double, Byte, Short, Long, BigInteger or BigDecimal.
//		//
//		// For other transformations you can register a DataTableType.
//		throw new io.cucumber.java.PendingException();
//	}
//	@Given("{string} is logged in to their account")
//	public void is_logged_in_to_their_account(String string) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//	@When("{string} requests the appointment calendar for the week starting on {string}")
//	public void requests_the_appointment_calendar_for_the_week_starting_on(String string, String string2) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//	@Then("the following slots shall be unavailable:")
//	public void the_following_slots_shall_be_unavailable(io.cucumber.datatable.DataTable dataTable) {
//		// Write code here that turns the phrase above into concrete actions
//		// For automatic transformation, change DataTable to one of
//		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//		// Double, Byte, Short, Long, BigInteger or BigDecimal.
//		//
//		// For other transformations you can register a DataTableType.
//		throw new io.cucumber.java.PendingException();
//	}
//	@Then("the following slots shall be available:")
//	public void the_following_slots_shall_be_available(io.cucumber.datatable.DataTable dataTable) {
//		// Write code here that turns the phrase above into concrete actions
//		// For automatic transformation, change DataTable to one of
//		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//		// Double, Byte, Short, Long, BigInteger or BigDecimal.
//		//
//		// For other transformations you can register a DataTableType.
//		throw new io.cucumber.java.PendingException();
//	}
//	@When("{string} requests the appointment calendar for the day of {string}")
//	public void requests_the_appointment_calendar_for_the_day_of(String string, String string2) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//	@Then("the system shall report {string}")
//	public void the_system_shall_report(String string) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}

//	private static User findUser(String username) {
//		User foundUser = null;
//		if (username.equals("owner")) {
//			Owner owner = FlexiBookApplication.getFlexibook().getOwner();
//			foundUser = owner;
//			return foundUser;
//		}
//		else {
//			for (Customer customer : FlexiBookApplication.getFlexibook().getCustomers()) {
//				if (customer.getUsername().equals(username)) {
//					foundUser = customer;
//					//return foundUser;
//				}
//			}
//		}
//		return foundUser;
//	}
// --------------------------------------------------LAB Ta7t el khat da fo2ih rico---------------------------------------
		@Given("a Flexibook system exists")
		public void a_flexibook_system_exists() {
		    // Write code here that turns the phrase above into concrete actions
			flexibook = FlexiBookApplication.getFlexibook();
			error = "";
			errorCntr = 0;
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
		@Given("the Owner with username {string} is logged in")
		public void the_owner_with_username_is_logged_in(String string) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@When("{string} initiates the definition of a service combo {string} with main service {string}, services {string} and mandatory setting {string}")
		public void initiates_the_definition_of_a_service_combo_with_main_service_services_and_mandatory_setting(String string, String string2, String string3, String string4, String string5) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the service combo {string} shall exist in the system")
		public void the_service_combo_shall_exist_in_the_system(String string) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the service combo {string} shall contain the services {string} with mandatory setting {string}")
		public void the_service_combo_shall_contain_the_services_with_mandatory_setting(String string, String string2, String string3) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the main service of the service combo {string} shall be {string}")
		public void the_main_service_of_the_service_combo_shall_be(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the service {string} in service combo {string} shall be mandatory")
		public void the_service_in_service_combo_shall_be_mandatory(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the number of service combos in the system shall be {string}")
		public void the_number_of_service_combos_in_the_system_shall_be(String string) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
//--------------------------------------------

			
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
			
			@Then("an error message with content {string} shall be raised")
			public void an_error_message_with_content_shall_be_raised(String string) {
			    // Write code here that turns the phrase above into concrete actions
			    throw new io.cucumber.java.PendingException();
			}
			@Then("the service combo {string} shall not exist in the system")
			public void the_service_combo_shall_not_exist_in_the_system(String string) {
			    // Write code here that turns the phrase above into concrete actions
			    throw new io.cucumber.java.PendingException();
			}
//-----------------------------------------------
			
				@Then("the service combo {string} shall preserve the following properties:")
				public void the_service_combo_shall_preserve_the_following_properties(String string, io.cucumber.datatable.DataTable dataTable) {
				    // Write code here that turns the phrase above into concrete actions
				    // For automatic transformation, change DataTable to one of
				    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
				    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
				    // Double, Byte, Short, Long, BigInteger or BigDecimal.
				    //
				    // For other transformations you can register a DataTableType.
				    throw new io.cucumber.java.PendingException();
				}
//-------------------------------------------------------------------------
			
					@Given("the following customers exist in the system:")
					public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
					    // Write code here that turns the phrase above into concrete actions
					    // For automatic transformation, change DataTable to one of
					    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
					    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
					    // Double, Byte, Short, Long, BigInteger or BigDecimal.
					    //
					    // For other transformations you can register a DataTableType.
						List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
						for (Map<String, String> columns : rows) {
							flexibook.addCustomer(columns.get("username"), columns.get("password"));
						
						}
					    throw new io.cucumber.java.PendingException();
					}
					
		@Given("Customer with username {string} is logged in")
					public void customer_with_username_is_logged_in(String string) {
					    // Write code here that turns the phrase above into concrete actions
					    throw new io.cucumber.java.PendingException();
		}
//----------------------------
//-----------------------------
// Delete SC

		
//----------------------------
//----------------------------
// Update SC
		
		@When("{string} initiates the update of service combo {string} to name {string}, main service {string} and services {string} and mandatory setting {string}")
		public void initiates_the_update_of_service_combo_to_name_main_service_and_services_and_mandatory_setting(String string, String string2, String string3, String string4, String string5, String string6) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the service combo {string} shall be updated to name {string}")
		public void the_service_combo_shall_be_updated_to_name(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
//------------------------
//nothing new for update, invalid parameters
//---------------------
//nothing new for update, unauthorized
//-----------------------
//--------------------------
// Delete SC

	
		@Given("the system's time and date is {string}")
		public void the_system_s_time_and_date_is(String string) {
		    // Write code here that turns the phrase above into concrete actions
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
		
		@When("{string} initiates the deletion of service combo {string}")
		public void initiates_the_deletion_of_service_combo(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		
		
		@Then("the number of appointments in the system with service {string} shall be {string}")
		public void the_number_of_appointments_in_the_system_with_service_shall_be(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the number of appointments in the system shall be {string}")
		public void the_number_of_appointments_in_the_system_shall_be(String string) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}

//-------------------------------
//nothing new for delete, with future apptmts
//----------------------------
//nothing new for delete, unauthorized attempt to delete sc	

		@After
		public void tearDown() {
			FlexiBookApplication.setCurrentUser(null);
			flexibook.delete();
		} 
}

