package com.blog_app.services.impl;

import com.blog_app.config.AppConstants;
import com.blog_app.entities.Role;
import com.blog_app.entities.User;
import com.blog_app.exceptions.ResourceNotFoundException;
import com.blog_app.payloads.UserDto;
import com.blog_app.repositories.RoleRepo;
import com.blog_app.repositories.UserRepo;
import com.blog_app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepo userRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo ur;

    @Autowired
    private ModelMapper mm;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.mm.map(userDto, User.class);

        // encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = this.userRepo.save(user);

        return this.mm.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto user) {
        return this.userToDto(ur.save(dtoToUser(user)));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.ur.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return this.userToDto(ur.save(user));
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.ur.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> u = this.ur.findAll();
        List<UserDto> U = u.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return U;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.ur.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
        this.ur.delete(user);
    }
    User dtoToUser(UserDto u)
    {
        User U = this.mm.map(u,User.class);
        //U.setId(u.getId());
        //U.setAbout(u.getAbout());
        //U.setEmail(u.getEmail());
        //U.setPassword(u.getPassword());
        //U.setName(u.getName());

        return U;
    }
    UserDto userToDto(User u)
    {
        UserDto U = this.mm.map(u, UserDto.class);
        return U;
    }

}
