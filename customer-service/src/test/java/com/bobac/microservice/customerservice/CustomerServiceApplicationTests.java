package com.bobac.microservice.customerservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false"})
class CustomerServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
