package com.nakul.HotelService.Services;

import com.nakul.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel getHotel(Integer id);
}
