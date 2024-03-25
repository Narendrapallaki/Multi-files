package com.companyservice.companyRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.companyservice.entity.Company;
@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer>{

	
	List<Company> findByProductId(String productId);

	
	//Optional<Company> delete(String productId);
}
