package com.alex123.springframework.shopcart.repositories;

import com.alex123.springframework.shopcart.dto.OrderResponse;
import com.alex123.springframework.shopcart.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT new com.alex123.springframework.shopcart.dto.OrderResponse (c.name , p.productName , p.price )FROM Customer c JOIN c.productList p")
    public List<OrderResponse> getJoinInformation();

    List<Customer> findAllById(int id);

}
