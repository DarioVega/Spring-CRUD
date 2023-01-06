package com.example.CrudUser.model.service;

import com.example.CrudUser.dto.SuccesfullyCreatedDto;
import com.example.CrudUser.dto.SuccesfullyDeletedDto;
import com.example.CrudUser.dto.UserDto;
import com.example.CrudUser.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserDto> getUsers();

    UserDto getUserById(Long id);

    SuccesfullyCreatedDto createUser(UserDto userDto);

    SuccesfullyDeletedDto deleteUserById(Long id);

    Optional<ResponseEntity<UserDto>> updateUser(Long id, UserDto userDto);


}
