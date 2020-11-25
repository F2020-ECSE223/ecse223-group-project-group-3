/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.flexibook.controller;
import java.sql.Date;
import java.util.*;

// line 49 "../../../../../FlexiBookTransferObjects.ump"
public class TOAppointmentCalendarItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOAppointmentCalendarItem Attributes
  private Date date;

  //TOAppointmentCalendarItem Associations
  private List<TOTimeSlot> availableTimeSlots;
  private List<TOTimeSlot> unavailableTimeSlots;
  private List<TOAppointment> tOAppointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOAppointmentCalendarItem(Date aDate)
  {
    date = aDate;
    availableTimeSlots = new ArrayList<TOTimeSlot>();
    unavailableTimeSlots = new ArrayList<TOTimeSlot>();
    tOAppointments = new ArrayList<TOAppointment>();
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
  /* Code from template association_GetMany */
  public TOTimeSlot getAvailableTimeSlot(int index)
  {
    TOTimeSlot aAvailableTimeSlot = availableTimeSlots.get(index);
    return aAvailableTimeSlot;
  }

  public List<TOTimeSlot> getAvailableTimeSlots()
  {
    List<TOTimeSlot> newAvailableTimeSlots = Collections.unmodifiableList(availableTimeSlots);
    return newAvailableTimeSlots;
  }

  public int numberOfAvailableTimeSlots()
  {
    int number = availableTimeSlots.size();
    return number;
  }

  public boolean hasAvailableTimeSlots()
  {
    boolean has = availableTimeSlots.size() > 0;
    return has;
  }

  public int indexOfAvailableTimeSlot(TOTimeSlot aAvailableTimeSlot)
  {
    int index = availableTimeSlots.indexOf(aAvailableTimeSlot);
    return index;
  }
  /* Code from template association_GetMany */
  public TOTimeSlot getUnavailableTimeSlot(int index)
  {
    TOTimeSlot aUnavailableTimeSlot = unavailableTimeSlots.get(index);
    return aUnavailableTimeSlot;
  }

  public List<TOTimeSlot> getUnavailableTimeSlots()
  {
    List<TOTimeSlot> newUnavailableTimeSlots = Collections.unmodifiableList(unavailableTimeSlots);
    return newUnavailableTimeSlots;
  }

  public int numberOfUnavailableTimeSlots()
  {
    int number = unavailableTimeSlots.size();
    return number;
  }

  public boolean hasUnavailableTimeSlots()
  {
    boolean has = unavailableTimeSlots.size() > 0;
    return has;
  }

  public int indexOfUnavailableTimeSlot(TOTimeSlot aUnavailableTimeSlot)
  {
    int index = unavailableTimeSlots.indexOf(aUnavailableTimeSlot);
    return index;
  }
  /* Code from template association_GetMany */
  public TOAppointment getTOAppointment(int index)
  {
    TOAppointment aTOAppointment = tOAppointments.get(index);
    return aTOAppointment;
  }

  public List<TOAppointment> getTOAppointments()
  {
    List<TOAppointment> newTOAppointments = Collections.unmodifiableList(tOAppointments);
    return newTOAppointments;
  }

  public int numberOfTOAppointments()
  {
    int number = tOAppointments.size();
    return number;
  }

  public boolean hasTOAppointments()
  {
    boolean has = tOAppointments.size() > 0;
    return has;
  }

  public int indexOfTOAppointment(TOAppointment aTOAppointment)
  {
    int index = tOAppointments.indexOf(aTOAppointment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAvailableTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addAvailableTimeSlot(TOTimeSlot aAvailableTimeSlot)
  {
    boolean wasAdded = false;
    if (availableTimeSlots.contains(aAvailableTimeSlot)) { return false; }
    availableTimeSlots.add(aAvailableTimeSlot);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAvailableTimeSlot(TOTimeSlot aAvailableTimeSlot)
  {
    boolean wasRemoved = false;
    if (availableTimeSlots.contains(aAvailableTimeSlot))
    {
      availableTimeSlots.remove(aAvailableTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAvailableTimeSlotAt(TOTimeSlot aAvailableTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addAvailableTimeSlot(aAvailableTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAvailableTimeSlots()) { index = numberOfAvailableTimeSlots() - 1; }
      availableTimeSlots.remove(aAvailableTimeSlot);
      availableTimeSlots.add(index, aAvailableTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAvailableTimeSlotAt(TOTimeSlot aAvailableTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(availableTimeSlots.contains(aAvailableTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAvailableTimeSlots()) { index = numberOfAvailableTimeSlots() - 1; }
      availableTimeSlots.remove(aAvailableTimeSlot);
      availableTimeSlots.add(index, aAvailableTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAvailableTimeSlotAt(aAvailableTimeSlot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUnavailableTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addUnavailableTimeSlot(TOTimeSlot aUnavailableTimeSlot)
  {
    boolean wasAdded = false;
    if (unavailableTimeSlots.contains(aUnavailableTimeSlot)) { return false; }
    unavailableTimeSlots.add(aUnavailableTimeSlot);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUnavailableTimeSlot(TOTimeSlot aUnavailableTimeSlot)
  {
    boolean wasRemoved = false;
    if (unavailableTimeSlots.contains(aUnavailableTimeSlot))
    {
      unavailableTimeSlots.remove(aUnavailableTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUnavailableTimeSlotAt(TOTimeSlot aUnavailableTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addUnavailableTimeSlot(aUnavailableTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUnavailableTimeSlots()) { index = numberOfUnavailableTimeSlots() - 1; }
      unavailableTimeSlots.remove(aUnavailableTimeSlot);
      unavailableTimeSlots.add(index, aUnavailableTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUnavailableTimeSlotAt(TOTimeSlot aUnavailableTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(unavailableTimeSlots.contains(aUnavailableTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUnavailableTimeSlots()) { index = numberOfUnavailableTimeSlots() - 1; }
      unavailableTimeSlots.remove(aUnavailableTimeSlot);
      unavailableTimeSlots.add(index, aUnavailableTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUnavailableTimeSlotAt(aUnavailableTimeSlot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTOAppointments()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTOAppointment(TOAppointment aTOAppointment)
  {
    boolean wasAdded = false;
    if (tOAppointments.contains(aTOAppointment)) { return false; }
    tOAppointments.add(aTOAppointment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTOAppointment(TOAppointment aTOAppointment)
  {
    boolean wasRemoved = false;
    if (tOAppointments.contains(aTOAppointment))
    {
      tOAppointments.remove(aTOAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTOAppointmentAt(TOAppointment aTOAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addTOAppointment(aTOAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTOAppointments()) { index = numberOfTOAppointments() - 1; }
      tOAppointments.remove(aTOAppointment);
      tOAppointments.add(index, aTOAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTOAppointmentAt(TOAppointment aTOAppointment, int index)
  {
    boolean wasAdded = false;
    if(tOAppointments.contains(aTOAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTOAppointments()) { index = numberOfTOAppointments() - 1; }
      tOAppointments.remove(aTOAppointment);
      tOAppointments.add(index, aTOAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTOAppointmentAt(aTOAppointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    availableTimeSlots.clear();
    unavailableTimeSlots.clear();
    tOAppointments.clear();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}