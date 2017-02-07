/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * yanglie@yiji.com 2015-12-21 17:15 创建
 *
 */
package com.cly.common.id;

import com.yjf.common.lang.exception.Exceptions;
import com.yjf.common.log.Logger;
import com.yjf.common.log.LoggerFactory;
import org.joda.time.DateTime;

/**
 * 业务流水id生成器，生成的id为35位
 * <p/>
 * <p>
 * id=系统编码[4位]+节点编码[6位]+保留域[9位]+时间戳[12位]+顺序号[4位] 共35位
 * <p/>
 * *
 * <p>
 * 系统编码：GID在初始化时,会调用hera项目提供的项目注册服务,根据项目名,hera会(生成)并返回一个系统唯一标识
 * </p>
 * <p>
 * 节点编码：由ip加端口的hash值，转换为62进制表示的6位字符
 * </p>
 * <p>
 * 保留域：默认000000000，提供set方法修改
 * </p>
 * <p>
 * 时间戳：yyMMddHHmmss
 * </p>
 * <p/>
 * 顺序号: 保证单位时间内(同一秒)唯一.
 * <p>
 * <h3>Usage Examples</h3>
 * *
 * 
 * <pre class="code">
 * {@code
 * String gid = GID.newID();
 * logger.info("生成唯一的gid:{}", gid);
 * logger.info("此gid的系统码为：{}", GID.getSystemCode(gid);
 * logger.info("此gid的结点码为：{}", GID.getNodeCode(gid);
 * logger.info("此gid的保留域为：{}", GID.getReservedCode(gid);
 * logger.info("此gid的时间戳为：{}", GID.getTimeStamp(gid);
 * logger.info("此gid的序列为：{}", GID.getSequenceNo(gid);
 * ##更改gid字段
 * gid = GID.setSystemCode(gid,"xxxx");
 * gid = GID.setNodeCode(gid, "mm8X2m");
 * gid = GID.setReservedCode(gid, "00000xx00");
 * gid = GID.setTimeStamp(gid, "161228163208");
 * gid = GID.setSequenceNo(gid, "0010");
 * }
 * </pre>
 * </p>
 * <h3>注意:</h3>
 * 时间跳变(比如运维人员把时间向前调整了)可能导致生成重复的id,这个问题基本上没有办法避免,基于时间的id生成器都会遇到这样的问题. </p>
 * @author yanglie@yiji.com
 */
public class GID {
	private static final Logger logger = LoggerFactory.getLogger(GID.class.getName());
	private static final ThreadLocal<String> threadLocal=new ThreadLocal<>();
	private static final int GID_LEN = 35;
    private static final int GID_NODE_CODE_LEN = 6;
	private static String NODE_CODE = null;
	static {
		try {
			CodeGenerator.genSystemCode();
			NODE_CODE = CodeGenerator.padding(CodeGenerator.genNodeCode(), 6, CodeGenerator.PADDING_CHAR);
		} catch (Exception e) {
			logger.error("hera注册失败",e);
		}
	}

	/**
	 * 获取gid
	 * 如果ThreadLocal中存在，则从ThreadLocal中获取，如果不存在，则新生成，并存入ThreadLocal.
	 *
	 * 注意，在线程执行完毕时，调用{@link #clearGIDThreadLocal()} 方法
	 * @return
	 */
	public static String getOrNewGIDFromThreadLocal(){
		String gid=getGIDFromThreadLocal();
		if(gid==null){
			gid=newGID();
			setGIDToThreadLocal(gid);
		}
		return gid;
	}

	public static String getGIDFromThreadLocal(){
		return threadLocal.get();
	}


	public static void setGIDToThreadLocal(String gid){
        threadLocal.set(gid);
    }
	/**
	 * 从线程变量中清理gid
	 */
	public static void clearGIDThreadLocal(){
		threadLocal.remove();
	}


	public static String newGID() {
		StringBuilder gidBuilder = new StringBuilder(35);
		//4
		gidBuilder.append(CodeGenerator.getSystemCode());
		//6
		gidBuilder.append(NODE_CODE);
		//9
		gidBuilder.append(CodeGenerator.getReservedCode());
		DateTime now = getTime();
		//12
		gidBuilder.append(CodeGenerator.getTimeStrCode(now));
		//4
		gidBuilder.append(CodeGenerator.getSequenceNoCode(now));
		String gid = gidBuilder.toString();
		checkGid(gid);
		return gid;
	}

	private static void checkGid(String gid) {
		if (gid == null || gid.length() != 35) {
			throw Exceptions.newRuntimeExceptionWithoutStackTrace("gid格式不正确：" + gid);
		}
	}

	/**
	 * 获取去除掉毫秒的时间
	 * @return
	 */
	private static DateTime getTime() {
		DateTime nowMs = DateTime.now();
		DateTime now = new DateTime(nowMs.getYear(), nowMs.getMonthOfYear(), nowMs.getDayOfMonth(),
			nowMs.getHourOfDay(), nowMs.getMinuteOfHour(), nowMs.getSecondOfMinute());
		return now;
	}

