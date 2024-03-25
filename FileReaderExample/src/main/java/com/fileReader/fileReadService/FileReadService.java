package com.fileReader.fileReadService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.pdf.AbsorbedCell;
import com.aspose.pdf.AbsorbedRow;
import com.aspose.pdf.AbsorbedTable;
import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TableAbsorber;
import com.aspose.pdf.TextFragment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileReadService {

	public List<String> readExcelData(MultipartFile eFile) throws IOException {
		List<String> emailDataList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(eFile.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

			// Iterate over each row
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					StringBuilder rowData = new StringBuilder();

					// Iterate over each cell in the row
					for (int j = 0; j < row.getLastCellNum(); j++) {
						Cell cell = row.getCell(j);
						if (cell != null) {
							// Append cell value to rowData
							rowData.append(cell.toString()).append("  ");
						}
					}
					// Add rowData to emailDataList
					emailDataList.add(rowData.toString());
				}
			}
		}
		return emailDataList;
	}

/*	public List<List<String>> readTableFromPdf(MultipartFile file) throws IOException {

		List<List<String>> result = new ArrayList<>();
		Document doc = new Document(file.getInputStream());

		for (int page = 1; page <= doc.getPages().size(); page++) {

			Page get_Item = doc.getPages().get_Item(page);

			TableAbsorber absorber = new TableAbsorber();
			absorber.visit(get_Item);

			List<List<String>> tableData = new ArrayList<>();

			for (AbsorbedTable table : absorber.getTableList()) {

				List<List<String>> rows = new ArrayList<>();

				for (AbsorbedRow row : table.getRowList()) {

					List<String> cells = new ArrayList<>();

					for (AbsorbedCell cell : row.getCellList()) {

						StringBuilder cellText = new StringBuilder();

						for (TextFragment fragment : cell.getTextFragments()) {

							// cellText.append(fragment.getText());
							cellText.append(fragment.getText());

						}
						// System.out.println(cellText);
						cells.add(cellText.toString().trim());

					}
					rows.add(cells);

				}
				tableData.addAll(rows);
			}

			log.info("table : {}", tableData);
			tableData.forEach(row -> {
				row.forEach(cell -> {
					System.out.print(cell + "         "); // Print cell with separator
				});
				System.out.println(" "); // Newline after row
			});
			System.out.println(); // Newline after all tables on the current page
			result.addAll(tableData);
		}

		return result;
	}*/
	
	public List<List<String>> readTableFromPdf(MultipartFile file) throws IOException {
	    List<List<String>> result = new ArrayList<>();
	    Document doc = new Document(file.getInputStream());

	    for (int page = 1; page <= doc.getPages().size(); page++) {
	        Page currentPage = doc.getPages().get_Item(page);
	        TableAbsorber absorber = new TableAbsorber();
	        absorber.visit(currentPage);

	        List<List<String>> tableData = new ArrayList<>();

	        for (AbsorbedTable table : absorber.getTableList()) {
	            List<List<String>> rows = new ArrayList<>();

	            for (AbsorbedRow row : table.getRowList()) {
	                List<String> cells = new ArrayList<>();

	                for (AbsorbedCell cell : row.getCellList()) {
	                    StringBuilder cellText = new StringBuilder();

	                    // Iterate over text fragments within the cell
	                    for (TextFragment fragment : cell.getTextFragments()) {
	                        // Append text fragment to cell text
	                        cellText.append(fragment.getText());
	                    }

	                    // Add cell text to cells list
	                    cells.add(cellText.toString().trim());
	                }

	                // Add cells to the current row
	                rows.add(cells);
	            }

	            // Add rows to the table data
	            tableData.addAll(rows);
	        }

	        // Print table data for debugging
	        log.info("Table Data on Page {}: {}", page, tableData);
	        tableData.forEach(row -> {
	            row.forEach(cell -> {
	                System.out.print(cell + "\t"); // Print cell with tab separator
	            });
	            System.out.println(); // Newline after row
	        });

	        // Add table data to the result
	        result.addAll(tableData);
	    }

	    return result;
	}

}
