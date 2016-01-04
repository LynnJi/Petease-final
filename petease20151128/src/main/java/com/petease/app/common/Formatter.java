package com.petease.app.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class Formatter {
	public static String convertTo2Digtal(int n)
	{
		return n>9?String.valueOf(n):"0"+String.valueOf(n);
	}
	
	public static String date2String(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return String.valueOf(cal.get(Calendar.YEAR))+"-"
				+Formatter.convertTo2Digtal(cal.get(Calendar.MONTH)+1)+"-"
				+Formatter.convertTo2Digtal(cal.get(Calendar.DAY_OF_MONTH));
	}
	
	public static Date string2Date(String dateStr) {
		String[] dateStrArray = dateStr.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateStrArray[0]), Integer.parseInt(dateStrArray[1])-1, Integer.parseInt(dateStrArray[2]));
		return cal.getTime();
	}
	
	public static String datetime2String(Date datetime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(datetime);
		return String.valueOf(cal.get(Calendar.YEAR))+"-"
				+Formatter.convertTo2Digtal(cal.get(Calendar.MONTH)+1)+"-"
				+Formatter.convertTo2Digtal(cal.get(Calendar.DAY_OF_MONTH))+" "
				+Formatter.convertTo2Digtal(cal.get(Calendar.HOUR_OF_DAY))+":"
				+Formatter.convertTo2Digtal(cal.get(Calendar.MINUTE))+":"
				+Formatter.convertTo2Digtal(cal.get(Calendar.SECOND));
	}
	
	public static Date string2Datetime(String datetimeStr) {
		String[] datetimeStrArray = datetimeStr.split(" ");
		String dateStr = datetimeStrArray[0];
		String timeStr = datetimeStrArray[1];
		String[] dateStrArray = dateStr.split("-");
		String[] timeStrArray = timeStr.split(":");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateStrArray[0]), Integer.parseInt(dateStrArray[1])-1, Integer.parseInt(dateStrArray[2]),
				Integer.parseInt(timeStrArray[0]), Integer.parseInt(timeStrArray[1]), Integer.parseInt(timeStrArray[2]));
		return cal.getTime();
	}
	
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
 
}
