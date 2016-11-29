package com.cly.common.lang;

import com.cly.common.lang.exception.ResourceNotFoundException;

/**
 * 如果要获取某个对象，而持有实例中没有这个对象时抛出该异常。
 * 
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 * 
 */
public class NoSuchObjectException extends ResourceNotFoundException {
	
	/** 版本号 */
	private static final long serialVersionUID = -7508422277727094661L;
	
	/**
	 * 构造一个 NoSuchObjectException 。
	 * 
	 * @param name 资源名。
	 * @param message 详细消息。
	 * @param cause 原因/相关的异常。
	 */
	public NoSuchObjectException(Object name, String message, Throwable cause) {
		super(name, message(name) + message, cause);
	}
	
	/**
	 * 构造一个 NoSuchObjectException 。
	 * 
	 * @param name 资源名。
	 * @param message 详细消息。
	 */
	public NoSuchObjectException(Object name, String message) {
		super(name, message(name) + message, null);
	}
	
	/**
	 * 构造一个 NoSuchObjectException 。
	 * 
	 * @param name 资源名。
	 */
	public NoSuchObjectException(Object name) {
		super(name, message(name), null);
	}
	
	/**
	 * 构造一个 NoSuchObjectException 。
	 * 
	 * @param name 资源名。
	 * @param cause 原因/相关的异常。
	 */
	public NoSuchObjectException(String message, Throwable cause) {
		super(null, message, cause);
	}
	
	/**
	 * 构造一个 NoSuchObjectException 。
	 * 
	 * @param message 详细消息。
	 */
	public NoSuchObjectException(String message) {
		super(null, message, null);
	}
	
	private static String message(Object name) {
		if (name == null) {
			return "";
		}
		return "对象'" + name + "'没有找到。";
	}
	
}
