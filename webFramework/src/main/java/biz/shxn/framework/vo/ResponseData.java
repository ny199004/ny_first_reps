package biz.shxn.framework.vo;

/** 
 * @ClassName: ResponseData
 * @Description: 响应数据实体类
 * @author wangjf
 * @date 2017年5月24日
 * @version V1.0
 */
public class ResponseData {

	/**
	 * 返回结果编码
	 */
	private int ret;
    /**
     * 返回结果信息
     */
	private String msg;
	/**
	 * 返回具体数据
	 */
	private Object data;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
