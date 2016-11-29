package com.cly.common.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 检查异常的包装器，所有的方法都委托调用被包装的方法。
 * 
 * <p>
 * 由于该包装器自身也是 {@link RuntimeException} 的子类，所以也是一个不检查异常。
 * 
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 * 
 */
public class RuntimeExceptionWrapper extends RuntimeException {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 3926111618855461965L;
	
	/**
	 * 使用需要被包装的 {@link Throwable} 构造一个 RuntimeExceptionWrapper 。
	 * 
	 * @param cause 需要被包装的 {@link Throwable} 。
	 */
	public RuntimeExceptionWrapper(Throwable cause) {
		super(cause);
	}
	
	@Override
	public String getMessage() {
		return getCause().getMessage();
	}
	
	@Override
	public void printStackTrace(PrintStream ps) {
		getCause().printStackTrace(ps);
	}
	
	@Override
	public void printStackTrace(PrintWriter pw) {
		getCause().printStackTrace(pw);
	}
	
	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
	
	@Override
	public Throwable getCause() {
		return super.getCause();
	}
	
	@Override
	public String getLocalizedMessage() {
		return getCause().getLocalizedMessage();
	}
	
	@Override
	public StackTraceElement[] getStackTrace() {
		return getCause().getStackTrace();
	}
	
	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return getCause().initCause(cause);
	}
	
	@Override
	public void printStackTrace() {
		getCause().printStackTrace();
	}
	
	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		getCause().setStackTrace(stackTrace);
	}
	
	@Override
	public String toString() {
		return getCause().toString();
	}
	
}
