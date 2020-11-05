package ca.mcgill.ecse.flexibook.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class SystemTime {

	private static Date sysDate = null;
	private static Time sysTime = null;

	public static Date getSysDate() {
		return sysDate;
	}

	public static Time getSysTime() {
		return sysTime;
	}

	public static void setSysDate(Date date) {
		sysDate = date;
	}

	public static void setSysTime(Time time) {
		sysTime = time;
	}
	
	public static void setSysDateAndTime(String s) {
		String date = s.substring(0, 10);
		String time = s.substring(11, 16);
		time = time+":00";
		sysDate = Date.valueOf(date);
		sysTime = Time.valueOf(time);
	}
	
}