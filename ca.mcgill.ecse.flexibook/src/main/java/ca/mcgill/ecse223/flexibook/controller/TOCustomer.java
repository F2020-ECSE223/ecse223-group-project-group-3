/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.flexibook.controller;

// line 5 "../../../../../FlexiBookTransferObjects.ump"
public class TOCustomer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOCustomer Attributes
  private String name;
  private int noShow;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOCustomer(String aName, int aNoShow)
  {
    name = aName;
    noShow = aNoShow;
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

  public boolean setNoShow(int aNoShow)
  {
    boolean wasSet = false;
    noShow = aNoShow;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getNoShow()
  {
    return noShow;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "noShow" + ":" + getNoShow()+ "]";
  }
}