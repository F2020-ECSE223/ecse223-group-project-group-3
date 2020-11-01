package ca.mcgill.ecse.flexibook.application;

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
		String[] dateAndTime = s.split("+");
		String[] date = dateAndTime[0].split("-");
		String[] time = dateAndTime[1].split(":");
		LocalDate localDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		LocalTime localTime = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
		sysDate = Date.valueOf(localDate);
		sysTime = Time.valueOf(localTime);
		
	}
}
