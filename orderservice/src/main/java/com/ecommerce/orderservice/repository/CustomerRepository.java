package com.ecommerce.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.orderservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {}
