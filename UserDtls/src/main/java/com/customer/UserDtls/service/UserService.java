package com.customer.UserDtls.service;

import com.customer.UserDtls.exception.InvalidData;
import com.customer.UserDtls.model.UserData;
import com.customer.UserDtls.model.UserDto;

import java.util.List;

public interface UserService {
    UserData save(UserDto user) throws InvalidData;
    List<UserData> findAll();
    UserData findOne(String username);
    boolean findAtt(String username, String email, String phone);
    UserDto findByUsername(String usernameFromToken);
}
