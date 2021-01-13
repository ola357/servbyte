package com.byteworks.servbyte.service;

import com.byteworks.servbyte.model.DeliveryChannel;
import com.byteworks.servbyte.repository.DeliveryChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DeliveryCompanyService {
    private final DeliveryChannelRepository deliveryChannelRepository;

    public List<DeliveryChannel> getAllDeliveryChannels(){
        return deliveryChannelRepository.findAll();
    }

}
