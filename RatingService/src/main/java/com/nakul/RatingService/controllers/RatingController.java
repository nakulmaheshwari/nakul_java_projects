package com.nakul.RatingService.controllers;

import com.nakul.RatingService.entities.Rating;
import com.nakul.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingSERVICE;
    @PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
    {
        Rating u = this.ratingSERVICE.saveUser(rating);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAllUser(){

        return ResponseEntity.ok(this.ratingSERVICE.getAllRating());
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getSingleRating(@PathVariable Integer ratingId){
        return ResponseEntity.ok(this.ratingSERVICE.getRating(ratingId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUId(@PathVariable Integer userId){
        return ResponseEntity.ok(this.ratingSERVICE.getByUserId(userId));
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHId(@PathVariable Integer hotelId){
        return ResponseEntity.ok(this.ratingSERVICE.getByUserId(hotelId));
    }

}
