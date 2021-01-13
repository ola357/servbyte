package com.byteworks.servbyte.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String mealPicPath;

    private Float price;

    private LocalTime cookTime;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String description;

    @ManyToOne
    private Restaurant restaurant;

}
