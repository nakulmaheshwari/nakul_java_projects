package com.nakul.HotelService.controllers;

import com.nakul.HotelService.Services.HotelService;
import com.nakul.HotelService.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @PostMapping("/")
    public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel)
    {
        Hotel u = this.hotelService.create(hotel);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllUser(){
        return ResponseEntity.ok(this.hotelService.getAll());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleUser(@PathVariable Integer hotelId){
        return ResponseEntity.ok(this.hotelService.getHotel(hotelId));
    }
}
