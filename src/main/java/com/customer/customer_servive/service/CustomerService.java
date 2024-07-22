package com.customer.customer_servive.service;

import com.customer.customer_servive.dto.CustomerDTO;
import com.customer.customer_servive.dto.UpdatePasswordDTO;
import com.customer.customer_servive.model.Customer;
import com.customer.customer_servive.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;


    public ResponseEntity<Customer> createCustomer (CustomerDTO dto){
        if (repository.findByEmail(dto.getEmail()).isPresent()){
            return new ResponseEntity("Email already exist", HttpStatus.BAD_REQUEST);
        }
        Customer newCustomer = new Customer();

        newCustomer.setName(dto.getName());
        newCustomer.setEmail(dto.getEmail());
        newCustomer.setPassword(dto.getPassword());
        newCustomer.setPhone(dto.getPhone());
        newCustomer.setAddress(dto.getAddress());

        repository.save(newCustomer);

        return new ResponseEntity(dto, HttpStatus.OK);
    }

    public ResponseEntity<Customer> updateCustomer (Long id, CustomerDTO dto){
        Optional<Customer> existingData = repository.findById(id);

        if (existingData.isPresent()){
            Customer updateData = existingData.get();

            updateData.setName(dto.getName());
            updateData.setEmail(dto.getEmail());
            updateData.setPassword(dto.getPassword());
            updateData.setPhone(dto.getPhone());
            updateData.setAddress(dto.getAddress());

            repository.save(updateData);

            return new ResponseEntity(dto, HttpStatus.OK);
        }else{
            return new ResponseEntity("account doesnt exist", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CustomerDTO> getDataById (Long id){
        Optional<Customer> existingData = repository.findById(id);

        if (existingData.isPresent()){
            CustomerDTO dto = new CustomerDTO();
            Customer data = existingData.get();

            dto.setName(data.getName());
            dto.setEmail(data.getEmail());
            dto.setPassword(data.getPassword());
            dto.setPhone(data.getPhone());
            dto.setAddress(data.getAddress());

            return new ResponseEntity(dto, HttpStatus.OK);
        }else{
            return new ResponseEntity("account doesnt exist", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CustomerDTO> getDataByEmail (String email){
        Optional<Customer> existingData = repository.findByEmail(email);

        if (existingData.isPresent()){
            CustomerDTO dto = new CustomerDTO();
            Customer data = existingData.get();

            dto.setName(data.getName());
            dto.setEmail(data.getEmail());
            dto.setPassword(data.getPassword());
            dto.setPhone(data.getPhone());
            dto.setAddress(data.getAddress());

            return new ResponseEntity(dto, HttpStatus.OK);
        }else{
            return new ResponseEntity("account doesnt exist", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UpdatePasswordDTO> updatePassword (Long id, UpdatePasswordDTO dto){
        Optional<Customer> existingData = repository.findById(id);

        if (existingData.isPresent()){
            Customer data = existingData.get();

            if (dto.getOldPassword().equals(data.getPassword())){
                data.setPassword(dto.getNewPassword());

                repository.save(data);

                return new ResponseEntity("Password updated", HttpStatus.OK);
            }else {
                return new ResponseEntity("Password doesnt match", HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity("account doesnt exist", HttpStatus.BAD_REQUEST);
        }
    }
}
