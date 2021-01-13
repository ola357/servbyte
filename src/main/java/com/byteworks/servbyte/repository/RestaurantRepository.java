package com.byteworks.servbyte.repository;

import com.byteworks.servbyte.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
