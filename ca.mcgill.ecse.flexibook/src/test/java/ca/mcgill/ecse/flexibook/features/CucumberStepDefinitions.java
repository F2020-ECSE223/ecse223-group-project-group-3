package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
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
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;
import ca.mcgill.ecse223.flexibook.controller.FlexiBookController;
import ca.mcgill.ecse223.flexibook.controller.InvalidInputException;
import ca.mcgill.ecse223.flexibook.controller.TOTimeSlot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {


	private FlexiBook flexibook;
	private String error;
	int errorCntr;
	
		@Given("a Flexibook system exists")
		public void a_flexibook_system_exists() {
			flexibook = FlexiBookApplication.getFlexibook();
			error = "";
			errorCntr = 0;
		    throw new io.cucumber.java.PendingException();
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
			
		    throw new io.cucumber.java.PendingException();
		}
		
		@Given("an owner account exists in the system")
		public void an_owner_account_exists_in_the_system() {
			if(flexibook.getOwner() == null) {
				Owner owner = new Owner("owner", "owner", flexibook);
			}
		    throw new io.cucumber.java.PendingException();
		}
		
		@Given("a business exists in the system")
		public void a_business_exists_in_the_system() {
		   
			if(flexibook.getBusiness()==null) {
				Business business = new Business("Busy Diner", "123 New Str", "(514)987-6543", "busy@gmail.com", flexibook);
			}
		    throw new io.cucumber.java.PendingException();
		}
		
		@Given("the following customers exist in the system:")
		public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

			List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
			for (Map<String, String> columns : rows) {
				flexibook.addCustomer(columns.get("username"), columns.get("password"));
			
			}
		    throw new io.cucumber.java.PendingException();
		}
		
		@Given("the following services exist in the system:")
		public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		    
			List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

			for (Map<String, String> columns : rows) {
				Service service = new Service(columns.get("name"), flexibook, 
						Integer.parseInt(columns.get("duration")), 
						Integer.parseInt(columns.get("downtimeStart")),
						Integer.parseInt(columns.get("downtimeDuration")));
				flexibook.addBookableService(service);
			}
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
			List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
			
			for(int i=0; i<list.size(); i++) {
				Time startTime = Time.valueOf(list.get(i).get("startTime"));
				Time endTime = Time.valueOf(list.get(i).get("endTime"));
				Date startDate = Date.valueOf(list.get(i).get("date"));
				Date endDate = startDate;
				
				TimeSlot aTimeSlot = new TimeSlot(startDate, startTime, endDate, endTime, flexibook);
				
				Customer customer= (Customer) findUser(list.get(i).get("customer"));
				
				if(list.get(i).get("optServices")==null) {
					BookableService aBookableService = BookableService.getWithName(list.get(i).get("serviceName"));
					
					flexibook.addAppointment(customer, aBookableService, aTimeSlot);
				}
				else {
					BookableService aBookableService = (ServiceCombo) BookableService.getWithName(list.get(i).get("serviceName"));
					
					flexibook.addAppointment(customer, aBookableService, aTimeSlot);
				}
				
			}
			
		    throw new io.cucumber.java.PendingException();
		}
		
		@Given("{string} is logged in to their account")
		public void is_logged_in_to_their_account(String string) {
		  
			FlexiBookApplication.setCurrentUser(findUser(string));
		    throw new io.cucumber.java.PendingException();
		}
		
		@When("{string} attempts to cancel their {string} appointment on {string} at {string}")
		public void attempts_to_cancel_their_appointment_on_at(String string, String string2, String string3, String string4) {
		    // Write code here that turns the phrase above into concrete actions
			try {
			FlexiBookController.CancelAppointment(string, string2, string3, string4);
			
			}catch (InvalidInputException e){
				error+=e.getMessage();
				errorCntr++;

			}
		    throw new io.cucumber.java.PendingException();
		}
		
		@Then("the system shall report {string}")
		public void the_system_shall_report(String string) {
		    // Write code here that turns the phrase above into concrete actions
			assertTrue(error.contains(string));
		    throw new io.cucumber.java.PendingException();
		}
		
		@Then("{string} shall have a {string} appointment on {string} from {string} to {string}")
		public void shall_have_a_appointment_on_from_to(String string, String string2, String string3, String string4, String string5) {
		    // Write code here that turns the phrase above into concrete actions
			boolean is =false; 
			if(findAppointment(string, string2, string3, string4) != null) {
				is=true;
			}
			
			assertTrue(is);
		    throw new io.cucumber.java.PendingException();
		}
		
		@Then("there shall be {int} more appointment in the system")
		public void there_shall_be_more_appointment_in_the_system(Integer int1) {
		    // Write code here that turns the phrase above into concrete actions
			int size = flexibook.getAppointments().size();
			
			if(int1==-1) {
				size = size-1;
			}
			
			assertEquals(flexibook.getAppointments().size(), size);
		    throw new io.cucumber.java.PendingException();
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
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("the business has the following opening hours")
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
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("the business has the following holidays")
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
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("{string} schedules an appointment on {string} for {string} at {string}")
	public void schedules_an_appointment_on_for_at(String string, String string2, String string3, String string4) {
	    // Write code here that turns the phrase above into concrete actions
		FlexiBookController.makeAppointment(string, string2, string3, string4);
		
	    throw new io.cucumber.java.PendingException();
	}


//	@When("{string} schedules an appointment on on {string} for {string} at {string}")
//	public void schedules_an_appointment_on_on_for_at(String string, String string2, String string3, String string4) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
	

		
	@When("{string} attempts to update their {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_their_appointment_on_at_to_at(String string, String string2, String string3, String string4, String string5, String string6) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("the system shall report that the update was {string}")
	public void the_system_shall_report_that_the_update_was(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
		
	@When("{string} attempts to update {string}'s {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_s_appointment_on_at_to_at(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
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
		

		private static Service findService(String service) {
			FlexiBook flexibook = FlexiBookApplication.getFlexibook();

			for (BookableService aService : flexibook.getBookableServices()) {
				if (aService instanceof Service) {
					if (aService.getName().equals(service)) return (Service) aService;
				}
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
		private static TimeSlot findTimeSlotOfApp (String serviceName, String optServicesString, String date, String startTimeString) {
			FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
			startTimeString = startTimeString+":00";
			Time startTime = Time.valueOf(startTimeString);
			Date startDate = Date.valueOf(date);
			Time endTime= null;
			Date endDate = startDate;
			BookableService thisService = BookableService.getWithName(serviceName);
			Service service = (Service)thisService;
			
			String[] myArray = optServicesString.split(", ");
			List<String> optionalServices = new ArrayList<>();
			
			for (String str : myArray) {
			    optionalServices.add(str);
			}
			
			if (myArray == null) {
				
				endTime = new Time(startTime.getTime() + service.getDuration());
				
			} else {
				endTime = new Time(startTime.getTime() + service.getDuration());
				
				for(int i=0; i< optionalServices.size()-1; i++) {
					Service service2 = (Service) BookableService.getWithName(optionalServices.get(i));

					endTime = new Time(endTime.getTime() + service2.getDuration());
					
				}
								
			}
			
			TimeSlot aTimeSlot = new TimeSlot(startDate, startTime, endDate, endTime, flexiBook);


		return aTimeSlot;
		}
		
		private static Appointment findAppointment(String username, String appName, String date, String startTimeString) {
			Customer customer= (Customer) findUser(username);
			BookableService service = BookableService.getWithName(appName);
			TimeSlot aTimeSlot = null;
			aTimeSlot = findTimeSlotOfApp(username, appName, date, startTimeString);
			
			Appointment app=null;
			
			FlexiBook flexiBook = FlexiBookApplication.getFlexibook();
			for(int i=0; i<flexiBook.getAppointments().size(); i++) {
				if(flexiBook.getAppointment(i).getCustomer()== customer) {
					if(flexiBook.getAppointment(i).getBookableService()==service) {
						if(flexiBook.getAppointment(i).getTimeSlot()==aTimeSlot) {
							app = flexiBook.getAppointment(i);
						}
					}
				}
			}

		return app;
			}
}
