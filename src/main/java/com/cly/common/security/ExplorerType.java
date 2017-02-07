/**
 * www.yiji.com Inc.
 * Copyright (c) 2012 All Rights Reserved.
 */
package com.cly.common.security;

import com.yjf.common.lang.enums.Messageable;
import com.yjf.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Filename: ExplorerType.java
 * 
 * @Description:
 * 
 * @Version: 1.0
 * 
 * @Author: peigen
 * 
 * @Email: peigen@yiji.com
 * 
 * 
 * @History:<br> <li>Author: peigen</li> <li>Date: 2011-7-28</li> <li>Version:
 * 1.0</li> <li>Content: create</li>
 * 
 */
public enum ExplorerType implements Messageable {
	
	/** 未知异常 */
	IE("IE", "该死浏览器"),
	
	/** 火狐浏览器 */
	FIREFOX("FIREFOX", "火狐浏览器"),
	
	/** OPERA浏览器 */
	OPERA("OPERA", "OPERA浏览器"),
	
	/** 谷歌浏览器 */
	CHROME("CHROME", "谷歌浏览器"),
	
	/** HTTPCLIENT */
	HTTP_CLIENT("HTTP_CLIENT", "HTTP_CLIENT"),
	
	/** 其他 */
	OTHER("OTHER", "其他");
	
	/** 枚举值 */
	private final String code;
	
	/** 枚举描述 */
	private final String message;
	
	/**
	 * 构造一个<code>ExplorerType</code>枚举对象
	 * 
	 * @param code
	 * @param message
	 */
	private ExplorerType(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String code() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String message() {
		return message;
	}
	
	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return ExplorerType
	 */
	public static ExplorerType getByCode(String code) {
		for (ExplorerType _enum : values()) {
			if (StringUtils.equals(_enum.getCode(), code)) {
				return _enum;
			}
		}
		return null;
	}
	
	/**
	 * 获取全部枚举
	 * 
	 * @return List<ExplorerType>
	 */
	public List<ExplorerType> getAllEnum() {
		List<ExplorerType> list = new ArrayList<ExplorerType>();
		for (ExplorerType _enum : values()) {
			list.add(_enum);
		}
		return list;
	}
	
	/**
	 * 获取全部枚举值
	 * 
	 * @return List<String>
	 */
	public List<String> getAllEnumCode() {
		List<String> list = new ArrayList<String>();
		for (ExplorerType _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}
}
