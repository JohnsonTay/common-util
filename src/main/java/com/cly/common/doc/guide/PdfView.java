/*
 * www.wmjia.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * zjzj_547@outlook.com 2016/11/2 15:44 创建
 *
 */
package com.cly.common.doc.guide;

import com.cly.common.doc.PdfDocfUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author zjzj_547@outlook.com
 */
public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String offlineInfo = (String) model.get("pdfOfflineInfo");
        OutputStream outs = response.getOutputStream(); // 获取输出流
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String("线下支付信息单.pdf".getBytes("gb2312"), "ISO8859-1"));
        writer = PdfWriter.getInstance(document, outs);
        Rectangle rect = new Rectangle(36, 54, 559, 788);
        rect.setBorderColor(BaseColor.BLACK);
        writer.setBoxSize("art", rect);
        document.open();
        document.newPage();
        document.add(PdfDocfUtil.genTable(offlineInfo));
        document.close();
    }

}
