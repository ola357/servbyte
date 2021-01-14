package com.byteworks.servbyte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany
    private Set<Role> roles;

    public User(Long id) {
        setId(id);
    }


}
