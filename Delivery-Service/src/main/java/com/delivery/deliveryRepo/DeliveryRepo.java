package com.delivery.deliveryRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.entitys.ConsumerDetails;

@Repository
public interface DeliveryRepo extends JpaRepository<ConsumerDetails, Integer> {

	 Optional<ConsumerDetails> findByProductId(String productId);
 // Optional<ConsumerDetails>findByProductId(String productId);
}
