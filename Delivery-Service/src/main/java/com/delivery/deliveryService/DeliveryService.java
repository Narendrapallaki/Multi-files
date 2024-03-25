package com.delivery.deliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import com.delivery.customException.ProductIdNotFound;
import com.delivery.deliveryRepo.DeliveryRepo;
import com.delivery.dto.ResponseDto;
import com.delivery.dto.ResponseValues;
import com.delivery.entitys.ConsumerDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DeliveryService {

	@Autowired
	private WebClient client;

	public static final String companyUrl = "http://localhost:8082/getProduct/";

	
	
	
	
	@Autowired
	private DeliveryRepo deliveryRepo;

	public ConsumerDetails saveCompanyData(ConsumerDetails details) {

		ConsumerDetails save = deliveryRepo.save(details);
		return save;

	}

	public ResponseDto responseOpt(String productId) throws JsonMappingException, JsonProcessingException {

		ResponseDto dto = new ResponseDto();

		ConsumerDetails opt = deliveryRepo.findByProductId(productId)
				.orElseThrow(() -> new ProductIdNotFound("Consumer id not found in DB" + productId));
		// ResponseDto value = restTemplate.getForObject(companyUrl + productId,
		// ResponseDto.class);

		Mono<ResponseDto> block = client.get().uri(companyUrl + productId).retrieve().bodyToMono(ResponseDto.class);

		ResponseDto block2 = block.block();
		log.info("web client result :{}", block);

		// log.info("obect mapper values :{}", value);
		dto.setUserAddres(opt.getUserAddres());
		dto.setUserContact(opt.getUserContact());
		dto.setUserName(opt.getUserName());
		dto.setUserProduct(opt.getUserProduct());

		dto.setCompanyName(block2.getCompanyName());
		dto.setLocation(block2.getLocation());
		dto.setMobile(block2.getMobile());

		log.info("Dto class result :{}", dto);
		return dto;
	}

}
