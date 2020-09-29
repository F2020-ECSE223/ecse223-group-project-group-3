package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;

// line 82 "model.ump"
// line 201 "model.ump"
public class DownTime
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DownTime Attributes
  private int duration;
  private Time startTime;
  private Time endTime;

  //DownTime Associations
  private Service service;
  private FlexiBook flexiBook;
  private Appointment appointment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DownTime(int aDuration, Time aStartTime, Time aEndTime, Service aService, FlexiBook aFlexiBook, Appointment aAppointment)
  {
    duration = aDuration;
    startTime = aStartTime;
    endTime = aEndTime;
    boolean didAddService = setService(aService);
    if (!didAddService)
    {
      throw new RuntimeException("Unable to create downTime due to service. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create downTime due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAppointment = setAppointment(aAppointment);
    if (!didAddAppointment)
    {
      throw new RuntimeException("Unable to create downTime due to appointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
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

  public int getDuration()
  {
    return duration;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }
  /* Code from template association_GetOne */
  public Service getService()
  {
    return service;
  }
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setService(Service aNewService)
  {
    boolean wasSet = false;
    if (aNewService == null)
    {
      //Unable to setService to null, as downTime must always be associated to a service
      return wasSet;
    }
    
    DownTime existingDownTime = aNewService.getDownTime();
    if (existingDownTime != null && !equals(existingDownTime))
    {
      //Unable to setService, the current service already has a downTime, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Service anOldService = service;
    service = aNewService;
    service.setDownTime(this);

    if (anOldService != null)
    {
      anOldService.setDownTime(null);
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
      existingFlexiBook.removeDownTime(this);
    }
    flexiBook.addDownTime(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setAppointment(Appointment aNewAppointment)
  {
    boolean wasSet = false;
    if (aNewAppointment == null)
    {
      //Unable to setAppointment to null, as downTime must always be associated to a appointment
      return wasSet;
    }
    
    DownTime existingDownTime = aNewAppointment.getDownTime();
    if (existingDownTime != null && !equals(existingDownTime))
    {
      //Unable to setAppointment, the current appointment already has a downTime, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Appointment anOldAppointment = appointment;
    appointment = aNewAppointment;
    appointment.setDownTime(this);

    if (anOldAppointment != null)
    {
      anOldAppointment.setDownTime(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Service existingService = service;
    service = null;
    if (existingService != null)
    {
      existingService.setDownTime(null);
    }
    FlexiBook placeholderFlexiBook = flexiBook;
    this.flexiBook = null;
    if(placeholderFlexiBook != null)
    {
      placeholderFlexiBook.removeDownTime(this);
    }
    Appointment existingAppointment = appointment;
    appointment = null;
    if (existingAppointment != null)
    {
      existingAppointment.setDownTime(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "service = "+(getService()!=null?Integer.toHexString(System.identityHashCode(getService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null");
  }
}