/**   
* @Title: UtilDate.java
* @Package biz.shuangxin.framework.util
* @date 2012-7-11 上午11:35:57
* @version V1.0   
* 
* All rights reserved.
*
* This software is the confidential and proprietary information of
* LongTopOnline Corporation ("Confidential Information").  You
* shall not disclose such Confidential Information and shall use
* it only in accordance with the terms of the license agreement
* you entered into with LongTopOnline.
*/
package biz.shxn.framework.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
* @ClassName: UtilDate
* @Description: 日期辅助类
 * @author wangjf
 * @version 1.0, 2017/04/24
* 
*/
public class UtilDate {
	static SimpleDateFormat sdfLongTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmmss");
    static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat ddfLongTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	static SimpleDateFormat ddfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat ddfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getCurrentTime()
	{
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	/**
	 * 取得当前的Timestamp
	 *
	 * @return 当前的Timestamp
	 */
	public static java.sql.Timestamp nowTimestamp()
	{
		return getTimestamp(System.currentTimeMillis());
	}

	public static java.sql.Timestamp getTimestamp(long time)
	{
		return new java.sql.Timestamp(time);
	}

	public static java.util.Date nowDate()
	{
		return new java.util.Date();
	}

	/**
	 * 抽取日期的年
	 */
	public static int getYear(java.util.Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	public static int getMonth(java.util.Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH)+1;
	}
	public static int getDay(java.util.Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	public static int getMinute(java.util.Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);
	}

	public static String formatDate(Date date, String pattern)
	{
		if (date == null) {
			return "";
		}
		if (pattern == null) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return (sdf.format(date));
	}

	public static String formatDateTime(Date date)
	{
		return (formatDate(date, "yyyy-MM-dd HH:mm:ss"));
	}

	public static String formatDateTime()
	{
		return (formatDate(now(), "yyyy-MM-dd HH:mm:ss"));
	}
	
	public static String formatDateHour(Date date)
	{
		return (formatDate(date, "yyyy-MM-dd HH"));
	}
	
	public static String formatDateHour()
	{
		return (formatDate(now(), "yyyy-MM-dd HH"));
	}
	
	public static String formatDateMonth(Date date)
	{
		return (formatDate(date, "yyyy-MM"));
	}
	
	public static String formatDateMonth()
	{
		return (formatDate(now(), "yyyy-MM"));
	}

	public static String formatDate(Date date)
	{
		return (formatDate(date, "yyyy-MM-dd"));
	}

	public static String formatDate(Date date, boolean flag)
	{
		return (formatDate(date, "yyyyMMdd"));
	}

	public static String formatDate()
	{
		return (formatDate(now(), "yyyy-MM-dd"));
	}

	public static String formatTime(Date date)
	{
		return (formatDate(date, "HH:mm:ss"));
	}

	public static String formatTime()
	{
		return (formatDate(now(), "HH:mm:ss"));
	}

	public static String formatTime2()
	{
		return (formatDate(now(), "HHmmss"));
	}

	public static Date now()
	{
		return (new Date());
	}

	public static Date parseDateTime(String datetime)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ((datetime == null) || (datetime.equals(""))) {
			return null;
		} else {
			try {
				return formatter.parse(datetime);
			} catch (ParseException e) {
				return parseDate(datetime);
			}
		}
	}
	
