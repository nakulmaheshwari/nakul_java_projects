package com.nakul.UserService.external.service;

import com.nakul.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;
import java.util.Objects;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    //get

    //POST

    @PostMapping("/rating/")
    public ResponseEntity<Rating> createRating(Rating values);


    //PUT
    @PutMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") Integer ratingId, Rating rating);


    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
