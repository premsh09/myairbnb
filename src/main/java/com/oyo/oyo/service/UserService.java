package com.oyo.oyo.service;

import com.auth0.jwt.JWT;
import com.oyo.oyo.dto.LoginDto;
import com.oyo.oyo.dto.PropertyUserDto;
import com.oyo.oyo.entity.PropertyUser;
import com.oyo.oyo.repository.PropertyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private JWTService jwtService;

    private PropertyUserRepository userRepository;

    public UserService(JWTService jwtService, PropertyUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public PropertyUser addUser(PropertyUserDto propertyUserDto) {
        PropertyUser user = new PropertyUser();
        user.setId(propertyUserDto.getId());
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setEmail(propertyUserDto.getEmail());
        user.setUsername(propertyUserDto.getUsername());
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(), BCrypt.gensalt(10)));
        user.setUserRole(propertyUserDto.getUserRole());
        PropertyUser savedUser = userRepository.save(user);
        return savedUser;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<PropertyUser> opUser = userRepository.findByUsername(loginDto.getUsername());
        if(opUser.isPresent()){
            PropertyUser propertyUser = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), propertyUser.getPassword())){
                return jwtService.generateToken(propertyUser);
            }
        }
        return null;
    }
}
