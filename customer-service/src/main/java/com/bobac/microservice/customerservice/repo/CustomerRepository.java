package com.bobac.microservice.customerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bobac.microservice.customerservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
