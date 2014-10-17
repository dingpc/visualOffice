package com.boe.dao;

import java.util.List;
import java.util.Map;

import com.boe.entity.LotProgress;

public interface LotProgressDao {
	
	public abstract boolean save(LotProgress lotProgress);
	
	public abstract LotProgress load(LotProgress lotProgress);
	
	public abstract boolean delete(LotProgress lotProgress);
	
	public abstract boolean update(LotProgress lotProgress);
	
	public abstract int deleteBatch(String idStr);
	
	public abstract List<LotProgress> getAllList();
	
	public abstract List<LotProgress> getListByProductId(String productId);
	
	public abstract boolean isUnique(LotProgress lotProgress);
	
	public abstract boolean deleteByManageId(String idStr);
	
	public abstract boolean deleteByLotId(String idStr);
	
	public abstract boolean saveOrUpdate(LotProgress lotProgress);
	
	public abstract List getLotProgressList(Map map);
	
	public abstract boolean saveOrUpdateBatch(List<LotProgress> list);
}
