package com.example.communalPayment.service;

import com.example.communalPayment.dto.AddressDto;
import com.example.communalPayment.entity.UserAddress;
import com.example.communalPayment.repository.UserAddressRepository;
import com.example.communalPayment.utils.UserAddressUtils;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;
    private final CommunalUserService communalUserService;
    private final UserAddressUtils userAddressUtils;

    public UserAddressService(UserAddressRepository userAddressRepository, CommunalUserService communalUserService, UserAddressUtils userAddressUtils) {
        this.userAddressRepository = userAddressRepository;
        this.communalUserService = communalUserService;
        this.userAddressUtils = userAddressUtils;
    }

    public UserAddress findById(Long id) {
        return this.userAddressRepository.findById(id).orElseThrow();
    }

    public AddressDto createNewAddress(AddressDto addressDto) {
        var userByEmail = this.communalUserService.getUserByEmailIfExist(addressDto.getUserEmail());
        var userAddress = this.userAddressUtils.addressDtoToUserAddress(addressDto, userByEmail);
        var savedAddress = this.userAddressRepository.save(userAddress);
        return this.userAddressUtils.userAddressToAddressDto(savedAddress);
    }
}
