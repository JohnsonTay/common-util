/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zhouyang@yiji.com 2016-06-20 17:32 创建
 *
 */
package com.cly.common.util.holiday;

import com.google.common.collect.Lists;
import com.yjf.common.collection.CollectionUtils;
import com.yjf.common.lang.util.DateUtil;
import com.yjf.common.util.ResourceUtils;
import com.yjf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

/**
 * 工作日工具类
 *
 * @author zhouyang@yiji.com
 */
public class WeekdayUtil {
	private static final Logger logger = LoggerFactory.getLogger(WeekdayUtil.class);
	
	/**
	 * 节假日集合
	 */
	protected static final Map<String, Holiday> $HOLIDAYS = new TreeMap<String, Holiday>();
	/**
	 * 调休上班集合
	 */
	protected static final Map<String, Weekday> $WEEKDAY = new TreeMap<String, Weekday>();
	/**
	 * 工作时间
	 */
	protected static final TimeSlot $DUTY_TIME = new TimeSlot(new Time(9, 00), new Time(18, 0));
	
	private static Date startDate;
	private static Date endDate;
	
	static {
		loadHolidayDataConfigs();
		
		
//		////////////2017//////////////////
//		//元旦
//		//___放假
//		$HOLIDAYS.put("2017-01-01", new Holiday(2017, 1, 1));
//		$HOLIDAYS.put("2017-01-02", new Holiday(2017, 1, 2));
//
//		//春节
//		//___放假
//		$HOLIDAYS.put("2017-01-27", new Holiday(2017, 1, 27));
//		$HOLIDAYS.put("2017-01-28", new Holiday(2017, 1, 28));
//		$HOLIDAYS.put("2017-01-29", new Holiday(2017, 1, 29));
//		$HOLIDAYS.put("2017-01-30", new Holiday(2017, 1, 30));
//		$HOLIDAYS.put("2017-01-31", new Holiday(2017, 1, 31));
//		$HOLIDAYS.put("2017-02-01", new Holiday(2017, 2, 1));
//		$HOLIDAYS.put("2017-02-02", new Holiday(2017, 2, 2));
//		//___调休上班
//		$WEEKDAY.put("2017-01-22", new Weekday(2017, 1, 22, $DUTY_TIME));
//		$WEEKDAY.put("2017-02-04", new Weekday(2017, 2, 4, $DUTY_TIME));
//
//		//清明节
//		//___放假
//		$HOLIDAYS.put("2017-04-02", new Holiday(2017, 4, 2));
//		$HOLIDAYS.put("2017-04-03", new Holiday(2017, 4, 3));
//		$HOLIDAYS.put("2017-04-04", new Holiday(2017, 4, 4));
//		//___调休上班
//		$HOLIDAYS.put("2017-04-01", new Holiday(2017, 4, 1));
//
//		//劳动节
//		//___放假
//		$HOLIDAYS.put("2017-04-29", new Holiday(2017, 4, 29));
//		$HOLIDAYS.put("2017-04-30", new Holiday(2017, 4, 30));
//		$HOLIDAYS.put("2017-05-01", new Holiday(2017, 5, 1));
//
//		//端午节
//		//___放假
//		$HOLIDAYS.put("2017-05-28", new Holiday(2017, 5, 28));
//		$HOLIDAYS.put("2017-05-29", new Holiday(2017, 5, 29));
//		$HOLIDAYS.put("2017-05-30", new Holiday(2017, 5, 30));
//		//___调休上班
//		$WEEKDAY.put("2017-05-27", new Weekday(2017, 5, 27, $DUTY_TIME));
//
//		//中秋节
//		//国庆节
//		//___放假
//		$HOLIDAYS.put("2017-10-01", new Holiday(2017, 10, 1));
//		$HOLIDAYS.put("2017-10-02", new Holiday(2017, 10, 2));
//		$HOLIDAYS.put("2017-10-03", new Holiday(2017, 10, 3));
//		$HOLIDAYS.put("2017-10-04", new Holiday(2017, 10, 4));
//		$HOLIDAYS.put("2017-10-05", new Holiday(2017, 10, 5));
//		$HOLIDAYS.put("2017-10-06", new Holiday(2017, 10, 6));
//		$HOLIDAYS.put("2017-10-07", new Holiday(2017, 10, 7));
//		$HOLIDAYS.put("2017-10-08", new Holiday(2017, 10, 8));
//
//		//___调休上班
//		$WEEKDAY.put("2017-09-30", new Weekday(2017, 9, 30, $DUTY_TIME));
		
	}
	
