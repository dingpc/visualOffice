package com.boe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boe.common.util.FileUtil;

@Controller
@RequestMapping(value="/file")
public class FileController {
	private static Logger log = Logger.getLogger(FileController.class);
	
	@RequestMapping({"/add"})
	public String toupload(String productId, Model model) {
		String target = "/file/add";
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/upload"})
	public Map<String, String> upload(HttpServletRequest request, Model model) {
		Map<String, String> map = new HashMap();
		return map;
	}
	
	@RequestMapping({"/download"})  
    public ModelAndView download(HttpServletRequest request, HttpServletResponse response) {  
		
        String storeName = request.getParameter("fileName");//"processflow_import_template.xls";  
        String realName = request.getParameter("fileName");
        String contentType = "application/octet-stream";  
        log.debug("realName:" + realName);
  
        try {
			FileUtil.download(request, response, storeName, contentType, realName);
		} catch (Exception e) {
			e.printStackTrace();
		}  
        return null;  
    }  
}
