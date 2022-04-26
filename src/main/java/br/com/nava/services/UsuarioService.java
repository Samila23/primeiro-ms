package br.com.nava.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.UsuarioRepository;


//Informar que ela será injetada 
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void mostrar() {
		System.out.println("Mostrar");
	}
	//Retorna todos
	public List<UsuarioEntity> getAll(){
		return usuarioRepository.findAll();
	}
	//Retorna apenas 01, através do Id
	public UsuarioEntity getOne(int id) {
		Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
		UsuarioEntity usuario = optional.orElse(new UsuarioEntity());
		return usuario;
	}
	//Salvar 
	public UsuarioEntity save(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}
	//Update
	public UsuarioEntity update(int id, UsuarioEntity usuario) {
		Optional<UsuarioEntity> optional = usuarioRepository.findById(id);// Primeiro é verificar se existe no banco de dados
		if (optional.isPresent() == true){ // isPresent é o mesmo que contém, e está comparando se existe no banco de dados
			UsuarioEntity usuarioBD = optional.get();
			usuarioBD.setNome(usuario.getNome());
			usuarioBD.setEmail(usuario.getEmail());
			 
			return usuarioRepository.save(usuarioBD); 
		}
		else {
			return new UsuarioEntity(); // caso não exista no banco de dados  ele vai criar um novo objeto vario e retornar o msm 
		}
	}
	//Deletar
	public void delete(int id) {
		usuarioRepository.deleteById(id);
	}
}
