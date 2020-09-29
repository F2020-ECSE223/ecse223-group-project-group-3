package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 76 "model.ump"
// line 196 "model.ump"
public class HDate
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HDate Attributes
  private Date date;

  //HDate Associations
  private FlexiBook flexiBook;
  private Business business;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HDate(Date aDate, FlexiBook aFlexiBook, Business aBusiness)
  {
    date = aDate;
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create hDate due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBusiness = setBusiness(aBusiness);
    if (!didAddBusiness)
    {
      throw new RuntimeException("Unable to create holiday due to business. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }
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
      existingFlexiBook.removeHDate(this);
    }
    flexiBook.addHDate(this);
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
      existingBusiness.removeHoliday(this);
    }
    business.addHoliday(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    FlexiBook placeholderFlexiBook = flexiBook;
    this.flexiBook = null;
    if(placeholderFlexiBook != null)
    {
      placeholderFlexiBook.removeHDate(this);
    }
    Business placeholderBusiness = business;
    this.business = null;
    if(placeholderBusiness != null)
    {
      placeholderBusiness.removeHoliday(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "business = "+(getBusiness()!=null?Integer.toHexString(System.identityHashCode(getBusiness())):"null");
  }
}