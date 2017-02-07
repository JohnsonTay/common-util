/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zhouyang@yiji.com 2016-06-20 17:14 创建
 *
 */
package com.cly.common.util.holiday;

import com.yjf.common.util.ToString;

import java.io.Serializable;

/**
 * 工作日
 *
 * @author zhouyang@yiji.com
 */
public class Weekday implements Serializable {
	private static final long serialVersionUID = -6937109527308130887L;
	
	/**
	 * 时间
	 */
	private Day day;
	
	/**
	 * 值班时间
	 */
	private TimeSlot onDutyTime = new TimeSlot(new Time(), new Time());
	
	public Weekday(int year, int month, int date) {
		this(new Day(year, month, date));
	}
	
	public Weekday(Day day) {
		setDay(day);
	}
	
	public Weekday(int year, int month, int date, TimeSlot onDutyTime) {
		day = new Day(year, month, date);
		setOnDutyTime(onDutyTime);
	}
	
	public Weekday(TimeSlot onDutyTime) {
		this();
		setOnDutyTime(onDutyTime);
	}
	
	public Weekday() {
		day = new Day();
	}
	
	public Day getDay() {
		return day;
	}
	
	public void setDay(Day day) {
		this.day = day;
	}
	
	public TimeSlot getOnDutyTime() {
		return onDutyTime;
	}
	
	public void setOnDutyTime(TimeSlot onDutyTime) {
		this.onDutyTime = onDutyTime;
	}
	
	public String toString() {
		return ToString.toString(this);
	}
}
