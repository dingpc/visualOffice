package com.boe.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelReaderUtil {
	private static Logger log = Logger.getLogger(ExcelReaderUtil.class);
	public static Integer XLS_TYPE = 0;
	public static Integer XLSX_TYPE = 1;
	public static Integer OTHER_TYPE = 2;

	public static String XLS_TYPE_STRING = ".xls";
	public static String XLSX_TYPE_STRING = ".xlsx";

	public static Integer getType(final String path) {
		if (StringUtils.isEmpty(path)) {
			return OTHER_TYPE;
		}
		String type = StringUtils.substring(path, path.lastIndexOf('.'), path.length());
		type = StringUtils.lowerCase(type);
		if (XLS_TYPE_STRING.equals(type)) {
			return XLS_TYPE;
		} else if (XLSX_TYPE_STRING.equals(type)) {
			return XLSX_TYPE;
		} else {
			return OTHER_TYPE;
		}
	}

	public static Sheet getSheet(String path) {
		Integer type = getType(path);
		if (OTHER_TYPE.equals(type)) {
			return null;
		}
		try {
			InputStream is = new FileInputStream(path);
			if (XLS_TYPE.equals(type)) {// 2003
				POIFSFileSystem fs = new POIFSFileSystem(is);
				return new HSSFWorkbook(fs).getSheetAt(0);
			} else {// 2007
				return new XSSFWorkbook(is).getSheetAt(0);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public static Sheet getSheet(MultipartFile uploadFile) {
		String fileName = uploadFile.getOriginalFilename();
		Integer type = getType(fileName);
		if (OTHER_TYPE.equals(type)) {
			return null;
		}
		try {
			InputStream is = uploadFile.getInputStream();
			if (XLS_TYPE.equals(type)) {// 2003
				POIFSFileSystem fs = new POIFSFileSystem(is);
				return new HSSFWorkbook(fs).getSheetAt(0);
			} else {// 2007
				XSSFWorkbook wb = new XSSFWorkbook(is);
				return wb.getSheetAt(0);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param path
	 * @return String 表头内容的数组
	 */
	public static List<String> readExcelTitle(String path) {
		Sheet sheet = getSheet(path);
		if (sheet == null) {
			return Collections.EMPTY_LIST;
		}
		Row row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		List<String> title = new LinkedList<String>();
		for (int i = 0; i < colNum; i++) {
			title.add(getCellFormatValue(row.getCell(i)));
		}
		return title;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param path
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public static Map<Integer, String> readExcelContent(String path) {
		Sheet sheet = getSheet(path);
		if (sheet == null) {
			return Collections.EMPTY_MAP;
		}
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		Row row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str += getCellFormatValue(row.getCell(j)).trim() + "    ";
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param path
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public static List<String[]> readExcelContent(MultipartFile uploadFile) {
		Sheet sheet = getSheet(uploadFile);
		if (sheet == null) {
			return null;
		}
		List<String[]> list = new ArrayList<String[]>();
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		Row row = sheet.getRow(0);
		// 得到总列数
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			String[] arr = new String[colNum];
			for(int j = 0; j < colNum; j++) {
				arr[j] = getCellFormatValue(row.getCell(j));
			}
			list.add(arr);
		}
		return list;
	}
	
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private static String getStringCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell == null || strCell.equals("")) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private static String getDateCellValue(Cell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(cell.getDateCellValue());
				result = (calendar.get(Calendar.YEAR) + 1900) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			log.error("日期格式不正确!");
			log.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	public static void main(String[] args) {
		// 对读取Excel表格标题测试
		List<String> title = ExcelReaderUtil.readExcelTitle("E:\\test.xlsx");
		System.out.println("获得Excel表格的标题:");
		for (String s : title) {
			System.out.print(s + " ");
		}
		Map<Integer, String> map = ExcelReaderUtil
				.readExcelContent("E:\\test.xlsx");
		System.out.println("获得Excel表格的内容:");
		for (int i = 1; i <= map.size(); i++) {
			System.out.println(map.get(i));
		}
	}
}
