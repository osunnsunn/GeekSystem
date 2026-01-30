package com.example.demo.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
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
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderDetails;
import com.example.demo.service.OrderDetailsService;

@Service
public class ExcelOrder {

    @Autowired
    private OrderDetailsService orderDetailsService;

    public String exportOrderDetail(LocalDateTime start, LocalDateTime end) throws IOException {

        System.out.println("===START ApachePOI===");

        List<OrderDetails> od = orderDetailsService.getOrdersBetween(start, end);

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("商品発注詳細");

            // ヘッダー
            Row header = sheet.createRow(0);
            header.setHeightInPoints(30);
            String[] headers = {"発注ID", "発注者", "商品名", "数量", "発注日時", "商品画像"};
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // データ
            for (int i = 0; i < od.size(); i++) {
                OrderDetails de = od.get(i);
                Row row = sheet.createRow(i + 1);
                row.setHeightInPoints(120);
                row.createCell(0).setCellValue(de.getOrdersId());
                row.createCell(1).setCellValue(de.getOrders().getUsers().getFirstName());
                row.createCell(2).setCellValue(de.getGoods().getName());
                row.createCell(3).setCellValue(de.getQuantity());
                row.createCell(4).setCellValue(de.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

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

            String fileName = String.format("workbook_%s_to_%s.xlsx", start.toLocalDate(), end.toLocalDate());

            try (FileOutputStream fos = new FileOutputStream("src/main/resources/public/" + fileName)) {
                workbook.write(fos);
            }

            System.out.println("===END ApachePOI===");
            return fileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}