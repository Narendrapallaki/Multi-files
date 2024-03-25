package com.fFFGoogleDrive.gDriveControllor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fFFGoogleDrive.entity.FormDetails;
import com.fFFGoogleDrive.gDriveService.GdriveService;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RestController
public class GdriveControllor {

	@Autowired
	private GdriveService gdriveService;

	@GetMapping("/testingPage")
	public String testingPage() {
		return "emailForm";
	}

	@PostMapping("/send")
	public String sendMailDriveFile(@ModelAttribute FormDetails details,
			@RequestParam(name = "uploadFile", required = false) MultipartFile file,
			@RequestParam(name = "attachment", required = false) MultipartFile[] attFile) throws Exception {
                                                                
		                                                                                 
		log.info("contrllor values {}", details, file, attFile);

		gdriveService.excelFileSend(file, details);
		return "redirect:/successStatus";
	}

	@GetMapping("/successStatus")
	public String successPage() {
		return "success";
	}

	@GetMapping("/singleMultiFile")
	public String authorize() {
		return "output";
	}

	@PostMapping("/getFile")
	public String getFileFromLocal(@RequestParam(name = "uploadFile", required = false) MultipartFile file) throws IOException, MessagingException {

		gdriveService.sendLocal(file);
		// log.info("File name in local machine
		// :{}",multipartFile.getOriginalFilename());
		return "redirect:/successStatus";
	}

}
