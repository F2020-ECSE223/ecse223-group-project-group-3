package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;
import java.sql.Date;

// line 36 "model.ump"
// line 123 "model.ump"
// line 175 "model.ump"
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
  private Booking booking;
  private Service service;
  private ServiceCombo serviceCombo;
  private DownTime downTime;
  private FlexiBook flexiBook;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Time aStartTime, Date aDate, String aLocation, Time aEndTime, Service aService, ServiceCombo aServiceCombo, FlexiBook aFlexiBook)
  {
    startTime = aStartTime;
    date = aDate;
    location = aLocation;
    endTime = aEndTime;
    if (aService == null || aService.getAppointment() != null)
    {
      throw new RuntimeException("Unable to create Appointment due to aService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    service = aService;
    if (aServiceCombo == null || aServiceCombo.getAppointment() != null)
    {
      throw new RuntimeException("Unable to create Appointment due to aServiceCombo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    serviceCombo = aServiceCombo;
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create appointment due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Appointment(Time aStartTime, Date aDate, String aLocation, Time aEndTime, String aNameForService, int aDurationForService, boolean aIsMandatoryForService, ServiceCombo aServiceComboForService, FlexiBook aFlexiBookForService, Business aBusinessForService, String aNameForServiceCombo, int aNumberOfServicesForServiceCombo, FlexiBook aFlexiBookForServiceCombo, Service aMainServiceForServiceCombo, FlexiBook aFlexiBook)
  {
    startTime = aStartTime;
    date = aDate;
    location = aLocation;
    endTime = aEndTime;
    service = new Service(aNameForService, aDurationForService, aIsMandatoryForService, aServiceComboForService, aFlexiBookForService, this, aBusinessForService);
    serviceCombo = new ServiceCombo(aNameForServiceCombo, aNumberOfServicesForServiceCombo, aFlexiBookForServiceCombo, aMainServiceForServiceCombo, this);
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
  public Booking getBooking()
  {
    return booking;
  }

  public boolean hasBooking()
  {
    boolean has = booking != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Service getService()
  {
    return service;
  }
  /* Code from template association_GetOne */
  public ServiceCombo getServiceCombo()
  {
    return serviceCombo;
  }
  /* Code from template association_GetOne */
  public DownTime getDownTime()
  {
    return downTime;
  }

  public boolean hasDownTime()
  {
    boolean has = downTime != null;
    return has;
  }
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setBooking(Booking aBooking)
  {
    boolean wasSet = false;
    Booking existingBooking = booking;
    booking = aBooking;
    if (existingBooking != null && !existingBooking.equals(aBooking))
    {
      existingBooking.removeAppointment(this);
    }
    if (aBooking != null)
    {
      aBooking.addAppointment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setDownTime(DownTime aNewDownTime)
  {
    boolean wasSet = false;
    if (downTime != null && !downTime.equals(aNewDownTime) && equals(downTime.getAppointment()))
    {
      //Unable to setDownTime, as existing downTime would become an orphan
      return wasSet;
    }

    downTime = aNewDownTime;
    Appointment anOldAppointment = aNewDownTime != null ? aNewDownTime.getAppointment() : null;

    if (!this.equals(anOldAppointment))
    {
      if (anOldAppointment != null)
      {
        anOldAppointment.downTime = null;
      }
      if (downTime != null)
      {
        downTime.setAppointment(this);
      }
    }
    wasSet = true;
    return wasSet;
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
    if (booking != null)
    {
      Booking placeholderBooking = booking;
      this.booking = null;
      placeholderBooking.removeAppointment(this);
    }
    Service existingService = service;
    service = null;
    if (existingService != null)
    {
      existingService.delete();
    }
    ServiceCombo existingServiceCombo = serviceCombo;
    serviceCombo = null;
    if (existingServiceCombo != null)
    {
      existingServiceCombo.delete();
    }
    DownTime existingDownTime = downTime;
    downTime = null;
    if (existingDownTime != null)
    {
      existingDownTime.delete();
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
            "  " + "booking = "+(getBooking()!=null?Integer.toHexString(System.identityHashCode(getBooking())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "service = "+(getService()!=null?Integer.toHexString(System.identityHashCode(getService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "serviceCombo = "+(getServiceCombo()!=null?Integer.toHexString(System.identityHashCode(getServiceCombo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "downTime = "+(getDownTime()!=null?Integer.toHexString(System.identityHashCode(getDownTime())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null");
  }
}