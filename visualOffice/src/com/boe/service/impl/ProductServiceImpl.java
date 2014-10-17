package com.boe.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.ProcessFlowDao;
import com.boe.dao.ProductDao;
import com.boe.dao.ProductSampleDao;
import com.boe.dao.ProductSpecDao;
import com.boe.dao.ProductSplitDao;
import com.boe.entity.Product;
import com.boe.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	private static Logger log = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductSampleDao productSampleDao;
	
	@Autowired
	private ProductSpecDao productSpecDao;
	
	@Autowired
	private ProductSplitDao productSplitDao;
	
	@Autowired
	private ProcessFlowDao processFlowDao;

	@Override
	public boolean save(Product product) {
		return this.productDao.save(product);
	}

	@Override
	public Product load(Product product) {
		return this.productDao.load(product);
	}

	@Override
	public boolean delete(Product product) {
		return this.productDao.delete(product);
	}

	@Override
	public boolean update(Product product) {
		return this.productDao.update(product);
	}

	@Override
	public int deleteBatch(String idStr) {
		return this.productDao.deleteBatch(idStr);
	}

	@Override
	public List<Product> getAllList() {
		return this.productDao.getAllList();
	}
	
	@Override
	public boolean hasRelationData(String productId) {
		List sampleList = this.productSampleDao.getListByProductId(productId);
		if(sampleList != null && sampleList.size() > 0) {
			log.info("Product Sample is relation:" + productId);
			return true;
		}
		List specList = this.productSpecDao.getListByProductId(productId);
		if(specList != null && specList.size() > 0) {
			log.info("Product Spec is relation:" + productId);
			return true;
		}
		List splitList = this.productSplitDao.getListByProductId(productId);
		if(splitList != null && splitList.size() > 0) {
			log.info("Product Split is relation:" + productId);
			return true;
		}
		List flowList = this.processFlowDao.getListByProductId(productId);
		if(flowList != null && flowList.size() > 0) {
			log.info("Process Flow is relation:" + productId);
			return true;
		}
		return false;
	}

	@Override
	public boolean isUnique(Product product) {
		return this.productDao.isUnique(product);
	}
}
