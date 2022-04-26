package br.com.nava.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
//Indicando que é um controle, automaticamente importa org.springframework
// Tipo de comunicação Rest
@RestController
public class PrimeiroController {
	//Associa o metodo com o navegador/Esta pegando uma informacao
	@GetMapping("bem-vindo-spring")
	public String bemvindo() {
		return "Bem vindo";
	}
	// Post envia os dados 
	@PostMapping("bem-vindo-spring")
	public String bemVindoPost() {
		return  "Bem vindo Post";
	}
	
		
}
