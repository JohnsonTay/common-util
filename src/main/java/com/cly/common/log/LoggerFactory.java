/*
 * Copyright (c) 2004-2008 QOS.ch
 * All rights reserved.
 * 
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 * 
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 * 
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.cly.common.log;

/**
 * 提供生成变长参数的logger工厂
 * 
 * @Filename LoggerFactory.java
 * 
 * @Description
 * 
 * @Version 1.0
 * 
 * @Author bohr
 * 
 * @Email qzhanbo@yiji.com
 * 
 * @History <li>Author: bohr.qiu</li> <li>Date: 2013-5-3</li> <li>Version: 1.0</li>
 * <li>Content: create</li>
 * 
 */
public final class LoggerFactory {
	
	public static com.cly.common.log.Logger getLogger(String name) {
		org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(name);
		return new LoggerImpl(logger);
	}
	
	public static com.cly.common.log.Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}
}
