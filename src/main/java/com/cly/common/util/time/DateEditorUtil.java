/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * xuyin 2014年6月24号创建
 */
package com.cly.common.util.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class DateEditorUtil extends PropertyEditorSupport {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(text);
		} catch (ParseException e) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = format.parse(text);
			} catch (ParseException e1) {
				logger.info("日期转换出现异常，异常信息是：{}" + e1.getMessage());
			}
		}
		setValue(date);
	}

	/**
	 * 使用方法
	 * 在Controller中添加一下方法
	 * @param binder
	 */
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(Date.class, new DateEditor());
//	}
}