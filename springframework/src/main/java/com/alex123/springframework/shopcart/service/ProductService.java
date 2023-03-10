package com.alex123.springframework.shopcart.service;




import com.alex123.springframework.shopcart.repositories.ProductRepository;
import org.springframework.stereotype.Service;



@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



}
