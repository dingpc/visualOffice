package com.boe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.boe.common.constant.SystemConstant;
import com.boe.common.util.ExcelReaderUtil;
import com.boe.entity.ProcessFlow;
import com.boe.service.ProcessFlowService;

@Controller
@RequestMapping(value="/processflow")
public class ProcessFlowController {

	private static Logger log = Logger.getLogger(ProcessFlowController.class);
	
	@Autowired
	private ProcessFlowService processFlowService;
	
	@RequestMapping({"/list"})
	public String list(@RequestParam("productId") String productId, Model model) {
		String target = "/processflow/list";
		log.debug("productId:" + productId);
		List<ProcessFlow> list = processFlowService.getListByProductId(productId);
		model.addAttribute("list", list);
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/add"})
	public String add(String productId, Model model) {
		String target = "/processflow/add";
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, String> save(@RequestBody ProcessFlow processFlow) {
		Map<String, String> map = new HashMap<String, String>(1);
		log.debug("ProductId=" + processFlow.getProductId());
		boolean isUnique = processFlowService.isUnique(processFlow);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = processFlowService.save(processFlow);
		log.debug("result=" + result);
		if(result) {
			map.put("success", "true");  
		} 
		return map;
	}
	
	@RequestMapping({"/modify"})
	public String modify(ProcessFlow processFlow, Model model) {
		String target = "/processflow/modify";
		log.debug("id:" + processFlow.getId());
		ProcessFlow obj = processFlowService.load(processFlow);
		model.addAttribute("processFlow", obj);
		if(obj != null) {
			model.addAttribute("productId", obj.getProductId());
		}
		return target;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public Map<String, String> update(@RequestBody ProcessFlow processFlow) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean isUnique = processFlowService.isUnique(processFlow);
		if(!isUnique) {
			map.put("success", "isNotUnique");
			return map;
		}
		boolean result = processFlowService.update(processFlow);
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
	public Map<String, String> delete(ProcessFlow processFlow, Model model) {
		Map<String, String> map = new HashMap<String, String>(1);
		boolean result = processFlowService.delete(processFlow);
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
		int result = processFlowService.deleteBatch(idStr);
		log.debug("result:" + result);
		if(result > 0) {
			map.put("success", "true");  
		} else {
			map.put("success", "false");  
		}
		return map;
	}
	
	@RequestMapping({"/toImport"})
	public String toImport(String productId, Model model) {
		String target = "/processflow/import";
		model.addAttribute("productId", productId);
		return target;
	}
	
	@RequestMapping({"/importData"})
	@ResponseBody
	public Map<String, String> importData(String productId, MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap();
		log.info("productId:" + productId);
		//文件不存在
		if(uploadFile == null || uploadFile.isEmpty()) {
			map.put("success", "empty");
			return map;
		}
		String fileName = uploadFile.getOriginalFilename();
		log.info("fileName:" + fileName);
		//文件格式错误，不是Excel
		if(ExcelReaderUtil.OTHER_TYPE.equals(ExcelReaderUtil.getType(fileName))){
			map.put("success", "formatError");
			return map;
		}
		// 无sheet
		Sheet sheet = ExcelReaderUtil.getSheet(uploadFile);
		if (sheet == null) {
			map.put("success", "empty");
			return map;
		}
		List<ProcessFlow> list = new ArrayList<ProcessFlow>();
		int rowNum = sheet.getLastRowNum();
		if(rowNum > SystemConstant.PROCESSFLOW_ROW_COUNT || rowNum < 2) {
			map.put("success", "rowLimit");
			return map;
		}
		Row row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// Excel的列数错误
		if(colNum != SystemConstant.PROCESSFLOW_COL_COUNT) {
			map.put("success", "colError");
			return map;
		}
		
		Map stepMap = new HashMap();
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			ProcessFlow processFlow = new ProcessFlow();
			processFlow.setProductId(productId);
			processFlow.setStepId(ExcelReaderUtil.getCellFormatValue(row.getCell(0)));
			processFlow.setEquipName(ExcelReaderUtil.getCellFormatValue(row.getCell(1)));
			processFlow.setRcp(ExcelReaderUtil.getCellFormatValue(row.getCell(2)));
			processFlow.setProcessSpec(ExcelReaderUtil.getCellFormatValue(row.getCell(3)));
			processFlow.setOrderCode(ExcelReaderUtil.getCellFormatValue(row.getCell(4)));
			stepMap.put(processFlow.getStepId(), "0");
			boolean isUnique = processFlowService.isUnique(processFlow);
			if(!isUnique) {
				map.put("success", "isNotUnique");
				map.put("rowNum", "" + (i + 1));
				return map;
			}
			list.add(processFlow);
		}
		
		if(stepMap.size() < rowNum) {
			map.put("success", "excelRepeat");
			return map;
		}
		boolean result = processFlowService.saveBatch(list);
		if(!result) {
			map.put("success", "saveError");
			return map;
		}
		map.put("success", "true");
		return map;
	}
	
}
