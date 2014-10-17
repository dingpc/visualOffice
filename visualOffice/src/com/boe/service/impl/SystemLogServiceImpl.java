package com.boe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.SystemLogDao;
import com.boe.entity.SystemLog;
import com.boe.service.SystemLogService;

@Service
@Transactional
public class SystemLogServiceImpl implements SystemLogService {

	@Autowired
	private SystemLogDao systemLogDao;
	
	@Override
	public boolean save(SystemLog systemLog) {
		return systemLogDao.save(systemLog);
	}

	@Override
	public List<SystemLog> getAllList() {
		return systemLogDao.getAllList();
	}

	@Override
	public List<SystemLog> getSearchList(Map map) {
		return systemLogDao.getSearchList(map);
	}

}
