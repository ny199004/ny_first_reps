package biz.shxn.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	static SimpleDateFormat sdfLongTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmmss");
    static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat ddfLongTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	static SimpleDateFormat ddfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat ddfDate = new SimpleDateFormat("yyyy-MM-dd");
    
    /**获取当前日期时间
    *传入参数为整数8,14,17
    *根据传入长度返回8,14,17的字符串日期值
    **/
    public static String getStringDateTime(int len){
    	Date date=new Date();
    	String cdate="";
    	if(len==17){
    		cdate=sdfLongTime.format(date);
    	}else if(len==14){
    		cdate=sdfTime.format(date);
    	}else if(len==8){
    		cdate=sdfDate.format(date);
    	}
    	return cdate;
    	
    }
    
    /**
     * 
     * @param Idate数据
     * @param len需要剪切的长度
     * @return剪切后返回String类型
     */
    public static String getSubStringDateTime(String Idate,int len){
    	String rDate="";
    	if(Idate!=null && !Idate.equals("")){
	    	if(len==17){
	    		rDate=Idate;
	    	}else if(len==14){
	    		rDate=Idate.substring(0,len);
	    	}else if(len==8){
	    		rDate=Idate.substring(0,len);
	    	}
    	}
    	return rDate;
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
     * 截取
     * 入参是string 日期时间类型如20120608100000,len是长度14,8,17
     * @param date
     * @return返回输入的同等长度时间
     */
    public static String getDateSubString(String date,int len){
    	if(date!=null&&len>0){
	    	if(len==14){
	    		return date.substring(0,14);
	    	}else if(len==8){
	    		return date.substring(0,8);
	    	}else if(len==6){
	    		return date.substring(0,6);
	    	}
    	}
    	return null;
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
     * 获取系统时间
     */
    public static String getDateHourMinue(int len){
    	String date="";
    	if(len==10){
    		date=ddfDate.format(new Date());
    	}else if(len==19){
    		date=ddfTime.format(new Date());
    	}else if(len==23){
    		date=ddfLongTime.format(new Date());
    	}
    	return date;
    }
    /**
     * 与当前天差几天的某天
     * @param days 相差的天数
     * @return 返回的时间格式为20120827
     */
    public static String getDate(int days){
    	Calendar current =Calendar.getInstance();
    	current.add(Calendar.DAY_OF_MONTH, days);
    	Date date = current.getTime();
    	String day = sdfDate.format(date);
    	return day;
    }
    /**
     * 指定某天的开始和结束时间
     * @param date 格式20120827
     * @return
     */
    public static String[] getDateStartAndEnd(String date){
    	if(date == null || date.length()<1){
    		return null;
    	}
    	String[] dates = new String[2];
    	dates[0] = date+"0000000";
    	dates[1] = date+"2359599";
    	return dates;
    }
    /**
     * 相差几天的某天的起始时间
     * @return
     */
    public static String[] getStartAndEnd(int days){
    	Calendar current =Calendar.getInstance();
    	current.add(Calendar.DAY_OF_MONTH, days);
    	Date date = current.getTime();
    	String day = sdfDate.format(date);
    	String[] dates = new String[2];
    	dates[0] = day+"000000";
    	dates[1] = day+"235959";
    	return dates;
    }
    /**
     * 今天是礼拜几
     * 0-星期日
     * 6-星期六
     * @return
     */
    public static int getweekday(){
    	Calendar calendar = Calendar.getInstance();
    	int weekday = calendar.get(Calendar.DAY_OF_WEEK);
    	weekday -=1;
    	return weekday;
    }
    /**
     * 当前时间是几点(24HH)
     */
    public static int gethour(){
    	Calendar calendar = Calendar.getInstance();
    	return calendar.get(Calendar.HOUR_OF_DAY);
    }
    public static void main(String[] args){
    	String[] a = getStartAndEnd(-1);
    	System.out.println(a[0]+"==="+a[1]);
//    	String pw = "";
//    	for (int i = 0; i < 1; i++)
//    	{
//    	        char c = (char) (int) (97);
//    	        pw += c;
//    	}
//    	System.out.println(pw.toUpperCase());
//    	String a="20120628010101001";
//    	try {
//			String b=ddfLongTime.format(sdfLongTime.parse(a));
//			System.out.println(b);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	String a = "A11";
//    	String _a=a.substring(a.length()-3,1);
//    	String b=String.valueOf(Integer.parseInt(a.substring(a.length()-2))+1);
//    	if(b.length()<2){
//    		b="0"+b;
//    	}
//    	char _b=_a.charAt(0);
//    	System.out.println(_a);
//    	System.out.println((char)(((int)a.substring(a.length()-3).substring(0,1).charAt(0))+1));
//    	if(){
//    		
//    	}
    }
}
