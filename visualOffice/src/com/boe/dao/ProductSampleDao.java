package com.boe.dao;

import java.util.List;

import com.boe.entity.ProductSample;

public interface ProductSampleDao {

	public abstract boolean save(ProductSample productSample);
	
	public abstract ProductSample load(ProductSample productSample);
	
	public abstract boolean delete(ProductSample productSample);
	
	public abstract boolean update(ProductSample productSample);
	
	public abstract int deleteBatch(String idStr);
	
	public abstract List<ProductSample> getAllList();
	
	public abstract List<ProductSample> getListByProductId(String productId);
	
	public abstract boolean isUnique(ProductSample productSample);
}
