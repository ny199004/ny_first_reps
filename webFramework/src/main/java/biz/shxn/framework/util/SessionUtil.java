package biz.shxn.framework.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import biz.shxn.framework.util.StringUtil;


/**
 * 对session进行包装，从session读写数据统一用此工具类进行操作
 * 对session进行包装，从session读写数据统一用此工具类进行操作
 * 如果到时将session数据存入分布式缓存服务器，有可以统一进行缓存读写操作
 */
public class SessionUtil {
    
	/**记录当前正登录系统的所有账号*/
	private static Map<String, Map<String, Object>> singleLogin = new HashMap<String, Map<String,Object>>();
	
	public static Map<String, Map<String, Object>> getSingleLogin() {
		return singleLogin;
	}

	public static void setSingleLogin(Map<String, Map<String, Object>> singleLogin) {
		SessionUtil.singleLogin = singleLogin;
	}
	
	/**
	 * 设置session属性值
	 * @param key  键名
	 * @param obj  键所对应的值
	 * @param session  会话
	 */
	public static void  setAttribute(String key, Object obj, HttpSession session){
		session.setAttribute(key, obj);
	}
	
	/**
	 * 获取session属性值
	 * @param key 键名
	 * @param session 会话
	 * @return Object session中的数据
	 */
	public static Object getAttribute(String key, HttpSession session){
		
		Object obj = session.getAttribute(key);
		if(StringUtil.checkObj(obj)){
			return obj;
		}
		return null;
	}
	

	
}