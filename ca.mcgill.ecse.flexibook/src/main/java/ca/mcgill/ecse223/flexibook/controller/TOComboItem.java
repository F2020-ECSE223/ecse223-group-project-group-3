package ca.mcgill.ecse223.flexibook.controller;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 66 "model.ump"
// line 148 "model.ump"
public class TOComboItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOComboItem Attributes
  private boolean mandatory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOComboItem(boolean aMandatory)
  {
    mandatory = aMandatory;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMandatory(boolean aMandatory)
  {
    boolean wasSet = false;
    mandatory = aMandatory;
    wasSet = true;
    return wasSet;
  }

  public boolean getMandatory()
  {
    return mandatory;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isMandatory()
  {
    return mandatory;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "mandatory" + ":" + getMandatory()+ "]";
  }
}