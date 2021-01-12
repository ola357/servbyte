package com.byteworks.servbyte.repository;

import com.byteworks.servbyte.model.Role;
import com.byteworks.servbyte.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleType(RoleType roleType);

}
