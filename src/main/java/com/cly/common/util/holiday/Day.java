/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zhouyang@yiji.com 2016-06-20 16:58 创建
 *
 */
package com.cly.common.util.holiday;

import com.yjf.common.lang.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期实体类
 *
 * @author zhouyang@yiji.com
 */
public class Day implements Comparable<Day> {
	/**
	 * 年
	 */
	private int year;
	/**
	 * 月
	 */
	private int month;
	/**
	 * 日
	 */
	private int date;
	
	public Day() {
		this(DateUtil.now());
	}
	
	public Day(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		setYear(calendar.get(Calendar.YEAR));
		setMonth(calendar.get(Calendar.MONTH) + 1);
		setDate(calendar.get(Calendar.DATE));
	}
	
	public Day(int year, int month, int date) {
		setYear(year);
		setMonth(month);
		setDate(date);
	}
	
	/**
	 * 年
	 *
	 * @return
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * 年
	 *
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * 月
	 *
	 * @return
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * 月
	 *
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	
	/**
	 * 日
	 *
	 * @return
	 */
	public int getDate() {
		return date;
	}
	
	/**
	 * 日
	 *
	 * @param date
	 */
	public void setDate(int date) {
		this.date = date;
	}
	
	@Override
	public int compareTo(Day day) {
		if (day == null) {
			day = new Day();
		}
		if (year != day.year) {
			return year - day.year;
		}
		if (month != day.month) {
			return month - day.month;
		}
		return date - day.date;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		Day day = (Day) o;
		
		if (year != day.year) {
			return false;
		}
		if (month != day.month) {
			return false;
		}
		return date == day.date;
		
	}
	
	@Override
	public int hashCode() {
		int result = year;
		result = 31 * result + month;
		result = 31 * result + date;
		return result;
	}
	
	public String toString() {
		return year + "-" + (month < 10 ? "0" : "") + month + "-" + (date < 10 ? "0" : "") + date;
	}
}
