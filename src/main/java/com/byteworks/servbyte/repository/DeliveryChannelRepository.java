package com.byteworks.servbyte.repository;

import com.byteworks.servbyte.model.DeliveryChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DeliveryChannelRepository extends JpaRepository<DeliveryChannel, Long> {

}
