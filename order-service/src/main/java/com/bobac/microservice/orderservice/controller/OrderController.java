package com.bobac.microservice.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bobac.microservice.orderservice.model.Order;
import com.bobac.microservice.orderservice.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	@GetMapping("/order")
	public ResponseEntity<List<Order>> queryAllOrders(){
		return new ResponseEntity<List<Order>>(orderService.queryAllOrders(),HttpStatus.OK);
	}
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> queryOneOrder(@PathVariable Long id){
		return new ResponseEntity<Order>(orderService.queryOrder(id),HttpStatus.OK);
	}
	@PutMapping("/order/{id}")
	public ResponseEntity updateOrder(@PathVariable Long id, @RequestBody Order order){
		orderService.updateOrder(id, order);
		return new ResponseEntity(HttpStatus.OK);
	}
	@PostMapping("/order")
	public ResponseEntity saveOrder(@RequestBody Order order){
		orderService.saveOrder(order);
		return new ResponseEntity(HttpStatus.OK);
	}
	@GetMapping("/order/customer/{id}")
	public ResponseEntity<List<Order>> queryOCustomerOrder(@PathVariable Long id){
		return new ResponseEntity<List<Order>>(orderService.queryCustomerOrder(id),HttpStatus.OK);
	}
}
