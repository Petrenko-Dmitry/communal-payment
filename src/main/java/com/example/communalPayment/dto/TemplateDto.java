package com.example.communalPayment.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TemplateDto {
    private Long id;

    private String templateName;
    private String iban;
    private String paymentPurpose;
    private Long userAddressId;
}
