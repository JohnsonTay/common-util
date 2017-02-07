/*
 * www.yiji.com Inc.
 * Copyright (c) 2015 All Rights Reserved
 */

/*
 * 修订记录:
 * qzhanbo@yiji.com 2015-09-09 13:21 创建
 *
 */
package com.cly.common.dubbo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * 在spring context关闭时关闭dubbo
 * @author qiubo@yiji.com
 */
public class DubboShutdownApplicationListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        new DubboShutdownHook().run();
    }
}
