/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * yanglie@yiji.com 2016-01-13 10:13 创建
 *
 */
package com.cly.common.id;

/*
 * 修订记录:
 * qzhanbo@yiji.com 2013-5-25 10:43 创建
 * qzhanbo@yiji.com 2013-2-24 10:43 修改IDEA提示不需要使用StringBuilder
 */

import com.yjf.common.lang.exception.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * OID(order id)生成长度20位的唯一内部订单id
 * <p>
 * 此id会调用hera服务注册，获取唯一(参考{@link GID})system code,并生成唯一的时间序列，最后编码为字符串
 * <p>
 * id=encode(time) + encode(system code)+ encode(node code) +checksum
 * @author yanglie <yanglie@yiji.com>
 */
public class OID {
	private static final Logger logger = LoggerFactory.getLogger(OID.class);
	
	private final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
											'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
											'u', 'v', 'w', 'x', 'y', 'z' };
	private final static int RADIX = digits.length;
	/**
	 * 每毫秒内生成id最大数
	 */
	private static final int COUNT_IN_MILL_SECOND = 100;
	
	private static AtomicLong lastTime = new AtomicLong(0);
	
	private static final String DEFAULT_RESERVED = "00";
	
	private static final int TIME_LENGTH = 11;
	private static final int RESERVED_LEN = 2;
	private static final int TOTAL_LENGTH = CodeGenerator.SYSTEM_CODE_LEN + CodeGenerator.NODE_CODE_LEN + TIME_LENGTH
											+ RESERVED_LEN;
	
	static {
		try {
			CodeGenerator.genSystemCode();
			CodeGenerator.genNodeCode();
		} catch (Exception e) {
			logger.error("ID注册启动失败", e);
		}
	}
	
	/**
	 * 生成长度(20位)的唯一id
	 */
	public static String newID() {
		return newID(DEFAULT_RESERVED);
	}
	
	/**
	 * 生成长度(20位)的唯一id，并自定义保留域，限2位
	 */
	public static String newID(String reservedCode) {
		if (reservedCode.length() != RESERVED_LEN) {
			throw Exceptions.newRuntimeExceptionWithoutStackTrace("保留域长度应该为：" + RESERVED_LEN);
		}
		StringBuilder sb = new StringBuilder(20);
		Date now = new Date();
		long time = createTime(now.getTime());
		//4位
		sb.append(CodeGenerator.getSystemCode());
		//3位
		sb.append(CodeGenerator.getNodeCode());
		//11位
		sb.append(CodeGenerator.padding(toString(time), TIME_LENGTH, CodeGenerator.PADDING_CHAR));
		//2位
		sb.append(reservedCode);
		return sb.toString();
	}
	
	public static String getReservedCode(String oid) {
		return oid.substring(TOTAL_LENGTH - RESERVED_LEN, TOTAL_LENGTH);
	}
	
	/**
	 * 根据当前时间(毫秒)获取到唯一的时间标识 g
	 * @param currentTimeMillis 当前毫秒时间
	 * @return 时间标识
	 */
	public static long createTime(long currentTimeMillis) {
		long timeMillis = currentTimeMillis * COUNT_IN_MILL_SECOND;
		while (true) {
			long last = lastTime.get();
			if (timeMillis > last) {
				if (lastTime.compareAndSet(last, timeMillis)) {
					break;
				}
			} else {
				if (lastTime.compareAndSet(last, last + 1)) {
					timeMillis = last + 1;
					break;
				}
			}
		}
		return timeMillis;
	}
	
	public static String toString(long i) {
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
	
}
