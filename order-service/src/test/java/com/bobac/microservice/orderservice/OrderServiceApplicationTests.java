package com.bobac.microservice.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false"})
class OrderServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
