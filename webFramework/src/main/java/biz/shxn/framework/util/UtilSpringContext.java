package biz.shxn.framework.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
 */
public class UtilSpringContext {
	private static Log log = LogFactory.getLog(UtilSpringContext.class);
	// 可变常量 
	private static ApplicationContext applicationContext = null;

	static {
		// 构造Spring ApplicationContext。
		long l = System.currentTimeMillis();
		log.info("Load SpringContext Start");
		applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		log.info("Load SpringContext end--------------"+(System.currentTimeMillis()-l));
	}
	
	/**
	 * 依赖尽量使用注入的方式。减少对该方法的直接引用。
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
		
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * 关闭Spring ApplicationContext。一般应用代码不要调用。
	 */
	public static void close(){
		// 在ApplicationContext关闭时释放资源
		ApplicationContext ac = getApplicationContext();
		if (ac instanceof ConfigurableApplicationContext) {
			((ConfigurableApplicationContext) ac).close();
		}
	}

}

