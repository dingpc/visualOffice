package com.boe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boe.dao.ProcessFlowDao;
import com.boe.entity.ProcessFlow;
import com.boe.service.ProcessFlowService;

@Service
@Transactional
public class ProcessFlowServiceImpl implements ProcessFlowService {

	@Autowired
	private ProcessFlowDao processFlowDao;
	
	@Override
	public boolean save(ProcessFlow processFlow) {
		return this.processFlowDao.save(processFlow);
	}

	@Override
	public ProcessFlow load(ProcessFlow processFlow) {
		return this.processFlowDao.load(processFlow);
	}

	@Override
	public boolean delete(ProcessFlow processFlow) {
		return this.processFlowDao.delete(processFlow);
	}

	@Override
	public boolean update(ProcessFlow processFlow) {
		return this.processFlowDao.update(processFlow);
	}

	@Override
	public int deleteBatch(String idStr) {
		return this.processFlowDao.deleteBatch(idStr);
	}

	@Override
	public List<ProcessFlow> getAllList() {
		return this.processFlowDao.getAllList();
	}

	@Override
	public List<ProcessFlow> getListByProductId(String productId) {
		return this.processFlowDao.getListByProductId(productId);
	}

	@Override
	public boolean isUnique(ProcessFlow processFlow) {
		return this.processFlowDao.isUnique(processFlow);
	}

	@Override
	public boolean saveBatch(List<ProcessFlow> list) {
		return this.processFlowDao.saveBatch(list);
	}
}
