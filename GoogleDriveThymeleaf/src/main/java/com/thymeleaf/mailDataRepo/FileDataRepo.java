package com.thymeleaf.mailDataRepo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thymeleaf.entity.FileDetails;

@Repository
public interface FileDataRepo  extends JpaRepository<FileDetails, Integer>{

	
	FileDetails findByFileName(String fileName);
}
