package com.alex123.springframework.shopcart.service;

import com.alex123.springframework.shopcart.entity.Customer;
import com.alex123.springframework.shopcart.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService( CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer findById(int id){
        return customerRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Not here/")
        );
    }

}
