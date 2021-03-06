package com.bobac.microservice.customerservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bobac.microservice.customerservice.exception.CustomerOrderNotFoundException;
import com.bobac.microservice.customerservice.exception.UserNotFoundException;
import com.bobac.microservice.customerservice.model.Customer;
import com.bobac.microservice.customerservice.model.CustomerOrder;
import com.bobac.microservice.customerservice.model.Order;
import com.bobac.microservice.customerservice.repo.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private OrderServiceProxy proxy;
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
		customerOrder.setOrders(callOrderFeign(customerId));
		return customerOrder;
	}
	public List<Order> callOrderService(Long customerId){
		queryCustomerDb(customerId);
		RestTemplate restTemplate = new RestTemplate();
		String url ="http://order:8000/order/customer/" + customerId;
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {}).getBody();
		}
		catch(final HttpClientErrorException e) {
			if(e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new CustomerOrderNotFoundException(e.getMessage());
		}
		return orders;
	}
	public List<Order> callOrderFeign(Long customerId){
		queryCustomerDb(customerId);
		return proxy.queryCustomerOrder(customerId);
	}
	public Customer queryCustomerDb(Long id) {
		return repo.findById(id).orElseThrow(
				() -> new UserNotFoundException("Customer: " + id + " is not found!"));
		
	}
}
