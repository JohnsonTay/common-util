/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved
 */

/*
 * 修订记录:
 * qzhanbo@yiji.com 2015-09-07 18:16 创建
 *
 */
package com.cly.common.dubbo;

import com.yjf.common.lang.exception.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 用于关闭dubbo
 * @author qiubo@yiji.com
 */
public class DubboShutdownHook implements Runnable {
	private static volatile boolean run = false;
	private static final Logger logger = LoggerFactory.getLogger(DubboShutdownHook.class);
		
	
	@Override
	public void run() {
		if (run) {
			return;
		}
		logger.info("关闭dubbo");
		run = true;
		try {
			final Class protocolConfig = Class.forName("com.alibaba.dubbo.config.ProtocolConfig");
			final Method method = protocolConfig.getMethod("destroyAll");
			try {
				method.invoke(protocolConfig);
			} catch (Exception e) {
				throw Exceptions.newRuntimeExceptionWithoutStackTrace(e);
			}
		} catch (Throwable e) {
			//ignore
		}
	}
}
