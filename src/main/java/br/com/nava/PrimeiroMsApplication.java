package br.com.nava;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.ProfessorRepository;
import br.com.nava.repositories.UsuarioRepository;
import br.com.nava.services.BDService;

@SpringBootApplication
//CommandLineRunner para conseguir rodar os comandos Java, sem precisar usar o Postman
public class PrimeiroMsApplication implements CommandLineRunner {
	
	// @Autowired vai gerenciar a instanciação(new) do ProfessorRepository ou injeção de dependencia
//	@Autowired
//	private ProfessorRepository professorRepository;
//
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//	@Autowired
//	private BDService bdService;
	
	public static void main(String[] args) {
		SpringApplication.run(PrimeiroMsApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		//bdService.inserirVendas();
//		// armazena na listaProfessor as informações da tabela PROFESSOR
//		List<ProfessorEntity> listaProfessor = professorRepository.findAll(); // fidAll() é o SELECT * FROM USUARIOS
//		// mostra a lista professor
//		for (int i = 0; i < listaProfessor.size(); i++) {
//			System.out.println(listaProfessor.get(i));
//		}
//		
//		// fidAll() é o SELECT * FROM USUARIOS
//		List<UsuarioEntity> listaUsuario = usuarioRepository.findAll();
//		// mostra a lista Usuario
//		for (int i=0; i < listaUsuario.size(); i++ ) {
//			System.out.println(listaUsuario.get(i));
//		}
//		
//		UsuarioEntity usuario = new UsuarioEntity();
//		usuario.setEmail("user@gmail.com");
//		usuario.setNome("User");
//		usuarioRepository.save(usuario);
	}
		
}
