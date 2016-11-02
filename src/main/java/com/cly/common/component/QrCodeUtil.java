/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * xuyin@yiji.com 2015-11-26 20:26 创建
 *
 */
package com.cly.common.component;


import com.cly.common.util.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信扫码生成二维码
 *
 * @author xuyin@yiji.com
 */
public class QrCodeUtil {
	private static Logger logger = LoggerFactory.getLogger(QrCodeUtil.class);
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;
	
	/**
	 * 生成二维码图片 不存储 直接以流的形式输出到页面
	 * @param codeUrl 二维码来源地址
	 * @param response 返回响应
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void encodeQrcode(String codeUrl, HttpServletResponse response) {
		if (StringUtils.isBlank(codeUrl)) {
			return;
		}
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
		//设置字符集编码类型
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(codeUrl, BarcodeFormat.QR_CODE, 290, 290, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			//输出二维码图片流
			try {
				ImageIO.write(image, "png", response.getOutputStream());
			} catch (IOException e) {
				logger.info("输出二维码图片流出现异常，异常信息：{}", e);
			}
		} catch (WriterException e1) {
			logger.error("生成二维码图片出现异常，异常信息是：{}", e1);
		}
	}
	
	/**
	 * 生成二维码内容<br>
	 */
	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
}
