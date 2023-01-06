package com.mprog.fraud.service;

import com.mprog.fraud.model.FraudCheckHistory;
import com.mprog.repository.FraudCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .customerId(customerId)
                        .build()
        );
        return false;
    }
}
