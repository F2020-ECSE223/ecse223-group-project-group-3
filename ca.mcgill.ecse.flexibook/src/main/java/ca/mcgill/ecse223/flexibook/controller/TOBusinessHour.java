/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse223.flexibook.controller;
import java.sql.Time;

// line 25 "../../../../../FlexiBookTransferObjects.ump"
public class TOBusinessHour
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TODayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBusinessHour Attributes
  private TODayOfWeek TODayOfWeek;
  private Time startTime;
  private Time endTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBusinessHour(TODayOfWeek aTODayOfWeek, Time aStartTime, Time aEndTime)
  {
    TODayOfWeek = aTODayOfWeek;
    startTime = aStartTime;
    endTime = aEndTime;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTODayOfWeek(TODayOfWeek aTODayOfWeek)
  {
    boolean wasSet = false;
    TODayOfWeek = aTODayOfWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public TODayOfWeek getTODayOfWeek()
  {
    return TODayOfWeek;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "TODayOfWeek" + "=" + (getTODayOfWeek() != null ? !getTODayOfWeek().equals(this)  ? getTODayOfWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}