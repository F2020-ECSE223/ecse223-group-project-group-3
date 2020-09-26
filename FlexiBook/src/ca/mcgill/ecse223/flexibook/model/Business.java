package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.util.*;

// line 50 "model.ump"
// line 118 "model.ump"
// line 158 "model.ump"
public class Business
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Business Attributes
  private String name;
  private Time startTime;
  private Time endTime;
  private String phoneNumber;
  private String holidays;
  private String address;
  private String emailAddress;

  //Business Associations
  private List<Service> services;
  private OwnerAccount owner;
  private FlexiBook flexiBook;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Business(String aName, Time aStartTime, Time aEndTime, String aPhoneNumber, String aHolidays, String aAddress, String aEmailAddress, OwnerAccount aOwner, FlexiBook aFlexiBook)
  {
    name = aName;
    startTime = aStartTime;
    endTime = aEndTime;
    phoneNumber = aPhoneNumber;
    holidays = aHolidays;
    address = aAddress;
    emailAddress = aEmailAddress;
    services = new ArrayList<Service>();
    if (aOwner == null || aOwner.getBusiness() != null)
    {
      throw new RuntimeException("Unable to create Business due to aOwner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    owner = aOwner;
    if (aFlexiBook == null || aFlexiBook.getBusiness() != null)
    {
      throw new RuntimeException("Unable to create Business due to aFlexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    flexiBook = aFlexiBook;
  }

  public Business(String aName, Time aStartTime, Time aEndTime, String aPhoneNumber, String aHolidays, String aAddress, String aEmailAddress, String aNameForOwner, String aPasswordForOwner, FlexiBook aFlexiBookForOwner, OwnerAccount aOwnerAccountForFlexiBook)
  {
    name = aName;
    startTime = aStartTime;
    endTime = aEndTime;
    phoneNumber = aPhoneNumber;
    holidays = aHolidays;
    address = aAddress;
    emailAddress = aEmailAddress;
    services = new ArrayList<Service>();
    owner = new OwnerAccount(aNameForOwner, aPasswordForOwner, aFlexiBookForOwner, this);
    flexiBook = new FlexiBook(aOwnerAccountForFlexiBook, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
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

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setHolidays(String aHolidays)
  {
    boolean wasSet = false;
    holidays = aHolidays;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    emailAddress = aEmailAddress;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getHolidays()
  {
    return holidays;
  }

  public String getAddress()
  {
    return address;
  }

  public String getEmailAddress()
  {
    return emailAddress;
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
  /* Code from template association_GetOne */
  public OwnerAccount getOwner()
  {
    return owner;
  }
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, int aDuration, boolean aIsMandatory, boolean aHasDowntime, int aDowntime, ServiceCombo aCombo, FlexiBook aFlexiBook, Appointment aAppointment)
  {
    return new Service(aName, aDuration, aIsMandatory, aHasDowntime, aDowntime, aCombo, aFlexiBook, aAppointment, this);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    Business existingBusiness = aService.getBusiness();
    boolean isNewBusiness = existingBusiness != null && !this.equals(existingBusiness);
    if (isNewBusiness)
    {
      aService.setBusiness(this);
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
    //Unable to remove aService, as it must always have a business
    if (!this.equals(aService.getBusiness()))
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

  public void delete()
  {
    while (services.size() > 0)
    {
      Service aService = services.get(services.size() - 1);
      aService.delete();
      services.remove(aService);
    }
    
    OwnerAccount existingOwner = owner;
    owner = null;
    if (existingOwner != null)
    {
      existingOwner.delete();
    }
    FlexiBook existingFlexiBook = flexiBook;
    flexiBook = null;
    if (existingFlexiBook != null)
    {
      existingFlexiBook.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "holidays" + ":" + getHolidays()+ "," +
            "address" + ":" + getAddress()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null");
  }
}