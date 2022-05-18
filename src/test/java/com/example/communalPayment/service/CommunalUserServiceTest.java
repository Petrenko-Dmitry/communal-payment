package com.example.communalPayment.service;

import com.example.communalPayment.dto.UserDto;
import com.example.communalPayment.entity.CommunalUser;
import com.example.communalPayment.exception.EntityExistException;
import com.example.communalPayment.repository.CommunalUserRepository;
import com.example.communalPayment.utils.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommunalUserServiceTest {

    private CommunalUserRepository communalUserRepository;
    private UserUtils userUtils;
    private CommunalUserService communalUserService;

    @BeforeEach
    void setUp() {
        communalUserRepository = mock(CommunalUserRepository.class);
        userUtils = mock(UserUtils.class);
        communalUserService = new CommunalUserService(communalUserRepository, userUtils);
    }

    @Test
    void createNewUserIfExistTest() {
        UserDto userDto = mock(UserDto.class);
        String email = UUID.randomUUID().toString();
        when(userDto.getEmail()).thenReturn(email);
        CommunalUser communalUser = mock(CommunalUser.class);
        when(this.communalUserRepository.findByEmail(eq(email))).thenReturn(Optional.of(communalUser));

        Throwable throwable = catchThrowable(() -> this.communalUserService.createNewUser(userDto));

        verify(this.communalUserRepository).findByEmail(eq(email));
        verify(userDto, times(2)).getEmail();
        assertThat(throwable).isInstanceOf(EntityExistException.class).hasMessage("User with " + email + " email is already exist!");
    }

    @Test
    void createNewUserIfNotExistTest() {
        //Arrange
        UserDto userDto = mock(UserDto.class);
        String email = UUID.randomUUID().toString();
        when(userDto.getEmail()).thenReturn(email);
        when(this.communalUserRepository.findByEmail(eq(email))).thenReturn(Optional.empty());
        CommunalUser communalUser = mock(CommunalUser.class);
        when(this.userUtils.userDtoToCommunalUser(userDto)).thenReturn(communalUser);
        when(this.communalUserRepository.save(communalUser)).thenReturn(communalUser);
        UserDto userDto1 = mock(UserDto.class);
        when(this.userUtils.communalUserToUserDto(communalUser)).thenReturn(userDto1);

        //Act
        this.communalUserService.createNewUser(userDto);

        //Assert
        verify(userDto).getEmail();
        verify(this.communalUserRepository).findByEmail(eq(email));
        verify(this.userUtils).userDtoToCommunalUser(userDto);
        verify(this.communalUserRepository).save(communalUser);
        verify(this.userUtils).communalUserToUserDto(communalUser);
    }
}