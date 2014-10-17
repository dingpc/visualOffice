package com.boe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.LotDao;
import com.boe.entity.Lot;
import com.boe.service.LotService;

@Service
@Transactional
public class LotServiceImpl implements LotService {
	
	@Autowired
	private LotDao lotDao;
	
	@Override
	public boolean save(Lot lot) {
		return this.lotDao.save(lot);
	}

	@Override
	public Lot load(Lot lot) {
		return this.lotDao.load(lot);
	}

	@Override
	public boolean delete(Lot lot) {
		return this.lotDao.delete(lot);
	}

	@Override
	public boolean update(Lot lot) {
		return this.lotDao.update(lot);
	}

	@Override
	public int deleteBatch(String idStr) {
		return this.lotDao.deleteBatch(idStr);
	}

	@Override
	public List<Lot> getAllList() {
		return this.lotDao.getAllList();
	}

	@Override
	public List<Lot> getListByProductId(String productId) {
		return this.lotDao.getListByProductId(productId);
	}

	@Override
	public boolean isUnique(Lot lot) {
		return this.lotDao.isUnique(lot);
	}

}
