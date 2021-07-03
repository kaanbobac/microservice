package com.bobac.microservice.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobac.microservice.orderservice.exception.OrderNotFoundException;
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
		return queryOrderDb(id);
	}
	public void updateOrder(Long id, Order o){
		Order order = queryOrderDb(id);
		o.setId(id);
		repo.save(o);
	}
	public void saveOrder(Order o){
		repo.save(o);
	}
	public List<Order> queryCustomerOrder(Long id){
		if(repo.findByCustomerId(id).isEmpty())
			throw new OrderNotFoundException("Customer " + id + " has no order!");
		return repo.findByCustomerId(id);
	}
	private Order queryOrderDb(Long id) {
		return repo.findById(id).orElseThrow(
				() -> new OrderNotFoundException("Order: " + id + " is not found"));
	}
}
