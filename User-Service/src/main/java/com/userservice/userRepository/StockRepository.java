package com.userservice.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.entity.WareHouse;
@Repository
public interface StockRepository extends JpaRepository<WareHouse, Integer>{


	Iterable<WareHouse> findByItem(String item);
}
