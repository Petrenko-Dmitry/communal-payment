package com.example.communalPayment.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String templateName;
    private Long cardNumber;
    private Long paymentSum;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private Long dateCreation;
    private Long dateChange;
}
