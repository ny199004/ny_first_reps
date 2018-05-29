package biz.shxn.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import biz.shxn.framework.vo.RequestData;

/**
 * @ClassName: ParamGetterUtil
 * @Description: 请求参数获取工具
 * @author wangjf
 * @date 2017年5月23日
 * @version V1.0
 */
public class ParamGetterUtil{

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * <p>Title: getData<／p>
	 * <p>Description: 获取具体请求参数<／p>
	 * @param requestData
	 * @param clazz
	 * @return
	 * @author wangjf
	 * @version 1.0
	 * @return 
	 * @return 
	 */
	public static <T>T getData(RequestData requestData, Class<T> clazz){
		Object data = requestData.getData();
		T obj = null;
		if(StringUtil.checkObj(data)){
			obj = (T) objectMapper.convertValue(data, clazz);
		}
		return obj;
	}
}
