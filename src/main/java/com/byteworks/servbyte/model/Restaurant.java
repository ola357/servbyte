package com.byteworks.servbyte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant extends User {

    @Id
    @GeneratedValue
    private Long id;

    private String phoneNumber;

    private String name;

    @ManyToOne
    private City city;

    private String logoPicPath;

}
