package com.boe.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.StringUtil;
import com.boe.dao.LotDao;
import com.boe.entity.Lot;

@Repository
public class LotDaoImpl implements LotDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(Lot lot) {
		getSession().save(lot);
		return true;
	}

	@Override
	public Lot load(Lot lot) {
		Lot currentLot = (Lot)getSession().load(Lot.class, lot.getId());
		return currentLot;
	}
	
	@Override
	public boolean delete(Lot lot) {
		getSession().delete(lot);
		return true;
	}

	@Override
	public boolean update(Lot lot) {
		getSession().update(lot);
		return true;
	}

	@Override
	public int deleteBatch(String idStr) {
		String hql = "delete from " + Lot.class.getSimpleName() + " lot where lot.id in(:idList)";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToLongList(idStr, ","));
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public List<Lot> getAllList() {
		Query query = getSession().createQuery("from Lot");
	    List list = query.list();
	    return list;
	}

	@Override
	public List<Lot> getListByProductId(String productId) {
		String hql = "from " + Lot.class.getSimpleName() + " lot where lot.productId in(:idList)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToList(productId, ","));
	    List list = query.list();
		return list;
	}

	@Override
	public boolean isUnique(Lot lot) {
		String hql = " from " + Lot.class.getSimpleName() 
				+ " lot where lot.productId=:productId"
				+ " and lot.lotName=:lotName";
		Long id = lot.getId();
		if(id != null) {
			hql += " and lot.id<>:id";
		}
		Query query =  getSession().createQuery(hql);
		query.setParameter("productId", lot.getProductId());
		query.setParameter("lotName", lot.getLotName());
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
