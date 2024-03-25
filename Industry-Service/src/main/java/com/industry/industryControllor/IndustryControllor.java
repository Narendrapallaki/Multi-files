package com.industry.industryControllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.industry.dto.IndustryDto;
import com.industry.entity.IndustryData;
import com.industry.industryService.IndustryService;

@RestController
public class IndustryControllor {

	@Autowired
	private IndustryService industryService;
	
	
	
	@PostMapping("/saveIndustry")
	public ResponseEntity<IndustryData>saveData(@RequestBody IndustryData data)
	{
		IndustryData saveData = industryService.saveIndustryData(data);
		
		return ResponseEntity.ok(saveData);
	}
	
	
	@GetMapping("/getIndustry/{industryId}")
	public ResponseEntity<IndustryDto>getIndustryData(@PathVariable String industryId)
	{
		
		IndustryDto industryDto = industryService.getIndustryData(industryId);
		

		return ResponseEntity.ok(industryDto);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
