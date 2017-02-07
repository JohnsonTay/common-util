/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * qiubo@yiji.com 2016-11-24 00:04 创建
 */
package com.cly.common.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.yjf.common.service.OrderBase;
import org.slf4j.MDC;

import static com.yjf.common.service.Order.GID_KEY;

@Activate(group = { Constants.PROVIDER }, order = Integer.MIN_VALUE)
public class MDCFilter implements Filter {
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
		Object[] args = inv.getArguments();
		boolean mdcEnable = false;
		if (args != null) {
			for (Object arg : args) {
				if (arg instanceof OrderBase) {
					mdcEnable = true;
					setGid((OrderBase) arg);
					break;
				}
			}
		}
		Result result;
		try {
			result = invoker.invoke(inv);
		} finally {
			if (mdcEnable) {
				MDC.remove(GID_KEY);
			}
		}
		return result;
	}
	
	private void setGid(OrderBase arg) {
		String gid = arg.getGid();
		if (gid == null) {
			gid = "";
		}
		MDC.put(GID_KEY, gid);
	}
}
