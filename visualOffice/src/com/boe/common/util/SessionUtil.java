package com.boe.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.boe.common.constant.SystemConstant;
import com.boe.entity.User;

public class SessionUtil {

	public static HttpSession getHttpSession(HttpServletRequest request) {
		return request.getSession(true);
	}

	public static String getSessionUserId(HttpServletRequest request) {
		User user = (User)getHttpSession(request).getAttribute(SystemConstant.USER);
		return user.getUserId();
	}
	
	public static String getSessionUserName(HttpServletRequest request) {
		User user = (User)getHttpSession(request).getAttribute(SystemConstant.USER);
		return user.getUserName();
	}
}
