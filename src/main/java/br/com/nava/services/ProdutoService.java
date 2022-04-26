package br.com.nava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entities.ProdutoEntity;
import br.com.nava.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	//__________________________________________ Mostrar Todos____________________________________________________
	public List<ProdutoEntity> getAll(){
		return produtoRepository.findAll();
	}
	//__________________________________________ Mostrar apenas 01_________________________________________________
	public ProdutoEntity getOne(int id) {
		return produtoRepository.findById(id).orElse(new ProdutoEntity());
	}
	//__________________________________________ INSERIR um novo ___________________________________________________
	public ProdutoEntity save(ProdutoEntity produto) {
		return this.produtoRepository.save(produto);
	}
	//__________________________________________ Atualizando os dados / UPDATE______________________________________
	public ProdutoEntity update(int id, ProdutoEntity novoProduto) {
		
		Optional<ProdutoEntity> optional = produtoRepository.findById(id);
		
		if (optional.isPresent()) {
			ProdutoEntity produto = optional.get();
			produto.setDescricao( novoProduto.getDescricao() );
			produto.setNome( novoProduto.getNome() );			
			produto.setPreco( novoProduto.getPreco() );
			
			return produtoRepository.save(produto);
		}else {
			return new ProdutoEntity();
		}
	}
	//__________________________________________ Deletar um endereço__________________________________________________
	public void delete(int id) {
		produtoRepository.deleteById(id);
	}
}
