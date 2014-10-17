package com.boe.service;

import java.util.List;

import com.boe.entity.ProductSplit;

public interface ProductSplitService {

	public abstract boolean save(ProductSplit productSplit);
	
	public abstract ProductSplit load(ProductSplit productSplit);
	
	public abstract boolean delete(ProductSplit productSplit);
	
	public abstract boolean update(ProductSplit productSplit);
	
	public abstract int deleteBatch(String idStr);
	
	public abstract List<ProductSplit> getAllList();
	
	public abstract List<ProductSplit> getListByProductId(String productId);
	
	public abstract boolean isUnique(ProductSplit productSplit);
}
