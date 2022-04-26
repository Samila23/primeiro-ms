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

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.services.EnderecoService;
@RestController
@RequestMapping(value = "enderecos")
public class EnderecoController {
	 																			//	A anotação para não precisar usar o New, e consumir memoria apenas no momento devido
																				//	conhecido como injecao de dependencia
	@Autowired
	private EnderecoService enderecoService;
	//____________________________________________ Retorna todos os itens__________________________________________
	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll());
	}
	//____________________________________________ Retorna apenas 01, buscando pelo ID______________________________
																				//Retorno apenas 01 Endereco, identificado pelo ID, através  do metodo Get
	@GetMapping("{id}")
	public ResponseEntity<EnderecoDTO> getOne(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getOne(id));
	}
	//____________________________________________ Insere um novo item _____________________________________________
																				// Inclui um novo Endereco através do metodo POST
	@PostMapping
	public ResponseEntity<EnderecoDTO> save(@RequestBody EnderecoDTO endereco) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(endereco.toEntity()));
	}
	//____________________________________________ Altera um item, buscando pelo ID _________________________________
																				// Recebe um Id, verifica se existe e faz alteração através do metodo PATCH
	@PatchMapping(value = "{id}")
	public ResponseEntity<EnderecoDTO> update(@PathVariable int id, @RequestBody EnderecoDTO endereco) {
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.update(id, endereco.toEntity()));
	}
	//____________________________________________ Exclui um item, buscando pelo ID ___________________________________
																				// Localiza o professor pelo ID, e faz a exclusão do objeto, através do metodo DELETE
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable int id) {
		enderecoService.delete(id);
	}
}
