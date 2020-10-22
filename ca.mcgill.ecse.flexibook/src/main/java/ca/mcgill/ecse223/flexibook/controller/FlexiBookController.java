package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
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


	/**
	 * Registers a customer account given a username and password
	 * @param username: The username to set to the account
	 * @param password: The password to set to the account
	 * @throws InvalidInputException
	 */
	public static void signUpCustomerAccount(String username, String password) throws InvalidInputException {
		try {
			FlexiBook flexibook = FlexiBookApplication.getFlexibook();

			if(username.equals("") || username == null) {
				throw new InvalidInputException("The user name cannot be empty");
			} else if(password.equals("") || password == null) {
				throw new InvalidInputException("The password cannot be empty");
			} else if(findUser("owner") != null && FlexiBookApplication.getCurrentUser() == findUser("owner") ) {
				throw new InvalidInputException("You must log out of the owner account before creating a customer account");
			} else if(findUser(username) != null) {
				throw new InvalidInputException("The username already exists");
			} else

				flexibook.addCustomer(username, password);

		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	/**
	 * Updates a user account by setting a new username and and a new passowrd to the account
	 * @param username: The current username set to the account 
	 * @param newUsername: The new username set to the account 
	 * @param newPassword: The new password set to the account
	 * @throws InvalidInputException
	 */
	public static void updateAccount(String username, String newUsername, String newPassword) throws InvalidInputException  {

		try {
			if(newUsername == null || newUsername.length() == 0) {
				throw new InvalidInputException("The user name cannot be empty");
			}

			if(newPassword == null || newPassword.length() == 0) {
				throw new InvalidInputException("The password cannot be empty");
			}
			FlexiBook flexibook = FlexiBookApplication.getFlexibook();

			if (flexibook.getOwner() != null) {
				if(flexibook.getOwner().getUsername().equals(username)){  
					if(username.equals(newUsername)) {
						flexibook.getOwner().setPassword(newPassword);
						return;
					} else {
						throw new InvalidInputException("Changing username of owner is not allowed");
					}
				}
			}

			User currUser = findCustomer(username);
			if(currUser == null) {
				throw new InvalidInputException("User does not exist");
			}
			if (!currUser.setUsername(newUsername)) {
				throw new InvalidInputException("Username not available");
			}
			if(!currUser.setPassword(newPassword)) {
				throw new InvalidInputException("Unable to change password");
			}

		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	/**
	 * Deletes a customer account given a username 
	 * @param username: The username of the account being deleted
	 * @param target
	 * @throws InvalidInputException
	 */
	public static void deleteCustomerAccount(String username, String target) throws InvalidInputException {

		try {
			FlexiBook flexibook = FlexiBookApplication.getFlexibook();
			if(!(username.equals(target)) || findOwner(username) != null || findOwner(target) != null) {
				throw new InvalidInputException("You do not have permission to delete this account");
			} else {

				Customer customerToDelete = findCustomer(username);
				List<Appointment> oldAppointments = customerToDelete.getAppointments();

				for(Appointment oldAppointment: oldAppointments) {
					flexibook.removeAppointment(oldAppointment);
					oldAppointment.delete();
				}
				
				flexibook.removeCustomer(customerToDelete);
				customerToDelete.delete();

			}
			FlexiBookApplication.setCurrentUser(null);


		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}


	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	private static Owner findOwner(String username) {
		Owner foundOwner = null;

		if (FlexiBookApplication.getFlexibook().getOwner() != null) {
			if((FlexiBookApplication.getFlexibook().getOwner().getUsername()).equals(username)){  
				Owner owner = FlexiBookApplication.getFlexibook().getOwner();
				foundOwner = owner;
				return foundOwner;
			}
		}
		return foundOwner;
	}

	/**
	 * 
	 * @param username
	 * @return
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

		if (FlexiBookApplication.getFlexibook().getOwner() != null) {
			if((FlexiBookApplication.getFlexibook().getOwner().getUsername()).equals(username)){  
				Owner owner = FlexiBookApplication.getFlexibook().getOwner();
				foundUser = owner;
				return foundUser;
			}
		}
		for (Customer customer : FlexiBookApplication.getFlexibook().getCustomers()) {
			if (customer.getUsername().equals(username)) {
				foundUser = customer;
				return foundUser;
			}
		}


		return foundUser;
	}


	private static boolean checkPassword(User user, String password) {
		if (user.getPassword().equals(password)) return true;

		else return false;
	}
}
