package com.boe.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.StringUtil;
import com.boe.dao.ProductSplitDao;
import com.boe.entity.ProductSplit;

@Repository
public class ProductSplitDaoImpl implements ProductSplitDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(ProductSplit productSplit) {
		getSession().save(productSplit);
		return true;
	}

	@Override
	public ProductSplit load(ProductSplit productSplit) {
		ProductSplit currentProductSplit = (ProductSplit)getSession().load(ProductSplit.class, productSplit.getId());
		return currentProductSplit;
	}
	
	@Override
	public boolean delete(ProductSplit productSplit) {
		getSession().delete(productSplit);
		return true;
	}

	@Override
	public boolean update(ProductSplit productSplit) {
		getSession().update(productSplit);
		return true;
	}

	@Override
	public int deleteBatch(String idStr) {
		String hql = "delete from " + ProductSplit.class.getSimpleName() + " productSplit where productSplit.id in(:idList)";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToLongList(idStr, ","));
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public List<ProductSplit> getAllList() {
		Query query = getSession().createQuery("from ProductSplit");
	    List productlist = query.list();
	    return productlist;
	}
	
	@Override
	public List<ProductSplit> getListByProductId(String productId) {
		String hql = "from " + ProductSplit.class.getSimpleName() + " productSplit where productSplit.productId in(:idList)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToList(productId, ","));
	    List list = query.list();
		return list;
	}

	@Override
	public boolean isUnique(ProductSplit productSplit) {
		String hql = " from " + ProductSplit.class.getSimpleName() 
				+ " productSplit where productSplit.productId=:productId"
				+ " and productSplit.glassId=:glassId";
		Long id = productSplit.getId();
		if(id != null) {
			hql += " and productSplit.id<>:id";
		}
		Query query =  getSession().createQuery(hql);
		query.setParameter("productId", productSplit.getProductId());
		query.setParameter("glassId", productSplit.getGlassId());
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
