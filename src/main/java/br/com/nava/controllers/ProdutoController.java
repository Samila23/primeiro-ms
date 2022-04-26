package br.com.nava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.services.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	//____________________________________________ Retorna todos os itens__________________________________________
																					//Retorno de todos os itens através do metodo Get
	@GetMapping
	public List<ProdutoEntity> getAll(){
		return this.produtoService.getAll();
	}
	
	//____________________________________________ Retorna apenas 01, buscando pelo ID______________________________
																					//Retorno apenas 01 Produto, identificado pelo ID, através  do metodo Get
	@GetMapping("{id}")
	public ProdutoEntity getOne(@PathVariable int id) {
		return produtoService.getOne(id);
	}
	
	//____________________________________________ Insere um novo item _____________________________________________
																					//inclui um novo Produto através do metodo POST
	@PostMapping
	public ProdutoEntity save(@RequestBody ProdutoEntity produto) {
		return produtoService.save(produto);
	}
	
	//____________________________________________ Altera um item, buscando pelo ID _________________________________
																					// Recebe um Id, verifica se existe e faz alteração através do metodo PATCH
	@PatchMapping("{id}")
	public ProdutoEntity update(@PathVariable int id, 
			@RequestBody ProdutoEntity produto) {
		return produtoService.update(id, produto);
	}
	
	//____________________________________________ Exclui um item, buscando pelo ID ___________________________________
																					// Localiza o professor pelo ID, e faz a exclusão do objeto, através do metodo DELETE
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		produtoService.delete(id);
	}
}
