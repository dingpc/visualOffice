package com.boe.dao;

import java.util.List;

import com.boe.entity.User;

public interface UserDao {
	
	public abstract boolean save(User user);
	
	public abstract User load(User user);
	
	public abstract boolean delete(User user);
	
	public abstract boolean update(User user);
	
	public abstract int deleteBatch(String idStr);
	
	public abstract List<User> getAllUser();
	
	public abstract User getOneUser(User user);
	
	public abstract User getUserByUserId(String userId);
	
	public abstract boolean isUnique(User user);
}
