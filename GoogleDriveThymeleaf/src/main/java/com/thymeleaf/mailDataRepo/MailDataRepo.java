package com.thymeleaf.mailDataRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thymeleaf.entity.SaveMailData;

@Repository
public interface MailDataRepo extends JpaRepository<SaveMailData, Integer> {

	SaveMailData findByEmail(String email);
}
