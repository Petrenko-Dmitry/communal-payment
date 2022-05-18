package com.example.communalPayment.service;

import com.example.communalPayment.dto.AddressDto;
import com.example.communalPayment.entity.CommunalUser;
import com.example.communalPayment.entity.UserAddress;
import com.example.communalPayment.repository.UserAddressRepository;
import com.example.communalPayment.utils.UserAddressUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAddressServiceTest {

    private UserAddressRepository userAddressRepository;
    private CommunalUserService communalUserService;
    private UserAddressUtils userAddressUtils;
    private UserAddressService userAddressService;

    @BeforeEach
    void setUp() {
        userAddressRepository = mock(UserAddressRepository.class);
        communalUserService = mock(CommunalUserService.class);
        userAddressUtils = mock(UserAddressUtils.class);
        userAddressService = new UserAddressService(userAddressRepository, communalUserService, userAddressUtils);
    }

    @Test
    void createNewAddressTest() {
        //Arrange
        AddressDto addressDto = mock(AddressDto.class);
        CommunalUser userByEmail = mock(CommunalUser.class);
        String email = UUID.randomUUID().toString();
        when(addressDto.getUserEmail()).thenReturn(email);
        when(this.communalUserService.getUserByEmailIfExist(email)).thenReturn(userByEmail);
        UserAddress userAddress = mock(UserAddress.class);
        when(this.userAddressUtils.addressDtoToUserAddress(addressDto, userByEmail)).thenReturn(userAddress);
        UserAddress savedAddress = mock(UserAddress.class);
        when(this.userAddressRepository.save(userAddress)).thenReturn(savedAddress);
        AddressDto addressDtoToReturn = mock(AddressDto.class);
        when(this.userAddressUtils.userAddressToAddressDto(savedAddress)).thenReturn(addressDtoToReturn);

        //Act
        this.userAddressService.createNewAddress(addressDto);

        //Assert
        verify(addressDto).getUserEmail();
        verify(this.communalUserService).getUserByEmailIfExist(email);
        verify(this.userAddressUtils).addressDtoToUserAddress(addressDto, userByEmail);
        verify(this.userAddressRepository).save(userAddress);
        verify(this.userAddressUtils).userAddressToAddressDto(savedAddress);
    }
}