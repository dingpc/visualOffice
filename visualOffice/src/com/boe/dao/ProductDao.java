package com.boe.dao;

import java.util.List;

import com.boe.entity.Product;

public interface ProductDao {

	public abstract boolean save(Product product);
	
	public abstract Product load(Product product);
	
	public abstract boolean delete(Product product);
	
	public abstract boolean update(Product product);
	
	public abstract int deleteBatch(String idStr);
	
	public abstract List<Product> getAllList();
	
	public abstract boolean isUnique(Product product);
}
