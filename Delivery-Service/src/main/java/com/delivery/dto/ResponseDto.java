package com.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	
	
	private String userName;
	private String userProduct;
	private String userContact;
	private String userAddres;
	
	private String companyName;

	private String location;

	private String mobile;
}
