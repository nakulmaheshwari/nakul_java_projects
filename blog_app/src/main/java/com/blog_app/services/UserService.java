package com.blog_app.services;

import com.blog_app.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userI);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);



}
