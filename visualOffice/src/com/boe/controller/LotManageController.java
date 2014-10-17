package com.boe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boe.common.util.SessionUtil;
import com.boe.entity.LotManage;
import com.boe.entity.SystemLog;
import com.boe.service.LotManageService;
import com.boe.service.SystemLogService;

@Controller
@RequestMapping(value="/lotmanage")
public class LotManageController {

	private static Logger log = Logger.getLogger(LotManageController.class);
	
	@Autowired
	private LotManageService lotManageService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@RequestMapping({"/list"})
	public String list(String productId, Model model) {
		String target = "/lotmanage/list";
		log.debug("productId:" + productId);
		List<LotManage> list = lotManageService.getListByProductId(productId);
		model.addAttribute("list", list);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/add"})
	public String add(String productId, Model model) {
		String target = "/lotmanage/add";
		LotManage lotManage = new LotManage();
		lotManage.setGlassExtra("N");
		lotManage.setSplitInfo("N");
		model.addAttribute("lotManage", lotManage);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(@RequestBody LotManage lotManage, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("ProductId=" + lotManage.getProductId());
		boolean isUnique = lotManageService.isUnique(lotManage);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = lotManageService.save(lotManage);
		log.debug("result=" + result);
		if(result) {
			systemLogService.save(new SystemLog(SessionUtil.getSessionUserId(request), "新增", "LOT:" + lotManage.getStepId() + "----" + lotManage.getProductId()));
			map.put("success", "true");  
		} 
		return map;
	}
	
	@RequestMapping({"/modify"})
	public String modify(LotManage lotManage, Model model) {
		String target = "/lotmanage/modify";
		log.debug("id:" + lotManage.getId());
		LotManage obj = lotManageService.load(lotManage);
		model.addAttribute("lotManage", obj);
		if(obj != null) {
			model.addAttribute("productId", obj.getProductId());
		}
		return target;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public Map<String, String> update(@RequestBody LotManage lotManage, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean isUnique = lotManageService.isUnique(lotManage);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = lotManageService.update(lotManage);
		log.debug("result=" + result);
		if(result) {
			systemLogService.save(new SystemLog(SessionUtil.getSessionUserId(request), "修改", "LOT:" + lotManage.getStepId() + "----" + lotManage.getProductId()));
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
	
	@RequestMapping({"/delete"})
	@ResponseBody  
	public Map<String, String> delete(LotManage lotManage, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean result = lotManageService.delete(lotManage);
		if(result) {
			systemLogService.save(new SystemLog(SessionUtil.getSessionUserId(request), "删除", "LOT:" + lotManage.getId()));
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
	
	@RequestMapping({"/deleteBatch"})
	@ResponseBody  
	public Map<String, String> deleteBatch(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(1);
		String idStr = request.getParameter("idStr");
		log.debug("idStr:" + idStr);
		int result = lotManageService.deleteBatch(idStr);
		if(result > 0) {
			systemLogService.save(new SystemLog(SessionUtil.getSessionUserId(request), "批量删除", "LOT:" + "idStr:" + idStr));
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
}
