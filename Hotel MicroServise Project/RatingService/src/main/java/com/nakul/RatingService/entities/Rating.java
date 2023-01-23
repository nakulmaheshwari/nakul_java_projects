package com.nakul.RatingService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="micro_ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ratingId;
    private Integer userId;
    private Integer hotelId;
    private int rating;
    private String feedback;
}
