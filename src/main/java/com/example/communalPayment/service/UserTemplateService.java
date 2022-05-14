package com.example.communalPayment.service;

import com.example.communalPayment.dto.TemplateDto;
import com.example.communalPayment.repository.TemplateRepository;
import com.example.communalPayment.utils.UserTemplateUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserTemplateService {

    private final UserAddressService userAddressService;
    private final TemplateRepository templateRepository;
    private final UserTemplateUtils userTemplateUtils;

    private final Map<String, String> inMemoryTemplateName = new HashMap<>();


    public UserTemplateService(UserAddressService userAddressService, TemplateRepository templateRepository, UserTemplateUtils userTemplateUtils) {
        this.userAddressService = userAddressService;
        this.templateRepository = templateRepository;
        this.userTemplateUtils = userTemplateUtils;
    }

    public TemplateDto createNewTemplate(TemplateDto templateDto) {
        var byId = this.userAddressService.findById(templateDto.getUserAddressId());
        var userTemplate = this.userTemplateUtils.templateDtoToUserTemplate(templateDto, byId);
        var savedTemplate = this.templateRepository.save(userTemplate);
        this.inMemoryTemplateName.put("Last template name", savedTemplate.getTemplateName());
        return this.userTemplateUtils.userTemplateToTemplateDto(savedTemplate);
    }

    public String getLastSavedTemplateName(){
        return this.inMemoryTemplateName.get("Last template name");
    }
}
