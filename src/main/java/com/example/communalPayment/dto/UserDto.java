package com.example.communalPayment.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private String fio;
    private String email;
    private Long phoneNumber;
}
