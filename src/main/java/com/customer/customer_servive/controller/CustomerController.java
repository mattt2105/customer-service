package com.customer.customer_servive.controller;

import com.customer.customer_servive.dto.CustomerDTO;
import com.customer.customer_servive.dto.UpdatePasswordDTO;
import com.customer.customer_servive.model.Customer;
import com.customer.customer_servive.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping("/customer/create")
    public ResponseEntity<Customer> createCustomer (@RequestBody CustomerDTO dto){
        return service.createCustomer(dto);
    }

    @PutMapping("/customer/update/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO dto){
        return service.updateCustomer(id,dto);
    }

    @GetMapping("/customer/get/by/id/{id}")
    public ResponseEntity<CustomerDTO> getDataById(@PathVariable Long id){
        return service.getDataById(id);
    }

    @GetMapping("/customer/get/by/email/{email}")
    public ResponseEntity<CustomerDTO> getDataByEmail(@PathVariable String email){
        return service.getDataByEmail(email);
    }

    @PutMapping("/customer/update/password/{id}")
    public ResponseEntity<UpdatePasswordDTO> updateCustomerPassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordDTO dto){
        return service.updatePassword(id,dto);
    }

}
