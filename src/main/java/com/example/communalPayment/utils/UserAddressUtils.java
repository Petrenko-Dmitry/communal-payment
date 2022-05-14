package com.example.communalPayment.utils;

import com.example.communalPayment.dto.AddressDto;
import com.example.communalPayment.entity.CommunalUser;
import com.example.communalPayment.entity.UserAddress;
import org.springframework.stereotype.Component;

@Component
public class UserAddressUtils {

    public AddressDto userAddressToAddressDto(UserAddress userAddress) {
        return AddressDto.builder()
                .id(userAddress.getId())
                .address(userAddress.getAddress())
                .userEmail(userAddress.getCommunalUser().getEmail())
                .build();
    }

    public UserAddress addressDtoToUserAddress(AddressDto addressDto, CommunalUser communalUser) {
        return UserAddress.builder()
                .address(addressDto.getAddress())
                .communalUser(communalUser)
                .build();
    }
}
