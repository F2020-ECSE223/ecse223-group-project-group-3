package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 48 "model.ump"
// line 116 "model.ump"
// line 161 "model.ump"
public class Business
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Business Attributes
  private String name;
  private String businessHours;
  private String phoneNumber;
  private String holidays;
  private String address;
  private String emailAddress;

  //Business Associations
  private List<Service> services;
  private OwnerAccount owner;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Business(String aName, String aBusinessHours, String aPhoneNumber, String aHolidays, String aAddress, String aEmailAddress, OwnerAccount aOwner)
  {
    name = aName;
    businessHours = aBusinessHours;
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
  }

  public Business(String aName, String aBusinessHours, String aPhoneNumber, String aHolidays, String aAddress, String aEmailAddress, String aNameForOwner, String aPasswordForOwner)
  {
    name = aName;
    businessHours = aBusinessHours;
    phoneNumber = aPhoneNumber;
    holidays = aHolidays;
    address = aAddress;
    emailAddress = aEmailAddress;
    services = new ArrayList<Service>();
    owner = new OwnerAccount(aNameForOwner, aPasswordForOwner, this);
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

  public boolean setBusinessHours(String aBusinessHours)
  {
    boolean wasSet = false;
    businessHours = aBusinessHours;
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

  public String getBusinessHours()
  {
    return businessHours;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, int aDuration, boolean aIsMandatory, boolean aIsDowntime, int aDowntime, ServiceCombo aCombo, Appointment aAppointment)
  {
    return new Service(aName, aDuration, aIsMandatory, aIsDowntime, aDowntime, aCombo, aAppointment, this);
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
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "businessHours" + ":" + getBusinessHours()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "holidays" + ":" + getHolidays()+ "," +
            "address" + ":" + getAddress()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null");
  }
}