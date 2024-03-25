package com.fFFGoogleDrive.excelReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.fFFGoogleDrive.entity.ExcelData;

public class ExcelReader {

	public static List<ExcelData> readExcelData(MultipartFile eFile) throws IOException {
		List<ExcelData> emailDataList = new ArrayList<>();
		Workbook workbook = WorkbookFactory.create(eFile.getInputStream());
		Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {

				String email = row.getCell(1).getStringCellValue();
				String fileName = row.getCell(3).getStringCellValue();
				emailDataList.add(new ExcelData(email, fileName));
			}
		}
		workbook.close();
		return emailDataList;
	}

	
	
}
