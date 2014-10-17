package com.boe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boe.common.util.EncryptUtil;
import com.boe.entity.User;
import com.boe.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping({"/list"})
	public String list(HttpServletRequest request, HttpSession session, Model model) {
		String target = "/user/list";
		log.debug("success");
		List<User> userList = userService.getAllUser();
		model.addAttribute("userList", userList);
		return target;
	}
	
	@RequestMapping({"/add"})
	public String add(HttpServletRequest request, HttpSession session, Model model) {
		String target = "/user/add";
		User user = new User();
		user.setIsAdmin("Y");
		user.setIsAdd("Y");
		user.setIsModify("Y");
		user.setIsDelete("Y");
		model.addAttribute("user", user);
		log.debug("success");
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(@RequestBody User user) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("userId:" + user.getUserId());
		boolean isUnique = userService.isUnique(user);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		String password = user.getPassword();
		if(password != null && password.length() > 0) {
			user.setPassword(EncryptUtil.encrypt(password));
		} 
		boolean result = userService.save(user);
		
		if(result) {
			map.put("success", "true");  
		} 
		return map;
	}
	
	@RequestMapping({"/modify"})
	public String modify(User user, Model model) {
		String target = "/user/modify";
		log.debug("userId:" + user.getUserId());
		User curUser = userService.load(user);
		String password = curUser.getPassword();
		curUser.setPassword(EncryptUtil.decrypt(password));
		model.addAttribute("user", curUser);
		return target;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public Map<String, String> update(@RequestBody User user) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("userId:" + user.getUserId());
		boolean isUnique = userService.isUnique(user);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		String password = user.getPassword();
		if(password != null && password.length() > 0) {
			user.setPassword(EncryptUtil.encrypt(password));
		} 
		boolean result = userService.update(user);
		log.debug("result=" + result);
		if(result) {
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
	
	@RequestMapping({"/delete"})
	@ResponseBody  
	public Map<String, String> delete(User user, Model model) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("userId:" + user.getUserId());
		boolean result = userService.delete(user);
		if(result) {
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
	
	@RequestMapping({"/deleteBatch"})
	@ResponseBody  
	public Map<String, String> deleteBatch(HttpServletRequest request, Model model) {
		Map<String, String> map = new HashMap<String, String>(1);
		String idStr = request.getParameter("idStr");
		log.debug("idStr:" + idStr);
		int result = userService.deleteBatch(idStr);
		if(result > 0) {
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
}
