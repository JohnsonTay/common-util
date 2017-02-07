/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * zhouyang@yiji.com 2016-05-05 11:46 创建
 *
 */
package com.cly.common.security;

/**
 * Created by mhuhan@yiji.com on 2016/4/13.
 */
public class IndexUtil {

	private static final String SALT = "169d1f7a";
	
	/**
	 * 根据名文生成摘要信息
	 */
	public static String getDigest(String sourceText) {
		return generateDigest(sourceText);
	}
	
	/**
	 * 生成摘要信息
	 */
	private static String generateDigest(String sourceText) {
		return MD5Util.encoderByMd5(sourceText + SALT);
	}
	
}
