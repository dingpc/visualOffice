package com.boe.service;

import java.util.List;
import java.util.Map;

import com.boe.entity.SystemLog;

public interface SystemLogService {

	public abstract boolean save(SystemLog systemLog);
	
	public abstract List<SystemLog> getAllList();
	
	public abstract List<SystemLog> getSearchList(Map map);
}
