package br.com.nava.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.VendasEntity;
import br.com.nava.repositories.ProdutoRepository;
import br.com.nava.repositories.VendasRepository;

@Service
public class VendasService {
	@Autowired
	private VendasRepository vendasRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	//__________________________________________ Mostrar Todos____________________________________________________
	public List<VendasEntity> getAll(){
		return vendasRepository.findAll();
	}
	//__________________________________________ Mostrar apenas 01_________________________________________________
	public VendasEntity getOne(int id) {
		return vendasRepository.findById(id).orElse(new VendasEntity());
	}
	//__________________________________________ INSERIR um novo ___________________________________________________
	public VendasEntity save(VendasEntity venda) {
																//primeiro teremos que salvar a venda (para preencher a lista de vendas para cada produto)
		
		VendasEntity vendaSalva = vendasRepository.save(venda);
		
																// depois teremos que alterar a lista de vendas para cada produtos	
																// para cada produto da venda do body, temos que atualizar a venda salva no banco
		
																//todos os produtos da venda
		List<ProdutoEntity> listaProdutos = venda.getProdutos();
		
																// atualizando as vendas para cada produto acima
		
		for(int i = 0; i < listaProdutos.size(); i++) {
																// Arrays.asList(): converte um conjunto de objetos em uma lista
			listaProdutos.get(i).setVendas( Arrays.asList(vendaSalva)  );
		}
		
																//salvando as atualizações no banco de dados
		produtoRepository.saveAll(listaProdutos);
		
		return vendaSalva;
	
	}
	//__________________________________________ Atualizando os dados / UPDATE______________________________________
	public VendasEntity update(int id, VendasEntity novaVenda) {
		
		Optional<VendasEntity> optional = vendasRepository.findById(id);
		
		if (optional.isPresent()) {
			VendasEntity venda = optional.get();
			
			venda.setValorTotal( novaVenda.getValorTotal());
			
			return vendasRepository.save(venda);
		}else {
			return new VendasEntity();
		}
	}
	//__________________________________________ Deletar um endereço__________________________________________________
	public void delete(int id) {
		vendasRepository.deleteById(id);
	}
}
