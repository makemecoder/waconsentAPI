package com.hdfc.waconsentAPI.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CommonUtility {
	
	public static String convertEpocTimeToDate(String epochTime) {
		
		Date outputDate = null;
		long epoch= Long.parseLong(epochTime);
	    System.out.println("Epoch Time:"+epoch);
	    LocalDateTime ldt = Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
	    System.out.println("LocalDateTime:"+ldt);		
	    DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formatDateTime = ldt.format(yyyyMMddHHmmss);
	    
	    try {
	    	outputDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
		return outputDate.toString();

	}
	
	public static String getCurrentTimestamp() {       
        Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());  
        return ts.toString();
	}
	
	public static String getCurrentTimestampWithFormat() {       
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date());  
        return timeStamp;
	}

}