	/**
	 * 读取节假日配置文件
	 */
	private static void loadHolidayDataConfigs(){
		InputStream is = ResourceUtils.getResourceAsStream("com/yjf/common/lang/util/holiday/holiday.data",WeekdayUtil.class);
		InputStreamReader bsr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(bsr);
		String value = null;
		
		String startDateStr=DateUtil.dtSimpleFormat(DateUtil.now());
		String endDateStr=startDateStr;
		
		try {
			while((value = br.readLine()) !=null){
				value = StringUtils.trim(value);
				if(StringUtils.isNotBlank(value)){
					//注释
					if(value.startsWith("#")){
						continue;
					}
					
					try {
						if(value.startsWith("H:")){
							//节假日
							value = value.substring(2);
							$HOLIDAYS.put(value, new Holiday(new Day(DateUtil.strToDtSimpleFormat(value))));
						}else if(value.startsWith("W:")){
							//调休
							value = value.substring(2);
							$WEEKDAY.put(value, new Weekday(new Day(DateUtil.strToDtSimpleFormat(value))));
						}
					} catch (Exception e) {
						logger.warn("解析节假日日期[{}]出错,请修正配置文件[holiday.data]!",value);
					}
					
				}
			}
		} catch (Exception e) {
		}finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}
		
		List<String> dates = Lists.newArrayList($HOLIDAYS.keySet());
		dates.addAll($WEEKDAY.keySet());
		
