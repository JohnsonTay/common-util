package com.cly.common.lang.exception;

/**
 * 所需资源未找到的异常的基异常。
 * 
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 * 
 */
public class ResourceNotFoundException extends SystemException {
	
	private static final long serialVersionUID = -941298082874015076L;
	
	/** 资源名 */
	protected final Object name;
	
	/**
	 * 基础构造方法，仅供子类使用。
	 */
	protected ResourceNotFoundException() {
		this(null, null, null);
	}
	
	protected ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.name = null;
	}
	
	/**
	 * 构造一个 ResourceNotFoundException 。
	 * 
	 * @param name 资源名。
	 * @param message 详细消息。
	 */
	public ResourceNotFoundException(Object name, String message) {
		this(name, message, null);
	}
	
	/**
	 * 构造一个 ResourceNotFoundException 。
	 * 
	 * @param name 资源名。
	 */
	public ResourceNotFoundException(Object name) {
		this(name, null, null);
	}
	
	/**
	 * 构造一个 ResourceNotFoundException 。
	 * 
	 * @param name 资源名。
	 * @param message 详细消息。
	 * @param cause 原因/相关的异常。
	 */
	public ResourceNotFoundException(Object name, String message, Throwable cause) {
		super(message(name) + message, cause);
		this.name = name;
	}
	
	/**
	 * 构造一个 ResourceNotFoundException 。
	 * 
	 * @param message 详细消息。
	 * @param cause 原因/相关的异常。
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		this(null, message, cause);
	}
	
	/**
	 * 构造一个 ResourceNotFoundException 。
	 * 
	 * @param message 详细消息。
	 */
	public ResourceNotFoundException(String message) {
		this(null, message, null);
	}
	
	/**
	 * 构造一个 ResourceNotFoundException 。
	 * 
	 * @param cause 原因/相关的异常。
	 */
	public ResourceNotFoundException(Throwable cause) {
		this(null, null, cause);
	}
	
	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
	
	private static String message(Object name) {
		if (name == null) {
			return "";
		}
		return "资源'" + name + "'没有找到。";
	}
	
	/**
	 * 得到资源名。
	 * 
	 * @return 资源名。
	 */
	public Object getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		String message = getLocalizedMessage();
		return getClass().getName() + " [name=" + this.name + "]" + (message == null ? "" : message);
	}
	
}
