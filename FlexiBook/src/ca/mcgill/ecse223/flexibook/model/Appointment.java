package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.sql.Date;
import java.util.*;

// line 38 "model.ump"
// line 108 "model.ump"
// line 153 "model.ump"
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private Time startTime;
  private Date date;
  private String location;
  private Time endTime;

  //Appointment Associations
  private Booking bookings;
  private List<Service> services;
  private List<ServiceCombo> serviceCombos;
  private FlexiBook flexiBook;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Time aStartTime, Date aDate, String aLocation, Time aEndTime, FlexiBook aFlexiBook)
  {
    startTime = aStartTime;
    date = aDate;
    location = aLocation;
    endTime = aEndTime;
    services = new ArrayList<Service>();
    serviceCombos = new ArrayList<ServiceCombo>();
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create appointment due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Date getDate()
  {
    return date;
  }

  public String getLocation()
  {
    return location;
  }

  public Time getEndTime()
  {
    return endTime;
  }
  /* Code from template association_GetOne */
  public Booking getBookings()
  {
    return bookings;
  }

  public boolean hasBookings()
  {
    boolean has = bookings != null;
    return has;
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
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setBookings(Booking aBookings)
  {
    boolean wasSet = false;
    Booking existingBookings = bookings;
    bookings = aBookings;
    if (existingBookings != null && !existingBookings.equals(aBookings))
    {
      existingBookings.removeAppointment(this);
    }
    if (aBookings != null)
    {
      aBookings.addAppointment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfServicesValid()
  {
    boolean isValid = numberOfServices() >= minimumNumberOfServices();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Service addService(String aName, int aDuration, boolean aIsMandatory, boolean aHasDowntime, int aDowntime, ServiceCombo aCombo, FlexiBook aFlexiBook, Business aBusiness)
  {
    Service aNewService = new Service(aName, aDuration, aIsMandatory, aHasDowntime, aDowntime, aCombo, aFlexiBook, this, aBusiness);
    return aNewService;
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    Appointment existingAppointment = aService.getAppointment();
    boolean isNewAppointment = existingAppointment != null && !this.equals(existingAppointment);

    if (isNewAppointment && existingAppointment.numberOfServices() <= minimumNumberOfServices())
    {
      return wasAdded;
    }
    if (isNewAppointment)
    {
      aService.setAppointment(this);
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
    //Unable to remove aService, as it must always have a appointment
    if (this.equals(aService.getAppointment()))
    {
      return wasRemoved;
    }

    //appointment already at minimum (1)
    if (numberOfServices() <= minimumNumberOfServices())
    {
      return wasRemoved;
    }

    services.remove(aService);
    wasRemoved = true;
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfServiceCombosValid()
  {
    boolean isValid = numberOfServiceCombos() >= minimumNumberOfServiceCombos();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceCombos()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public ServiceCombo addServiceCombo(String aName, int aNumberOfServices, FlexiBook aFlexiBook, Service aMainService)
  {
    ServiceCombo aNewServiceCombo = new ServiceCombo(aName, aNumberOfServices, aFlexiBook, aMainService, this);
    return aNewServiceCombo;
  }

  public boolean addServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasAdded = false;
    if (serviceCombos.contains(aServiceCombo)) { return false; }
    Appointment existingAppointment = aServiceCombo.getAppointment();
    boolean isNewAppointment = existingAppointment != null && !this.equals(existingAppointment);

    if (isNewAppointment && existingAppointment.numberOfServiceCombos() <= minimumNumberOfServiceCombos())
    {
      return wasAdded;
    }
    if (isNewAppointment)
    {
      aServiceCombo.setAppointment(this);
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
    //Unable to remove aServiceCombo, as it must always have a appointment
    if (this.equals(aServiceCombo.getAppointment()))
    {
      return wasRemoved;
    }

    //appointment already at minimum (1)
    if (numberOfServiceCombos() <= minimumNumberOfServiceCombos())
    {
      return wasRemoved;
    }

    serviceCombos.remove(aServiceCombo);
    wasRemoved = true;
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
  /* Code from template association_SetOneToMany */
  public boolean setFlexiBook(FlexiBook aFlexiBook)
  {
    boolean wasSet = false;
    if (aFlexiBook == null)
    {
      return wasSet;
    }

    FlexiBook existingFlexiBook = flexiBook;
    flexiBook = aFlexiBook;
    if (existingFlexiBook != null && !existingFlexiBook.equals(aFlexiBook))
    {
      existingFlexiBook.removeAppointment(this);
    }
    flexiBook.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (bookings != null)
    {
      Booking placeholderBookings = bookings;
      this.bookings = null;
      placeholderBookings.removeAppointment(this);
    }
    for(int i=services.size(); i > 0; i--)
    {
      Service aService = services.get(i - 1);
      aService.delete();
    }
    for(int i=serviceCombos.size(); i > 0; i--)
    {
      ServiceCombo aServiceCombo = serviceCombos.get(i - 1);
      aServiceCombo.delete();
    }
    FlexiBook placeholderFlexiBook = flexiBook;
    this.flexiBook = null;
    if(placeholderFlexiBook != null)
    {
      placeholderFlexiBook.removeAppointment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "location" + ":" + getLocation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "bookings = "+(getBookings()!=null?Integer.toHexString(System.identityHashCode(getBookings())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null");
  }
}