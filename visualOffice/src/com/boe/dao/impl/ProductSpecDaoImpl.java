package com.boe.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.StringUtil;
import com.boe.dao.ProductSpecDao;
import com.boe.entity.ProductSpec;

@Repository
public class ProductSpecDaoImpl implements ProductSpecDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(ProductSpec productSpec) {
		getSession().save(productSpec);
		return true;
	}

	@Override
	public ProductSpec load(ProductSpec productSpec) {
		ProductSpec currentProductSpec = (ProductSpec)getSession().load(ProductSpec.class, productSpec.getId());
		return currentProductSpec;
	}
	
	@Override
	public boolean delete(ProductSpec productSpec) {
		getSession().delete(productSpec);
		return true;
	}

	@Override
	public boolean update(ProductSpec productSpec) {
		getSession().update(productSpec);
		return true;
	}

	@Override
	public int deleteBatch(String idStr) {
		String hql = "delete from " + ProductSpec.class.getSimpleName() + " productSpec where productSpec.id in(:idList)";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToLongList(idStr, ","));
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public List<ProductSpec> getAllList() {
		Query query = getSession().createQuery("from ProductSpec");
	    List productlist = query.list();
	    return productlist;
	}
	
	@Override
	public List<ProductSpec> getListByProductId(String productId) {
		String hql = "from " + ProductSpec.class.getSimpleName() + " productSpec where productSpec.productId in (:idList)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToList(productId, ","));
	    List list = query.list();
		return list;
	}

	@Override
	public boolean isUnique(ProductSpec productSpec) {
		String hql = " from " + ProductSpec.class.getSimpleName() 
				+ " productSpec where productSpec.productId=:productId"
				+ " and productSpec.layer=:layer";
		Long id = productSpec.getId();
		if(id != null) {
			hql += " and productSpec.id<>:id";
		}
		Query query =  getSession().createQuery(hql);
		query.setParameter("productId", productSpec.getProductId());
		query.setParameter("layer", productSpec.getLayer());
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
