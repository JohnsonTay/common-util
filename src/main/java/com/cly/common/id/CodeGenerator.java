/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * yanglie@yiji.com 2015-12-22 20:32 创建
 *
 */
package com.cly.common.id;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cly.common.env.Env;
import com.cly.common.exception.Exceptions;
import com.cly.common.ip.IPUtil;
import com.cly.common.log.Logger;
import com.cly.common.log.LoggerFactory;
import com.cly.common.util.ShutdownHooks;
import com.cly.common.util.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yanglie@yiji.com
 */
public class CodeGenerator {
	private static final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);
	
	public static final int REGISTER_TRY_TIME = 3;
	/**
	 * 系统编码长度
	 */
	public static final int SYSTEM_CODE_LEN = 4;
	
	/**
	 * 节点编码长度
	 */
	public static final int NODE_CODE_LEN = 3;
	/**
	 * 时间戳
	 */
	public static final int TIMESTAMP_LEN = 12;
	/**
	 * 保留域长度
	 */
	public static final int RESERVED_LEN = 9;
	/**
	 * 顺序号长度
	 */
	public static final int SEQUENCE_LEN = 4;
	
	/**
	 * id长度
	 */
	public static final int GID_LEN = SYSTEM_CODE_LEN + NODE_CODE_LEN + RESERVED_LEN + TIMESTAMP_LEN + SEQUENCE_LEN;
	
	public static final char PADDING_CHAR = '0';
	
	private final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
											'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
											'u', 'v', 'w', 'x', 'y', 'z', };
	private final static int RADIX = digits.length;
	
	/**
	 * 时间缓存
	 */
	private static volatile long lastTimeCache = 0l;
	
	/**
	 * 时间字符串缓存
	 */
	private static volatile String lastTimeStrCache = null;
	
	/**
	 * 12位的时间格式
	 */
	private static final String TIME_FORMAT = "yyMMddHHmmss";
	
	private static final String DEFAULT_RESERVED = "000000000";
	
	/**
	 * 每秒内生成id最大数
	 */
	private static final int COUNT_IN_SECOND = 10000000;
	
	/**
	 * 从hera服务器注册服务时的安全码
	 */
	private static final String SECURITY_KEY = "yjf-gid-hera";
	
	private static String heraRegisterUrl = getHeraRegisterUrl();
	
	private static AtomicLong lastTimeWithCount = new AtomicLong(0);
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(TIME_FORMAT);
	private static String heraUrl;
	
	private static String systemCode;
	private static String nodeCode;
	static {
		ShutdownHooks.addShutdownHook(new Runnable() {
			@Override
			public void run() {
				CodeGenerator.unregisterNode();
			}
		}, "取消hera 服务节点注册");
	}
	
	public static void setHeraRegisterUrl(String registerUrl) {
		heraRegisterUrl = registerUrl;
	}
	
	/**
	 * 获取当前应用系统编码,长度4位
	 */
	public static String getSystemCode() {
		return genSystemCode();
	}
	
	/**
	 * 获取当前应用节点编码,长度3位
	 */
	public static String getNodeCode() {
		return genNodeCode();
	}
	
	public static String getReservedCode() {
		return DEFAULT_RESERVED;
	}
	
	public static String getTimeStrCode(DateTime now) {
		String timeStr;
		if (lastTimeCache == now.getMillis()) {
			timeStr = lastTimeStrCache;
		} else {
			timeStr = now.toString(dateTimeFormatter);
			lastTimeStrCache = timeStr;
			lastTimeCache = now.getMillis();
		}
		return timeStr;
	}
	
	public static String getSequenceNoCode(DateTime now) {
		long timeWithCount = (now.getMillis() / 1000) * COUNT_IN_SECOND;
		while (true) {
			long last = lastTimeWithCount.get();
			if (timeWithCount > last) {
				if (lastTimeWithCount.compareAndSet(last, timeWithCount)) {
					break;
				}
			} else {
				if (lastTimeWithCount.compareAndSet(last, last + 1)) {
					timeWithCount = last + 1;
					break;
				}
			}
		}
		int seqNo = (int) (timeWithCount % COUNT_IN_SECOND);
		String seqNoCode = padding(toString(seqNo), SEQUENCE_LEN, PADDING_CHAR);
		return seqNoCode;
	}
	
	public static String genSystemCode() {
		if (systemCode != null) {
			return systemCode;
		} else {
			synchronized (CodeGenerator.class) {
				if (systemCode == null) {
					systemCode = getSystemCodeFormHera();
				}
			}
		}
		return systemCode;
	}
	
	public static String genNodeCode() {
		if (nodeCode != null) {
			return nodeCode;
		} else {
			synchronized (CodeGenerator.class) {
				if (nodeCode == null) {
					nodeCode = getNodeCodeFormHera();
				}
			}
		}
		return nodeCode;
	}
	
	public static DateTime parseTime(String timeStamp) {
		return DateTime.parse(timeStamp, dateTimeFormatter);
	}
	
	private static String getSystemCodeFormHera() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String result = null;
		try {
			result = getDataFromHera("project/getSystemCode.json", dataMap);
			if (result == null || result.length() != SYSTEM_CODE_LEN) {
				throw Exceptions.newRuntimeException("从hera读取SystemCode长度不对,请联系hera开发者,systemCode=" + result);
			}
			logger.info("请求hera成功, 获取到system code:{}", result);
		} catch (RuntimeException e) {
			if (isTestEnv()) {
				result = "0000";
				logger.info("测试环境获取系统码失败,使用默认系统码:{}", result);
			} else {
				logger.error("获取系统码失败,系统关闭", e);
				System.exit(0);
			}
		}
		return result;
	}
	
	private static String getNodeCodeFormHera() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String ip = IPUtil.getFirstNoLoopbackIPV4Address();
		String port = Env.getPort();
		String ipAndPort = ip + ":" + port;
		dataMap.put("ipAndPort", ipAndPort);
		String result = null;
		try {
			result = getDataFromHera("project/getNodeCode.json", dataMap);
			if (result == null || result.length() != NODE_CODE_LEN) {
				throw Exceptions.newRuntimeException("从hera读取NodeCode长度不对,请联系hera开发者,NodeCode=" + result);
			}
			logger.info("请求hera成功, 获取到node code:{}", result);
		} catch (RuntimeException e) {
			if (isTestEnv()) {
				result = padding(Integer.toString(new Random().nextInt(100)), NODE_CODE_LEN, PADDING_CHAR);
				logger.info("测试环境获取节点码失败,使用随机节点码:{}", result);
			} else {
				logger.error("获取节点码失败,系统关闭", e);
				System.exit(0);
			}
		}
		return result;
	}
	
	public static void unregisterNode() {
		Map<String, Object> dataMap = new HashMap<>();
		String ip = IPUtil.getFirstNoLoopbackIPV4Address();
		String port = Env.getPort();
		String ipAndPort = ip + ":" + port;
		dataMap.put("ipAndPort", ipAndPort);
		String result = getDataFromHera("project/delNodeCode.json", dataMap);
		logger.info("ID服务取消注册返回结果:{}", result);
	}
	
	private static String getDataFromHera(String uri, Map<String, Object> dataMap) {
		String projectCode = System.getProperty("yiji.appName");
		if (StringUtils.isEmpty(projectCode)) {
			projectCode = System.getProperty("sys.name");
		}
		if (StringUtils.isEmpty(projectCode)) {
			throw Exceptions.newRuntimeException("系统变量中没有发现配置系统名:yiji.appName或sys.name");
		}
		int retry = REGISTER_TRY_TIME;
		if (isTestEnv()) {
			retry = 1;
		}
		for (int i = 0; i < retry; i++) {
			try {
				dataMap.put("projectCode", projectCode);
				dataMap
					.put(DigestUtil.SIGN_KEY, DigestUtil.digest(dataMap, SECURITY_KEY, DigestUtil.DigestALGEnum.MD5));
				String url = heraRegisterUrl + uri;
				logger.info("注册hera，请求数据,url={}", url);
				int timeout = 5000;
				if (isTestEnv()) {
					timeout = 2000;
				}
				String data = HttpRequest.post(url, dataMap, false).readTimeout(timeout).connectTimeout(timeout).body();
				logger.info("注册hera,返回数据:{}", data);
				JSONObject resultObject = JSON.parseObject(data);
				if (resultObject.getString("success").equals("true")) {
					String result = resultObject.getString("singleResult");
					logger.info("请求hera成功, 获取到数据:{}", result);
					return result;
				} else {
					throw new AppException("hera注册服务返回失败:" + resultObject.getString("message"));
				}
			} catch (Exception e) {
				logger.error("请求hera注册失败:{}, 将进行第{}次重试", e.getMessage(), i);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				logger.warn(e.getMessage());
			}
		}
		throw new AppException("从hera获取返回失败");
	}
	
	/**
	 * 填充字符串
	 *
	 * @param str 待填充字符串
	 * @param len 填充后的位数
	 * @param padding 填充字符
	 */
	public static String padding(String str, int len, char padding) {
		if (str.length() < len) {
			StringBuilder sb = new StringBuilder(len);
			int toPadLen = len - str.length();
			for (int i = 1; i <= toPadLen; i++) {
				sb.append(padding);
			}
			sb.append(str);
			return sb.toString();
		} else {
			return str;
		}
	}
	
	private static String toString(long i) {
		char[] buf = new char[65];
		int charPos = 64;
		boolean negative = (i < 0);
		
		if (!negative) {
			i = -i;
		}
		
		while (i <= -RADIX) {
			buf[charPos--] = digits[(int) (-(i % RADIX))];
			i = i / RADIX;
		}
		buf[charPos] = digits[(int) (-i)];
		
		if (negative) {
			buf[--charPos] = '-';
		}
		return new String(buf, charPos, (65 - charPos));
	}
	
	public static String getHeraRegisterUrl() {
		if (heraUrl != null) {
			return heraUrl;
		}
		String url = System.getProperty("hera.register.url", null);
		if (url == null) {
			//线上和预发布环境设置为线上的hera
			url = "http://snet.yiji:47688/";
			if (!isTestEnv()) {
				url = "http://hera.yiji.com:8310/";
				logger.info(Env.getEnv() + "环境，使用线上的hera地址:" + url);
			} else {
				//线下设置为线下的hera
				logger.info("线下环境，使用线下的hera地址:" + url);
			}
		} else {
			logger.info("使用系统参数hera.register.url配置的hera地址:" + url);
		}
		heraUrl = url;
		return url;
	}
	
	private static boolean isTestEnv() {
		return !Env.isOnline() && !Env.isPre();
	}
}
