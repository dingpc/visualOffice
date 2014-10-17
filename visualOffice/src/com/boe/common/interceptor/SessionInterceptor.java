package com.boe.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boe.common.constant.SystemConstant;
import com.boe.entity.User;

@Repository
public class SessionInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = Logger.getLogger(SessionInterceptor.class);
	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		log.debug("execute");
        request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=UTF-8");  
   
        //过滤登录、退出访问  
        String[] noFilters = new String[] { "/", "/index/validate", "/index/logout", "/index/login"};  
        String uri = request.getRequestURI();  
        log.debug("uri=" + uri);
        boolean beFilter = true;  
        boolean result = true;  
        for (String s : noFilters) {  
//            if (uri.indexOf(s) != -1) {  
//                beFilter = false;  
//                break;  
//            }
        	if (s.equals(uri)) {  
        		log.debug(uri + "未被拦截！");
                beFilter = false;  
                break;  
            }  
        }  
        log.debug(uri + "将做拦截处理！");
        User user = (User)request.getSession().getAttribute(SystemConstant.USER);
        if (beFilter) {  
            if (null == user) {  
            	log.debug("session超时！");
                //ajax方式交互  
                if (request.getHeader("x-requested-with") != null  
                        && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))// 如果是ajax请求响应头会有，x-requested-with；  
                {                     
                    response.setHeader("sessionstatus", "timeout");// 在响应头设置session状态  
                    return false;  
                }  
                // 未登录  
                PrintWriter out = response.getWriter();  
                StringBuilder builder = new StringBuilder();  
                builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
                builder.append("alert(\"页面超时，请重新登录\");");  
                builder.append("window.top.location.href='/index/logout';");  
                builder.append("</script>");  
                out.print(builder.toString());  
                out.close();  
                return false;  
            } else {  
            	log.debug("session正常，正常提交！");
            }  
        }  
        result = super.preHandle(request, response, handler);
        log.debug("result=" + result);
        return result;  
    }  
}
