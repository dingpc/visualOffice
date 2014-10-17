package com.boe.dao;

import java.util.List;

import com.boe.entity.Lot;

public interface LotDao {
	
	public abstract boolean save(Lot lot);
	
	public abstract Lot load(Lot lot);
	
	public abstract boolean delete(Lot lot);
	
	public abstract boolean update(Lot lot);
	
	public abstract int deleteBatch(String idStr);
	
	public abstract List<Lot> getAllList();
	
	public abstract List<Lot> getListByProductId(String productId);
	
	public abstract boolean isUnique(Lot lot);
}
