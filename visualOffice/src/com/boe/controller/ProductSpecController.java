package com.boe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boe.entity.ProductSpec;
import com.boe.service.ProductSpecService;

@Controller
@RequestMapping(value="/productspec")
public class ProductSpecController {

	private static Logger log = Logger.getLogger(ProductSpecController.class);
	
	@Autowired
	private ProductSpecService productSpecService;
	
	@RequestMapping({"/list"})
	public String list(String productId, Model model) {
		String target = "/productspec/list";
		log.debug("productId:" + productId);
		List<ProductSpec> list = productSpecService.getListByProductId(productId);
		model.addAttribute("list", list);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/add"})
	public String add(String productId, Model model) {
		String target = "/productspec/add";
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(@RequestBody ProductSpec productSpec) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("ProductId=" + productSpec.getProductId());
		boolean isUnique = productSpecService.isUnique(productSpec);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = productSpecService.save(productSpec);
		log.debug("result=" + result);
		if(result) {
			map.put("success", "true");  
		} 
		return map;
	}
	
	@RequestMapping({"/modify"})
	public String modify(ProductSpec productSpec, Model model) {
		String target = "/productspec/modify";
		log.debug("id:" + productSpec.getId());
		ProductSpec obj = productSpecService.load(productSpec);
		model.addAttribute("productSpec", obj);
		if(obj != null) {
			model.addAttribute("productId", obj.getProductId());
		}
		return target;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public Map<String, String> update(@RequestBody ProductSpec productSpec) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean isUnique = productSpecService.isUnique(productSpec);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = productSpecService.update(productSpec);
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
	public Map<String, String> delete(ProductSpec productSpec, Model model) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean result = productSpecService.delete(productSpec);
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
		int result = productSpecService.deleteBatch(idStr);
		if(result > 0) {
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
}
