package biz.shxn.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类，
 */
@SuppressWarnings("unused")
public class StringUtil {

    /**
     * 判断字符串不为空
     * @param str 请求参数
     * @return boolean 返回类型
     */
	public static boolean checkStr(String str) {
		boolean bool = true;
		if (str == null || "".equals(str)){
			bool = false;
		}
		return bool;
	}

	/**
	 * 判断对象不为空(字符串、数组、map、list、set)
	 * @param obj 请求类型
	 * @return boolean 返回类型
	 */
	public static boolean checkObj(Object obj) {
		boolean bool = true;
		if (obj == null || "".equals(obj.toString().trim())){
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 判断对象不为空(字符串、数组、map、list、set)
	 * @param obj 请求类型
	 * @return boolean 返回类型
	 */
	public static boolean checkList(List obj) {
		boolean bool = true;
		if (obj == null || obj.isEmpty()){
			bool = false;
		}else{
			if(obj.get(0) == null)
				bool = false;
		}
		return bool;
	}

	/**
	 * 返回对象不为空的toString方法
	 * 使用场景？
	 * @param obj 请求对象
	 * @return String 返回类型
	 */
	public static String toString(Object obj) {
		return obj != null ? obj.toString().trim() : "";
	}
	
	/**
	 * 对象转数值
	 * String s = "20"; toInteger(s); // 20;
	 * Man man = new Man();
	 * man.toString(); // "15"
	 * toInteger(man); // 15
	 * @param obj 请求参数
	 * @return Integer 返回类型
	 */
	public static Integer toInteger(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : 0;
	}

	/**
	 * 字符串转数值，如果字符串为空，则返回-1；
	 * String s = "";
	 * toInt(s); // -1
	 * @param str 请求参数
	 * @return int 返回类型
	 */
	public static int toInt(String str) {
		return "".equals(str) ? -1 : Integer.parseInt(str);
	}

	/**
	 * 字符串编码从ISO8859_1转成GBK
	 * @param str 请求参数
	 * @return String 返回类型
	 */
	public static String getISOToGBK(String str) {
		String strName = "";
		try {
			if (str != null) {
				strName = new String(str.getBytes("ISO8859_1"), "GBK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strName;
	}

	/**
	 * 字符串编码从ISO8859_1转成UTF8
	 * @param str 请求参数
	 * @return String 返回类型
	 */
	public static String getISOToUTF8(String str) {
		String strName = "";
		try {
			if (str != null) {
				strName = new String(str.getBytes("ISO8859_1"), "UTF8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strName;
	}

	/**
	 * 根据参数获取随机值的整位数
	 * @param num 请求参数
	 * @return String 返回类型
	 */
	public static String getRandom(int num) {
		return (Math.random() + "").substring(2, num + 2);
	}

	/**
	 * 判断字符串是否中文
	 * @param str 请求参数
	 * @return boolean 返回类型
	 */
	public static boolean isChinese(String str) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 字符串首字母大写
	 * @param str 请求参数
	 * @return String 返回类型
	 */
	public static String toFirstUpperCase(String str) {
		if (str == null || "".equals(str.trim())){
			return "";
		}
		String firstChar = str.substring(0, 1).toUpperCase();
		String lastStr = str.substring(1);
		return firstChar + lastStr;
	}

	/**
	 * 判断一个字符串是不是数字;
	 * 必须以-、+、数字或.号为开头，允许以数字或小数点结尾，但不能是null,空字符或仅仅只是.号 ;
	 * 总之就是Double.parseDouble转换不报异常即可;
	 * 如 -1.；.1；1.；-.123；1.1；-12.12； 都会返回true 。
	 *    .1.；1 123；1-123；..1；1..；..；.； 都会返回false。
	 * @param str 字符串
	 * @return 是数值返回true,否则false
	 */
	public static boolean isNum(String str) {
		boolean flg = false;
		
		if(str != null && (str = str.trim()).length() > 0){
			String dbl = "((^(\\-|\\+)?([0-9]+)?(\\.)?)([0-9]+$))|" +
					"((^(\\-|\\+)?([0-9]+)?)[0-9]+([0-9\\.]$))";
			flg = Pattern.compile(dbl).matcher(str).matches();
		}
		return flg;
	}

	/**
	 * 判断一个字符串是不是整数
	 * @param str 字符串
	 * @return 是数值返回true,否则false
	 */
	public static boolean isInteger(String str){
		str = str.trim();
		String integer = "[\\-\\+]?[0-9]+$";
		boolean flg = Pattern.compile(integer).matcher(str).matches();
		return flg;
	}
	
	/**
	 * 去除字符串数组中的重复值
	 * @param stringArray 字符串数组
	 * @return 去重复的字符串数组
	 */
	public static String[] filterRepeat(String[] stringArray) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String str : stringArray) {
			if (!arrayList.contains(str)) {
				arrayList.add(str);
			}
		}
		return (String[]) arrayList.toArray(new String[] {});
	}

	/**
	 * 格式字符串 格式：a,b,v====>'a','b','v'
	 * @param str 请求参数
	 * @return String 返回类型
	 */
	public static String getFormatString(String str) {
		String strArr[] = str.split(",");
		String retStr = "";
		for (int i = 0; i < strArr.length; i++) {
			if (i > 0) {
				retStr = retStr + ",";
			}
			retStr = retStr + "'" + strArr[i] + "'";
		}
		return retStr;
	}

	/**
	 * 获取字符串的中文个数
	 * @param str 请求参数
	 * @return 中文个数
	 */
	public static int getChineseCount(String str) {
		return str.getBytes().length - str.length();
	}
	
	/**
	 * 效验字符串是否符合正则表达式格式
	 * @param value 值
	 * @param regexp 表达式
	 * @return 是否符合要求
	 */
	public static boolean checkRegexp(String value, String regexp) {
		return Pattern.compile(regexp).matcher(value).matches();
	}
	
	/**
	 * 转整型,大于10位转成Long,否则Integer
	 * @param value 被转换的值
	 * @return 整数
	 */
	private static Object toDigit(String value){
		return (value.length() >= 10) ? Long.valueOf(value) : StringUtil.toInt(value);
	}
	
	/**
	 * 截取带中文字符串的方法(中文占2个字符)
	 * @param str 字符串
	 * @param pstart 截取起始位置
	 * @param pend 截取长度
	 * @return String 结果字符串
	 */
	public static String getSubString(String str, int pstart, int pend) {
		if(str == null || str.length()==0)return "";
		String resu = "";
		int beg = 0;
		int end = 0;
		int count1 = 0;
		char[] temp = new char[str.length()];
		str.getChars(0, str.length(), temp, 0);
		boolean[] bol = new boolean[str.length()];
		for (int i = 0; i < temp.length; i++) {
			bol[i] = false;
			if ((int) temp[i] > 255) {// 说明是中文
				count1++;
				bol[i] = true;
			}
		}

		if (pstart > str.length() + count1) {
			resu = null;
		}
		if (pstart > pend) {
			resu = null;
		}
		if (pstart < 1) {
			beg = 0;
		} else {
			beg = pstart - 1;
		}
		if (pend > str.length() + count1) {
			end = str.length() + count1;
		} else {
			end = pend;// 在substring的末尾一样
		}
		// 下面开始求应该返回的字符串
		if (resu != null) {
			if (beg == end) {
				int count = 0;
				if (beg == 0) {
					if (bol[0] == true)
						resu = null;
					else
						resu = new String(temp, 0, 1);
				} else {
					int len = beg;// zheli
					for (int y = 0; y < len; y++) {// 表示他前面是否有中文,不管自己
						if (bol[y] == true)
							count++;
						len--;// 想明白为什么len--
					}
					// for循环运行完毕后，len的值就代表在正常字符串中，目标beg的上一字符的索引值
					if (count == 0) {// 说明前面没有中文
						if ((int) temp[beg] > 255)// 说明自己是中文
							resu = null;// 返回空
						else
							resu = new String(temp, beg, 1);
					} else {// 前面有中文，那么一个中文应与2个字符相对
						if ((int) temp[len + 1] > 255)// 说明自己是中文
							resu = null;// 返回空
						else
							resu = new String(temp, len + 1, 1);
					}
				}
			} else {// 下面是正常情况下的比较
				int temSt = beg;
				int temEd = end - 1;// 这里减掉一
				for (int i = 0; i < temSt; i++) {
					if (bol[i] == true)
						temSt--;
				}// 循环完毕后temSt表示前字符的正常索引
				for (int j = 0; j < temEd; j++) {
					if (bol[j] == true)
						temEd--;
				}// 循环完毕后temEd-1表示最后字符的正常索引
				if (bol[temSt] == true)// 说明是字符，说明索引本身是汉字的后半部分，那么应该是不能取的
				{
					int cont = 0;
					for (int i = 0; i <= temSt; i++) {
						cont++;
						if (bol[i] == true)
							cont++;
					}
					if (pstart == cont)// 是偶数不应包含,如果pstart<cont则要包含
						temSt++;// 从下一位开始
				}
				if (bol[temEd] == true) {// 因为temEd表示substring
											// 的最面参数，此处是一个汉字，下面要确定是否应该含这个汉字
					int cont = 0;
					for (int i = 0; i <= temEd; i++) {
						cont++;
						if (bol[i] == true)
							cont++;
					}
					if (pend < cont)// 是汉字的前半部分不应包含
						temEd--;// 所以只取到前一个
				}
				if (temSt == temEd) {
					resu = new String(temp, temSt, 1);
				} else if (temSt > temEd) {
					resu = null;
				} else {
					resu = str.substring(temSt, temEd + 1);
				}
			}
		}
		return resu;// 返回结果
	}
	
	/**
	 * 获取UUID
	 * @return UUID 返回类型
	 */
	public static String getUUID(){
		String uuId = java.util.UUID.randomUUID().toString().replaceAll("-", "");
		return uuId;
	}
	
	/**
	 * 获取IP地址
	 * @return IP地址
	 */
	public static String getIPAddress(){
		String ip = null;
		try {
			String os = System.getProperty("os.name");
			ip = os.startsWith("Windows") ? getIPOnWindows() : getIPOnLinux();
		} catch (Exception e) {
		}
		
		return ip;
	}
	
	/**
     * Windows下获取本机IP地址
     * @return IP地址
     */
    private static String getIPOnWindows() throws Exception{
        String ip = "";
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ipconfig");
            bufferedReader = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            String line = null;
            int index = -1;
            boolean isLocal = false;
            while ((line = bufferedReader.readLine()) != null) {
            	//找到本地连接信息
            	if(line.toLowerCase().indexOf("ethernet adapter 本地连接:") >= 0 ||
                		line.indexOf("以太网适配器 本地连接:") >=0 ||
                		line.indexOf("以太网适配器 以太网:") >=0){
                    isLocal = true;
                }
            	
            	//找到IP信息
            	if(line.toLowerCase().indexOf("ipv4") >= 0 || 
            			line.toLowerCase().indexOf("ip address") >= 0){
            		if(isLocal){
            			index = line.indexOf(":");
            			ip = line.substring(index + 1).trim();
                        break;
                    }
            	}
            }
            //没有取到本地连接中的IP信息，则通过Java API来获取
            if(!StringUtil.checkStr(ip)){
            	InetAddress inet = InetAddress.getLocalHost();
                ip = inet.getHostAddress();
            }
        } catch (IOException e) {
        	throw e;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
            }
            bufferedReader = null;
            process = null;
        }

        return ip;   
    }
    
    /**
     * Linux下获取本机IP地址
     * @return IP地址
     */
    private static String getIPOnLinux() throws Exception{
    	String ip = "";
		try {
			Enumeration<?> e1 = (Enumeration<?>) NetworkInterface
					.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				if (!ni.getName().equals("eth0")) {
					continue;
				} else {
					Enumeration<?> e2 = ni.getInetAddresses();
					while (e2.hasMoreElements()) {
						InetAddress ia = (InetAddress) e2.nextElement();
						if (ia instanceof Inet6Address)
							continue;
						ip = ia.getHostAddress();
					}
					break;
				}
			}
		} catch (Exception e) {
		}
		
		return ip; 
    }
    
    /**
     * 把一个list转化数组
     * @param list
     * @return
     */
    public static String [] list2Array(List<String> list){
    	int size = list.size();
    	String [] array = new String[size];
    	for(int i=0;i<size;i++){
    		array[i] = list.get(i);
    	}
		return array;
    	
    }
    /**
     * 把时间字符串如15:30转换成“0 30 15 **？*”
     * */
    public static String timeToExp(String time){
    	if(time == null){
    		return null;
    	}
    	String[] timeData = time.split(":");
    	
    	return "0 " + timeData[1] + " " + timeData[0] + " * * ? *";
    }
}