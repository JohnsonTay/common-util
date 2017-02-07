/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * husheng@yiji.com 上午9:56:42 创建
 */
package com.cly.common.util;

import com.cly.common.util.holiday.ChinaDateUtil;
import com.cly.common.util.holiday.WeekdayUtil;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * 中国大陆节假日工具类。
 * <p>
 * 节假日包括：
 * <ul>
 * <li>周六、周日
 * <li>国家法定假日及调休
 *
 * @author husheng@yiji.com
 * @author zhouyang@yiji.com
 * 将部分功能移入到 WeekdayUtil 中
 *
 */
public class HolidayUtil extends WeekdayUtil {
	
	/**
	 * 判断传入日期是否是节假日（包括法定假日、调休、周六和周日）
	 * 
	 * @param date
	 * @return
	 * @since 2015年
	 * @throws IllegalArgumentException 日期不能为空，日期必须不能早于2015年
	 */
	@Deprecated
	public static boolean isHoliday(@NotNull Date date) {
		Assert.notNull(date, "日期不能为空");
		return WeekdayUtil.isHoliday(date);
	}
	
	/**
	 * 判断传入日期是否是法定节假日（包括法定假日、调休。不包括普通周六和周日）
	 * 
	 * @param date
	 * @return
	 * @since 2015年
	 * @throws IllegalArgumentException 日期不能为空，日期必须不能早于2015年
	 */
	@Deprecated
	public static boolean isLegalHoliday(Date date) {
		Assert.notNull(date, "日期不能为空");

		if(ChinaDateUtil.isHoliday(date)){
			return true;
		}
		String dateStr = DateUtil.simpleFormat(date);
		return WeekdayUtil.$HOLIDAYS.containsKey(dateStr);
	}
	
	/**
	 * 获取下一个工作日
	 * @param date
	 * @return
	 * @throws IllegalArgumentException 日期不能为空，日期必须不能早于2015年
	 * @see WeekdayUtil#getNextWeekday(Date)
	 */
	@Deprecated
	public static Date getNextWorkingDate(Date date) {
		Assert.notNull(date, "日期不能为空");
		date = WeekdayUtil.getNextWeekday(date);
		try {
			date = DateUtil.shortstring2Date( DateUtil.shortDate(date));
		} catch (ParseException e) {
		}
		return date;
	}
}
