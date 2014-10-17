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

import com.boe.entity.Product;
import com.boe.service.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	
	private static Logger log = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping({"/list"})
	public String list(HttpServletRequest request, HttpSession session, Model model) {
		String target = "/product/list";
		log.debug("success");
		List<Product> list = productService.getAllList();
		model.addAttribute("list", list);
		return target;
	}
	
	@RequestMapping({"/add"})
	public String add(HttpServletRequest request, HttpSession session, Model model) {
		String target = "/product/add";
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(@RequestBody Product product) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("id=" + product.getId());
		boolean isUnique = productService.isUnique(product);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = productService.save(product);
		log.debug("result=" + result);
		if(result) {
			map.put("success", "true");  
		} 
		return map;
	}
	
	@RequestMapping({"/modify"})
	public String modify(Product product, Model model) {
		String target = "/product/modify";
		log.debug("id:" + product.getId());
		Product obj = productService.load(product);
		model.addAttribute("product", obj);
		return target;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public Map<String, String> update(@RequestBody Product product) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean isUnique = productService.isUnique(product);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = productService.update(product);
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
	public Map<String, String> delete(Product product, Model model) {
		Map<String, String> map = new HashMap<String, String>(1);
		String id = product.getId().toString();
		if(productService.hasRelationData(id)) {
			map.put("success", "relation");  
			return map;
		}
		boolean result = productService.delete(product);
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
		int result = productService.deleteBatch(idStr);
		if(result > 0) {
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
	
	@RequestMapping({"/info"})
	public String info(Product product, HttpServletRequest request, HttpSession session, Model model) {
		String target = "/product/info";
		log.debug("success:" + request.getParameter("id"));
		Product obj = productService.load(product);
		model.addAttribute("product", obj);
		return target;
	}
}
