package com.bobac.microservice.customerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobac.microservice.customerservice.model.Customer;
import com.bobac.microservice.customerservice.repo.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	public List<Customer> queryAll(){
		return repo.findAll();
	}
	public void addCustomer(Customer c) {
		repo.save(c);
	}
	public void editCustomer(Long id,Customer c) {
		c.setId(id);
		repo.save(c);
	}
	public void deleteCustomer(Long id) {
		repo.deleteById(id);
	}
}
