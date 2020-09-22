package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 65 "model.ump"
// line 171 "model.ump"
public class ServiceCombo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceCombo Attributes
  private String name;
  private String numberOfServices;

  //ServiceCombo Associations
  private List<Service> services;
  private Service mainService;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceCombo(String aName, String aNumberOfServices, Service aMainService)
  {
    name = aName;
    numberOfServices = aNumberOfServices;
    services = new ArrayList<Service>();
    boolean didAddMainService = setMainService(aMainService);
    if (!didAddMainService)
    {
      throw new RuntimeException("Unable to create serviceCombo due to mainService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setNumberOfServices(String aNumberOfServices)
  {
    boolean wasSet = false;
    numberOfServices = aNumberOfServices;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getNumberOfServices()
  {
    return numberOfServices;
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
  public Service getMainService()
  {
    return mainService;
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
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Service addService(String aName, int aDuration, boolean aIsMandatory, boolean aIsDowntime, int aDowntime, Appointment aAppointment, Business aBusiness)
  {
    Service aNewService = new Service(aName, aDuration, aIsMandatory, aIsDowntime, aDowntime, this, aAppointment, aBusiness);
    return aNewService;
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    ServiceCombo existingCombo = aService.getCombo();
    boolean isNewCombo = existingCombo != null && !this.equals(existingCombo);

    if (isNewCombo && existingCombo.numberOfServices() <= minimumNumberOfServices())
    {
      return wasAdded;
    }
    if (isNewCombo)
    {
      aService.setCombo(this);
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
    //Unable to remove aService, as it must always have a combo
    if (this.equals(aService.getCombo()))
    {
      return wasRemoved;
    }

    //combo already at minimum (2)
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
  /* Code from template association_SetOneToMany */
  public boolean setMainService(Service aMainService)
  {
    boolean wasSet = false;
    if (aMainService == null)
    {
      return wasSet;
    }

    Service existingMainService = mainService;
    mainService = aMainService;
    if (existingMainService != null && !existingMainService.equals(aMainService))
    {
      existingMainService.removeServiceCombo(this);
    }
    mainService.addServiceCombo(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=services.size(); i > 0; i--)
    {
      Service aService = services.get(i - 1);
      aService.delete();
    }
    Service placeholderMainService = mainService;
    this.mainService = null;
    if(placeholderMainService != null)
    {
      placeholderMainService.removeServiceCombo(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "numberOfServices" + ":" + getNumberOfServices()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mainService = "+(getMainService()!=null?Integer.toHexString(System.identityHashCode(getMainService())):"null");
  }
}