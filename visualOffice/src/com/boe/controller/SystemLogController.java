package com.boe.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boe.common.util.DateUtil;
import com.boe.common.util.StringUtil;
import com.boe.entity.SystemLog;
import com.boe.service.SystemLogService;

@Controller
@RequestMapping(value="/systemlog")
public class SystemLogController {

	private static Logger log = Logger.getLogger(SystemLogController.class);
	
	@Autowired
	private SystemLogService systemLogService;
	
	@RequestMapping({"/list"})
	public String list(HttpServletRequest request, Model model) {
		String target = "/systemlog/list";
		String searchCreateTime = StringUtil.isEmpty(request.getParameter("searchCreateTime")) ? DateUtil.convertDateToString("yyyy-MM-dd", new Date()) : request.getParameter("searchCreateTime");
		log.info("searchCreateTime:" + searchCreateTime);
		Map map = new HashMap();
		map.put("searchCreateTime", searchCreateTime);
		List<SystemLog> list = systemLogService.getSearchList(map);
		model.addAttribute("list", list);
		model.addAttribute("searchCreateTime", searchCreateTime);
		return target;
	}
}
