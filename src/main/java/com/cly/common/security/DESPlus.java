package com.cly.common.security;

import com.google.common.base.Charsets;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.yjf.common.util.ShutdownHooks;
import com.yjf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.Security;

/**
 * 提供线程安全的 DESPlus
 * 
 * @Filename DESPlus.java
 * 
 * @Description
 * 
 * @Version 1.0
 * 
 * @Author peigen
 * 
 * @Email peigen@yiji.com
 * 
 * @History <li>Author: peigen</li> <li>Date: 2012-9-20</li> <li>Version: 1.0</li>
 * <li>Content: create</li>
 * @modify bohr.qiu
 */
@SuppressWarnings("restriction")
public class DESPlus {
	
	private static final Logger logger = LoggerFactory.getLogger(DESPlus.class.getName());
	
	static {
		try {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
		} catch (Exception e) {
			logger.error("", e);
		}
		
		ShutdownHooks.addShutdownHook(new Runnable() {
			@Override
			public void run() {
				threadLocal.remove();
			}
		}, "DESPlusShutdownHook");
	}
	private static final ThreadLocal<LoadingCache<String, DESPlus>> threadLocal = new ThreadLocal<LoadingCache<String, DESPlus>>() {
		@Override
		protected LoadingCache<String, DESPlus> initialValue() {
			return CacheBuilder.newBuilder().maximumSize(100).build(new CacheLoader<String, DESPlus>() {
				@Override
				public DESPlus load(final String key) throws Exception {
					DESPlus des = null;
					try {
						des = new DESPlus(key);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					return des;
					
				}
			});
		}
	};
	private static String strDefaultKey = "y1j1f0-2012";
	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;
	
	/**
	 * 静态构造器，封装异常
	 * 
	 * @return
	 */
	private static DESPlus newDESPlus(final String strKey) {
		if (StringUtils.isEmpty(strKey)) {
			throw new IllegalArgumentException("key must be not null.");
		}
		return threadLocal.get().getUnchecked(strKey);
	}
	
	/**
	 * @param strIn 待解密字符串
	 * @return
	 */
	public static String safeDecrypt(String strIn) {
		return safeDecrypt(strDefaultKey, strIn);
	}
	
	/**
	 * @param key 解密密钥
	 * @param strIn 待解密字符串
	 * @return
	 */
	public static String safeDecrypt(String key, String strIn) {
		if (StringUtils.isEmpty(strIn)) {
			return strIn;
		}
		try {
			return newDESPlus(key).decrypt(strIn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param key 解密密钥
	 * @return
	 */
	public static byte[] safeDecrypt(String key, byte[] arrB) {
		if (arrB == null) {
			return arrB;
		}
		try {
			return newDESPlus(key).decrypt(arrB);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param arrB 待解密
	 */
	public static byte[] safeDecrypt(byte[] arrB) {
		return safeDecrypt(strDefaultKey, arrB);
		
	}
	
	/**
	 * 加密方法
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] safeEncrypt(String key, byte[] arrB) {
		try {
			return newDESPlus(key).encrypt(arrB);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] safeEncrypt(byte[] arrB) {
		try {
			return newDESPlus(strDefaultKey).encrypt(arrB);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 加密密方法
	 * 
	 * @param key
	 * @param strIn
	 * @return
	 */
	public static String safeEncrypt(String key, String strIn) {
		try {
			return newDESPlus(key).encrypt(strIn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 加密方法
	 * 
	 * @param strIn
	 * @return
	 */
	public static String safeEncrypt(String strIn) {
		try {
			return newDESPlus(strDefaultKey).encrypt(strIn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 默认构造方法，使用默认密钥
	 * 
	 * @throws Exception
	 */
	public DESPlus() throws Exception {
		this(strDefaultKey);
	}
	
	/**
	 * 指定密钥构造方法
	 * 
	 * @param strKey 指定的密钥
	 * @throws Exception
	 * 
	 */
	public DESPlus(String strKey) throws Exception {
		
		Key key = getKey(strKey.getBytes(Charsets.UTF_8));
		
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}
	
	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB 需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception 本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}
	
	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn 需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception 本方法不处理任何异常，所有异常全部抛出
	 * @author abc
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes(Charsets.UTF_8);
		int iLen = arrB.length;
		
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2, Charsets.UTF_8);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	/**
	 * 加密字节数组
	 * 
	 * @param arrB 需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}
	
	/**
	 * 加密字符串 建议使用静态加密方法
	 * 
	 * @param strIn 需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes(Charsets.UTF_8)));
	}
	
	/**
	 * 解密字节数组 建议直接使用静态解密方法{@link DESPlus#safeDecrypt(byte[])}
	 * 
	 * @param arrB 需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}
	
	/**
	 * 解密字符串
	 * 
	 * @param strIn 需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)), Charsets.UTF_8);
	}
	
	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp 构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws Exception
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		
		return key;
	}
}
