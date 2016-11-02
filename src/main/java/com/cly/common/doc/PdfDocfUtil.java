/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * linying@yiji.com 2016/11/1 21:06 创建
 *
 */
package com.cly.common.doc;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author linying@yiji.com
 */
public class PdfDocfUtil {
    private static Logger logger = LoggerFactory.getLogger(PdfDocfUtil.class);

    private static Font pdf12BFont;// 设置字体大小
    private static Font pdf10NFont;// 设置字体大小
    private static Font pdf8NFont;// 设置字体大小
    private static Font pdf10BRFont;// 设置字体大小
    private static Font threefont;// 设置字体大小
    private static Font englishfont;// 设置字体大小
    private static BaseFont bfChinese;
    private static BaseFont bfEnglish;
    /** 单位缩进字符串*/
    private static String SPACE = "   ";

    static {
        try {
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            bfEnglish = BaseFont.createFont();
            pdf12BFont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
            pdf10NFont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
            pdf8NFont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
            pdf10BRFont = new Font(bfChinese, 10, Font.BOLD, BaseColor.RED);// 设置字体大小
            threefont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
            englishfont = new Font(bfEnglish, 12, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void genPdfFile(String path){
        try{
            Document document = new Document(PageSize.A4, 100, 100, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            openDoc(document);

            document.newPage();
            document.add(genTable());

            closeDoc(document);
        } catch (FileNotFoundException e) {
            logger.error("文件不存在，异常信息：{}", e);
        } catch (DocumentException e) {
            logger.error("实例化PdfWriter失败，异常信息：{}", e);
        }
    }



    /**
     * 生成表格
     */
    private static PdfPTable genTable(){
        PdfPTable table = new PdfPTable(15);
        table.setSpacingBefore(30);
        table.setSpacingAfter(30);

        table.addCell(createCell("线下支付信息单", pdf12BFont, Element.ALIGN_CENTER, 15));

        table.addCell(createCell("交易信息", pdf10NFont, Element.ALIGN_LEFT, 15));
        addTradeCell(table);

        table.addCell(createCell("转账信息", pdf10NFont, Element.ALIGN_LEFT, 15));
        addTransferCell(table);

        addPromptCell(table);

        return table;
    }

    private static void openDoc(Document document){
        document.open();
    }

    private static void closeDoc(Document document){
        document.close();
    }



    public static void addTradeCell(PdfPTable table) {
        table.addCell(createColCell("订单号", pdf10NFont, Element.ALIGN_RIGHT, 5, true));
        table.addCell(createColCell("CDHK201610100028", pdf10NFont, Element.ALIGN_RIGHT, 10, true));
        table.addCell(createColCell("商品名称", pdf10NFont, Element.ALIGN_RIGHT, 5, true));
        table.addCell(createColCell("重庆易极付科技有限公司", pdf10NFont, Element.ALIGN_RIGHT, 10, true));
        table.addCell(createColCell("交易时间", pdf10NFont, Element.ALIGN_RIGHT, 5, true));
        table.addCell(createColCell("2016-10-28 12:00", pdf10NFont, 10, Element.ALIGN_RIGHT, true));
    }

    public static void addTransferCell(PdfPTable table) {
        table.addCell(createRowCell("收款人", pdf10NFont, Element.ALIGN_CENTER, 4, 1, true));

        table.addCell(createColCell("收款银行", pdf10NFont, Element.ALIGN_RIGHT, 4, true));
        table.addCell(createColCell("CDHK201610100028", pdf10NFont, Element.ALIGN_RIGHT, 10, true));
        table.addCell(createColCell("开户行", pdf10NFont, Element.ALIGN_RIGHT, 4, true));
        table.addCell(createColCell("重庆易极付科技有限公司", pdf10NFont, Element.ALIGN_RIGHT, 10, true));
        table.addCell(createColCell("银行账号", pdf10NFont, Element.ALIGN_RIGHT, 4, true));
        table.addCell(createColCell("2016-10-28 12:00", pdf10NFont, Element.ALIGN_RIGHT, 10, true));
        table.addCell(createColCell("银行户名", pdf10NFont, Element.ALIGN_RIGHT, 4, true));
        table.addCell(createColCell("易极付", pdf10NFont, Element.ALIGN_RIGHT, 10, true));

        table.addCell(createColCell("用途", pdf10NFont, Element.ALIGN_RIGHT, 5, true));
        table.addCell(createColCell("20161102MGVB", pdf10BRFont, Element.ALIGN_RIGHT, 10, true));
        table.addCell(createColCell("转账金额", pdf10NFont, Element.ALIGN_RIGHT, 5, true));
        table.addCell(createColCell("48787200", pdf10BRFont, Element.ALIGN_RIGHT, 10, true));
        table.addCell(createColCell("金额大写", pdf10NFont, Element.ALIGN_RIGHT, 5, true));
        table.addCell(createColCell("48787200", pdf10NFont, Element.ALIGN_RIGHT, 10, true));
    }

    public static void addPromptCell(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(15);
        Phrase phrase = new Phrase();
        phrase.add(new Chunk("重要提示：\n银行转账时，", pdf10NFont));
        phrase.add(new Chunk("用途、金额", pdf10BRFont));
        phrase.add(new Chunk("等信息请务必按照如上信息填写", pdf10NFont));
        cell.setPhrase(phrase);
        table.addCell(cell);

    }


    public static PdfPCell createCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    public static PdfPCell createCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    public static PdfPCell createCell(String value, Font font, int align, int colspan) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    public static PdfPCell createColCell(String value, Font font, int align, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }
    public static PdfPCell createColCell(String value, Font font, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }

    public static PdfPCell createRowCell(String value, Font font, int colalign, int rowspan, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setHorizontalAlignment(colalign);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value, font));
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }

    public static PdfPCell createCell(String value, Font font, int rowspan, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value, font));
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }
    public static PdfPCell createColCell(String value, Font font, int rowspan, int colspan, Border border) {
        PdfPCell cell = new PdfPCell();
        setBaseCell(cell);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value, font));
        if (!border.top) cell.setBorderWidthTop(0);
        if (!border.right) cell.setBorderWidthRight(0);
        if (!border.buttom) cell.setBorderWidthBottom(0);
        if (!border.left) cell.setBorderWidthLeft(0);
        return cell;
    }

    private static void setBaseCell(PdfPCell cell){
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(4.0f);
    }

    public static class Border{
        private boolean top = true;
        private boolean right = true;
        private boolean buttom = true;
        private boolean left = true;

        public Border(){ }
        public Border(boolean top, boolean right, boolean buttom, boolean left){
            this.top = top;
            this.right = right;
            this.buttom = buttom;
            this.left = left;
        }
    }

    /**
     * 生成列表清单
     */
    private static List genList(){
        List list = new List(List.ORDERED);
        for (int i = 0; i < 10; i++) {
            ListItem item = new ListItem(String.format("%s: %d movies",
                    "country" + (i + 1), (i + 1) * 100), new Font(
                    Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE));
            List movielist = new List(List.ORDERED, List.ALPHABETICAL);
            movielist.setLowercase(List.LOWERCASE);
            for (int j = 0; j < 5; j++) {
                ListItem movieitem = new ListItem("Title" + (j + 1));
                List directorlist = new List(List.UNORDERED);
                for (int k = 0; k < 3; k++) {
                    directorlist.add(String.format("%s, %s", "Name1" + (k + 1),
                            "Name2" + (k + 1)));
                }
                movieitem.add(directorlist);
                movielist.add(movieitem);
            }
            item.add(movielist);
            list.add(item);
        }
        return list;
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(SPACE);
        }
        return result.toString();
    }


    public static void main(String args[]){
        String path = "D:\\ITextTest.pdf";
        genPdfFile(path);
//        logger.info("生成PDF文件成功");
    }

}
