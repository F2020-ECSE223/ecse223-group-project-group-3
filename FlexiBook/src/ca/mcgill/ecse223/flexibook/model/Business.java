package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.util.*;
import java.sql.Date;

// line 49 "model.ump"
// line 136 "model.ump"
// line 180 "model.ump"
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
  private String address;
  private String emailAddress;

  //Business Associations
  private List<Service> services;
  private OwnerAccount owner;
  private List<HDate> holidays;
  private FlexiBook flexiBook;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Business(String aName, Time aStartTime, Time aEndTime, String aPhoneNumber, String aAddress, String aEmailAddress, OwnerAccount aOwner, FlexiBook aFlexiBook)
  {
    name = aName;
    startTime = aStartTime;
    endTime = aEndTime;
    phoneNumber = aPhoneNumber;
    address = aAddress;
    emailAddress = aEmailAddress;
    services = new ArrayList<Service>();
    if (aOwner == null || aOwner.getBusiness() != null)
    {
      throw new RuntimeException("Unable to create Business due to aOwner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    owner = aOwner;
    holidays = new ArrayList<HDate>();
    if (aFlexiBook == null || aFlexiBook.getBusiness() != null)
    {
      throw new RuntimeException("Unable to create Business due to aFlexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    flexiBook = aFlexiBook;
  }

  public Business(String aName, Time aStartTime, Time aEndTime, String aPhoneNumber, String aAddress, String aEmailAddress, String aNameForOwner, String aPasswordForOwner, FlexiBook aFlexiBookForOwner, OwnerAccount aOwnerAccountForFlexiBook)
  {
    name = aName;
    startTime = aStartTime;
    endTime = aEndTime;
    phoneNumber = aPhoneNumber;
    address = aAddress;
    emailAddress = aEmailAddress;
    services = new ArrayList<Service>();
    owner = new OwnerAccount(aNameForOwner, aPasswordForOwner, aFlexiBookForOwner, this);
    holidays = new ArrayList<HDate>();
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
  /* Code from template association_GetMany */
  public HDate getHoliday(int index)
  {
    HDate aHoliday = holidays.get(index);
    return aHoliday;
  }

  public List<HDate> getHolidays()
  {
    List<HDate> newHolidays = Collections.unmodifiableList(holidays);
    return newHolidays;
  }

  public int numberOfHolidays()
  {
    int number = holidays.size();
    return number;
  }

  public boolean hasHolidays()
  {
    boolean has = holidays.size() > 0;
    return has;
  }

  public int indexOfHoliday(HDate aHoliday)
  {
    int index = holidays.indexOf(aHoliday);
    return index;
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
  public Service addService(String aName, int aDuration, boolean aIsMandatory, ServiceCombo aServiceCombo, FlexiBook aFlexiBook, Appointment aAppointment)
  {
    return new Service(aName, aDuration, aIsMandatory, aServiceCombo, aFlexiBook, aAppointment, this);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHolidays()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public HDate addHoliday(Date aDate, FlexiBook aFlexiBook)
  {
    return new HDate(aDate, aFlexiBook, this);
  }

  public boolean addHoliday(HDate aHoliday)
  {
    boolean wasAdded = false;
    if (holidays.contains(aHoliday)) { return false; }
    Business existingBusiness = aHoliday.getBusiness();
    boolean isNewBusiness = existingBusiness != null && !this.equals(existingBusiness);
    if (isNewBusiness)
    {
      aHoliday.setBusiness(this);
    }
    else
    {
      holidays.add(aHoliday);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHoliday(HDate aHoliday)
  {
    boolean wasRemoved = false;
    //Unable to remove aHoliday, as it must always have a business
    if (!this.equals(aHoliday.getBusiness()))
    {
      holidays.remove(aHoliday);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHolidayAt(HDate aHoliday, int index)
  {  
    boolean wasAdded = false;
    if(addHoliday(aHoliday))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHolidays()) { index = numberOfHolidays() - 1; }
      holidays.remove(aHoliday);
      holidays.add(index, aHoliday);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHolidayAt(HDate aHoliday, int index)
  {
    boolean wasAdded = false;
    if(holidays.contains(aHoliday))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHolidays()) { index = numberOfHolidays() - 1; }
      holidays.remove(aHoliday);
      holidays.add(index, aHoliday);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHolidayAt(aHoliday, index);
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
    for(int i=holidays.size(); i > 0; i--)
    {
      HDate aHoliday = holidays.get(i - 1);
      aHoliday.delete();
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
            "address" + ":" + getAddress()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null");
  }
}