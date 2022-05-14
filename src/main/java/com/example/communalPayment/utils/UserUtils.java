package com.example.communalPayment.utils;

import com.example.communalPayment.dto.UserDto;
import com.example.communalPayment.entity.CommunalUser;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    public UserDto communalUserToUserDto(CommunalUser communalUser) {
        return UserDto.builder()
                .email(communalUser.getEmail())
                .fio(communalUser.getFio())
                .phoneNumber(communalUser.getPhoneNumber())
                .build();
    }

    public CommunalUser userDtoToCommunalUser(UserDto communalUser) {
        return CommunalUser.builder()
                .email(communalUser.getEmail())
                .fio(communalUser.getFio())
                .phoneNumber(communalUser.getPhoneNumber())
                .build();
    }
}
