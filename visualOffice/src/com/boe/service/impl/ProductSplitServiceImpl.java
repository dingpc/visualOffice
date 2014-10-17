package com.boe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.ProductSplitDao;
import com.boe.entity.ProductSplit;
import com.boe.service.ProductSplitService;

@Service
@Transactional
public class ProductSplitServiceImpl implements ProductSplitService{

	@Autowired
	private ProductSplitDao productSplitDao;
	
	@Override
	public boolean save(ProductSplit productSplit) {
		return this.productSplitDao.save(productSplit);
	}

	@Override
	public ProductSplit load(ProductSplit productSplit) {
		return this.productSplitDao.load(productSplit);
	}

	@Override
	public boolean delete(ProductSplit productSplit) {
		return this.productSplitDao.delete(productSplit);
	}

	@Override
	public boolean update(ProductSplit productSplit) {
		return this.productSplitDao.update(productSplit);
	}

	@Override
	public List<ProductSplit> getAllList() {
		return this.productSplitDao.getAllList();
	}
	
	@Override
	public int deleteBatch(String idStr) {
		return this.productSplitDao.deleteBatch(idStr);
	}
	
	@Override
	public List<ProductSplit> getListByProductId(String productId) {
		return this.productSplitDao.getListByProductId(productId);
	}

	@Override
	public boolean isUnique(ProductSplit productSplit) {
		return this.productSplitDao.isUnique(productSplit);
	}
}
