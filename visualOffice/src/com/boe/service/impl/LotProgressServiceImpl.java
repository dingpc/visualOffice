package com.boe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.LotProgressDao;
import com.boe.entity.LotProgress;
import com.boe.service.LotProgressService;

@Service
@Transactional
public class LotProgressServiceImpl implements LotProgressService {

	@Autowired
	private LotProgressDao lotProgressDao;
	
	@Override
	public boolean isUnique(LotProgress lotProgress) {
		return false;
	}

	@Override
	public boolean deleteByManageId(String idStr) {
		return false;
	}

	@Override
	public boolean deleteByLotId(String idStr) {
		return false;
	}

	@Override
	public boolean saveOrUpdate(LotProgress lotProgress) {
		return false;
	}

	@Override
	public List getLotProgressList(Map map) {
		return this.lotProgressDao.getLotProgressList(map);
	}

	@Override
	public boolean saveOrUpdateBatch(List<LotProgress> list) {
		return this.lotProgressDao.saveOrUpdateBatch(list);
	}

}
