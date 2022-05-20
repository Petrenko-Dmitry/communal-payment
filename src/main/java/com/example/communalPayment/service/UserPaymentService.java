package com.example.communalPayment.service;

import com.example.communalPayment.dto.PaymentDto;
import com.example.communalPayment.entity.Payment;
import com.example.communalPayment.entity.PaymentStatus;
import com.example.communalPayment.repository.PaymentRepository;
import com.example.communalPayment.utils.UserPaymentUtils;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Payment> findAllByStatus(PaymentStatus paymentStatus) {
        return this.paymentRepository.findAllByPaymentStatus(paymentStatus);
    }

    public void saveAll(List<Payment> paymentsToSave) {
        this.paymentRepository.saveAll(paymentsToSave);
    }

    public PaymentDto createNewPayment(PaymentDto paymentDto) {
        var currentTimeMillis = System.currentTimeMillis();
        var dtoTemplateName = paymentDto.getTemplateName();
        paymentDto.setTemplateName(Strings.isNullOrEmpty(dtoTemplateName) ?
                this.userTemplateService.getLastSavedTemplateName() : dtoTemplateName);
        paymentDto.setDateChange(currentTimeMillis);
        paymentDto.setDateCreation(currentTimeMillis);
        paymentDto.setPaymentStatus(PaymentStatus.NEW);
        var payment = this.userPaymentUtils.paymentDtoToPayment(paymentDto);
        var savedPayment = this.paymentRepository.save(payment);
        return this.userPaymentUtils.paymentToPaymentDto(savedPayment);
    }
}
