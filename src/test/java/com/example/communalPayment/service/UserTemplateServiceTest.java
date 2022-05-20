package com.example.communalPayment.service;

import com.example.communalPayment.dto.TemplateDto;
import com.example.communalPayment.entity.UserAddress;
import com.example.communalPayment.entity.UserTemplate;
import com.example.communalPayment.repository.TemplateRepository;
import com.example.communalPayment.utils.UserTemplateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTemplateServiceTest {

    private UserAddressService userAddressService;
    private TemplateRepository templateRepository;
    private UserTemplateUtils userTemplateUtils;

    private UserTemplateService userTemplateService;

    @BeforeEach
    void setUp() {
        this.userAddressService = mock(UserAddressService.class);
        this.templateRepository = mock(TemplateRepository.class);
        this.userTemplateUtils = mock(UserTemplateUtils.class);
        this.userTemplateService = new UserTemplateService(userAddressService, templateRepository, userTemplateUtils);
    }

    @Test
    void createNewTemplateTest() {
        //Assert
        TemplateDto templateDto = mock(TemplateDto.class);
        UserAddress userAddress = mock(UserAddress.class);
        long addressId = 1L;
        when(templateDto.getUserAddressId()).thenReturn(addressId);
        when(this.userAddressService.findById(addressId)).thenReturn(userAddress);
        UserTemplate userTemplate = mock(UserTemplate.class);
        when(this.userTemplateUtils.templateDtoToUserTemplate(templateDto, userAddress)).thenReturn(userTemplate);
        when(this.templateRepository.save(userTemplate)).thenReturn(userTemplate);
        when(this.userTemplateUtils.userTemplateToTemplateDto(userTemplate)).thenReturn(templateDto);

        //Act
        this.userTemplateService.createNewTemplate(templateDto);

        //Assert
        verify(templateDto).getUserAddressId();
        verify(this.userAddressService).findById(addressId);
        verify(this.userTemplateUtils).templateDtoToUserTemplate(templateDto, userAddress);
        verify(this.userTemplateUtils).userTemplateToTemplateDto(userTemplate);
        verify(this.templateRepository).save(userTemplate);
    }
}