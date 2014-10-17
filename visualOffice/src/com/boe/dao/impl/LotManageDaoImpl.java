package com.boe.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.StringUtil;
import com.boe.dao.LotManageDao;
import com.boe.entity.LotManage;

@Repository
public class LotManageDaoImpl implements LotManageDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(LotManage lotManage) {
		getSession().save(lotManage);
		return true;
	}

	@Override
	public LotManage load(LotManage lotManage) {
		LotManage currentLotManage = (LotManage)getSession().load(LotManage.class, lotManage.getId());
		return currentLotManage;
	}
	
	@Override
	public boolean delete(LotManage lotManage) {
		getSession().delete(lotManage);
		return true;
	}

	@Override
	public boolean update(LotManage lotManage) {
		getSession().update(lotManage);
		return true;
	}

	@Override
	public int deleteBatch(String idStr) {
		String hql = "delete from " + LotManage.class.getSimpleName() + " lotManage where lotManage.id in(:idList)";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToLongList(idStr, ","));
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public List<LotManage> getAllList() {
		Query query = getSession().createQuery("from LotManage");
	    List list = query.list();
	    return list;
	}

	@Override
	public List<LotManage> getListByProductId(String productId) {
		String hql = "from " + LotManage.class.getSimpleName() + " lotManage where lotManage.productId in(:idList)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToList(productId, ","));
	    List list = query.list();
		return list;
	}

	@Override
	public boolean isUnique(LotManage lotManage) {
		String hql = " from " + LotManage.class.getSimpleName() 
				+ " lotManage where lotManage.productId=:productId"
				+ " and lotManage.stepId=:stepId";
		Long id = lotManage.getId();
		if(id != null) {
			hql += " and lotManage.id<>:id";
		}
		Query query =  getSession().createQuery(hql);
		query.setParameter("productId", lotManage.getProductId());
		query.setParameter("stepId", lotManage.getStepId());
		if(id != null) {
			query.setParameter("id", id);
		}
		List list = query.list();
		if(list != null && list.size() > 0) {
			return false;
		}
		return true;
	}
}
