package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.OrderAddress;

public interface OrderAddressRepo extends JpaRepository<OrderAddress, Integer>{

}