	/**
	 * 通过gid获取系统编码
	 *
	 * @param gid gid
	 * @return 获取系统编码
	 */
	public static String getSystemCode(String gid) {
		checkGid(gid);
		return gid.substring(0, CodeGenerator.SYSTEM_CODE_LEN);
	}

	/**
	 * 修改gid的系统编码
	 * @param gid
	 * @param systemCode
	 * @return 修改后的gid
	 */
	public static String setSystemCode(String gid, String systemCode) {
		checkGid(gid);
		if (systemCode.length() != CodeGenerator.SYSTEM_CODE_LEN) {
			throw Exceptions.newRuntimeExceptionWithoutStackTrace("system code格式不正确：" + systemCode);
		}
		return systemCode + gid.substring(CodeGenerator.SYSTEM_CODE_LEN, GID_LEN);
	}

	/**
	 * 通过gid获取节点编码
	 *
	 * @param gid gid
	 * @return 获取节点编码
	 */
	public static String getNodeCode(String gid) {
		checkGid(gid);
		return gid
			.substring(CodeGenerator.SYSTEM_CODE_LEN, CodeGenerator.SYSTEM_CODE_LEN + GID_NODE_CODE_LEN);
	}

	/**
	 * 修改gid的 节点编码
	 * @param gid
	 * @param nodeCode
	 * @return 修改后的gid
	 */
	public static String setNodeCode(String gid, String nodeCode) {
		checkGid(gid);
		if (nodeCode.length() != GID_NODE_CODE_LEN) {
			throw Exceptions.newRuntimeExceptionWithoutStackTrace("node code格式不正确：" + nodeCode);
		}
		return gid.substring(0, CodeGenerator.SYSTEM_CODE_LEN) + nodeCode
				+ gid.substring(CodeGenerator.SYSTEM_CODE_LEN + GID_NODE_CODE_LEN, GID_LEN);
	}

	/**
	 * 通过gid获取保留域
	 * @param gid
	 * @return
	 */
	public static String getReservedCode(String gid) {
		checkGid(gid);
		return gid.substring(CodeGenerator.SYSTEM_CODE_LEN + GID_NODE_CODE_LEN,
                GID_LEN - CodeGenerator.SEQUENCE_LEN - CodeGenerator.TIMESTAMP_LEN);
	}

	/**
	 * 修改gid的 修改保留域
	 * @param gid
	 * @param reservedCode
	 * @return 修改后的gid
	 */
	public static String setReservedCode(String gid, String reservedCode) {
		checkGid(gid);
		if (reservedCode.length() != CodeGenerator.RESERVED_LEN) {
			throw Exceptions.newRuntimeExceptionWithoutStackTrace("node code格式不正确：" + reservedCode);
		}
		return gid.substring(0, CodeGenerator.SYSTEM_CODE_LEN + GID_NODE_CODE_LEN)
				+ reservedCode
				+ gid.substring(GID_LEN - CodeGenerator.SEQUENCE_LEN - CodeGenerator.TIMESTAMP_LEN,
                GID_LEN);
	}

	public static String getTimeStamp(String gid) {
		checkGid(gid);
		return gid.substring(CodeGenerator.SYSTEM_CODE_LEN + GID_NODE_CODE_LEN + CodeGenerator.RESERVED_LEN,
                GID_LEN - CodeGenerator.SEQUENCE_LEN);
	}

	/**
	 * 修改gid的 修改时间戳
	 * @param gid
	 * @param timeStamp
	 * @return 修改后的gid
	 */
	public static String setTimeStamp(String gid, String timeStamp) {
		checkGid(gid);
		if (timeStamp.length() != CodeGenerator.TIMESTAMP_LEN) {
			throw Exceptions.newRuntimeExceptionWithoutStackTrace("timeStamp格式不正确：" + timeStamp);
		}
		//检查timestamp是否是一个正确的时间格式
		CodeGenerator.parseTime(timeStamp);
		return gid.substring(0, CodeGenerator.SYSTEM_CODE_LEN + GID_NODE_CODE_LEN
								+ CodeGenerator.RESERVED_LEN)
				+ timeStamp + gid.substring(GID_LEN - CodeGenerator.SEQUENCE_LEN, GID_LEN);
	}
	
	public static String getSequenceNo(String gid) {
		checkGid(gid);
		return gid.substring(GID_LEN - CodeGenerator.SEQUENCE_LEN, GID_LEN);
	}
	
	public static String setSequenceNo(String gid, String sequenceNo) {
		checkGid(gid);
		if (sequenceNo.length() != CodeGenerator.SEQUENCE_LEN) {
			throw Exceptions.newRuntimeExceptionWithoutStackTrace("sequenceNo格式不正确：" + sequenceNo);
		}
		return gid.substring(0, GID_LEN - CodeGenerator.SEQUENCE_LEN) + sequenceNo;
	}
	
}
