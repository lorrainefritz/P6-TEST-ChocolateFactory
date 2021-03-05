package com.chocolateFactory.Chocolate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolateFactory.Chocolate.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
	Role findByName(String name);
}
