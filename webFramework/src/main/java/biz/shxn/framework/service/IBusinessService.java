package biz.shxn.framework.service;

import java.io.Serializable;
import java.util.List;

import biz.shxn.framework.vo.ValueObject;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
 */
public interface IBusinessService {
	
//============================================================================
//======================================Save操作===============================
//============================================================================
	/**
	 * 插入数据，采用iBatis完成<br>
	 * <p/>
	 * 使用方法：<br>
	 * <pre>
	 * 		1:假设iBatis配置文件中的操作语句为：<br>
	 * 		&lt;sqlMap namespace="Account"&gt;		<br>
	 * 			......<br>
	 * 			&lt;insert id="insertAccount" parameterMap="insertParam"&gt;<br>
	 * 				insert into ACCOUNT ( accountky, accountno,CREATEDTIME) values ( ?,?,?)<br>
	 * 			&lt;/insert&gt;<br>
	 * 			......<br>
	 * 		&lt;/sqlMap &gt;<br>
	 * 		则可见参数params将以parameterMap的形式传递<br>
	 * 		2：调用save_iBatis方法<br>
	 * 		String sqlmap_name="Account.insertAccount";<br>
	 * 		AccountVO accountVO=new AccountVO();<br>
	 * 		......<br>
	 * 		defautMediator.save_iBatis(sqlmap_name, new Object[]{accountVO});<br>
	 * </pre>
	 *
	 * @param queryID 在iBatis映射文件中配置的sql语句编号<br>
	 * @param params  参数列表，可为null
	 * @return 操作所影响的记录数
	 * @
	 */
	public int save_iBatis(final String queryID, final Object[] params) ;

	//===============================================================================================
	//======================================Delete操作================================================
	//===============================================================================================
	/**
	 * 删除操作，采用iBatis完成<br> <br>
	 * 使用方法1<br>
	 * <pre>
	 * 		1:iBatis的配置文件：<br>
	 * 			&lt;delete id="deleteAccountById" parameterClass="Long"&gt;<br>
	 * 	  			delete from ACCOUNT where accountky = #objectId# <br>
	 * 			&lt;/delete&gt;<br>
	 * 		可见其中的参数是以java基本数据对象的parameterClass形式进行传递的，保存在数值的Index=0的位置<br>
	 * 		2：调用<br>
	 * 		String ibatis_delete="Account.deleteAccountById";	<br>
	 * 		defautMediator.delete_iBatis (ibatis_delete,new Object[]{555555L});<br>
	 * </pre>
	 * <p/>
	 * 使用方法2<br>
	 * <pre>
	 * 		1:iBatis的配置文件：<br>
	 * 			&lt;delete id="deleteAccountByParamClass" parameterClass="Account"&gt;<br>
	 * 				delete from ACCOUNT where accountky = #objectId# and accountno= #accountNo#<br>
	 * 			&lt;/delete&gt;<br>
	 * 		可见其中的参数是以自定义Account的parameterClass形式进行传递的，保存在数值的Index=0的位置<br>
	 * 		2：调用<br>
	 * 		String ibatis_delete="Account.deleteAccountByParamClass";	<br>
	 * 		AccountVO accountVO=new AccountVO();<br>
	 * 		......<br>
	 * 		defautMediator.delete_iBatis (ibatis_delete,new Object[]{accountVO});<br>
	 * </pre>
	 *
	 * @param queryID 在iBatis映射文件中配置的sql语句编号
	 * @param params  参数列表,可为null
	 * @return 操作所影响的记录数
	 * @
	 */
	public int delete_iBatis(final String queryID, final Object[] params) ;

