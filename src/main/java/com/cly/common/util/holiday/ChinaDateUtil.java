/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zhouyang@yiji.com 2016-06-20 17:25 创建
 *
 */
package com.cly.common.util.holiday;

import com.google.common.collect.Sets;
import com.yjf.common.lang.util.DateUtil;
import com.yjf.common.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouyang@yiji.com
 */
public class ChinaDateUtil {
	final private static long[] lunarInfo = new long[] { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554,
														 0x056a0, 0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250,
														 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970,
														 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570,
														 0x052f2, 0x04970, 0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0,
														 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6,
														 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950,
														 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573,
														 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60,
														 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,
														 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558,
														 0x0b540, 0x0b5a0, 0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0,
														 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5,
														 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60,
														 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552,
														 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0,
														 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0,
														 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6,
														 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0,
														 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
														 0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50,
														 0x1b255, 0x06d20, 0x0ada0 };
	private final static int[] solarTermInfo = { 0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551,
												 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447,
												 419210, 440795, 462224, 483532, 504758 };
	final private static int[] year20 = new int[] { 1, 4, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1 };
	final private static int[] year19 = new int[] { 0, 3, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0 };
	final private static int[] year2000 = new int[] { 0, 3, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1 };
	public final static String[] $nStr1 = new String[] { "", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬",
														 "腊" };
	private final static String[] $Gan = new String[] { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
	private final static String[] $Zhi = new String[] { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
	private final static String[] $Animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗",
															"猪" };

	private final static String[] $SolarTerm = new String[] { "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏",
															  "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分",
															  "寒露", "霜降", "立冬", "小雪", "大雪", "冬至" };
	/**
	 * 国历节日 *表示放假日
	 */
	private final static String[][] sFtv = { { "0101*元旦" },
											 { "0214 情人节" },
											 { "0308 妇女节", "0312 植树节", "0315 消费者权益日" },
											 { "0401 愚人节" },//清明节
											 { "0501*劳动节", "0504 青年节", "0509 郝维节", "0512 护士节" },
											 { "0601 儿童节" },
											 { "0701 建党节 香港回归纪念" },
											 { "0801 建军节", "0808 父亲节", "0816 燕衔泥节" },
											 { "0903 抗日战争胜利纪念日", "0909 毛泽东逝世纪念", "0910 教师节", "0928 孔子诞辰" },
											 { "1001*国庆节", "1002*国庆节", "1003*国庆节", "1004*国庆节", "1005*国庆节", "1006*国庆节",
											   "1007*国庆节", "1006 老人节", "1024 联合国日" },
											 { "1111 光棍节", "1112 孙中山诞辰纪念" },
											 { "1220 澳门回归纪念", "1225 圣诞节", "1226 毛泽东诞辰纪念" } };
	/**
	 * 农历节日 *表示放假日
	 */
	private final static String[][] lFtv = { { "0101*春节、弥勒佛诞", "0102*春节", "0103*春节", "0104*春节", "0105*春节", "0106*春节",
											   "0106 定光佛诞", "0115 元宵节" },
											 { "0208 释迦牟尼佛出家", "0215 释迦牟尼佛涅槃", "0209 海空上师诞", "0219 观世音菩萨诞",
											   "0221 普贤菩萨诞" },
											 { "0316 准提菩萨诞" },
											 { "0404 文殊菩萨诞", "0408 释迦牟尼佛诞",
											   "0415 佛吉祥日——释迦牟尼佛诞生、成道、涅槃三期同一庆(即南传佛教国家的卫塞节)" },
											 { "0505*端午节", "0513 伽蓝菩萨诞" },
											 { "0603 护法韦驮尊天菩萨诞", "0619 观世音菩萨成道——此日放生、念佛，功德殊胜" },
											 { "0707 七夕情人节", "0713 大势至菩萨诞", "0715 中元节", "0724 龙树菩萨诞", "0730 地藏菩萨诞" },
											 { "0815*中秋节", "0822 燃灯佛诞" },
											 { "0909 重阳节", "0919 观世音菩萨出家纪念日", "0930 药师琉璃光如来诞" },
											 { "1005 达摩祖师诞" },
											 { "1107 阿弥陀佛诞" },
											 { "1208 释迦如来成道日，腊八节", "1224 小年", "1229 华严菩萨诞", "0100*除夕" } };
	/**
	 * 某月的第几个星期几
	 */
	private static String[] wFtv = { "0520 母亲节", "0716 合作节", "0730 被奴役国家周" };

	private final static Pattern sFreg = Pattern.compile("^(\\d{2})(\\d{2})([\\s\\*])(.+)$");
	private final static Pattern wFreg = Pattern.compile("^(\\d{2})(\\d)(\\d)([\\s\\*])(.+)$");

	//////
	private static Calendar utcCal = null;

	private static synchronized Calendar getUTCCalendar() {
		if (utcCal == null) {
			utcCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		}
		return utcCal;
	}

	/**
	 * 返回全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数。
	 *
	 * @param y   指定年份
	 * @param m   指定月份
	 * @param d   指定日期
	 * @param h   指定小时
	 * @param min 指定分钟
	 * @param sec 指定秒数
	 * @return 全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数
	 */
	public static synchronized long UTC(int y, int m, int d, int h, int min, int sec) {
		Calendar utcCal = getUTCCalendar();
		utcCal.clear();
		utcCal.set(y, m, d, h, min, sec);
		return utcCal.getTimeInMillis();
	}

	/**
	 * 取 Date 对象中用全球标准时间 (UTC) 表示的日期
	 *
	 * @param date 指定日期
	 * @return UTC 全球标准时间 (UTC) 表示的日期
	 */
	public static synchronized int getUTCDay(Date date) {
		Calendar utcCal = getUTCCalendar();
		utcCal.clear();
		utcCal.setTimeInMillis(date.getTime());
		return utcCal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回今天的节气字符串
	 *
	 * @return 二十四节气字符串, 若不是节气日, 返回空串(例:冬至)
	 */
	public static String getSolarTerm() {
		return getSolarTerm(new Date());
	}

	/**
	 * 返回公历日期的节气字符串
	 *
	 * @return 二十四节气字符串, 若不是节气日, 返回空串(例:冬至)
	 */
	public static String getSolarTerm(Date day) {

		int solarYear = getYear(day);
		int solarMonth = getMonth(day) - 1;
		int solarDay = getDate(day);

		// 二十四节气
		String termString = "";
		if (getSolarTermDay(solarYear, solarMonth * 2) == solarDay) {
			termString = $SolarTerm[solarMonth * 2];
		} else if (getSolarTermDay(solarYear, solarMonth * 2 + 1) == solarDay) {
			termString = $SolarTerm[solarMonth * 2 + 1];
		}
		return termString;
	}

	/**
	 * 返回公历年节气的日期
	 *
	 * @param solarYear 指定公历年份(数字)
	 * @param index     指定节气序号(数字,0从小寒算起)
	 * @return 日期(数字, 所在月份的第几天)
	 */
	private static int getSolarTermDay(int solarYear, int index) {
		return getUTCDay(getSolarTermCalendar(solarYear, index));
	}

	/**
	 * 返回公历年节气的日期
	 *
	 * @param solarYear 指定公历年份(数字)
	 * @param index     指定节气序号(数字,0从小寒算起)
	 * @return 日期(数字, 所在月份的第几天)
	 */
	public static Date getSolarTermCalendar(int solarYear, int index) {
		long l = (long) 31556925974.7 * (solarYear - 1900) + solarTermInfo[index] * 60000L;
		l = l + UTC(1900, 0, 6, 2, 5, 0);
		return new Date(l);
	}

	/**
	 * 传回农历 y年的总天数
	 *
	 * @param y
	 * @return
	 */
	final private static int lYearDays(int y) {
		int i, sum = 348;
		for (i = 0x8000; i > 0x8; i >>= 1) {
			if ((lunarInfo[y - 1900] & i) != 0)
				sum += 1;
		}
		return (sum + leapDays(y));
	}

	private static int toInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}

	public static boolean isHoliday() {
		return isHoliday(DateUtil.now());
	}

	public static boolean isHoliday(Date date) {
		return StringUtils.isNotEmpty(getHoliday(date));
	}

	public static String getHoliday(Date date) {
		return getALLFestival(date, true);
	}

	private static String getALLFestival(Date date, boolean onlyHoliday) {
		Calendar solar = getCalendar(date);

		int sM = getMonth(date);
		int sD = getDate(date);

		long[] cal = calElement(date);
		int lM = ((int) cal[1]);
		int lD = (int) cal[2];

		solar.add(Calendar.DATE, 1);
		cal = calElement(solar.getTime());
		int lM1 = ((int) cal[1]);
		int lD1 = (int) cal[2] - 1;

		StringBuilder festivalName = new StringBuilder();
		Matcher m;
		String[] msFtv = sFtv[sM - 1];
		for (int i = 0; i < msFtv.length; i++) {
			m = sFreg.matcher(msFtv[i]);
			if (m.find()) {
				if (sM == toInt(m.group(1)) && sD == toInt(m.group(2))) {
					if (onlyHoliday) {
						if ("*".equals(m.group(3))) {
							festivalName.append("|").append(m.group(4));
						}
					} else {
						festivalName.append("|").append(m.group(4));
					}
					break;
				}
			}
		}
		String[] mlFtv = lFtv[lM - 1];
		for (int i = 0; i < mlFtv.length; i++) {
			m = sFreg.matcher(mlFtv[i]);
			if (m.find()) {
				if (lM == toInt(m.group(1)) && lD == toInt(m.group(2))) {
					if (onlyHoliday) {
						if ("*".equals(m.group(3))) {
							festivalName.append("|").append(m.group(4));
						}
					} else {
						festivalName.append("|").append(m.group(4));
					}
				} else if (lM1 == toInt(m.group(1)) && lD1 == toInt(m.group(2))) {
					if (onlyHoliday) {
						if ("*".equals(m.group(3))) {
							festivalName.append("|").append(m.group(4));
						}
					} else {
						festivalName.append("|").append(m.group(4));
					}
				}
			}
		}

		// 月周节日
		int w, d;
		for (int i = 0; i < wFtv.length; i++) {
			m = wFreg.matcher(wFtv[i]);
			if (m.find()) {
				if (sM == toInt(m.group(1))) {
					w = toInt(m.group(2));
					d = toInt(m.group(3));
					if (solar.get(Calendar.WEEK_OF_MONTH) == w && solar.get(Calendar.DAY_OF_WEEK) == d) {
						if (onlyHoliday) {
							if ("*".equals(m.group(4))) {
								festivalName.append("|").append(m.group(5));
							}
						} else {
							festivalName.append("|").append(m.group(5));
						}
					}
				}
			}
		}

		if ("清明".equals(getSolarTerm(date))) {
			festivalName.append("|").append("清明节");
		}

		if (festivalName.length() > 0) {
			festivalName.deleteCharAt(0);
		}
		return festivalName.toString();
	}

	public static String getFestival(Date date) {
		return getALLFestival(date, false);
	}

	/**
	 * 传回农历 y年闰月的天数
	 *
	 * @param y
	 * @return
	 */
	final private static int leapDays(int y) {
		if (leapMonth(y) != 0) {
			if ((lunarInfo[y - 1900] & 0x10000) != 0)
				return 30;
			else
				return 29;
		} else
			return 0;
	}

	/**
	 * 传回农历 y年闰哪个月 1-12 , 没闰传回 0
	 *
	 * @param y
	 * @return
	 */
	final private static int leapMonth(int y) {
		return (int) (lunarInfo[y - 1900] & 0xf);
	}

	/**
	 * 传回农历 y年m月的总天数
	 *
	 * @param y
	 * @param m
	 * @return
	 */
	final private static int monthDays(int y, int m) {
		if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
			return 29;
		else
			return 30;
	}

	/**
	 * 传回农历 y年的生肖
	 *
	 * @param y
	 * @return
	 */
	final public static String AnimalsYear(int y) {
		return $Animals[(y - 4) % 12];
	}

	/**
	 * 传入 月日的offset 传回干支,0=甲子
	 *
	 * @param num
	 * @return
	 */
	final private static String cyclicalm(int num) {
		return ($Gan[num % 10] + $Zhi[num % 12]);
	}

	/**
	 * 传入 offset 传回干支, 0=甲子
	 *
	 * @param y
	 * @return
	 */
	final public static String cyclical(int y) {
		int num = y - 1900 + 36;
		return (cyclicalm(num));
	}

	/**
	 * 传出农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
	 *
	 * @param y
	 * @param m
	 * @return
	 */
	final private long[] Lunar(int y, int m) {
		return calElement(y, m, 1);
	}

	/**
	 * 传出y年m月d日对应的农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
	 *
	 * @param day
	 * @return
	 */
	final public static long[] calElement(Date day) {
		Calendar today = getCalendar(day);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);

		return calElement(year, month, date);
	}

