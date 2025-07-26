package com.custom.met.cmmn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/**
	 * <pre>
	 * 메소드명: format
	 * 설명: format 형식의 {@code String} 반환
	 * </pre>
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.format(date);
	}
}
