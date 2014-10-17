package com.boe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.ProductSampleDao;
import com.boe.entity.ProductSample;
import com.boe.service.ProductSampleService;

@Service
@Transactional
public class ProductSampleServiceImpl implements ProductSampleService{

	@Autowired
	private ProductSampleDao productSampleDao;
	
	@Override
	public boolean save(ProductSample productSample) {
		return this.productSampleDao.save(productSample);
	}

	@Override
	public ProductSample load(ProductSample productSample) {
		return this.productSampleDao.load(productSample);
	}

	@Override
	public boolean delete(ProductSample productSample) {
		return this.productSampleDao.delete(productSample);
	}

	@Override
	public boolean update(ProductSample productSample) {
		return this.productSampleDao.update(productSample);
	}

	@Override
	public List<ProductSample> getAllList() {
		return this.productSampleDao.getAllList();
	}
	
	@Override
	public int deleteBatch(String idStr) {
		return this.productSampleDao.deleteBatch(idStr);
	}

	@Override
	public List<ProductSample> getListByProductId(String productId) {
		return this.productSampleDao.getListByProductId(productId);
	}

	@Override
	public boolean isUnique(ProductSample productSample) {
		return this.productSampleDao.isUnique(productSample);
	}
}