	/**
	 * 传出y年m月d日对应的农历.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
	 *
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 */
	final public static long[] calElement(int y, int m, int d) {
		long[] nongDate = new long[7];
		int i = 0, temp = 0, leap = 0;
		Date baseDate = new GregorianCalendar(0 + 1900, 0, 31).getTime();
		Date objDate = new GregorianCalendar(y, m - 1, d).getTime();
		long offset = (objDate.getTime() - baseDate.getTime()) / 86400000L;
		nongDate[5] = offset + 40;
		nongDate[4] = 14;
		for (i = 1900; i < 2050 && offset > 0; i++) {
			temp = lYearDays(i);
			offset -= temp;
			nongDate[4] += 12;
		}
		if (offset < 0) {
			offset += temp;
			i--;
			nongDate[4] -= 12;
		}
		nongDate[0] = i;
		nongDate[3] = i - 1864;
		leap = leapMonth(i); // 闰哪个月
		nongDate[6] = 0;
		for (i = 1; i < 13 && offset > 0; i++) {
			// 闰月
			if (leap > 0 && i == (leap + 1) && nongDate[6] == 0) {
				--i;
				nongDate[6] = 1;
				temp = leapDays((int) nongDate[0]);
			} else {
				temp = monthDays((int) nongDate[0], i);
			}
			// 解除闰月
			if (nongDate[6] == 1 && i == (leap + 1))
				nongDate[6] = 0;
			offset -= temp;
			if (nongDate[6] == 0)
				nongDate[4]++;
		}
		if (offset == 0 && leap > 0 && i == leap + 1) {
			if (nongDate[6] == 1) {
				nongDate[6] = 0;
			} else {
				nongDate[6] = 1;
				--i;
				--nongDate[4];
			}
		}
		if (offset < 0) {
			offset += temp;
			--i;
			--nongDate[4];
		}
		nongDate[1] = i;
		nongDate[2] = offset + 1;
		return nongDate;
	}

