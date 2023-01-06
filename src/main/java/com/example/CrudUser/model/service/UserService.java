package com.example.CrudUser.model.service;

import com.example.CrudUser.dto.SuccesfullyCreatedDto;
import com.example.CrudUser.dto.SuccesfullyDeletedDto;
import com.example.CrudUser.dto.UserDto;
import com.example.CrudUser.model.entity.User;
import com.example.CrudUser.model.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IUserService{
        private IUserRepository userRepository;

        public UserService(IUserRepository userRepository){
            this.userRepository = userRepository;
        }


    @Override
    public List<UserDto> getUsers() {
        ModelMapper mapper = new ModelMapper();
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.stream().forEach(c -> userDtos.add(mapper.map(c,UserDto.class)));

        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) {
            Optional<User> user = userRepository.findById(id);
            return new UserDto(user.get().getName(), user.get().getLastName(), user.get().getNationality());
    }

    @Override
    public SuccesfullyCreatedDto createUser(UserDto userDto) {
        //transformar el userDto en User
        User user = new User(userDto.getName(),userDto.getLastName(), userDto.getNationality());
        //llamar un método del repositorio para guardar al user
        userRepository.save(user);
        //crear respuesta
        return new SuccesfullyCreatedDto("User succesfully created!!!");
    }



    @Override
    public SuccesfullyDeletedDto deleteUserById(Long id) {
        userRepository.deleteById(id);
        SuccesfullyDeletedDto succesfullyDeletedDto = new SuccesfullyDeletedDto("User succesfully deleted!!!");
        return succesfullyDeletedDto;
    }

    @Override
    public Optional<ResponseEntity<UserDto>> updateUser(Long id, UserDto userDto) {
                return userRepository.findById(id)
                        .map(u->{
                            u.setName(userDto.getName());
                            u.setLastName(userDto.getLastName());
                            u.setNationality(userDto.getNationality());

                            User updatedUser = userRepository.save(u);
                            UserDto updatedUserDto = new UserDto(updatedUser.getName(),updatedUser.getLastName(),updatedUser.getNationality());
                            return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
                        });
    }


}
