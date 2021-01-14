package com.byteworks.servbyte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryCompany extends User {
    @Id
    @GeneratedValue
    private Long id;

    private String phoneNumber;

    private String name;

    @OneToMany
    private Set<City> cities = new HashSet<>();

    @ManyToMany
    private Set<DeliveryChannel> deliveryChannels = new HashSet<>();

    private String logoPicPath;

}
