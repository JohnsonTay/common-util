/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zhouyang@yiji.com 2016-06-20 17:20 创建
 *
 */
package com.cly.common.util.holiday;

import com.yjf.common.util.ToString;

import java.io.Serializable;

/**
 * @author zhouyang@yiji.com
 */
public class Holiday implements Serializable{

	private static final long serialVersionUID = 4098282693768400315L;

	/** 时间 */
	private Day day;
	
	public Holiday(int year, int month, int date) {
		this(new Day(year, month, date)) ;
	}
	public Holiday(Day day) {
		setDay(day);
	}
	
	public Day getDay() {
		return day;
	}
	
	public void setDay(Day day) {
		this.day = day;
	}


	public String toString() {
		return ToString.toString(this);
	}
}
