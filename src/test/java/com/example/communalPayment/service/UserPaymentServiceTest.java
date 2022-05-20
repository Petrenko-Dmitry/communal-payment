package com.example.communalPayment.service;

import com.example.communalPayment.dto.PaymentDto;
import com.example.communalPayment.entity.Payment;
import com.example.communalPayment.repository.PaymentRepository;
import com.example.communalPayment.repository.UserAddressRepository;
import com.example.communalPayment.utils.UserAddressUtils;
import com.example.communalPayment.utils.UserPaymentUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserPaymentServiceTest {

    private PaymentRepository paymentRepository;
    private UserTemplateService userTemplateService;
    private UserPaymentUtils userPaymentUtils;

    private UserPaymentService userPaymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        userTemplateService = mock(UserTemplateService.class);
        userPaymentUtils = mock(UserPaymentUtils.class);
        userPaymentService = new UserPaymentService(paymentRepository, userTemplateService, userPaymentUtils);
    }

    @Test
    void createNewPaymentIfTemplateNameIsNullTest() {
        //Arrange
        PaymentDto paymentDto = mock(PaymentDto.class);

        when(paymentDto.getTemplateName()).thenReturn(null);
        when(this.userTemplateService.getLastSavedTemplateName()).thenReturn("tempName");
        Payment payment = mock(Payment.class);
        when(this.userPaymentUtils.paymentDtoToPayment(paymentDto)).thenReturn(payment);
        when(this.paymentRepository.save(payment)).thenReturn(payment);
        when(this.userPaymentUtils.paymentToPaymentDto(payment)).thenReturn(paymentDto);

        //Act
        this.userPaymentService.createNewPayment(paymentDto);

        //Assert
        verify(paymentDto).getTemplateName();
        verify(this.userTemplateService).getLastSavedTemplateName();
        verify(this.userPaymentUtils).paymentDtoToPayment(paymentDto);
        verify(this.userPaymentUtils).paymentToPaymentDto(payment);
        verify(this.paymentRepository).save(payment);
    }

    @Test
    void createNewPaymentIfTemplateNameIsNonNullTest() {
        //Arrange
        PaymentDto paymentDto = mock(PaymentDto.class);

        when(paymentDto.getTemplateName()).thenReturn("temp");
        Payment payment = mock(Payment.class);
        when(this.userPaymentUtils.paymentDtoToPayment(paymentDto)).thenReturn(payment);
        when(this.paymentRepository.save(payment)).thenReturn(payment);
        when(this.userPaymentUtils.paymentToPaymentDto(payment)).thenReturn(paymentDto);

        //Act
        this.userPaymentService.createNewPayment(paymentDto);

        //Assert
        verify(paymentDto).getTemplateName();
        verify(this.userPaymentUtils).paymentDtoToPayment(paymentDto);
        verify(this.userPaymentUtils).paymentToPaymentDto(payment);
        verify(this.paymentRepository).save(payment);
    }
}