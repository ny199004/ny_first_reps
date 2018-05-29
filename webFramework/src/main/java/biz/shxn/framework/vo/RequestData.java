package biz.shxn.framework.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RequestData
 * @Description: 请求数据实体类
 * @author wangjf
 * @date 2017年5月23日
 * @version V1.0
 */
public class RequestData implements Serializable{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 4371814298342646125L;
	
	
	/**
	 * 方法名
	 */
	private String method;
	/**
	 * 当前页码
	 */
	private int pageno;
	/**
	 * 每页记录数
	 */
	private int pagesize;
	/**
	 * 具体请求数据
	 */
	private Object data;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
