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
import ca.mcgill.ecse.flexibook.application.SystemTime;
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
	
	/**
	 * author: Fadi Tawfik Beshay
	 * @param name business name stored as string
	 * @param address business address stored as string
	 * @param phoneNumber business phone number stored as string
	 * @param email business email stored as string
	 * @throws InvalidInputException
	 */
	public static void SetUpContactInfo(String name, String address, String phoneNumber, String email) throws InvalidInputException{	
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		if ((email.indexOf('@') == -1) || (email.indexOf('.') == -1) || (email.indexOf('.') < email.indexOf('@')) || (email.indexOf('@') == email.length()-1) || (email.indexOf('.') == email.length()-1)){
			throw new InvalidInputException("Invalid email");
		}
		try {
			FlexiBookApplication.getFlexibook().setBusiness(new Business(name, address, phoneNumber, email, FlexiBookApplication.getFlexibook()));
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	/**
	 * author: Fadi Tawfik Beshay
	 * @param Day enum Day of Week used to determine the corresponding day of the week
	 * @param temp2 Time object representing start time of the business hours
	 * @param temp3 Time object representing end time of the business hours
	 * @throws InvalidInputException
	 */
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
				if (temp3.before(test.get(i).getEndTime()) && (temp2.before(test.get(i).getEndTime()))) {
					throw new InvalidInputException("The business hours cannot overlap");
				}
				if (temp3.after(test.get(i).getEndTime()) && (temp2.before(test.get(i).getEndTime()))) {
					throw new InvalidInputException("The business hours cannot overlap");
				}
				if (temp3.before(test.get(i).getStartTime()) && (temp2.before(test.get(i).getStartTime()))) {
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
	
	/**
	 * author: Fadi Tawfik Beshay
	 * @return a string list containing the name, address, phone number and email of the business
	 * @throws InvalidInputException
	 */
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
	
	/**
	 * author: Fadi Tawfik Beshay
	 * @param type string indicating the new time slot is a holiday or vacation
	 * @param startDate Date object referring to start date of time slot
	 * @param startTime Time object referring to start time of time slot
	 * @param endDate Date object referring to end date of time slot
	 * @param endTime Time object referring to end time of time slot
	 * @throws InvalidInputException
	 */
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
		if (startDate.before(SystemTime.getSysDate())) {
			if(type.equals("holiday")) {
				throw new InvalidInputException("Holiday cannot start in the past");
			}
			else {
				throw new InvalidInputException("Vacation cannot start in the past");
			}
		}
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
	
	/**
	 * author: Fadi Tawfik Beshay
	 * @param name string referring to business name
	 * @param address string referring to business address
	 * @param phoneNumber string referring to business phone number
	 * @param email string referring to business email
	 * @throws InvalidInputException
	 */
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
	
	/**
	 * author: Fadi Tawfik Beshay
	 * @param day1 DayOfWeek object from an enum of the days of the week referring to day of business hours to be updated
	 * @param time Time object referring to start time of the business hours to be changed
	 * @param day2 DayOfWeek object from an enum of the days of the week referring to new day of business hours
	 * @param startTime Time object referring to start time of the updated business hours
	 * @param endTime Time object referring to end time of the updated business hours
	 * @throws InvalidInputException
	 */
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
	
	/**
	 * author: Fadi Tawfik Beshay
	 * @param day1 DayOfWeek object from an enum of the days of the week referring to day of business hours to be removed
	 * @param time Time object referring to start time of the business hours to be removed
	 * @throws InvalidInputException
	 */
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

	/**
	 * author: Fadi Tawfik Beshay
	 * @param type string indicating whether the time slot is a holiday or vacation
	 * @param date date object referring to start date of time slot to be updated
	 * @param time time object referring to start time of time slot to be updated
	 * @param startDate date object referring to start date of new time slot
	 * @param startTime time object referring to start time of new time slot
	 * @param endDate date object referring to end date of new time slot
	 * @param endTime time object referring to end time of new time slot
	 * @throws InvalidInputException
	 */
	public static void UpdateHolidayOrVacation(String type, Date date, Time time, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException{
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to update business information");
		}
		if (endDate.before(startDate) || (startDate.equals(endDate) && endTime.before(startTime))) {
			throw new InvalidInputException("Start time must be before end time");
		}
		if (startDate.before(SystemTime.getSysDate())) {
			if(type.equals("holiday")) {
				throw new InvalidInputException("Holiday cannot start in the past");
			}
			else {
				throw new InvalidInputException("Vacation cannot start in the past");
			}
		}
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
	
	/**
	 * author: Fadi Tawfik Beshay
	 * @param type string indicating whether the time slot is a holiday or vacation
	 * @param startDate date object referring to start date of time slot to be removed
	 * @param startTime time object referring to start time of time slot to be removed
	 * @param endDate date object referring to end date of time slot to be removed
	 * @param endTime time object referring to end time of time slot to be removed
	 * @throws InvalidInputException
	 */
	public static void RemoveTimeSlot(String type, Date startDate, Time startTime, Date endDate, Time endTime) throws InvalidInputException{
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())){
			throw new InvalidInputException("No permission to set up business information");
		}
		TimeSlot remove = new TimeSlot(startDate, startTime, endDate, endTime, FlexiBookApplication.getFlexibook());
		try {
			if(type.equals("holiday")) {
				FlexiBookApplication.getFlexibook().getBusiness().removeHoliday(remove);
			}
			else {
				FlexiBookApplication.getFlexibook().getBusiness().removeVacation(remove);
			}
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	
	
	
	
	private static boolean checkPassword(User user, String password) {
		if (user.getPassword().equals(password)) return true;
		
		else return false;
	}
}
