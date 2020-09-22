package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 25 "model.ump"
// line 96 "model.ump"
// line 151 "model.ump"
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String name;
  private int duration;
  private boolean isMandatory;
  private boolean isDowntime;
  private int downtime;

  //Service Associations
  private ServiceCombo combo;
  private List<ServiceCombo> serviceCombos;
  private Appointment appointment;
  private Business business;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aName, int aDuration, boolean aIsMandatory, boolean aIsDowntime, int aDowntime, ServiceCombo aCombo, Appointment aAppointment, Business aBusiness)
  {
    name = aName;
    duration = aDuration;
    isMandatory = aIsMandatory;
    isDowntime = aIsDowntime;
    downtime = aDowntime;
    boolean didAddCombo = setCombo(aCombo);
    if (!didAddCombo)
    {
      throw new RuntimeException("Unable to create service due to combo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    serviceCombos = new ArrayList<ServiceCombo>();
    boolean didAddAppointment = setAppointment(aAppointment);
    if (!didAddAppointment)
    {
      throw new RuntimeException("Unable to create service due to appointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setIsDowntime(boolean aIsDowntime)
  {
    boolean wasSet = false;
    isDowntime = aIsDowntime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDowntime(int aDowntime)
  {
    boolean wasSet = false;
    downtime = aDowntime;
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

  public boolean getIsDowntime()
  {
    return isDowntime;
  }

  public int getDowntime()
  {
    return downtime;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsMandatory()
  {
    return isMandatory;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsDowntime()
  {
    return isDowntime;
  }
  /* Code from template association_GetOne */
  public ServiceCombo getCombo()
  {
    return combo;
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
  public Appointment getAppointment()
  {
    return appointment;
  }
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setCombo(ServiceCombo aCombo)
  {
    boolean wasSet = false;
    //Must provide combo to service
    if (aCombo == null)
    {
      return wasSet;
    }

    if (combo != null && combo.numberOfServices() <= ServiceCombo.minimumNumberOfServices())
    {
      return wasSet;
    }

    ServiceCombo existingCombo = combo;
    combo = aCombo;
    if (existingCombo != null && !existingCombo.equals(aCombo))
    {
      boolean didRemove = existingCombo.removeService(this);
      if (!didRemove)
      {
        combo = existingCombo;
        return wasSet;
      }
    }
    combo.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceCombo addServiceCombo(String aName, String aNumberOfServices)
  {
    return new ServiceCombo(aName, aNumberOfServices, this);
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setAppointment(Appointment aAppointment)
  {
    boolean wasSet = false;
    //Must provide appointment to service
    if (aAppointment == null)
    {
      return wasSet;
    }

    if (appointment != null && appointment.numberOfServices() <= Appointment.minimumNumberOfServices())
    {
      return wasSet;
    }

    Appointment existingAppointment = appointment;
    appointment = aAppointment;
    if (existingAppointment != null && !existingAppointment.equals(aAppointment))
    {
      boolean didRemove = existingAppointment.removeService(this);
      if (!didRemove)
      {
        appointment = existingAppointment;
        return wasSet;
      }
    }
    appointment.addService(this);
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

  public void delete()
  {
    ServiceCombo placeholderCombo = combo;
    this.combo = null;
    if(placeholderCombo != null)
    {
      placeholderCombo.removeService(this);
    }
    for(int i=serviceCombos.size(); i > 0; i--)
    {
      ServiceCombo aServiceCombo = serviceCombos.get(i - 1);
      aServiceCombo.delete();
    }
    Appointment placeholderAppointment = appointment;
    this.appointment = null;
    if(placeholderAppointment != null)
    {
      placeholderAppointment.removeService(this);
    }
    Business placeholderBusiness = business;
    this.business = null;
    if(placeholderBusiness != null)
    {
      placeholderBusiness.removeService(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "isMandatory" + ":" + getIsMandatory()+ "," +
            "isDowntime" + ":" + getIsDowntime()+ "," +
            "downtime" + ":" + getDowntime()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "combo = "+(getCombo()!=null?Integer.toHexString(System.identityHashCode(getCombo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "business = "+(getBusiness()!=null?Integer.toHexString(System.identityHashCode(getBusiness())):"null");
  }
}