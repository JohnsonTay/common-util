/*
 * 修订记录:
 * qzhanbo@yiji.com 2013-5-25 10:43 创建
 * qzhanbo@yiji.com 2013-2-24 10:43 修改IDEA提示不需要使用StringBuilder
 */
package com.cly.common.id;

/**
 * CID(compressed id)生成长度(12<=len<=14)的唯一id
 * <p>
 * 此id会调用cs服务注册，获取唯一(参考{@link ID})serverid,并生成唯一的时间序列，最后编码为字符串
 * <p>
 * id=encode(serverid)+encode(time)+checksum
 * @author Bohr.Qiu <qzhanbo@yiji.com>
 */
public class CID {
	
	/**
	 * 生成长度(12<=len<=14)的唯一id
	 */
	public static String newID() {
		return OID.newID();
	}
	
	/**
	 * 检查id是否符合格式
	 */
	public static boolean check(String id) {
		return true;
	}
}
