/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * qzhanbo@yiji.com 2014-07-06 17:42 创建
 *
 */
package com.cly.common.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.*;
import com.yjf.common.util.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * dubbo 请求日志filter
 * <p/>
 * <h3>Usage Examples</h3>
 * 1.配置所有consumer,请在dubbo:consumer节点上添加filter
 *
 * <pre class="code">
 * {@code
 * <dubbo:consumer filter="requestLogFilter"/>
 * }
 * <p/>
 * 2.配置某个consumer,请在dubbo:reference上添加filter
 * <pre class="code">
 * {@code
 * <dubbo:reference id="demoService"
 * interface="com.alibaba.dubbo.demo.DemoService" version="1.0"  filter="requestLogFilter"/>
 * }
 * <p/>
 * 建议仅在测试环境使用此filter.
 * </pre>
 *
 * @author qzhanbo@yiji.com
 */
public class RequestLogFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);
	
	private static final AtomicLong requestId = new AtomicLong();
	
	public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
		long id = requestId.getAndIncrement();
		long now = System.currentTimeMillis();
		try {
			
			RpcContext context = RpcContext.getContext();
			
			String serviceName = invoker.getInterface().getSimpleName();
			String version = invoker.getUrl().getParameter(Constants.VERSION_KEY);
			String group = invoker.getUrl().getParameter(Constants.GROUP_KEY);
			StringBuilder sn = new StringBuilder(200);
			if (null != group && group.length() > 0) {
				sn.append(group).append("/");
			}
			sn.append(serviceName);
			if (null != version && version.length() > 0) {
				sn.append(":").append(version);
			}
			sn.append("#");
			sn.append(inv.getMethodName());
			sn.append("(");
			Class<?>[] types = inv.getParameterTypes();
			if (types != null && types.length > 0) {
				boolean first = true;
				for (Class<?> type : types) {
					if (first) {
						first = false;
					} else {
						sn.append(",");
					}
					sn.append(type.getSimpleName());
				}
			}
			sn.append(") ");
			Object[] args = inv.getArguments();
			if (args != null && args.length > 0) {
				sn.append(ToString.toString(args));
			}
			sn.append(" 服务器:").append(context.getRemoteHost()).append(":").append(context.getRemotePort());
			String msg = sn.toString();
			logger.info("[DUBBO-{}]请求:{}", id, msg);
		} catch (Throwable t) {
			logger.warn("Exception in ConsumerRequestLogFilter of service(" + invoker + " -> " + inv + ")", t);
		}
		
		Result result = invoker.invoke(inv);
		logger.info("[DUBBO-{}]响应:{} 耗时:{}ms", id, result, System.currentTimeMillis() - now);
		return result;
	}
	
}
