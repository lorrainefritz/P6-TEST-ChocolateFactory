package com.chocolateFactory.Chocolate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolateFactory.Chocolate.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

//	User getUserByEmail(String email);


}
