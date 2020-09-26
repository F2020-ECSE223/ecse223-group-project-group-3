package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 2 "model.ump"
// line 173 "model.ump"
public class FlexiBook
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FlexiBook Associations
  private List<CustomerAccount> customerAccounts;
  private OwnerAccount ownerAccount;
  private List<Service> services;
  private List<Appointment> appointments;
  private Business business;
  private List<Booking> bookings;
  private List<ServiceCombo> serviceCombos;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FlexiBook(OwnerAccount aOwnerAccount, Business aBusiness)
  {
    customerAccounts = new ArrayList<CustomerAccount>();
    if (aOwnerAccount == null || aOwnerAccount.getFlexiBook() != null)
    {
      throw new RuntimeException("Unable to create FlexiBook due to aOwnerAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    ownerAccount = aOwnerAccount;
    services = new ArrayList<Service>();
    appointments = new ArrayList<Appointment>();
    if (aBusiness == null || aBusiness.getFlexiBook() != null)
    {
      throw new RuntimeException("Unable to create FlexiBook due to aBusiness. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    business = aBusiness;
    bookings = new ArrayList<Booking>();
    serviceCombos = new ArrayList<ServiceCombo>();
  }

  public FlexiBook(String aNameForOwnerAccount, String aPasswordForOwnerAccount, Business aBusinessForOwnerAccount, String aNameForBusiness, Time aStartTimeForBusiness, Time aEndTimeForBusiness, String aPhoneNumberForBusiness, String aHolidaysForBusiness, String aAddressForBusiness, String aEmailAddressForBusiness, OwnerAccount aOwnerForBusiness)
  {
    customerAccounts = new ArrayList<CustomerAccount>();
    ownerAccount = new OwnerAccount(aNameForOwnerAccount, aPasswordForOwnerAccount, this, aBusinessForOwnerAccount);
    services = new ArrayList<Service>();
    appointments = new ArrayList<Appointment>();
    business = new Business(aNameForBusiness, aStartTimeForBusiness, aEndTimeForBusiness, aPhoneNumberForBusiness, aHolidaysForBusiness, aAddressForBusiness, aEmailAddressForBusiness, aOwnerForBusiness, this);
    bookings = new ArrayList<Booking>();
    serviceCombos = new ArrayList<ServiceCombo>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public CustomerAccount getCustomerAccount(int index)
  {
    CustomerAccount aCustomerAccount = customerAccounts.get(index);
    return aCustomerAccount;
  }

  public List<CustomerAccount> getCustomerAccounts()
  {
    List<CustomerAccount> newCustomerAccounts = Collections.unmodifiableList(customerAccounts);
    return newCustomerAccounts;
  }

  public int numberOfCustomerAccounts()
  {
    int number = customerAccounts.size();
    return number;
  }

  public boolean hasCustomerAccounts()
  {
    boolean has = customerAccounts.size() > 0;
    return has;
  }

  public int indexOfCustomerAccount(CustomerAccount aCustomerAccount)
  {
    int index = customerAccounts.indexOf(aCustomerAccount);
    return index;
  }
  /* Code from template association_GetOne */
  public OwnerAccount getOwnerAccount()
  {
    return ownerAccount;
  }
  /* Code from template association_GetMany */
  public Service getService(int index)
  {
    Service aService = services.get(index);
    return aService;
  }

  public List<Service> getServices()
  {
    List<Service> newServices = Collections.unmodifiableList(services);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(Service aService)
  {
    int index = services.indexOf(aService);
    return index;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
  }
  /* Code from template association_GetMany */
  public Booking getBooking(int index)
  {
    Booking aBooking = bookings.get(index);
    return aBooking;
  }

  public List<Booking> getBookings()
  {
    List<Booking> newBookings = Collections.unmodifiableList(bookings);
    return newBookings;
  }

  public int numberOfBookings()
  {
    int number = bookings.size();
    return number;
  }

  public boolean hasBookings()
  {
    boolean has = bookings.size() > 0;
    return has;
  }

  public int indexOfBooking(Booking aBooking)
  {
    int index = bookings.indexOf(aBooking);
    return index;
  }
  /* Code from template association_GetMany */
  public ServiceCombo getServiceCombo(int index)
  {
    ServiceCombo aServiceCombo = serviceCombos.get(index);
    return aServiceCombo;
  }

  public List<ServiceCombo> getServiceCombos()
  {
    List<ServiceCombo> newServiceCombos = Collections.unmodifiableList(serviceCombos);
    return newServiceCombos;
  }

  public int numberOfServiceCombos()
  {
    int number = serviceCombos.size();
    return number;
  }

  public boolean hasServiceCombos()
  {
    boolean has = serviceCombos.size() > 0;
    return has;
  }

  public int indexOfServiceCombo(ServiceCombo aServiceCombo)
  {
    int index = serviceCombos.indexOf(aServiceCombo);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCustomerAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public CustomerAccount addCustomerAccount(String aName, String aPassword)
  {
    return new CustomerAccount(aName, aPassword, this);
  }

  public boolean addCustomerAccount(CustomerAccount aCustomerAccount)
  {
    boolean wasAdded = false;
    if (customerAccounts.contains(aCustomerAccount)) { return false; }
    FlexiBook existingFlexiBook = aCustomerAccount.getFlexiBook();
    boolean isNewFlexiBook = existingFlexiBook != null && !this.equals(existingFlexiBook);
    if (isNewFlexiBook)
    {
      aCustomerAccount.setFlexiBook(this);
    }
    else
    {
      customerAccounts.add(aCustomerAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomerAccount(CustomerAccount aCustomerAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomerAccount, as it must always have a flexiBook
    if (!this.equals(aCustomerAccount.getFlexiBook()))
    {
      customerAccounts.remove(aCustomerAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomerAccountAt(CustomerAccount aCustomerAccount, int index)
  {  
    boolean wasAdded = false;
    if(addCustomerAccount(aCustomerAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomerAccounts()) { index = numberOfCustomerAccounts() - 1; }
      customerAccounts.remove(aCustomerAccount);
      customerAccounts.add(index, aCustomerAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAccountAt(CustomerAccount aCustomerAccount, int index)
  {
    boolean wasAdded = false;
    if(customerAccounts.contains(aCustomerAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomerAccounts()) { index = numberOfCustomerAccounts() - 1; }
      customerAccounts.remove(aCustomerAccount);
      customerAccounts.add(index, aCustomerAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAccountAt(aCustomerAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, int aDuration, boolean aIsMandatory, boolean aHasDowntime, int aDowntime, ServiceCombo aCombo, Appointment aAppointment, Business aBusiness)
  {
    return new Service(aName, aDuration, aIsMandatory, aHasDowntime, aDowntime, aCombo, this, aAppointment, aBusiness);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    FlexiBook existingFlexiBook = aService.getFlexiBook();
    boolean isNewFlexiBook = existingFlexiBook != null && !this.equals(existingFlexiBook);
    if (isNewFlexiBook)
    {
      aService.setFlexiBook(this);
    }
    else
    {
      services.add(aService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    //Unable to remove aService, as it must always have a flexiBook
    if (!this.equals(aService.getFlexiBook()))
    {
      services.remove(aService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceAt(Service aService, int index)
  {  
    boolean wasAdded = false;
    if(addService(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceAt(Service aService, int index)
  {
    boolean wasAdded = false;
    if(services.contains(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceAt(aService, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(Time aStartTime, Date aDate, String aLocation, Time aEndTime)
  {
    return new Appointment(aStartTime, aDate, aLocation, aEndTime, this);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    FlexiBook existingFlexiBook = aAppointment.getFlexiBook();
    boolean isNewFlexiBook = existingFlexiBook != null && !this.equals(existingFlexiBook);
    if (isNewFlexiBook)
    {
      aAppointment.setFlexiBook(this);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a flexiBook
    if (!this.equals(aAppointment.getFlexiBook()))
    {
      appointments.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Booking addBooking(CustomerAccount aCustomer)
  {
    return new Booking(aCustomer, this);
  }

  public boolean addBooking(Booking aBooking)
  {
    boolean wasAdded = false;
    if (bookings.contains(aBooking)) { return false; }
    FlexiBook existingFlexiBook = aBooking.getFlexiBook();
    boolean isNewFlexiBook = existingFlexiBook != null && !this.equals(existingFlexiBook);
    if (isNewFlexiBook)
    {
      aBooking.setFlexiBook(this);
    }
    else
    {
      bookings.add(aBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBooking(Booking aBooking)
  {
    boolean wasRemoved = false;
    //Unable to remove aBooking, as it must always have a flexiBook
    if (!this.equals(aBooking.getFlexiBook()))
    {
      bookings.remove(aBooking);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookingAt(Booking aBooking, int index)
  {  
    boolean wasAdded = false;
    if(addBooking(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookingAt(Booking aBooking, int index)
  {
    boolean wasAdded = false;
    if(bookings.contains(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookingAt(aBooking, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceCombo addServiceCombo(String aName, int aNumberOfServices, Service aMainService, Appointment aAppointment)
  {
    return new ServiceCombo(aName, aNumberOfServices, this, aMainService, aAppointment);
  }

  public boolean addServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasAdded = false;
    if (serviceCombos.contains(aServiceCombo)) { return false; }
    FlexiBook existingFlexiBook = aServiceCombo.getFlexiBook();
    boolean isNewFlexiBook = existingFlexiBook != null && !this.equals(existingFlexiBook);
    if (isNewFlexiBook)
    {
      aServiceCombo.setFlexiBook(this);
    }
    else
    {
      serviceCombos.add(aServiceCombo);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasRemoved = false;
    //Unable to remove aServiceCombo, as it must always have a flexiBook
    if (!this.equals(aServiceCombo.getFlexiBook()))
    {
      serviceCombos.remove(aServiceCombo);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceComboAt(ServiceCombo aServiceCombo, int index)
  {  
    boolean wasAdded = false;
    if(addServiceCombo(aServiceCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceCombos()) { index = numberOfServiceCombos() - 1; }
      serviceCombos.remove(aServiceCombo);
      serviceCombos.add(index, aServiceCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceComboAt(ServiceCombo aServiceCombo, int index)
  {
    boolean wasAdded = false;
    if(serviceCombos.contains(aServiceCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceCombos()) { index = numberOfServiceCombos() - 1; }
      serviceCombos.remove(aServiceCombo);
      serviceCombos.add(index, aServiceCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceComboAt(aServiceCombo, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (customerAccounts.size() > 0)
    {
      CustomerAccount aCustomerAccount = customerAccounts.get(customerAccounts.size() - 1);
      aCustomerAccount.delete();
      customerAccounts.remove(aCustomerAccount);
    }
    
    OwnerAccount existingOwnerAccount = ownerAccount;
    ownerAccount = null;
    if (existingOwnerAccount != null)
    {
      existingOwnerAccount.delete();
    }
    while (services.size() > 0)
    {
      Service aService = services.get(services.size() - 1);
      aService.delete();
      services.remove(aService);
    }
    
    while (appointments.size() > 0)
    {
      Appointment aAppointment = appointments.get(appointments.size() - 1);
      aAppointment.delete();
      appointments.remove(aAppointment);
    }
    
    Business existingBusiness = business;
    business = null;
    if (existingBusiness != null)
    {
      existingBusiness.delete();
    }
    while (bookings.size() > 0)
    {
      Booking aBooking = bookings.get(bookings.size() - 1);
      aBooking.delete();
      bookings.remove(aBooking);
    }
    
    while (serviceCombos.size() > 0)
    {
      ServiceCombo aServiceCombo = serviceCombos.get(serviceCombos.size() - 1);
      aServiceCombo.delete();
      serviceCombos.remove(aServiceCombo);
    }
    
  }

}