package com.mprog.customer.http.request;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
