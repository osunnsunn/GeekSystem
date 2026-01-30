package com.example.demo.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderDetails;
import com.example.demo.repository.OrderDetailsRepository;

@Service
@Component
public class ExcelTest {
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	public void exportOrderDetail() {
	
		System.out.println("===START ApachePOI===");
		
		List<OrderDetails> od = orderDetailsRepository.findAll();
		
        try (
        	Workbook workbook = new XSSFWorkbook()) {
        	
        	//ヘッダー用スタイル
            CellStyle headerStyle = workbook.createCellStyle();
            //背景色
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //フォント
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());
            headerStyle.setFont(headerFont);
            //中央寄せ
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //罫線
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            
            // 新規シートの作成
            Sheet sheet = workbook.createSheet("商品発注詳細");
            //ヘッダーの作成
            Row header = sheet.createRow(0);
            //ヘッダーの高さ
            header.setHeightInPoints(30);

            String[] headers = {
                "発注ID", "発注者", "商品名", "数量", "発注日時", "商品画像"
            };
            for (int i = 0; i < headers.length; i++) {
                var cell = header.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            //セルの幅
            sheet.autoSizeColumn(0);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 5000);
            sheet.setColumnWidth(3, 2000);
            sheet.setColumnWidth(4, 5000);

            // データの書き込み
            for (int i = 0; i < od.size(); i++) {
            	OrderDetails de = od.get(i);
            	Row row = sheet.createRow(i+1);
            	
            	row.setHeightInPoints(120);//セルの高さ
            	
            	row.createCell(0).setCellValue(de.getOrdersId());
            	row.createCell(1).setCellValue(de.getOrders().getUsers().getFirstName());
            	row.createCell(2).setCellValue(de.getGoods().getName());
            	row.createCell(3).setCellValue(de.getQuantity());
            	row.createCell(4).setCellValue(de.getCreatedAt());
            	
            	String imgname = de.getGoods().getImage();
            	if (imgname == null || imgname.isBlank()) {
            		row.createCell(5).setCellValue("画像なし");
            		continue;
            	}
            	try {
            	    ClassPathResource resource = new ClassPathResource("static/img/" + imgname);
            	    byte[] bytes;
            	    try (InputStream is = resource.getInputStream()) {
            	        bytes = is.readAllBytes();
            	    }
            	    int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            	    Drawing<?> drawing = sheet.createDrawingPatriarch();
            	    ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
            	    anchor.setCol1(5);
            	    anchor.setRow1(i + 1);
            	    Picture pict = drawing.createPicture(anchor, pictureIdx);
            	    pict.resize();
            	} catch (Exception e) {
            		row.createCell(5).setCellValue("画像なし");
            	}
            }
            sheet.setColumnWidth(5, 20000);
      
            // ファイルへの保存
            try (
            	FileOutputStream fileOut = new FileOutputStream("src/main/resources/public/workbook.xlsx")) {
                workbook.write(fileOut);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("===END ApachePOI===");
    }
}