package com.example.communalPayment.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDto {
    private Long id;

    private String address;
    private String userEmail;
}
