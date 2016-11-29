package com.cly.common.lang.exception;

import com.cly.common.lang.RunException;

/**
 * 系统的基础异常。
 *
 */
public class SystemException extends RunException {
	
	private static final long serialVersionUID = 7769982372460790248L;
	
	/**
	 * 构造一个 SystemException 。
	 */
	public SystemException() {
		super();
	}
	
	protected SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	/**
	 * 构造一个 SystemException 。
	 * @param message 详细消息。
	 * @param cause 原因/相关的异常。
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 构造一个 SystemException 。
	 * @param message 详细消息。
	 */
	public SystemException(String message) {
		super(message);
	}
	
	/**
	 * 构造一个 SystemException 。
	 * @param cause 原因/相关的异常。
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}
	
}
