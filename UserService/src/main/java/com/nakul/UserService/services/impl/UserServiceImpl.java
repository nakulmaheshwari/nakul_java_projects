package com.nakul.UserService.services.impl;

import com.nakul.UserService.entities.Hotel;
import com.nakul.UserService.entities.Rating;
import com.nakul.UserService.entities.User;
import com.nakul.UserService.exceptions.ResourceNotFoundException;
import com.nakul.UserService.repositories.UserRepository;
import com.nakul.UserService.services.UserService;
import com.nakul.UserService.external.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository ur;

    @Autowired
    private RestTemplate rt;

    @Autowired
    private HotelService hotelService;
    @Override
    public User saveUser(User user) {
        //UUID.randomUUID().toString();
        return ur.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return ur.findAll();
    }

    @Override
    public User getUser(Integer userId) {

        User u = this.ur.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
        Rating []rr=rt.getForObject("http://RATING-SERVICE/rating/user/"+u.getUserId(), Rating[].class);
        System.out.println(rr);
        List<Rating> r = Arrays.stream(rr).toList();

        List<Rating> list = r.stream().map(rating -> {

            //Hotel h = rt.getForObject("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel h = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(h);
            return rating;
        }).collect(Collectors.toList());
         u.setRatings(list);
        return u;
    }
}
