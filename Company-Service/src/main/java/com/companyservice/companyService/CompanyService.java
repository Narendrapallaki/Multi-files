package com.companyservice.companyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companyservice.companyRepo.CompanyRepo;
import com.companyservice.customException.ProductIdNotFound;
import com.companyservice.dto.ResponseValues;
import com.companyservice.entity.Company;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CompanyService {

	@Autowired
	private CompanyRepo companyRepo;

	public Company saveCompanyData(Company company) {

		Company save = companyRepo.save(company);
		return save;

	}
	
	
	public ResponseValues getByproductId(String productId)
	{
		 
		ResponseValues rv=new ResponseValues();
		
	 List<Company> company = companyRepo.findByProductId(productId);
	 
	 log.info("List of product id result:{}",company);
		//.orElseThrow(()->  new  ProductIdNotFound("ProductId not found for productId: " + productId));
		for (Company company2 : company) {
			rv.setCompanyName(company2.getCompanyName());
			rv.setLocation(company2.getLocation());
			rv.setMobile(company2.getMobile());
		}
		
		return rv;
	}

	
	
	public String removeData(Integer productId)
	{
		
		companyRepo.deleteById(productId);
		return "deleted";
// return companyRepo.deleteByProductId(productId)
		//.orElseThrow(()-> new ProductIdNotFound("Product id not found in DB :"+productId));
	}
}
