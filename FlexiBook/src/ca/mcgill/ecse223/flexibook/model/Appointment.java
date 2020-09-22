package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.sql.Date;
import java.util.*;

// line 37 "model.ump"
// line 107 "model.ump"
// line 156 "model.ump"
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private Time time;
  private Date date;
  private String location;
  private int duration;

  //Appointment Associations
  private Booking bookings;
  private List<Service> services;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Time aTime, Date aDate, String aLocation, int aDuration)
  {
    time = aTime;
    date = aDate;
    location = aLocation;
    duration = aDuration;
    services = new ArrayList<Service>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTime(Time aTime)
  {
    boolean wasSet = false;
    time = aTime;
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

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public Time getTime()
  {
    return time;
  }

  public Date getDate()
  {
    return date;
  }

  public String getLocation()
  {
    return location;
  }

  public int getDuration()
  {
    return duration;
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
  public Service addService(String aName, int aDuration, boolean aIsMandatory, boolean aIsDowntime, int aDowntime, ServiceCombo aCombo, Business aBusiness)
  {
    Service aNewService = new Service(aName, aDuration, aIsMandatory, aIsDowntime, aDowntime, aCombo, this, aBusiness);
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
  }


  public String toString()
  {
    return super.toString() + "["+
            "location" + ":" + getLocation()+ "," +
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "time" + "=" + (getTime() != null ? !getTime().equals(this)  ? getTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "bookings = "+(getBookings()!=null?Integer.toHexString(System.identityHashCode(getBookings())):"null");
  }
}