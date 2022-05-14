package com.example.communalPayment.controller;

import com.example.communalPayment.dto.AddressDto;
import com.example.communalPayment.dto.PaymentDto;
import com.example.communalPayment.dto.TemplateDto;
import com.example.communalPayment.dto.UserDto;
import com.example.communalPayment.entity.CommunalUser;
import com.example.communalPayment.service.CommunalUserService;
import com.example.communalPayment.service.UserAddressService;
import com.example.communalPayment.service.UserPaymentService;
import com.example.communalPayment.service.UserTemplateService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommunalPaymentController {

    private final CommunalUserService communalUserService;
    private final UserAddressService userAddressService;
    private final UserTemplateService userTemplateService;
    private final UserPaymentService userPaymentService;

    public CommunalPaymentController(CommunalUserService communalUserService,
                                     UserAddressService userAddressService,
                                     UserTemplateService userTemplateService,
                                     UserPaymentService userPaymentService) {
        this.communalUserService = communalUserService;
        this.userAddressService = userAddressService;
        this.userTemplateService = userTemplateService;
        this.userPaymentService = userPaymentService;
    }

    @PostMapping("/registryNewUser")
    public UserDto registryNewUser(@RequestBody UserDto userDto) {
        return this.communalUserService.createNewUser(userDto);
    }

    @GetMapping("/getUserByEmail")
    public CommunalUser getUserByEmail(@RequestParam String email) {
        return this.communalUserService.getUserByEmailIfExist(email);
    }

    @PostMapping("/createNewAddress")
    public AddressDto createNewAddress(@RequestBody AddressDto addressDto) {
        return this.userAddressService.createNewAddress(addressDto);
    }

    @PostMapping("/createNewTemplate")
    public TemplateDto createNewTemplate(@RequestBody TemplateDto templateDto) {
        return this.userTemplateService.createNewTemplate(templateDto);
    }

    @PostMapping("/createNewPayment")
    public PaymentDto createNewPayment(@RequestBody PaymentDto paymentDto) {
        return this.userPaymentService.createNewPayment(paymentDto);
    }
}
