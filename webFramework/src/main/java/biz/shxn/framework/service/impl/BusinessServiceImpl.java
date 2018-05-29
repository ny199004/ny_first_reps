package biz.shxn.framework.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.shxn.framework.dao.IBaseDAO;
import biz.shxn.framework.service.IBusinessService;
import biz.shxn.framework.util.UtilSpringContext;
import biz.shxn.framework.vo.ValueObject;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
 */
@Service("businessService")
public class BusinessServiceImpl implements IBusinessService {

	/**
	 * 通常不带业务逻辑的操作实现
	 */

	/**
	 * 注入IBaseDAO，对持久层操作
	 */
	@Autowired
	private IBaseDAO baseDAO;
	/**
	 * 
	 * @param baseDAO
	 */
	public void setBaseDAO(IBaseDAO baseDAO){
		this.baseDAO=baseDAO;
	}
	/**
	 * 
	 * @return baseDAO
	 */
	public IBaseDAO getBaseDAO(){
		return this.baseDAO;
	}
	
	/**
	 * 提供获取Spring容器环境内注册的Bean
	 *
	 * @param beanName 注册的beanID
	 * @return 返回结果对象
	 */
	public Object getBean(String beanName)
	{
		return UtilSpringContext.getBean(beanName);
	}

	/* (non-Javadoc)
	 * @see biz.shuangxin.framework.service.IBusinessService#save_iBatis(java.lang.String, java.lang.Object[])
	 */
	public int save_iBatis(String queryID, Object[] params) {
		return baseDAO.save_iBatis(queryID, params);
	}

	/* (non-Javadoc)
	 * @see biz.shuangxin.framework.service.IBusinessService#delete_iBatis(java.lang.String, java.lang.Object[])
	 */
	public int delete_iBatis(String queryID, Object[] params) {
		return baseDAO.delete_iBatis(queryID, params);
	}

	/* (non-Javadoc)
	 * @see biz.shuangxin.framework.service.IBusinessService#update_iBatis(java.lang.String, java.lang.Object[])
	 */
	public int update_iBatis(String queryID, Object[] params) {
		return baseDAO.update_iBatis(queryID, params);
	}

	/* (non-Javadoc)
	 * @see biz.shuangxin.framework.service.IBusinessService#findByID_iBatis(java.lang.String, java.io.Serializable)
	 */
	public ValueObject findByID_iBatis(String queryID, Serializable id) {
		return baseDAO.findByID_iBatis(queryID, id);
	}
	
	/* (non-Javadoc)
	 * @see biz.shuangxin.framework.service.IBusinessService#findAll_iBatis(java.lang.String)
	 */
	public List findAll_iBatis(String queryID) {
		return baseDAO.findAll_iBatis(queryID);
	}

	/* (non-Javadoc)
	 * @see biz.shuangxin.framework.service.IBusinessService#find_iBatis(java.lang.String, java.lang.Object, int, int)
	 */
	public List find_iBatis(String queryID, Object para, int offset, int size) {
		return baseDAO.find_iBatis(queryID,para,offset,size);
	}
	public Object[] find_iBatisForCount(String queryID, Object para, int offset, int size){
		return baseDAO.find_iBatisForCount(queryID, para, offset, size);
	}
	/* (non-Javadoc)
	 * @see biz.shuangxin.framework.service.IBusinessService#find_iBatis(java.lang.String, java.lang.Object)
	 */
	public List find_iBatis(String queryName, Object para) {
		return baseDAO.find_iBatis(queryName, para);
	}

	public Object[] find_iBatis_paging(String queryID,Object para, int offset, int size){
		return baseDAO.find_iBatisForCount(queryID,para, offset, size);
	}

}
