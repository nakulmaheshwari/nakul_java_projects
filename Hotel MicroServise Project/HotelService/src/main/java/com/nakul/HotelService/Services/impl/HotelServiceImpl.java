package com.nakul.HotelService.Services.impl;

import com.nakul.HotelService.Services.HotelService;
import com.nakul.HotelService.entities.Hotel;
import com.nakul.HotelService.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hr;
    @Override
    public Hotel create(Hotel hotel) {
        return hr.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hr.findAll();
    }

    @Override
    public Hotel getHotel(Integer id) {
        return hr.findById(id).orElseThrow();
    }
}
