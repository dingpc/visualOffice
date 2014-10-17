package com.boe.dao;

import java.util.List;

import com.boe.entity.ProductSpec;

public interface ProductSpecDao {

	public abstract boolean save(ProductSpec productSpec);
	
	public abstract ProductSpec load(ProductSpec productSpec);
	
	public abstract boolean delete(ProductSpec productSpec);
	
	public abstract boolean update(ProductSpec productSpec);
	
	public abstract int deleteBatch(String idStr);
	
	public abstract List<ProductSpec> getAllList();
	
	public abstract List<ProductSpec> getListByProductId(String productId);
	
	public abstract boolean isUnique(ProductSpec productSpec);
}
