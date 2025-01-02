package com.felisberto.hrpayroll.services;

import com.felisberto.hrpayroll.entities.Payment;
import com.felisberto.hrpayroll.entities.Worker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    public Payment getPayment(Long workerId, int days) {
        return new Payment("Francisco", 200.0, days);
    }
}