	//===================================================================================================
	//=======================================Update操作===================================================
	//===================================================================================================
	/**
	 * 更新操作，采用iBatis完成<br>
	 * 调用iBatis的update方法实现<br>
	 * <p/>
	 * <p/>
	 * 使用方法<br>
	 * <pre>
	 * 		1:iBatis配置文件中
	 * 		&lt;update id="updateAccount" parameterClass="Account"&gt;<br>
	 * 			update ACCOUNT set accountno = #accountNo# where accountky =#objectId#<br>
	 * 		&lt;/update&gt; <br>
	 * 		可见参数是以自定义的Account的parameterClass形式进行传递的<br>
	 * 		2：调用方法：<br>
	 * 		String ibaits_update_query="Account.updateAccount";<br>
	 * 		defautMediator.update_iBatis (ibaits_update_query,new Object[]{account});		<br>
	 * </pre>
	 *
	 * @param queryID 在iBatis映射文件中配置的sql语句编号<br>
	 * @param params  参数列表,可为null
	 * @return 操作所影响的记录数
	 * @
	 */
	public int update_iBatis(final String queryID, final Object[] params) ;

	//=============================================================================================
	//===========================================findByID操作=======================================
	//=============================================================================================
	/**
	 * 根据主键查找对象，采用iBatis方式完成<br>
	 * 调用iBatis的queryForObject方法实现<br>
	 * <p/>
	 * 使用方法1<br>
	 * iBatis的resultClass机制，在本例中，返回列的名称必须在resultClass中域的范围内<br>
	 * <pre>
	 * 		1：iBatis配置文件中<br>
	 *  	&lt;select id="selectAccountById" parameterClass="Long" resultClass="Account"&gt;<br>
	 * 			select accountky as objectid, accountno as accountno from ACCOUNT where accountky = #objectId#<br>
	 * 		&lt;/select&gt;<br>
	 * 		2：调用方法<br>
	 * 		String ibatis_findid_query="Account.selectAccountById";<br>
	 * 		AccountVO vo=(AccountVO)defautMediator.findByID_iBatis (ibatis_findid_query, Long.valueOf(555555));<br>
	 * </pre>
	 * <p/>
	 * 使用方法2<br>
	 * iBatis的resultMap机制，在本例中，返回列的名称通过resultMap进行转换，以此和Class域匹配<br>
	 * <pre>
	 * 		1:iBatis的配置文件中<br>
	 * 			&lt;resultMap id="AccountResult" class="Account"&gt;<br>
	 * 				&lt;result property="objectId" column="accountky" /&gt;<br>
	 * 				&lt;result property="accountNo" column="accountNo" /&gt;<br>
	 * 				&lt;result property="createdTime" column="CREATEDTIME" /&gt;<br>
	 * 			&lt;/resultMap&gt;<br>
	 * 			......<br>
	 * 			&lt;select id="selectAccountWithMap" parameterClass="Long"  resultMap="AccountResult"&gt;<br>
	 * 				select accountky  , accountno ,createdtime  from  ACCOUNT where accountky != #objectId#<br>
	 * 			&lt;/select&gt;<br>
	 * 		2:调用方法<br>
	 * 		String ibatis_findid_query2="Account.selectAccountByIdWithMap";<br>
	 * 		AccountVO vo=(AccountVO)defautMediator.findByID_iBatis (ibatis_findid_query2, Long.valueOf(555555));<br>
	 * </pre>
	 *
	 * @param queryID 在iBatis映射文件中配置的sql语句编号
	 * @param id	  主键
	 * @return 操作所影响的记录数
	 * @
	 */
	public ValueObject findByID_iBatis(String queryID, Serializable id) ;


	//=============================================================================================
	//===========================================findAll操作========================================
	//=============================================================================================
	/**
	 * 查询，采用iBatis完成<br>
	 * 用iBatis的queryForList完成，需要在映射文件中的查询语句中有ResultClass或者ResultMap，这样能够使返回列表中是由对象组成的<br>
	 * <p/>
	 * 使用方法<br>
	 * <pre>
	 * 		1:iBatis的配置文件 <br>
	 * 		&lt;select id="selectAllAccounts" resultMap="AccountResult" &gt;<br>
	 * 			select * from ACCOUNT<br>
	 * 		&lt;/select&gt; <br>
	 * 		2：调用方法<br>
	 * 		String ibatis_findall_query="Account.selectAllAccounts";<br>
	 * 		List list=defautMediator.findAll_iBatis (ibatis_findall_query);		<br>
	 * </pre>
	 *
	 * @param queryID 在iBatis映射文件中配置的sql语句编号<br>
	 * @return 查询到的所有结果列表
	 * @
	 */
	public List findAll_iBatis(String queryID) ;
	//============================================================================
	//========================================复杂的find操作=========================
	//============================================================================

