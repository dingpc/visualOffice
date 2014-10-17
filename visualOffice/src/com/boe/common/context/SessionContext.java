package com.boe.common.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.boe.common.constant.SystemConstant;
import com.boe.entity.User;

public class SessionContext {
	private static SessionContext instance;
	
	private static Logger log = Logger.getLogger(SessionContext.class);
	
	private HashMap<String, HttpSession> sessionMap;
	
	private SessionContext() {
		sessionMap = new HashMap<String, HttpSession>();
	}
	
	public static SessionContext getInstance() {  
        if (instance == null) {  
            instance = new SessionContext();  
        }  
        return instance;  
    }  
	
	public synchronized void addSession(HttpSession session) {
		log.debug("sessionId=" + session.getId());
		if(session != null) {
			sessionMap.put(session.getId(), session);
		}
	}
	
	public synchronized void delSession(HttpSession session) {
		log.debug("sessionId=" + session.getId());
		log.debug("----3");
		if(session != null) {
			sessionMap.remove(session.getId());
			User user = (User)session.getAttribute(SystemConstant.USER);
			if(user != null) {
				sessionMap.remove(user.getId().toString());
				session.removeAttribute(SystemConstant.USER);
			}
		}
		String idInSession = "";
		if(sessionMap != null) {
			Set set = sessionMap.keySet();
			Iterator it = set.iterator();
			while(it.hasNext()) {
				idInSession += it.next() + ",";
			}
		}
		log.debug("session Map key is:" + idInSession);
		log.debug("----4");
	}
	
	public synchronized HttpSession getSession(String sessionId) {
		log.debug("sessionId=" + sessionId);
		if(sessionId == null) {
			return null;
		}
		return sessionMap.get(sessionId);
	}

	public HashMap<String, HttpSession> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(HashMap<String, HttpSession> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	
}
