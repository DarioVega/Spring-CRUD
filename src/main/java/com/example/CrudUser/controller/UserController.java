package com.example.CrudUser.controller;

import com.example.CrudUser.dto.SuccesfullyCreatedDto;
import com.example.CrudUser.dto.SuccesfullyDeletedDto;
import com.example.CrudUser.dto.UserDto;
import com.example.CrudUser.model.service.IUserService;
import com.example.CrudUser.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccesfullyCreatedDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(id,userDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccesfullyDeletedDto> deleteUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }

}
