package com.boe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.UserDao;
import com.boe.entity.User;
import com.boe.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean save(User user) {
		return this.userDao.save(user);
	}

	@Override
	public User load(User user) {
		return this.userDao.getOneUser(user);
	}

	@Override
	public boolean delete(User user) {
		return this.userDao.delete(user);
	}

	@Override
	public boolean update(User user) {
		return this.userDao.update(user);
	}

	@Override
	public List<User> getAllUser() {
		return this.userDao.getAllUser();
	}

	@Override
	public User getOneUser(User user) {
		return this.userDao.getOneUser(user);
	}

	@Override
	public User getUserByUserId(String userId) {
		return this.userDao.getUserByUserId(userId);
	}

	@Override
	public int deleteBatch(String idStr) {
		return this.userDao.deleteBatch(idStr);
	}

	@Override
	public boolean isUnique(User user) {
		return this.userDao.isUnique(user);
	}
}
