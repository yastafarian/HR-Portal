package com.yalarifi.hrportal.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	
	public static Date getInfinityDate() throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date toDate = sdf.parse("9999-01-01");
		return toDate;
	}
}
