package biz.shxn.framework.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import biz.shxn.framework.dao.IBaseDAO;
import biz.shxn.framework.vo.ValueObject;

/**
 * BaseDAO实现，绑定Hibernate,iBatis,JDBC三种数据访问技术
 * Description:持久层操作的接口,完成增删，条件查询，批量更新等操作
 * 
 * @author wangjf
 * @version 1.0, 2017/04/24
 */
@Component("baseDAO")
public class BaseDAOImpl implements IBaseDAO {

	/**
	 * 构造函数
	 */
	public BaseDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 以私有成员变量保存SqlSession
	 */
	@Autowired
	private SqlSession sqlSession;

	/**
	 * @param sqlSession
	 *            the sqlSession to set
	 */
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.shuangxin.framework.dao.IBaseDAO#getSqlSession()
	 */
	public SqlSession getSqlSession() {
		// TODO Auto-generated method stub
		return this.sqlSession;		
	}

	public int save_iBatis(String queryID, Object[] params) {
		// TODO 用iBatis完成 ,此时参数params保存在index=0的位置
		int rs = 0;
		if (params == null || params.length == 0)
			getSqlSession().insert(queryID, null);
		else
			getSqlSession().insert(queryID, params[0]);
		rs = 1;
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.shuangxin.framework.dao.IBaseDAO#delete_iBatis(java.lang.String,
	 * java.lang.Object[])
	 */
	public int delete_iBatis(String queryID, Object[] params) {
		// TODO 用iBatis的查询完成,此时参数params保存在index=0的位置
		int rs = 0;
		if (params != null && params.length > 0)
			getSqlSession().delete(queryID, params[0]);
		else
			getSqlSession().delete(queryID, null);

		rs = 1;
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.shuangxin.framework.dao.IBaseDAO#update_iBatis(java.lang.String,
	 * java.lang.Object[])
	 */
	public int update_iBatis(String queryID, Object[] params) {
		// TODO 用iBatis完成 ,此时参数params保存在index=0的位置
		int rs = 0;
		if (params != null && params.length > 0)
			getSqlSession().update(queryID, params[0]);
		else
			getSqlSession().update(queryID, null);

		rs = 1;
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.shuangxin.framework.dao.IBaseDAO#findByID_iBatis(java.lang.String,
	 * java.io.Serializable)
	 */
	public ValueObject findByID_iBatis(String queryID, Serializable id) {
		// TODO 用iBatis的queryForObject完成
		return (ValueObject) (getSqlSession().selectOne(queryID, id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.shuangxin.framework.dao.IBaseDAO#findAll_iBatis(java.lang.String)
	 */
	public List findAll_iBatis(String queryID) {
		// TODO 用iBatis的queryForList完成，
		// TODO 本方法实现最好在映射文件中的查询语句中有resultclass或者resultmap，这样能够使返回列表中是由对象组成的。
		return getSqlSession().selectList(queryID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.shuangxin.framework.dao.IBaseDAO#find_iBatis(java.lang.String,
	 * java.lang.Object, int, int)
	 */
	public List find_iBatis(String queryID, Object para, int offset, int size) {
		// TODO 希望使用平台优化过的分页机制。但启用平台对iBtatis分页的优化机制并不等于就能够用，还要看当前数据库本身是否支持。
//		setEnableCustomizedPaged(true);
		return getSqlSession().selectList(queryID, para, new RowBounds(offset,size));
	}
	/**
	 * Mybatis 分页查询，支持查询结果和总数。
	 * sqlmapper中必须包含（queryID+"Count"）的select语句，且语句count（*） 必须 as recordCount
	 * 示例“select count(*) as recordCount from menu”
	 * @param queryID sqlMapper的唯一标识
	 * @param para	参数对象
	 * @param offset 查询结果的起始行，从0开始。如果不需要，则设置为-1。
	 * @param size	查询结果的最大行数。如果不需要，则设置为-1
	 * @return
	 */
	public Object[] find_iBatisForCount(String queryID, Object para, int offset, int size) {
		// TODO 希望使用平台优化过的分页机制。但启用平台对iBtatis分页的优化机制并不等于就能够用，还要看当前数据库本身是否支持。
//		setEnableCustomizedPaged(true);
		List list= getSqlSession().selectList(queryID, para, new RowBounds(offset,size));
		//Integer count= getSqlSession().selectOne(queryID+"Count",para);
//		List listCount=getSqlSession().selectList(queryID,para);
		List<ValueObject> listall=getSqlSession().selectList(queryID, para, new RowBounds(-1,-1));
		ValueObject count= (ValueObject) listall.get(0);
		return new Object[]{list,count.getTotalCount()};
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.shuangxin.framework.dao.IBaseDAO#find_iBatis(java.lang.String,
	 * java.lang.Object)
	 */
	public List find_iBatis(String queryName, Object para) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(queryName, para);
	}
}
