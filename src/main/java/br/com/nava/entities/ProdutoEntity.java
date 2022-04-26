package br.com.nava.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 																						//Inclui todos os Gte e Set
@AllArgsConstructor 																		//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																			//Incluir o contrutores padrão

@Entity 																					//@Entity é o mapeamento entre a classe e tabela

@Table(name="PRODUTOS")																		//@Table passa o nome da tabela do banco que receberá os dados , caso nao ele procura o nome da tabela com o nome da classe
public class ProdutoEntity {
	@Id 																					// especifica qual é a chave primaria independente da descrição da chave primaria 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 									// Avisa que o Banco irá gerar o ID e como será gerado o cod ID
	private Integer id;
	private String nome;
	private String descricao;
	private float preco;
	
	
	//__________________________________________Relacionamento de classes VENDA E PRODUTO ___________________________________	

	@ManyToMany 																			// Muitos Produtos para muitas Vendas
	@JoinTable(
			name = "VENDA_PRODUTO",
			joinColumns = @JoinColumn(name = "PRODUTO_ID"),
			inverseJoinColumns = @JoinColumn(name = "VENDA_ID")			
	)
	private List<VendasEntity> vendas;
}
