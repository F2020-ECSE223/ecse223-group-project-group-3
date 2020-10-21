/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.flexibook.controller;
import java.util.*;

// line 40 "../../../../../../model.ump"
// line 105 "../../../../../../model.ump"
public abstract class TOBookableService
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, TOBookableService> tobookableservicesByName = new HashMap<String, TOBookableService>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBookableService Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBookableService(String aName)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      tobookableservicesByName.remove(anOldName);
    }
    tobookableservicesByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static TOBookableService getWithName(String aName)
  {
    return tobookableservicesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public void delete()
  {
    tobookableservicesByName.remove(getName());
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}