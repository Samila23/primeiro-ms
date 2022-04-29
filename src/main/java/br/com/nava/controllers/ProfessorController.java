package br.com.nava.controllers;

//import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.services.ProfessorService;

@RestController
@RequestMapping("professores")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessorController {

																					//	conhecido como injecao de dependencia
	@Autowired																		//	A anotação para não precisar usar o New, e consumir memoria apenas no momento devido
	private ProfessorService professorService;
	
	//____________________________________________ Retorna todos os itens__________________________________________
																					//Retorno de todos os itens através do metodo Get
	@GetMapping()
	public ResponseEntity<List<ProfessorDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
	}
	
	
	//____________________________________________ Retorna apenas 01, buscando pelo ID______________________________
																					//Retorno apenas 01 professor, identificado pelo ID, através  do metodo Get
	@GetMapping("{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getOne(id));
	}
	
	
	//____________________________________________ Insere um novo item _____________________________________________
																					//inclui um novo Professor através do metodo POST
	@PostMapping()
	public ResponseEntity<ProfessorDTO> save(@Valid @RequestBody ProfessorDTO professor) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professor.toEntity()));
	}
	
	
	//____________________________________________ Altera um item, buscando pelo ID _________________________________
																					// Recebe um Id, verifica se existe e faz alteração através do metodo PATCH
	@PatchMapping("{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable int id, @RequestBody ProfessorDTO professor) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.update(id, professor.toEntity()));
	}
	
	
	//____________________________________________ Exclui um item, buscando pelo ID ___________________________________
																					// Localiza o professor pelo ID, e faz a exclusão do objeto, através do metodo DELETE
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		professorService.delete(id);
		return ResponseEntity.ok().build();
	}
	//____________________________________________ Retorna todos / Aplicando filtros __________________________________________
	@GetMapping(value = "search-by-name/{name}")
	// https://localhost:8080/professores/search-by-name/fab
	public ResponseEntity<List<ProfessorDTO>> searchByName(@PathVariable String name){
		List<ProfessorDTO> lista = professorService.searchByName(name);
		return ResponseEntity.ok().body( lista );
		// return ResponseEntity.ok().body( professorService.searchByName(name) );
	}
}