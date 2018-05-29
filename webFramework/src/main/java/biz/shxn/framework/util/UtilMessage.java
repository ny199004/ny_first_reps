package biz.shxn.framework.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * @author wangjf
 * @version 1.0, 2017/04/24
*/
public class UtilMessage {

	private static final Log log = LogFactory.getLog(UtilMessage.class);

	/**
	 * 缓存信息，其中的value的类型是java.util.Map
	 */
	private static final Map bundleLocaleCache = new HashMap();
	

	public static String getMessage(String name) {
		return getMessageByResource(parserResource(name),name,  getDefaultLocale());
	}
	
	public static String getMessage(String name,Object[] arguments) {
		return getMessageByResource(parserResource(name),name,arguments,  getDefaultLocale());
	}
	
	public static String getMessage(String name,
			Object[] arguments,Locale locale) {
		return getMessageByResource(parserResource(name),name, arguments, locale==null? getDefaultLocale():locale);
	}

	/**
	 * 根据locale，信息资源文件取得信息
	 * 
	 * @param resource
	 *            信息资源文件，如: exampleMessage
	 * @param name
	 *            信息key
	 * @param arguments
	 *            信息体包含的变量值
	 * @param locale
	 *            如：zh_CN等对应的Locale
	 * @return 信息值
	 */
	protected static String getMessageByResource(String resource, String name,
			Object[] arguments, Locale locale) {
		String value = getMessageByResource(resource, name, locale);
		if (value == null) {
			return "";
		} else if (value.trim().length() == 0) {
			return value;
		} else {
			if (arguments != null && arguments.length > 0) {
				value = MessageFormat.format(value, arguments);
			}
			return value;
		}
	}

	/**
	 * 根据locale，信息资源文件取得信息
	 * 
	 * @param resource
	 *            信息资源文件，如: exampleMessage
	 * @param name
	 *            信息key
	 * @param locale
	 *            如：zh_CN等对应的Locale
	 * @return 信息值
	 */
	protected static String getMessageByResource(String resource, String name, Locale locale) {
		if (resource == null || resource.trim().length() == 0)
			return "";
		if (name == null || name.trim().length() == 0)
			return "";

		ResourceBundle bundle = getResourceBundle(resource, locale);
		if (bundle == null)
			return "";

		String value = null;
		try {
			value = bundle.getString(name);
		} catch (Exception e) {
			// can not find resource or error
			log.warn("",e);
		}
		return value == null ? "" : value.trim();
	}

	/**
	 * 取得<tt>ResourceBundle</tt>
	 */
	protected static ResourceBundle getResourceBundle(String resource,
			Locale locale) {
		if (resource == null || resource.trim().length() == 0 || locale == null) {
			return null;
		}
		String resourceCacheKey = resource + "_" + locale.toString();
		ResourceBundle result = (ResourceBundle) bundleLocaleCache
				.get(resourceCacheKey);
		if (result == null) {
			// find and cache if no null
			result = getBaseResourceBundle(resource, locale);
			if (result != null) {
				bundleLocaleCache.put(resourceCacheKey, result);
			}
		}
		return result;
	}
	
	/**
	 * 根据name（key）来取得对应的i18n文件的resource名称，如mps-bas-message
	 * @param name mps.bas.0000
	 * @return mps-bas-message
	 */
	protected static String parserResource(String name){
		if(name == null || name.trim().length()==0){
			return null;
		}
		//
		String[] splits = name.split("[.]");
		if(splits == null || splits.length<3){
			return null;
		}else{
			return splits[0]+"-"+splits[1]+"-message";
		}
	}

	protected static ResourceBundle getBaseResourceBundle(String resource,
			Locale locale) {
		if (resource == null || resource.length() <= 0)
			return null;
		if (locale == null)
			locale = Locale.getDefault();

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle(resource, locale, loader);
		} catch (MissingResourceException e) {
			log.error("Could not find resource: " + resource + " for locale "
					+ locale.toString() + ": " + e.toString());
			return null;
		}
		if (bundle == null) {
			log.error("Could not find resource: " + resource + " for locale "
					+ locale.toString());
			return null;
		}

		return bundle;
	}
	
	private static Locale defaultLocale = Locale.SIMPLIFIED_CHINESE;
	
	private static Locale getDefaultLocale(){
		return defaultLocale;
	}
	
	public static void setDefaultLocale(Locale locale){
		if(locale != null){
			defaultLocale = locale;
		}
	}
}
