/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.flexibook.controller;

// line 47 "../../../../../FlexiBookTransferObjects.ump"
public class TOServiceCombo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOServiceCombo Attributes
  private String name;
  private String mainService;
  private String opServices;
  private String mandatoryServices;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOServiceCombo(String aName, String aMainService, String aOpServices, String aMandatoryServices)
  {
    name = aName;
    mainService = aMainService;
    opServices = aOpServices;
    mandatoryServices = aMandatoryServices;
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

  public boolean setMainService(String aMainService)
  {
    boolean wasSet = false;
    mainService = aMainService;
    wasSet = true;
    return wasSet;
  }

  public boolean setOpServices(String aOpServices)
  {
    boolean wasSet = false;
    opServices = aOpServices;
    wasSet = true;
    return wasSet;
  }

  public boolean setMandatoryServices(String aMandatoryServices)
  {
    boolean wasSet = false;
    mandatoryServices = aMandatoryServices;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getMainService()
  {
    return mainService;
  }

  public String getOpServices()
  {
    return opServices;
  }

  public String getMandatoryServices()
  {
    return mandatoryServices;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "mainService" + ":" + getMainService()+ "," +
            "opServices" + ":" + getOpServices()+ "," +
            "mandatoryServices" + ":" + getMandatoryServices()+ "]";
  }
}