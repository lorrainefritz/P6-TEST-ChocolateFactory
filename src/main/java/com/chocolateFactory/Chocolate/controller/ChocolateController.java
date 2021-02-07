package com.chocolateFactory.Chocolate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.chocolateFactory.Chocolate.entities.Chocolate;
import com.chocolateFactory.Chocolate.repository.ChocolateRepository;
import com.chocolateFactory.Chocolate.service.ChocolateService;

@Controller
public class ChocolateController {
	@Autowired
	ChocolateService chocolateService;
	private final Logger logger = LoggerFactory.getLogger(ChocolateController.class);

	@GetMapping("/ajouterDesChocolats")
	public String showChocolateForm(Model model) {
		logger.info(" HTTP GET received at /ajouterDesChocolats");
		model.addAttribute("chocolate", new Chocolate());
		return "ajouterDesChocolats";
	}

	@PostMapping("/ajouterDesChocolats")
	public String submitChocolateForm(@Validated @ModelAttribute("chocolate") Chocolate chocolate,
			BindingResult bindingResult) {
		logger.info(" HTTP POST received at /ajouterDesChocolats");
		if (bindingResult.hasErrors()) {
			return "ajouterDesChocolats";
		} else {	
			logger.info(" On ajoute le chocolat avec les valeurs suivantes ; id : " +chocolate.getId() +" nom : "+chocolate.getName());
			chocolateService.addChocolate(chocolate);
		}
		return "redirect:/listeDesChocolats";
	}

	@GetMapping("/listeDesChocolats")
	public ModelAndView getChocolateList() {
		logger.info("HTTP GET received at /listeDesChocolats");
		String viewName = "listeDesChocolats";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("chocolates", chocolateService.getAllChocolates());
		return new ModelAndView(viewName, model);
	}
	
	@GetMapping(path="/editChocolate")
	public String editChocolate(Model model, Integer id) {
		logger.info("HTTP GET received at /editChocolate" + id);
		Chocolate chocolate = chocolateService.getOneChocolateById(id);
		model.addAttribute("chocolate",chocolate);
		return "ajouterDesChocolats";
	}
	
	
	@GetMapping("/deleteChocolate")
	public String deleteChocolate(Integer id) {
		logger.info("HTTP GET received at /deleteChocolate");
		chocolateService.deleteChocolate(id);
		 return "redirect:/listeDesChocolats";
	}

}
