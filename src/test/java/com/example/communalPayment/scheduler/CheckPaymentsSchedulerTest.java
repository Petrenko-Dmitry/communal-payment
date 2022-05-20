package com.example.communalPayment.scheduler;

import com.example.communalPayment.entity.Payment;
import com.example.communalPayment.entity.PaymentStatus;
import com.example.communalPayment.service.UserPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckPaymentsSchedulerTest {

    private UserPaymentService userPaymentService;
    private CheckPaymentsScheduler checkPaymentsScheduler;

    @BeforeEach
    void setUp() {
        this.userPaymentService = mock(UserPaymentService.class);
        this.checkPaymentsScheduler = new CheckPaymentsScheduler(userPaymentService);
    }

    @Test
    void checkNewPaymentTest() {
        //Arrange
        Payment payment1 = Payment.builder().dateCreation(10000L).dateChange(12000L).paymentStatus(PaymentStatus.NEW).build();
        Payment payment2 = Payment.builder().dateCreation(10000L).dateChange(10000L).paymentStatus(PaymentStatus.NEW).build();
        Payment payment3 = Payment.builder().dateCreation(10000L).dateChange(15000L).paymentStatus(PaymentStatus.NEW).build();
        List<Payment> paymentsList = List.of(payment1, payment2, payment3);
        when(this.userPaymentService.findAllByStatus(PaymentStatus.NEW)).thenReturn(paymentsList);

        //Act
        this.checkPaymentsScheduler.checkNewPayment();

        //Assert
        assertNotEquals(12000L, payment1.getDateChange());
        assertNotEquals(10000L, payment2.getDateChange());
        assertNotEquals(15000L, payment3.getDateChange());
        verify(this.userPaymentService).saveAll(paymentsList);
    }
}