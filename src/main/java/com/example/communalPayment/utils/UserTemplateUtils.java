package com.example.communalPayment.utils;

import com.example.communalPayment.dto.TemplateDto;
import com.example.communalPayment.entity.UserAddress;
import com.example.communalPayment.entity.UserTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserTemplateUtils {

    public TemplateDto userTemplateToTemplateDto(UserTemplate userTemplate) {
        return TemplateDto.builder()
                .id(userTemplate.getId())
                .templateName(userTemplate.getTemplateName())
                .iban(userTemplate.getIban())
                .paymentPurpose(userTemplate.getPaymentPurpose())
                .userAddressId(userTemplate.getUserAddress().getId())
                .build();
    }

    public UserTemplate templateDtoToUserTemplate(TemplateDto templateDto, UserAddress userAddress) {
        return UserTemplate.builder()
                .templateName(templateDto.getTemplateName())
                .iban(templateDto.getIban())
                .paymentPurpose(templateDto.getPaymentPurpose())
                .userAddress(userAddress)
                .build();
    }
}
