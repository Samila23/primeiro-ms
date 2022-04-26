package br.com.nava.controllers;

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

import br.com.nava.entities.VendasEntity;
import br.com.nava.services.VendasService;

@RestController
@RequestMapping("vendas")
public class VendasController {

	@Autowired
	private VendasService vendasService;
	
	
	//____________________________________________ Retorna todos os itens__________________________________________
																				//Retorno de todos os itens através do metodo Get
	@GetMapping
	public List<VendasEntity> getAll(){
		return this.vendasService.getAll();
	}
	
	
	//____________________________________________ Retorna apenas 01, buscando pelo ID______________________________
	 																			//Retorno apenas 01 Produto, identificado pelo ID, através  do metodo Get
	@GetMapping("{id}")
	public VendasEntity getOne(@PathVariable int id) {
		return vendasService.getOne(id);
	}
	
	
	//____________________________________________ Insere um novo item _____________________________________________
																				//inclui um novo Produto através do metodo POST
	@PostMapping
	public VendasEntity save(@RequestBody VendasEntity venda) {
		return vendasService.save(venda);
	}
	
	
	//____________________________________________ Altera um item, buscando pelo ID _________________________________
																				// Recebe um Id, verifica se existe e faz alteração através do metodo PATCH
	@PatchMapping("{id}")
	public VendasEntity update(@PathVariable int id, 
			@RequestBody VendasEntity venda) {
		return vendasService.update(id, venda);
	}
	
	
	//____________________________________________ Exclui um item, buscando pelo ID ___________________________________
																				// Localiza o professor pelo ID, e faz a exclusão do objeto, através do metodo DELETE
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		vendasService.delete(id);
	}
}
