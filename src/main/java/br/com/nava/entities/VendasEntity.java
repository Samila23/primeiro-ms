package br.com.nava.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	
@Data 								//Inclui todos os Gte e Set
@AllArgsConstructor					//Inclui os construtores com todos os paramentros
@NoArgsConstructor					//Incluir o contrutores padrão
@Entity 							//@Entity é o mapeamento entre a classe e tabela
@Table(name="VENDAS") 				//@Table passa o nome da tabela do banco que receberá os dados , caso nao ele procura o nome da tabela com o nome da classe
public class VendasEntity {
	@Id							 	// especifica qual é a chave primaria independente da descrição da chave primaria 
	@GeneratedValue (strategy = GenerationType.IDENTITY) // Avisa que o Banco irá gerar o ID e como será gerado o cod ID
	private Integer id;
	@Column(name= "VALOR_TOTAL") 	//Fazendo referencia a coluna no BD, exatamente como está escrita no BD
	private float valorTotal;
	
	
	//__________________________________________Relacionamento de classes VENDA E USUARIOS ___________________________________
	@ManyToOne						 //Muitas vendas para 01 Usuario
	@JoinColumn(name = "USUARIO_ID") // /vai na entidade mais forte :  informa qual o nome da coluna que é a chave que faz o relacionamento com a tabela UsuarioEntity
	private UsuarioEntity usuario;
	
	
	

	
	//__________________________________________Relacionamento de classes VENDA E PRODUTO ___________________________________
	@ManyToMany(mappedBy = "vendas") //Muitas vendas, para muitos produtos
	private List<ProdutoEntity> produtos;
}
