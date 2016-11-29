package com.cly.common.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 运行时的不检查异常。
 * 
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 * 
 */
public class RunException extends RuntimeException {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 3926442618855461965L;
	
	public RunException() {
		super();
	}
	
	public RunException(String msg) {
		super(msg);
	}
	
	public RunException(StringBuilder msg) {
		super(msg == null ? null : msg.toString());
	}
	
	public RunException(StringBuffer msg) {
		super(msg == null ? null : msg.toString());
	}
	
	public RunException(Throwable cause) {
		super(cause);
	}
	
	public RunException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public RunException(StringBuilder msg, Throwable cause) {
		super(msg == null ? null : msg.toString(), cause);
	}
	
	public RunException(StringBuffer msg, Throwable cause) {
		super(msg == null ? null : msg.toString(), cause);
	}
	
	protected RunException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	@Override
	public String getMessage() {
		return ThrowableUtils.buildMessage(super.getMessage(), getCause());
	}
	
	@Override
	public void printStackTrace(PrintStream ps) {
		if (getCause() == null) {
			super.printStackTrace(ps);
		} else {
			ps.println(this);
			ps.print("原因: ");
			getCause().printStackTrace(ps);
		}
	}
	
	@Override
	public void printStackTrace(PrintWriter pw) {
		if (getCause() == null) {
			super.printStackTrace(pw);
		} else {
			pw.println(this);
			pw.print("原因: ");
			getCause().printStackTrace(pw);
		}
	}
}
