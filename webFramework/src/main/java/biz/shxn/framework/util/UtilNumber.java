/**   
* @Title: UtilNumber.java
* @Package biz.shuangxin.framework.util
* @date 2012-9-25 下午04:02:59
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

/**
* @ClassName: UtilNumber
* @Description: 数字辅助类
* @author wangjf
* @version 1.0, 2017/04/24
* 
*/
public class UtilNumber {

	/**
	* @Title: parseDouble
	* @Description: 将字符转换为Double
	* @param str
	* @return 
	*/
	public static Double parseDouble(String str)
	{
		if(str==null || "".equals(str))
		{
			return 0D;
		}else
		{
			return Double.parseDouble(str);
		}
	}
}
