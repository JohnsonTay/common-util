/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zhouyang@yiji.com 2016-06-20 16:59 创建
 *
 */
package com.cly.common.util.holiday;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间实体类(时:分:秒)
 * @author zhouyang@yiji.com
 */
public class Time implements Comparable<Time> {
	/**
	 * 小时
	 */
	private int	hour;
	/**
	 * 分钟
	 */
	private int	minute;
	/**
	 * 秒
	 */
	private int	second	= 0;
	
	public Time() {
		Calendar calendar = Calendar.getInstance();
		setHour(calendar.get(Calendar.HOUR_OF_DAY));
		setMinute(calendar.get(Calendar.MINUTE));
		setSecond(calendar.get(Calendar.SECOND));
	}
	
	public Time(int hour, int minute) {
		setHour(hour);
		setMinute(minute);
	}
	
	public Time(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setHour(calendar.get(Calendar.HOUR_OF_DAY));
		setMinute(calendar.get(Calendar.MINUTE));
		setSecond(calendar.get(Calendar.SECOND));
	}
	
	/**
	 * 小时
	 *
	 * @return
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * 小时
	 *
	 * @param hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	/**
	 * 分钟
	 *
	 * @return
	 */
	public int getMinute() {
		return minute;
	}
	
	/**
	 * 分钟
	 *
	 * @param minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public int getSecond() {
		return second;
	}
	
	public void setSecond(int second) {
		this.second = second;
	}
	

	@Override
	public int compareTo(Time time) {
		if (time == null) {
			time = new Time();
		}
		if (hour != time.hour) {
			return hour - time.hour;
		}
		if (minute != time.minute) {
			return minute - time.minute;
		}
		return second - time.second;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		Time time = (Time) o;
		
		if (hour != time.hour) {
			return false;
		}
		if (minute != time.minute) {
			return false;
		}
		return second == time.second;
		
	}
	
	@Override
	public int hashCode() {
		int result = hour;
		result = 31 * result + minute;
		result = 31 * result + second;
		return result;
	}
	
	public String toString() {
		return (hour < 10 ? "0" : "")+ hour + ":" + (minute < 10 ? "0" : "") + minute + ":"
				+ (second < 10 ? "0" : "") + second;
	}
}
