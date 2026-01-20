package com.example.demo.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderDetails;
import com.example.demo.repository.OrderDetailsRepository;

@Service
public class ExcelTest {
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	public void exportOrderDetail() {
	
		System.out.println("開始");
		
		List<OrderDetails> od = orderDetailsRepository.findAll();
		
        try (Workbook workbook = new XSSFWorkbook()) {
            // 新規シートの作成
            Sheet sheet = workbook.createSheet("商品発注詳細");
            //ヘッダーの作成
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("発注ID");
            header.createCell(1).setCellValue("商品");
            header.createCell(2).setCellValue("数量");
            // データの書き込み
            for (int i = 0; i < od.size(); i++) {
            	OrderDetails de = od.get(i);
            	Row row = sheet.createRow(i);
            	row.createCell(0).setCellValue(de.getOrdersId());
            	row.createCell(1).setCellValue(de.getGoodsId());
            	row.createCell(2).setCellValue(de.getQuantity());
            }
            // ファイルへの保存
            try (FileOutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("終了");
    }
}