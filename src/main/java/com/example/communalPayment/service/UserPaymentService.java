package com.example.communalPayment.service;

import com.example.communalPayment.dto.PaymentDto;
import com.example.communalPayment.entity.PaymentStatus;
import com.example.communalPayment.repository.PaymentRepository;
import com.example.communalPayment.utils.UserPaymentUtils;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentService {

    private final PaymentRepository paymentRepository;
    private final UserTemplateService userTemplateService;
    private final UserPaymentUtils userPaymentUtils;

    public UserPaymentService(PaymentRepository paymentRepository, UserTemplateService userTemplateService, UserPaymentUtils userPaymentUtils) {
        this.paymentRepository = paymentRepository;
        this.userTemplateService = userTemplateService;
        this.userPaymentUtils = userPaymentUtils;
    }

    public PaymentDto createNewPayment(PaymentDto paymentDto) {
        var currentTimeMillis = System.currentTimeMillis();

        paymentDto.setTemplateName(Strings.isNullOrEmpty(paymentDto.getTemplateName()) ?
                this.userTemplateService.getLastSavedTemplateName() : paymentDto.getTemplateName());
        paymentDto.setDateChange(currentTimeMillis);
        paymentDto.setDateCreation(currentTimeMillis);
        paymentDto.setPaymentStatus(PaymentStatus.NEW);
        var payment = this.userPaymentUtils.paymentDtoToPayment(paymentDto);
        var savedPayment = this.paymentRepository.save(payment);
        return this.userPaymentUtils.paymentToPaymentDto(savedPayment);
    }
}
