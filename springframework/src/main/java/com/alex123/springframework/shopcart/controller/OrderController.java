package com.alex123.springframework.shopcart.controller;

import com.alex123.springframework.jwt.authenticate.AuthenticateRequest;
import com.alex123.springframework.jwt.authenticate.UserResponse;
import com.alex123.springframework.jwt.service.JwtService;
import com.alex123.springframework.log.entity.UserInfo;
import com.alex123.springframework.log.service.UserInfoUserDetailsService;
import com.alex123.springframework.qr.QRCodeGenerator;
import com.alex123.springframework.shopcart.dto.OrderRequest;
import com.alex123.springframework.shopcart.dto.OrderResponse;
import com.alex123.springframework.shopcart.entity.Customer;

import com.alex123.springframework.shopcart.repositories.CustomerRepository;
import com.alex123.springframework.shopcart.service.CustomerService;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/market")
public class OrderController {

    private final CustomerRepository customerRepository;
    private final UserInfoUserDetailsService service;

    private final CustomerService customerService;


    private final JwtService jwtService;
    private final UserResponse response;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public OrderController(CustomerRepository customerRepository, UserInfoUserDetailsService service, CustomerService customerService, JwtService jwtService, UserResponse response, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;

        this.service = service;
        this.customerService = customerService;

        this.jwtService = jwtService;
        this.response = response;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "This is not a secured endpoint.";
    }


    @PostMapping("/order")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public Customer placeOrder(@RequestBody OrderRequest request) {
        return customerRepository.save(request.getCustomer());
    }

    @PostMapping("/new/user")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return response.addUser(userInfo);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<Customer> findAllOrders() {
        return customerRepository.findAll();
    }

    @GetMapping("/orders/info")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<OrderResponse> getJoinInformation() {
        return customerRepository.getJoinInformation();
    }

    @GetMapping("/search/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<Customer> getById(@PathVariable int id) {

        return customerRepository.findAllById(id);
    }

    @GetMapping("/getQR")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getAllCustomerDetails() throws IOException, WriterException {
        List<Customer> customers = customerService.getCustomers();
        if (customers.size() != 0) {
            for (Customer customer : customers) {
                QRCodeGenerator.generateQRCode(customer);
            }
        }
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping("/staff/authenticate")
    public String authenticate(@RequestBody AuthenticateRequest authReq) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        if (auth.isAuthenticated()) {
            return jwtService.generateToken(authReq.getUsername());
        } else {
            throw new UsernameNotFoundException("Test !");
        }

    }
}
