package com.boe.common.config;

import java.text.ParseException;
import java.util.Date;

import com.boe.common.util.DateUtil;
import com.boe.common.util.EncryptUtil;

public class Test {
	public static void main(String[] args) throws ParseException {
		//StringUtil.stringToLongList("e10adc3949ba59abbe56e057f20f883e", ",");
		
		//System.out.println(EncryptUtil.decrypt("e10adc3949ba59abbe56e057f20f883e"));
		System.out.println(EncryptUtil.encrypt("123456"));
		System.out.println(DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
		System.out.println(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss.SSS", "2014-08-28 06:03:58.000"));
	}
}
