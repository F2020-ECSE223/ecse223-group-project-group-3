/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Time;

// line 94 "../../../../../FlexiBook.ump"
public class SystemTime
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SystemTime()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}


  /**
   * @author: Fadi Tawfik Beshay
   */
  // line 108 "../../../../../FlexiBook.ump"
   public static  Date getSysDate(){
    return sysDate;
  }


  /**
   * @author: Fadi Tawfik Beshay
   */
  // line 115 "../../../../../FlexiBook.ump"
   public static  Time getSysTime(){
    return sysTime;
  }


  /**
   * @author: Eric Chehata
   */
  // line 122 "../../../../../FlexiBook.ump"
   public static  void setSysDate(Date date){
    sysDate = date;
  }


  /**
   * @author: Eric Chehata
   */
  // line 129 "../../../../../FlexiBook.ump"
   public static  void setSysTime(Time time){
    sysTime = time;
  }


  /**
   * @author: Robert Aprahamian
   * @param: s, String that corresponds to the system date and time
   */
  // line 137 "../../../../../FlexiBook.ump"
   public static  void setSysDateAndTime(String s){
    String date = s.substring(0, 10);
		String time = s.substring(11, 16);
		String[] dateInArray = date.split("-");
		String[] timeInArray = time.split(":");
		LocalDate localDate = LocalDate.of(Integer.parseInt(dateInArray[0]), Integer.parseInt(dateInArray[1]), Integer.parseInt(dateInArray[2]));
		LocalTime localTime = LocalTime.of(Integer.parseInt(timeInArray[0]), Integer.parseInt(timeInArray[1]));
		sysDate = Date.valueOf(localDate);
		sysTime = Time.valueOf(localTime);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 101 "../../../../../FlexiBook.ump"
  private static Date sysDate = null ;
// line 102 "../../../../../FlexiBook.ump"
  private static Time sysTime = null ;

  
}