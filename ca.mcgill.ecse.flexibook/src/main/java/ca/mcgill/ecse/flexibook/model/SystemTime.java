package ca.mcgill.ecse.flexibook.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class SystemTime {

	private static Date sysDate = null;
	private static Time sysTime = null;

	/*
	 * @author: Fadi Tawfik Beshay
	 */
	public static Date getSysDate() {
		return sysDate;
	}

	/*
	 * @author: Fadi Tawfik Beshay
	 */
	public static Time getSysTime() {
		return sysTime;
	}

	/*
	 * @author: Eric Chehata
	 */
	public static void setSysDate(Date date) {
		sysDate = date;
	}

	/*
	 * @author: Eric Chehata
	 */
	public static void setSysTime(Time time) {
		sysTime = time;
	}
	
	/*
	 * @author: Robert Aprahamian
	 * @param: s, String that corresponds to the system date and time
	 */
	public static void setSysDateAndTime(String s) {
		String date = s.substring(0, 10);
		String time = s.substring(11, 16);
		String[] dateInArray = date.split("-");
		String[] timeInArray = time.split(":");
		LocalDate localDate = LocalDate.of(Integer.parseInt(dateInArray[0]), Integer.parseInt(dateInArray[1]), Integer.parseInt(dateInArray[2]));
		LocalTime localTime = LocalTime.of(Integer.parseInt(timeInArray[0]), Integer.parseInt(timeInArray[1]));
		sysDate = Date.valueOf(localDate);
		sysTime = Time.valueOf(localTime);
	}
	
}