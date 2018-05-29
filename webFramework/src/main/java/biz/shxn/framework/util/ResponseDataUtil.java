package biz.shxn.framework.util;

import biz.shxn.framework.vo.ResponseData;

/**
 * @ClassName: ResponseDataUtil
 * @Description: ResponseData 构造类
 * @author wangjf
 * @date 2017年5月24日
 * @version V1.0
 */
public class ResponseDataUtil {

	/**
	 * <p>Title: getSuccessResponse<／p>
	 * <p>Description: 构造成功响应数据<／p>
	 * @param data
	 * @return
	 * @author wangjf
	 * @version 1.0
	 */
	public static ResponseData getSuccessResponse(Object data){
		ResponseData responseData = new ResponseData();
		responseData.setRet(1);
		responseData.setMsg("success");
		responseData.setData(data);
		return responseData;
	}
	
	/**
	 * <p>Title: getFailResponse<／p>
	 * <p>Description: 构造失败响应数据<／p>
	 * @param errorMsg
	 * @return
	 * @author wangjf
	 * @version 1.0
	 */
	public static ResponseData getFailResponse(String errorMsg){
		ResponseData responseData = new ResponseData();
		responseData.setRet(0);
		responseData.setMsg(errorMsg);
		return responseData;
	}
}
