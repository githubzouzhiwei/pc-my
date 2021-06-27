package cn.pconline.pcloud.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类型
 * @author Haif
 * @date 2019年7月18日  
 * @description
 */
public class DateUtil {
	
	/**
	 * 日期对象转为字符串
	 * @param date 日期对象
	 * @param pattern 格式化模式
	 * @return
	 */
	public static String format(Date date, String pattern) {
        try {
        	if(date == null) {
        		return "";
        	}
        	
        	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/**
	 * 日期对象转为'yyyy-MM-dd HH:mm:ss'字符串
	 * @param date 日期对象
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 格式字符串为日期对象
	 * @param dateTime 日期字符串
	 * @param pattern 格式化模式
	 * @return
	 */
	public static Date parse(String dateTime, String pattern) {
        try {
        	if(dateTime == null || dateTime.length() <= 0) {
        		return null;
        	}
        	
        	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        	return sdf.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	
	/**
	 * 格式字符串为'yyyy-MM-dd HH:mm:ss'日期对象
	 * @param dateTime 日期字符串
	 * @return
	 */
	public static Date parse(String dateTime) {
		return parse(dateTime, "yyyy-MM-dd HH:mm:ss");
	}
	
	
	
}