	/**
	 * 字符串转化成date类型
	 * @param datetime yyyyMMddHHmmssSSS
	 * @return Date
	 */
	public static Date parse17DateTime(String datetime)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		if ((datetime == null) || (datetime.equals(""))) {
			return null;
		} else {
			try {
				return formatter.parse(datetime);
			} catch (ParseException e) {
				return parseDate(datetime);
			}
		}
	}
	 /**
     * 将数据库的string日期类型转成日期格式,len是按长度转换
     * 入参如20010101返回2001-01-01
     * @param date
     * @param len
     * @return
     */
    public static String getStringToDate(String date,int len){
    	String strTodate="";
    	if(date!=""){
    		if(len==8){
	    		try {
					strTodate=ddfDate.format(sdfDate.parse(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else if(len==14){
    	    		try {
    					strTodate=ddfTime.format(sdfTime.parse(date));
    				} catch (ParseException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        	}else if(len==17){
	    		try {
					strTodate=ddfLongTime.format(sdfLongTime.parse(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
    	}
    	return strTodate;
    }
	/**
	 * 字符串类型日期转化成"yyyy-MM"格式
	 * 如果日期字符串全部都是0，即“00000000000000000”则返回至今；
	 * @param datetime yyyyMMddHHmmssSSS
	 * @return yyyy-MM
	 */
	public static String parse17YearMonth(String datetime)
	{
		if("00000000000000000".equals(datetime)){
			return "至今";
		}
		Date date=parse17DateTime(datetime);
		return date==null?null:getYear(date)+"-"+getMonth(date);
	}
	/**
	 * 字符串类型日期转化成"yyyy-MM-dd"格式
	 * 如果日期字符串全部都是0，即“00000000000000000”则返回至今；
	 * @param datetime yyyyMMddHHmmssSSS
	 * @return	yyyy-MM-dd
	 */
	public static String parse17YearMonthDay(String datetime)
	{
		if("00000000000000000".equals(datetime)){
			return "至今";
		}
		Date date=parse17DateTime(datetime);
		return date==null?null:getYear(date)+"-"+getMonth(date)+"-"+getDay(date);
	}

	public static Date parseDate(String date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if ((date == null) || (date.equals(""))) {
			return null;
		} else {
			try {
				return formatter.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
	}
	
	public static Date parseDate(String date,String pattern){
		if ((date == null) || (date.equals(""))) {
			return null;
		} else {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				return formatter.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
	}
	
	public static Date parseDate(Date datetime)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if (datetime == null) {
			return null;
		} else {
			try {
				return formatter.parse(formatter.format(datetime));
			} catch (ParseException e) {
				return null;
			}
		}
	}

	public static Date parseDateYYMMDD(String date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		if ((date == null) || (date.equals(""))) {
			return null;
		} else {
			try {
				return formatter.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	public static String formatDate(Object o)
	{
		if (o == null) {
			return "";
		}
		if (o.getClass() == String.class) {
			return formatDate((String) o);
		} else if (o.getClass() == Date.class) {
			return formatDate((Date) o);
		} else if (o.getClass() == Timestamp.class) {
			return formatDate(new Date(((Timestamp) o).getTime()));
		} else {
			return o.toString();
		}
	}

	public static String formatDateTime(Object o)
	{
		if (o.getClass() == String.class) {
			return formatDateTime((String) o);
		} else if (o.getClass() == Date.class) {
			return formatDateTime((Date) o);
		} else if (o.getClass() == Timestamp.class) {
			return formatDateTime(new Date(((Timestamp) o).getTime()));
		} else {
			return o.toString();
		}
	}

	/**
	 * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
	 *
	 * @param date   要加减前的时间，如果不传，则为当前日期
	 * @param field  时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br>
	 *               Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
	 * @param amount 按指定时间域加减的时间数量，正数为加，负数为减。
	 * @return 变动后的时间
	 */
	public static Date add(Date date, int field, int amount)
	{
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	public static Date addMilliSecond(Date date, int amount)
	{
		return add(date, Calendar.MILLISECOND, amount);
	}

	public static Date addSecond(Date date, int amount)
	{
		return add(date, Calendar.SECOND, amount);
	}

	public static Date addMiunte(Date date, int amount)
	{
		return add(date, Calendar.MINUTE, amount);
	}

	public static Date addHour(Date date, int amount)
	{
		return add(date, Calendar.HOUR, amount);
	}

	public static Date addDay(Date date, int amount)
	{
		return add(date, Calendar.DATE, amount);
	}

	public static Date addMonth(Date date, int amount)
	{
		return add(date, Calendar.MONTH, amount);
	}

	public static Date addYear(Date date, int amount)
	{
		return add(date, Calendar.YEAR, amount);
	}

	public static Date getDate()
	{
		return parseDate(formatDate());
	}

	public static Date getDateTime()
	{
		return parseDateTime(formatDateTime());
	}

	public static Date parseDate(String date, boolean flag)
	{
		if (flag) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

			if ((date == null) || (date.equals(""))) {
				return null;
			} else {
				try {
					Date a = formatter.parse(date);
					return a;
				} catch (ParseException e) {
					return null;
				}
			}
		} else {
			return parseDate(date);
		}
	}

	/**
	 * 将字符串型日期转换为日期型
	 * 
	 * @param strDate
	 *            字符串型日期
	 * @param srcDateFormat
	 *            源日期格式
	 * @param dstDateFormat
	 *            目标日期格式
	 * @return Date 返回的util.Date型日期
	 */
	public static Date stringToDate(String strDate, String srcDateFormat,
			String dstDateFormat)
	{
		Date rtDate = null;
		Date tmpDate = (new SimpleDateFormat(srcDateFormat)).parse(strDate,
				new ParsePosition(0));
		String tmpString = null;
		if (tmpDate != null)
			tmpString = (new SimpleDateFormat(dstDateFormat)).format(tmpDate);
		if (tmpString != null)
			rtDate = (new SimpleDateFormat(dstDateFormat)).parse(tmpString,
					new ParsePosition(0));
		return rtDate;
	}
	
	/**
	* @Title: string10ToDate
	* @Description: 2011/04/15转换成date 
	* @param string
	* @return 
	*/
	public static Date string10ToDate(String string)
	{
		 	
		 return stringToDate(string.replace("/", "-"), "yyyy-MM-dd",
		"yyyy-MM-dd");
	}
	
	
	public static String getCurrentTime(int i)
	{
		String returnStr = null;
		SimpleDateFormat f = null;
		if(i==17)
		{
			f = new SimpleDateFormat("yyyyMMddHHmmssSSS");	
		}else if(i==14)
		{
			f = new SimpleDateFormat("yyyyMMddHHmmss");
		}
		
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	
	public static String parseDate(String date,String pattern1,String pattern2){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern2);
		Date dt=parseDate(date,pattern1);
		return sdf.format(dt);
	}
	
	/**
	 * @Title: changeDay
	 * @Description: TODO(天数加减)
	 * @param date
	 * @param offset
	 * @return Date
	 */
	public static Date changeDay(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,
				(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}
	
    /**
     * 入参是string 日期时间类型如2012-06-08 10:00,2012-06-08等,len是长度
     * @param date
     * @return返回输入的同等长度时间
     */
    public static String getHourMinue(String date,int len){
    	Date date1=new Date();
    	String sDate="";
		date=date.trim();
    	if(date!=null){
    		if(date.indexOf("-")>-1){
		    	try {
					if(len==14){
						date1=ddfTime.parse(date);
						sDate=sdfTime.format(date1);
				    }else if(len==8){
						date1=ddfDate.parse(date);
				    	sDate=sdfDate.format(date1);
					}else if(len==17){
						date1=ddfLongTime.parse(date);
						sDate=sdfLongTime.format(date1);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
    		}else{
    			sDate=date;
    		}
    	}
    	return sDate;
    }
    
    /** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public static int getDaysByYearMonth(int year, int month) {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    } 
    /**
     * 获取某一天内的首尾时间戳  精确到秒
     */
    public static long[] getTimeStampByDay(String timeStr){
    	long[] timeStamp=new long[2];
    	DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = df.parse(timeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c1=new GregorianCalendar();
		c1.setTime(date);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		timeStamp[0]=c1.getTimeInMillis()/1000;
		c1.set(Calendar.HOUR_OF_DAY, 23);
		c1.set(Calendar.MINUTE, 59);
		c1.set(Calendar.SECOND, 59);
		timeStamp[1]=c1.getTimeInMillis()/1000;
		return timeStamp;
    }
    /**
     * 获取某一月内的首尾时间戳  精确到秒
     */
    public static long[] getTimeStampByMonth(String timeStr){
    	long[] timeStamp=new long[2];
    	DateFormat df=new SimpleDateFormat("yyyy-MM");
		Date date=null;
		try {
			date = df.parse(timeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Calendar c1=new GregorianCalendar();
		int firstDay = c1.getActualMinimum(Calendar.DAY_OF_MONTH); 
		c1.setTime(date);
		c1.set(Calendar.DAY_OF_MONTH, firstDay);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		timeStamp[0]=c1.getTimeInMillis()/1000;
		int lastDay = c1.getActualMaximum(Calendar.DAY_OF_MONTH); 
		c1.set(Calendar.DAY_OF_MONTH, lastDay);
		c1.set(Calendar.HOUR_OF_DAY, 23);
		c1.set(Calendar.MINUTE, 59);
		c1.set(Calendar.SECOND, 59);
		timeStamp[1]=c1.getTimeInMillis()/1000;
		return timeStamp;
    }
    /**
     * 获取某一年内的首尾时间戳  精确到秒
     * @param timeStr
     * @return
     */
    public static long[] getTimeStampByYear(String timeStr){
    	long[] timeStamp=new long[2];
    	DateFormat df=new SimpleDateFormat("yyyy");
		Date date=null;
		try {
			date = df.parse(timeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Calendar c1=new GregorianCalendar();
		int firstDay = c1.getActualMinimum(Calendar.DAY_OF_MONTH); 
		c1.setTime(date);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, firstDay);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		timeStamp[0]=c1.getTimeInMillis()/1000;
		int lastDay = c1.getActualMaximum(Calendar.DAY_OF_MONTH); 
		c1.set(Calendar.MONTH, 11);
		c1.set(Calendar.DAY_OF_MONTH, lastDay);
		c1.set(Calendar.HOUR_OF_DAY, 23);
		c1.set(Calendar.MINUTE, 59);
		c1.set(Calendar.SECOND, 59);
		timeStamp[1]=c1.getTimeInMillis()/1000;
		return timeStamp;
    }
    
}
