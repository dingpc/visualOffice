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

import com.boe.entity.ProductSample;
import com.boe.service.ProductSampleService;

@Controller
@RequestMapping(value="/productsample")
public class ProductSampleController {
	
	private static Logger log = Logger.getLogger(ProductSampleController.class);
	
	@Autowired
	private ProductSampleService productSampleService;
	
	@RequestMapping({"/list"})
	public String list(String productId, Model model) {
		String target = "/productsample/list";
		log.debug("productId:" + productId);
		List<ProductSample> list = productSampleService.getListByProductId(productId);
		model.addAttribute("list", list);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/add"})
	public String add(String productId, Model model) {
		String target = "/productsample/add";
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(@RequestBody ProductSample productSample) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("ProductId=" + productSample.getProductId());
		boolean isUnique = productSampleService.isUnique(productSample);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = productSampleService.save(productSample);
		log.debug("result=" + result);
		if(result) {
			map.put("success", "true");  
		} 
		return map;
	}
	
	@RequestMapping({"/modify"})
	public String modify(ProductSample productSample, Model model) {
		String target = "/productsample/modify";
		log.debug("id:" + productSample.getId());
		ProductSample obj = productSampleService.load(productSample);
		model.addAttribute("productSample", obj);
		if(obj != null) {
			model.addAttribute("productId", obj.getProductId());
		}
		return target;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public Map<String, String> update(@RequestBody ProductSample productSample) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean isUnique = productSampleService.isUnique(productSample);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = productSampleService.update(productSample);
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
	public Map<String, String> delete(ProductSample productSample, Model model) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean result = productSampleService.delete(productSample);
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
		int result = productSampleService.deleteBatch(idStr);
		if(result > 0) {
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
}