	/**
	 * 使用iBatis完成复杂的查询操作，支持分页<br>
	 * 关于iBatis分页机制的说明<br>
	 * <p/>
	 * 1：数据库支持物理分页的，如MySQL,Oracle,SQL Server2005等，交给数据库本身处理<p/>
	 * 2：数据库不支持物理分页的，如informix,Sybase 12.5.3,SQLServer，交给JDBC驱动的ResultSet的absolute定位完成<p/>
	 * 3：以上都不支持的，通过ResultSet.next()方式逐条遍历<p/>
	 * <p/>
	 * <p/>
	 * 关于如何使用iBatis分页机制的操作说明<br>
	 * 1：是否要用平台优化的分页功能，目前在实现类<code>DefaultMediatorImpl</code>中是规定必须要的，因此在本方法的实现中强制了setEnableCustomizedPaged(true);
	 * 2：在Spring的配置文件中，对于IMediator的实现类的配置如下
	 * &lt;bean   id="defaultMediator"     class="com.usi.usdp.base.dao.mediator.impl.DefaultMediatorImpl" <br>
	 * init-method="initialize" &gt; <br>
	 * &lt;!--以下配置Hibernate的连接session--&gt;<br>
	 * &lt;property name="sessionFactory"&gt;<br>
	 * &lt;ref bean="sessionFactory"/&gt;<br>
	 * &lt;/property&gt;<br>
	 * &lt;!--以下配置iBatis的连接session--&gt;<br>
	 * &lt;property name="sqlMapClient"&gt;<br>
	 * &lt;ref bean="sqlMapClient"/&gt;<br>
	 * &lt;/property&gt;<br>
	 * &lt;!--以下配置jdbc的连接datasource--&gt;<br>
	 * &lt;property name="dataSource"&gt;<br>
	 * &lt;ref bean="dataSource"/&gt;<br>
	 * &lt;/property&gt;<br>
	 * &lt;!--以下配置iBatis的分页扩展机制--&gt;<br>
	 * &lt;property name="sqlExecutor"&gt;<br>
	 * &lt;ref bean="sqlExecutor"/&gt;<br>
	 * &lt;/property&gt;<br>
	 * &lt;/bean&gt;<br>
	 * 3：在persistence/ibatis.xml文件中，需要配置好各种数据库对应的分页机制实现类，同时在sqlExecutor中选择当前使用的数据库分页类
	 * &lt;bean id="sqlExecutor" class="com.usi.usdp.base.dao.orm.IBatisSqlExecutor"&gt;  <br>
	 * &lt;property name="dialect"&gt;  <br>
	 * &lt;ref bean="oracleDialect" /&gt; <br>
	 * &lt;/property&gt;  <br>
	 * &lt;/bean&gt;    <br>
	 * <bean id="oracleDialect" class="com.usi.usdp.base.dao.orm.impl.OracleDialect"/> <br>
	 * <bean id="db2Dialect" class="com.usi.usdp.base.dao.orm.impl.DB2Dialect"/> <br>
	 * <bean id="mssqlDialect" class="com.usi.usdp.base.dao.orm.impl.SQLServerDialect"/> <br>
	 * <bean id="mssq2005lDialect" class="com.usi.usdp.base.dao.orm.impl.SQLServer2005Dialect"/> <br>
	 * <bean id="sybaseDialect" class="com.usi.usdp.base.dao.orm.impl.SybaseDialect"/> <br>
	 * <bean id="informixDialect" class="com.usi.usdp.base.dao.orm.impl.InformixDialect"/> <br>
	 * <p/>
	 * </p>
	 * <p/>
	 * 使用方法
	 * <pre>
	 * 		1：iBatis的配置文件中<br>
	 * 			&lt;select id="selectAllAccounts" resultMap="AccountResult"&gt;<br>
	 * 				select * from ACCOUNT<br>
	 * 			&lt;/select&gt;<br>
	 * 		2:分页调用方法<br>
	 * 		注意从IntelliWeb传过来的OffSet的值是从0开始的<br>
	 * 		假设为第一页查询，且pagesize=5，则对应的参数offset=0，size=5；<br>
	 * 		String ibatis_query="Account.selectAllAccounts";<br>
	 * 		List list=defautMediator.find_iBatis(ibatis_query, null, 0, 2); <br>
	 * 		以此类推，如是第二页查询，则对应的参数offset=5,size=5<br>
	 * 		3：不需分页<br>
	 * 		List list=defautMediator.find_iBatis(ibatis_query, null, 0, -999999);<br>
	 * <p/>
	 * </pre>
	 *
	 * @param queryID 在iBatis映射文件中定义的语句编号
	 * @param para	参数列表，可为null
	 * @param offset  查询结果的起始行，从0开始。如果不需要分页，则设offset置为0，同时size设置为-999999。
	 * @param size	查询结果的最大行数。如果不需要分页，则size设置为-999999，同时设offset置为0
	 * @return 返回值将是映射文件中的查询语句中定义的ResultMap或者ResultClass组成的List
	 * @
	 */
	public List find_iBatis(final String queryID, final Object para, final int offset, final int size) ;
	
	
	/**
	 * 
	 * 使用iBatis完成物理的查询操作分页<br>
	 * 关于iBatis分页机制的说明<br>
	 * 数据库支持物理分页的，如MySQL,Oracle,SQL Server2005等，交给数据库本身处理<p/>
	 * 关于如何使用iBatis分页机制的操作说明<br>
	 * 在mybatis的配置文件中，对于mapper-config.xml的文件的配置如下
	 * <properties>  
	 *<property name="dialect" value="biz.shuangxin.framework.dao.impl.OracleDialect"/>  
     *</properties>  
     *biz.shuangxin.framework.dao.impl.OracleDialect实现该类,目前本框架实现了oracle,比如mysql要写mysql的方言实现类
     *使用方法
     *每个模块必须在config/mybatis/app下生成一个mybatis的xml配置文件
     *1.在xml配置文件中:
     *<select id="selectMenuAll" resultType="com.usi.usdp.app.menu.vo.UpResMenuVO"
	 *parameterType="com.usi.usdp.app.menu.vo.UpResMenuVO">
	 *	<![CDATA[
	 *	SELECT 
	 *	A.ID AS id,
	 *	A.MENU_CODE AS menuCode,
	 *	A.MENU_NAME AS menuName,
	 *	A.MENU_URL AS menuUrl,
	 *	A.MENU_ENABLED as menuEnabled 
	 *	FROM UP_RES_MENU A
	 *	]]>
	 *</select>
	 *2:分页调用方法<br>
	 * 		注意从acton传过来的OffSet的值是从0开始的<br>
	 * 		假设为第一页查询，且pagesize=5，则对应的参数offset=0，size=5；<br>
	 * 		String queryID="usdp.menu.selectMenuAll";<br>
	 * 		Object[] obj=defautMediator.find_iBatis(ibatis_query, para, 0, 2,queryCountId); <br>
	 * 		以此类推，如是第二页查询，则对应的参数offset=5,size=5
	 * @param queryID ibatis映射文件中定义的查询语句编号
	 * @param para  参数列表,是HashMap对象
	 * @param offset  查询结果的起始行，从0开始。
	 * @param size    查询结果的最大行数。
	 * @param queryName ibatis映射文件中定义的查询分页总记录数语句编号
	 * @return
	 */
	public Object[] find_iBatis_paging(String queryID,Object para, int offset, int size);	
	
	/**
	 * 
	 * @param queryID
	 * @param para
	 * @param offset
	 * @param size
	 * @return
	 */
	public Object[] find_iBatisForCount(String queryID, Object para, int offset, int size);
	
	/**
	 * 使用iBatis完成查询操作， 不支持分页<br>
	 *
	 * @param queryName 在iBatis映射文件中定义的语句编号
	 * @param para	参数列表，可为null
	 * @return 返回值将是映射文件中的查询语句中定义的ResultMap或者ResultClass组成的List
	 * @
	 * @see com.usi.usdp.base.dao.mediator.IMediator#find_iBatis(java.lang.String,
	 *	  java.lang.Object, int, int)
	 */
	public List find_iBatis(String queryName, Object para) ;

}
