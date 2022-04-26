package br.com.nava.controllers;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.services.UsuarioService;
@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	//____________________________________________ Retorna todos os itens__________________________________________
	@GetMapping("")
	public List<UsuarioEntity> getAll() {
		return usuarioService.getAll();
	}
	
	
	//____________________________________________ Retorna apenas 01, buscando pelo ID______________________________
	@GetMapping("{id}")
	
	public UsuarioEntity getOne(@PathVariable Integer id) {							// o ID entre {} e o comentário @PathVariable, indica que o Id é variavel 
		return usuarioService.getOne(id);
	}
	
	
	//____________________________________________ Insere um novo item _____________________________________________
	@PostMapping ("")
	
	public UsuarioEntity save(@RequestBody UsuarioEntity usuario) {					//fazendo mapeamento do que está vindo pelo corpo Body @RequestBody
		return usuarioService.save(usuario);
	}
	
	
	//____________________________________________ Altera um item, buscando pelo ID _________________________________
	@PatchMapping("{id}")
	public UsuarioEntity update(@PathVariable int id, @RequestBody UsuarioEntity usuario) {
		return usuarioService.update(id, usuario);
	}
	
	
	//____________________________________________ Exclui um item, buscando pelo ID ___________________________________
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		usuarioService.delete(id);
	}
}
