/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zhouyang@yiji.com 2016-06-20 17:18 创建
 *
 */
package com.cly.common.util.holiday;

import java.util.Date;

/**
 * @author zhouyang@yiji.com
 */
public class TimeSlot {
	/**
	 * 开始时间
	 */
	private Time	start;
	
	/**
	 * 结束时间
	 */
	private Time	end;
	
	public TimeSlot() {
		
	}
	
	public TimeSlot(Time start, Time end) {
		setStart(start);
		setEnd(end);
	}
	
	/**
	 * 开始时间
	 *
	 * @return
	 */
	public Time getStart() {
		return start;
	}
	
	/**
	 * 开始时间
	 *
	 * @param start
	 */
	public void setStart(Time start) {
		this.start = start;
	}
	
	/**
	 * 结束时间
	 *
	 * @return
	 */
	public Time getEnd() {
		return end;
	}
	
	/**
	 * 结束时间
	 *
	 * @param end
	 */
	public void setEnd(Time end) {
		this.end = end;
	}
	
	public String toString() {
		return start + "~" + end;
	}
	
	/**
	 * 判断给定时间片在当前
	 * @param time
	 * @return
	 */
	public boolean isTimeBetween(Time time) {
		return time.compareTo(start) > 0 && time.compareTo(end) < 0;
	}
	
	/**
	 * 判断给定时间片在当前
	 * @param time
	 * @return
	 */
	public boolean isTimeBetween(Date time) {
		return isTimeBetween(new Time(time));
	}
}
