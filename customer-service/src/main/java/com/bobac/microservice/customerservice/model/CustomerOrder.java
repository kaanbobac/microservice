package com.bobac.microservice.customerservice.model;

import java.util.List;

import lombok.Data;

@Data
public class CustomerOrder {
	private Long id;
	private String name;
	private String environment;
	private List<Order> orders;
}
