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
	public String gotoIndex() {
		return "index";
	}


	@GetMapping("/connexion")
	public String gotoConnexion() {
		return "/connexion";
	}
}
