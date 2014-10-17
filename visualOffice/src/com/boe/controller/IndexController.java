package com.boe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boe.common.constant.SystemConstant;
import com.boe.common.listener.SessionListener;
import com.boe.common.util.EncryptUtil;
import com.boe.entity.User;
import com.boe.service.UserService;

@Controller
@RequestMapping(value="/index")
public class IndexController {
	private static Logger log = Logger.getLogger(IndexController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping({"/login"})
	public String login(HttpServletRequest request, HttpSession session, Model model) {
		String target = "/login";
		log.debug("login");
		return target;
	}
	
	@RequestMapping({""})
	public String home(HttpServletRequest request, HttpSession session, Model model) {
		String target = "/home";
		return target;
	}
	
	@RequestMapping({"/top"})
	public String top(HttpServletRequest request, HttpSession session, Model model) {
		String target = "/top";
		return target;
	}
	
	@RequestMapping({"/logout"})
	public String logout(HttpServletRequest request, HttpSession session, Model model) {
		String target = "/login";
		if (null != session) {
			//session.removeAttribute(SystemConstant.USER);
			log.debug("----1");
			session.invalidate();
			log.debug("----2");
		}
		return target;
	}
	
	@RequestMapping({"/validate"})
	@ResponseBody
	public String validate(@RequestBody User user, HttpServletRequest request, HttpServletResponse response,  Model model) {
		String result = "";
	    
		String userId = user.getUserId();
		log.debug("userId=" + userId);
		String password = EncryptUtil.encrypt(user.getPassword());
		User curUser = userService.getUserByUserId(userId);
		log.debug("curUserId=" + curUser.getUserId());
		if(curUser != null && password.equals(curUser.getPassword())) {
			log.debug("success");
			result = "1";
			HttpSession session = request.getSession();
			session.setAttribute(SystemConstant.USER, curUser);
			sessionHandlerByCacheMap(session);
		} else {
			log.debug("failure");
			result = "2";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void sessionHandlerByCacheMap(HttpSession session) {
		Map sessionMap = SessionListener.sessionContext.getSessionMap();
		String idInSession = "";
		if(sessionMap != null) {
			Set set = sessionMap.keySet();
			Iterator it = set.iterator();
			while(it.hasNext()) {
				idInSession += it.next() + ",";
			}
		}
		log.debug("user in session is:" + idInSession);
		
		log.debug("sessionId=" + session.getId());
		User user = (User)session.getAttribute(SystemConstant.USER);
		String id = user.getId().toString();
		if (SessionListener.sessionContext.getSessionMap().get(id) != null) {
			HttpSession oldSession = (HttpSession) SessionListener.sessionContext.getSessionMap().get(id);
			// 注销在线用户
			if(oldSession != null) {
				log.debug("oldSession is not null");
				oldSession.invalidate();
			}
			SessionListener.sessionContext.getSessionMap().remove(id);
			// 清除在线用户后，更新map,替换map sessionid
			SessionListener.sessionContext.getSessionMap().remove(session.getId());
			SessionListener.sessionContext.getSessionMap().put(id, session);
		} else {
			// 根据当前sessionid 取session对象。 更新map key=用户名 value=session对象 删除map
			HttpSession curSession = SessionListener.sessionContext.getSessionMap().get(session.getId());
			SessionListener.sessionContext.getSessionMap().put(id, curSession);
			SessionListener.sessionContext.getSessionMap().remove(session.getId());
		}
	}
	
	@RequestMapping({"/clear"})
	@ResponseBody  
	public Map<String, String> clear(HttpSession session, Model model) {
		log.debug("clear success");
		Map<String, String> map = new HashMap<String, String>(1);
		if(session != null) {
			session.invalidate();
		}
		map.put("success", "true");  
		return null;
	}
}
