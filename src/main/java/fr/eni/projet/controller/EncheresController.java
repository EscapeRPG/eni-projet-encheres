package fr.eni.projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class EncheresController {
	
//	private EncheresService encheresService;
//
//	public EncheresController(EncheresService encheresService) {
//		this.encheresService = encheresService;
//	}
	
	@GetMapping("/")
	public String goToIndex() {
		return "index";
	}

	@GetMapping("/inscription")
	public String inscription() {
		return "inscription";
	}
	

}
