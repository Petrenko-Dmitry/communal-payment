package com.example.communalPayment.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CommunalUser {
    @Id
    private String email;
    private String fio;
    private Long phoneNumber;
}
