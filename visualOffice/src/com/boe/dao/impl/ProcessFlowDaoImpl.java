package com.boe.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.StringUtil;
import com.boe.dao.ProcessFlowDao;
import com.boe.entity.ProcessFlow;

@Repository
public class ProcessFlowDaoImpl implements ProcessFlowDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(ProcessFlow processFlow) {
		getSession().save(processFlow);
		return true;
	}

	@Override
	public ProcessFlow load(ProcessFlow processFlow) {
		ProcessFlow currentProcessFlow = (ProcessFlow)getSession().load(ProcessFlow.class, processFlow.getId());
		return currentProcessFlow;
	}
	
	@Override
	public boolean delete(ProcessFlow processFlow) {
		getSession().delete(processFlow);
		return true;
	}

	@Override
	public boolean update(ProcessFlow processFlow) {
		getSession().update(processFlow);
		return true;
	}

	@Override
	public int deleteBatch(String idStr) {
		String hql = "delete from " + ProcessFlow.class.getSimpleName() + " processFlow where processFlow.id in(:idList)";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToLongList(idStr, ","));
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public List<ProcessFlow> getAllList() {
		Query query = getSession().createQuery("from ProcessFlow");
	    List list = query.list();
	    return list;
	}

	@Override
	public List<ProcessFlow> getListByProductId(String productId) {
		String hql = "from " + ProcessFlow.class.getSimpleName() + " processFlow where processFlow.productId in(:idList)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToList(productId, ","));
	    List list = query.list();
		return list;
	}

	@Override
	public boolean isUnique(ProcessFlow processFlow) {
		String hql = " from " + ProcessFlow.class.getSimpleName() 
				+ " processFlow where processFlow.productId=:productId"
				+ " and processFlow.stepId=:stepId";
		Long id = processFlow.getId();
		if(id != null) {
			hql += " and processFlow.id<>:id";
		}
		Query query =  getSession().createQuery(hql);
		query.setParameter("productId", processFlow.getProductId());
		query.setParameter("stepId", processFlow.getStepId());
		if(id != null) {
			query.setParameter("id", id);
		}
		List list = query.list();
		if(list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveBatch(List<ProcessFlow> list) {
		if(list == null || list.size() == 0) {
			return false;
		}
		for(ProcessFlow processFlow : list) {
			getSession().save(processFlow);
		}
		return true;
	}
	
	
}
