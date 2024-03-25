package com.companyservice.companyControllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.companyservice.companyService.CompanyService;
import com.companyservice.dto.ResponseValues;
import com.companyservice.entity.Company;

@RestController
public class CompanyControllor {

	@Autowired
	private CompanyService companyService;

	@PostMapping("/companyInfo")
	public ResponseEntity<Company> saveCompanyInfo(@RequestBody Company company) {

		Company saveCompanyData = companyService.saveCompanyData(company);

		return ResponseEntity.ok(saveCompanyData);

	}

	@GetMapping("/getProduct/{productId}")
	public ResponseEntity<ResponseValues> getCompanyInfo(@PathVariable String productId) {

		ResponseValues byproductId = companyService.getByproductId(productId);

		return ResponseEntity.ok(byproductId);

	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Integer productId) {

		String removeData = companyService.removeData(productId);

		return ResponseEntity.ok(removeData);
	}

}
