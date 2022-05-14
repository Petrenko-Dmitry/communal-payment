package com.example.communalPayment.dto;

import com.example.communalPayment.entity.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {
    private Long id;

    private String templateName;
    private Long cardNumber;
    private Long paymentSum;

    private PaymentStatus paymentStatus;
    private Long dateCreation;
    private Long dateChange;
}
