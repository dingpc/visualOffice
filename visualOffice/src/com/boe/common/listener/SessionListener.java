package com.boe.common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.boe.common.context.SessionContext;

public class SessionListener implements HttpSessionListener {
	
	public static SessionContext sessionContext = SessionContext.getInstance();  
	private static Logger log = Logger.getLogger(SessionListener.class);
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.debug("execute");
		sessionContext.addSession(event.getSession()); 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.debug("execute");
		sessionContext.delSession(event.getSession());
	}
}
