package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.entities.ProfessorEntity;
import br.com.nava.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;
	//__________________________________________ Mostrar Todos____________________________________________________
	public List<EnderecoDTO> getAll(){
		List<EnderecoEntity> lista =  enderecoRepository.findAll();
		List<EnderecoDTO> listaDTO = new ArrayList<>();
		
		for (EnderecoEntity enderecoEntity : lista) {
			listaDTO.add(enderecoEntity.toTDO());
		}
		return listaDTO;
	}
	//__________________________________________ Mostrar apenas 01_________________________________________________
	public EnderecoDTO getOne(int id) {
		Optional<EnderecoEntity> optional = enderecoRepository.findById(id);
		EnderecoEntity endereco = optional.orElse( new EnderecoEntity() );
		return endereco.toTDO();
	}
	//__________________________________________ INSERIR um novo ___________________________________________________
	public EnderecoDTO save(EnderecoEntity endereco) {		
		return enderecoRepository.save(endereco).toTDO();
	}
	//__________________________________________ Atualizando os dados / UPDATE______________________________________
	public EnderecoDTO update(int id, EnderecoEntity novoEndereco) {
		
																				//verificar se o registro existe
		
		Optional<EnderecoEntity> optional = enderecoRepository.findById(id);
		
																				// se o registro existir
		if (optional.isPresent() == true) {
			EnderecoEntity enderecoBD = optional.get();
			enderecoBD.setCep( novoEndereco.getCep() );
			enderecoBD.setCidade( novoEndereco.getCidade() );
			enderecoBD.setEstado(novoEndereco.getEstado());
			enderecoBD.setNumero(novoEndereco.getNumero());
			enderecoBD.setRua( novoEndereco.getRua());
			enderecoBD.setUsuario( novoEndereco.getUsuario()); 
			
			return enderecoRepository.save(enderecoBD).toTDO();						
		}
		else {
			return new EnderecoEntity().toTDO();
		}
	}
	//__________________________________________ Deletar um endereço_________________________________________________
	public void delete(int id) {
		enderecoRepository.deleteById(id);
	}
}
