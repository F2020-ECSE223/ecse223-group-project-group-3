package ca.mcgill.ecse223.flexibook.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 63 "model.ump"
// line 147 "model.ump"
// line 185 "model.ump"
public class Booking
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Booking Associations
  private CustomerAccount customer;
  private FlexiBook flexiBook;
  private List<Appointment> appointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Booking(CustomerAccount aCustomer, FlexiBook aFlexiBook)
  {
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create booking due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create booking due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointments = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public CustomerAccount getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(CustomerAccount aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    CustomerAccount existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeBooking(this);
    }
    customer.addBooking(this);
    wasSet = true;
    return wasSet;
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
      existingFlexiBook.removeBooking(this);
    }
    flexiBook.addBooking(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    Booking existingBooking = aAppointment.getBooking();
    if (existingBooking == null)
    {
      aAppointment.setBooking(this);
    }
    else if (!this.equals(existingBooking))
    {
      existingBooking.removeAppointment(aAppointment);
      addAppointment(aAppointment);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    if (appointments.contains(aAppointment))
    {
      appointments.remove(aAppointment);
      aAppointment.setBooking(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    CustomerAccount placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeBooking(this);
    }
    FlexiBook placeholderFlexiBook = flexiBook;
    this.flexiBook = null;
    if(placeholderFlexiBook != null)
    {
      placeholderFlexiBook.removeBooking(this);
    }
    while( !appointments.isEmpty() )
    {
      appointments.get(0).setBooking(null);
    }
  }

}