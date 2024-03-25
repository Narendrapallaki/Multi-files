package com.industry.industryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.industry.customException.IndustryIdNotFound;
import com.industry.dto.IndustryDto;
import com.industry.entity.IndustryData;
import com.industry.industryRepo.IndustryRepo;

@Service
public class IndustryService {

	@Autowired
	private IndustryRepo industryRepo;

	public IndustryData saveIndustryData(IndustryData data) {

		IndustryData save = industryRepo.save(data);
		return save;

	}
	
	
	
	public IndustryDto getIndustryData(String industryId)
	{
		
		IndustryData dto = industryRepo.findByIndustryId(industryId)
		.orElseThrow(()-> 
		new IndustryIdNotFound("Industry id not found in DB :"+industryId));
		
		
		IndustryDto Idto=new IndustryDto(dto.getIndustryId(), dto.getAddress(), dto.getIndustryName());
		
		return Idto;
	}
	
	
	

}
