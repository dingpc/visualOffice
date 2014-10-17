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
import com.boe.entity.Lot;
import com.boe.entity.SystemLog;
import com.boe.service.LotService;
import com.boe.service.SystemLogService;

@Controller
@RequestMapping(value="/lot")
public class LotController {
	private static Logger log = Logger.getLogger(LotController.class);
	
	@Autowired
	private LotService lotService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@RequestMapping({"/list"})
	public String list(String productId, Model model) {
		String target = "/lot/list";
		log.debug("productId:" + productId);
		List<Lot> list = lotService.getListByProductId(productId);
		model.addAttribute("list", list);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/add"})
	public String add(String productId, Model model) {
		String target = "/lot/add";
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(@RequestBody Lot lot, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("ProductId=" + lot.getProductId());
		boolean isUnique = lotService.isUnique(lot);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = lotService.save(lot);
		
		log.debug("result=" + result);
		if(result) {
			systemLogService.save(new SystemLog(SessionUtil.getSessionUserId(request), "新增", "LOT:" + lot.getLotName() + "----" + lot.getProductId()));
			map.put("success", "true");  
		} 
		return map;
	}
	
	@RequestMapping({"/modify"})
	public String modify(Lot lot, Model model) {
		String target = "/lot/modify";
		log.debug("id:" + lot.getId());
		Lot obj = lotService.load(lot);
		model.addAttribute("lot", obj);
		if(obj != null) {
			model.addAttribute("productId", obj.getProductId());
		}
		return target;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public Map<String, String> update(@RequestBody Lot lot, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean isUnique = lotService.isUnique(lot);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = lotService.update(lot);
		log.debug("result=" + result);
		if(result) {
			systemLogService.save(new SystemLog(SessionUtil.getSessionUserId(request), "修改", "LOT:" + lot.getLotName() + "----" + lot.getProductId()));
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
	
	@RequestMapping({"/delete"})
	@ResponseBody  
	public Map<String, String> delete(Lot lot, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean result = lotService.delete(lot);
		if(result) {
			systemLogService.save(new SystemLog(SessionUtil.getSessionUserId(request), "删除", "LOT:" + lot.getId()));
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
		int result = lotService.deleteBatch(idStr);
		if(result > 0) {
			systemLogService.save(new SystemLog(SessionUtil.getSessionUserId(request), "批量删除", "LOT:" + "idStr:" + idStr));
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
}
