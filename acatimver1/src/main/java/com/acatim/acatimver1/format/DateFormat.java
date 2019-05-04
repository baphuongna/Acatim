package com.acatim.acatimver1.format;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateFormat {

	public String currentDate() {
		Date date = new Date();
		long time = date.getTime();
		Timestamp currentDate = new Timestamp(time);
		return currentDate.toString();
	}

	public String StringToDateSQL(String date) {
		String formatedDate;
		try {
			Date initDate = new SimpleDateFormat("MM/dd/yy").parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(initDate);
			
			formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
					+ cal.get(Calendar.DATE);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}
		return formatedDate;
	}

	public String StringToDateTimeSQL(String date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// String dateString = format.format(new Date());
		Date dateSQL;
		try {
			dateSQL = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return dateSQL.toString();
	}
	
	public String RandomCode() {
		Random r = new Random();

	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    String code = "";
	    for (int i = 0; i < 20; i++) {
	    	code += alphabet.charAt(r.nextInt(alphabet.length()));
	    }
	    
		return code;
	}
	
	public String RandomPassword() {
		Random r = new Random();

	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    String code = "";
	    for (int i = 0; i < 9; i++) {
	    	code += alphabet.charAt(r.nextInt(alphabet.length()));
	    }
	    
		return code;
	}
	
	public String RandomKeyConfirm() {
		Random r = new Random();

	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    String code = "";
	    for (int i = 0; i < 40; i++) {
	    	code += alphabet.charAt(r.nextInt(alphabet.length()));
	    }
	    
		return code;
	}
	
	public boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}

}
