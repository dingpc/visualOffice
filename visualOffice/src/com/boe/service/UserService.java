package com.boe.service;

import java.util.List;

import com.boe.entity.User;

public interface UserService {
	
	public abstract boolean save(User user);
	
	public abstract User load(User user);
	
	public abstract boolean delete(User user);
	
	public abstract boolean update(User user);
	
	public abstract List<User> getAllUser();
	
	public abstract User getOneUser(User user);
	
	public abstract User getUserByUserId(String userId);
	
	public abstract int deleteBatch(String idStr);
	
	public abstract boolean isUnique(User user);
}
