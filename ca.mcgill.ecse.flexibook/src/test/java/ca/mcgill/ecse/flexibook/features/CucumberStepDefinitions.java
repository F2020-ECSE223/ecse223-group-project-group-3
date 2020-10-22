  package ca.mcgill.ecse.flexibook.features;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
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
		assertEquals(string, FlexiBookApplication.getCurrentUser().getUsername());
		assertEquals(string2, FlexiBookApplication.getCurrentUser().getPassword());
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
		String temp1 = string.substring(0, 10);
		String temp2 = string.substring(11, 16);
		temp2 = temp2+":00";
		Time time = Time.valueOf(temp2);
		Date date = Date.valueOf(temp1);
		SystemTime.setSysDate(date);
		SystemTime.setSysTime(time);
	}
	@Given("an owner account exists in the system")
	public void an_owner_account_exists_in_the_system() {
		if(flexibook.getOwner() == null) {
			Owner owner = new Owner("ownerUsername", "ownerPassword", flexibook);
			FlexiBookApplication.getFlexibook().setOwner(owner);
		}
	}
	
	@Given("a business exists in the system")
	public void a_business_exists_in_the_system() {
		if(flexibook.getBusiness() == null) {
			Business business = new Business ("businessName","location","phoneNumber","email", flexibook);
			FlexiBookApplication.getFlexibook().setBusiness(business);	
			}
		//flexibook.setBusiness(new Business("businessName","location","phoneNumber","email",flexibook));
	//	FlexiBookApplication.getFlexibook().setBusiness(aNewBusiness)
	}
	
	
	
	
	@Given("the following services exist in the system:")
	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable){
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> columns : rows) {
			Service service = new Service(columns.get("name"), flexibook, 
					Integer.parseInt(columns.get("duration")), 
					Integer.parseInt(columns.get("downtimeStart")),
					Integer.parseInt(columns.get("downtimeDuration")));
			flexibook.addBookableService(service);
		}
	}
		

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

			for (int i = 0; i<listOfService.size(); i++) {
				for(int j = 0; j<booleans.length;j++) {
					if (i == j) {
						Service service = findService(listOfService.get(i));
						boolean isMandatory = false;
						if (booleans[j].equals("true")) isMandatory = true;
						ComboItem item = new ComboItem(isMandatory, service, combo);
						break;
					}
				}

			}
			flexibook.addBookableService(combo);
		}
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
		
		

		@Given("the Owner with username {string} is logged in")
		public void the_owner_with_username_is_logged_in(String username) {
			if (username.equals("owner")) {
				FlexiBookApplication.setCurrentUser(findUser(username));
			}
				
		}
		
		@When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
		public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration
		(String serviceName, String duration,String downtimeDuration,String downtimeStart,String user) {
			try {  
				
			FlexiBookController.addService(serviceName,Integer.parseInt(duration),
					Integer.parseInt(downtimeDuration),Integer.parseInt(downtimeStart), user);
			}
		
		catch (InvalidInputException e) {
			error = e.getMessage();
		
			errorCntr++;
		}
	}
		@Then("the service {string} shall exist in the system")
		public void the_service_shall_exist_in_the_system(String string) {
			 for(BookableService b: flexibook.getBookableServices()){
		          b.getName();
		          assertTrue(b.getName().equals(string));
		 
		            }
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the service {string} shall have duration {string}, start of down time {string} and down time duration {string}")
		public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("the number of services in the system shall be {string}")
		public void the_number_of_services_in_the_system_shall_be(String string) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}

		

		
			@Then("an error message with content {string} shall be raised")
			public void an_error_message_with_content_shall_be_raised(String username) {
				if (username.equals("customer")) {
					FlexiBookApplication.setCurrentUser(findCustomer(username));
				
			    // Write code here that turns the phrase above into concrete actions
				}
			}
			@Then("the service {string} shall not exist in the system")
			public void the_service_shall_not_exist_in_the_system(String string) {
			    // Write code here that turns the phrase above into concrete actions
			    throw new io.cucumber.java.PendingException();
			}
		
		
			
				
				@Then("the service {string} shall still preserve the following properties:")
				public void the_service_shall_still_preserve_the_following_properties(String string, io.cucumber.datatable.DataTable dataTable) {
				    // Write code here that turns the phrase above into concrete actions
				    // For automatic transformation, change DataTable to one of
				    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
				    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
				    // Double, Byte, Short, Long, BigInteger or BigDecimal.
				    //
				    // For other transformations you can register a DataTableType.
				    throw new io.cucumber.java.PendingException();
				}
				


	
		
					
					@Given("Customer with username {string} is logged in")
					public void customer_with_username_is_logged_in(String string) {
					    // Write code here that turns the phrase above into concrete actions
					    throw new io.cucumber.java.PendingException();
					}
				
				
					
					@When("{string} initiates the deletion of service {string}")
					public void initiates_the_deletion_of_service(String service,String username) {
						try{
						//	if(flexibook.getOwner().getUsername() == username) {
				            FlexiBookController.deleteService(service, username);
						//	}
						}													
				        catch (InvalidInputException e){
				            error += e.getMessage();
				            errorCntr++;
				        }
						 throw new io.cucumber.java.PendingException();
					}
					
					@Then("the number of appointments in the system with service {string} shall be {string}")
					public void the_number_of_appointments_in_the_system_with_service_shall_be
					(String number, String name) {
						
					//	assertEquals(flexibook.getAppointments().contains(name),0);
						}
					
					
					@Then("the number of appointments in the system shall be {string}")
					public void the_number_of_appointments_in_the_system_shall_be(String string)
					{
						//assertEquals(,);
						
					    // Write code here that turns the phrase above into concrete actions
					    throw new io.cucumber.java.PendingException();
					}
		
						
						@Then("the service combos {string} shall not exist in the system")
						public void the_service_combos_shall_not_exist_in_the_system(String string) {
						    
							
							
							// Write code here that turns the phrase above into concrete actions
						    throw new io.cucumber.java.PendingException();
						}
						@Then("the service combos {string} shall not contain service {string}")
						public void the_service_combos_shall_not_contain_service(String string, String string2) {
						    // Write code here that turns the phrase above into concrete actions
						    throw new io.cucumber.java.PendingException();
						}
						@Then("the number of service combos in the system shall be {string}")
						public void the_number_of_service_combos_in_the_system_shall_be(String string) {
						}
	
				
						
					@When("{string} initiates the update of the service {string} to name {string}, duration {string}, "
							+ "start of down time {string} and down time duration {string}")
			    	public void initiates_the_update_of_the_service_to_name_duration_start_of_down_time_and_down_time_duration
			    	(String service, String duration,String downtimeDuration,String downtimeStart, String user, String serviceName) throws InvalidInputException {
						try {
						FlexiBookController.updateService(service, Integer.parseInt(duration),
								Integer.parseInt(downtimeDuration), Integer.parseInt(downtimeStart), user, serviceName);											
					}
						catch (InvalidInputException e){
				            error += e.getMessage();
				            errorCntr++;
				        }
					}
				
					@Then("the service {string} shall be updated to name {string}, duration {string}, start of down time {string} and down time duration {string}")
					public void the_service_shall_be_updated_to_name_duration_start_of_down_time_and_down_time_duration
					(String service, int duration,int downtimeDuration, int downtimeStart, String user, String serviceName) {											
								 
						Service S = (Service) BookableService.getWithName(service);
						   assertEquals(S.getName(),(serviceName)); 
						   assertEquals(S.getDuration(),duration);
						   assertEquals(S.getDowntimeDuration() ,downtimeDuration);
						   assertEquals(S.getDowntimeStart() ,downtimeStart);							   
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

			}
		}

	}
	return foundUser;
}	

