package com.byteworks.servbyte.seed;

import com.byteworks.servbyte.model.City;
import com.byteworks.servbyte.model.DeliveryChannel;
import com.byteworks.servbyte.repository.CityRepository;
import com.byteworks.servbyte.repository.DeliveryChannelRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.iyanuadelekan.paystackjava.core.Customers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Data
@Component
@RequiredArgsConstructor
public class AppData implements CommandLineRunner {

    private final CityRepository cityRepository;

    private final DeliveryChannelRepository deliveryChannelRepository;


    @Override
    @SneakyThrows
    public void run(String... args) {

        cityRepository.saveAll(
                List.of("abuja", "lagos", "ibadan", "port-harcourt", "enugu", "asaba", "kano", "umuahia", "onitsha",
                        "aba", "owerri")
                        .stream()
                        .map(city -> City.builder().name(city).build())
                        .collect(Collectors.toList()));

        deliveryChannelRepository.saveAll(List.of("bike", "car", "boat")
                .stream()
                .map(channel -> DeliveryChannel.builder().channel(channel).build())
                .collect(Collectors.toList()));

    }
}
