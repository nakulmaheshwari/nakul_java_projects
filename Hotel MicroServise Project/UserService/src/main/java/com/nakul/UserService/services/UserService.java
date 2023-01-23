package com.nakul.UserService.services;

import com.nakul.UserService.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUser(Integer userId);



}
