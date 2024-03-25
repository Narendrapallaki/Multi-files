package com.fFFGoogleDrive.gDriveService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fFFGoogleDrive.entity.ExcelData;
import com.fFFGoogleDrive.entity.FormDetails;
import com.fFFGoogleDrive.excelReader.ExcelReader;
import com.fFFGoogleDrive.googleDriveConfig.GoogleDriveConfig;
import com.fFFGoogleDrive.localDrive.LocalDrive;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GdriveService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private TemplateEngine engine;
	@Autowired
	private GoogleDriveConfig driveConfig;

	public void excelFileSend(MultipartFile file, FormDetails user) throws Exception {

		log.info("gDriveService class :{}", file.getSize(), user.toString());
		List<ExcelData> excelData = ExcelReader.readExcelData(file);
		log.info("excel reader data :{}", excelData);

		String excelFile = "";
		String email = "";
		for (ExcelData data : excelData) {

			excelFile = data.getFileName();
			email = data.getEmail();

			MultipartFile fileByName = driveConfig.getFileByName(excelFile);
			//String filePath = "C:/Narendra/LOI.pdf";
           //   MultipartFile localStorage = driveConfig.getFileFromLocalStorage(filePath);
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(email);
			helper.setSubject(user.getSubject());

			helper.setText(user.getBody());

			Context con = new Context();
			con.setVariable("name", "All");

			con.setVariable("text", user.getBody());

			String process = engine.process("mail-template.html", con);

			helper.setText(process, true);
			ByteArrayResource iss = new ByteArrayResource(fileByName.getBytes());
			helper.addAttachment(fileByName.getOriginalFilename(), iss);

			//mailSender.send(mimeMessage);

		}
	log.info("Mail sended successfully....!");
	}

	
	
	
	public static final String filePath="C:/Narendra/";
	
	  public void sendLocal(MultipartFile file) throws MessagingException, IOException
	  {
		  log.info("file name in service :{}",file.getOriginalFilename());
		  log.info("file size :{}",file.getSize());
		  List<ExcelData> excelData = ExcelReader.readExcelData(file);
		                  
		  log.info("excel read opt :{}",excelData);
		  
		  String email="";
		  String fileName="";
		  for (ExcelData data : excelData) {
			
			  
			  email=data.getEmail();
			  fileName=data.getFileName();
			  
			  MimeMessage mimeMessage = mailSender.createMimeMessage();

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setTo(email);
				helper.setSubject("Local file attached");

				helper.setText("File read from local mechine");
	 
				
				MultipartFile multipartFile = driveConfig.convertToMultipartFile(filePath+fileName);
				//Context con = new Context();
				//con.setVariable("name", "All");

				//con.setVariable("text", user.getBody());

				//String process = engine.process("mail-template.html", con);

				//helper.setText(process, true);
				ByteArrayResource iss = new ByteArrayResource(multipartFile.getBytes());
				helper.addAttachment(multipartFile.getOriginalFilename(), iss);

				mailSender.send(mimeMessage);

		}
		 log.info("mail sended....!");
		  
	  }
	
	
	
}