package com.beck.util.myutil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beck.util.myutil.PdfInfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

/**
 * @ClassName PdfTest
 * @Description TODO
 * @Author edz
 * @Date 2019/10/16 18:20
 * @Version 1.0
 **/
public class PdfTest1 {


    public static void main(String[] args) throws Exception{
        OutputStream outputStream = new FileOutputStream("D:/test_pdf.pdf");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("forderNo","111112222233");
        dataMap.put("forderPickupCode","22233sssss");
        dataMap.put("fleaveTime","2019-05-12 00:00:00");
        dataMap.put("fbuyerName","sssssssssssssssssssssssssssssss");
        dataMap.put("freceiveMobile","13854383845");
        dataMap.put("pickUpAddress","火星一号ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        List<PdfInfo> skuList = new ArrayList<PdfInfo>();
        for (int i = 0; i<50;i++){
            PdfInfo pdfInfo = new PdfInfo();
            pdfInfo.setBarCode("222333");
            pdfInfo.setFgoodsItemPic("http://img0.linkiebuy.com/M00/01/12/rBIBoF2mhU2AEZeTAAAoFiCH-l0155.jpg");
            pdfInfo.setBarCodePic("file:///d:/barcode.png");
            pdfInfo.setFquantity("3ssssss");
            pdfInfo.setFskuName("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
            pdfInfo.setFskuSpecValue("jajflajlfjalfjlssssssssssssssssssssssssssssssssssssss");
            pdfInfo.setSkuCode("22222222222222222222222222222");
            skuList.add(pdfInfo);
        }
        dataMap.put("skuList",skuList);

        generaPdf(dataMap, outputStream);
    }

    public static void generaPdf(Map<String, Object> dataMap, OutputStream outputStream) throws Exception{
        //创建文本
        Document document = new Document(PageSize.A4);

        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font cellContent = new Font(bfChinese, 11, Font.NORMAL);

        PdfWriter.getInstance(document, outputStream);
        //打开文本
        document.open();
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);


        //标题
        //边距
        Paragraph paragraph = new Paragraph(40);
        //对齐方式
        paragraph.setAlignment(1);
        //字体
        Font font = new Font(baseFont);
        //字体大小
        font.setSize(14);
        //设置段落字体
        paragraph.setFont(font);
        Chunk chunk = new Chunk("待付款清单");
        paragraph.add(chunk);
        document.add(paragraph);

        //画表头第一行
        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(400);
        table.setSplitLate(false);
        table.setSplitRows(true);
        float[] columnWidth={60,120,60,160};
        table.setTotalWidth(columnWidth);
        //宽度算正确
        table.setLockedWidth(true);

        //标题与表格中间的距离
        Paragraph p = new Paragraph(1);
        //字体
        Font f = new Font(baseFont);
        //字体大小
        f.setSize(1);
        //设置段落字体
        p.setFont(f);
        Chunk c = new Chunk(" ");
        p.add(c);
        document.add(p);

        table.addCell(drawPdfPCell("订单编号：",baseFont,10,0,30));
        String forderNo = dataMap.get("forderNo") != null ? (String) dataMap.get("forderNo"): "-";
        table.addCell(drawPdfPCell(forderNo,baseFont,10,0,30));
        table.addCell(drawPdfPCell("",baseFont,10,0,30));
        table.addCell(drawPdfPCell("",baseFont,10,0,30));

        table.addCell(drawPdfPCell("预约码：",baseFont,10,0,30));
        String forderPickupCode = dataMap.get("forderPickupCode") != null ? (String) dataMap.get("forderPickupCode"): "-";
        table.addCell(drawPdfPCell(forderPickupCode,baseFont,10,0,30));
        table.addCell(drawPdfPCell("离境时间：",baseFont,10,0,30));
        String fleaveTime = dataMap.get("fleaveTime") != null ? (String) dataMap.get("fleaveTime"): "-";
        table.addCell(drawPdfPCell(fleaveTime,baseFont,10,0,30));

        table.addCell(drawPdfPCell("姓名：",baseFont,10,0,30));
        String fbuyerName = dataMap.get("fbuyerName") != null ? (String) dataMap.get("fbuyerName"): "-";
        table.addCell(drawPdfPCell(fbuyerName,baseFont,10,0,30));
        table.addCell(drawPdfPCell("手机号：",baseFont,10,0,30));
        String freceiveMobile = dataMap.get("freceiveMobile") != null ? (String) dataMap.get("freceiveMobile"): "-";
        table.addCell(drawPdfPCell(freceiveMobile,baseFont,10,0,30));

        table.addCell(drawPdfPCell("提货点：",baseFont,10,0,30));
        String pickUpAddress = dataMap.get("pickUpAddress") != null ? (String) dataMap.get("pickUpAddress"): "-";

        PdfPCell pickUpCell = drawPdfPCell(pickUpAddress,baseFont,10,0,30);
        pickUpCell.setColspan(3);
        table.addCell(pickUpCell);
        table.addCell(drawPdfPCell("",baseFont,10,0,30));
        table.addCell(drawPdfPCell("",baseFont,10,0,30));
        document.add(table);
        //标题与表格中间的距离
        Paragraph p1 = new Paragraph(5);
        //字体
        Font f1 = new Font(baseFont);
        //字体大小
        f1.setSize(18);
        //设置段落字体
        p1.setFont(f);
        Chunk c1 = new Chunk(" ");
        p1.add(c1);
        document.add(p1);
        //画一条直线
        DottedLineSeparator line = new DottedLineSeparator();
        //下移五个单位
        line.setGap(0);
        line.setOffset(0);
        document.add(line);

        //画表体部分
        List<PdfInfo> skuList = JSONArray.parseArray(JSONObject.toJSONString(dataMap.get("skuList")), PdfInfo.class);
        if (skuList == null || skuList.size() == 0){
            document.close();
            return;
        }
        int tem = 0;
        for(PdfInfo pdfInfo: skuList){
            table = new PdfPTable(4);
            table.setTotalWidth(400);
            table.setSplitLate(false);
            table.setSplitRows(true);
            float[] columnWidth5={70,70, 160, 100};
            table.setTotalWidth(columnWidth5);
            //宽度算正确
            table.setLockedWidth(true);
            tem += 1;
            PdfPCell cellp = new PdfPCell(new Paragraph(tem+"", cellContent));
            cellp.setPadding(1);
            cellp.setRowspan(1);
            cellp.setMinimumHeight(30*3);
            cellp.disableBorderSide(15);
            table.addCell(cellp);

            String itemPic = pdfInfo.getFgoodsItemPic();
            java.net.URL itemPicUrl = new java.net.URL(itemPic);
            java.awt.Image awtItemImage = ImageIO.read(itemPicUrl);

            String barCodePic = pdfInfo.getBarCodePic();
            java.net.URL barCodeUrl = new java.net.URL(barCodePic);
            java.awt.Image awtBarCodeImage = ImageIO.read(barCodeUrl);


            PdfPCell baseTableCell = null;
            if(null!=awtItemImage){
                Image img = Image.getInstance(awtItemImage,null);
                img.scaleAbsolute((float) 210, (float) 300);
                baseTableCell = new PdfPCell(img,true);
                baseTableCell.setPadding(1);
                baseTableCell.setRowspan(3);
                baseTableCell.setMinimumHeight(30*3);

                baseTableCell.disableBorderSide(15);
                table.addCell(baseTableCell);

            }else{
                baseTableCell = new PdfPCell(new Paragraph("图片", cellContent));
                baseTableCell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
                baseTableCell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
                baseTableCell.setRowspan(3);
                baseTableCell.setMinimumHeight(30*3);
                baseTableCell.disableBorderSide(15);
                table.addCell(baseTableCell);
            }
            //字体
            Font contentFont = new Font(baseFont);
            //字体大小
            contentFont.setSize(8);
            PdfPCell pdfCell = drawPdfPCell("",baseFont,8,0,7);
            pdfCell.setRowspan(3);
            pdfCell.setColspan(1);
            pdfCell.disableBorderSide(15);

            PdfPTable subTable = new PdfPTable(new float[]{100});
            table.setSplitLate(false);
            table.setSplitRows(true);
            PdfPCell subPdfCell = null;

            String skuName = pdfInfo.getFskuName();
            subPdfCell = drawPdfPCell(skuName,baseFont,8,0,7);
            subTable.addCell(subPdfCell);

            String skuSpecValue = pdfInfo.getFskuSpecValue();
            subPdfCell = drawPdfPCell(skuSpecValue,baseFont,8,0,7);
            subTable.addCell(subPdfCell);

            String quantity = pdfInfo.getFquantity();
            subPdfCell = drawPdfPCell("X"+quantity,baseFont,8,0,7);
            subTable.addCell(subPdfCell);

            pdfCell.addElement(subTable);
            table.addCell(pdfCell);

            PdfPCell pdfCell1 = drawPdfPCell("",baseFont,8,0,7);
            pdfCell1.setRowspan(3);
            pdfCell1.setColspan(1);
            PdfPTable subTable1 = new PdfPTable(new float[]{100});
            table.setSplitLate(false);
            table.setSplitRows(true);
            PdfPCell subPdfCell1 = null;

            Image barCodeImage = Image.getInstance(awtBarCodeImage,null);
            barCodeImage.scalePercent((20));
            subPdfCell1 = new PdfPCell(barCodeImage,true);
            subPdfCell1.setColspan(1);
            subPdfCell1.setMinimumHeight(7);
            subPdfCell1.disableBorderSide(15);
            subTable1.addCell(subPdfCell1);

            String barCode = pdfInfo.getBarCode();
            subPdfCell1 = drawPdfPCell("条形码："+barCode,baseFont,8,0,7);
            subTable1.addCell(subPdfCell1);

            String skuCode = pdfInfo.getSkuCode();
            subPdfCell1 = drawPdfPCell("sku编码：" + skuCode,baseFont,8,0,7);
            subTable1.addCell(subPdfCell1);
            pdfCell1.addElement(subTable1);

            table.addCell(pdfCell1);
            document.add(table);
        }

        //关闭文本
        document.close();
    }

    private static PdfPCell drawPdfPCell(String cellText,BaseFont baseFont,float size,int alignment,int minimumHeight) throws Exception{
        //为null会报错  防止报错
        if(cellText==null){
            cellText=" ";
        }
        //表格开始
        Paragraph paragraph = new Paragraph();
        //对齐方式
        paragraph.setAlignment(alignment);
        //字体
        Font font = new Font(baseFont);
        //字体大小
        font.setSize(size);
        //设置段落字体
        paragraph.setFont(font);
        Chunk chunk = new Chunk(cellText);
        paragraph.add(chunk);
        PdfPCell cell = new PdfPCell();
        cell.setUseAscender(true);
        //设置cell垂直居中
        cell.setVerticalAlignment(Element.ALIGN_LEFT);
        //设置单元格最小高度，当前行最小高度
        cell.setMinimumHeight(minimumHeight);
        cell.addElement(paragraph);
        cell.disableBorderSide(15);
        return cell;
    }

}
