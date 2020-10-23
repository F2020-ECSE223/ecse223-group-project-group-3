package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;

/**
 * 
 * @author Robert Aprahamian
 *
 */

public class CucumberStepDefinitions {

	private static FlexiBook flexibook;
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
		    // Write code here that turns the phrase above into concrete actions
			flexibook = FlexiBookApplication.getFlexibook();
			error = "";
			errorCntr = 0;
		}

		@Given("an owner account exists in the system")
		public void an_owner_account_exists_in_the_system() {
			if(FlexiBookApplication.getFlexibook().getOwner()==null) {
				new Owner("owner", "owner", flexibook);	
			}
		    // Write code here that turns the phrase above into concrete actions
		}
		@Given("a business exists in the system")
		public void a_business_exists_in_the_system() {
		    // Write code here that turns the phrase above into concrete actions
			if(flexibook.getBusiness()==null) {
				new Business("Busy Diner", "123 New Str", "(514)987-6543", "busy@gmail.com", flexibook);
			}
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
			List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

			for (Map<String, String> columns : rows) {
				new Service(columns.get("name"), flexibook, 
						Integer.parseInt(columns.get("duration")), 
						Integer.parseInt(columns.get("downtimeStart")),
						Integer.parseInt(columns.get("downtimeDuration")));
			}
		}
		@Given("the Owner with username {string} is logged in")
		public void the_owner_with_username_is_logged_in(String string) {
			if (flexibook.getOwner()!=null) {
				flexibook.getOwner().setUsername(string);
			}
			else {
				new Owner(string, "owner", flexibook);
			}
				FlexiBookApplication.setCurrentUser(flexibook.getOwner());
		    // Write code here that turns the phrase above into concrete actions
		}
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
		@Then("the service combo {string} shall exist in the system")
		public void the_service_combo_shall_exist_in_the_system(String string) {
			assertEquals(findServiceCombo(string).getName(), string);
		    // Write code here that turns the phrase above into concrete actions
		}
		@Then("the service combo {string} shall contain the services {string} with mandatory setting {string}")
		public void the_service_combo_shall_contain_the_services_with_mandatory_setting(String string, String string2, String string3) {
			ServiceCombo sc = findServiceCombo(string);
			assertEquals(joinServices(sc),string2);
			assertEquals(joinMandatories(sc),string3);
		    // Write code here that turns the phrase above into concrete actions
		}
		
		
		@Then("the main service of the service combo {string} shall be {string}")
		public void the_main_service_of_the_service_combo_shall_be(String string, String string2) {
			assertEquals(findServiceCombo(string).getMainService().getService().getName(),string2);
		    // Write code here that turns the phrase above into concrete actions
		}
		@Then("the service {string} in service combo {string} shall be mandatory")
		public void the_service_in_service_combo_shall_be_mandatory(String string, String string2) {
			assertEquals(findServiceCombo(string2).getMainService().getMandatory(),true);
		    // Write code here that turns the phrase above into concrete actions
		}
		@Then("the number of service combos in the system shall be {string}")
		public void the_number_of_service_combos_in_the_system_shall_be(String string) {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(numSCs(),Integer.parseInt(string));
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
								if (service.getName().equals(mainService)) {
									combo.setMainService(item);
								}
							}
						}
					}
					flexibook.addBookableService(combo);
				}
			}
			
			@Then("an error message with content {string} shall be raised")
			public void an_error_message_with_content_shall_be_raised(String string) {
			    // Write code here that turns the phrase above into concrete actions
				assertTrue(error.contains(string));
			}
			@Then("the service combo {string} shall not exist in the system")
			public void the_service_combo_shall_not_exist_in_the_system(String string) {
			    // Write code here that turns the phrase above into concrete actions
				assertEquals(findServiceCombo(string),null);
			}

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
					}
					
		@Given("Customer with username {string} is logged in")
			public void customer_with_username_is_logged_in(String string) {
			if (FlexiBookApplication.getCurrentUser()==null) {
					if (findCustomer(string)==null) {
						new Customer(string,"customer",flexibook);
					}
					FlexiBookApplication.setCurrentUser(findCustomer(string));
			}
			else {
					if (findCustomer(string)==null) {
					new Customer(string,"customer",flexibook);
			}
					FlexiBookApplication.setCurrentUser(findCustomer(string));
			}
					 // Write code here that turns the phrase above into concrete actions
		}

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
		
		@Then("the service combo {string} shall be updated to name {string}")
		public void the_service_combo_shall_be_updated_to_name(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(findServiceCombo(string2).getName(),string2);
		}
		

		@Given("the system's time and date is {string}")
		public void the_system_s_time_and_date_is(String string) {
		    // Write code here that turns the phrase above into concrete actions
			String temp1 = string.substring(0, 10);
			String temp2 = string.substring(11, 16);
			temp2 = temp2+":00";
			Time time = Time.valueOf(temp2);
			Date date = Date.valueOf(temp1);
			SystemTime.setSysDate(date);
			SystemTime.setSysTime(time);
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
		
		
		
		@When("{string} initiates the deletion of service combo {string}")
		public void initiates_the_deletion_of_service_combo(String string, String string2) {
			try {
				FlexiBookController.deleteServiceCombo(string, string2);
			}catch (InvalidInputException e){
				error+=e.getMessage();
				errorCntr++;
			}
		    // Write code here that turns the phrase above into concrete actions
		}
		
		@Then("the number of appointments in the system with service {string} shall be {string}")
		public void the_number_of_appointments_in_the_system_with_service_shall_be(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
				assertEquals(getNumAppForService(string), Integer.parseInt(string2));
		}
		@Then("the number of appointments in the system shall be {string}")
		public void the_number_of_appointments_in_the_system_shall_be(String string) {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(flexibook.getAppointments().size(), Integer.parseInt(string));
		}
		
		

		@After
		public void tearDown() {
			FlexiBookApplication.setCurrentUser(null);
			flexibook.delete();
		} 
		private static Service findService(String service) {
			for (BookableService aService : flexibook.getBookableServices()) {
				if (aService instanceof Service) {
					if (aService.getName().equals(service)) return (Service) aService;
				}
			}

			return null;
		}
		
		private static int getNumAppForService(String service) {
			int size = 0;
			for (Appointment app : flexibook.getAppointments()) {
				if(app.getBookableService().getName().equals(service)) size++;
			}
			return size;
		}
		
		private static int numSCs() {
			int count=0;
			for (BookableService aService : flexibook.getBookableServices()) {
				if (aService instanceof ServiceCombo) {
					count++;
				}
			}
			return count;
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
		
		private static Time toTime(String t) {
			String[] tArray = t.split(":");
			int[] intArray = new int[2];
			intArray[0] = Integer.parseInt(tArray[0]);
			intArray[1] = Integer.parseInt(tArray[1]);
			LocalTime localTime = LocalTime.of(intArray[0], intArray[1]);
			return Time.valueOf(localTime);
		}
		
		private static BookableService findBookableService(String name) {
			BookableService SCfound = null;
			for (BookableService Sc : flexibook.getBookableServices()) {
				if (Sc.getName().equals(name)) {
					SCfound = Sc;
				}
			}
			return SCfound;
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
		
		private static ServiceCombo findServiceCombo(String serviceCombo) {
			for (BookableService aService : flexibook.getBookableServices()) {
				if (aService instanceof ServiceCombo) {
					if (aService.getName().equals(serviceCombo) ) return (ServiceCombo) aService;
				}
			}
			return null;
		}
		
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
		
}

