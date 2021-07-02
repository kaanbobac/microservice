package com.bobac.microservice.orderservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bobac.microservice.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	public List<Order> findByCustomerId(Long customerId);
}