		if(dates.size()>0) {
			startDateStr = StringUtils.min(startDateStr, StringUtils.min(CollectionUtils.toArray(dates, String.class)));
			endDateStr = StringUtils.max(CollectionUtils.toArray(dates, String.class));
		}
		try {
			startDate = DateUtil.parseDateNoTime(startDateStr, DateUtil.dtSimple);
			endDate = DateUtil.parseDateNoTime(endDateStr, DateUtil.dtSimple);
		} catch (Exception e) {
		}
	}

	public static boolean isTodayHoliday() {
		return isHoliday(DateUtil.now());
	}

	public static boolean isHoliday(Date date) {
		return !isWeekday(date);
	}

	/**
	 * 今天是否工作日
	 *
	 * @return
	 */
	public static boolean isTodayWeekday() {
		return isTodayWeekday($WEEKDAY, $HOLIDAYS);
	}
	
	/**
	 * 判断给定时间是否是工作日
	 *
	 * @param weekdayMap 工作时间(周六、周日)
	 * @param holidayMap 假日时间(周一至周五)
	 * @return
	 */
	public static boolean isTodayWeekday(Map<String, Weekday> weekdayMap,
										 Map<String, Holiday> holidayMap) {
		return isWeekday(new Date());
	}
	
	/**
	 * 判断给定日期是否工作日（2015年）
	 *
	 * @param date （2015年）
	 * @return
	 */
	public static boolean isWeekday(Date date) {
		return isWeekday(date, $WEEKDAY, $HOLIDAYS);
	}
	
	/**
	 * 判断给定时间是否是工作日
	 *
	 * @param date
	 * @param weekdayMap 工作时间(周六、周日)
	 * @param holidayMap 假日时间(周一至周五)
	 * @return
	 */
	public static boolean isWeekday(Date date, Map<String, Weekday> weekdayMap,
									Map<String, Holiday> holidayMap) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//如果给定时间不在限定的时间内
		if (!DateUtil.isDateBetween(date, startDate, endDate)) {
			boolean isHoliday = ChinaDateUtil.isHoliday(date);
			if (isHoliday) {
				return !isHoliday;
			} else {
				int flag = calculateHoliday(calendar);
				if (flag == 1) {
					return false;
				} else if (flag == 0) {
					return true;
				}
			}
		}

		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
			|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			//如果是周末周日，判断是否为调休上班时间
			return weekdayMap != null && weekdayMap.containsKey(DateUtil.dtSimpleFormat(date));
		} else {
			//如果不是周末周日，判断是否节日
			if (holidayMap != null) {
				if (holidayMap.containsKey(DateUtil.dtSimpleFormat(date))) {
					return false;
				} else {
					if (!DateUtil.isDateBetween(date, startDate, endDate)) {
						return !ChinaDateUtil.isHoliday(date);
					}
				}
			}
			return true;
		}
	}

	/**
	 * @param calendar 不是节日
	 * @return 1:假日；0:工作日;-1未知
	 */
	private static int calculateHoliday(Calendar calendar) {
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			//如果今天是星期一  且星期二是非春节和国庆的假日，那么今天应该是倒休,否则正常上班
			Calendar tomorrow = ((Calendar) calendar.clone());
			tomorrow.add(Calendar.DATE, 1);
			if (ChinaDateUtil.isHoliday(tomorrow.getTime())) {
				if (ChinaDateUtil.getHoliday(tomorrow.getTime()).contains("除夕") || DateUtil
						.shortDate(tomorrow.getTime())
						.endsWith("1001")) {
					return 0;
				} else {
					return 1;
				}
			}
			//昨天是节假日，今天该倒休
			Calendar yesterday = ((Calendar) calendar.clone());
			yesterday.add(Calendar.DATE, -1);
			if (ChinaDateUtil.isHoliday(yesterday.getTime())) {
				return 1;
			}

		} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			//如果今天是星期五  且星期四是非春节和国庆的假日，那么今天应该是倒休,否则正常上班
			Calendar thursday = ((Calendar) calendar.clone());
			thursday.add(Calendar.DATE, -1);
			if (ChinaDateUtil.isHoliday(thursday.getTime())) {
				if (!ChinaDateUtil.getHoliday(thursday.getTime()).contains("除夕")
					&& !ChinaDateUtil.getHoliday(thursday.getTime()).contains("春节")
					&& !DateUtil.shortDate(thursday.getTime()).endsWith("1001")) {
					return 1;
				}
			}

			//明天是小假，且今天不是八月十四，那今天倒休
			Calendar tomorrow = ((Calendar) calendar.clone());
			tomorrow.add(Calendar.DATE, 1);
			if (ChinaDateUtil.isHoliday(tomorrow.getTime())) {
				if (ChinaDateUtil.getHoliday(tomorrow.getTime()).contains("除夕") || DateUtil
						.shortDate(tomorrow.getTime())
						.endsWith("1001")) {
					return 0;
				} else if (ChinaDateUtil.getChinaMonth(tomorrow.getTime()).equals("八月十四")) {
					return 1;
				}
			}
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			//如果今天是星期六  下周二是非春节和国庆的假日，那么今天应该是倒休上班，接着放3天假
			Calendar tuesday = ((Calendar) calendar.clone());
			tuesday.add(Calendar.DATE, 3);
			if (ChinaDateUtil.isHoliday(tuesday.getTime())) {
				String holiday = ChinaDateUtil.getHoliday(tuesday.getTime());
				if (holiday.contains("除夕") || DateUtil.shortDate(tuesday.getTime()).endsWith("1001")) {
					return 1;
				} else {
					return 0;
				}
			}

			//星期一到星期二是大假，那么就得倒休上班
			for (int i = 1; i <= 2; i++) {
				Calendar day = ((Calendar) calendar.clone());
				day.add(Calendar.DATE, i);
				if (ChinaDateUtil.isHoliday(day.getTime())) {
					if (ChinaDateUtil.getHoliday(day.getTime()).contains("除夕") || DateUtil
							.shortDate(day.getTime())
							.endsWith("1001")) {
						return 0;
					}
				}
			}

			//上周是大假，今天倒休上班
			for (int i = -10; i <= -3; i++) {
				Calendar day = ((Calendar) calendar.clone());
				day.add(Calendar.DATE, i);
				if (ChinaDateUtil.isHoliday(day.getTime())) {
					if (ChinaDateUtil.getHoliday(day.getTime()).contains("除夕") || DateUtil
							.shortDate(day.getTime())
							.endsWith("1001")) {
						return 0;
					}
				}
			}
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			//如果今天是星期天  上周四是节假日，那么今天该倒休上班(不管是春节还是国庆)
			Calendar thursday = ((Calendar) calendar.clone());
			thursday.add(Calendar.DATE, -3);
			if (ChinaDateUtil.isHoliday(thursday.getTime())) {
				//上周1到现在有几个接口
				Calendar monday = ((Calendar) calendar.clone());
				monday.add(Calendar.DATE, -7);
				//如果国庆和中秋重合
				if(ChinaDateUtil.getHolidays(monday.getTime(),calendar.getTime()).size()>1){
					return 1;
				}
				return 0;
			}

			//下周有大假，那么就得倒休上班
			for (int i = 1; i <= 5; i++) {
				Calendar day = ((Calendar) calendar.clone());
				day.add(Calendar.DATE, i);
				if (ChinaDateUtil.isHoliday(day.getTime())) {
					if (ChinaDateUtil.getHoliday(day.getTime()).contains("除夕") || DateUtil
							.shortDate(day.getTime())
							.endsWith("1001")) {
						return 0;
					}
				}
			}

			//上周是大假，今天倒休上班
			for (int i = -8; i <= -7; i++) {
				Calendar day = ((Calendar) calendar.clone());
				day.add(Calendar.DATE, i);
				if (ChinaDateUtil.isHoliday(day.getTime())) {
					if (ChinaDateUtil.getHoliday(day.getTime()).contains("除夕") || DateUtil
							.shortDate(day.getTime())
							.endsWith("1001")) {
						return 0;
					}
				}
			}

		}

		//如果后天是10月1日或者正月初一，那么今天应该是倒休上班，接着放7天假
		return -1;
	}

	/**
	 * 当前时间是否工作时间
	 *
	 * @return
	 */

	public static boolean isNowDutyTime() {
		return isNowDutyTime($DUTY_TIME);
	}
	
	/**
	 * 当前时间是否工作时间
	 *
	 * @param timeSlot 工作时间区间
	 * @return
	 */
	public static boolean isNowDutyTime(TimeSlot timeSlot) {
		return isDutyTime(new Date(), timeSlot);
	}
	
	/**
	 * 判断给定时间是否工作时间
	 *
	 * @param date
	 * @return
	 */
	public static boolean isDutyTime(Date date) {
		return isDutyTime(date, $DUTY_TIME);
	}
	
	/**
	 * 判断给定时间是否工作时间
	 *
	 * @param date
	 * @param workTime 工作时间区间
	 * @return
	 */
	public static boolean isDutyTime(Date date, TimeSlot workTime) {
		return isDutyTime(date, $WEEKDAY, $HOLIDAYS, workTime);
	}
	
	/**
	 * 判断给定时间是否工作时间
	 *
	 * @param date
	 * @param weekdayMap 工作时间(周六、周日)
	 * @param holidayMap 假日时间(周一至周五)
	 * @param workTime
	 * @return
	 */
	public static boolean isDutyTime(Date date, Map<String, Weekday> weekdayMap,
									 Map<String, Holiday> holidayMap, TimeSlot workTime) {
		//判断当日是否工作日
		if (isWeekday(date, weekdayMap, holidayMap)) {
			if (workTime == null) {
				workTime = $DUTY_TIME;
			}
			return workTime.isTimeBetween(date);
		}
		return false;
	}

	public static Date getWeekdayTime(){
		return getWeekdayTime(DateUtil.now());
	}
	public static Date getWeekdayTime(Date date) {
		return getWeekdayTime(date,$WEEKDAY, $HOLIDAYS,$DUTY_TIME);
	}
	private static Date getWeekdayTime(Date date, Map<String, Weekday> weekdayMap,
									   Map<String, Holiday> holidayMap, TimeSlot workTime) {
		if (workTime == null) {
			workTime = $DUTY_TIME;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		//非工作时间，调整到工作时间
		if (!isDutyTime(calendar.getTime(), weekdayMap, holidayMap, workTime)) {
			Time now = new Time(calendar.getTime());
			//未到开始时间
			if (workTime.getStart().compareTo(now) > 0) {
				calendar.set(Calendar.HOUR_OF_DAY, workTime.getStart().getHour());
				calendar.set(Calendar.MINUTE, workTime.getStart().getMinute());
				calendar.clear(Calendar.SECOND);
			} else if (workTime.getEnd().compareTo(now) < 0) {
				//超过结束时间,调整到下一日
				calendar.add(Calendar.DATE, 1);
				calendar.set(Calendar.HOUR_OF_DAY, workTime.getStart().getHour());
				calendar.set(Calendar.MINUTE, workTime.getStart().getMinute());
				calendar.clear(Calendar.SECOND);
			}
		}

		//非工作日，调整到工作日
		if (!isWeekday(calendar.getTime(), weekdayMap, holidayMap)) {
			calendar.add(Calendar.DATE, 1);
			while (!isWeekday(calendar.getTime(), weekdayMap, holidayMap)) {
				calendar.add(Calendar.DATE, 1);
			}
		}

		return calendar.getTime();
	}

	/**
	 * 获取下一个工作日
	 *
	 * @return
	 */
	public static Date getNextWeekday() {
		return getNextWeekday(new Date());
	}
	
	/**
	 * 获取下一个工作日
	 *
	 * @param date
	 * @return
	 */
	public static Date getNextWeekday(Date date) {
		return getNextWeekday(date, $DUTY_TIME);
	}
	
	public static Date getNextWeekday(Date date, TimeSlot workTime) {
		return getNextWeekday(date, $WEEKDAY, $HOLIDAYS, workTime);
	}
	
	public static Date getNextWeekday(Date date, Map<String, Weekday> weekdayMap,
									  Map<String, Holiday> holidayMap) {
		return getNextWeekday(date, weekdayMap, holidayMap, $DUTY_TIME);
	}
	
	public static Date getNextWeekday(Date date, Map<String, Weekday> weekdayMap,
									  Map<String, Holiday> holidayMap, TimeSlot workTime) {
		if (workTime == null) {
			workTime = $DUTY_TIME;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, workTime.getStart().getHour());
		calendar.set(Calendar.MINUTE, workTime.getStart().getMinute());
		calendar.clear(Calendar.SECOND);


		return getWeekdayTime(calendar.getTime(), weekdayMap, holidayMap,workTime );
	}

	/**
	 * 返回 两个日期间的有效工作日，包含起始日期和结束日期
	 *
	 * @param fromDate 起始日期
	 * @param toDate   结束日期
	 * @return
	 */
	public static int getWeekays(@NotNull Date fromDate, @NotNull Date toDate) {
		Assert.notNull(fromDate, "起始日期不能为空");
		Assert.notNull(toDate, "终止日期不能为空");
		Assert.isTrue(fromDate.getTime() < toDate.getTime(), "起始日期必须小于终止日期");

		//将日期初始化为00:00:00
		try {
			toDate = DateUtil.shortstring2Date(DateUtil.shortDate(toDate));
		} catch (ParseException e) {
		}

		int count = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		while (calendar.getTime().getTime() < toDate.getTime()) {
			if (isWeekday(calendar.getTime())) {
				count++;
			}
			calendar.add(Calendar.DATE, 1);
		}
		if (isWeekday(toDate)) {
			count++;
		}
		return count;
	}
}
