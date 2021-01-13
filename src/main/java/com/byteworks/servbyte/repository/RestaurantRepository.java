package com.byteworks.servbyte.repository;

import com.byteworks.servbyte.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findRestaurantsByCity_Name(String cityName);


}
