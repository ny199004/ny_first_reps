/**
 * 
 */
package biz.shxn.framework.common;


/**
 * @author wangjf
 * @version 1.0, 2017/04/24
 *
 */
public class Constants extends BaseConstants {
	
	/**
	 * 服务接口名称
	 */
	public static final String USER_SERVICE = "userRoles";
	public static final String SYSCONFIG_SERVICE = "sysConfig";
	
	/**
	 * 用户状态1:已删除，0：可用，2:锁定
	 */
	public static final String USER_NOR_DEL = "0";
	public static final String USER_DEL = "1";
	public static final String USER_LOCK = "2";
	/**
	 * 连续输入密码错误5次以上锁定用户
	 */
	public static final int LOCKUSER_COUNT = 5;
	/**
	 * 登录用户保留时间
	 */
	public static final int SESSION_TIMEOUT = 1200;
	
	//放置校验码的
		public final static String SESSION_RANDOM_CODE="SESSION_RANDOM_CODE";
		public final static String SESSION_CURRENT_MEMBER="currentMember";
		public final static String SESSION_CURRENT_ACCOUNT="currentAccount";
		public final static String SESSION_CURRENT_GROUPCUSTOMER="currentGroupCustomer";
		public final static String SESSION_CURRENT_SUPPLIER="currentSupplier";
		public final static String SESSION_CURRENT_STORE="currentStore";
		public final static String SESSION_CURRENT_MEMBER_LOGIN="memberLoginFlag";
		public final static String SESSION_CURRENT_ADMIN_LOGIN="accountLoginFlag";
		public final static String SESSION_ACCOUNT_LOGIN_COUNT="accountLoginCount";
		public final static String SESSION_MEMBER_LOGIN_COUNT="memberLoginCount";
		public final static String SESSION_ACCOUNT_RIGHT_URL="accountRightUrl";

}
