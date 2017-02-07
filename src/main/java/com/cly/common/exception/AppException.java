/**
 * www.yiji.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.cly.common.exception;

/**
 * @Filename AppException.java
 * @Description
 * @Version 1.0
 * @Author bohr.qiu
 * @Email qzhanbo@yiji.com
 * @History <li>Author: bohr.qiu</li> <li>Date: 2013-10-31</li> <li>Version: 1.0
 * </li> <li>Content: create</li>
 */
public class AppException extends RuntimeException {
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	public AppException() {
		super();
	}
	
	public AppException(String message) {
		super(message);
	}
	
	public AppException(Throwable cause) {
		super(cause);
	}
	
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
