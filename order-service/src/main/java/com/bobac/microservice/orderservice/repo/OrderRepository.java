package com.bobac.microservice.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bobac.microservice.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
