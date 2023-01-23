package com.nakul.HotelService.repositories;
import com.nakul.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Integer>{
}
