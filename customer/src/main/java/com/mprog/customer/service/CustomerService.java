package com.mprog.customer.service;

import com.mprog.customer.dto.FraudCheckResponse;
import com.mprog.customer.http.request.CustomerRegistrationRequest;
import com.mprog.customer.model.Customer;
import com.mprog.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;

    public void register(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse response = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (Objects.requireNonNull(response).isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }
}
