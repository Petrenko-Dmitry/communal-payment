package com.example.communalPayment.service;

import com.example.communalPayment.dto.UserDto;
import com.example.communalPayment.entity.CommunalUser;
import com.example.communalPayment.exception.EntityExistException;
import com.example.communalPayment.exception.NotFoundException;
import com.example.communalPayment.repository.CommunalUserRepository;
import com.example.communalPayment.utils.UserUtils;
import org.springframework.stereotype.Service;

@Service
public class CommunalUserService {

    private final CommunalUserRepository communalUserRepository;
    private final UserUtils userUtils;


    public CommunalUserService(CommunalUserRepository communalUserRepository, UserUtils userUtils) {
        this.communalUserRepository = communalUserRepository;
        this.userUtils = userUtils;
    }

    public CommunalUser getUserByEmailIfExist(String email) {
        return this.communalUserRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Can not find User with email: " + email));
    }

    public UserDto createNewUser(UserDto userDto) {
        var userOptional = this.communalUserRepository.findByEmail(userDto.getEmail());
        if (userOptional.isEmpty()) {
            var communalUser = this.userUtils.userDtoToCommunalUser(userDto);
            var savedUser = this.communalUserRepository.save(communalUser);
            return this.userUtils.communalUserToUserDto(savedUser);
        } else {
            throw new EntityExistException("User with " + userDto.getEmail() + " email is already exist!");
        }
    }
}