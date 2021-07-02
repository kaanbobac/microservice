package com.bobac.microservice.customerservice.model;

import lombok.Data;

@Data
public class Order {
	private Long id;
	private Long customerId;
	private String description;
	private String status;
}
