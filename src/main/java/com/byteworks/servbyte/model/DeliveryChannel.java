package com.byteworks.servbyte.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeliveryChannel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(unique = true)
    private String channel;

    @ManyToMany
    private Set<DeliveryCompany> deliveryCompanies = new HashSet<>();

    public DeliveryChannel(String channel) {
        setChannel(channel);
    }

}
