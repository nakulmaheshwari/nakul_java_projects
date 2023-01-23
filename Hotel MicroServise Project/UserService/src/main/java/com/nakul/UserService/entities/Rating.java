package com.nakul.UserService.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Rating {
    private Integer ratingId;
    private Integer userId;
    private Integer hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
