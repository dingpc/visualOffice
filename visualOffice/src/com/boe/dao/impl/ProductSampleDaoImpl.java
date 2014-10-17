package com.boe.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.StringUtil;
import com.boe.dao.ProductSampleDao;
import com.boe.entity.ProductSample;

@Repository
public class ProductSampleDaoImpl implements ProductSampleDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(ProductSample productSample) {
		getSession().save(productSample);
		return true;
	}

	@Override
	public ProductSample load(ProductSample productSample) {
		ProductSample currentProductSample = (ProductSample)getSession().load(ProductSample.class, productSample.getId());
		return currentProductSample;
	}
	
	@Override
	public boolean delete(ProductSample productSample) {
		getSession().delete(productSample);
		return true;
	}

	@Override
	public boolean update(ProductSample productSample) {
		getSession().update(productSample);
		return true;
	}

	@Override
	public int deleteBatch(String idStr) {
		String hql = "delete from " + ProductSample.class.getSimpleName() + " productSample where productSample.id in(:idList)";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToLongList(idStr, ","));
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public List<ProductSample> getAllList() {
		Query query = getSession().createQuery("from ProductSample");
	    List productlist = query.list();
	    return productlist;
	}

	@Override
	public List<ProductSample> getListByProductId(String productId) {
		String hql = "from " + ProductSample.class.getSimpleName() + " productSample where productSample.productId in(:idList)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToList(productId, ","));
	    List list = query.list();
		return list;
	}

	@Override
	public boolean isUnique(ProductSample productSample) {
		String hql = " from " + ProductSample.class.getSimpleName() 
				+ " productSample where productSample.productId=:productId"
				+ " and productSample.stepId=:stepId";
		Long id = productSample.getId();
		if(id != null) {
			hql += " and productSample.id<>:id";
		}
		Query query =  getSession().createQuery(hql);
		query.setParameter("productId", productSample.getProductId());
		query.setParameter("stepId", productSample.getStepId());
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
