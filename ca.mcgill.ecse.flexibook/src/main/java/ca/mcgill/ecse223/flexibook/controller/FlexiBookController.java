package ca.mcgill.ecse223.flexibook.controller;

import java.sql.Date;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.application.SystemTime;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.User;

/**
 * 
 * @author Robert Aprahamian
 *
 */

public class FlexiBookController {

	public FlexiBookController() {	
	}

	/**
	 * 1. Define ServiceCombo
	 * @param ownerName is the username of the user trying to define a service combo 
	 * @param SCname is the name of the service combo to be defined
	 * @param mainService is the main Service of the service combo to be defined
	 * @param services is a string that contains the names of the services that should be included in the service combo that is to be defined
	 * @param mandatory is a string containing all the mandatory status of each service in correspondence with the service with the same "index" in the previous parameter (the services names) 
	 * @throws InvalidInputException in the case where a customer tried to define a service combo or an owner is trying to define a service combo with parameters that create conflicts with the system.
	 * defineServiceCombo is a method that basically is taking the previously mentioned parameters and creates a service combo using them.
	 * The first step is to check is the user defining the service combo is the owner. Then the method makes sure the parameters inserted are applicable to the system.
	 * The next step is to create combo items that are assigned to a newly created service combo and applying the parameter to that service combo by manipulating the input strings.
	 */
	public static void defineServiceCombo(String ownerName, String SCname, String mainService, String services, String mandatory) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())) {
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		if (findServiceCombo(SCname)!=null) throw new InvalidInputException("Service combo " + SCname + " already exists");
		if (findService(mainService) == null) throw new InvalidInputException("Service " + mainService + " does not exist");
		
		String[] elements = services.split(",");
		String[] mandatories = mandatory.split(",");
		
		if (elements.length<2) {
			throw new InvalidInputException("A service Combo must contain at least 2 services");
		}
		for (int l=0;l<elements.length;l++) {
			if (findService(elements[l])==null) {
				throw new InvalidInputException("Service "+elements[l]+" does not exist");
			}
		}
		List<String> a = Arrays.asList(elements);
		ArrayList<String> listOfService = new ArrayList<String>(a);
		if (!listOfService.contains(mainService)) {
			throw new InvalidInputException("Main service must be included in the services");
		}
		int mainIndex = 0;
		for (int m=0;m<elements.length;m++) {
			if (elements[m].equals(mainService)) mainIndex = m;
		}
		if (!mandatories[mainIndex].equals("true")) throw new InvalidInputException("Main service must be mandatory");
		
		FlexiBook f = FlexiBookApplication.getFlexibook();
		ServiceCombo sc = new ServiceCombo(SCname, f);
		
		for (int i= 0 ;i<elements.length ;i++) {
			boolean isMand = false;
			if(mandatories[i].equals("true")) isMand = true;
			ComboItem coI = new ComboItem(isMand, findService(elements[i]),sc); 
			sc.addService(coI);
			if (coI.getService().getName().equals(mainService)) sc.setMainService(coI);
		}
	}
	
	private static Service findService(String service) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof Service) {
				if (aService.getName().equals(service) ) return (Service) aService;
			}
		}
		return null;
	}
	 
	/**
	 * 2. Update ServiceCombo
	 * @param ownerName is the username of the user trying to update a service combo.
	 * @param SCOldName is the name of the service combo to be updated.
	 * @param newSCName is the new name for the service combo after being updated.
	 * @param mainService is the main Service of the service combo to be after the update.
	 * @param services is a string that contains the names of the services that should be included in the service combo after being updated.
	 * @param mandatory is a string containing all the mandatory status of each service in correspondence with the service with the same "index" in the previous parameter (the services names) after the update of the service combo.
	 * @throws InvalidInputException in the case where a customer tried to update a service combo or an owner is trying to update a service combo with parameters that create conflicts with the system.
	 * updateServiceCombo is a method that must use the previously mentioned input to have an updated service combo. 
	 * The chosen service combo has parameters that are to be replaceed with the ones that are in the input. 
	 * In this method, the first step was to check that the user trying to update a service combo is the owner.
	 * The next step was to make the inserted parameters are valid and that they do not create any conflicts with the system.
	 * After that, to update the service combo, the method looks for the service combo with the old name, then deletes all its parameters then adds the new parameters to the service combo.
	 * By doing that the service combo has completely changed its parameters which means it has been updated.
	 */
	public static void updateServiceCombo(String ownerName, String SCOldName,String newSCName, String mainService, String services, String mandatory) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())) {
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		if (!SCOldName.equals(newSCName) && findServiceCombo(newSCName)!=null) throw new InvalidInputException("Service combo " + newSCName + " already exists");
		ServiceCombo sc = findServiceCombo(SCOldName);
		
		String[] elements = services.split(",");
		String[] mandatories = mandatory.split(",");
		if (elements.length<2) throw new InvalidInputException("A service Combo must have at least 2 services");
		for (int l=0;l<elements.length;l++) {
			if (findService(elements[l])==null) {
				throw new InvalidInputException("Service "+elements[l]+" does not exist");
			}
		}
		
		if (findService(mainService) == null) throw new InvalidInputException("Service " + mainService + " does not exist");
		List<String> a = Arrays.asList(elements);
		ArrayList<String> listOfService = new ArrayList<String>(a);
		if (!listOfService.contains(mainService)) {
			throw new InvalidInputException("Main service must be included in the services");
		}
		
		int mainIndex = 0;
		for (int m=0;m<elements.length;m++) {
			if (elements[m].equals(mainService)) mainIndex = m;
		}
		if (!mandatories[mainIndex].equals("true")) throw new InvalidInputException("Main service must be mandatory");
		sc.setName(newSCName);
		
		ArrayList<ComboItem> items = new ArrayList<ComboItem>();
		for (int j=0;j<sc.getServices().size();j++) {
			items.add(sc.getService(j));
		}
		for (int i= 0 ;i<elements.length ;i++) {
			boolean isMand = false;
			if(mandatories[i].equals("true")) isMand = true;
			ComboItem coI = new ComboItem(isMand, findService(elements[i]),sc); 
			sc.addService(coI);
			if (coI.getService().getName().equals(mainService)) sc.setMainService(coI);
		}
		int sizeMax = items.size();
		for (int k = 0;k<sizeMax;k++) {
			items.get(k).delete();
		}
		
	}
	
	private static ServiceCombo findServiceCombo(String serviceCombo) {
		FlexiBook flexibook = FlexiBookApplication.getFlexibook();
		for (BookableService aService : flexibook.getBookableServices()) {
			if (aService instanceof ServiceCombo) {
				if (aService.getName().equals(serviceCombo) ) return (ServiceCombo) aService;
			}
		}
		return null;
	}
	 
	/**
	 * 3. Delete ServiceCombo
	 * @param ownerName is the username of the user trying to update a service combo.
	 * @param scDelete is the name of the service combo to be deleted. 
	 * @throws InvalidInputException is for the case where a customer tries to delete a service combo or when an owner tried to delete a service combo with future appointments.
	 * deleteServiceCombo is a method that deletes a certain service combo from the system. 
	 * The first step is to check that the owner is the user trying to do the deletion and that they are doing a deletion of a service combo without future appointments.
	 * The next step is to simply eliminate, delete the service combo from the system.
	 */
	public static void deleteServiceCombo(String ownerName, String scDelete) throws InvalidInputException {
		if (!FlexiBookApplication.getCurrentUser().getUsername().equals(FlexiBookApplication.getFlexibook().getOwner().getUsername())) {
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		else {
		ServiceCombo sc = findServiceCombo(scDelete);
		
		List<Appointment> a = sc.getAppointments();//FlexiBookApplication.getFlexibook().getAppointments();
		for (int i =0;i<a.size();i++) {
			if (a.get(i).getTimeSlot().getStartDate().after(SystemTime.getSysDate())) throw new InvalidInputException("Service combo "+scDelete+" has future appointments"); 
			if (a.get(i).getTimeSlot().getStartDate().equals(SystemTime.getSysDate())) {
				if (a.get(i).getTimeSlot().getEndTime().after(SystemTime.getSysTime())) throw new InvalidInputException("Service combo "+scDelete+" has future appointments");
				else a.get(i).delete();
			}
			if (a.get(i).getTimeSlot().getStartDate().before(SystemTime.getSysDate())) {
				a.get(i).delete();
			}
		}

		if (sc != null) {
			sc.delete();
		}
//		else {
//			throw new InvalidInputException("There is no Service combo with this name");
//		}
		}
	}
}