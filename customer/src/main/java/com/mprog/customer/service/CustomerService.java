package com.mprog.customer.service;

import com.mprog.customer.http.request.CustomerRegistrationRequest;
import com.mprog.customer.model.Customer;
import com.mprog.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void register(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.save(customer);
    }
}
