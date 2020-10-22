package ca.mcgill.ecse.flexibook.application;

import java.sql.Date;
import java.sql.Time;

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
   
}

