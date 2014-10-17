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

import com.boe.entity.ProductSplit;
import com.boe.service.ProductSplitService;

@Controller
@RequestMapping(value="/productsplit")
public class ProductSplitController {

	private static Logger log = Logger.getLogger(ProductSplitController.class);
	
	@Autowired
	private ProductSplitService productSplitService;
	
	@RequestMapping({"/list"})
	public String list(String productId, Model model) {
		String target = "/productsplit/list";
		log.debug("productId:" + productId);
		List<ProductSplit> list = productSplitService.getListByProductId(productId);
		model.addAttribute("list", list);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/add"})
	public String add(String productId, Model model) {
		String target = "/productsplit/add";
		log.debug("productId:" + productId);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(@RequestBody ProductSplit productSplit) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("ProductId=" + productSplit.getProductId());
		boolean isUnique = productSplitService.isUnique(productSplit);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = productSplitService.save(productSplit);
		log.debug("result=" + result);
		if(result) {
			map.put("success", "true");  
		} 
		return map;
	}
	
	@RequestMapping({"/modify"})
	public String modify(ProductSplit productSplit, Model model) {
		String target = "/productsplit/modify";
		log.debug("id:" + productSplit.getId());
		ProductSplit obj = productSplitService.load(productSplit);
		model.addAttribute("productSplit", obj);
		if(obj != null) {
			model.addAttribute("productId", obj.getProductId());
		}
		return target;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public Map<String, String> update(@RequestBody ProductSplit productSplit) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean isUnique = productSplitService.isUnique(productSplit);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = productSplitService.update(productSplit);
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
	public Map<String, String> delete(ProductSplit productSplit, Model model) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean result = productSplitService.delete(productSplit);
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
		int result = productSplitService.deleteBatch(idStr);
		if(result > 0) {
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
}
