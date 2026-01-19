package com.example.demo.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTest {
	public static void main(String[] args) {
		
		System.out.println("開始");
		
        try (Workbook workbook = new XSSFWorkbook()) {
            // 新規シートの作成
            Sheet sheet = workbook.createSheet("Sheet1");
            // データの書き込み
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Hello, Apache POI!");
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
