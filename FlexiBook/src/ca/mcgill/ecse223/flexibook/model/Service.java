package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 26 "model.ump"
// line 111 "model.ump"
// line 170 "model.ump"
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String name;
  private int duration;
  private boolean isMandatory;

  //Service Associations
  private ServiceCombo serviceCombo;
  private List<ServiceCombo> serviceCombos;
  private FlexiBook flexiBook;
  private Appointment appointment;
  private Business business;
  private DownTime downTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aName, int aDuration, boolean aIsMandatory, ServiceCombo aServiceCombo, FlexiBook aFlexiBook, Appointment aAppointment, Business aBusiness)
  {
    name = aName;
    duration = aDuration;
    isMandatory = aIsMandatory;
    boolean didAddServiceCombo = setServiceCombo(aServiceCombo);
    if (!didAddServiceCombo)
    {
      throw new RuntimeException("Unable to create service due to serviceCombo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    serviceCombos = new ArrayList<ServiceCombo>();
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create service due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aAppointment == null || aAppointment.getService() != null)
    {
      throw new RuntimeException("Unable to create Service due to aAppointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointment = aAppointment;
    boolean didAddBusiness = setBusiness(aBusiness);
    if (!didAddBusiness)
    {
      throw new RuntimeException("Unable to create service due to business. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Service(String aName, int aDuration, boolean aIsMandatory, ServiceCombo aServiceCombo, FlexiBook aFlexiBook, Time aStartTimeForAppointment, Date aDateForAppointment, String aLocationForAppointment, Time aEndTimeForAppointment, ServiceCombo aServiceComboForAppointment, FlexiBook aFlexiBookForAppointment, Business aBusiness)
  {
    name = aName;
    duration = aDuration;
    isMandatory = aIsMandatory;
    boolean didAddServiceCombo = setServiceCombo(aServiceCombo);
    if (!didAddServiceCombo)
    {
      throw new RuntimeException("Unable to create service due to serviceCombo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    serviceCombos = new ArrayList<ServiceCombo>();
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create service due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointment = new Appointment(aStartTimeForAppointment, aDateForAppointment, aLocationForAppointment, aEndTimeForAppointment, this, aServiceComboForAppointment, aFlexiBookForAppointment);
    boolean didAddBusiness = setBusiness(aBusiness);
    if (!didAddBusiness)
    {
      throw new RuntimeException("Unable to create service due to business. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsMandatory(boolean aIsMandatory)
  {
    boolean wasSet = false;
    isMandatory = aIsMandatory;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getDuration()
  {
    return duration;
  }

  public boolean getIsMandatory()
  {
    return isMandatory;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsMandatory()
  {
    return isMandatory;
  }
  /* Code from template association_GetOne */
  public ServiceCombo getServiceCombo()
  {
    return serviceCombo;
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
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
  }
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasSet = false;
    //Must provide serviceCombo to service
    if (aServiceCombo == null)
    {
      return wasSet;
    }

    if (serviceCombo != null && serviceCombo.numberOfServices() <= ServiceCombo.minimumNumberOfServices())
    {
      return wasSet;
    }

    ServiceCombo existingServiceCombo = serviceCombo;
    serviceCombo = aServiceCombo;
    if (existingServiceCombo != null && !existingServiceCombo.equals(aServiceCombo))
    {
      boolean didRemove = existingServiceCombo.removeService(this);
      if (!didRemove)
      {
        serviceCombo = existingServiceCombo;
        return wasSet;
      }
    }
    serviceCombo.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceCombo addServiceCombo(String aName, int aNumberOfServices, FlexiBook aFlexiBook, Appointment aAppointment)
  {
    return new ServiceCombo(aName, aNumberOfServices, aFlexiBook, this, aAppointment);
  }

  public boolean addServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasAdded = false;
    if (serviceCombos.contains(aServiceCombo)) { return false; }
    Service existingMainService = aServiceCombo.getMainService();
    boolean isNewMainService = existingMainService != null && !this.equals(existingMainService);
    if (isNewMainService)
    {
      aServiceCombo.setMainService(this);
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
    //Unable to remove aServiceCombo, as it must always have a mainService
    if (!this.equals(aServiceCombo.getMainService()))
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
      existingFlexiBook.removeService(this);
    }
    flexiBook.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBusiness(Business aBusiness)
  {
    boolean wasSet = false;
    if (aBusiness == null)
    {
      return wasSet;
    }

    Business existingBusiness = business;
    business = aBusiness;
    if (existingBusiness != null && !existingBusiness.equals(aBusiness))
    {
      existingBusiness.removeService(this);
    }
    business.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setDownTime(DownTime aNewDownTime)
  {
    boolean wasSet = false;
    if (downTime != null && !downTime.equals(aNewDownTime) && equals(downTime.getService()))
    {
      //Unable to setDownTime, as existing downTime would become an orphan
      return wasSet;
    }

    downTime = aNewDownTime;
    Service anOldService = aNewDownTime != null ? aNewDownTime.getService() : null;

    if (!this.equals(anOldService))
    {
      if (anOldService != null)
      {
        anOldService.downTime = null;
      }
      if (downTime != null)
      {
        downTime.setService(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ServiceCombo placeholderServiceCombo = serviceCombo;
    this.serviceCombo = null;
    if(placeholderServiceCombo != null)
    {
      placeholderServiceCombo.removeService(this);
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
      placeholderFlexiBook.removeService(this);
    }
    Appointment existingAppointment = appointment;
    appointment = null;
    if (existingAppointment != null)
    {
      existingAppointment.delete();
    }
    Business placeholderBusiness = business;
    this.business = null;
    if(placeholderBusiness != null)
    {
      placeholderBusiness.removeService(this);
    }
    DownTime existingDownTime = downTime;
    downTime = null;
    if (existingDownTime != null)
    {
      existingDownTime.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "isMandatory" + ":" + getIsMandatory()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "serviceCombo = "+(getServiceCombo()!=null?Integer.toHexString(System.identityHashCode(getServiceCombo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "business = "+(getBusiness()!=null?Integer.toHexString(System.identityHashCode(getBusiness())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "downTime = "+(getDownTime()!=null?Integer.toHexString(System.identityHashCode(getDownTime())):"null");
  }
}