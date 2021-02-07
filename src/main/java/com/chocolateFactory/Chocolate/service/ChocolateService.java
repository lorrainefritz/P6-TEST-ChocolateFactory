package com.chocolateFactory.Chocolate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolateFactory.Chocolate.controller.ChocolateController;
import com.chocolateFactory.Chocolate.entities.Chocolate;
import com.chocolateFactory.Chocolate.repository.ChocolateRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChocolateService {
	private final Logger logger = LoggerFactory.getLogger(ChocolateController.class);
	@Autowired
	ChocolateRepository chocolateRepository;
	
	public List <Chocolate> getAllChocolates(){
		List <Chocolate> listOfChocolates = chocolateRepository.findAll(); 
		return listOfChocolates;
	}
	
	public int getChocolateListSize() {
		logger.info("in ChocolateService GetChocolateListSize, et chocolateListSize = " + getAllChocolates().size());
		return getAllChocolates().size();
	}
	
	public Chocolate getOneChocolateById(Integer id) {
		logger.info("in ChocolateService GetOneChocolateById");
		return chocolateRepository.getOne(id);
	}
	

//	public Chocolate findOneChocolateById(Integer id) {
//		logger.info("in ChocolateService GetOneChocolateById");
//		return chocolateRepository.findById(id).get();
//	}
	
	public Chocolate addChocolate(Chocolate choco) {
		logger.info("in ChocolateService addChocolate");
		return chocolateRepository.save(choco);
	}
	
	public void deleteChocolate(Integer id){
		logger.info("in ChocolateService deleteChocolate");
		chocolateRepository.deleteById(id);
	}
	
	
}
