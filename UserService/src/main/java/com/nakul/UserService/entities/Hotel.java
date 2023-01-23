package com.nakul.UserService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Hotel {
    private Integer hotelId;
    private String name;
    private String location;
    private String about;
}