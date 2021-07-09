package com.bobac.microservice.customerservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bobac.microservice.customerservice.model.Order;

@FeignClient(name="order-service",url="order:8000")
public interface OrderServiceProxy {
	@GetMapping("/order/customer/{id}")
	public List<Order> queryCustomerOrder(@PathVariable Long id);
}
