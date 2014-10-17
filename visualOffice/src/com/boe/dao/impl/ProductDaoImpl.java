package com.boe.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.StringUtil;
import com.boe.dao.ProductDao;
import com.boe.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(Product product) {
		getSession().save(product);
		return true;
	}

	@Override
	public Product load(Product product) {
		Product currentProduct = (Product)getSession().load(Product.class, product.getId());
		return currentProduct;
	}
	
	@Override
	public boolean delete(Product product) {
		getSession().delete(product);
		return true;
	}

	@Override
	public boolean update(Product product) {
		getSession().update(product);
		return true;
	}

	@Override
	public int deleteBatch(String idStr) {
		String hql = "delete from " + Product.class.getSimpleName() + " product where product.id in(:idList)";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToLongList(idStr, ","));
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public List<Product> getAllList() {
		Query query = getSession().createQuery("from Product");
	    List list = query.list();
	    return list;
	}

	@Override
	public boolean isUnique(Product product) {
		String hql = " from " + Product.class.getSimpleName() + " product where product.productId=:productId";
		Long id = product.getId();
		if(id != null) {
			hql += " and product.id<>:id";
		}
		Query query =  getSession().createQuery(hql);
		query.setParameter("productId", product.getProductId());
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
