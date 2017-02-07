/**
 * www.yiji.com Inc.
 * Copyright (c) 2012 All Rights Reserved.
 */

/*
 * 修订记录:
 * peigen@yiji.com 2012-5-31 创建
 *
 * qzhanbo@yiji.com 2014-02-24 增加统一流水好支持.
 *
 */

package com.cly.common.service;

import java.io.Serializable;

/**
 *
 * 接口入参对象接口
 * @author peigen
 */
public interface Order extends Serializable {
	String GID_KEY = "gid";

	/**
	 * 校验入参
	 */
	public void check();

	/**
	 * 获取统一流水号
	 * @return 统一流水号
	 */
	String getGid();

	/**
	 * 设置统一流水号
	 * @param gid 统一流水号
	 */
	void setGid(String gid);
}
