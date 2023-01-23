package com.nakul.RatingService.services.impl;

import com.nakul.RatingService.entities.Rating;
import com.nakul.RatingService.repository.RatingRepository;
import com.nakul.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository rr;
    @Override
    public Rating saveUser(Rating rating) {
        return rr.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return rr.findAll();
    }

    @Override
    public Rating getRating(Integer ratingId) {
        return rr.findById(ratingId).orElseThrow();
    }

    @Override
    public List<Rating> getByUserId(Integer userId) {
        return rr.findByUserId(userId);
    }

    @Override
    public List<Rating> getByHotelId(Integer hotelId)
    {
        return rr.findByHotelId(hotelId);
    };

}
