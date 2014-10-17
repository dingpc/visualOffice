package com.boe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boe.common.util.StringUtil;
import com.boe.entity.Lot;
import com.boe.entity.LotManage;
import com.boe.entity.LotProgress;
import com.boe.service.LotManageService;
import com.boe.service.LotProgressService;
import com.boe.service.LotService;

@Controller
@RequestMapping(value="/lotprogress")
public class LotProgressController {
	private static Logger log = Logger.getLogger(LotProgressController.class);
	
	@Autowired
	private LotManageService lotManageService;
	
	@Autowired
	private LotService lotService;
	
	@Autowired
	private LotProgressService lotProgressService;
	
	@RequestMapping({"/home"})
	public String list(String productId, Model model) {
		String target = "/lotprogress/home";
		log.debug("productId:" + productId);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/info"})
	public String info(String productId, Model model) {
		String target = "/lotprogress/info";
		log.debug("productId:" + productId);
		List<Lot> lotList = lotService.getListByProductId(productId);
		List<LotManage> lotManageList = lotManageService.getListByProductId(productId);
		List<Map> list = new ArrayList<Map>();
		int lotSize = 0;
		if(lotList != null) {
			lotSize = lotList.size();
		}
		for(int i = 0; i < lotManageList.size(); i++) {
			Map map = new HashMap();
			LotManage lotManage = lotManageList.get(i);
			Long manageId = lotManage.getId();
			map.put("productId", productId);
			map.put("manageId", manageId);
			List tempList = lotProgressService.getLotProgressList(map);
			log.info("StepId:" + lotManage.getStepId());
			if(tempList != null) {
				log.info("tempList size:" + tempList.size());
			}
			map.put("progressList", tempList);
			map.put("lotManage", lotManage);
			map.put("flag", i);
			list.add(map);
		}

		for(int i = 0; i < list.size(); i++) {
			Map map1 = (Map)list.get(i);
			log.info("flag:" + map1.get("flag"));
		}
		model.addAttribute("lotList", lotList);
		model.addAttribute("lotSize", lotSize);
		model.addAttribute("productId", productId);
		model.addAttribute("list", list);
		
		return target;
	}
	
	@RequestMapping({"/modify"})
	public String modify(String productId, Model model) {
		String target = "/lotprogress/modify";
		log.debug("productId:" + productId);
		List<Lot> lotList = lotService.getListByProductId(productId);
		List<LotManage> lotManageList = lotManageService.getListByProductId(productId);
		List<Map> list = new ArrayList<Map>();
		int lotSize = 0;
		if(lotList != null) {
			lotSize = lotList.size();
		}
		for(int i = 0; i < lotManageList.size(); i++) {
			Map map = new HashMap();
			LotManage lotManage = lotManageList.get(i);
			Long manageId = lotManage.getId();
			map.put("productId", productId);
			map.put("manageId", manageId);
			List tempList = lotProgressService.getLotProgressList(map);
			log.info("StepId:" + lotManage.getStepId());
			if(tempList != null) {
				log.info("tempList size:" + tempList.size());
			}
			map.put("progressList", tempList);
			map.put("lotManage", lotManage);
			map.put("flag", i);
			list.add(map);
		}
		
		for(int i = 0; i < list.size(); i++) {
			Map map1 = (Map)list.get(i);
			log.info("flag:" + map1.get("flag"));
		}
		model.addAttribute("lotList", lotList);
		model.addAttribute("lotSize", lotSize);
		model.addAttribute("productId", productId);
		model.addAttribute("list", list);
		
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(HttpServletRequest request, Model model) {
		Map<String, String> map = new HashMap<String, String>(1);
		String checkedProgress = request.getParameter("checkedProgress");
		String notCheckedProgress = request.getParameter("notCheckedProgress");
		log.debug("checkedProgress=" + checkedProgress);
		log.debug("notCheckedProgress=" + notCheckedProgress);
		boolean result = true;
		List<LotProgress> list = new ArrayList<LotProgress>();
		
		if(!StringUtil.isEmpty(checkedProgress)) {
			String[] checkedArr = checkedProgress.split(",");
			for(String str : checkedArr) {
				LotProgress progress = new LotProgress();
				String[] arr = str.split("####");
				progress.setManageId(new Long(arr[0]));
				progress.setLotId(new Long(arr[1]));
				progress.setIsComplete("Y");
				list.add(progress);
			}
		}
		if(!StringUtil.isEmpty(notCheckedProgress)) {
			String[] checkedArr = notCheckedProgress.split(",");
			for(String str : checkedArr) {
				LotProgress progress = new LotProgress();
				String[] arr = str.split("####");
				progress.setManageId(new Long(arr[0]));
				progress.setLotId(new Long(arr[1]));
				progress.setIsComplete("N");
				list.add(progress);
			}
		}
		log.debug("list size:" + list.size());
		result = lotProgressService.saveOrUpdateBatch(list);
		log.debug("result=" + result);
		if(result) {
			map.put("success", "true");  
		} 
		return map;
	}
}
