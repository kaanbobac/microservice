package com.bobac.microservice.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private Long id;
	private Long customerId;
	private String description;
	private String status;
}
