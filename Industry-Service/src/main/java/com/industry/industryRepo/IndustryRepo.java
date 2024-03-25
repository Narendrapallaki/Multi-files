package com.industry.industryRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.industry.dto.IndustryDto;
import com.industry.entity.IndustryData;

@Repository
public interface IndustryRepo  extends JpaRepository<IndustryData, Integer>{

	
	
	Optional<IndustryData>findByIndustryId(String industryId);
}
