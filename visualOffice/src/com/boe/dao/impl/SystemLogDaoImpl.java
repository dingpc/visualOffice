package com.boe.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boe.common.util.DateUtil;
import com.boe.dao.SystemLogDao;
import com.boe.entity.SystemLog;

@Repository
public class SystemLogDaoImpl implements SystemLogDao {

	private static Logger log = Logger.getLogger(SystemLogDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean save(SystemLog systemLog) {
		getSession().save(systemLog);
		return true;
	}

	@Override
	public List<SystemLog> getAllList() {
		Query query = getSession().createQuery("from SystemLog");
	    List list = query.list();
	    return list;
	}

	@Override
	public List<SystemLog> getSearchList(Map map) {
		String searchCreateTime = (String)map.get("searchCreateTime");
		log.info("searchCreateTime" + searchCreateTime);
		Date startTime = new Date();
		Date endTime = new Date();
		try {
			startTime = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", searchCreateTime + " 00:00:00");
			endTime = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", searchCreateTime + " 23:59:59");
		} catch (ParseException e) {
			log.debug("ParseException:" + e.getMessage());
			e.printStackTrace();
		}
		Query query = getSession().createQuery("from SystemLog systemLog where systemLog.createTime between :startTime and :endTime");
		query.setTimestamp("startTime", startTime);
		query.setTimestamp("endTime", endTime);
		List<SystemLog> list = query.list();
		return list;
	}

}
