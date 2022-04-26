package br.com.nava.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data 																						//Inclui todos os Gte e Set
@AllArgsConstructor 																		//Inclui os construtores com todos os paramentros
@NoArgsConstructor 																			//Incluir o contrutores padrão
@Entity 																					//@Entity é o mapeamento entre a classe e tabela
@Table(name="USUARIOS") 																	//@Table passa o nome da tabela do banco que receberá os dados , caso nao ele procura o nome da tabela com o nome da classe
public class UsuarioEntity {
	@Id 																					// especifica qual é a chave primaria independente da descrição da chave primaria 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 									// Avisa que o Banco irá gerar o ID e como será gerado o cod ID
	private int id;
	@Column (name = "nome") 																//Fazendo referencia a coluna no BD, exatamente como está escrita no BD
	private String nome;
	private String email;
	
	//__________________________________________Relacionamento de classes USUARIO E ENDERECO ___________________________________
	@OneToOne 																				// Indica o tipo de mapeamento, indica que terá um relacionamento 01 para 01, com a classe EnderecoEntity
	@JoinColumn(name="endereco_id") 														//vai na entidade mais forte :  informa qual o nome da coluna que é a chave que faz o relacionamento com a tabela UsuarioEntity
	private EnderecoEntity endereco; 														//criando uma chave estrangeira, é necessario criar um objeto no spring

	
	//__________________________________________Relacionamento de classes USUARIO E VENDAS ____________________________
	@JsonIgnore
	//@ToString.Exclude
	@OneToMany(mappedBy = "usuario") 														// 01 usuario tem muitas vendas
	private List<VendasEntity> vendas;
}
