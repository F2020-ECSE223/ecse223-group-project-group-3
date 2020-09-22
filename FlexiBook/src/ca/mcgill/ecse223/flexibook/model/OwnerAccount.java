package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 20 "model.ump"
// line 91 "model.ump"
// line 146 "model.ump"
public class OwnerAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OwnerAccount Associations
  private Business business;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OwnerAccount(String aName, String aPassword, Business aBusiness)
  {
    super(aName, aPassword);
    if (aBusiness == null || aBusiness.getOwner() != null)
    {
      throw new RuntimeException("Unable to create OwnerAccount due to aBusiness. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    business = aBusiness;
  }

  public OwnerAccount(String aName, String aPassword, String aNameForBusiness, String aBusinessHoursForBusiness, String aPhoneNumberForBusiness, String aHolidaysForBusiness, String aAddressForBusiness, String aEmailAddressForBusiness)
  {
    super(aName, aPassword);
    business = new Business(aNameForBusiness, aBusinessHoursForBusiness, aPhoneNumberForBusiness, aHolidaysForBusiness, aAddressForBusiness, aEmailAddressForBusiness, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
  }

  public void delete()
  {
    Business existingBusiness = business;
    business = null;
    if (existingBusiness != null)
    {
      existingBusiness.delete();
    }
    super.delete();
  }

}