private static Customer findCustomer(String username) {
	Customer foundCustomer = null;
	for (Customer customer : FlexiBookApplication.getFlexibook().getCustomers()) {
		if (customer.getUsername().equals(username)) {
			foundCustomer = customer;

		}
	}
	return foundCustomer;
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

private static BookableService findBookableService(String service) {
	FlexiBook flexibook = FlexiBookApplication.getFlexibook();

	for (BookableService aService : flexibook.getBookableServices()) {

		if (aService.getName().equals(service)) return aService;

	}

	return null;
}

private static Time toTime(String t) {
	String[] tArray = t.split(":");
	int[] intArray = new int[2];
	intArray[0] = Integer.parseInt(tArray[0]);
	intArray[1] = Integer.parseInt(tArray[1]);
	LocalTime localTime = LocalTime.of(intArray[0], intArray[1]);
	return Time.valueOf(localTime);

}

private static Date toDate(String d) {
	String[] dArray = d.split("-");
	int[] intArray = new int[3];
	intArray[0] = Integer.parseInt(dArray[0]);
	intArray[1] = Integer.parseInt(dArray[1]);
	intArray[2] = Integer.parseInt(dArray[2]);


	LocalDate localDate = LocalDate.of(intArray[0], intArray[1], intArray[2]);
	return Date.valueOf(localDate);

}

}
	
			