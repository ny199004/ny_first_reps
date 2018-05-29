package biz.shxn.framework.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.shxn.framework.util.StringUtil;

/**Request对象的工具类
 */
public class RequestUtil {

	/**异步请求返回
	 * @param encoding 编码格式
	 * @param data data
	 * @param response HttpServletResponse
	 */
	public static void responseOut(String encoding, String data,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=" + encoding);
		try {
			PrintWriter pw = response.getWriter();
			pw.print(data);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**获取request对象中所有参数，并设置到map中
	 * @param request HttpServletRequest
	 * @return 将request的请求参数封装成map（包括URL传和通过form表单提交的参数）
	 */
	@SuppressWarnings("rawtypes")
	public static Map getMapByRequest(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			String paraValue = request.getParameter(paraName);
			if (paraValue != null && !"".equals(paraValue)) {
				map.put(paraName, paraValue.trim());
			}
		}
		return map;
	}

	/**针对jquery的ajax请求
	 * @param request HttpServletRequest
	 * @return true:是Ajax请求
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		return (header != null && "XMLHttpRequest".equals(header));
	}
	
	/**
	 * 获取绝对路径
	 * @param request HttpServletRequest对象
	 * @return 应用绝对路径
	 */
	public static String getRealPath(HttpServletRequest request){
		String realPath = RequestUtil.getRealPath(
				request.getSession().getServletContext());
		return realPath;
	}
	
	/**
	 * 获取绝对路径
	 * @param context ServletContext对象
	 * @return 应用绝对路径
	 */
	public static String getRealPath(ServletContext context){
		//系统文件分隔符
		String separator = System.getProperty("file.separator");
		String realPath = context.getRealPath("/");
		realPath = realPath.endsWith(separator) ? realPath : (realPath + separator);
		return realPath;
	}
	
	/**获得请求客户端的IP
	 * @param request HttpServletRequest
	 * @return IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr(); //取值可能为0:0:0:0:0:0:0:1
		}
		
		if (ip != null && (ip.indexOf("127.0.0.1") >= 0 || ip.indexOf("0:0:0:0:0:0:0:1") >= 0
				|| ip.indexOf("localhost") >= 0)) {
			ip = StringUtil.getIPAddress();
		}
		return ip;
	}
}