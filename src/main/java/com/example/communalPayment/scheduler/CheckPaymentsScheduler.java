package com.example.communalPayment.scheduler;

import com.example.communalPayment.entity.Payment;
import com.example.communalPayment.entity.PaymentStatus;
import com.example.communalPayment.service.UserPaymentService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableAsync
@EnableScheduling
public class CheckPaymentsScheduler {

    private final UserPaymentService userPaymentService;

    private static final long THREE_SECOND_IN_MILLI = 3000;

    public CheckPaymentsScheduler(UserPaymentService userPaymentService) {
        this.userPaymentService = userPaymentService;
    }

    @Async
    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void checkNewPayment() {
        List<Payment> paymentWithNewStatus = this.userPaymentService.findAllByStatus(PaymentStatus.NEW);

        paymentWithNewStatus.forEach(payment -> {

            if ((payment.getDateChange() - payment.getDateCreation()) >= THREE_SECOND_IN_MILLI) {
                PaymentStatus.setRandomStatus(payment);
            }
            payment.setDateChange(System.currentTimeMillis());
        });
        this.userPaymentService.saveAll(paymentWithNewStatus);
    }
}
