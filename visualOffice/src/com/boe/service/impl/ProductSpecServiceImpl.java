package com.boe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.ProductSpecDao;
import com.boe.entity.ProductSpec;
import com.boe.service.ProductSpecService;

@Service
@Transactional
public class ProductSpecServiceImpl implements ProductSpecService{

	@Autowired
	private ProductSpecDao productSpecDao;
	
	@Override
	public boolean save(ProductSpec productSpec) {
		return this.productSpecDao.save(productSpec);
	}

	@Override
	public ProductSpec load(ProductSpec productSpec) {
		return this.productSpecDao.load(productSpec);
	}

	@Override
	public boolean delete(ProductSpec productSpec) {
		return this.productSpecDao.delete(productSpec);
	}

	@Override
	public boolean update(ProductSpec productSpec) {
		return this.productSpecDao.update(productSpec);
	}

	@Override
	public List<ProductSpec> getAllList() {
		return this.productSpecDao.getAllList();
	}
	
	@Override
	public int deleteBatch(String idStr) {
		return this.productSpecDao.deleteBatch(idStr);
	}
	
	@Override
	public List<ProductSpec> getListByProductId(String productId) {
		return this.productSpecDao.getListByProductId(productId);
	}

	@Override
	public boolean isUnique(ProductSpec productSpec) {
		return this.productSpecDao.isUnique(productSpec);
	}
}
