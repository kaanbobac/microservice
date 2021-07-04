package com.bobac.microservice.customerservice.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bobac.microservice.customerservice.model.Customer;
import com.bobac.microservice.customerservice.model.Order;
import com.bobac.microservice.customerservice.service.CustomerService;
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class QueryCustomerOrderApiTest {
	private static final Long CUSTOMER_ID_EXIST =1L;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;
	@BeforeAll
	void setUp() {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(1L,1L,"first order","NEW"));
		orders.add(new Order(2L,1L,"second order","NEW"));
		when(customerService.queryCustomerDb(CUSTOMER_ID_EXIST)).thenReturn(
				new Customer(CUSTOMER_ID_EXIST,"test customer", "test environment"));
		when(customerService.callOrderService(CUSTOMER_ID_EXIST)).thenReturn(
				orders);
	}
	@Test
	void queryCustomerOrder() throws Exception {
		mockMvc.perform(get("/customer/"+String.valueOf(CUSTOMER_ID_EXIST)+"/order"))
		.andExpect(status().isOk());

	}
}
