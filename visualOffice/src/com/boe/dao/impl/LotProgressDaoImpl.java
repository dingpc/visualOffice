package com.boe.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.dao.LotProgressDao;
import com.boe.entity.LotProgress;

@Repository
public class LotProgressDaoImpl implements LotProgressDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(LotProgress lotProgress) {
		return false;
	}

	@Override
	public LotProgress load(LotProgress lotProgress) {
		return null;
	}

	@Override
	public boolean delete(LotProgress lotProgress) {
		return false;
	}

	@Override
	public boolean update(LotProgress lotProgress) {
		return false;
	}

	@Override
	public int deleteBatch(String idStr) {
		return 0;
	}

	@Override
	public List<LotProgress> getAllList() {
		return null;
	}

	@Override
	public List<LotProgress> getListByProductId(String productId) {
		return null;
	}

	@Override
	public boolean isUnique(LotProgress lotProgress) {
		return false;
	}

	@Override
	public boolean deleteByManageId(String idStr) {
		return false;
	}

	@Override
	public boolean deleteByLotId(String idStr) {
		return false;
	}

	@Override
	public boolean saveOrUpdate(LotProgress lotProgress) {
		getSession().saveOrUpdate(lotProgress);
		return true;
	}
	
	public List getLotProgressList(Map map) {
		List list = new ArrayList();
		String productId = (String)map.get("productId");
		Long manageId = (Long)map.get("manageId");
//		String hql = "select lot.id, lot.lotName, lot.productId, lp.lotId, lp.manageId, lp.isComplete from " 
//				+ Lot.class.getSimpleName() 
//				+ " lot LEFT OUTER JOIN "
//				+ "(select lotProgress from " + LotProgress.class.getSimpleName() + " lotProgress where lotProgress.manageId=:manageId) lp on(lot.id=lp.lotId) "
//				+ "where lot.productId=:productId order by lot.lotName";
//		Query query = getSession().createQuery(hql);
//		query.setParameter("productId", productId);
//		query.setParameter("manageId", manageId);
//		list = query.list();
		String sql = "select lot.id, lot.lotName, lot.productId, lp.lotId, lp.manageId, lp.isComplete from Lot lot LEFT OUTER JOIN (select lotProgress.* from Lot_Progress lotProgress where lotProgress.manageId=:manageId) lp on lot.id=lp.lotId" 
				+ " where lot.productId=:productId order by lot.lotName";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		//sqlQuery.addEntity("LotProgress", LotProgress.class);
		//sqlQuery.addEntity("Lot", Lot.class);
		sqlQuery.setParameter("productId", productId);
		sqlQuery.setParameter("manageId", manageId);
		list = sqlQuery.list();
		return list;
	}

	@Override
	public boolean saveOrUpdateBatch(List<LotProgress> list) {
		if(list == null || list.size() == 0) {
			return false;
		}
		for(LotProgress progress : list) {
			getSession().saveOrUpdate(progress);
		}
		return true;
	}

}
