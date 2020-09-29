package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Time;

// line 20 "model.ump"
// line 106 "model.ump"
// line 164 "model.ump"
public class OwnerAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OwnerAccount Associations
  private FlexiBook flexiBook;
  private Business business;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OwnerAccount(String aName, String aPassword, FlexiBook aFlexiBook, Business aBusiness)
  {
    super(aName, aPassword);
    if (aFlexiBook == null || aFlexiBook.getOwnerAccount() != null)
    {
      throw new RuntimeException("Unable to create OwnerAccount due to aFlexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    flexiBook = aFlexiBook;
    if (aBusiness == null || aBusiness.getOwner() != null)
    {
      throw new RuntimeException("Unable to create OwnerAccount due to aBusiness. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    business = aBusiness;
  }

  public OwnerAccount(String aName, String aPassword, Business aBusinessForFlexiBook, String aNameForBusiness, Time aStartTimeForBusiness, Time aEndTimeForBusiness, String aPhoneNumberForBusiness, String aAddressForBusiness, String aEmailAddressForBusiness, FlexiBook aFlexiBookForBusiness)
  {
    super(aName, aPassword);
    flexiBook = new FlexiBook(this, aBusinessForFlexiBook);
    business = new Business(aNameForBusiness, aStartTimeForBusiness, aEndTimeForBusiness, aPhoneNumberForBusiness, aAddressForBusiness, aEmailAddressForBusiness, this, aFlexiBookForBusiness);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
  }

  public void delete()
  {
    FlexiBook existingFlexiBook = flexiBook;
    flexiBook = null;
    if (existingFlexiBook != null)
    {
      existingFlexiBook.delete();
    }
    Business existingBusiness = business;
    business = null;
    if (existingBusiness != null)
    {
      existingBusiness.delete();
    }
    super.delete();
  }

}