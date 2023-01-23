package com.nakul.UserService.controllers;

import com.nakul.UserService.entities.User;
import com.nakul.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User u = this.userService.saveUser(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @GetMapping("/{userId}")
    //@CircuitBreaker(name="ratingHotleBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUser(userId));
    }

    //creating fall back  method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        logger.info("Fallback is executed because service is down : ", ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some service is down")
                .userId(111)
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
