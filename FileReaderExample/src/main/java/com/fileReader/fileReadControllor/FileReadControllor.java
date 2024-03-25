package com.fileReader.fileReadControllor;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileReader.fileReadService.FileReadService;
@RestController
public class FileReadControllor {

	@Autowired
	 private FileReadService fileReadService;
	
	@GetMapping("/fileReader")
	public ResponseEntity<List<String>>readFile(@RequestParam("file") MultipartFile file)
	{
		
		List<String> fileRead = null;
		try {
			fileRead = fileReadService.readExcelData(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(fileRead);
	}
	
	@GetMapping("/pdfReader")
	public ResponseEntity<List<List<String>>>readPdf(@RequestParam("pdf") MultipartFile pdf) throws IOException
	{
		
		List<List<String>> tableFromPdf = fileReadService.readTableFromPdf(pdf);
	
		return ResponseEntity.ok(tableFromPdf);
	}
	

	/*
	 * @PostMapping("/extractTables") public String
	 * extractTables(@RequestParam("filePath") String filePath) { try {
	 * fileReadService.extractTables(filePath); return
	 * "Tables extracted successfully!"; } catch (IOException e) {
	 * e.printStackTrace(); return
	 * "Failed to extract tables. Please check the file path."; } }
	 */
}
