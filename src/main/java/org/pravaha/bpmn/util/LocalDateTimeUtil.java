package org.pravaha.bpmn.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.expression.ParseException;

public class LocalDateTimeUtil {
	
	
	public static Date getTodaysDate() throws java.text.ParseException {
	    Date todaysDate = Calendar.getInstance().getTime();
	    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    String outputDateStr = outputFormat.format(todaysDate); // Format the date to string
	    Date date = null;
	    try {
	        date = outputFormat.parse(outputDateStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return date;
	}

}
