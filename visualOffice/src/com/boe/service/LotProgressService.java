package com.boe.service;

import java.util.List;
import java.util.Map;

import com.boe.entity.LotProgress;

public interface LotProgressService {

public abstract boolean isUnique(LotProgress lotProgress);
	
	public abstract boolean deleteByManageId(String idStr);
	
	public abstract boolean deleteByLotId(String idStr);
	
	public abstract boolean saveOrUpdate(LotProgress lotProgress);
	
	public abstract List getLotProgressList(Map map);
	
	public abstract boolean saveOrUpdateBatch(List<LotProgress> list);
}
