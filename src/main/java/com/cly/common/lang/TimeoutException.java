package com.cly.common.lang;

import com.cly.common.lang.exception.SystemException;

/**
 * 在操作超时时，抛出该异常。
 * 
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 * 
 */
public class TimeoutException extends SystemException {
	
	/** 版本号 */
	private static final long serialVersionUID = 5419788348258892009L;
	
	public TimeoutException() {
		
	}
	
	public TimeoutException(String msg) {
		super(msg);
	}
	
}
