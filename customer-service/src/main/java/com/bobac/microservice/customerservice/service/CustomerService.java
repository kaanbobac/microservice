package com.bobac.microservice.customerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bobac.microservice.customerservice.exception.UserNotFoundException;
import com.bobac.microservice.customerservice.model.Customer;
import com.bobac.microservice.customerservice.model.CustomerOrder;
import com.bobac.microservice.customerservice.model.Order;
import com.bobac.microservice.customerservice.repo.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private ServiceUtil util;
	@Autowired
	private CustomerRepository repo;
	public List<Customer> queryAll(){
		return repo.findAll();
	}
	public void addCustomer(Customer c) {
		repo.save(c);
	}
	public void editCustomer(Long id,Customer c) {
		Customer customer = queryCustomerDb(id);
		c.setId(id);
		repo.save(c);
	}
	public void deleteCustomer(Long id) {
		Customer customer =  queryCustomerDb(id);
		repo.deleteById(id);
	}
	public Customer queryOneCustomer(Long id) {
		Customer customer =  queryCustomerDb(id);
		customer.setEnvironment(util.getServiceAddress());
		return customer;
	}
	public CustomerOrder queryCustomerOrder(Long customerId) {
		Customer customer = queryCustomerDb(customerId);
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setEnvironment(util.getServiceAddress());
		customerOrder.setId(customerId);
		customerOrder.setName(customer.getName());
		customerOrder.setOrders(callOrderService(customerId));
		return customerOrder;
	}
	public List<Order> callOrderService(Long customerId){
		queryCustomerDb(customerId);
		RestTemplate restTemplate = new RestTemplate();
		String url ="http://localhost:8000//order/customer/" + customerId;
		List <Order> orders = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {}).getBody();
		return orders;
	}
	private Customer queryCustomerDb(Long id) {
		return repo.findById(id).orElseThrow(
				() -> new UserNotFoundException("Customer: " + id + " is not found"));
		
	}
}