	public final static String getChinaDate(int day) {
		String a = "";
		if (day == 10)
			return "初十";
		if (day == 20)
			return "二十";
		if (day == 30)
			return "三十";
		int two = (int) ((day) / 10);
		if (two == 0)
			a = "初";
		if (two == 1)
			a = "十";
		if (two == 2)
			a = "廿";
		if (two == 3)
			a = "卅";
		int one = (int) (day % 10);
		switch (one) {
			case 1:
				a += "一";
				break;
			case 2:
				a += "二";
				break;
			case 3:
				a += "三";
				break;
			case 4:
				a += "四";
				break;
			case 5:
				a += "五";
				break;
			case 6:
				a += "六";
				break;
			case 7:
				a += "七";
				break;
			case 8:
				a += "八";
				break;
			case 9:
				a += "九";
				break;
		}
		return a;
	}

	public static String today() {
		return oneDay(new Date());
	}

	public static String oneDay(Date day) {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		today.setTime(day);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);

		return oneDay(year, month, date);
	}

	public static String oneDay(int year, int month, int day) {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		today.set(year, month - 1, day);
		long[] l = calElement(year, month, day);
		StringBuffer sToday = new StringBuffer();
		try {
			synchronized (sdf) {
				//As the JavaDoc states, DateFormats are inherently unsafe for multithreaded use.
				sToday.append(sdf.format(today.getTime()));
			}
			sToday.append(" 农历");
			sToday.append(cyclical(year));
			sToday.append('(');
			sToday.append(AnimalsYear(year));
			sToday.append(")年");
			sToday.append($nStr1[(int) l[1]]);
			sToday.append("月");
			sToday.append(getChinaDate((int) (l[2])));
			return sToday.toString();
		} finally {
			sToday = null;
		}
	}

	public static String getChinaMonth(Date day) {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		today.setTime(day);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		return getChinaMonth(year, month, date);
	}

	public static String getChinaMonth(int year, int month, int day) {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		today.set(year, month - 1, day);
		long[] l = calElement(year, month, day);
		StringBuffer sToday = new StringBuffer();
		try {
			sToday.append($nStr1[(int) l[1]]);
			sToday.append("月");
			sToday.append(getChinaDate((int) (l[2])));
			return sToday.toString();
		} finally {
			sToday = null;
		}
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日 EEEEE");

	public static int getYear(Date date) {
		return getCalendar(date).get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		return getCalendar(date).get(Calendar.MONTH) + 1;
	}

	public static int getDate(Date date) {
		return getCalendar(date).get(Calendar.DATE);
	}

	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * 返回给定时间内包含多少个节日
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<String> getHolidays(Date start, Date end) {
		String endDate = DateUtil.shortDate(end);
		Calendar calendar = getCalendar(start);
		Set<String> holidays = Sets.newHashSet();
		int day = 0;
		do{
			calendar.add(Calendar.DATE,day++);
			holidays.add(getHoliday(calendar.getTime()));
		}while (DateUtil.shortDate(calendar.getTime()).compareTo(endDate) <=0);
		return holidays;
	}
}
