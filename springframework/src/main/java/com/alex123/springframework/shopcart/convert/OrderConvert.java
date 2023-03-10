package com.alex123.springframework.shopcart.convert;

import com.alex123.springframework.shopcart.dto.OrderRequest;
import com.alex123.springframework.shopcart.entity.Customer;

public class OrderConvert {

    public static OrderRequest convertToEntity (Customer customer){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomer(new Customer());
        return orderRequest;
    }

}
