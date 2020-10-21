package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;

public class FlexiBookController {

	
	public FlexiBookController() {	
	}
	
	public static void login (String username, String password) throws InvalidInputException{
		try {
			if (username.equals("owner") && password.equals("owner")) {
				Owner owner = new Owner(username, password, FlexiBookApplication.getFlexibook());
				FlexiBookApplication.setCurrentUser(owner);
				return;
			}
			
			else if (findUser(username) != null  && checkPassword(findUser(username), password)) {
				FlexiBookApplication.setCurrentUser(findUser(username));
				return;
			}
			
			else throw new InvalidInputException("Username/password not found");

		}
		catch (RuntimeException e) {
			
			throw new InvalidInputException(e.getMessage());
		}		
		
	}
	
	public static void logout () throws InvalidInputException{
		try {
			if (FlexiBookApplication.getCurrentUser() != null) FlexiBookApplication.setCurrentUser(null);
			else throw new InvalidInputException("The user is already logged out");
			
		}
		catch (RuntimeException e) {
			
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void viewAppointmentCalendar(String username, Date date, boolean dailyView) throws InvalidInputException{
		
	}
	
	private static User findUser(String username) {
		User foundUser = null;
		
		if (FlexiBookApplication.getFlexibook().getOwner() != null) {
			if((FlexiBookApplication.getFlexibook().getOwner().getUsername()).equals(username)){  
				Owner owner = FlexiBookApplication.getFlexibook().getOwner();
				foundUser = owner;
				return foundUser;
			}
		}
		for (Customer customer : FlexiBookApplication.getFlexibook().getCustomers()) {
			if (customer.getUsername() == username) {
				foundUser = customer;
				return foundUser;
			}
		}
		
		return foundUser;
	}
	
	public static void SetUpContactInfo(String name, String address, String phoneNumber, String email) throws InvalidInputException{
		
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		
		if ((email.indexOf('@') == -1) || (email.indexOf('.') == -1) || (email.indexOf('.') < email.indexOf('@')) || (email.indexOf('@') == email.length()-1) || (email.indexOf('.') == email.length()-1)){
			throw new InvalidInputException("Invalid email");
		}
	
		
		try {
			Business Business = new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexibook());
			FlexiBookApplication.getFlexibook().setBusiness(Business);
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}
	
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
				if (test.get(i).getEndTime().after(temp2)) {
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
	
	public static List<String> ViewBusinessInfo() throws InvalidInputException {
		List<String> BusinessInfo = null;
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
		
//		LocalDate tester = LocalDate.now();
//		if (startDate.before(Date.valueOf(tester))) {
//			if(type.equals("holiday")) {
//				throw new InvalidInputException("Holiday cannot start in the past");
//			}
//			else {
//				throw new InvalidInputException("Vacation cannot start in the past");
//			}
//		}
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
	
	public static void UpdateHolidayOrVacation(String type, Date date, Time time, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException{
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to update business information");
		}
		if (endDate.before(startDate) || (startDate.equals(endDate) && endTime.before(startTime))) {
			throw new InvalidInputException("Start time must be before end time");
		}

//		LocalDate tester = LocalDate.now();
//		if (startDate.before(Date.valueOf(tester))) {
//			if(type.equals("holiday")) {
//				throw new InvalidInputException("Holiday cannot start in the past");
//			}
//			else {
//				throw new InvalidInputException("Vacation cannot start in the past");
//			}
//		}
		
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
	
	public static void RemoveTimeSlot(String type, String string2, String string3, String string4, String string5) throws InvalidInputException{
		
//		string3 = string3+":00";
//		string5 = string5+":00";
//		Time startTime = Time.valueOf(string3);
//		Time endTime = Time.valueOf(string5);
//		
//		Date startDate = Date.valueOf(string2);
//		Date endDate = Date.valueOf(string4);
		
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
//		TimeSlot remove = new TimeSlot(startDate, startTime, endDate, endTime, FlexiBookApplication.getFlexibook());
//		try {
//			if(type.equals("holiday")) {
//				FlexiBookApplication.getFlexibook().getBusiness().removeHoliday(remove);
//			}
//			else {
//				FlexiBookApplication.getFlexibook().getBusiness().removeVacation(remove);
//			}
//		}
//		catch(RuntimeException e){
//			throw new InvalidInputException(e.getMessage());
//		}
	}
	
	
	
	
	
	private static boolean checkPassword(User user, String password) {
		if (user.getPassword().equals(password)) return true;
		
		else return false;
	}
}
