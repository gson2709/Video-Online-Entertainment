package com.poly.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poly.entity.LikesCount;
import com.poly.entity.User;

public class ExcelUtis {
	
	public static List<User> getData(File excelFile) throws Exception{
		List<User> list = new ArrayList<User>();
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
		XSSFSheet sheet = workbook.getSheet("User");
		int lastRowNum = sheet.getLastRowNum();

		for (int i = 0; i < lastRowNum; i++) {
			Row row = sheet.getRow(i);
			User user = new User();
			Cell cell1 = row.getCell(0);
			user.setUsername(cell1.getStringCellValue());

			Cell cell2 = row.getCell(1);
			user.setFullname(cell2.getStringCellValue());

			Cell cell3 = row.getCell(2);
			user.setEmail(cell3.getStringCellValue());

			Cell cell4 = row.getCell(3);
			user.setPassword(cell4.getStringCellValue());

			Cell cell5 = row.getCell(4);
			user.setAdmin(cell5.getStringCellValue().equalsIgnoreCase("admin") ? true : false);

			list.add(user);
		}
		workbook.close();
		return list;
	}
	
	public static void setData(File file, List<LikesCount> list, String header[]) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Favorite Count By Video");
		Row rowHeader = sheet.createRow(0);
		for (int i = 0; i < header.length; i++) {
			Cell cell = rowHeader.createCell(i);
			cell.setCellValue(header[i]);
		}
		int count = 1;
		for (LikesCount like : list) {
			Row row = sheet.createRow(count++);
			Cell cell = row.createCell(0);
			cell.setCellValue(like.getTitle());
			cell = row.createCell(1);
			cell.setCellValue(like.getLikes());
			cell = row.createCell(2);
			cell.setCellValue(like.getNewest());
			cell = row.createCell(3);
			cell.setCellValue(like.getOldest());
		}
		for (int i = 0; i < header.length; i++) {
            sheet.autoSizeColumn(i);
        }
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		workbook.close();
	}
}
