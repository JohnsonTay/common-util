/*
 * www.wmjia.com Inc.
 * Copyright (c) 2015 All Rights Reserved.
 */

/*
 * 修订记录：
 * zjzj_547@outlook.com 2015年10月20日 下午9:14:27 创建
 */
package com.cly.common.doc.guide;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;


/**
 *
 * @author zjzj_547@outlook.com
 *
 */

/**
 * 1.需要继承AbstractView
 * 2.去掉下面注释
 * 3.Controller里使用：
 *      retrun new ModelAndView(new PdfView(), modelMap);
 * @return
 */
public abstract class AbstractPdfView /** extends AbstractView */{
//	public AbstractPdfView() {
//        setContentType("application/pdf");
//    }
//
//    @Override
//    protected boolean generatesDownloadContent() {
//        return true;
//    }
//
//    @Override
//    protected final void renderMergedOutputModel(Map<String, Object> model,
//            HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        ByteArrayOutputStream baos = createTemporaryOutputStream();
//        Document document = newDocument();
//        PdfWriter writer = newWriter(document, baos);
//        prepareWriter(model, writer, request);
//        buildPdfMetadata(model, document, request);
//        document.open();
//
//        buildPdfDocument(model, document, writer, request, response);
//
//        document.close();
//        writeToResponse(response, baos);
//    }

    protected Document newDocument() {
        return new Document(PageSize.A4, 100, 100, 50, 50);
    }

    protected PdfWriter newWriter(Document document, ByteArrayOutputStream baos)
            throws DocumentException {
        return PdfWriter.getInstance(document, baos);
    }

    protected void prepareWriter(Map<String, Object> model, PdfWriter writer,
            HttpServletRequest request) throws DocumentException {

        writer.setViewerPreferences(getViewerPreferences());
    }

    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }

    protected void buildPdfMetadata(Map<String, Object> model,
            Document document, HttpServletRequest request) {
    }

    protected abstract void buildPdfDocument(Map<String, Object> model,
            Document document, PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
}
