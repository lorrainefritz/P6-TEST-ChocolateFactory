package com.chocolateFactory.Chocolate.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolateFactory.Chocolate.entities.Chocolate;
@Repository
public interface ChocolateRepository extends JpaRepository<Chocolate, Integer> {

	
}
