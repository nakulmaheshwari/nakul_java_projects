package com.nakul.RatingService.services;

import com.nakul.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating saveUser(Rating rating);
    List<Rating> getAllRating();
    Rating getRating (Integer ratingId);
    List<Rating> getByHotelId(Integer hotelId);
    List<Rating> getByUserId(Integer userId);
}
