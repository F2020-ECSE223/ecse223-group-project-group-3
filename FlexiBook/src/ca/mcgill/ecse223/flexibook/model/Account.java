package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 7 "model.ump"
// line 96 "model.ump"
// line 154 "model.ump"
public abstract class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String name;
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aName, String aPassword)
  {
    name = aName;
    password = aPassword;
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

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}