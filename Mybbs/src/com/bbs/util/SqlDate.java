package com.bbs.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlDate {
	public static java.sql.Date toDate(java.util.Date date) {
		java.sql.Date sqldate = new Date(date.getTime());
		return sqldate;
	}

	public static java.sql.Date toDate(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqldate = new Date(date.getTime());
		return sqldate;
	}
}
