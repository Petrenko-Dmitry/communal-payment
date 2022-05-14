package com.example.communalPayment.utils;

import com.example.communalPayment.dto.PaymentDto;
import com.example.communalPayment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class UserPaymentUtils {

    public PaymentDto paymentToPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .templateName(payment.getTemplateName())
                .cardNumber(payment.getCardNumber())
                .paymentSum(payment.getPaymentSum())
                .paymentStatus(payment.getPaymentStatus())
                .dateCreation(payment.getDateCreation())
                .dateChange(payment.getDateChange())
                .build();
    }

    public Payment paymentDtoToPayment(PaymentDto paymentDto) {
        return Payment.builder()
                .id(paymentDto.getId())
                .templateName(paymentDto.getTemplateName())
                .cardNumber(paymentDto.getCardNumber())
                .paymentSum(paymentDto.getPaymentSum())
                .paymentStatus(paymentDto.getPaymentStatus())
                .dateCreation(paymentDto.getDateCreation())
                .dateChange(paymentDto.getDateChange())
                .build();
    }
}
