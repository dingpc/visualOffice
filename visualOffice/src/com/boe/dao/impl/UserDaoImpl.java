package com.boe.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.StringUtil;
import com.boe.dao.UserDao;
import com.boe.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(User user) {
		getSession().save(user);
		return true;
	}

	@Override
	public User load(User user) {
		User currentUser = (User)getSession().load(User.class, user.getId());
		return currentUser;
	}
	
	@Override
	public boolean delete(User user) {
		getSession().delete(user);
		return true;
	}

	@Override
	public boolean update(User user) {
		getSession().update(user);
		return true;
	}

	@Override
	public List<User> getAllUser() {
		Query query = getSession().createQuery("from User user where user.userId<>'admin'");
	    List userlist = query.list();
	    return userlist;
	}

	@Override
	public User getOneUser(User user) {
		User currentUser = (User)getSession().load(User.class, user.getId());
		return currentUser;
	}
	
	@Override
	public User getUserByUserId(String userId) {
		User loginUser = new User();
		String hql = " from " + User.class.getSimpleName() + " user where user.userId=:userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		List<Object> list = query.list();
		if(list != null && list.size() == 1) {
			loginUser = (User)list.get(0);
		} 
		return loginUser;
	}

	@Override
	public int deleteBatch(String idStr) {
		String hql = "delete from " + User.class.getSimpleName() + " user where user.id in(:idList)";
		Query query =  getSession().createQuery(hql);
		query.setParameterList("idList", StringUtil.stringToLongList(idStr, ","));
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public boolean isUnique(User user) {
		String hql = " from " + User.class.getSimpleName() + " user where user.userId=:userId";
		Long id = user.getId();
		if(id != null) {
			hql += " and user.id<>:id";
		}
		Query query =  getSession().createQuery(hql);
		query.setParameter("userId", user.getUserId());
		if(id != null) {
			query.setParameter("id", id);
		}
		List list = query.list();
		if(list != null && list.size() > 0) {
			return false;
		}
		return true;
	}
}
