package biz.shxn.framework.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

/** 
 * 类说明 
 * @author wangjf
 * @version 1.0, 2017/04/24
 * @since 2014-6-18 下午03:23:18 
 */
@SuppressWarnings("rawtypes")
public class JackJson {
	
	/**
	 * 对象转json串
	 * @param obj 传入对象
	 * @return String 返回类型
	 */
	public static String getBaseJsonData(Object obj){
		StringWriter writer = new StringWriter();
		if(obj != null){
			ObjectMapper mapper = new ObjectMapper();
			try {
				mapper.writeValue(writer, obj);
			} catch(Exception e){
				e.getStackTrace();
			}
		}
		return writer.toString();
    } 
	
	/**
	 * json数组文本串转集合
	 * @param json 字符创
	 * @return List 返回集合
	 */
	@SuppressWarnings("unchecked")
	public static List<LinkedHashMap<String, Object>> getListByJsonArray(
			String json){
		List<LinkedHashMap<String, Object>> list = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			list = mapper.readValue(json, List.class); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * json串转对象
	 * @param json 字符创
	 * @param c 类
	 * @return Object 返回对象
	 */
	@SuppressWarnings("unchecked")
	public static Object getObjectByJson(String json, Class c){
		Object obj = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			obj = mapper.readValue(json, c);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * json串转Map
	 * @param jsonStr 字符创
	 * @return  Map 返回类型
	 */
	public static Map getMapByJsonString(String jsonStr){
		HashMap m = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			m = mapper.readValue(jsonStr, HashMap.class); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * 将JSON字符串转换为Map实现类对象
	 * @param jsonStr 字符创
	 * @param c 类类型
	 * @return Map 返回类型
	 */
	public static Map getMapByJsonString(String jsonStr, Class<? extends Map> c){
		Map m = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			m = mapper.readValue(jsonStr, c); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
}