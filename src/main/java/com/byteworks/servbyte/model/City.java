package com.byteworks.servbyte.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class City {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(unique = true)
    private String name;

}
