package com.thymeleaf.driveService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.entity.FileDetails;
import com.thymeleaf.entity.SaveMailData;
import com.thymeleaf.mailDataRepo.FileDataRepo;
import com.thymeleaf.mailDataRepo.MailDataRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DriveService {

	@Autowired
	private MailDataRepo dataRepo;
	
	@Autowired
	private FileDataRepo fileDataRepo;

	public void mailDataSave(SaveMailData saveMailData) {

		dataRepo.save(saveMailData);

	}

	public List<SaveMailData> getAll() {
		// return
		List<SaveMailData> all = dataRepo.findAll();

		log.info("get All data form service :{}", all);

		return all;
	}
  
	
	public SaveMailData getByMail(String email)
	{
		SaveMailData byEmail = dataRepo.findByEmail(email);
		log.info("Find by email service method :{}", byEmail);
		return byEmail;
	}
	
	public List<FileDetails>getFileData()
	{
		List<FileDetails> all = fileDataRepo.findAll();
		log.info("get All file Names :{}",all);
		
		return all;
	}
}
