package com.bobac.microservice.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bobac.microservice.customerservice.model.Customer;
import com.bobac.microservice.customerservice.model.CustomerOrder;
import com.bobac.microservice.customerservice.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> queryAll() {
		return new ResponseEntity<List<Customer>>(customerService.queryAll(), HttpStatus.OK);
	}
	@PostMapping("/customer")
	public ResponseEntity addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity(HttpStatus.OK);
	}
	@PutMapping("/customer/{id}")
	public ResponseEntity editCustomer(@PathVariable Long id,@RequestBody Customer customer) {
		customerService.editCustomer(id, customer);
		return new ResponseEntity(HttpStatus.OK);
	}
	@DeleteMapping("/customer/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> queryOneCustomer(@PathVariable Long id) {
		return new ResponseEntity<Customer>(customerService.queryOneCustomer(id), HttpStatus.OK);
	}
	@GetMapping("/customer/{id}/order")
	public ResponseEntity<CustomerOrder> queryCustomerOrder(@PathVariable Long id){
		return new ResponseEntity<CustomerOrder>(customerService.queryCustomerOrder(id), HttpStatus.OK);
	}
}
