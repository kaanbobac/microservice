package com.bobac.microservice.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobac.microservice.orderservice.model.Order;
import com.bobac.microservice.orderservice.repo.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	public List<Order> queryAllOrders(){
		return repo.findAll();
	}
	public Order queryOrder(Long id){
		return repo.findById(id).orElse(null);
	}
	public void updateOrder(Long id, Order o){
		o.setId(id);
		repo.save(o);
	}
	public void saveOrder(Order o){
		repo.save(o);
	}
	public List<Order> queryCustomerOrder(Long id){
		return repo.findByCustomerId(id);
	}
}
